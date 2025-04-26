# Mobile-Service-Testing
CS 320: Software Test, Automation
<br/>
<br/>(From the SNHU CS-320 Syllabus)
<br/>Students will apply software engineering testing strategies and practices as part of the software development life cycle, including requirements analysis, verification and validation, and quality management. The creation of unit tests and analysis of various testing approaches will also be covered.

# Course Competencies
This course covers the following competencies, which represent the knowledge and skills relevant to the field:
<br/>CS-30415: Create unit tests using code to uncover errors
<br/>CS-30416: Analyze various approaches to software testing based on requirements
<br/>CS-30417: Apply appropriate testing strategies to meet requirements

# Software and Tools
<br/>Java 17: For writing all the classes and unit tests.
<br/>Eclipse IDE for Java Developers: For coding, project management, and running tests. Also used Eclipse's built-in Code Coverage tool to measure test coverage.
<br/>JUnit 5 (JUnit Jupiter): For writing and executing automated unit tests.

# Software Test Project Overview
__Project Summary__<br/>
This project is a backend service for a mobile application, built with Java and tested using JUnit 5. It includes Contact, Task, and Appointment management, each with strict validation rules for fields like IDs, names, phone numbers, dates, and descriptions.<br/>
- ContactService manages contacts and supports adding, updating, and deleting contacts with validated fields.
- TaskService manages tasks, allowing name and description updates while enforcing immutability of task IDs.
- AppointmentService manages appointments with future date validation and description constraints.<br/>

Unit tests cover object creation, field validation, and service operations. Achieved over 80% code coverage using Eclipse's coverage tools to ensure functionality and reliability.

__Ensuring Functionality and Security__<br/>
I ensured the functionality of the project by writing detailed JUnit 5 unit tests to validate both valid and invalid behaviors. I tested boundary cases, field constraints, and service operations like adding, updating, and deleting objects. I confirmed over 80% code coverage using Eclipse tools. Input validation was built into each class to reject invalid data, helping maintain program stability and reduce risks.

__Incorporating User Needs__<br/>
User needs were interpreted directly from the project’s requirements, such as enforcing character limits, non-null fields, and immutability for IDs. I implemented these rules in both the object and service classes and tested how the program handled both correct and incorrect input to ensure it aligned with real-world use expectations.

__Approach to Software Design__<br/>
My approach to designing the software focused on keeping classes modular, simple, and aligned with their specific roles. Each service class was built to manage its corresponding object cleanly, and validation was handled at the object level. I designed unit tests alongside the code to ensure that functionality was enforced and easy to maintain over time.
