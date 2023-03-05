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

O crescimento dos hábitos de consumo da população se mostrou mais expressivo a partir da transição gerada pela Revolução Industrial, que ocorreu em meados do século XVIII, segundo as Rotas Culturais do Conselho Europeu (_European Route of Industrial Heritage_)[1]. Como resultado de um maior investimento nos setores industriais, a quantidade de mercadorias ofertadas à população cresceu vertiginosamente, estimulando cada vez mais a aquisição de novos produtos. A partir dai, acumular objetos se tornou um hábito, o que pode levar a desorganização do espaço doméstico e à dificuldade de encontrar itens de maior necessidade.

O problema gerado por essa visão de consumo, assim como qualquer excesso, é o desperdício. Para fins de exemplo, um índice comumente utilizado é o número de furadeiras para uso doméstico. Em grande parte das residências, é muito comum que seus moradores tenham uma furadeira guardada. No entanto, o que muitos não sabem, é que durante toda a sua vida útil, essa furadeira é usada, em média, por apenas 40 minutos, segundo a Instituição Akatu[2]. Ou seja, um produto que na maior parte de sua vida útil, ficará em desuso. Considerando o cenário descrito, o **sKanboo** foi desenvolvido para permitir que objetos em nossas casas que possam estar em desuso sejam utilizados por outras pessoas. Para isso, propomos um modelo de escambo[3] entre usuários, de modo que ambos possam adquirir o objeto que desejam sem a necessidade de comprar um novo produto.

Então, por que utilizar escambo atualmente?

O modelo de troca de mercadorias (escambo), apesar de antigo, tem ganhado cada vez mais força devido à crescente preocupação com a sustentabilidade. O que, inclusive, está em acordo com as premissas da economia circular, que prevê o uso e reuso de uma dado item[4]. Além disso, é importante mencionar que, devido à instabilidade financeira causada pela pandemia da COVID-19, muitas pessoas buscaram formas mais baratas de suprir suas necessidades. Ou seja, ao fazer escambo a pessoa evita gastar dinheiro por trocar bens que já possui por outros de que necessita, ganha mais flexibilidade por poder negociar diretamente com a outra pessoa, uma vez que não se tem preço fixo como em lojas, e também é uma forma mais sustentável de consumir.

### 1.2 Problema

Conforme introduzido no tópico anterior, o consumo constante de novos bens, hábito comum de grande parte da população, gera diversos problemas por si só, por exemplo o acúmulo de itens em desuso nas casas, gastos desnecessários e desperdício. Pela ótica ambiental, a produção em massa consome recursos naturais, emite gases poluentes e gera resíduos que podem contaminar o solo e a água. Para minimizar esse impacto, é preciso reduzir o consumo, reutilizar e reciclar produtos, princípios da economia circular.

Partindo desse pressuposto, seria possível criar uma plataforma na qual as pessoas conseguissem adquirir novos bens, apenas se desfazendo daqueles que já tem e não usam, economizando dinheiro e, por consequência, de maneira sustentável? Esse é o problema que será analisado e desenvolvido no projeto apresentado a seguir.

### 1.3 Objetivo geral

O objetivo geral deste projeto é desenvolver uma aplicação web que permita que seus usuários realizem trocas de objetos uns com os outros, de maneira simples e sem gastar com isso.

#### 1.3.1 Objetivos específicos

- Facilitar a troca de objetos entre pessoas;
- Fornecer alternativas para obter produtos que não envolvam dinheiro;
- Permitir que os usuários da aplicação negociem diretamente uns com os outros;
- Permitir que os usuários adquiram produtos por meio de uma moeda própria do site.

### 1.4 Justificativas

O escambo, uma prática antiga de troca de mercadorias sem uso de dinheiro, tem se tornado uma alternativa atraente nos dias atuais. Com o consumo desenfreado, as pessoas acumulam muitos objetos em casa, muitos dos quais desnecessários. Ao fazer trocas, é possível se livrar de coisas que não precisamos mais e, ao mesmo tempo, adquirir itens úteis para nosso dia a dia sem gastar dinheiro. Além disso, a prática do escambo ajuda a reduzir o impacto ambiental, uma vez que evita o desperdício e o descarte desnecessário de objetos.

Portanto, o objetivo principal do **sKanboo** é facilitar e incentivar a troca consciente de bens materiais acumulados entre os usuários. Além disso, promove uma reflexão sobre a ótica financeira e do consumo.

## 2. Participantes do processo

