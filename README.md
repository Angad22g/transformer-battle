# Transformers Battle Project


## Build Project

The first step is build project to create .jar

Open the terminal and follow the steps:

```bash
$ cd transformers-battle
$ mvn clean package
```

## Run on Intellij
Take the git pull from this link  in the Intellij IDE and open TransformersApplication file.
You will see a run button click on it and wait till you don't see the logs of StartedApplication
The Application is running on 8080 port.

## Run on Docker container

```bash
$ cd transformers-battle
$ docker build -t transformers-battle-api-1.0.0 .
$ docker run -d -p 8080:8080 -t transformers-battle-api-1.0.0
```

Docker is running on 8080 port.

## Assumption:
I was a little unclear about the special rule which described about the battle winner incase of Optimus Prime/Predaking fighting.
Hence,I assumed that the team with any of the above will win except if both are present or are duplicated.

In case of no battle i have considered the case to be tie as no one has won a battle.

## Usage


Available while the docker is running

[![View in Swagger](https://raw.githubusercontent.com/jessemillar/view-in-swagger-button/03073fe128d35adfcad35b03b853aa76cfdd9002/button.svg)](http://localhost:8080/transformers-battle-api/swagger-ui/index.html?configUrl=/transformers-battle-api/api-docs/swagger-config#/)
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/38159c0dbf18f9b31247)