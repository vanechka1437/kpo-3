# Антиплагиат-анализатор (микросервисная система)

## Описание проекта
Система для анализа текстовых отчетов студентов, включающая:
- Хранение файлов
- Подсчет статистики (абзацы, слова, символы)
- API для интеграции с внешними сервисами

Соответствует заданию по курсу "Конструирование программного обеспечения".

Сервисы:

**API Gateway** (порт 8080) - маршрутизация запросов

**File Storage** (порт 8081) - хранение файлов

**File Analysis** (порт 8082) - анализ текста

**PostgreSQL** (порт 5432) - хранение данных

Запуск системы

Требования:

Docker 20.10+

Docker Compose 2.5+

JDK 17 (для сборки)

### Инструкция:

#### 1. Клонировать репозиторий
```
git clone https://github.com/ваш-репозиторий.git
cd antiplagiat
```

#### 2. Собрать сервисы
```
mvn clean package -f api-gateway/pom.xml
mvn clean package -f file-storage-service/pom.xml
mvn clean package -f file-analysis-service/pom.xml
```

#### 3. Запустить систему
```
docker-compose up -d
```
### API Документация

#### Основные эндпоинты:

**POST**	/api/upload	Загрузка файла (form-data)

**GET**	/api/analyze/{id}	Получение статистики по файлу

Пример запроса:

```bash
curl -X POST -F "file=@report.txt" http://localhost:8080/api/upload
```