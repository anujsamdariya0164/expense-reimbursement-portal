# Expense Reimbursement Portal

This is an expense reimbursement portal where employees can seek raise claims and get reimbursed accordingly.

### Features:

1. **Role-based access control with roles:** ADMIN, MANAGER, and EMPLOYEE.
2. **EMPLOYEE:** Can raise claims and check there status.
3. **MANAGER:** Can check claims sent in his department and approve them according to the budget assigned to his/her department.
4. **ADMIN:** Can oversee all the departments, there employees, managers, and also check the audit logs that document all the status updates. The admin is responsible for the final reimbursement.

## How to run it on your device?
To run this application on your device, follow the steps mentioned below:

1. Clone the repository:
```bash
git clone https://github.com/anujsamdariya0164/expense-reimbursement-portal.git .
```

2. Run the server: 
- Move to `backend` folder
```bash
cd backend/
```
- Package the application
```bash
mvn clean package # ensure that JAVA_HOME variable is set
```
- Run the package
```bash
java -jar target/expenseReimbursementPortal-0.0.1-SNAPSHOT.jar
```

3. Run the frontend: 
- In another terminal, move to `frontend` folder
```bash
cd frontend/
```
- Install all the dependencies
```bash
npm install
```
- Run the react app
```bash
npm run dev
```