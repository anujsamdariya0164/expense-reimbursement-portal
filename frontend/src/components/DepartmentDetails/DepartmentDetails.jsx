import React, { useEffect } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { useDepartmentStore } from '../../store/useDepartmentStore'
import { useClaimStore } from '../../store/useClaimStore'

const DepartmentDetails = () => {
  const params = useParams()

  const {id: departmentId} = params

  const {department, getDepartmentById} = useDepartmentStore()

  const {claims, getClaimsByDepartment} = useClaimStore()

  const navigate = useNavigate()

  useEffect(() => {
    console.log(departmentId)
    getDepartmentById(departmentId)
    getClaimsByDepartment(departmentId)
  }, [])

  useEffect(() => {
    console.log(department)
    console.log(claims)
  }, [department, claims])

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
            <h1><span className='font-bold'>Department ID: </span> {department.id ?? '-'}</h1>
            <h1><span className='font-bold'>Department Name: </span> {department.name ?? '-'}</h1>
          </div>
          
          <div>
            <h1><span className='font-bold'>Manager Email: </span> {department.managerEmail ?? '-'}</h1>
            <h1><span className='font-bold'>Manager Name: </span> {department.managerName ?? '-'}</h1>
          </div>

          <div>
            <h1><span className='font-bold'>Budget Limit: </span> ${department.budgetLimit ?? '-'}</h1>
            <h1><span className='font-bold'>Budget Amount: </span> ${department.budgetAmount ?? '-'}</h1>
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
            {
              department.managerId === null ? (
                <button 
                  className='font-bold text-2xl cursor-pointer border-white border-2 p-3 rounded hover:bg-white hover:text-[#303030] flex items-end h-auto' 
                  onClick={() => navigate(`/admin/dashboard/manager/assign/${department.id}`)}
                >
                  <h1>Assign Manager +</h1>
                </button>
              ) : (
                <button 
                  className='font-bold text-2xl cursor-pointer border-white border-2 p-3 rounded hover:bg-white hover:text-[#303030] flex items-end h-auto' 
                  onClick={() => navigate(`/admin/dashboard/employee/add/${department.id}`)}
                >
                  <h1>Add Employee +</h1>
                </button>
              )
            }

            {
              department.budgetId === null ? (
                <button 
                  className='font-bold text-2xl cursor-pointer border-white border-2 p-3 rounded hover:bg-white hover:text-[#303030] flex items-end h-auto' 
                  onClick={() => navigate(`/admin/dashboard/budget/add/${department.id}`)}
                >
                  <h1>Add Budget +</h1>
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
                    claims && claims.length > 0 && 
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
              <div className='h-[10vh] border-2 flex justify-center items-center'>
                <h1>No claims made</h1>
              </div>
            )
          }
        </div>
      </div>
  </div>
  )
}

export default DepartmentDetails