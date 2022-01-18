# inventorytracker

## Setup

### 1) Install Java (version 8 or higher)
```
https://java.com/en/download/help/download_options.html
```

### 2) Install gradle
Mac:
```
brew install gradle
```

Windows:
```
https://gradle.org/install/
```

### 3) Install npm
```
npm install
```


## Running the web application

## 1) Run front-end application
In a new terminal window, go in the frontend directory and e
```
npm run serve
```

## 2) Run backend application
Open a terminal window, in the backend directory
```
gradle bootRun
```

## 3) Access the application
Visit http://localhost:8081/ to access the application.




## Troubleshooting
The PostgreSQL database is hosted on Heroku, which performs maintenance from time to time. During maintenance, the database is read-only. Maintenance should last around 10 minutes.
