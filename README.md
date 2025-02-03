# CalenDataApi

**CalenDataApi** is a Spring Boot application that interacts with the [Date Nager API](https://date.nager.at/api/) to retrieve holiday data for specified countries. The program can return:

- The last three celebrated holidays for a given country.
- The number of public holidays in a given year that do not fall on weekends, for one or more countries.
- A deduplicated list of dates and local holiday names celebrated in two countries in a given year.

## Technologies Used

- Java 21
- Spring Boot
- Swagger for API documentation
- RestTemplate for HTTP calls
- JUnit for unit testing

## API Endpoints

### 1. Get Last 3 Holidays for a Country

**Request:**

`GET /api/holidays/last-three/{countryCode}`

**Response:**
```json
{
  "holidays": [
    {
      "date": "2025-01-01",
      "name": "New Year's Day"
    },
    {
      "date": "2024-12-26",
      "name": "St. Stephen's Day"
    },
    {
      "date": "2024-12-25",
      "name": "Christmas Day"
    }
  ]
}
```
### 2. Get Number of Public Holidays Not Falling on Weekends for a Year and a list of Countries

**Request:**

`GET /api/holidays/non-weekend?year={year}&countryCodes={countryCode1},{countryCode2},{countryCode3}`

**Response:**
```json
{
  "nonWeekendHolidays": [
    {
      "countryCode": "CA",
      "numberOfHolidays": 29
    },
    {
      "countryCode": "US",
      "numberOfHolidays": 16
    },
    {
      "countryCode": "BR",
      "numberOfHolidays": 10
    }
  ]
}
```
### 3. Get Deduplicated List of Common Holidays Between Two Countries

**Request:**

`GET /api/holidays/common?firstCountry={countryCode1}&secondCountry={countryCode2}&year={year}`

**Response:**
```json
{
  "firstCountry": "BR",
  "secondCountry": "NL",
  "commonHolidays": [
    {
      "date": "2025-01-01",
      "localNameToFirstCountry": "Confraternização Universal",
      "localNameToSecondCountry": "Nieuwjaarsdag"
    },
    {
      "date": "2025-04-18",
      "localNameToFirstCountry": "Sexta-feira Santa",
      "localNameToSecondCountry": "Goede Vrijdag"
    },
    {
      "date": "2025-04-20",
      "localNameToFirstCountry": "Domingo de Páscoa",
      "localNameToSecondCountry": "Eerste Paasdag"
    },
    {
      "date": "2025-04-21",
      "localNameToFirstCountry": "Dia de Tiradentes",
      "localNameToSecondCountry": "Tweede Paasdag"
    },
    {
      "date": "2025-12-25",
      "localNameToFirstCountry": "Natal",
      "localNameToSecondCountry": "Eerste Kerstdag"
    }
  ]
}
```

## Installation

To get started with **CalenDataApi**, follow these steps:

### Prerequisites

- **Java 21** or higher
- **Maven** (for building the project)
- **Git** (for cloning the repository)

### Clone the repository
```bash
git clone https://github.com/nathaliadantasv/calendata-api.git
```

### Build the project
```bash
cd CalenDataApi
mvn clean install
```
### Run the project
```bash
mvn spring-boot:run
```

## Swagger UI
To interact with the API and explore all the available endpoints, you can use the integrated Swagger UI:

``http://localhost:8080/swagger-ui/``

## Postman Collection

A Postman collection is included in the project for easy testing of the API. You can import the collection (Assessment.postman_collection.json) into your Postman application to quickly test the available endpoints.

To use the Postman collection:

1. Open Postman.
2. Click on "Import" in the top-left corner.
3. Select "File" and choose the Assessment.postman_collection.json file from the project directory.
4. After importing, you can start testing the endpoints directly from Postman.