# profile-ms
## Profile Server

###Instruções em português

Serviço criado para registrar os perfis de usuário do LigadIN.

#### Dependências 
* java 8
* Apache Maven 3.6.3
* Docker
* Docker Compose

Link do tutorial de instalação do maven
https://maven.apache.org/install.html

Link para baixar o maven
https://maven.apache.org/download.cgi

Link para baixar e instalar Docker Compose
https://docs.docker.com/compose/install/

#### Entrar na pasta do projeto e executar os comandos abaixo
mvn -B package --file pom.xml

docker-compose up

#### Uso e Documentação no Swagger

Para ver a documentação da API do projeto basta acessar http://"localhost":8080/swagger-ui.html#/

###English instructions

Service created to register LigadIN user profiles.

#### Dependencies 
* java 8
* Apache Maven 3.6.3
* Docker
* Docker Compose

Maven installation tutorial link
https://maven.apache.org/install.html

Link to download maven
https://maven.apache.org/download.cgi

Link to download and install Docker Compose
https://docs.docker.com/compose/install/

#### Enter the project folder and execute the commands below
mvn -B package --file pom.xml

docker-compose up

#### Swagger Usage and Documentation

To view the project's API documentation, simply access http://"localhost":8080/swagger-ui.html#/


