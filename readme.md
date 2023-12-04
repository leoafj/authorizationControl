# VoteSystem

O VoteSystem é uma aplicação java para gerenciar sessões, com ela podemos criar várias sessões com tempo determinado para votação e votar apenas uma vez por associado, e assim que o tempo de votação acabar, gera o resultado da assembleia.

## 🚀 Começando

Essas instruções permitirão que você obtenha uma cópia do projeto em operação na sua máquina local para fins de desenvolvimento e teste, na pasta raiz, contém uma coleção do postman caso prefira testar por ele.


### 📋 Pré-requisitos

Para executar o projeto, é necessário ter:
1. intellij
2. mysql
3. wildfly


## 📦 Como rodar a aplicação

1. Antes de tudo, devemos clonar o projeto:
```
Clone este repositório: git clone https://github.com/leoafj/authorizationControl.git
```

2. Acesse o do projeto:
```
utilize o intellij para abrir o projeto
```

3. Abra um terminal dentro do intellij no diretorio do projeto:
```
$ mvn clean install -U
```

4. Instale o Jboss/wildfly 29.0.1.Final no link https://www.wildfly.org/downloads/:
```
$ Extraia a pasta
```

5. Vá em File > Settings e procure por Application Servers.

Clique no + e depois em JBoss/WildFly Server, especifique o caminho do JBoss/WildFly e clique em OK e a seguir em Apply.

Após isso vá em Run > Edit Configurations e clique em + e adicione um Jboss/Wildfly Server Local.

Na aba Server configure a URL para ser: http://localhost:8080/procedureControl-1.0-SNAPSHOT/control e por fim vá na aba Deployment e adicione o Artifact desafio-zitrus:war exploded.

6. Conecte-se ao localhost no mysql utilizando o intellij ou workbench:
```
usuario: root
password: root
```

7. Rode o script zitrus.sql para termos alguns dados iniciais:

8. Inicie o projeto para realizar as autorizações


## 📌 Versão

É utilizado o padrão [Keep a Changelog](https://keepachangelog.com/en/1.0.0/) para controle de versão.

## ✒️ Autores



* **Leonardo** - *Desenvolvedor* - [Leonardo](https://github.com/viniciius083)





