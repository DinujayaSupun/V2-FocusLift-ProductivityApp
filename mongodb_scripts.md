# MongoDB Database Creation Scripts

## Database Creation Commands

### 1. Connect and Create Database
```javascript
// Connect to MongoDB
use employee_management_system

// Create collections with validation
db.createCollection("employees")
db.createCollection("attendance")
db.createCollection("training")
db.createCollection("courses")
db.createCollection("cfo")
db.createCollection("certifications")
db.createCollection("prevention_officers")
db.createCollection("clients")
db.createCollection("inventory_items")
db.createCollection("inventory_logs")
db.createCollection("orders")
db.createCollection("suppliers")
db.createCollection("purchase_orders")
db.createCollection("reports")
db.createCollection("budget")
db.createCollection("salary")
db.createCollection("transactions")
db.createCollection("mission_records")
```

### 2. Index Creation
```javascript
// Primary indexes
db.employees.createIndex({"emp_id": 1}, {unique: true})
db.attendance.createIndex({"attendance_id": 1}, {unique: true})
db.training.createIndex({"training_id": 1}, {unique: true})
db.courses.createIndex({"course_id": 1}, {unique: true})
db.cfo.createIndex({"cfo_id": 1}, {unique: true})
db.certifications.createIndex({"certification_id": 1}, {unique: true})
db.prevention_officers.createIndex({"pv_id": 1}, {unique: true})
db.clients.createIndex({"client_id": 1}, {unique: true})
db.inventory_items.createIndex({"item_id": 1}, {unique: true})
db.inventory_logs.createIndex({"log_id": 1}, {unique: true})
db.orders.createIndex({"order_id": 1}, {unique: true})
db.suppliers.createIndex({"supplier_id": 1}, {unique: true})
db.purchase_orders.createIndex({"purchase_id": 1}, {unique: true})
db.reports.createIndex({"report_id": 1}, {unique: true})
db.budget.createIndex({"budget_id": 1}, {unique: true})
db.salary.createIndex({"salary_id": 1}, {unique: true})
db.transactions.createIndex({"transaction_id": 1}, {unique: true})
db.mission_records.createIndex({"mission_id": 1}, {unique: true})

// Secondary indexes for performance
db.employees.createIndex({"email": 1})
db.attendance.createIndex({"emp_id": 1, "date": 1})
db.training.createIndex({"emp_id": 1})
db.inventory_items.createIndex({"category": 1})
db.inventory_logs.createIndex({"item_id": 1, "log_date": 1})
db.salary.createIndex({"emp_id": 1, "period_start": 1})
```

## Sample Data Insertion

### 1. Insert Sample Employee
```javascript
db.employees.insertOne({
  "emp_id": "EMP001",
  "first_name": "John",
  "last_name": "Doe",
  "phone_num": "+94-11-1234567",
  "title": "Software Engineer",
  "email": "john.doe@company.com",
  "qualification": "BSc IT",
  "duty_status": "Active",
  "created_at": new Date(),
  "updated_at": new Date()
})
```

### 2. Insert Sample Attendance Record
```javascript
db.attendance.insertOne({
  "attendance_id": "ATT001",
  "emp_id": "EMP001",
  "date": new Date("2024-01-15"),
  "check_in": new Date("2024-01-15T08:00:00Z"),
  "check_out": new Date("2024-01-15T17:00:00Z"),
  "work_hours": 8.5,
  "ot_hours": 1.5,
  "created_at": new Date()
})
```

### 3. Insert Sample Training Record
```javascript
db.training.insertOne({
  "training_id": "TRN001",
  "emp_id": "EMP001",
  "specialization": "Data Science",
  "qualification": "MSc Data Science",
  "year_of_experience": 3,
  "status": "Active",
  "created_at": new Date()
})
```

### 4. Insert Sample Course
```javascript
db.courses.insertOne({
  "course_id": "CRS001",
  "course_name": "Advanced Data Analytics",
  "start_date": new Date("2024-02-01"),
  "end_date": new Date("2024-04-30"),
  "description": "Comprehensive course on data analytics",
  "status": "Active",
  "created_at": new Date()
})
```

### 5. Insert Sample CFO
```javascript
db.cfo.insertOne({
  "cfo_id": "CFO001",
  "emp_id": "EMP002",
  "qualification": "ACCA",
  "duty_status": "Active",
  "created_at": new Date()
})
```

