# OpenAPI definition

### est-app will provide authentication and authorization using access token
### Framework 


 [Swagger UI](http://localhost:8080/app/swagger-ui/index.html)
 
 [H2 Database](http://localhost:8080/app/h2)   
 
### Main Frameworks    
#### Spring Boot: 3.2.0
#### Spring Security: 6.2.0  
#### H2 Database: 2.2.224



### /generate/token/{username} [GET]
#### Generate JWT by name 

Create token by name

#### Parameters

|   Name   |  Located in | Description | Required | Schema |
|  ----    | ----------  | ----------- | -------- |  ----  |
| username |    path     |             |    Yes   | string |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | Token created |
| 403 | User not found |
| 404 | Not Found |
| 422 | Unprocessable Entity |
| 500 | Internal Server Error |

### /api/users/test [GET]
Add header Authorization : Bearer abcdefg 
#### GET
##### Responses

| Code |      Description      |
| ---- |      -----------      |
| 200  |          OK           |
| 404  |       Not Found       |
| 422  |  Unprocessable Entity |
| 500  |  Internal Server Error|

### /api/users/find

#### Get users
Add header Authorization : Bearer abcdefg 
#### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 404 | Not Found |
| 422 | Unprocessable Entity |
| 500 | Internal Server Error |
