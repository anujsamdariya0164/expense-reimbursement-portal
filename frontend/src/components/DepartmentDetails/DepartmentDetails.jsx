import React from 'react'
import { useNavigate } from 'react-router-dom'

const DepartmentDetails = () => {
  const claims = [
  {
    "amount": 10000,
    "approvalMode": "AUTO",
    "budgetId": 1,
    "category": "MEALS",
    "comment": null,
    "departmentId": 1,
    "departmentName": "Engineering",
    "employeeEmail": "vihaan.joshi@gmail.com",
    "employeeId": 8,
    "employeeName": "Vihaan Joshi",
    "id": 4,
    "proofUrl": "https://example.com/proof.jpg",
    "status": "REJECTED"
  },
  {
    "amount": 100000,
    "approvalMode": "AUTO",
    "budgetId": 1,
    "category": "MEALS",
    "comment": "This is a travel claim for a business trip.",
    "departmentId": 1,
    "departmentName": "Engineering",
    "employeeEmail": "vihaan.joshi@gmail.com",
    "employeeId": 8,
    "employeeName": "Vihaan Joshi",
    "id": 6,
    "proofUrl": "https://example.com/proof.jpg",
    "status": "REJECTED"
  },
  {
    "amount": 10000,
    "approvalMode": "MANAGER_AND_ADMIN",
    "budgetId": 1,
    "category": "TRAVEL",
    "comment": "This is a travel claim for a business trip.",
    "departmentId": 1,
    "departmentName": "Engineering",
    "employeeEmail": "vihaan.joshi@gmail.com",
    "employeeId": 8,
    "employeeName": "Vihaan Joshi",
    "id": 1,
    "proofUrl": "https://example.com/proof.jpg",
    "status": "APPROVED"
  },
  {
    "amount": 10000,
    "approvalMode": "MANAGER_AND_ADMIN",
    "budgetId": 1,
    "category": "TRAVEL",
    "comment": "This is a travel claim for a business trip.",
    "departmentId": 1,
    "departmentName": "Engineering",
    "employeeEmail": "vihaan.joshi@gmail.com",
    "employeeId": 8,
    "employeeName": "Vihaan Joshi",
    "id": 2,
    "proofUrl": "https://example.com/proof.jpg",
    "status": "APPROVED"
  },
  {
    "amount": 10000,
    "approvalMode": "MANAGER_AND_ADMIN",
    "budgetId": 1,
    "category": "TRAVEL",
    "comment": "This is a travel claim for a business trip.",
    "departmentId": 1,
    "departmentName": "Engineering",
    "employeeEmail": "vihaan.joshi@gmail.com",
    "employeeId": 8,
    "employeeName": "Vihaan Joshi",
    "id": 3,
    "proofUrl": "https://example.com/proof.jpg",
    "status": "PAID"
  },
  {
    "amount": 10000,
    "approvalMode": "MANAGER_AND_ADMIN",
    "budgetId": 1,
    "category": "TRAVEL",
    "comment": "This is a travel claim for a business trip.",
    "departmentId": 1,
    "departmentName": "Engineering",
    "employeeEmail": "vihaan.joshi@gmail.com",
    "employeeId": 8,
    "employeeName": "Vihaan Joshi",
    "id": 7,
    "proofUrl": "https://example.com/proof.jpg",
    "status": "PAID"
  },
  {
    "amount": 10000,
    "approvalMode": "MANAGER_AND_ADMIN",
    "budgetId": 1,
    "category": "MEALS",
    "comment": "This is a travel claim for a business trip.",
    "departmentId": 1,
    "departmentName": "Engineering",
    "employeeEmail": "vihaan.joshi@gmail.com",
    "employeeId": 8,
    "employeeName": "Vihaan Joshi",
    "id": 8,
    "proofUrl": "https://example.com/proof.jpg",
    "status": "PAID"
  },
  {
    "amount": 100,
    "approvalMode": "AUTO",
    "budgetId": 1,
    "category": "MEALS",
    "comment": "This is a travel claim for a business trip.",
    "departmentId": 1,
    "departmentName": "Engineering",
    "employeeEmail": "vihaan.joshi@gmail.com",
    "employeeId": 8,
    "employeeName": "Vihaan Joshi",
    "id": 9,
    "proofUrl": "https://example.com/proof.jpg",
    "status": "PAID"
  },
  {
    "amount": 10000,
    "approvalMode": "MANAGER_AND_ADMIN",
    "budgetId": 1,
    "category": "MEALS",
    "comment": "This is a travel claim for a business trip.",
    "departmentId": 1,
    "departmentName": "Engineering",
    "employeeEmail": "vihaan.joshi@gmail.com",
    "employeeId": 8,
    "employeeName": "Vihaan Joshi",
    "id": 5,
    "proofUrl": "https://example.com/proof.jpg",
    "status": "PAID"
  },
  {
    "amount": 1000,
    "approvalMode": "MANAGER",
    "budgetId": 1,
    "category": "MEALS",
    "comment": "This is a travel claim for a business trip.",
    "departmentId": 1,
    "departmentName": "Engineering",
    "employeeEmail": "vihaan.joshi@gmail.com",
    "employeeId": 8,
    "employeeName": "Vihaan Joshi",
    "id": 10,
    "proofUrl": "https://example.com/proof.jpg",
    "status": "PAID"
  },
  {
    "amount": 2000,
    "approvalMode": "MANAGER_AND_ADMIN",
    "budgetId": 1,
    "category": "MEALS",
    "comment": "This is a travel claim for a business trip.",
    "departmentId": 1,
    "departmentName": "Engineering",
    "employeeEmail": "vihaan.joshi@gmail.com",
    "employeeId": 8,
    "employeeName": "Vihaan Joshi",
    "id": 11,
    "proofUrl": "https://example.com/proof.jpg",
    "status": "PAID"
  },
]

  const department = {
    "budgetAmount": 45200,
    "budgetId": 1,
    "budgetLimit": 50000,
    "employees": [
      {
        "email": "pari.khanna@gmail.com",
        "id": 2,
        "name": "Pari Khanna",
        "role": "MANAGER"
      },
      {
        "email": "vihaan.joshi@gmail.com",
        "id": 8,
        "name": "Vihaan Joshi",
        "role": "EMPLOYEE"
      },
      {
        "email": "ishwar.verma@gmail.com",
        "id": 9,
        "name": "Ishwar Verma",
        "role": "EMPLOYEE"
      },
      {
        "email": "aditya.kapoor@gmail.com",
        "id": 10,
        "name": "Aditya Kapoor",
        "role": "EMPLOYEE"
      }
    ],
    "id": 1,
    "managerEmail": "pari.khanna@gmail.com",
    "managerId": 2,
    "managerName": "Pari Khanna",
    "name": "Engineering"
  }

  const navigate = useNavigate()

  return (
    <div className='flex flex-col m-6 gap-5'>
      <div>
        <div>
          <h1 className='font-bold text-3xl underline'>
            Department Details
          </h1>
        </div>

        <div className='flex justify-between p-5'>
          <div>
            <h1><span className='font-bold'>Department ID: </span> {department.id}</h1>
            <h1><span className='font-bold'>Department Name: </span> {department.name}</h1>
          </div>

          <div>
            <h1><span className='font-bold'>Budget Limit: </span> ${department.budgetLimit}</h1>
            <h1><span className='font-bold'>Budget Amount: </span> ${department.budgetAmount}</h1>
          </div>
        </div>
      </div>

      <div>
        <div className='mb-3 flex justify-between'>
          <div className='flex justify-between'>
            <div>
              <h1 className='font-bold text-3xl underline'>List Of Employees</h1>
              <h3>Click the show more button at the rightmost column to know more employee detais</h3>
            </div>
          </div>

          <div className='flex gap-5 items-center'>
            <button 
              className='font-bold text-2xl cursor-pointer border-white border-2 p-3 rounded hover:bg-white hover:text-[#303030] flex items-end h-auto' 
              onClick={() => navigate(`/admin/dashboard/employee/add/${department.id}`)}
            >
              <h1>Add Employee +</h1>
            </button>

            {
              department.managerId === null ? (
                <button 
                  className='font-bold text-2xl cursor-pointer border-white border-2 p-3 rounded hover:bg-white hover:text-[#303030] flex items-end h-auto' 
                  onClick={() => navigate(`/admin/dashboard/manager/assign/${department.id}`)}
                >
                  <h1>Assign Manager +</h1>
                </button>
              ) : (<></>)
            }
          </div>
        </div>

        <div className='w-full'>
          {
            department.employees && 
            (
              <table className='w-full border-2'>
                <thead>
                  <tr className='grid grid-cols-5 p-5'>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>More Details</th>
                  </tr>
                </thead>

                <tbody>
                  {
                    department.employees && 
                    department.employees.map((employee, index) => (
                      <tr key={index} className='grid grid-cols-5 p-5 text-center border-2'>
                        <td>{employee.id}</td>
                        <td>{employee.name}</td>
                        <td>{employee.email}</td>
                        <td>{employee.role ?? '-'}</td>
                        <td 
                          className='underline font-semibold text-sm cursor-pointer' 
                          onClick={() => navigate(`/admin/dashboard/employee/${employee.id}`)}
                          >Show More</td>
                      </tr>
                    ))
                  }
                </tbody>
              </table>
            )
          }

          {
            !department.employees && (
              <h1>No employees added yet</h1>
            )
          }
        </div>
      </div>

      <div>
        <div className='mb-3 flex justify-between'>
          <div>
            <h1 className='font-bold text-3xl underline'>Manage Claims</h1>
            <h3>Click the status button at the rightmost column to update any claim's status (it will not work for statuses that are already paid or rejected)</h3>
          </div>
        </div>

        <div className='w-full'>
          {
            claims && 
            (
              <table className='w-full border-2'>
                <thead>
                  <tr className='grid grid-cols-5 p-5'>
                    <th>ID</th>
                    <th>Amount</th>
                    <th>Category</th>
                    <th>Proof URL</th>
                    <th>Status</th>
                  </tr>
                </thead>

                <tbody>
                  {
                    claims && 
                    claims.map((claim, index) => (
                      <tr key={index} className='grid grid-cols-5 p-5 text-center border-2'>
                      <td>{claim.id}</td>
                      <td>${claim.amount}</td>
                      <td>{claim.category}</td>
                      <td>{claim.proofUrl ?? '-'}</td>
                      <td>
                        <button 
                          disabled={claim.status === 'REJECTED' || claim.status === 'PAID'}
                          className={`p-2 rounded ${claim.status === 'REJECTED' || claim.status === 'PAID' ? 'text-gray-300' : 'cursor-pointer'} bg-gray-700`}
                          onClick={() => navigate(`/claim/${claim.id}`)}
                        >
                          {claim.status}
                        </button>
                      </td>
                      </tr>
                    ))
                  }
                </tbody>
              </table>
            )
          }

          {
            !claims && (
              <h1>No claims made</h1>
            )
          }
        </div>
      </div>
  </div>
  )
}

export default DepartmentDetails