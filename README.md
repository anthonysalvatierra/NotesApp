Description: It is an application where you can log in and manage your notes.

Technologies: The following technologies were used to create this application: -JDK 17 -Maven -Spring Mvc -Spring Jpa -Thymeleaf -H2 database -Itellij Idea -Spring Security

Considerations before use: You will have to have maven 3.9.6 and have Jdk version 17, in addition to having the JAVA_HOME environment variable added to your system.

The following steps will tell you how to use the application:

Step 1: You will need to clone the repository with the following command: git clone https://github.com/ensolvers-github-challenges/NoteApp.git

or download the .zip directly.

Step 2: Unzip the .zip file and from the terminal go to the project address, for example: yubal@yubal-Ubuntu:~/Download/NoteApp-main/NotesApp$

Step 3: Once you are in the project directory, execute the command ./mvnw package This will download and deploy the application on your computer, for example: yubal@yubal-Ubuntu:~/Download/NoteApp-main/NotesApp$ sudo ./mvnw package

Step 4: Once the command has been executed, all the project dependencies will have been downloaded. The next thing is to enter the target folder and execute the following command: java -jar NotesApp-0.0.1-SNAPSHOT.jar for example: yubal@yubal-Ubuntu:~/Download/NoteApp-main/NotesApp/target$ java -jar NotesApp-0.0.1-SNAPSHOT.jar

Step 5: Once the application has been launched, it will be ready to use. In your preferred browser, access the route: http://localhost:8080 and start using the application.

Grades: To enter the application you must register, validations are not available, which is why it is better to fill out all the data to test the application.
