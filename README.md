# inventorytracker

## Setup

### 1) Install Java (version 8 or higher)
```
https://java.com/en/download/help/download_options.html
```

### 2) Install gradle
Mac (with Homebrew):
```
brew install gradle
```

Mac (without Homebrew) or Windows:
```
https://gradle.org/install/
```

### 3) Install npm
```
npm install
```


## Running the web application

## 1) Run front-end application
In a new terminal window, go in the frontend directory and execute the following command:
```
npm run serve
```
Leave this terminal window open.

## 2) Run backend application
In a new terminal window, go in the backend directory and execute the following command:
```
gradle bootRun
```
Leave this terminal window open.

## 3) Access the application
Visit http://localhost:8081/ to access the application.




## Troubleshooting
The PostgreSQL database is hosted on Heroku, which performs maintenance from time to time. During maintenance, the database is read-only. Maintenance should last around 10 minutes.
