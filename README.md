# SpringSecurityRestAPI

[![Build Status](https://app.travis-ci.com/yhtyyar/SpringSecurityRestAPI.svg?branch=master)](https://app.travis-ci.com/yhtyyar/SpringSecurityRestAPI)


Необходимо реализовать REST API, которое взаимодействует с файловым хранилищем AWS S3 и предоставляет возможность получать доступ к файлам и истории загрузок.

## Сущности:

User

Event (User user, File file)

File (id, location, ...)

User -> … List<Events> events ...

Взаимодействие с S3 должно быть реализовано с помощью AWS SDK.


## Уровни доступа:

ADMIN - полный доступ к приложению

MODERATOR - добавление и удаление файлов

USER - только чтение всех данных, кроме User

## Технологии: Java, MySQL, Spring (IoC, Data, Sercurity), AWS SDK, MySQL, Travis, Docker, JUnit, Mockito, Gradle.
