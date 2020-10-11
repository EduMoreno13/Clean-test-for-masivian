# Clean-test-for-masivian
Clean-test made for masivian
This project is a api for roulette services that run in the localhost:8080
## Available points
|Method|resource|description|
|-------|-------|-----------|
|POST|/buildroulette|create a new roulette and return a the ID|
|PUT|/openroulette/{id}|change the roulette status to open and return if the operation was succed or denied|
|POST|/Betting|create a new bet and recieve a JSON ["rouletteID":"","type":"color or numero","betValue":"negro, rojo or a number between 0 and 36", "money":"the quanty of money in float"] in the body of the request and the user ID in the HEADER "Id-user"|
|PUT|/closeroulette/{id}|change the satus of the roulette to "closed" and return the results of the bet in a map the user-id, money and win number.|
|GET|/roulettes|return a list of roulettes and their status if is "open" or "closed"|
## Data Base
For the data base use a redis in a standalone configuration in the default port 6379
