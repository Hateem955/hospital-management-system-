# 🏥 Hospital Management System API

## Built with Spring Boot and JWT Authentication

This is a robust **Hospital** **Appointment** **System** built using **Spring Boot** and secured with **JSON Web Tokens (JWT)**. It provides a complete set of RESTful APIs for patient registration, doctor listing, and appointment management.

## 🌐 Live Demo (Railway Cloud Deployment)

**Want to test the API without any local setup?** Use our live cloud deployment!

### Base URL:
```
    Full URL: https://hospital-management-system-production-195e.up.railway.app
```

### 📮 Testing with Postman:
Simply replace `http://localhost:8080` with the Railway URL in all endpoints.

**Example:**
```
POST https://https://tinyurl.com/zmezvadp/register/patient
POST https://https://tinyurl.com/zmezvadp/login
GET https://https://tinyurl.com/zmezvadp/doctors
```

## **🛠️ Technology Stack**

- **Backend Framework**: Spring Boot

- **Authentication**: JWT (JSON Web Tokens)

- **Testing Tool**: Postman (Recommended)

- **Database**: (Specify your database, e.g., MySQL)

## **🚀 Getting Started**

Follow these steps to set up and run the application locally.

### **Prerequisites**

- Java Development Kit (JDK) 17+

- Maven or Gradle

- Docker & Docker compose
- A running database instance (as specified in the Tech Stack).

### **1. Project Setup**

1. **Clone the Repository:**
```
git clone [YOUR_REPO_URL]
cd your-repo-name
```


2. **Configuration: Create the .env File**

The application requires specific credentials for the database and email service, which are loaded from an environment file ```(.env)``` that is ignored by Git for security.

Create a file named ```(.env)``` in the root directory, where ```pom.xml``` or ```docker-compose.yml``` file is present,  of the project and populate it with the following structure, replacing the placeholder values:

```
## Database Credentials (used by docker-compose.yml and application.properties)
DB_ROOT_PASSWORD=YOUR_DB_ROOT_PASSWORD_HERE
DB_NAME=hospital

# Email Credentials (used by application.properties for appointment notifications)
# NOTE: If using Gmail, you must generate an App Password, not your regular account password.
MAIL_USERNAME=YOUR_EMAIL@gmail.com
MAIL_PASSWORD=YOUR_GMAIL_APP_PASSWORD
MAIL_FROM_ADDRESS=YOUR_EMAIL@gmail.com
```

⚠️ Security Note: If using a cloud email service (like Gmail), use an App Password for the ```MAIL_PASSWORD``` to ensure security.

**3.  **Run the Application (Docker Compose)****

Since the `HospitalApp.jar` file is already included in the `target/` directory, you only need to run one command to start the entire containerized system:

```
docker-compose up --build

```

The application will start on port ```8080``` (or as configured) at ```http://localhost:8080```.

**Note:** The application database comes pre-populated with initial doctor profiles and schedules, so you can proceed immediately to patient registration and appointment booking (Step 1 of the Patient API Workflow).

### **Default Accounts for Testing**
| Role | Username | Password | Notes |
| :--- | :--- | :--- | :--- |
| **Admin** | `admin` | `admin123` | Use this to create new doctor accounts. |
| **Doctor** | `Bob` | (Hashed) | Used for Dr. Bob Johnson |
| **Doctor** | `Steve` | (Hashed) | Used for Dr. Steve Williams | 


### 💻 Patient API Workflow

All APIs run on the base URL ```http://localhost:8080```. Use **Postman** to easily test these steps.

**1. Register Account**

Create a new patient user account.


| Method | Endpoint | Description | 
| ----- | ----- | ----- | 
| **POST** | `/register/patient` | Creates a new user with username and password. |
#### **Request Body Example:**
```
{
    "username": "YOUR_NAME",
    "password": "YOUR_PASSWORD"
}
```

**2. Log in and Get JWT Token**

Authenticate to receive the JWT Token, which is mandatory for all secure API calls.

