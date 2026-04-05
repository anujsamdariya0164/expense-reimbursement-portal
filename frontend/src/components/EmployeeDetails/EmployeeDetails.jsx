import React, { useEffect } from 'react'
import { useParams } from 'react-router-dom'
import { useUserStore } from '../../store/useUserStore'

const EmployeeDetails = () => {
    const param = useParams()

    const {user, getUserById} = useUserStore()

    useEffect(() => {
      if (!param.id) return

      getUserById(param.id)
    }, [param.id])

  return (
    <div className='flex items-center justify-center h-[70vh]'>
      <div className='px-[20rem] py-[2rem] border-white border-2 rounded flex flex-col justify-center items-center gap-2'>
        <h1 className='font-bold text-2xl'>Employee Details for ID: {(user && user.id) ?? '-'}</h1>

        <div className='flex flex-col gap-[1rem] items-center'>
          <h3><span className='font-bold'>Name:</span> {(user && user.name) ?? '-'}</h3>

          <h3><span className='font-bold'>Email:</span> {(user && user.email) ?? '-'}</h3>

          <h3><span className='font-bold'>Role:</span> {(user && user.role) ?? '-'}</h3>

          <h3><span className='font-bold'>Department ID:</span> {(user && user.departmentId) ?? '-'}</h3>

          <h3><span className='font-bold'>Department Name:</span> {(user && user.departmentName) ?? '-'}</h3>
        </div>
      </div>
    </div>
  )
}

export default EmployeeDetails