Foram mapeados 2 participantes em nossos processos, sendo eles o "comprador" e o "vendedor".

Sobre esses participantes, cabe mencionar que eles não são, de fato, compradores e vendedores, uma vez que não há relação de compra por dinheiro na aplicação. O título de comprador e vendedor diz com relação ao usuário que oferta um produto e ao outro que busca adquiri-lo.

Além disso, em um processo de troca, um mesmo usuário pode assumir o papel de "vendedor" ou "comprador", uma vez que ele pode, ao mesmo tempo que oferece um produto, estar procurando por outro, simultaneamente.

As descrições das atividades de cada uma das partes são as seguintes:

**1. Comprador** 

    1.1. Pode adquirir a moeda própria do site;
    1.2. Trocar um objeto por outro, com um usuário;
    1.2. Adquirir um objeto de outro usuário, por meio da moeda própria do site.

**2. Vendedor**

    2.1 Divulga um produto no site;
    2.2 Troca o produto divulgado por outro;
    2.3 Troca o produto divulgado por moeda própria do site.

## 3. Modelagem do processo de negócio

### 3.1. Análise da situação atual

Os sistemas atuais abordam a troca de bens somente através do uso de dinheiro. Nos modelos atuais, caso uma pessoa esteja interessada em um determinado objeto divulgado em um site de vendas, mas não tenha o dinheiro necessário, a negociação não poderá ocorrer.

