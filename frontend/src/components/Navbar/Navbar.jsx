import { useNavigate } from "react-router-dom"

const Navbar = () => {
    const navigate = useNavigate()

    const authUser = {
        id: 1,
        name: "Anuj Samdariya"
    }

    const handleLogout = async (e) => {
        e.preventDefault()

        try {
            await logOut()
        } catch (error) {
            console.log("ERROR:", error)
        }
    }

  return (
    <div className='border-b-2 border-b-white h-auto px-3 py-5 flex justify-between'>
        <div>
            <h1 
            onClick={() => navigate('/')} 
            className='text-3xl font-bold cursor-pointer'
            >Expense Reimbursement Portal</h1>
        </div>

        <div className='text-xl font-semibold flex gap-5 items-center'>
            {authUser && (
                <h2>Hi, <span className='text-green-400'>{authUser.name.split(' ')[0] || 'User'}</span></h2>
            )}
            
            
            {authUser && (
                <>
                    {
                        authUser.role === 'ADMIN' && (
                            <button onClick={() => navigate('/dashboard')} className='cursor-pointer bg-gray-800 hover:bg-gray-700 text-white px-2 py-1 rounded'>Dashboard</button>
                        )
                    }

                    <button className='cursor-pointer bg-red-600 hover:bg-red-400 text-white px-2 py-1 rounded' onClick={handleLogout}>Logout</button>
                </>
            )}

            {!authUser && (
                <>                    
                    <button onClick={() => navigate('/login')} className='cursor-pointer bg-white hover:bg-gray-300 text-black px-2 py-1 rounded'>Sign In</button>
                </>
            )}
        </div>
    </div>
  )
}

export default Navbar