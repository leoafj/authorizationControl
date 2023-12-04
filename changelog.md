# VoteSystem Changelog

Todas as alterações do projeto devem ser documentadas neste arquivo.

O formato é baseado no [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

## [0.5.0] - 2023-12-04

## Added

- Changelog
- Readme
- zitrus.sql

## [0.4.1] - 2023-12-02

## Corrected

- Conflits when get data from database at index.jsp
- Changed the project pattern to english

Tests

- AuthorizationServletTest

## [0.4.0] - 2023-12-01

## Added

Jsp Pages

- index.jsp
- header.jsp
- footer.jsp

Db config

- liquibase changelog

## [0.3.0] - 2023-11-30

## Added

AuthorizationServlet

- doGet
- doPost
- loadSolicitationForEditing
- loadIndex


## [0.2.0] - 2023-11-29

## Added

ProcedureDAO
- getAllProcedure
- findProcedureById

RuleDAO

- validateAuthorization
- validateAuthorizationSql

## [0.1.0] - 2023-11-28

## Added

AuthorizarionDAO
- createAuthorization
- findAll
- findAuthorizationById
- updateAuthorization
- updateAuthorizationSql
- createAuthorizationFromResultSet
- deleteAuthorization
- cancelAuthorization
- createAuthorizationSql


## [0.0.1] - 2023-11-27

### Added

- Enums - Authorizarion, Gender
- Model - Authorization, Proceduresql, Gender
- infra - ConnectionFactory