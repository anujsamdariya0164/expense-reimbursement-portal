import { useNavigate } from 'react-router-dom'
import { useClaimStore } from '../../store/useClaimStore'
import { useAuthStore } from '../../store/useAuthStore'
import { useEffect } from 'react'

const Home = () => {
  const navigate = useNavigate()

  const {authUser} = useAuthStore()

  const {claims, getClaimsByEmployee} = useClaimStore()

  useEffect(() => {
    console.log(authUser)
    getClaimsByEmployee(authUser.id)
  }, [authUser])

  useEffect(() => {
    console.log(claims)
  }, [claims])

  return (
    <div className='flex flex-col m-6'>
      <div className='mb-5 flex justify-between'>
        <div>
          <h1 className='font-bold text-3xl'>Claims submitted:</h1>
        </div>
        <div className='text-right'>
          <button 
            className='font-bold text-2xl cursor-pointer border-white border-2 px-10 rounded hover:bg-white hover:text-[#303030] flex items-end h-auto' 
            onClick={() => navigate('/claim/add')}
          >
            <h1>Add Claim +</h1>
          </button>
        </div>
      </div>

      <div className='w-full p-10'>
        {
          claims && 
          (
            <table className='w-full border-2 p-10'>
              <thead>
                <tr className='grid grid-cols-6 p-10'>
                  <th>ID</th>
                  <th>Amount</th>
                  <th>Category</th>
                  <th>Comment</th>
                  <th>Proof URL</th>
                  <th>Status</th>
                </tr>
              </thead>

              <tbody>
                {
                  claims && claims.length > 0 && 
                  claims.map((claim, index) => (
                    <tr key={index} className='grid grid-cols-6 p-10 text-center border-2'>
                      <td>{claim.id}</td>
                      <td>${claim.amount}</td>
                      <td>{claim.category}</td>
                      <td>{claim.comment ?? '-'}</td>
                      <td>{claim.proofUrl ?? '-'}</td>
                      <td><span className='p-2 rounded bg-gray-700'>{claim.status}</span></td>
                    </tr>
                  ))
                }
              </tbody>
            </table>
          )
        }

        {
          (!claims || claims.length === 0)&& (
            <div className='border-2 h-[10vh] flex items-center justify-center'>
              <h1>No claims made</h1>
            </div>
          )
        }
      </div>
    </div>
  )
}

export default Home