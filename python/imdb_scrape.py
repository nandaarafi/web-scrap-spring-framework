from selenium import webdriver
from selenium.webdriver.common.by import By
import csv
import time
import json


driver = webdriver.Chrome()

# IMDb Top 250 URL
url = 'https://www.imdb.com/chart/top'
driver.get(url)

time.sleep(2)

movies = driver.find_elements(By.CSS_SELECTOR, 'li.ipc-metadata-list-summary-item.sc-10233bc-0.TwzGn.cli-parent')

top_100_movies = []
for movie in movies[:100]:
    # Extract the title
    try:
        title = movie.find_element(By.CSS_SELECTOR, 'h3.ipc-title__text').text
        title = title.split('. ', 1)[1]
    except:
        title = 'N/A'  # Fallback in case title is not found

    # Extract the year
    try:
        year_metadata = movie.find_element(By.CSS_SELECTOR, 'div.sc-ab348ad5-7.cqgETV.cli-title-metadata')
        year = year_metadata.find_element(By.CSS_SELECTOR, 'span.sc-ab348ad5-8.cSWcJI.cli-title-metadata-item').text
    except:
        year = 'N/A'

    # Extract the rating
    try:
        rating = movie.find_element(By.CSS_SELECTOR, 'span.ipc-rating-star--rating').text
    except:
        rating = 'N/A'

    top_100_movies.append({
            "title": title,
            "year": int(year) if year.isdigit() else year,  # Convert to int if possible
            "rating": float(rating) if rating.replace('.', '', 1).isdigit() else rating  # Convert to float if possible
        })

with open('top_100_movies.json', 'w', encoding='utf-8') as json_file:
    json.dump(top_100_movies, json_file, ensure_ascii=False, indent=4)

print("Top 100 movies scraped and saved to top_100_movies.json")

driver.quit()
