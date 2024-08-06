# Лабораторная работа для отбора в Т-Финтех Джуниор(ниже приведена инструкция по сборке и документация)

## Условие задания

Вам необходимо разработать веб-приложение на языке Java/Kotlin для перевода набора слов на другой язык с использованием стороннего сервиса перевода (Яндекс, Google или др.).

### Требования к программе:

Приложение должно принимать на вход строку, состоящую из набора слов, исходный язык и целевой язык в качестве параметров для перевода. В ответе программа должна вернуть переведенную строку.
Каждое слово должно быть переведено отдельно в нескольких потоках. При этом число одновременно работающих потоков не должно превышать 10.
Приложение должно сохранять в реляционную базу данных информацию о запросе: IP-адрес пользователя, входную строку для перевода и результат перевода. Структуру хранения нужно придумать самостоятельно.
Код программы должен быть выложен на github и содержать readme — инструкции по запуску приложения и как его использовать.
Дополнительные требования:

---
Можно использовать фреймворк Spring/SpringBoot
Для базы данных использовать только JDBC
Для вызова внешней системы использовать RestTemplate
---
### Пример:

#### Вход

en → ru

Hello world, this is my first program

#### Выход

Пример 1: http 200 Привет мир, это является мой первый программа

Пример 2: http 400 Не найден язык исходного сообщения

Пример 3: http 400 Ошибка доступа к ресурсу перевода

## Автор решения

Гловацкий Даниил Олегович

## Инструкция по сборке и запуску решения

### 1. Установите Java, Maven и Docker

Убедитесь, что на вашем компьютере установлены Java(21 или новее), Maven и Docker. Вы можете проверить их наличие, выполнив следующие команды в терминале или командной строке:

```bash
java -version
mvn -version
```

### 2. Клонируйте репозиторий

```bash
git clone https://github.com/lesam19092/FintechLab
```

### 3. Перейдите в директорию проекта

```bash
cd путь_к_директории_проекта
```

### 4. Сборка проекта
Выполните следующую команду Maven для сборки проекта:

```bash
mvn clean install
```

### 5. Установите интерфейс командной строки для YandexCloud
Сначала нужно установить интерфейс YandexCloud для командной строки и настроить его. Вся необходимая информация находится по ссылке: https://yandex.cloud/ru/docs/cli/quickstart#install

### 6. Получите API-токен

После установки интерфейса выполните команду
```bash
yc iam create-token
```
Полученный API-токен вставьте в переменную окружения в параметрах запуска с именем IAM_TOKEN

---
Больше информации здесь:

https://yandex.cloud/ru/docs/iam/operations/iam-token/create?utm_referrer=about%3Ablank

https://yandex.cloud/ru/docs/cli/quickstart#install

---

### 7. Получите FolderId своей папки в YandexCloud

```bash
yc config list
```

Полученный FolderId вставьте в переменную окружения в параметрах запуска с именем folderId

---
Больше информации здесь:

https://yandex.cloud/ru/docs/iam/operations/iam-token/create?utm_referrer=about%3Ablank

https://yandex.cloud/ru/docs/cli/quickstart#install

---

### 8. Запустите Spring Boot приложение
После успешной сборки вы можете запустить ваше приложение с помощью команды:

```bash
mvn spring-boot:run
```

Не забудьте запустить Docker(Docker Daemon)

Docker-контейнеры должны подтянуться и запуститься автоматически после запуска приложения.

### 9. ДОКУМЕНТАЦИЯ

---

# TranslateClient 🌍

**TranslateClient** is a Java class designed for text translation using the Yandex Translate API. It offers two modes of operation: single-threaded and multi-threaded, catering to different translation needs.

## Features ✨

- **Single-threaded translation**: Translate entire texts from one language to another in a straightforward, sequential manner.
- **Multi-threaded translation**: Break down text into words and translate them concurrently, offering potentially faster results.

## Usage 📖

### Method: `translate`

- **Description**: Translates text from one language to another in single-threaded mode.
- **Parameters**:
    - `sourceLanguage` - The language code of the source text.
    - `targetLanguage` - The language code to translate the text into.
    - `text` - The text that needs to be translated.
