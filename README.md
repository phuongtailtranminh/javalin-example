# javalin-example
Simple web app with Javalin

Create database: `test_db` with username: `newuser` and password: `password`

Execute migration: `mvn clean flyway:migrate -Dflyway.configFiles=flyway.properties`

Generate JooQ POJO and DAO:
`mvn compile`

Generate artifact:
`mvn package`