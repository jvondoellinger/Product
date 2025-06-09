#!/bin/bash

# Configuração LocalStack
LOCALSTACK_URL="http://localhost:4566"
QUEUE_NAME="product-creation-test"

# Obter a URL da fila
QUEUE_URL=$(aws --endpoint-url=$LOCALSTACK_URL sqs get-queue-url --queue-name $QUEUE_NAME --query "QueueUrl" --output text)

if [ "$QUEUE_URL" == "None" ]; then
  echo "Erro ao obter URL da fila."
  exit 1
fi

echo "Fila localizada com sucesso! URL: $QUEUE_URL"

# Enviar mensagens em um loop while
COUNTER=0
MAX_MESSAGES=100  # Defina o número máximo de mensagens que deseja enviar

while [ $COUNTER -lt $MAX_MESSAGES ]
do
  COUNTER=$((COUNTER + 1))

  # Enviar uma mensagem com um corpo JSON
  echo "Enviando mensagem $COUNTER para a fila..."

  aws --endpoint-url=$LOCALSTACK_URL sqs send-message \
    --queue-url $QUEUE_URL \
    --message-body '{
      "publishedBy": "test_system-share",
      "name": "some_name_value",
      "amount": 1000.50
    }' &

  echo "Mensagem $COUNTER enviada com sucesso!"

done

echo "Todas as mensagens foram enviadas!"
