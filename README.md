# CountySuggestionApplication

This Spring Boot application provides an API endpoint to suggest counties based on user input. Users can search for counties by name or state using the /suggest GET request.

Prerequisites:
- Java JDK 8 or newer
- Maven
- IDE for Java development (e.g., IntelliJ IDEA, Eclipse, Visual Studio Code)

Setting Up the Project:
1) Clone the Repository
      Use Git to clone the project repository to your local machine:  git clone <repository-url>
      Replace <repository-url> with the actual URL of your project repository. Open the Project
3) Import Dependencies
     The project uses Maven or Gradle for dependency management. Most IDEs will automatically import the dependencies once you open the project.
     If needed, you can manually trigger the dependency import in your IDE or use the command line:
     For Maven: mvn clean install

Running the Application Locally:
1) Start the Application
    You can start the application directly from your IDE by running the main method in the CountySuggestionApplication class.
Alternatively, use the command line to start the application:
For Maven:
    mvn spring-boot:run
2) Access the Application:
     Once the application is running, you can access the API at http://localhost:8080/suggest?q=<query>, replacing <query> with your search term (e.g., a county name or state abbreviation).
