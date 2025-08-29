
# Desafio API – RestAssured + JUnit4 + Allure

Cobertura completa dos endpoints `/users` da API sugerida (https://serverest.dev/#/).

## ▶️ Rodar localmente

Pré-requisitos:
- Java 17+
- Maven 3.9+

Instalar dependências e executar testes:
```bash
mvn -q -DskipTests=false test
```

Gerar e abrir relatório Allure:
```bash
mvn allure:report
mvn allure:serve
```

Propriedades configuráveis (via `-D` ou `pom.xml` profile):
- `baseUrl` (default: `https://serverest.dev`)
- `adminEmail` (default: `admin@serverest.dev`)
- `adminPassword` (default: `admin`)

## 🧪 Casos cobertos
- `GET /users` (lista)
- `POST /users` (sucesso e validação)
- `GET /users/{id}`
- `PUT /users/{id}`
- `DELETE /users/{id}`
- Autenticação (token inválido)
- Rate limit (teste tolerante)

## 🔁 CI/CD (GitLab)
```yaml
image: maven:3.9-eclipse-temurin-17

stages: [test]

cache:
  paths:
    - ~/.m2/repository

test_api:
  stage: test
  script:
    - mvn -B -DskipTests=false test
  artifacts:
    when: always
    paths:
      - target/surefire-reports
      - reports/allure-results
```
