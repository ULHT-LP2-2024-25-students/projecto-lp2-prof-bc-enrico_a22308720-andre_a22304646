# The Night of the Living DEISI - Part 1

**Project Version**: V1.0.0  
**Course**: Programming Languages II - 2024/25  
**University**: Universidade Lusófona de Humanidades e Tecnologias  
**Authors**: Bruno Cipriano, Duarte Neves, Pedro Alves

---

## Project Overview

The objective of this project is to develop a Java application, following Object-Oriented Programming principles such as encapsulation, inheritance, polymorphism, etc. This project is divided into two parts:

1. **Part 1** - Focuses on modeling and implementing the basic functionalities of the system.
2. **Part 2** - Introduces new requirements, potentially requiring modifications to the existing model and adding new functionalities.

In Part 1, the goal is to:
- Design a UML class diagram to model the system based on the requirements provided.
- Implement the model and basic functionalities using **Java 17**.
- Write unit tests using **JUnit 5** to ensure code reliability.

---

## Technical Restrictions

- No use of inheritance or interfaces is allowed.
- Streams are prohibited.
- Static variables and functions should be avoided to maintain a pure object-oriented approach.

---

## Unit Testing

The project includes at least 4 test cases for the methods within the `GameManager` class, as part of the API specification. The tests are written using **JUnit 5** and are located in the `pt.ulusofona.lp2.thenightofthelivingdeisi.TestGameManager2.java` file.

---

## Creativity and Customization (Optional)

Although optional in Part 1, students are encouraged to customize the game by:
- Defining new images for characters and equipment through the `getSquareInfo()` function.
- Personalizing the credits window using `getCreditsPanel()`.
- Customizing the board layout using the `customizeBoard()` method.

Screenshots of any customization can be included in this README file.

---

## UML Diagram

Below is the UML class diagram representing the model designed for this project:

![](diagrama.png?raw=true "UML Class Diagram")

---

## How to Run the Project

To run this project, follow the steps below:
1. Download the required `.jar` file from Moodle and place it in the `lib` folder.
2. Add the `.jar` file as a library in your IntelliJ project.
3. Ensure that the `GameManager` class is correctly implemented with the required methods.
4. Run the application using the `AppLauncher` class from the provided `.jar` file.

---

## Project Structure

The project follows the structure outlined below:

