SmartPark:Intelligent Vehicle Parking Management System
A Mini Real-World Java Application
Team Members:
Ann Mariya Sajeev -24UBC112
Vaishnavi Vijayan -24UBC158
Course: BCA
Internal Assessment – Mini Real-World Java Application
Course Outcome: CO5 – Develop and execute Java programs implementing object-oriented principles, GUI components, exception handling, multithreading, and database connectivity using JDBC.
 1.Introduction
In today’s rapidly evolving digital era, automation plays a crucial role in improving efficiency and minimizing human errors. Traditional parking management systems often rely on manual record keeping and human supervision, leading to inefficiencies, incorrect billing, and poor slot monitoring.
SmartPark is a desktop-based Java application designed to automate and streamline parking operations in real-world environments such as shopping malls, hospitals, corporate offices, airports, and residential complexes. The system provides an interactive GUI that allows users to manage parking slots, calculate billing automatically, and maintain records efficiently.
The project integrates Object-Oriented Programming principles, exception handling, multithreading, and JDBC database connectivity, thereby demonstrating practical implementation of Course Outcome CO5.
 2.Problem Statement
Manual parking systems suffer from:
Duplicate vehicle entries
Incorrect billing calculations
No proper data storage
Lack of real-time slot monitoring
Human errors in record management
There is a need for an automated system that ensures accuracy, reliability, and efficiency in managing parking operations.
 3.Objectives
 ------------
The main objectives of SmartPark are:
To design a real-world Java GUI application
To implement core OOP concepts
To integrate JDBC for database connectivity
To apply exception handling for robust performance
To use multithreading for background processing
To simulate an intelligent parking management system
4.OOP Concepts Implemented
-------------------------
-Encapsulation
Database operations are encapsulated within the ParkingDAO class.
-Abstraction
The ParkingOperations interface defines core parking functionalities.
-Polymorphism
Method overriding allows flexible booking and extension behavior.
-Inheritance
The LiveUpdater class extends Thread to enable multithreading.
5.Key Features
-------------
-Slot Booking
-Enter vehicle number and owner name
-Select number of hours
-Automatic slot allocation
-Duplicate vehicle detection-
-Slot color changes to Re-d
 -Extend Booking
-Add extra hours
-Automatic bill recalculation
-Database update
 -Slot Reservation
-Reserve slot in advance
-Slot marked Blue
-Prevents booking by others
 -Slot Release
-Release occupied slot
-Record removed from database
-Slot becomes Available
-Automated Billing System
-₹50 per hour calculation
-Instant total amount display
-Dynamic billing logic
-Payment Simulation
Simulated payment confirmation via:
GPay
PhonePe
Paytm
User-Friendly GUI
Developed using Java Swing:
-Forms
-Buttons
-Dialog boxes
-Color-coded slot grid
-Real-time status display
 6.Database Connectivity
 -----------------------
Database Used: H2 Embedded Database
JDBC is used to:
Insert booking records
Update booking details
Delete released slots
Fetch parking data
Table Structure:
Field
Type
slot_id
INT
vehicle_no
VARCHAR
owner_name
VARCHAR
hours
INT
amount
DOUBLE
status
VARCHAR
7.Multithreading
----------------
The LiveUpdater thread:
Runs in the background
Refreshes slot status every 5 seconds
Keeps GUI responsive
Prevents application freezing
8.Exception Handling
-------------------
Handled scenarios:
Empty input fields
Invalid vehicle number
Negative or zero hours
Duplicate vehicle booking
Database errors
Uses try-catch blocks and validation logic.
 9.Technologies Used
 -------------------
-Java
-Java Swing
-JDBC
-H2 Database
-Multithreading
Git & GitHub
10.Project Structure
--------------------
Copy code

SmartPark/
│
├── src/
│   ├── SmartParkGUI.java
│   ├── ParkingDAO.java
│   ├── ParkingOperations.java
│   ├── LiveUpdater.java
│
├── lib/
│   └── h2.jar
│
├── screenshots/
│
└── README.md
11.Steps to Run
---------------
Clone the repository
Open in IntelliJ IDEA or NetBeans
Add H2 .jar file to libraries
Compile the project
Run SmartParkGUI.java
12.Sample Test Case
-------------------
Input:
Vehicle No: KL07AB1234
Owner Name: Ammu
Hours: 3
Output:
Booking Successful
Total Amount: ₹150
Slot marked as Booked (Red)
13.Future Enhancements
----------------------
-Online payment gateway integration
-MySQL cloud database
-Admin dashboard
-Report generation module
-QR-based vehicle entry system
-Mobile application version
14.Conclusion
-------------
SmartPark – Intelligent Vehicle Parking Management System successfully demonstrates the practical implementation of core Java programming concepts in a real-world scenario. The project integrates Object-Oriented Programming principles, GUI development using Java Swing, exception handling for robust execution, multithreading for responsive background processing, and JDBC-based database connectivity for persistent data management.
The system effectively automates parking operations such as slot booking, reservation, billing calculation, and record maintenance, thereby reducing human errors and improving operational efficiency. The use of a structured layered architecture ensures maintainability, scalability, and modularity of the application.
Through this project, theoretical knowledge has been transformed into a functional software solution that simulates real-time parking management environments like malls, hospitals, offices, and residential complexes. The implementation reflects strong understanding of software design principles, problem-solving skills, and application development practices.
Overall, SmartPark fulfills the requirements of Course Outcome CO5 and serves as a foundation for developing more advanced, enterprise-level management systems in the future.



screnshots
----------
[screenshots.pdf](https://github.com/user-attachments/files/25554384/screenshots.pdf)

screenshot order
---------------
after released.png
before booking selected screenshot.png
booking.png
exexend time.png
extend time confirm.png
main GUI.png
reserved.png



