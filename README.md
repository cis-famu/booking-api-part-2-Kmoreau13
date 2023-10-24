[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/hP86jo5x)


# **Required Stories**
- [x] Create a Firebase database for your application. . (3 points)
    - [x] The names of class fields should match the names presented in the last coding                 assignment (this includes casing)
    - [x] Add a createdAt field to each table
    - [x] Include sample data

- [x] Create an endpoint for each of the major classes (Hotel, Room, User) (6 points)
    - [x] Create the required models, controllers, and services
    - [x] Each controller should have at least 2 methods
        - Get all
        - Get by id
      
- [ ] Document endpoints. (1 point)

   All URIs start with: http://localhost:8080/api/v1
   |Network| Description |
   |---------|-----------|
   |/user|	Retrieves all users|
   |/{userId}| Retrieves a specific product based on its ID  |   
   |/hotel| Retrieves all hotels|
   |/{hotelId}| Retrieves a specific hotel based on its ID  |
   |/room|	Retrieves all rooms|
   |/{roomID}| Retrieves a specific room  based on its ID  |

# **GIF Walkthrough**
    - https://imgur.com/sojcFg2
# **Stretch Stories**
 - [ ] Create an endpoint that allows the user to sort descending or ascending by the createdAt. (5 points)
   - Add this by using a query string (ie, http://localhost:8080/api/v1/product/?sort=asc)
