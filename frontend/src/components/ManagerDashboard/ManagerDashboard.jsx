import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import {useAuthStore} from '../../store/useAuthStore'
import { useDepartmentStore } from '../../store/useDepartmentStore'
import { useClaimStore } from '../../store/useClaimStore'

const ManagerDashboard = () => {
    const {authUser} = useAuthStore()

    const {department, getDepartmentById} = useDepartmentStore()

    const {claims, getClaimsByDepartment} = useClaimStore()

  const navigate = useNavigate()

  useEffect(() => {
    getDepartmentById(authUser.departmentId)
  }, [authUser])

  useEffect(() => {
    getClaimsByDepartment(department.id)
  }, [department])
  
    return (
        <div className='flex flex-col m-6'>
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
                    <h1><span className='font-bold'>Budget Limit: </span> ${department.budgetLimit ?? '-'}</h1>
                    <h1><span className='font-bold'>Budget Amount: </span> ${department.budgetAmount ?? '-'}</h1>
                </div>
            </div>

            <div className='mb-5 flex justify-between'>
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
    )
}

export default ManagerDashboard