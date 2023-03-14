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

O crescimento dos hábitos de consumo da população se mostrou mais expressivo a partir da transição gerada pela Revolução Industrial, que ocorreu em meados do século XVIII, segundo as Rotas Culturais do Conselho Europeu (_European Route of Industrial Heritage_)[1]. Como resultado de um maior investimento nos setores industriais, a quantidade de mercadorias ofertadas à população cresceu vertiginosamente, estimulando cada vez mais a aquisição de novos produtos. A partir então, acumular objetos se tornou um hábito na vida da grande maioria das pessoas.

O modelo de troca de mercadorias (escambo), apesar de antigo, tem ganhado cada vez mais força devido à crescente preocupação com a sustentabilidade, o que reforça as premissas da economia circular, que prevê o uso e reuso de uma dado item[2]. Além disso, devido à instabilidade financeira causada pela pandemia da COVID-19, muitas pessoas vêm buscado formas mais baratas de suprir suas necessidades. Ou seja, ao fazer escambo a pessoa evita gastar dinheiro por trocar bens que já possui por outros de que necessita, ganha mais flexibilidade por poder negociar diretamente com a outra pessoa, uma vez que não se tem preço fixo como em lojas, e também é uma forma mais sustentável de consumir.

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

O **sKanboo** tem como intenção facilitar a troca de objetos entre as pessoas e fornecer uma plataforma que intermediará todo o processo, trazendo segurança para ambos os [negociantes](#2-participantes-do-processo). É uma alternativa que não envolve dinheiro para obter produtos, ideal para aqueles que estão interessados em algo mas não possuem o dinheiro no momento. Além disso, a aplicação permitirá a negociação direta entre os usuários, dando abertura para que eles cheguem em um consenso sobre a melhor forma de realizar a troca.

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

Os sistemas atuais abordam a troca de bens somente através do uso de dinheiro. Nos modelos atuais, caso uma pessoa esteja interessada em um determinado objeto divulgado em um site de vendas, mas não tenha o dinheiro necessário, a negociação não poderá ocorrer.

A proposta do **sKanboo** é inovadora pois, conforme citado no tópico [1.2](#12-problema), oferece maneiras alternativas de se realizar a troca de objetos sem o uso de dinheiro, sendo através da troca mútua de objetos.

### 3.2. Descrição Geral da proposta

A proposta deste projeto é trazer maneiras alternativas de se realizar trocas para o mundo online, conforme abordado no tópico anterior. Para isso, Para isso, pretende-se criar um sistema de escambo, no qual usuários podem ofertar itens que não são mais utilizados e receberem propostas de troca nas seguintes maneiras: trocando por um outro objeto, que será ofertado por outros usuários da plataforma, ou sendo pago através de uma moeda virtual que ainda será determinada.

Contudo, podemos perceber algumas limitações com relação a adesão de usuários, considerando que seria a implementação de um novo modelo que não inclui a opção de trocas utilizando dinheiro. Porém também com relação à tecnologias, principalmente na implementação da moeda virtual e na forma com que esta interação (moeda-sistema) iria ocorrer.

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

**[1]** - _European route of industrial heritage - cultural routes - publi.coe.int. ([s.d.]). Cultural Routes. Recuperado 2 de março de 2023, de https://www.coe.int/en/web/cultural-routes/european-route-of-industrial-heritage_

**[2]** - _Economia Circular - O que é a Economia Circular? ([s.d.]). Nomia.pt. Recuperado 2 de março de 2023, de https://eco.nomia.pt/pt/economia-circular/estrategias_

**[3]** - _Akatu, E. (2012, outubro 11). Como o consumo colaborativo pode revolucionar uma cidade. Instituto Akatu; Akatu. https://akatu.org.br/como-o-consumo-colaborativo-pode-revolucionar-uma-cidade/_

# APÊNDICES

**Colocar link:**

Do código (armazenado no repositório);

Dos artefatos (armazenado do repositório);

Da apresentação final (armazenado no repositório);

Do vídeo de apresentação (armazenado no repositório).

