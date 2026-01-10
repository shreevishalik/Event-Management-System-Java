# Event Management System (C)

A menu-driven Event Management System developed in C using file handling.
The system manages events and participant registrations with persistent storage using text files.

## Features
- Add new events
- View available events
- Register participants
- Store data using `.txt` files
- Simple menu-driven interface

## Technologies Used
- C Programming Language
- File Handling (`.txt` files)

## Project Structure
Event-Management-System/
├── src/
│   └── event_management.c
├── data/
│   ├── events.txt
│   └── registrations.txt
└── README.md

## File Description
- `event_management.c` – Main C source file containing program logic
- `events.txt` – Stores event details
- `registrations.txt` – Stores participant registration details

## How to Run
1. Open terminal / command prompt
2. Navigate to the `src` directory
3. Compile the program:
   gcc event_management.c -o event_management
4. Run the executable:
   ./event_management
5. Make sure the `.txt` files are present in the `data` folder

## Learning Outcomes
- Practical understanding of file handling in C
- Experience with menu-driven programs
- Improved logical thinking and program structuring
