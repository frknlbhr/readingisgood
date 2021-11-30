## ReadingIsGood 
ReadingIsGood is an online books retail firm which operates only on the Internet. Main
target of ReadingIsGood is to deliver books from its one centralized warehouse to their
customers within the same day. That is why stock consistency is the first priority for their
vision operations.

## FEATURES     

    • Registering New Customer 
    • Placing a new order
    • Tracking the stock of books
    • List all orders of the customer with Pagination
    • Viewing the order details
    • Query Monthly Statistics  
      
## Tech Stack
 - Java 11      
 - Spring Boot 2.6.0
   - Spring Web 
   - Spring Security 
   - JWT
   - Spring Data MongoDB 
   - Bean Validation   
 - MongoDB
 - Swagger2
 - Docker    
 - Docker Compose
 
 ### Documentation
For swagger documentation; 
```
http://localhost:8080/swagger-ui.html
```  

### Usage  

docker run : 
```
docker build -f Dockerfile -t reading-is-good .  
docker run -p 8080:8080 reading-is-good  
```  

To access endpoints get bearer token with username and password that provided. (For test usage, username is **test**, password is **1234**)
Get token from;
```
http://localhost:8080/auth/getToken
```  
