# Web Scraping IMDB and Spring CRUD
This project is a comprehensive example of combining web scraping, data handling, and API integration with a CRUD (Create, Read, Update, Delete) Spring Boot Application. It demonstrates how to scrape data from websites using Selenium, store that data in JSON format, and then use the data to interact with a REST API built using Spring Boot.

The project can be broken down into three major components:

1. Web Scraping with Selenium: Automating the extraction of data from websites, Why using selenium? when using beautiful soup I got limit to just 25 movies.
2. Data Storage in JSON: Storing the scraped data in a structured JSON format.
3. CRUD API with Spring Boot: Building a Spring Boot REST API to handle CRUD operations with the scraped data.
4. Client-Side Posting: Using a Python client to POST the JSON data to the API.

## Project Structure
- Web Scraping and client post api in python folder 
- Palindrome string in com.example.rafi.crud_imdb.palindrome.PalindromeChecker
- CRUD database Spring Framework with PostgreSQL driver, 

## How to run, PS: this is using windows cli
### How Run the java spring framework server
First setup the PostgreSQL database using docker compose
```
docker-compose up
```
run the java spring framework project
```
mvn spring-boot:run
```
## How to run the web scraping
- Install the browser driver first, for example for chrome
https://developer.chrome.com/docs/chromedriver/downloads 

- install the requirements first
```
pip install -r python\requirements.txt
```
- run the scraping imdb movie that have 100 top movies data
```
python .\python\imdb_scrape.py
```

- Post Request to insert the data into the database
```
python .\python\client.py
```
