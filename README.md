# [WIP] Este exemplo ainda está em desenvolvimento e foi testado somente em ambiente Linux Ubuntu.

## Fluxo do exemplo

<img src="./img/grpc-microsservices-schema.png" alt="Schema"/>

### RabbitMQ e Protocol Buffers

Este exemplo utiliza o [Protocol Buffers](https://developers.google.com/protocol-buffers) para serializar os dados enviados para as filas RabbitMQ. Os formatos de mensagem foram definidos nos arquivos .proto e a serialização 
padrão do [Micronaut (Jackson)](https://micronaut-projects.github.io/micronaut-rabbitmq/latest/guide/#serdes) foi
sobrescrita para atender ao propósito deste exemplo. Os arquivos com a nova implementação estão no diretório /config dos serviços que comunicam com a fila.


#### Como executar

- 1º No diretório raiz, execute o docker-compose.yml para subir as instâncias do RabbitMQ e Postgres;

- 2º Com todos os contêineres em execução, agora precisamos configurar as exchanges e filas do RabbitMQ, para isso
faça o seguinte:
    - `sudo chmod +x configure-rabbitmq.sh`
    - `./configure-rabbitmq.sh`

- 3º Com o ambiente preparado, importe todos os projetos em sua IDE de preferência, execute a task `generateProto` do Gradle em cada um deles e inicie as aplicações.
- 4º Para testar a comunicação utilize um client gRPC como o [Insomnia](https://insomnia.rest/), importe o arquivo
**order-service.proto** que está no diretório **shopping-cart-service/src/proto**, configure a porta gRPC como
**0.0.0.0:50051** e selecione o método **order**. O corpo da requisição deverá conter este formato:
``` javascript
{
    "customer": {
	    "id": 2
	},
	"product": {
			"productId": 4,
			"quantity": 4
		}
}
```

Os serviços com banco de dados possuem um script SQL básico que adiciona alguns dados para teste. No caso da requisição acima, o **shopping-cart-service** se comunicará com o **customer-service** e o **product-service** para obter respectivamente os dados de clientes e produtos. Em seguida, ele postará uma solicitação de pagamento
na fila RabbitMQ do **payment-service**, que após processá-lo enviará a resposta para as filas individuais do
**send-email-service** e **shopping-cart-service**. Você pode acompanhar tudo pelos logs impressos nos terminais de
execução dos serviços.
