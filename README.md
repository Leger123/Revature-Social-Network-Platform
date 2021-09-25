# Earth Best Revature Social Network

## Description
In this social network, everyone is friends with everyone. As a user, you will be able to register and login to your account. When you successfully login, you are automatically friends with everyone which means you will see a feed of everyone's posts. Users will be able to create a post and like other people's posts. Users will also have the ability to view other profiles which will display posts of that specific user.

## Technologies
* Apache Tomcat
* Spring Boot
* Spring Data
* Spring MVC
* Docker
* Bootstrap
* Servlet
* Jackson Databind
* Bcrypt
* Junit
* Log4j
* JavaMail
* PostgreSQL
* Mockito
* Sonar Cloud
* Angular 2+
* AWS S3
* AWS EC2
* AWS RDS

## Features
* Can login to the social media site.
* Can register a new account for the social media site.
* Can request a new password at the homepage.
* Can create posts.
* When creating posts the user can add text, images, and videos.
* The user can comment another user's posts.
* The user can like other posts
* The user can update their own profile.
* The user can search for other users.
* The user can see other user's posts.
* The user will be informed of other users' actions through notifications.

## Improvements
* CSS Styling of the website.
* Reinfe the way the search is handled.
* Refine the way the users get notifications.
* Refine the comment post and like post functions.

## Getting Started
* Make sure you have IntelliJ and Visual Studio Code.
* In IntelliJ make sure you have all the dependencies needed for the project. The following code will show you what dependencies you will need:
* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>

		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>com.amazonaws</groupId>
			<artifactId>aws-java-sdk-s3</artifactId>
			<version>1.12.55</version>
		</dependency>

		<dependency>
			<groupId>commons-fileupload</groupId>
			<artifactId>commons-fileupload</artifactId>
			<version>1.4</version>
		</dependency>

		<dependency>
			<groupId>at.favre.lib</groupId>
			<artifactId>bcrypt</artifactId>
			<version>0.9.0</version>
		</dependency>

		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.17</version>
		</dependency>

		<dependency>
			<groupId>com.sun.mail</groupId>
			<artifactId>javax.mail</artifactId>
			<version>1.6.2</version>
		</dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <scope>test</scope>
        </dependency>

    </dependencies>
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
* Make sure your application.properties file in IntelliJ is set up correctly. The following code will show what it should look like:
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
server.port = 9000

spring.datasource.url = jdbc:postgresql://revaturedatabase.cf37gycdfqtj.us-east-2.rds.amazonaws.com/social
spring.datasource.username = postgres
spring.datasource.password = p4ssw0rd
spring.datasource.driver-class-name = org.postgresql.Driver
spring.jpa.database-platform = org.hibernate.dialect.PostgreSQL82Dialect

spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto = validate
spring.jpa.properties.hibernate.temp.user_jdbc_metadata_defaults = false
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation = true

spring.servlet.multipart.max-file-size=2MB
spring.servlet.multipart.max-request-size=2MB
spring.servlet.multipart.enabled=true
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
* Once this is set up, run the SpringBoot Application.
* In your VCS code open up the frontend folder and select the folder "social-network-frontend"
* On the Command Line Interface (CLI), switch to using Git Bash, and type in npm-install command. The image below will illustrate this.
![Project#2_3](https://user-images.githubusercontent.com/90068310/134755509-3d05a92a-6b2b-4e63-ad4c-9299899d4c78.PNG)

* You will also need to use the ng add @angular/material command in the CLI, as it is using some Angular Mateiral resources.
* Once this is accomplished, use the ng serve -o command to run the website. If successful it should look like this:
![Project#2_4](https://user-images.githubusercontent.com/90068310/134755598-17c67b7f-d509-4d96-9d14-ae49e666067c.PNG)


## Usage
* When you start the homepage, you will be immediately be taken to the login. If you don't have an account yet, click on the register button to create an account and fill int the required info to make an account. If you decide to not create an account, then just hit the back to login button. The following image will show what it should look like:
![Project#2_11](https://user-images.githubusercontent.com/90068310/134755666-d8474b0c-86ab-4069-8a1f-4f1b66d2f575.PNG)

* If you forgot your password, click on the forgot password button and put in your email to reset your password.
![Project#2_12](https://user-images.githubusercontent.com/90068310/134755793-0496d9ab-0715-4c80-a440-eaf8973b2ccb.PNG)

* Once logged in you will be directed to the dashboard page. On the dashboard page you will be able to see all the posts. In addition to see all notifications click on the clock button on the top left corner. If you want to create a post click on the plus button on the bottom right corner. To comment a post tpye what you want to say on the rectangular textbox below the post and hit the submit button next to it. To like a post, hit the like post. Use the tabs to head to update your profile or serach other users and their posts.
![Project#2_6](https://user-images.githubusercontent.com/90068310/134755889-d4ee04f9-ac4f-4fc9-8edf-f29d48a751e1.PNG)

* When creating a post type in what you want to say on the text box. To put in an image click on the choose file button, select the image, and then hit the upload button. To add video you will need its id, which is located on the right side of "v=". Copy and paste the id on the rectangular box.
![Project#2_7](https://user-images.githubusercontent.com/90068310/134755950-96e5c9ee-344a-4519-ac83-c4dafd3fb2d3.PNG)
![Project#2_13](https://user-images.githubusercontent.com/90068310/134755952-221f4530-409f-4db0-be54-7a0a633f9cb2.PNG)

* To update your profile simply change your name and password. You can also upload a profile image if you wish.
![Project#2_8](https://user-images.githubusercontent.com/90068310/134756038-0c4c6d5e-1ac3-44f1-be8f-e143ca634272.PNG)

* In the serach page, to view another user and their posts click on their name link. Use the search bar to narrow down the list of people you want to see.
![Project#2_14](https://user-images.githubusercontent.com/90068310/134756079-fbbc2183-4bd0-4581-8f19-1f08f6be7dd5.PNG)
![Project#2_9](https://user-images.githubusercontent.com/90068310/134756082-3a03a6a5-bd2f-4950-9ccb-3ab869e762e4.PNG)

* To logout hit the logout button, which is the yellow button, and it will redirect you back to the homepage.
![Project#2_15](https://user-images.githubusercontent.com/90068310/134756109-49ac29e5-68c7-4ec1-a0fe-4a7451ae812c.PNG)

## Contributors
* Zimi Li (Team Lead): <zimi.li@revature.net>
* Andrew Patrick
* Kevin Dian
* Bhavani Yelagala
