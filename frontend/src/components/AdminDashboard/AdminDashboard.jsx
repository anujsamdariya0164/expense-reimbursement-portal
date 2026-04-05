import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { useDepartmentStore } from '../../store/useDepartmentStore'

const AdminDashboard = () => {
    const navigate = useNavigate()

    const {departments, getAllDepartments} = useDepartmentStore()

  const auditLogs = [
    {
        "action": "SUBMITTED",
        "actorEmail": "vihaan.joshi@gmail.com",
        "actorId": 8,
        "actorName": "Vihaan Joshi",
        "claimId": 3,
        "id": 1,
        "role": "EMPLOYEE",
        "timestamp": "2026-04-04T18:39:28.016702"
    },
    {
        "action": "REJECTED",
        "actorEmail": "vihaan.joshi@gmail.com",
        "actorId": 8,
        "actorName": "Vihaan Joshi",
        "claimId": 4,
        "id": 2,
        "role": "EMPLOYEE",
        "timestamp": "2026-04-04T18:39:43.873479"
    },
    {
        "action": "SUBMITTED",
        "actorEmail": "vihaan.joshi@gmail.com",
        "actorId": 8,
        "actorName": "Vihaan Joshi",
        "claimId": 5,
        "id": 3,
        "role": "EMPLOYEE",
        "timestamp": "2026-04-04T18:39:54.579735"
    },
    {
        "action": "REJECTED",
        "actorEmail": "vihaan.joshi@gmail.com",
        "actorId": 8,
        "actorName": "Vihaan Joshi",
        "claimId": 6,
        "id": 4,
        "role": "EMPLOYEE",
        "timestamp": "2026-04-04T18:39:59.767149"
    },
    {
        "action": "SUBMITTED",
        "actorEmail": "vihaan.joshi@gmail.com",
        "actorId": 8,
        "actorName": "Vihaan Joshi",
        "claimId": 7,
        "id": 5,
        "role": "EMPLOYEE",
        "timestamp": "2026-04-04T18:40:15.530778"
    },
    {
        "action": "SUBMITTED",
        "actorEmail": "vihaan.joshi@gmail.com",
        "actorId": 8,
        "actorName": "Vihaan Joshi",
        "claimId": 8,
        "id": 6,
        "role": "EMPLOYEE",
        "timestamp": "2026-04-04T18:40:21.487993"
    },
    {
        "action": "APPROVED",
        "actorEmail": "vihaan.joshi@gmail.com",
        "actorId": 8,
        "actorName": "Vihaan Joshi",
        "claimId": 9,
        "id": 7,
        "role": "EMPLOYEE",
        "timestamp": "2026-04-04T18:40:29.813788"
    },
    {
        "action": "SUBMITTED",
        "actorEmail": "vihaan.joshi@gmail.com",
        "actorId": 8,
        "actorName": "Vihaan Joshi",
        "claimId": 10,
        "id": 8,
        "role": "EMPLOYEE",
        "timestamp": "2026-04-04T18:40:33.781635"
    },
    {
        "action": "SUBMITTED",
        "actorEmail": "vihaan.joshi@gmail.com",
        "actorId": 8,
        "actorName": "Vihaan Joshi",
        "claimId": 11,
        "id": 9,
        "role": "EMPLOYEE",
        "timestamp": "2026-04-04T18:40:39.352459"
    },
    {
        "action": "SUBMITTED",
        "actorEmail": "ishwar.verma@gmail.com",
        "actorId": 9,
        "actorName": "Ishwar Verma",
        "claimId": 12,
        "id": 10,
        "role": "EMPLOYEE",
        "timestamp": "2026-04-04T19:04:47.198307"
    },
    {
        "action": "SUBMITTED",
        "actorEmail": "ishaan.reddy@gmail.com",
        "actorId": 11,
        "actorName": "Ishaan Reddy",
        "claimId": 13,
        "id": 11,
        "role": "EMPLOYEE",
        "timestamp": "2026-04-04T19:05:45.088329"
    },
    {
        "action": "SUBMITTED",
        "actorEmail": "ishaan.reddy@gmail.com",
        "actorId": 11,
        "actorName": "Ishaan Reddy",
        "claimId": 14,
        "id": 12,
        "role": "EMPLOYEE",
        "timestamp": "2026-04-04T19:08:32.099133"
    }
]

    const convert = (timestamp) => {
        const year = timestamp.slice(0, 4)
        const month = timestamp.slice(5, 7)
        const day = timestamp.slice(8, 10)

        const hour = timestamp.slice(11, 13)
        const minute = timestamp.slice(14, 16)
        const second = timestamp.slice(17, 19)

        const date = day + '-' + month + '-' + year
        const time = hour + ':' + minute + ':' + second

        return date + ' at ' + time
    }

    useEffect(() => {
        getAllDepartments()
    }, [])
  
    return (
    <div className='flex flex-col gap-4 m-6'>
        <div className='flex flex-col gap-4'>
            <h1 className='text-3xl font-bold underline'>List of all departments</h1>
            {
                departments && 
                (
                    <div className='w-full'>
                        <table className='w-full border-2'>
                            <thead>
                                <tr className='grid grid-cols-5 p-5'>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Manager Email</th>
                                    <th>Manager Name</th>
                                    <th>Visit</th>
                                </tr>
                            </thead>

                            <tbody>
                                {
                                    departments && 
                                    departments.map((department, index) => (
                                        <tr key={index} className='grid grid-cols-5 p-5 text-center border-2'>
                                            <td>{department.id}</td>
                                            <td>{department.name}</td>
                                            <td>{department.managerEmail ?? '-'}</td>
                                            <td>{department.managerName ?? '-'}</td>
                                            <td className='underline cursor-pointer text-sm font-semibold' onClick={() => navigate(`/admin/dashboard/department/${department.id}`)}>Show More</td>
                                        </tr>
                                    ))
                                }
                            </tbody>
                        </table>
                    </div>
                )
            }

            {
                !departments && (
                    <div className='w-full border-2 p-5 text-center'>
                        <h1>No departments made</h1>
                    </div>
                )
            }
        </div>

        <div className='flex flex-col gap-4'>
            <h1 className='text-3xl font-bold underline'>Audit Logs</h1>

            {
                auditLogs && 
                (
                    <div className='w-full'>
                        <table className='w-full border-2'>
                            <thead>
                                <tr className='grid grid-cols-7 p-5'>
                                    <th>ID</th>
                                    <th>Claim ID</th>
                                    <th>Action</th>
                                    <th>Actor Email</th>
                                    <th>Actor Name</th>
                                    <th>Actor Role</th>
                                    <th>Timestamp</th>
                                </tr>
                            </thead>

                            <tbody>
                                {
                                    auditLogs && 
                                    auditLogs.reverse().map((auditLog, index) => (
                                        <tr key={index} className='grid grid-cols-7 p-5 text-center border-2'>
                                            <td>{auditLog.id}</td>
                                            <td>{auditLog.claimId}</td>
                                            <td>{auditLog.action}</td>
                                            <td>{auditLog.actorEmail ?? '-'}</td>
                                            <td>{auditLog.actorName ?? '-'}</td>
                                            <td>{auditLog.role ?? '-'}</td>
                                            <td>{convert(auditLog.timestamp) ?? '-'}</td>
                                        </tr>
                                    ))
                                }
                            </tbody>
                        </table>
                    </div>
                )
            }

            {
                !auditLogs && (
                    <div className='w-full border-2 p-5 text-center'>
                        <h1>No audit logs made</h1>
                    </div>
                )
            }
        </div>
    </div>
  )
}

export default AdminDashboard