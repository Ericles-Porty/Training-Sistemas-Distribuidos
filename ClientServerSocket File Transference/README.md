# Implementar um versão simplificada de um servidor web.
## Lado do Servidor:
1) Vai existir um diretório chamado “arquivos” onde vai conter arquivos HTML exemplo:
índex.html, etc...
2) O servidor deverá ser capaz de receber varias conexões simultaneamente, ou seja, o
servidor é capaz de atender a vários clientes ao mesmo tempo.
3) Cada conexão estabelecida com um cliente dar o direito do mesmo solicitar somente
um arquivo. Essa solicitação deverá ser feita passando o nome do arquivo que o
mesmo deseja.
4) Recebido a solicitação do arquivo, o servidor irá localiza-lo no diretório “arquivos” e o
enviará para o cliente o arquivo desejado e em seguida irá finalizar a conexão e
encerrará a thread que foi criada para aquela conexão.
5) Caso o cliente passe mais de 1 minuto para solicitar um arquivo, o servidor deverá
fechar a conexão e encerrará a thread que foi criada para aquela conexão.

## Lado do Cliente:

1) A interface do cliente irá solicitar um nome do arquivo ao usuário.
2) De posse do nome do arquivo o cliente abrirá uma conexão com o servidor solicitando
o mesmo.
3) A medida que o cliente recebe as informações do servidor ele vai imprimindo o mesmo
na interface ou salvar o arquivo em disco.
4) Caso o servidor demore mais de 1 minuto para responder com o arquivo solicitado, o
cliente deverá encerrar a conexão.
