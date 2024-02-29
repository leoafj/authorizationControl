# Procedure control

O Procedure control é uma aplicação para realizar o controle de autorizações de um plano de saúde nele você pode registrar, atualizar, deletar e cancelar autorizações.

## 🚀 Começando

Essas instruções permitirão que você obtenha uma cópia do projeto em operação na sua máquina local para fins de desenvolvimento e teste, na pasta raiz, contém um script.sql para inserir dados iniciais por ele.


### 📋 Pré-requisitos

Para executar o projeto, é necessário ter:
1. Docker desktop
2. mysql container
3. wildfly container
4. Intellij

## 📦 Como rodar a aplicação(Docker)

1. Antes de tudo, devemos clonar o projeto:
```
Clone este repositório: git clone https://github.com/leoafj/authorizationControl.git
```

3. No terminal inicialize o banco de dados mysql:
```
docker run --name mysql_zitrus -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:8
```
4. Rode o script zitrus.sql para termos alguns dados iniciais:
```
docker cp zitrus.sql mysql_zitrus:/zitrus.sql
docker exec -it mysql_zitrus mysql -p
digite a senha: root
source /zitrus.sql
aperte crtl+D para sair
```
5. Acesse o projeto na IDE, abra o connection factory e altere o localhost pelo seu endereço, 
rode o comando abaixo no powershell, copie o ip gerado e cole no localhost no connection factory:
```
docker inspect --format='{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' mysql_zitrus
```

6. Rode o build da imagem:
```
docker build -t procedure .
```

7. Rode o comando para iniciar a imagem:
```
docker run --name procedure_control -p 8080:8080 -p 9990:9990  procedure
```

8. Acesse a interface da aplicação pelo endereço:
```
http://localhost:8080/procedureControl/control
```

9. Utilize o projeto e faça suas autorizações


## 📌 Versão

É utilizado o padrão [Keep a Changelog](https://keepachangelog.com/en/1.0.0/) para controle de versão.

## ✒️ Autores



* **Leonardo** - *Desenvolvedor* - [Linkedin - Leonardo ](https://linkedin.com/in/leoafj)





