# 🛒 Analysis and Design of Software Systems Course

**Second Year, Second Semester | Java | SQLite | Swing | JUnit**

This repository contains the final project completed for the **Analysis and Design of Software Systems** course.  
The project focuses on designing and implementing a supermarket management system with delivery, and employee modules, supporting both GUI and CLI interfaces.

The development was first done in **two teams of two**, each focusing on separate modules (Delivery / HR Management), and later combined into a **single team of four** to integrate the full system.

---

## 📂 Repository Structure

- **Delivery Management Module**
- **Employee Management Module**
- **Graphical and Textual User Interfaces**
- **SQLite Database Integration**
- **JUnit Testing Suite**

---

## 🧩 Project Summaries

### 🚚 Delivery Management Module

- Manages the **logistics and scheduling** of supermarket deliveries.
- Features:
  - Resource Management (Sites, Trucks, Drivers).
  - Delivery Planning, Creation, Editing, and Cancellation.
  - Truck weight limit enforcement and cooling requirements.
  - JSON-based delivery order parsing and analysis.
- Highlights:
  - Structured delivery advancement process with error handling.
  - Support for managing multi-stop deliveries within shipping zones.

---

### 👥 HR and Employee Management Module

- Manages **human resources operations** for the supermarket.
- Features:
  - Employee Addition, Editing, and Role Assignment.
  - Shift Management and Assignment (Branch Employees, Drivers).
  - Employee Availability Constraints Management.
  - Product Cancellation Permissions for Authorized Roles.
- Highlights:
  - Role-based access control (Store Manager, HR Manager, Employee).
  - Authentication system based on ID and password.

---

### 🖥️ GUI and CLI Interfaces

- Both **Graphical User Interface** (Swing-based) and **Command-Line Interface** are supported.
- Users can interact based on role:
  - Store Manager
  - HR Manager
  - Delivery Manager
  - Regular Employee
- Flexible launching options using specific terminal commands.

---

## 🛠️ Technologies Used

- Java 17
- SQLite with `sqlite-jdbc`
- Swing (GUI Framework)
- Jackson (JSON Parsing)
- JUnit 5.8.1 (Testing)

---

## 📄 How to Run

1. Place `adss2023_v03.jar` in a folder.
2. Open Terminal in that folder.
3. Run one of the following commands depending on desired mode:

```bash
java -jar adss2023_v03.jar # Opens the GUI for the Store Manager.
java -jar adss2023_v03.jar CLI  # Opens the CLI for the Store Manager.
java -jar adss2023_v03.jar CLI StoreManager # Opens the CLI for the Store Manager.
java -jar adss2023_v03.jar CLI HRManager  # Opens the CLI for the HR Manager.
java -jar adss2023_v03.jar CLI DeliveryManager # Opens the CLI for the Delivery Manager.
java -jar adss2023_v03.jar CLI Employee # Opens the CLI for a Regular Employee.
java -jar adss2023_v03.jar GUI # Opens the GUI for the Store Manager.
java -jar adss2023_v03.jar GUI StoreManager # Opens the GUI for the Store Manager.
java -jar adss2023_v03.jar GUI HRManager # Opens the GUI for the HR Manager.
java -jar adss2023_v03.jar GUI DeliveryManager # Opens the GUI for the Delivery Manager.
java -jar adss2023_v03.jar GUI Employee # Opens the GUI for a Regular Employee.
java -cp adss2023_v03.jar SuperLiMainCLI # Opens the CLI for the Store Manager.
java -cp adss2023_v03.jar SuperLiMainGUI # Opens the GUI for the Store Manager.
java -cp adss2023_v03.jar HRManagerCLI # Opens the CLI for the HR Manager.
java -cp adss2023_v03.jar HRManagerGUI # Opens the GUI for the HR Manager.
java -cp adss2023_v03.jar EmployeeCLI # Opens the CLI for a Regular Employee.
java -cp adss2023_v03.jar EmployeeGUI # Opens the GUI for a Regular Employee.
java -cp adss2023_v03.jar DeliveryManagerCLI # Opens the CLI for the Delivery Manager.
java -cp adss2023_v03.jar DeliveryManagerGUI # Opens the GUI for the Delivery Manager.
```

---

## 🛡️ Highlights

- ✅ Full-featured supermarket delivery and HR management system.
- 🖥️ Dual-mode interaction: GUI (Swing) and CLI.
- 📦 Integrated JSON parsing and database persistence.
- 🔒 Role-based authentication and secure access control.
- 🚚 Realistic delivery planning with resource and constraint management.

---

## 👨‍💻 Team Members

- Omer Dor
- Adi Cohen
- Martin Korotkov
- Kfir Nissim

---

## Notes:

Sample data is included in the code in the resetData function.

Delivery order JSON example:
{
 "date": "14/04/2023",
 "sourceAddress" : "Beer Sheva",
 "destAddress" : "Tel Aviv",
 "sourceContactName" : "Moshe",
 "destContactName" : "Avi",
 "sourcePhone" : "0502222222",
 "destPhone" : "0501111111",
 "products" : [ {
    "first" : "222-222",
    "second" : 5,
    "third" : 1.0
 }, {
    "first" : "222-222",
    "second" : 5,
    "third" : 1.0
 } ]
}
