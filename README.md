# OpenAPI definition

 [Swagger UI](http://localhost:8080/app/swagger-ui/index.html)
 
 [H2 Database](http://localhost:8080/app/h2)   
    
## Version: v0

### /generate/token/{username}

#### GET
##### Summary:

Create token by name

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| username | path |  | Yes | string |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | Token created |
| 403 | User not found |
| 404 | Not Found |
| 422 | Unprocessable Entity |
| 500 | Internal Server Error |

### /api/users/test

#### GET
##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 404 | Not Found |
| 422 | Unprocessable Entity |
| 500 | Internal Server Error |

### /api/users/find

#### GET
##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 404 | Not Found |
| 422 | Unprocessable Entity |
| 500 | Internal Server Error |