A proposta do **sKanboo** é inovadora pois, conforme citado no tópico [1.2](#12-problema), oferece maneiras alternativas de se realizar a troca de objetos sem o uso de dinheiro, sendo através da troca mútua de objetos ou utilizando uma moeda virtual.

### 3.2. Descrição Geral da proposta

A proposta deste projeto é trazer maneiras alternativas de se realizar trocas para o mundo online, conforme abordado no tópico anterior. Para isso, Para isso, pretende-se criar um sistema de escambo, no qual usuários podem ofertar itens que não são mais utilizados e receberem propostas de troca nas seguintes maneiras: trocando por um outro objeto, que será ofertado por outros usuários da plataforma, ou sendo pago através de uma moeda virtual que ainda será determinada.

Contudo, podemos perceber algumas limitações com relação a adesão de usuários, considerando que seria a implementação de um novo modelo que não inclui a opção de trocas utilizando dinheiro. Porém também com relação à tecnologias, principalmente na implementação da moeda virtual e na forma com que esta interação (moeda-sistema) iria ocorrer.

### 3.3. Modelagem dos Processos

#### 3.3.1 Processo 1 – NOME DO PROCESSO

Apresente aqui o nome e as oportunidades de melhorias para o processo 1. Em seguida, apresente o modelo do processo 1, descrito no padrão BPMN.

![Exemplo de um Modelo BPMN do PROCESSO 1](imagens/process.png "Modelo BPMN do Processo 1.")


### 3.3.2 Processo 2 – NOME DO PROCESSO

Apresente aqui o nome e as oportunidades de melhorias para o processo 2. Em seguida, apresente o modelo do processo 2, descrito no padrão BPMN.

![Exemplo de um Modelo BPMN do PROCESSO 2](imagens/call_process.png "Modelo BPMN do Processo 2.")

## 4. Projeto da Solução

### 4.1. Detalhamento das atividades

Descrever aqui cada uma das propriedades das atividades de cada um dos processos. Devem estar relacionadas com o modelo de processo apresentado anteriormente.

#### Processo 1 – NOME DO PROCESSO

**Nome da atividade 1**

| **Campo** | **Tipo** | **Restrições** | **Valor default** |
| --- | --- | --- | --- |
| [Nome do campo] | [Área de texto, Caixa de texto, Número, Data, Imagem, Seleção única, Múltipla escolha, Arquivo, Link, Tabela] |  |  |
| ***Exemplo:***  |    |     |
| login | Caixa de Texto | formato de e-mail |  |
| senha | Caixa de Texto | mínimo de 8 caracteres |   |

**Nome da atividade 2**

| **Campo** | **Tipo** | **Restrições** | **Valor default** |
| --- | --- | --- | --- |
| [Nome do campo] | [Área de texto, Caixa de texto, Número, Data, Imagem, Seleção única, Múltipla escolha, Arquivo, Link, Tabela] |  |  |
|    |    |     |

#### Processo 2 – NOME DO PROCESSO

**Nome da atividade 1**

| **Campo** | **Tipo** | **Restrições** | **Valor default** |
| --- | --- | --- | --- |
| [Nome do campo] | [Área de texto, Caixa de texto, Número, Data, Imagem, Seleção única, Múltipla escolha, Arquivo, Link, Tabela] |  |  |
|    |    |     |

**Nome da atividade 2**

| **Campo** | **Tipo** | **Restrições** | **Valor default** |
| --- | --- | --- | --- |
| [Nome do campo] | [Área de texto, Caixa de texto, Número, Data, Imagem, Seleção única, Múltipla escolha, Arquivo, Link, Tabela] |  |  |
|    |    |     |

### 4.2. Tecnologias

Descreva qual(is) tecnologias você vai usar para resolver o seu problema, ou seja implementar a sua solução. Liste todas as tecnologias envolvidas, linguagens a serem utilizadas, serviços web, frameworks, bibliotecas, IDEs de desenvolvimento, e ferramentas. Apresente também uma figura explicando como as tecnologias estão relacionadas ou como uma interação do usuário com o sistema vai ser conduzida, por onde ela passa até retornar uma resposta ao usuário.

## 5. Modelo de dados

Apresente o modelo de dados por meio de um modelo relacional ou Diagrama de Entidade-Relacionamento (DER) que contemple todos conceitos e atributos apresentados item anterior. 

![Diagrama de Entidade Relacionamento de Exemplo](imagens/er_diagram.png "Diagrama de Entidade Relacionamento de Exemplo")

## 6. Indicadores de desempenho

Apresente aqui os principais indicadores de desempenho e algumas metas para o processo. Atenção: as informações necessárias para gerar os indicadores devem estar contempladas no diagrama de classe. Colocar no mínimo 5 indicadores.

Usar o seguinte modelo:

| **Indicador** | **Objetivos** | **Descrição** | **Cálculo** | **Fonte dados** | **Perspectiva** |
| --- | --- | --- | --- | --- | --- |
| Percentual reclamações | Avaliar quantitativamente as reclamações | Percentual de reclamações em relação ao total atendimento |   | Tabela reclamações | Aprendizado e Crescimento |
| Taxa de Requisições abertas | Melhorar a prestação de serviços medindo a porcentagem de requisições | Mede % de requisições atendidas na semana | ![\frac{\sum{atendidas}}{\sum{requisicoes}}100](https://latex.codecogs.com/svg.latex?\frac{\sum{atendidas}}{\sum{requisicoes}}100) | Tabela solicitações | Processos internos |
| Taxa de entrega de material | Manter controle sobre os materiais que estão sendo entregues | Mede % de material entregue dentro do mês |   | Tabela Pedidos | Clientes |

Obs.: todas as informações para gerar os indicadores devem estar no diagrama de classe **a ser proposto**

## 7.Sistema desenvolvido

Faça aqui uma breve descrição do software e coloque as principais telas com uma explicação de como usar cada uma.

## 8. Conclusão

Apresente aqui a conclusão do seu trabalho. Discussão dos resultados obtidos no trabalho, onde se verifica as observações pessoais de cada aluno. Poderá também apresentar sugestões de novas linhas de estudo.

# REFERÊNCIAS
Verifique no link abaixo como devem ser as referências no padrão ABNT:

http://www.pucminas.br/imagedb/documento/DOC\_DSC\_NOME\_ARQUI20160217102425.pdf


**[1]** - _European route of industrial heritage - cultural routes - publi.coe.int. ([s.d.]). Cultural Routes. Recuperado 2 de março de 2023, de https://www.coe.int/en/web/cultural-routes/european-route-of-industrial-heritage_

**[2]** - _Akatu, E. (2012, outubro 11). Como o consumo colaborativo pode revolucionar uma cidade. Instituto Akatu; Akatu. https://akatu.org.br/como-o-consumo-colaborativo-pode-revolucionar-uma-cidade/_

**[3]** - _SILVA, Daniel Neves. "Escambo"; Brasil Escola. Recuperado 2 de março de 2023, de https://brasilescola.uol.com.br/historiag/escambo.htm_

**[4]** - _Economia Circular - O que é a Economia Circular? ([s.d.]). Nomia.pt. Recuperado 2 de março de 2023, de https://eco.nomia.pt/pt/economia-circular/estrategias_


# APÊNDICES

**Colocar link:**

Do código (armazenado no repositório);

Dos artefatos (armazenado do repositório);

Da apresentação final (armazenado no repositório);

Do vídeo de apresentação (armazenado no repositório).




