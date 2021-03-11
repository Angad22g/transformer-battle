# transformer-battle


## Build Project

The first step is build project to create .jar

Open the terminal and follow the steps:

```bash
$ cd transformers-battle-api
$ mvn clean package
```
For coverage run the following

```bash
$ mvn clean package -Pcoverage
```
For running on sonar please execute the following command

```bash
$ mvn clean install sonar:sonar -Pcoverage -Dsonar.projectKey=sonar-test -Dsonar.host.url=http://localhost:9000 -Dsonar.login=8afa47eab95751af35c548ecff5c8ba0e9cb9aad 
```

##Run on Intellij
Take the git pull from this link  in the Intellij IDE and open TransformersApplication file.
You will see a run button click on it and wait till you don't see the logs of StartedApplication
The Application is running on 8080 port.

## Run on Docker container

```bash
$ cd project
$ docker build -t transformers-battle-api-1.0.0 .
$ docker run -d -p 8080:8080 -t transformers-battle-api-1.0.0
```

Docker is running on 8080 port.

## Assumption:
I was a little unclear about the special rule which described about the battle winner incase Optimus Prime or Predaking will fight.
Hence, it is assumed that the team with any of the above will win except if both are present or are duplicated.

## Usage


Available while the docker is running

[![View in Swagger](https://raw.githubusercontent.com/jessemillar/view-in-swagger-button/03073fe128d35adfcad35b03b853aa76cfdd9002/button.svg)](http://localhost:8080/transformers-battle-api/swagger-ui/index.html?configUrl=/transformers-battle-api/api-docs/swagger-config#/)
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/4fa3115297cde4d8f249)

