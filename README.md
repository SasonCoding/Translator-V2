[bla.json](https://github.com/user-attachments/files/15522837/bla.json)![Screenshot 2024-06-01 155330](https://github.com/SasonCoding/Translator/assets/76630855/17b98867-5e94-4add-991a-7cff5f188024)TravelFactory Translation Management Application
Overview

Technologies Used
Backend: Java with Spring Boot
Frontend: React
Features
Screen 1: Application Management
View Applications: List all existing applications.
Add Application: Opens a popup to enter a new application's name.
Download Translations: Download an csv file with all translations keys for the selected application.
Deploy Translations: Save translations as a JSON file on the server under the application’s name.
![Uploading Scre[bla.json](https://github.com/user-attachments/files/15522816/bla.json)enshot 2024-06-01 155330.png…]()

Screen 2: Translation Management
Add Translation Key: Form to add new translation keys for the selected application, including fields for key, language code, and text.
Usage Instructions
Running the Application:

make sure to use the currect json format when deploying it to the selected applicaiton:
{
  "entries": [
    {
      "key": "message",
      "translations": [
        {
          "languageCode": "en",
          "text": "Welcome to our application!"
        },
        {
          "languageCode": "fr",
          "text": "Bienvenue dans notre application!"
        }
      ]
    }
  ]
}


Clone the repository.
Navigate to the project directory.
Run mvn spring-boot:run to start the backend server.
Navigate to the frontend directory.
Run npm install and npm start to start the frontend server.
Accessing the Application:

Open a web browser and navigate to http://localhost:3000.
Use the application management screen to manage applications and download or deploy translations.
Use the translation management screen to add translation keys for applications.
Future Enhancements
Translation Validation: Ensure data integrity with validation checks.
Enhanced Reporting: Provide detailed reporting and analytics.
This project provides an efficient way for business teams to manage translations, reducing developer dependency and accelerating the deployment process.