- **Returns**: The translated text as a `String`.
- **Throws**: `JSONException` in case of errors in processing JSON.

### Method: `getTranslation`

- **Description**: Translates text using a multi-threaded approach, splitting the text into words.
- **Parameters**:
    - `sourceLanguage` - The language code of the source text.
    - `targetLanguage` - The language code to translate the text into.
    - `text` - The text that needs to be translated.
- **Returns**: The translated text as a `String`.
- **Throws**: `ExecutionException`, `InterruptedException` if there are errors during multi-threaded processing.

### Method: `getTranslatedTextWithMultithreading`

- **Description**: Internal method that implements the multi-threaded translation logic.
- **Parameters**:
    - `sourceLanguage` - The language code of the source text.
    - `targetLanguage` - The language code to translate the text into.
    - `text` - The text that needs to be translated.
- **Returns**: The translated text as a `String`.
- **Throws**: `InterruptedException`, `ExecutionException` if there are errors during multi-threaded processing.

### Method: `arrayListToString`

- **Description**: Converts a list of translated words into a single string.
- **Parameters**:
    - `arrayList` - A list of `Word` objects.
- **Returns**: A `String` with concatenated translated words.


Here is a detailed description of the `ApplicationConfig` class for your GitHub README file:

---

## ApplicationConfig Class 📚

The `ApplicationConfig` class is designed to store the application's configuration parameters required to work with the Yandex Translate API. It centralizes the management of settings, making the code more flexible and easier to maintain.

### Properties 🔧

- **`yaUrl` (String)**: The base URL for the Yandex Translate API.
- **`iamToken` (String)**: Your access token for the Yandex Translate API.
- **`folderId` (String)**: *(Optional)* The folder ID in Yandex Translate used for managing translations (if applicable).

### Key Features 🌟

- **`@Validated` Annotation**: Ensures the validation of configuration values.
- **Prefix `app`**: Used to bind properties with the application's properties file.
- **Ignoring Unknown Properties**: Allows the addition of new properties without needing to modify the configuration class.

### Usage Instructions 📖

1. **Adding to Your Application**:
    - Include the `ApplicationConfig` class in your Spring Boot application.

2. **Defining Properties**:
    - Define the properties `yaUrl`, `iamToken`, and optionally `folderId` in your application's properties file (`application.properties` or `application.yml`).

   Example in `application.properties`:
   ```properties
   app.yaUrl=https://translate.api.cloud.yandex.net/translate/v2/translate
   app.iamToken=your_yandex_iam_token_here
   app.folderId=optional_folder_id_here
   ```

   Example in `application.yml`:
   ```yaml
   app:
     yaUrl: "https://translate.api.cloud.yandex.net/translate/v2/translate"
     iamToken: "your_yandex_iam_token_here"
     folderId: "optional_folder_id_here"
   ```

3. **Dependency Injection**:
    - Use Spring Dependency Injection to obtain an instance of the `ApplicationConfig` class and access its properties throughout your application.
      Here’s a section you can add to your README file that explains the `RestTemplateConfig` class:

---

## RestTemplateConfig Class ⚙️

The `RestTemplateConfig` class is a Spring configuration class that sets up a `RestTemplate` bean for making REST API calls. This configuration customizes the `RestTemplate` with an error handler to manage responses more effectively.

### Key Features 🌟

- **`@Configuration` Annotation**: Indicates that this class contains one or more bean methods and may be processed by the Spring container to generate bean definitions.
- **`@Bean` Annotation**: Defines a `RestTemplate` bean that can be injected and used throughout the application.
- **Custom Error Handling**: Integrates a custom error handler, `RestTemplateResponseErrorHandler`, which manages exceptions and errors in a more controlled manner during REST API calls.

### Usage Instructions 📖

1. **Adding to Your Application**:
    - Include the `RestTemplateConfig` class in your Spring Boot application under the appropriate package (e.g., `edu.java.scrapper.configuration`).

2. **Using the RestTemplate Bean**:
    - You can inject the `RestTemplate` bean into your services or components where HTTP calls are needed.

