import React, { useState } from 'react'

const UpdateClaimStatus = () => {
  const claim = {
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
    "status": "SUBMITTED"
  }

  const statuses = ['REJECTED', 'SUBMITTED', 'APPROVED', 'PAID']

  const [status, setStatus] = useState(claim.status)

  const handleUpdate = (e) => {
    e.preventDefault()
    console.log(claim)
  }

  return (
    <div className='flex items-center justify-center h-[70vh]'>
      <div className='px-[20rem] py-[2rem] border-white border-2 rounded flex flex-col items-center gap-2'>

        <h1 className='font-bold text-2xl'>
          Claim ID: {claim.id}
        </h1>

        <form onSubmit={handleUpdate} className='flex flex-col gap-3 items-center'>

          <h3>
            <span className='font-bold'>Amount:</span>{" "}
            {claim?.amount}
          </h3>

          <h3>
            <span className='font-bold'>Approval Mode:</span>{" "}
            {claim?.approvalMode.replace('_', ' ').toLowerCase()}
          </h3>

          <h3>
            <span className='font-bold'>Category:</span>{" "}
            {claim?.category}
          </h3>

          <h3>
            <span className='font-bold'>Comment:</span>{" "}
            {claim?.comment}
          </h3>

          <h3>
            <span className='font-bold'>Employee Email:</span>{" "}
            {claim?.employeeEmail}
          </h3>

          <h3>
            <span className='font-bold'>Employee Name:</span>{" "}
            {claim?.employeeName}
          </h3>

          <h3>
            <span className='font-bold'>Proof URL:</span>{" "}
            <a href={claim?.proofUrl} target='_blank'>{claim?.proofUrl}</a>
          </h3>

          <div className="flex gap-2 items-center">
            <h3 className="font-bold">Status:</h3>
              <select
              disabled={claim.status === 'REJECTED' || claim.status === 'PAID'}
              value={status}
              onChange={(e) => setStatus(e.target.value)}
              className="bg-[#303030] text-white border border-white rounded-sm"
              >
                <option value="">Select Status</option>
                {statuses.map(s => (
                  <option key={s} value={s}>{s}</option>
                ))}
              </select>
          </div>

          {claim.status === 'REJECTED' || claim.status === 'PAID' ? (
            <p className='text-red-600'>Paid/Rejected claims cannot be updated!</p>
          ): (<></>)}

          <button
            disabled={claim.status === 'REJECTED' || claim.status === 'PAID'}
            type='submit'
            className={`${claim.status === 'REJECTED' || claim.status === 'PAID' ? 'text-gray-300' : 'hover:bg-white hover:text-[#303030]'} font-bold text-xl border-white border-2 px-10 rounded`}
          >
            Update
          </button>

        </form>
      </div>
    </div>
  )
}

export default UpdateClaimStatus