| Method | Endpoint | Description                          | 
| ----- |----------|--------------------------------------| 
| **POST** | `/login` | Authenticates and returns the token. |
#### **Request Body Example:**
```

{
    "username": "YOUR_NAME",
    "password": "YOUR_PASSWORD"
}
```

🔑 **IMPORTANT**: Save the token and pass it in the **Authorization** header as **Bearer Token** for subsequent requests.

**3. Set Patient Details**

Complete your patient profile.

| Method | Endpoint               | Description | 
| ----- |------------------------| ----- | 
| **POST** | `/register/setPatient` | Sets patient name, email (for notifications), and age. |

#### **Request Body Example:**
```
{
    "name": "Jane Doe",
    "email": "jane.doe@example.com",
    "age": "35"
}
```

**4. View all Doctors**

Retrieve a list of available doctors.

| Method  | Endpoint   | Description | 
|---------|------------| ----- | 
| **GET** | `/doctors` | Lists doctors with specialties, experience, and slots. |

Example URL (using pagination): ```http://localhost:8080/doctors?page=1&size=3```

**5. Book Appointment**

Book an appointment using the desired doctor's ID and a specific available slot ID.

| Method | Endpoint | Description | 
| ----- | ----- | ----- | 
| **POST** | `/bookAppointment` | Creates a new appointment booking. |
#### **Request Body Example:**
```
{
        "doc_id": "1",
        "symptoms": "High fever for last 3 days",
        "slot_id": "1"
}
```

**6. Track your Appointments**

View all your booked appointments and their current status.

| Method  | Endpoint | Description | 
|---------| ----- | ----- | 
| **GET** | `/viewMyAppointment` | Returns a list of all your appointments and their status. |




### **📧 Notifications**

Once a doctor approves an appointment using the `PUT /approve/{appointment_id}` API, an email will be automatically dispatched to the patient's registered email address.

### **⚙️ Admin API Workflow (Creating New Doctors)**
The administrator logs in using the default credentials (`admin/admin123`) to get a JWT token. This token is required for all doctor creation endpoints.

**Step A: Register Doctor User**

The admin registers a new user with the `ROLE_DOCTOR`.

| Method   | Endpoint | Description | 
|----------| ----- | ----- | 
| **POST** | `/register/doctor` | Creates the doctor's login credentials. |

#### **Request Body Example:**

```
{
    "username": "new_doctor_user",
    "password": "strong_password"
}
```

📌 IMPORTANT: This request returns the `userId` of the newly created doctor, which is required for Step B.

**Step B: Set Doctor Details**

The admin assigns specialization and experience to the doctor's user ID.

| Method   | Endpoint | Description | 
|----------| ----- | ----- | 
| **POST** | `/register/setDoctor/{user_id}` | Sets the doctor's profile details.

**Example URL**: `http://localhost:8080/register/setDoctor/3` (Using the ID returned in Step A)


#### **Request Body Example:**

```
{
    "name": "Dr. Steve Newby",
    "specialization": "Dermatologist",
    "yearsOfExperience": "4"
}
```

**Step C: Create Available Slots**

The admin sets the doctor's schedule (e.g., a daily shift).

| Method   | Endpoint | Description | 
|----------| ----- | ----- | 
| **POST** | `/create-slot` | Adds a range of available time slots for a doctor.

#### **Request Body Example:**

```
{
    "doctorId": "3", 
    "startTime": "10:00",
    "endTime": "14:00"
}
```

### **👩‍⚕️ Additional Doctor API Endpoints**

| Method  | Endpoint                    | Description                                            | 
|---------|-----------------------------|--------------------------------------------------------| 
| **GET** | `/viewMyAppointments`       | View all appointments booked for the logged-in doctor. |
| **PUT** | `/approve/{appointment_id}` | Approve a specific appointment by ID.                  | 
| **GET** | `/viewApprovedAppointments` | View all approved appointments for logged-in doctor.   | 
