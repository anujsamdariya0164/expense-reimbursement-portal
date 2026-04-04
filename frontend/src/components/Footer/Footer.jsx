import React from 'react'
import { useNavigate } from 'react-router-dom'

const Footer = () => {
    const navigate = useNavigate()

  return (
    <div className='border-t-2 border-b-white h-auto px-3 py-5 flex flex-col gap-5'>
        <div className='flex justify-between'>
            <div>
                <h1 
                onClick={() => navigate('/')} 
                className='text-2xl font-bold cursor-pointer'
                >Expense Reimbursement Portal</h1>
            </div>

            <div className='text-lg underline font-semibold flex flex-col gap-5 items-center'>
                <h3 onClick={() => navigate('/')} className='cursor-pointer px-2 py-1 rounded'>Home</h3>
            </div>
        </div>

        <div className='flex justify-between'>
            <h3>Copyright 2026 by FarEye</h3>
            <h3>All rights reserved.</h3>
        </div>
    </div>
  )
}

export default Footer