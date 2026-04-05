import React from 'react'
import { useParams } from 'react-router-dom'

const EmployeeDetails = () => {
    const param = useParams()

    const employee = {
      "departmentId": 2,
      "departmentName": "Product Management",
      "email": "ananya.verma@gmail.com",
      "id": 3,
      "managerEmail": null,
      "managerId": null,
      "managerName": null,
      "name": "Ananya Verma",
      "role": "MANAGER",
      "roleId": 2
    }

  return (
    <div className='flex items-center justify-center h-[70vh]'>
      <div className='px-[20rem] py-[2rem] border-white border-2 rounded flex flex-col justify-center items-center gap-2'>
        <h1 className='font-bold text-2xl'>Employee Details for ID: {(employee && employee.id) ?? '-'}</h1>

        <div className='flex flex-col gap-[1rem] items-center'>
          <h3><span className='font-bold'>Name:</span> {(employee && employee.name) ?? '-'}</h3>

          <h3><span className='font-bold'>Email:</span> {(employee && employee.email) ?? '-'}</h3>

          <h3><span className='font-bold'>Role:</span> {(employee && employee.role) ?? '-'}</h3>

          <h3><span className='font-bold'>Department ID:</span> {(employee && employee.departmentId) ?? '-'}</h3>

          <h3><span className='font-bold'>Department Name:</span> {(employee && employee.departmentName) ?? '-'}</h3>
        </div>
      </div>
    </div>
  )
}

export default EmployeeDetails