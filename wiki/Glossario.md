# Glossário Projeto Semestre4 LEI-ISEP

Alguns conceitos foram retirados do enunciado do projeto (LEI-2019-20-Sem4-SistemaDesenvolver.pdf disponível na UC de LAPR4), no entanto foram aqui colocados por terem relevância para o nosso modelo domínio e para o projeto que estamos a desenvolver. Reconhecemos que não são da nossa autoria, daí estarmos a identificar a sua origem.

- **Administrador:** coordena os restantes gestores.
- **Categoria:** categoria de matérias primas em que se encontra um tipo de matéria prima.
- **Consumo:** indica que deu entrada na máquina uma determinada quantidade de matéria-prima cuja origem é um depósito ou a máquina imediatamente anterior a si na linha de produção. A matéria-prima e a respetiva quantidade constam sempre na mensagem. Quando a origem é um depósito, a identificação do mesmo também consta na mensagem. Caso contrário, nenhuma máquina é identificada;
- **Depósito:** corresponde a um local onde são armazenados os produtos e as matérias-primas. Estes locais são comuns a todas as linhas de produção existentes na fábrica.
- **Desperdício:** matéria prima que não foi utilizada na produção de uma ordem de produção e pode ser reutilizada ou descartada, no entanto fica em stock como desperdício.
- **Entrega de Produção:** indica que a máquina entregou num depósito uma determinada quantidade de produto. O produto e a respetiva quantidade bem como a identificação do depósito constam sempre na mensagem. Opcionalmente, pode constar informação sobre o lote;
- **Estorno:** indica que deu saída da máquina uma determinada quantidade de matéria-prima cujo destino é um depósito. A matéria-prima e a respetiva quantidade bem como a identificação do depósito constam sempre na mensagem. Este tipo de mensagem distingue-se de “Entrega de Produção” por ser gerada em resultado de um consumo prévio e excessivo de matéria-prima ou em resultado de “desperdícios” gerados aquando da produção do produto pretendido e que são posteriormente reutilizáveis.
- **Fábrica:** é um local estruturado em linhas de produção com vista ao fabrico de um ou mais produtos.
- **Ficha de Produção:** corresponde à lista de matérias-primas e respetivas quantidades usadas para produzir uma quantidade standard (e.g. 1 tonelada; 100 unidades) de um dado produto.
- **Fim de Atividade:** indica que a máquina concluiu a sua operação no atual contexto. Opcionalmente, o contexto atual consta na mensagem enviada pela máquina em causa;
- **Gestor de Produção:** gere a produção total da fábrica, como eventuais problemas ou conflitos nas máquinas.
- **Gestor do Chão de Fábrica:** gere a fábrica, recursos humanos, problemas na mesma ou necessidades que surjam relativamente a todos os aspetos.
- **Inicio de Atividade:** indica que a máquina começou a operar num novo contexto. Neste caso o contexto, é sempre uma ordem de produção. A informação referente ao contexto nem sempre consta na mensagem enviada pela máquina em causa;
- **Linha de Produção:** organização sequencial de um conjunto de máquinas.
- **Lote:** corresponde a uma característica atribuída a um conjunto de exemplares de um produto.
- **Máquina:** é um equipamento produtivo capaz de realizar operações com vista a produzir um produto.
- **Matéria-Prima:** corresponde a um material e/ou produto usado no processo de fabrico de um ou mais produtos.
- **Mensagem (no âmbito de Máquina):** corresponde a um conjunto de dados gerado pela máquina e devidamente estruturado de acordo com um determinado tipo de mensagem.
- **Morada:** morada associada a uma encomenda para faturação/entrega.
- **Movimento de Stock:** corresponde à informação que regista a saída ou entrada de uma determinada quantidade de matéria-prima ou produto (em conformidade com uma unidade de medida) num determinado depósito em consequência da execução de uma dada ordem de produção.
- **Ordem de Produção:** documento em que se autoriza/solicita a produção de um produto numa determinada quantidade (a pretendida) através de um conjunto de matérias-primas e respetivas quantidades (de referência).
- **Papel:** papel do utilizador do sistema (a sua função), como por exemplo gestor de chão de fábrica ou administrador ou gestor de produção.
- **Paragem Forçada:** indica que a máquina interrompeu temporariamente a sua operação. Neste tipo de mensagens a máquina reporta sob a forma de um código a causa de paragem (e.g. interrupção solicitado pelo controlador; avaria; matéria-prima insuficiente; etc...);
- **Produção:** indica que a máquina produziu (em resultado da transformação por si aplicada) uma determinada quantidade de produto. O produto e a respetiva quantidade constam sempre na mensagem. Opcionalmente, pode constar informação sobre o lote;
- **Produto:** corresponde a um item que uma fábrica é capaz de produzir. Nalguns casos, um produto pode ser utilizado como matéria-prima para a produção de outro produto.
- **Retoma de Atividade:** indica que após uma paragem forçada a máquina retomou a sua operação;
- **Sistema Externo:** sistema que trata o envio de encomendas para o sistema da fábrica, assim como a gestão dos clientes.
- **Tempo Bruto (de execução):** corresponde à diferença entre a data/hora indicada numa mensagem de “Fim de Atividade” (e.g. 10h13) e a data/hora indicada numa outra mensagem de “Inicio de Atividade” (e.g. 10h00). Neste exemplo, o tempo bruto seria de 13 minutos.
- **Tempo Efetivo (de execução):** corresponde ao tempo bruto (de execução) subtraído do somatório do tempo despendido em paragens. O tempo de paragem corresponde à diferença entre a data/hora indicada numa mensagem de “Retoma de Atividade” (e.g. 10h09) e a data/hora indicada numa outra mensagem de “Paragem Forçada” (e.g. 10h07). Neste exemplo, o tempo de paragem seria de 2 minutos. Assim, considerando um tempo bruto de 13 minutos, o tempo efetivo seria de 11 minutos.
- **Tempo:** tempo total em que algo efetuado, para efeitos de contabilização de produção. Por exemplo: 30m / 500s / 100m.
- **Timestamp:** indica uma data e hora num formato apenas, utilizado para indicar uma data de início e de fim, ou de ocorrência. Por exemplo: 01/04/2020 14h30m55s.
- **Tipo de Mensagem (no âmbito de Máquina):** corresponde a um valor que permite determinar/classificar que género de conteúdo e estrutura consta de uma mensagem.
- **Unidade de Medida:** alguns exemplos são: (i) unidades; (ii) quilogramas; (iii) metro cúbico.


##### Algumas considerações sobre o nosso MD:
Os tempos efetivos e tempos brutos serão calculados a partir dos dados disponíveis pelas mensagens das máquinas. O mesmo se verifica com os desperdícios, no entanto estes são calculados a partir dos valores recebidos em sistema pelas mensagens de estorno.
