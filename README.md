# inventorytracker
An inventory tracking web service that provides the following information for each item stored in the inventory: name, quantity, and cost.

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
* PostgreSQL database - hosted on ElephantSQL

#### Frontend
* Vue.js Framework
* HTML/CSS

## Backend API Endpoints 
See ItemController (backend/src/main/java/com.fredmheir.inventorytracker.backend/controller/ItemController.java)
* GET /inventory/items/export - Export item list to CSV file
* GET /inventory/items/ - GET a list of items
* GET /inventory/items/{id} - GET an item by id
* POST /inventory/items - POST a new item
* PUT /inventory/items/{id} - PUT an item
* DELETE /inventory/items/{id} - DELETE an item
* DELETE /inventory/items - DELETE all items in inventory

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

#### 3) Install Node.js
Mac (with Homebrew):
```
brew install node
```
Mac (without Homebrew) or Windows:
```
https://nodejs.org/en/download/
```

#### 4) Install npm
```
npm install
```

#### 5) Optional : Install PostgreSQL
The PostgreSQL database is hosted with ElephantSQL. 
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



## Screenshot
#### List inventory items 
Accessible by visiting http://localhost:8081/
![image](https://user-images.githubusercontent.com/63975740/150046507-840924d3-0ded-4843-a630-6ba20bb6012f.png)

#### Add item (Create)
![image](https://user-images.githubusercontent.com/63975740/150046525-172b7337-1ace-4df2-bf9c-595b494833ad.png)

#### Edit item (Update, Delete)
![image](https://user-images.githubusercontent.com/63975740/150046572-fa08c8cd-6959-423b-933c-294f64eb7ed6.png)

#### Export to CSV
![image](https://user-images.githubusercontent.com/63975740/150047311-dcf158f3-ca7a-4333-b5dd-ba0f85cf99f2.png)
![image](https://user-images.githubusercontent.com/63975740/150047389-a1f9d0ed-2c5c-4f40-a6bc-48d67334b046.png)


