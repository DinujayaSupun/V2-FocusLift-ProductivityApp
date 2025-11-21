# MongoDB Database Design for Employee Management System

## Overview
This document presents the MongoDB database design for an Employee Management System, converted from the provided relational schema. The design follows MongoDB best practices and demonstrates document-oriented database modeling.

## Database Name
`employee_management_system`

## Collections Design

### 1. Employees Collection
```javascript
// Collection: employees
{
  "_id": ObjectId("..."),
  "emp_id": "EMP001",
  "first_name": "John",
  "last_name": "Doe",
  "phone_num": "+94-11-1234567",
  "title": "Software Engineer",
  "email": "john.doe@company.com",
  "qualification": "BSc IT",
  "duty_status": "Active",
  "created_at": ISODate("2024-01-01"),
  "updated_at": ISODate("2024-01-01")
}
```

### 2. Attendance Collection
```javascript
// Collection: attendance
{
  "_id": ObjectId("..."),
  "attendance_id": "ATT001",
  "emp_id": "EMP001",
  "date": ISODate("2024-01-15"),
  "check_in": ISODate("2024-01-15T08:00:00Z"),
  "check_out": ISODate("2024-01-15T17:00:00Z"),
  "work_hours": 8.5,
  "ot_hours": 1.5,
  "created_at": ISODate("2024-01-15")
}
```

### 3. Training Collection
```javascript
// Collection: training
{
  "_id": ObjectId("..."),
  "training_id": "TRN001",
  "emp_id": "EMP001",
  "specialization": "Data Science",
  "qualification": "MSc Data Science",
  "year_of_experience": 3,
  "status": "Active",
  "created_at": ISODate("2024-01-01")
}
```

### 4. Courses Collection
```javascript
// Collection: courses
{
  "_id": ObjectId("..."),
  "course_id": "CRS001",
  "course_name": "Advanced Data Analytics",
  "start_date": ISODate("2024-02-01"),
  "end_date": ISODate("2024-04-30"),
  "description": "Comprehensive course on data analytics",
  "status": "Active",
  "created_at": ISODate("2024-01-01")
}
```

### 5. CFO Collection
```javascript
// Collection: cfo
{
  "_id": ObjectId("..."),
  "cfo_id": "CFO001",
  "emp_id": "EMP002",
  "qualification": "ACCA",
  "duty_status": "Active",
  "created_at": ISODate("2024-01-01")
}
```

### 6. Certifications Collection
```javascript
// Collection: certifications
{
  "_id": ObjectId("..."),
  "certification_id": "CERT001",
  "cfo_id": "CFO001",
  "start_date": ISODate("2024-01-01"),
  "end_date": ISODate("2026-12-31"),
  "status": "Valid",
  "created_at": ISODate("2024-01-01")
}
```

### 7. Prevention Officers Collection
```javascript
// Collection: prevention_officers
{
  "_id": ObjectId("..."),
  "pv_id": "PV001",
  "emp_id": "EMP003",
  "specialization": "Fire Safety",
  "certifications": ["Fire Safety Level 1", "Emergency Response"],
  "status": "Active",
  "created_at": ISODate("2024-01-01")
}
```

### 8. Clients Collection
```javascript
// Collection: clients
{
  "_id": ObjectId("..."),
  "client_id": "CLT001",
  "name": "ABC Corporation",
  "type": "Corporate",
  "address": "123 Business Street, Colombo",
  "contact_person": "Jane Smith",
  "phone": "+94-11-9876543",
  "email": "info@abccorp.com",
  "status": "Active",
  "created_at": ISODate("2024-01-01")
}
```

### 9. Inventory Items Collection
```javascript
// Collection: inventory_items
{
  "_id": ObjectId("..."),
  "item_id": "ITM001",
  "item_name": "Fire Extinguisher",
  "quantity": 50,
  "threshold": 10,
  "expire_date": ISODate("2025-12-31"),
  "category": "Safety Equipment",
  "location": "Warehouse A",
  "status": "Active",
  "created_at": ISODate("2024-01-01")
}
```