### 6. Insert Sample Certification
```javascript
db.certifications.insertOne({
  "certification_id": "CERT001",
  "cfo_id": "CFO001",
  "start_date": new Date("2024-01-01"),
  "end_date": new Date("2026-12-31"),
  "status": "Valid",
  "created_at": new Date()
})
```

### 7. Insert Sample Prevention Officer
```javascript
db.prevention_officers.insertOne({
  "pv_id": "PV001",
  "emp_id": "EMP003",
  "specialization": "Fire Safety",
  "certifications": ["Fire Safety Level 1", "Emergency Response"],
  "status": "Active",
  "created_at": new Date()
})
```

### 8. Insert Sample Client
```javascript
db.clients.insertOne({
  "client_id": "CLT001",
  "name": "ABC Corporation",
  "type": "Corporate",
  "address": "123 Business Street, Colombo",
  "contact_person": "Jane Smith",
  "phone": "+94-11-9876543",
  "email": "info@abccorp.com",
  "status": "Active",
  "created_at": new Date()
})
```

### 9. Insert Sample Inventory Item
```javascript
db.inventory_items.insertOne({
  "item_id": "ITM001",
  "item_name": "Fire Extinguisher",
  "quantity": 50,
  "threshold": 10,
  "expire_date": new Date("2025-12-31"),
  "category": "Safety Equipment",
  "location": "Warehouse A",
  "status": "Active",
  "created_at": new Date()
})
```

### 10. Insert Sample Inventory Log
```javascript
db.inventory_logs.insertOne({
  "log_id": "LOG001",
  "item_id": "ITM001",
  "quantity": 5,
  "charge_type": "Issue",
  "remarks": "Issued to Fire Safety Team",
  "log_date": new Date("2024-01-15"),
  "created_by": "EMP004",
  "created_at": new Date()
})
```

## Query Examples

### 1. Find Employee with Attendance
```javascript
db.employees.aggregate([
  {
    $lookup: {
      from: "attendance",
      localField: "emp_id",
      foreignField: "emp_id",
      as: "attendance_records"
    }
  },
  {
    $match: {
      "emp_id": "EMP001"
    }
  }
])
```

### 2. Find Low Stock Items
```javascript
db.inventory_items.find({
  $expr: {
    $lte: ["$quantity", "$threshold"]
  }
})
```

### 3. Calculate Total Salary for Employee
```javascript
db.salary.aggregate([
  {
    $match: {
      "emp_id": "EMP001"
    }
  },
  {
    $group: {
      _id: "$emp_id",
      total_salary: { $sum: "$total_amount" },
      total_ot: { $sum: "$ot_amount" }
    }
  }
])
```

### 4. Find Active Employees with Training
```javascript
db.employees.aggregate([
  {
    $lookup: {
      from: "training",
      localField: "emp_id",
      foreignField: "emp_id",
      as: "training_info"
    }
  },
  {
    $match: {
      "duty_status": "Active"
    }
  }
])
```

## Data Validation

### 1. Employee Validation
```javascript
db.runCommand({
  collMod: "employees",
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["emp_id", "first_name", "last_name", "email"],
      properties: {
        emp_id: {
          bsonType: "string",
          description: "Employee ID must be a string"
        },
        email: {
          bsonType: "string",
          pattern: "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
          description: "Email must be a valid email address"
        }
      }
    }
  }
})
```

### 2. Attendance Validation
```javascript
db.runCommand({
  collMod: "attendance",
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["attendance_id", "emp_id", "date"],
      properties: {
        work_hours: {
          bsonType: "double",
          minimum: 0,
          maximum: 24,
          description: "Work hours must be between 0 and 24"
        }
      }
    }
  }
})
```

## Backup and Restore

### 1. Create Backup
```bash
mongodump --db employee_management_system --out /backup/
```

### 2. Restore Database
```bash
mongorestore --db employee_management_system /backup/employee_management_system/
```

## Performance Monitoring

### 1. Check Index Usage
```javascript
db.employees.getIndexes()
db.employees.stats()
```

### 2. Explain Query Performance
```javascript
db.employees.find({"emp_id": "EMP001"}).explain("executionStats")
```

### 3. Monitor Collection Stats
```javascript
db.stats()
db.employees.stats()
```