3. **Custom Error Handling**:
    - The `RestTemplate` is configured with a custom error handler (`RestTemplateResponseErrorHandler`) that you can further customize to handle specific HTTP response errors gracefully.


# Spring JDBC Configuration Example 📚

Welcome to the Spring JDBC Configuration example project! This repository demonstrates how to set up a JDBC connection to a PostgreSQL database using Spring Framework.

## Overview

This project contains a simple configuration class that sets up a connection to a PostgreSQL database. The configuration is done using Spring's `@Configuration` and `@Bean` annotations.

## Configuration Details

The configuration class `SpringJdbcConfig` provides a `DataSource` bean which is used to configure the connection to the PostgreSQL database.



- Driver Class Name: Specifies the PostgreSQL driver class.
- URL: The URL of the PostgreSQL database. In this example, it's set to jdbc:postgresql://localhost:5432/requests.
- Username and Password: Both are set to postgres for simplicity.
# Text Translation Controller 🌐

This project provides a simple web interface for text translation using a variety of languages. The translation service is powered by the `TranslationRequestService`, and the application handles various HTTP requests to perform translations and manage errors gracefully.

## Overview

The `TextController` class is a Spring MVC controller that manages HTTP requests for translating text from one language to another. It includes methods to handle the home page, process translation requests, and manage client and server errors.


- Home Method (`home`): Displays the main page with a list of supported languages.
- Add Method (`add`): Handles translation requests. It takes the source and target languages, the text to be translated, and the client's IP address. If the source and target languages are the same, it redirects to an error page.
- Exception Handlers: Manages client (HttpClientErrorException) and server (HttpServerErrorException) errors by logging appropriate messages and redirecting to an error page.
- Client IP Retrieval (`getClientIp`): Extracts the client's IP address from the request.

# Translation Request Repository 📦

This project provides a robust repository layer for handling translation requests. It demonstrates how to use Spring's JDBC support for database operations and transaction management.

## Overview

The `TranslationRequestRepository` interface and its implementation, `TranslationRequestRepositoryImpl`, form the core of the repository layer. This layer is responsible for persisting translation requests to the database.



#### TranslationRequestRepositoryImpl Class

This class implements the TranslationRequestRepository interface and uses Spring's JdbcClient to perform database operations. It is annotated with @Repository to indicate that it's a Spring-managed component, and @Transactional to ensure that the add method runs within a transactional context.



### Explanation

- Repository Interface (`TranslationRequestRepository`): Defines the add method for adding translation requests to the database.
- Repository Implementation (`TranslationRequestRepositoryImpl`):
    - Annotations:
        - @Repository: Indicates that the class is a repository.
        - @RequiredArgsConstructor: Generates a constructor with required arguments.
        - @Transactional: Ensures the add method is executed within a transaction.
    - Method:
        - add: Uses JdbcClient to execute an SQL INSERT statement, inserting the IP address, input text, and translated text into the requests table.
# 🌐 Language Scrapper DTOs

Welcome to the Language Scrapper Data Transfer Objects (DTOs) module! This module contains a set of Java classes designed to facilitate the exchange of translation-related data within the Language Scrapper application. Below, you'll find an overview of each class and its purpose.

## 📚 Classes

### `LanguageCode`

The `LanguageCode` enum defines a set of language codes along with their respective names in both English and their native scripts. This is essential for identifying and managing languages in the application.



### TranslateResponse

The TranslateResponse class represents a response from a translation request, containing a list of TranslatedWord objects.


### TranslatedWord

The TranslatedWord class encapsulates a single translated word or phrase.


### TranslationRequest

The TranslationRequest class is used to encapsulate the details of a translation request, including the IP address of the requester, the input string, and the translated string.



Welcome to the Language Scrapper project! This project aims to provide robust translation services, including handling translation requests, managing errors, and integrating with various translation clients. Below, you'll find an overview of the core components of the project.

#### `RestTemplateResponseErrorHandler`

A custom error handler for `RestTemplate` to handle client and server errors.



This package contains the service layer classes that implement the business logic.

#### TranslationRequestService

A service class responsible for handling translation requests and communicating with the translation client.


