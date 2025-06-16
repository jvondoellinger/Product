#!/bin/bash

# Configuração LocalStack
LOCALSTACK_URL="http://localhost:4566"
QUEUE_NAME="product-creation-test"


QUEUE_URL=$(aws --endpoint-url=$LOCALSTACK_URL sqs get-queue-url --queue-name $QUEUE_NAME --query "QueueUrl" --output text)

if [ "$QUEUE_URL" == "None" ]; then
  echo "Erro ao obter URL da fila."
  exit 1
fi

echo "Fila localizada com sucesso! URL: $QUEUE_URL"


COUNTER=0
MAX_MESSAGES=1000  
THREADS=8

# Dividir a quantidade total por número de threads
MESSAGES_PER_THREAD=$((MAX_MESSAGES / THREADS))

send_messages() {
  local START=$1
  local END=$2
  for ((i = START; i <= END; i++)); do
    echo "Thread $$ - Enviando mensagem $i para a fila..."

    aws --endpoint-url=$LOCALSTACK_URL sqs send-message \
      --queue-url $QUEUE_URL \
      --message-body "{
        \"publishedBy\": \"test_system-share\",
        \"name\": \"some_name_value\",
        \"amount\": 1000.50
      }" > /dev/null

    echo "Thread $$ - Mensagem $i enviada com sucesso!"
  done
}

# Disparar N threads
COUNTER=1
for ((t = 1; t <= THREADS; t++)); do
  START=$COUNTER
  END=$((COUNTER + MESSAGES_PER_THREAD - 1))
  send_messages $START $END &
  COUNTER=$((END + 1))
done

# Esperar todas as threads terminarem
wait
echo "✅ Envio paralelo concluído com $THREADS threads."

echo "Todas as mensagens foram enviadas!"
