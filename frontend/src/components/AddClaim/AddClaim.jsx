import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'

const AddClaim = () => {
    const categories = ['TRAVEL', 'TRANSPORTATION', 'MEALS', 'ENTERTAINMENT', 'SUPPLIES', 'INTERNET', 'TRAINING']

    const approvalModes = ['AUTO', 'MANAGER', 'MANAGER_AND_ADMIN']

    const navigate = useNavigate()

    const [formData, setFormData] = useState({
        amount: 0,
        comment: '',
        proofUrl: '',
        category: '',
        approvalMode: '',
        // employeeId to come from current session
    })

    const handleChange = (key, value) => {
        setFormData((prevFormData) => ({
            ...prevFormData, [key]: value
        }))
    }

    const handleSubmit = async (event) => {
        event.preventDefault()
        console.log(formData)
    }

  return (
    <div className='flex items-center justify-center h-[70vh]'>
        <div className='px-[10rem] py-[2rem] border-white border-2 rounded flex flex-col justify-center items-center gap-2'>
            <h1 className='font-bold text-2xl'>Add Claim</h1>

            <div className='px-[10rem] py-[2rem] border border-white rounded'>
                <form onSubmit={handleSubmit} className='flex flex-col gap-[1rem] items-center'>
                    <div className='flex gap-2 justify-between'>
                        <label htmlFor="">Amount:</label>
                        <input type="text" name='amount' className='border border-white text-white' value={formData.amount} onChange={(e) => handleChange(e.target.name, e.target.value)} />
                    </div>

                    <div className='flex gap-2 justify-between'>
                        <label htmlFor="">Proof URL:</label>
                        <input type="text" name='proofUrl' className='border border-white text-white' value={formData.proofUrl} onChange={(e) => handleChange(e.target.name, e.target.value)} />
                    </div>

                    <div className='flex gap-2 justify-between'>
                        <label htmlFor="">Comment:</label>
                        <textarea type="text" name='comment' className='border border-white text-white' value={formData.comment} onChange={(e) => handleChange(e.target.name, e.target.value)} />
                    </div>

                    <div className='flex gap-2 justify-between'>
                        <h3 className='font-bold'>Select Approval Mode:</h3>

                        <select
                            name='approvalMode'
                            value={formData.approvalMode}
                            onChange={(e) => handleChange(e.target.name, e.target.value)}
                            className="bg-[#303030] text-white border-white border rounded-sm"
                        >
                            <option value="">Select Approval Mode</option>
                            {approvalModes?.map((approvalMode, index) => (
                                <option key={index} value={approvalMode}>
                                    {approvalMode}
                                </option>
                            ))}
                        </select>
                    </div>

                    <div className='flex gap-2 justify-between'>
                        <h3 className='font-bold'>Select Category:</h3>

                        <select
                            name='category'
                            value={formData.category}
                            onChange={(e) => handleChange(e.target.name, e.target.value)}
                            className="bg-[#303030] text-white border-white border rounded-sm"
                        >
                            <option value="">Select Category</option>
                            {categories?.map((category, index) => (
                                <option key={index} value={category}>
                                    {category[0] + category.slice(1).toLowerCase()}
                                </option>
                            ))}
                        </select>
                    </div>

                    <div className='text-center'>
                        <button className='font-bold text-xl cursor-pointer border-white border-2 px-10 rounded hover:bg-white hover:text-[#303030] flex items-end h-auto' type='submit'>
                            <h1>Submit</h1>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
  )
}

export default AddClaim