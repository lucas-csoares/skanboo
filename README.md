<hr>
<br>
<h3 align="center">
<img width="400px" src="./docs/imagens/logo_traçada.png">
</h3>
<br>
<p align="center">
 <a href="#-sKanboo">Sobre</a> •
 <a href="#-integrantes">Integrantes</a> • 
 <a href="#-professor">Professores</a> • 
 <a href="#-tecnologias">Tecnologias</a> • 
 <a href="#-instruções-de-utilização">Instruções de Utilização</a>  
</p>

# 📝 sKanboo

O modelo de troca de mercadorias (escambo), apesar de antigo, tem ganhado cada vez mais força devido à crescente preocupação com a sustentabilidade, o que reforça as premissas da economia circular, que prevê o uso e reuso de uma dado item. Além disso, devido à instabilidade financeira causada pela pandemia da COVID-19, muitas pessoas vêm buscado formas mais baratas de suprir suas necessidades. Ou seja, ao fazer escambo a pessoa evita gastar dinheiro por trocar bens que já possui por outros de que necessita, ganha mais flexibilidade por poder negociar diretamente com a outra pessoa, uma vez que não se tem preço fixo como em lojas, e também é uma forma mais sustentável de consumir.

É com isso em mente que surgiu o **sKanboo**, que tem como objetivo facilitar a troca de produtos entre as pessoas. A ideia é proporcionar uma forma mais sustentável e econômica de consumo, permitindo que as pessoas adquiram novos objetos apenas se desfazendo daqueles que já possuem e não usam. É uma alternativa para se obter produtos, que não envolve dinheiro, ideal para aqueles que estão interessados em um produto mas não possuem o valor monetário no momento.

## 🤜 Integrantes

* [Ana Corina Damas Batista](https://github.com/corinnnab)
* [Giovanna Ferreira dos Santos de Almeida](https://github.com/giuvanna)
* [Guilherme Lage da Costa](https://github.com/guilhermelcosta)
* [Lucas Cabral Soares](https://github.com/lcsoares2022)
* [Vítor Lagares Stahlberg](https://github.com/VitorLS0)

## 👨‍💻 Professores

* Hugo Bastos de Paula
* Eveline Alonso Veloso

## 🚀 Tecnologias
- Frontend:
  - [Chart.js](https://www.chartjs.org/)
  - [FontAwesome](https://fontawesome.com/)
  - [JavaScript](https://www.javascript.com/)
  - [VueJs](https://vuejs.org/)

- Backend
  - [NodeJs](https://nodejs.org/)
  - [Spring Boot](https://spring.io/projects/spring-boot)
  - [Spring Security](https://spring.io/projects/spring-security)

- Database:
  - [MySQL Workbench](https://www.mysql.com/products/workbench/)
  
- Devops:
  - [GitHub Actions (CI/CD)](https://github.com/features/actions)
  - [Docker](https://www.docker.com/)
  - [Docker Compose](https://docs.docker.com/compose/)
    
- Cloud:
  - [Azure](https://azure.microsoft.com/) 

---
## 💻 Instruções de utilização

Antes de começar, você deve se certificar que tenha instalado na sua máquina o [Docker](https://docs.docker.com/engine/install/ubuntu/), em versão igual ou superior 20.10.

**Passo 01: clone o repositório**

`$ git clone https://github.com/ICEI-PUC-Minas-PPLES-TI/plf-es-2023-1-ti2-0924100-skanboo.git`

**Passo 02: instalar dependências para o Vue JS**

A instalação do Vue CLI pode ser acessada [aqui](https://cli.vuejs.org/guide/).

- Vue JS: `npm install vue@^3.2.13`
- Reactivity: `npm install @vue/reactivity@^3.3.4`
- Axios: `npm install @vue/reactivity@^3.3.4` 
- Chart JS: `npm install chart.js@^4.3.0`
- Core JS: `npm install core-js@^3.8.3`
- Router: `npm install vue-router@^4.0.3`
- Vue the mask: `npm install vue-the-mask@^0.11.1`

**Passo 03: iniciar backend via docker-compose**

- Passo 3.1: acesse a pasta clonada no **Passo 01** pelo CMD: `cd plf-es-2023-1-ti2-0924100-skanboo`;
- Passo 3.2: acesse a pasta src pelo CMD: `cd src`;
- Passo 3.3: acesse a pasta Skanboo pelo CMD: `cd Skanboo`;
- Passo 3.4: iniciar Docker-compose da API: `docker-compose up`

**Passo 04: iniciar frontend**

- Passo 4.1: acesse a pasta clonada no **Passo 01** pelo CMD: `cd plf-es-2023-1-ti2-0924100-skanboo`;
- Passo 4.2: acesse a pasta src pelo CMD: `cd src`;
- Passo 4.3: acesse a pasta Skanboo pelo CMD: `cd Skanboo-frontend`;
- Passo 3.4: iniciar aplicação frontend: `npm run serve`

A aplicação vai estar rodando em <http://localhost:8080>.

## Histórico de versões

* 1.0.0
  * Consolidação da versão final do código.
  
