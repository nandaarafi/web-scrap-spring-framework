import json
import requests

API_URL = 'http://localhost:8080/movies'

with open('top_100_movies.json', 'r', encoding='utf-8') as json_file:
    top_100_movies = json.load(json_file)

for movie in top_100_movies:

    payload = {
        "title": movie["title"],
        "year": movie["year"],
        "rating": movie["rating"]
    }

    # Send the POST request
    response = requests.post(API_URL, json=payload)

    # Check the response status
    if response.status_code == 201:  # Created
        print(f"Successfully added movie: {movie['title']}")
    else:
        print(f"Failed to add movie: {movie['title']}, Status Code: {response.status_code}, Response: {response.text}")