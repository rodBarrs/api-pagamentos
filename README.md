# api-pagamentos

## Visão Geral

 A API de Pagamentos foi desenvolvida para facilitar a gestão de pagamentos.
Ela suporta as seguintes operações: 

- Cadastro de novos pagamentos.
- Atualização do status de pagamentos existentes.
- Listagem de pagamentos com filtros específicos.
- Exclusão lógica de pagamentos com controle de status.

## Requisitos

Certifique-se de que você possui os seguintes requisitos antes de configurar o projeto:

- Java JDK 8 ou superior
- Spring Boot
- Um banco de dados relacional (configuração padrão é H2).

- ## Configuração do Ambiente

1. Clone este repositório em sua máquina local:

   git clone https://github.com/rodBarrs/api-pagamentos.git

2. O banco de dados padrão configurado é o H2. Se quiser alterar para outro banco, atualize as configurações no arquivo application.properties.
   credencias h2: spring.datasource.url=jdbc:h2:mem:api_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
                  spring.datasource.username=sa
                  spring.datasource.password=password

3. Execute o projeto Spring Boot:

   ./mvnw spring-boot:run

4. Por padrão, a API estará acessível em:
    http://localhost:8080

5. Documentação da API está disponível no arquivo api-pagamentos-fadesp.postman_collection, que está na raíz do projeto, e pode ser aberto pelo postman.   



  
