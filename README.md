# JAutoLayout Demo

This is a Java-Vaadin web application for demoing [JAutoLayout](https://github.com/kausko/JAutoLayout) - a constraint-based layout management library for Java that uses [Apple's AutoLayout and Visual Format Language (VFL)](https://developer.apple.com/library/archive/documentation/UserExperience/Conceptual/AutolayoutPG/VisualFormatLanguage.html) for layout specification.

## Running the application

The project is a standard Maven project. To run it from the command line,
run `mvnw`, then open
http://localhost:8080 in your browser.

You can also import the project to your IDE of choice as you would with any
Maven project. Read more on [how to import Vaadin projects to different IDEs](https://vaadin.com/docs/latest/guide/step-by-step/importing) (Eclipse, IntelliJ IDEA, NetBeans, and VS Code).

## Deploying to Production

The project is dockerized for deployment with any cloud provider. Please consult the documentation of your cloud provider for more details.

To manually create a production build, run `mvnw clean package -Pproduction`.
This will build a JAR file with all the dependencies and front-end resources,
ready to be deployed. The file can be found in the `target` folder after the build completes.

Once the JAR file is built, you can run it using
`java -jar target/jautolayout-1.0-SNAPSHOT.jar`

## Project structure

- `views` package in `src/main/java` contains the server-side Java views of your application.
- `views` folder in `frontend/` contains the client-side JavaScript views of your application.
- `themes` folder in `frontend/` contains the custom CSS styles.
