# **README.md**

## **Required Stories**
- [x] Create a Firebase database for your application. . (3 points)
    - [x] The names of class fields should match the names presented in the last coding                 assignment (this includes casing)
    - [x] Add a createdAt field to each table
    - [x] Include sample data

- [x] Create an endpoint for each of the major classes (Hotel, Room, User)
    - [x] Create the required models, controllers, and services
    - [x] Each controller should have at least 2 methods
        - Get all
        - Get by id
- [x] Create an endpoint for each of the major classes (Hotel, Room, User)
    - [x] Each controller should have 3 methods:
        -   Create a new entry
        -   Update an existing entry
        -   Delete an existing entry
- [x] Document endpoint      

   All URIs start with: http://localhost:8080/api/v1
   |Network| Description |
   |---------|-----------|
   |/user|	Retrieves all users|
   |/{userId}| Retrieves a specific product based on its ID  |   
   |/hotel| Retrieves all hotels|
   |/{hotelId}| Retrieves a specific hotel based on its ID  |
   |/room|	Retrieves all rooms|
   |/{roomID}| Retrieves a specific room  based on its ID  |

## **GIF Walkthrough**
    - https://imgur.com/sojcFg2
## **Stretch Stories**
 - [x] Create an endpoint that allows the user to sort descending or ascending by the createdAt.
     -  Add this by using a query string (ie, http://localhost:8080/api/v1/product/?sort=asc)
       
 - [x] Create an endpoint that allows the user to find all hotels with a room available on a specific date. Accept the date as a query parameter (3 points)
    
 - [ ] Create an Reservation collection. Note: ReservationDetail is not a collection (3 points)
    
 - [ ] Create an endpoint thhat allows the user to create a reservation. (2 points)
     - Add this end point to the table if you create it.
      

