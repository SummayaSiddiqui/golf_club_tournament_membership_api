# Golf Club Tournament Membership API

This is a Spring Boot-based API that manages golf club tournament memberships, using MySQL as the database.

## Prerequisites

Ensure you have the following installed on your system:

1. IntelliJ IDEA
2. Docker Desktop
3. MySQL
4. Postman

---
## Setup Instructions

1. Clone the Repository
   - Open Intellij IDEA.
   - Navigate to the terminal.
   - Type the following command to clone the repository

       ```
         git clone https://github.com/SummayaSiddiqui/golf_club_tournament_membership_api
       ```
2. Project Structure
    This project includes a `Dockerfile` in the root directory, which is used to build the Docker image for the Spring Boot application. The Dockerfile specifies how to package and run the application in a container environment.

3. Change Directory
    - Use the given command to navigate to the desired directory

        ```
          cd golf_club_tournament_membership_api
        ```
4. Configure Environment Variables
    To ensure the application works correctly, you need to make sure that the MySQL root password and the Spring Boot datasource password match. To change the passwords, follow these steps:
     - Open the `docker-compose.yml` file.
     - Locate the `mysql` service and update the `MYSQL_ROOT_PASSWORD` environment variable:
    
        ```
          environment:
            MYSQL_ROOT_PASSWORD: <your_password>
        ```
      - Locate the `myapp-main` service and update the `spring.datasource.password` environment variable to match:

        ```
          environment:
            spring.datasource.password=<your_password>
        ```
     **Important**: The value of `MYSQL_ROOT_PASSWORD` in the `mysql` service must match the value of `spring.datasource.password` in the `myapp-main` service.
    
    **Note**: The application is configured to use a database named `golf_club`. This is specified in the JDBC URL:
      ```
        spring.datasource.url=jdbc:mysql://mysql-db:3306/golf_club
      ```
    Ensure that this database name matches your requirements or update it if needed.

5. Build the Project
   - Run the following commands:
     ```
       mvn install
       mvn clean package
     ```
6. Start Services with Docker Compose
   - Run the following command to start all services:
       ```
         docker-compose up --build
       ```
   - **MySQL Service**:
     - A MySQL container will be created using the `mysql:8.0` image.
     - The database data will be persisted in a Docker volume named `mysql_data`.
     - An initialization SQL script (`init.sql`) will be executed during startup.
     - The MySQL service is named `mysql-db` within the Docker network. This is the hostname that the Spring Boot application uses to          connect to the database.
   - **Application Service**:
     - The Spring Boot application will start and connect to the MySQL database.
     - It will be accessible at `http://localhost:8080`.

7. Verify Services
   - **MySQL**: Ensure that MySQL is running by connecting to it using a client or checking logs.
   - **Application**: Test the API by sending requests via Postman or a browser.

---

## Notes

  - If you encounter issues with database connectivity, double-check that:
    - The MySQL container is running.
    - The database credentials (`username`, `password`) are correct.
    - The database URL (`jdbc:mysql://mysql-db:3306/golf_club`) is correct.
  
  - To stop all services, run:
     ```
       docker-compose down
     ```
---

## Additional Configuration

**Database Initialization**:
  - The `init.sql` file is included in the repository and will be automatically cloned along with the project.
  - This script will execute automatically when the MySQL container starts, ensuring the database is populated with initial data.
  
**Customizing Ports**:
  - Update the ports in `docker-compose.yml` if needed:
    ```
      ports:
        - "3306:3306" # For MySQL
        - "8080:8080" # For Spring Boot Application
    ```

---

By following these steps, you can set up and run the Golf Club Tournament Membership API successfully.

---

## API Endpoints

### Member Endpoints

- Get all members:
  ```
    GET http://localhost:8080/api/members/allMembers
  ```
- Get member by name:
    ```
      GET http://localhost:8080/api/members/name/John Doe
    ```
- Get members by address:
    ```
      GET http://localhost:8080/api/members/getMemberByAddress/123 Maple Street, Toronto
    ```
- Get member by phone number
   ```
     GET http://localhost:8080/api/members/getMemberByPhoneNumber/647-555-1234
   ```
- Get member by email address
    ```
      GET http://localhost:8080/api/members/getMemberByEmailAddress/johndoe1@email.com
    ```
- Get member by start date (YYYY-MM-DD)
  ```
    GET http://localhost:8080/api/members/getMemberByStartDate?startDate=2024-01-10
  ```
- Create a new member
    ```
      POST http://localhost:8080/api/members
    ```
  Example POST Request Body:
    ```
      {
        "memberName": "Brad Stokes",
        "memberAddress": "456 Oak Street",
        "memberEmailAddress": "bstokes@example.com",
        "memberPhoneNumber": "709-245-7091",
        "memberStartDate": "2024-01-01",
        "duration": "14 months"
      }
    ```
---

### Tournament Endpoints

- Get all tournaments:
  ```
    GET http://localhost:8080/api/tournaments/allTournaments
  ```
- Get tournament by start date:
    ```
      GET http://localhost:8080/api/tournaments/getTournamentByStartDate/2024-08-01
    ```
- Get tournament by end date:
    ```
      GET http://localhost:8080/api/tournaments/getTournamentByEndDate/2024-08-05
    ```
- Get tournament by location:
    ```
      GET http://localhost:8080/api/tournaments/getTournamentByLocation/Toronto
    ```
- Get members in a tournament
   ```
     GET http://localhost:8080/api/tournaments/getMembersInTournament/1
   ```
- Create a new tournament
    ```
      POST http://localhost:8080/api/tournaments
    ```
  Example POST Request Body:
    ```
      {
        "startDate": "2025-03-15",
        "endDate": "2025-03-18",
        "location": "st.John's",
        "entryFee": "30.0",
        "cashPrizeAmount": 1000.0,
        "participatingMembers": [
            {
                "id": 1,
                "memberName": "John Doe",
                "memberAddress": "123 Maple Street, Toronto",
                "memberEmailAddress": "johndoe1@email.com",
                "memberPhoneNumber": "647-555-1234",
                "memberStartDate": "2024-01-10",
                "duration": "1 year"
            },
            {
                "id": 3,
                "memberName": "Robert Brown",
                "memberAddress": "789 Pine Road, Calgary",
                "memberEmailAddress": "robertbrown@email.com",
                "memberPhoneNumber": "403-555-9012",
                "memberStartDate": "2024-02-20",
                "duration": "1 year"
            },
            {
                "id": 5,
                "memberName": "Michael Wilson",
                "memberAddress": "456 Oak Avenue, Vancouver",
                "memberEmailAddress": "michaelwilson@email.com",
                "memberPhoneNumber": "604-555-7890",
                "memberStartDate": "2024-04-12",
                "duration": "1 year"
            }
        ]
      }
    ```