### 10. Inventory Logs Collection
```javascript
// Collection: inventory_logs
{
  "_id": ObjectId("..."),
  "log_id": "LOG001",
  "item_id": "ITM001",
  "quantity": 5,
  "charge_type": "Issue",
  "remarks": "Issued to Fire Safety Team",
  "log_date": ISODate("2024-01-15"),
  "created_by": "EMP004",
  "created_at": ISODate("2024-01-15")
}
```

### 11. Orders Collection
```javascript
// Collection: orders
{
  "_id": ObjectId("..."),
  "order_id": "ORD001",
  "item_id": "ITM001",
  "requested_quantity": 20,
  "request_date": ISODate("2024-01-10"),
  "approved_by": "EMP005",
  "approve_date": ISODate("2024-01-12"),
  "status": "Approved",
  "created_at": ISODate("2024-01-10")
}
```

### 12. Suppliers Collection
```javascript
// Collection: suppliers
{
  "_id": ObjectId("..."),
  "supplier_id": "SUP001",
  "name": "Safety Equipment Ltd",
  "email": "sales@safetyequip.com",
  "phone": "+94-11-5555555",
  "address": "456 Industrial Zone, Colombo",
  "supplier_type": "Equipment",
  "quality_rating": 4.5,
  "status": "Active",
  "created_at": ISODate("2024-01-01")
}
```

### 13. Purchase Orders Collection
```javascript
// Collection: purchase_orders
{
  "_id": ObjectId("..."),
  "purchase_id": "PO001",
  "supplier_id": "SUP001",
  "delivery_terms": "FOB Colombo",
  "issue_date": ISODate("2024-01-15"),
  "required_date": ISODate("2024-02-15"),
  "expected_delivery_date": ISODate("2024-02-10"),
  "status": "Pending",
  "total_amount": 50000.00,
  "currency": "LKR",
  "created_at": ISODate("2024-01-15")
}
```

### 14. Reports Collection
```javascript
// Collection: reports
{
  "_id": ObjectId("..."),
  "report_id": "RPT001",
  "type": "Monthly",
  "period": "January 2024",
  "issued_date": ISODate("2024-02-01"),
  "description": "Monthly Safety Equipment Report",
  "financial_manager_id": "EMP006",
  "status": "Published",
  "created_at": ISODate("2024-02-01")
}
```

### 15. Budget Collection
```javascript
// Collection: budget
{
  "_id": ObjectId("..."),
  "budget_id": "BGT001",
  "year": 2024,
  "period_start": ISODate("2024-01-01"),
  "period_end": ISODate("2024-12-31"),
  "allocated_amount": 1000000.00,
  "role": "Safety Equipment",
  "financial_manager_id": "EMP006",
  "status": "Active",
  "created_at": ISODate("2024-01-01")
}
```

### 16. Salary Collection
```javascript
// Collection: salary
{
  "_id": ObjectId("..."),
  "salary_id": "SAL001",
  "emp_id": "EMP001",
  "period_start": ISODate("2024-01-01"),
  "period_end": ISODate("2024-01-31"),
  "basic_salary": 75000.00,
  "ot_hours": 12,
  "ot_amount": 9000.00,
  "total_amount": 84000.00,
  "status": "Paid",
  "created_at": ISODate("2024-01-31")
}
```

### 17. Transactions Collection
```javascript
// Collection: transactions
{
  "_id": ObjectId("..."),
  "transaction_id": "TXN001",
  "type": "Expense",
  "source": "Safety Equipment Purchase",
  "transaction_date": ISODate("2024-01-15"),
  "total_amount": 50000.00,
  "description": "Purchase of fire extinguishers",
  "financial_manager_id": "EMP006",
  "status": "Completed",
  "created_at": ISODate("2024-01-15")
}
```

### 18. Mission Records Collection
```javascript
// Collection: mission_records
{
  "_id": ObjectId("..."),
  "mission_id": "MSN001",
  "item_id": "ITM001",
  "mission_date": ISODate("2024-01-20"),
  "mission_type": "Emergency Response",
  "location": "Building A, Floor 3",
  "description": "Fire safety inspection and equipment deployment",
  "status": "Completed",
  "created_at": ISODate("2024-01-20")
}
```
