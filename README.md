# Sobre o kafka-ses
Aplicação na qual é usado o Apache Kafka para gerenciar o envio de emails feito através do Amazon SES. Temos um producer que envia mensagens e um consumer que as lê e envia emails para endereço informado via código. Além disso, o controller nesse app serve para facilitar o envio, precisando apenas informar uma mensagem qualquer para enviar o email.

## Instalando o Kafka
- [Download Kafka](https://kafka.apache.org/downloads)
- [Tutorial: download kafka no mac](https://www.tutorialkart.com/apache-kafka/install-apache-kafka-on-mac/)
```shell
# colocar no .bash_profile ou .zshrc:
alias start_zookeeper="sh ~/kafka_2.13-3.1.0/bin/zookeeper-server-start.sh ~/kafka_2.13-3.1.0/config/zookeeper.properties" 
alias start_kafka="sh ~/kafka_2.13-3.1.0/bin/kafka-server-start.sh ~/kafka_2.13-3.1.0/config/server.properties"
```
Sempre startar primeiro o zookeeper e depois o kafka.

## Criando o projeto
Gerando projeto spring com o [Spring Initializr](start.spring.io), com as dependências:
- Spring Boot DevTools
- Lombok
- Spring Web, e também:
```xml 
<dependency>
	<groupId>org.apache.kafka</groupId>
	<artifactId>kafka_2.13</artifactId>
	<version>3.1.0</version>
</dependency>
<dependency>
	<groupId>org.slf4j</groupId>
	<artifactId>slf4j-api</artifactId>
	<version>1.7.36</version>
</dependency>
<dependency>
	<groupId>ch.qos.logback</groupId>
	<artifactId>logback-classic</artifactId>
	<version>1.2.11</version>
</dependency>
<dependency>
  <groupId>com.amazonaws</groupId>
	<artifactId>aws-java-sdk-ses</artifactId>
	<version>1.12.210</version>
</dependency>
```

## Como rodar
Primeiro, ```start_zookeeper```. Em seguida, em outro terminal ```start_kafka```. Após isso, rode sua aplicação spring na IDE de sua preferência e por fim, acesse a URL: ```http://localhost:{porta}/email/{mensagem qualquer}```
<p>OBS.: é necessário ter conta na AWS, cadastrar e validar um email no SES.</p>
