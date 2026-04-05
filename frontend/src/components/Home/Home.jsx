import { useNavigate } from 'react-router-dom'

const Home = () => {
  const navigate = useNavigate()

  // Add claims made by the employee
  const claims = [
    {
      "amount": 10000,
      "approvalMode": "AUTO",
      "budgetId": 1,
      "category": "MEALS",
      "comment": null,
      "departmentId": 1,
      "departmentName": "Engineering",
      "employeeEmail": "vihaan.joshi@gmail.com",
      "employeeId": 8,
      "employeeName": "Vihaan Joshi",
      "id": 4,
      "proofUrl": "https://example.com/proof.jpg",
      "status": "REJECTED"
    },
    {
      "amount": 100000,
      "approvalMode": "AUTO",
      "budgetId": 1,
      "category": "MEALS",
      "comment": "This is a travel claim for a business trip.",
      "departmentId": 1,
      "departmentName": "Engineering",
      "employeeEmail": "vihaan.joshi@gmail.com",
      "employeeId": 8,
      "employeeName": "Vihaan Joshi",
      "id": 6,
      "proofUrl": "https://example.com/proof.jpg",
      "status": "REJECTED"
    },
    {
      "amount": 10000,
      "approvalMode": "MANAGER_AND_ADMIN",
      "budgetId": 1,
      "category": "TRAVEL",
      "comment": "This is a travel claim for a business trip.",
      "departmentId": 1,
      "departmentName": "Engineering",
      "employeeEmail": "vihaan.joshi@gmail.com",
      "employeeId": 8,
      "employeeName": "Vihaan Joshi",
      "id": 1,
      "proofUrl": "https://example.com/proof.jpg",
      "status": "APPROVED"
    },
    {
      "amount": 10000,
      "approvalMode": "MANAGER_AND_ADMIN",
      "budgetId": 1,
      "category": "TRAVEL",
      "comment": "This is a travel claim for a business trip.",
      "departmentId": 1,
      "departmentName": "Engineering",
      "employeeEmail": "vihaan.joshi@gmail.com",
      "employeeId": 8,
      "employeeName": "Vihaan Joshi",
      "id": 2,
      "proofUrl": "https://example.com/proof.jpg",
      "status": "APPROVED"
    },
    {
      "amount": 10000,
      "approvalMode": "MANAGER_AND_ADMIN",
      "budgetId": 1,
      "category": "TRAVEL",
      "comment": "This is a travel claim for a business trip.",
      "departmentId": 1,
      "departmentName": "Engineering",
      "employeeEmail": "vihaan.joshi@gmail.com",
      "employeeId": 8,
      "employeeName": "Vihaan Joshi",
      "id": 3,
      "proofUrl": "https://example.com/proof.jpg",
      "status": "PAID"
    },
    {
      "amount": 10000,
      "approvalMode": "MANAGER_AND_ADMIN",
      "budgetId": 1,
      "category": "TRAVEL",
      "comment": "This is a travel claim for a business trip.",
      "departmentId": 1,
      "departmentName": "Engineering",
      "employeeEmail": "vihaan.joshi@gmail.com",
      "employeeId": 8,
      "employeeName": "Vihaan Joshi",
      "id": 7,
      "proofUrl": "https://example.com/proof.jpg",
      "status": "PAID"
    },
    {
      "amount": 10000,
      "approvalMode": "MANAGER_AND_ADMIN",
      "budgetId": 1,
      "category": "MEALS",
      "comment": "This is a travel claim for a business trip.",
      "departmentId": 1,
      "departmentName": "Engineering",
      "employeeEmail": "vihaan.joshi@gmail.com",
      "employeeId": 8,
      "employeeName": "Vihaan Joshi",
      "id": 8,
      "proofUrl": "https://example.com/proof.jpg",
      "status": "PAID"
    },
    {
      "amount": 100,
      "approvalMode": "AUTO",
      "budgetId": 1,
      "category": "MEALS",
      "comment": "This is a travel claim for a business trip.",
      "departmentId": 1,
      "departmentName": "Engineering",
      "employeeEmail": "vihaan.joshi@gmail.com",
      "employeeId": 8,
      "employeeName": "Vihaan Joshi",
      "id": 9,
      "proofUrl": "https://example.com/proof.jpg",
      "status": "PAID"
    },
    {
      "amount": 10000,
      "approvalMode": "MANAGER_AND_ADMIN",
      "budgetId": 1,
      "category": "MEALS",
      "comment": "This is a travel claim for a business trip.",
      "departmentId": 1,
      "departmentName": "Engineering",
      "employeeEmail": "vihaan.joshi@gmail.com",
      "employeeId": 8,
      "employeeName": "Vihaan Joshi",
      "id": 5,
      "proofUrl": "https://example.com/proof.jpg",
      "status": "PAID"
    },
    {
      "amount": 1000,
      "approvalMode": "MANAGER",
      "budgetId": 1,
      "category": "MEALS",
      "comment": "This is a travel claim for a business trip.",
      "departmentId": 1,
      "departmentName": "Engineering",
      "employeeEmail": "vihaan.joshi@gmail.com",
      "employeeId": 8,
      "employeeName": "Vihaan Joshi",
      "id": 10,
      "proofUrl": "https://example.com/proof.jpg",
      "status": "PAID"
    },
    {
      "amount": 2000,
      "approvalMode": "MANAGER_AND_ADMIN",
      "budgetId": 1,
      "category": "MEALS",
      "comment": "This is a travel claim for a business trip.",
      "departmentId": 1,
      "departmentName": "Engineering",
      "employeeEmail": "vihaan.joshi@gmail.com",
      "employeeId": 8,
      "employeeName": "Vihaan Joshi",
      "id": 11,
      "proofUrl": "https://example.com/proof.jpg",
      "status": "PAID"
    },
  ]

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

      <div className='w-full border-2 p-10'>
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
                  claims && 
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
          !claims && (
            <h1>No claims made</h1>
          )
        }
      </div>
    </div>
  )
}

export default Home