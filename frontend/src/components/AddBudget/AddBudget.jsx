import React, { useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { useBudgetStore } from '../../store/useBudgetStore'

const AddBudget = () => {
  const param = useParams()

  const navigate = useNavigate()

  const {createBudget} = useBudgetStore()

  const [formData, setFormData] = useState({
      limit: 0,
      departmentId: param.id,
    })

    const handleChange = (key, value) => {
      setFormData((prevFormData) => ({
        ...prevFormData, [key]: value
      }))
    }

    const handleSubmit = async (event) => {
      event.preventDefault()

      await createBudget(formData)

      navigate(`/admin/dashboard/department/${id}`)

      console.log(formData)
    }

  return (
    <div className='flex items-center justify-center h-[70vh]'>
      <div className='px-[10rem] py-[2rem] border-white border-2 rounded flex flex-col justify-center items-center gap-2'>
        <h1 className='font-bold text-2xl'>Add Employee</h1>

        <div className='px-[10rem] py-[2rem] border border-white rounded'>
          <form onSubmit={handleSubmit} className='flex flex-col gap-[1rem] items-center'>
            <div className='flex gap-2 justify-between'>
              <label htmlFor="">Limit:</label>
              <input type="text" name='limit' className='border border-white text-white' value={formData.limit} onChange={(e) => handleChange(e.target.name, e.target.value)} />
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

export default AddBudget