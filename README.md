# University ERP System (Java + Swing)

Overview
This project is a desktop-based University ERP system developed in Java using Swing.
It supports role-based access for Students, Instructors, and Admins to manage courses, enrollments, grades, and users.

The system uses a separate authentication database and an ERP database to ensure security and clean design.

Roles and Features:

Student
Browse course catalog
Register and drop course sections
View timetable and grades
Download academic transcript

Instructor
View assigned course sections
Enter assessment scores
Compute final grades
View basic class statistics

Admin
Add students and instructors
Create courses and sections
Assign instructors to sections
Enable or disable Maintenance Mode (read-only access)

Key Concepts Used:
Role-based access control
Maintenance mode enforcement
Secure password hashing
Separate Auth DB and ERP DB
Clear separation between UI, service, and data layers

Tech Stack:
Language: Java
UI Framework: Swing
Database: MySQL or MariaDB
Build: Standard JDK

Project Structure:

edu.univ.erp
ui
domain
service
data
auth
access
util

How to Run:
Set up the Auth and ERP databases
Configure database credentials
Run the main application class
Log in using sample accounts

Academic Information:
Course: Advanced Programming
Project Type: Team-based academic project

Note:
This repository represents a collaborative academic project created for coursework evaluation.
Team Members- Arja Kaur Anand & Janisha Mehta
