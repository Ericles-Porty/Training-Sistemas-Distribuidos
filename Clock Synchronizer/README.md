# Implementação do algoritmo de Berkeley utilizado para sincronização de relógios físicos.

1. Implemente um protocolo que permita que um grupo de processos consigam sincronizar seus relógios físicos

2. Deverá ser empregado o algoritmo de Berkeley.

3. O grupo poderá ter qualquer quantidade de processos.

4. Cada processo deverá ter um identificador único no formato de um número inteiro.

5. Depois que os processos forem criados você pode definir manualmente quem será o servidor de tempo para fins de teste do algoritmo ficando a seu critério como será definido esse procedimento.

6. Após essa etapa faça a simulação de sincronização dos relógios entre os processos

7. Não será necessário tratar problemas de queda de servidor de tempo, ou seja, implemente utilizando a hipótese que o servidor tempo nunca ficará fora do ar.

## Instruções

### No arquivo Configs.java pode se alterar:

1.  O número de Processos/Threads/Clients ao mudar o valor da variável numberOfSockets.

2.  O tempo inicial do servidor pode mudar ao alterar o valor da variável serverInitialTimestamp.

3.  O tempo inicial do primeiro Client pode mudar ao alterar o valor da variável clientInitialTimestamp.

4.  O tempo que sera incrementado a cada novo serviço pode mudar ao alterar ao valor da variável incrementTimestamp.

### Para executar o sistema:

1. Executar o Server.java no terminal.

2. Executar o Client.java em outro terminal.

3. Analisar nos terminais se os horários foram sincronizados.
