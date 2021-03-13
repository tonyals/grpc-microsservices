echo "############# Configurando RabbitMQ #############"

echo "### Declarando Exchanges ###"

sudo docker exec -it rabbitmq rabbitmqadmin declare exchange name=payment.processor type=direct -u user -p user
sudo docker exec -it rabbitmq rabbitmqadmin declare exchange name=payment.status type=fanout -u user -p user

echo "\n### Declarando Queues ###"
sudo docker exec -it rabbitmq rabbitmqadmin declare queue name=payment.processor.solicitation durable=true -u user -p user
sudo docker exec -it rabbitmq rabbitmqadmin declare queue name=payment.status.cart.service durable=true -u user -p user
sudo docker exec -it rabbitmq rabbitmqadmin declare queue name=payment.status.email.service durable=true -u user -p user

echo "\n### Declarando Binding ###"
sudo docker exec -it rabbitmq rabbitmqadmin declare binding source=payment.processor destination=payment.processor.solicitation routing_key=payment.processor.solicitation -u user -p user
sudo docker exec -it rabbitmq rabbitmqadmin declare binding source=payment.status destination=payment.status.cart.service routing_key=payment.status.cart.service -u user -p user
sudo docker exec -it rabbitmq rabbitmqadmin declare binding source=payment.status destination=payment.status.email.service routing_key=payment.status.email.service -u user -p user
