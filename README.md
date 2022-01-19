# inventorytracker

## Features
#### Basic CRUD Functionalities
* Create inventory items
* Edit inventory items
* Delete inventory items
* View a list of inventory items

#### Additional feature
* Push a button to export the product data to a CSV

## Technology stack
#### Backend
* Java 14
* Spring Framework
* Apache Commons CSV dependency
* PostgreSQL database - hosted on Heroku

#### Frontend
* Vue.js Framework
* HTML/CSS

## Setup

#### 1) Install Java
```
https://java.com/en/download/help/download_options.html
```

#### 2) Install gradle
Mac (with Homebrew):
```
brew install gradle
```

Mac (without Homebrew) or Windows:
```
https://gradle.org/install/
```

#### 3) Install npm
```
npm install
```

#### 4) Optional : Install PostgreSQL
The PostgreSQL database is hosted with Heroku. 
However, it is entirely possible to use a localhost PostgreSQL database simply by changing the applications.properties file located at backend/src/resources/applications.properties .

## Running the web application

##### 1) Run front-end application
In a new terminal window, go in the frontend directory and execute the following command:
```
npm run serve
```
Leave this terminal window open.

##### 2) Run backend application
In a new terminal window, go in the backend directory and execute the following command:
```
gradle bootRun
```
Leave this terminal window open.

##### 3) Access the application
Visit http://localhost:8081/ to access the application.




## Troubleshooting
The PostgreSQL database is hosted on Heroku, which performs maintenance from time to time. During maintenance, the database is read-only. Maintenance should last around 10 minutes.
