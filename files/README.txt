BANCO DE DADOS:
- Criar um Banco no Postgres chamado Doberman
- Criar um usuário com o nome doberman e senha doberman
- Rodar o script create-table.sql
- Rodar o script ini-data.sql

SISTEMA:
- Excluir os arquivos secure.jar e basefile.jar (..\html\WEB-INF\lib)
- Copiar o arquivo doberman.jar para ..\html\WEB-INF\lib
- Copiar os arquivos narch.properties e narch-map.properties para ..\html\WEB-INF\classes
- Editar o arquivo narch.properties. Alterar o IP do servidor SQL na chave "narch.con.database"