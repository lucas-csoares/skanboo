# sKanboo

**Ana Corina Damas Batista | acorina59@gmail.com**

**Giovanna Ferreira dos Santos de Almeida | gfsalmeida@sga.pucminas.br**

**Guilherme Lage da Costa | guilhermeldcosta@gmail.com**

**Lucas Cabral Soares | 1425177@sga.pucminas.br**

**Vítor Lagares Stahlberg | vitorls2001@gmail.com**

---

Professores:

**Hugo Bastos de Paula**

**Eveline Alonso Veloso**

---

_Curso de Engenharia de Software, Unidade Praça da Liberdade_

_Instituto de Informática e Ciências Exatas – Pontifícia Universidade de Minas Gerais (PUC MINAS), Belo Horizonte – MG – Brasil_

---

_**Resumo**. Escrever aqui o resumo. O resumo deve contextualizar rapidamente o trabalho, descrever seu objetivo e, ao final, mostrar algum resultado relevante do trabalho (até 10 linhas)._

---

## 1. Introdução

### 1.1 Contextualização

O escambo é um modelo de comércio caracterizado pela troca direta de bens entre pessoas, sem o uso de dinheiro. Nesse tipo de negociação, cada uma das partes oferece algo que possui em troca de algo que necessita.

Apesar de ser uma prática antiga, o escambo vem ganhando cada vez mais força atualmente, em razão da crescente preocupação com a sustentabilidade e aplicação de premissas da economia circular[1], que prevê o uso e reuso de um recurso. Não obstante, o cenário de incerteza financeira gerado durante o período de pandemia fez com que diversas pessoas buscassem formas mais baratas de suprir as suas necessidades. Em [reportagem](https://g1.globo.com/sp/santos-regiao/noticia/2022/07/27/trocas-por-produtos-servicos-e-alimentos-escambo-ganha-forca-em-grupo-no-litoral-de-sp.ghtml) realizada pelo site G1 em julho de 2022, foi descrito um grupo de troca de objetos do Facebook que contava com mais de 6 mil usuários. Segundo a reportagem, o período delicado da pandemia foi a principal motivação para criação do grupo.

O crescimento dos hábitos de consumo da população se mostrou mais expressivo a partir da transição gerada pela Revolução Industrial, que ocorreu em meados do século XVIII, segundo as Rotas Culturais do Conselho Europeu _European Route of Industrial Heritage_[2]. Desde então, acumular objetos tornou-se quase um hábito na vida de diversas pessoas, objetos estes que, muitas vezes, estão em completo desuso. Para fins de exemplo, um índice comumente utilizado tem relação com o número de furadeiras para uso doméstico. Em grande parte das residências, é muito comum que os seus moradores tenham ao menos uma furadeira guardada. No entanto, o que muitos não sabem, é que durante toda a sua vida útil, essa furadeira é usada, em média, por apenas 40 minutos, segundo a Instituição Akatu[3]. Ou seja, um produto que na maior parte da sua vida útil, ficará em desuso.

Portanto, ao fazer escambo, as pessoas podem ser desfazer de bens que já possui (e que por ventura não utiliza), por outros que precisa, ganha mais flexibilidade por poder negociar diretamente com a outra parte, e também é uma forma mais sustentável de consumir.

### 1.2 Problema

Um problema comum enfrentado por muitas pessoas é o fato de querer adquirir novos objetos, seja por uma questão de necessidade ou desejo, mas ao mesmo tempo já possuírem muitos objetos em casa. Essa situação pode gerar uma sensação de culpa por gastar dinheiro em algo que talvez não seja realmente necessário.

Além disso, é comum as pessoas se interessarem por algo que excede seu orçamento. Em algumas situações, o indivíduo acaba se endividando para comprar o produto que deseja, o que pode gerar problemas financeiros.

Com isso, surgi a alternativa de trocar objetos com pessoas de seu entorno. No entanto, ele se depara com outro problema: A dificuldade de encontrar alguém interessado em adquirir os seus itens e que não possui o que ele deseja.

Partindo desse pressuposto, seria possível criar uma plataforma na qual as pessoas conseguissem se desfazer daqueles objetos que possuem e não usam, podendo obter o que querem sem ter que gastar para isso? Esse é o problema que será analisado e desenvolvido no projeto apresentado a seguir.


### 1.3 Objetivo geral

O objetivo geral do projeto em questão é desenvolver uma aplicação web que permita a troca de objetos entre os seus usuários.

#### 1.3.1 Objetivos específicos

Para desenvolver a aplicação descrita acima, mapeamos os seguintes objetivos específicos:

- Levantar os principais processos relacionados à troca de objetos;
- Desenvolver um banco de dados com as informações dos usuários cadastrados e objetos publicados;
- Mapear os objetos de maior interesse dos usuários da plataforma.

### 1.4 Justificativas

A aplicação **sKanboo** irá permitir que o usuário negocie e realize a troca de objetos em comum acordo, possibilitando que os usuários cheguem em um consenso sobre a melhor forma de realizar a troca  de modo a facilitar a aquisição de novos produtos. A plataforma **sKanboo** é uma alternativa que não envolve o uso de dinheiro para obter novos produtos, sendo ideal para aqueles que estão interessados em algo, mas não possuem o dinheiro no momento.

## 2. Participantes do processo

Foram mapeados dois papeis no processo de troca, **negociante 1** e **negociante 2**, cujas atividades são descritas a seguir: 

**Negociante 1**

    1.1 Publica seu produto na plataforma; 
    1.2 Aceita ou recusa ofertas;
    1.3 Informar se quer receber um produto específico ou outras ofertas.

**Negociante 2**

    2.1 Oferece um objeto seu em troca de outro publicado no site;
    2.2 Caso sua oferta seja aceita ele pode conversar com o negociante 1.

Além disso, ambos os participantes podem, ao final do processo de troca, avaliar como foi negociar com um determinado usuário, de modo que os bons negociantes sejam reconhecidos.

## 3. Modelagem do processo de negócio

### 3.1. Análise da situação atual

Os sistemas que encontramos no mercado atualmente, abordam a troca de bens somente através do uso de dinheiro. Nesses modelos, caso uma pessoa esteja interessada em um determinado objeto divulgado em um site de vendas, mas não tenha o dinheiro necessário, a negociação não poderá ocorrer. Além disso, pessoas que possuem uma grande quantidade de bens guardados, sem serem utilizados, terão de vendê-los para depois comprar algo que desejam, o que pode ser inconveniente, além de demorado.

A proposta do **sKanboo** é inovadora pois, conforme citado no tópico [1.2](#12-problema), oferece uma alternativa de se realizar a troca de objetos sem o uso de dinheiro, sendo através da troca mútua de objetos.

### 3.2. Descrição Geral da proposta

A proposta deste projeto é ampliar as maneiras de se realizar trocas utilizando os benefícios do mundo online. Isso porque a realização do escambo atualmente é limitada ao "boca-a-boca", ou seja, é necessário que alguém do círculo social do negociante esteja interessado no produto que ele tem a ofertar para que essa troca seja realizada. O que está sendo proposto é a ampliação do número de pessoas que o usuário pode alcançar para além da sua comunidade próxima, extendo para todos que tenham acesso ao sistema e interesse em trocar um objeto que não utilizam pelo que o usuário está ofertando. 

Contudo, existem algumas limitações à criação do projeto. Com relação a adesão de usuários, considerando que seria a implementação de um novo modelo que não inclui a opção de trocas utilizando dinheiro ou qualquer tipo de moeda de troca que não seja outro objeto, corre-se o risco de não ser aderido. Também há de se pensar na segurança do usuário, ou seja, na forma com que estas trocas iriam ocorrer, ou pelo menos o que seria permitido dentro das políticas do sistema, que também pode ser uma limitação tecnológica, pois dependendo da maneira que a questão for abordada, poderia gerar complicações no rastreamento do produto (onde está, previsão de entrega, previsão de troca, dentre outros). 

### 3.3. Modelagem dos Processos

#### 3.3.1 Processo 1 – NOME DO PROCESSO

Apresente aqui o nome e as oportunidades de melhorias para o processo 1. Em seguida, apresente o modelo do processo 1, descrito no padrão BPMN.

![Exemplo de um Modelo BPMN do PROCESSO 1](imagens/process.png 'Modelo BPMN do Processo 1.')

### 3.3.2 Processo 2 – NOME DO PROCESSO

Apresente aqui o nome e as oportunidades de melhorias para o processo 2. Em seguida, apresente o modelo do processo 2, descrito no padrão BPMN.

![Exemplo de um Modelo BPMN do PROCESSO 2](imagens/call_process.png 'Modelo BPMN do Processo 2.')

## 4. Projeto da Solução

### 4.1. Detalhamento das atividades

Descrever aqui cada uma das propriedades das atividades de cada um dos processos. Devem estar relacionadas com o modelo de processo apresentado anteriormente.

#### Processo 1 – NOME DO PROCESSO

**Nome da atividade 1**

| **Campo**       | **Tipo**                                                                                                      | **Restrições**         | **Valor default** |
| --------------- | ------------------------------------------------------------------------------------------------------------- | ---------------------- | ----------------- |
| [Nome do campo] | [Área de texto, Caixa de texto, Número, Data, Imagem, Seleção única, Múltipla escolha, Arquivo, Link, Tabela] |                        |                   |
| **_Exemplo:_**  |                                                                                                               |                        |
| login           | Caixa de Texto                                                                                                | formato de e-mail      |                   |
| senha           | Caixa de Texto                                                                                                | mínimo de 8 caracteres |                   |

**Nome da atividade 2**

| **Campo**       | **Tipo**                                                                                                      | **Restrições** | **Valor default** |
| --------------- | ------------------------------------------------------------------------------------------------------------- | -------------- | ----------------- |
| [Nome do campo] | [Área de texto, Caixa de texto, Número, Data, Imagem, Seleção única, Múltipla escolha, Arquivo, Link, Tabela] |                |                   |
|                 |                                                                                                               |                |

#### Processo 2 – NOME DO PROCESSO

**Nome da atividade 1**

| **Campo**       | **Tipo**                                                                                                      | **Restrições** | **Valor default** |
| --------------- | ------------------------------------------------------------------------------------------------------------- | -------------- | ----------------- |
| [Nome do campo] | [Área de texto, Caixa de texto, Número, Data, Imagem, Seleção única, Múltipla escolha, Arquivo, Link, Tabela] |                |                   |
|                 |                                                                                                               |                |

**Nome da atividade 2**

| **Campo**       | **Tipo**                                                                                                      | **Restrições** | **Valor default** |
| --------------- | ------------------------------------------------------------------------------------------------------------- | -------------- | ----------------- |
| [Nome do campo] | [Área de texto, Caixa de texto, Número, Data, Imagem, Seleção única, Múltipla escolha, Arquivo, Link, Tabela] |                |                   |
|                 |                                                                                                               |                |

### 4.2. Tecnologias

Descreva qual(is) tecnologias você vai usar para resolver o seu problema, ou seja implementar a sua solução. Liste todas as tecnologias envolvidas, linguagens a serem utilizadas, serviços web, frameworks, bibliotecas, IDEs de desenvolvimento, e ferramentas. Apresente também uma figura explicando como as tecnologias estão relacionadas ou como uma interação do usuário com o sistema vai ser conduzida, por onde ela passa até retornar uma resposta ao usuário.

## 5. Modelo de dados

Apresente o modelo de dados por meio de um modelo relacional ou Diagrama de Entidade-Relacionamento (DER) que contemple todos conceitos e atributos apresentados item anterior.

![Diagrama de Entidade Relacionamento de Exemplo](imagens/er_diagram.png 'Diagrama de Entidade Relacionamento de Exemplo')

## 6. Indicadores de desempenho

Apresente aqui os principais indicadores de desempenho e algumas metas para o processo. Atenção: as informações necessárias para gerar os indicadores devem estar contempladas no diagrama de classe. Colocar no mínimo 5 indicadores.

Usar o seguinte modelo:

| **Indicador**               | **Objetivos**                                                         | **Descrição**                                             | **Cálculo**                                                                                                                        | **Fonte dados**     | **Perspectiva**           |
| --------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ------------------- | ------------------------- |
| Percentual reclamações      | Avaliar quantitativamente as reclamações                              | Percentual de reclamações em relação ao total atendimento |                                                                                                                                    | Tabela reclamações  | Aprendizado e Crescimento |
| Taxa de Requisições abertas | Melhorar a prestação de serviços medindo a porcentagem de requisições | Mede % de requisições atendidas na semana                 | ![\frac{\sum{atendidas}}{\sum{requisicoes}}100](https://latex.codecogs.com/svg.latex?\frac{\sum{atendidas}}{\sum{requisicoes}}100) | Tabela solicitações | Processos internos        |
| Taxa de entrega de material | Manter controle sobre os materiais que estão sendo entregues          | Mede % de material entregue dentro do mês                 |                                                                                                                                    | Tabela Pedidos      | Clientes                  |

Obs.: todas as informações para gerar os indicadores devem estar no diagrama de classe **a ser proposto**

## 7.Sistema desenvolvido

Faça aqui uma breve descrição do software e coloque as principais telas com uma explicação de como usar cada uma.

## 8. Conclusão

Apresente aqui a conclusão do seu trabalho. Discussão dos resultados obtidos no trabalho, onde se verifica as observações pessoais de cada aluno. Poderá também apresentar sugestões de novas linhas de estudo.

# REFERÊNCIAS

Verifique no link abaixo como devem ser as referências no padrão ABNT:

http://www.pucminas.br/imagedb/documento/DOC\_DSC\_NOME\_ARQUI20160217102425.pdf

**[1]** - _Economia Circular - O que é a Economia Circular? ([s.d.]). Nomia.pt. Recuperado 2 de março de 2023, de https://eco.nomia.pt/pt/economia-circular/estrategias_

**[2]** - _European route of industrial heritage - cultural routes - publi.coe.int. ([s.d.]). Cultural Routes. Recuperado 2 de março de 2023, de https://www.coe.int/en/web/cultural-routes/european-route-of-industrial-heritage_

**[3]** - _Akatu, E. (2012, outubro 11). Como o consumo colaborativo pode revolucionar uma cidade. Instituto Akatu; Akatu. https://akatu.org.br/como-o-consumo-colaborativo-pode-revolucionar-uma-cidade/_

# APÊNDICES

**Colocar link:**

Do código (armazenado no repositório);

Dos artefatos (armazenado do repositório);

Da apresentação final (armazenado no repositório);

Do vídeo de apresentação (armazenado no repositório).

