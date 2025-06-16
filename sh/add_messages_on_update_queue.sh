#!/bin/bash

# ====================
# CONFIGURAÇÕES
# ====================
TABLE_NAME="marketplace-test"  # Nome da tabela DynamoDB
QUEUE_URL="http://localhost:4566/000000000000/product-update-test"
DYNAMO_ENDPOINT="http://localhost:4566"
MAX_JOBS=6

# Exporta para uso nas subshells do 'parallel'
export DYNAMO_ENDPOINT
export QUEUE_URL

# ====================
# FUNÇÃO DE ENVIO
# ====================
send_message() {
  local id="$1"
  local index="$2"
  json_payload=$(cat <<EOF
{
  "id": "$id",
  "publishedBy": "system_test_update_sh",
  "name": "system_test_update_sh",
  "amount": 199999.98
}
EOF
)

  aws sqs send-message \
    --queue-url "$QUEUE_URL" \
    --message-body "$json_payload" \
    --endpoint-url "$DYNAMO_ENDPOINT" > /dev/null

  if [ $? -eq 0 ]; then
    echo "✅ [ $index ] Mensagem enviada para ID: $id"
  else
    echo "❌ Falha ao enviar mensagem para ID: $id"
  fi
}

export -f send_message

# ====================
# SCAN DA TABELA DYNAMODB
# ====================
echo "🔍 Escaneando a tabela $TABLE_NAME..."
aws dynamodb scan \
  --table-name "$TABLE_NAME" \
  --endpoint-url "$DYNAMO_ENDPOINT" \
  --output json > scan_output.json

if [ $? -ne 0 ]; then
  echo "❌ Erro ao escanear a tabela DynamoDB."
  exit 1
fi

# ====================
# EXTRAÇÃO DE IDs
# ====================
jq -r '.Items[].id.S' scan_output.json > ids.txt

if [ ! -s ids.txt ]; then
  echo "⚠️ Nenhum ID encontrado na tabela."
  rm -f ids.txt scan_output.json
  exit 0
fi

# ====================
# ENVIO PARA A FILA EM PARALELO
# ====================
echo "🚀 Enviando mensagens para SQS com até $MAX_JOBS threads..."
awk '{print $0, NR}' ids.txt | parallel -j "$MAX_JOBS" --colsep ' ' send_message {1} {2}

# ====================
# LIMPEZA
# ====================
rm -f ids.txt scan_output.json
echo "🎉 Todos os envios foram concluídos."

