import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuthStore } from '../../store/useAuthStore'

const Login = () => {
  const navigate = useNavigate()

  const {login, isLoading} = useAuthStore()

  const [formData, setFormData] = useState({
    email: '',
    password: ''
  })

  const handleChange = (key, value) => {
    setFormData((prevFormData) => ({
      ...prevFormData, [key]: value
    }))
  }

  const handleSubmit = async (e) => {
    e.preventDefault()

    await login(formData)

    navigate('/')
  }

  return (
    <div className='flex items-center justify-center h-[70vh]'>
      <div className='px-[10rem] py-[2rem] border-white border-2 rounded flex flex-col justify-center items-center gap-2'>
        <h1 className='font-bold text-2xl'>Login</h1>

        <div className='px-[10rem] py-[2rem] border border-white rounded'>
          <form onSubmit={handleSubmit} className='flex flex-col gap-[1rem] items-center'>
            <div className='flex gap-2 justify-between'>
                <label htmlFor="" className='font-bold'>Email:</label>
                <input type="email" name='email' className='border border-white text-white' value={formData.email} onChange={(e) => handleChange(e.target.name, e.target.value)} required />
            </div>

            <div className='flex gap-2 justify-between'>
                <label htmlFor="" className='font-bold'>Password:</label>
                <input type="password" name='password' className='border border-white text-white' value={formData.password} onChange={(e) => handleChange(e.target.name, e.target.value)} required />
            </div>

            <div className='text-center'>
              <button disabled={isLoading} className='font-bold text-xl cursor-pointer border-white border-2 px-10 rounded hover:bg-white hover:text-[#303030] flex items-end h-auto' type='submit'>
                <h1>
                  {isLoading? 'Loading...': 'Submit'}
                </h1>
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  )
}

export default Login