import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { useDepartmentStore } from '../../store/useDepartmentStore'
import { useAuditLogsStore } from '../../store/useAuditLogStore'

const AdminDashboard = () => {
    const navigate = useNavigate()

    const {departments, getAllDepartments} = useDepartmentStore()

    const {auditLogs, getAllLogs, totalPages} = useAuditLogsStore()

    const [page, setPage] = React.useState(0)
    const size = 10

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

    useEffect(() => {
        getAllLogs({ page, size })
    }, [page])
  
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
                                    auditLogs.map((auditLog, index) => (
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
                        <div className='flex justify-center gap-4 mt-4'>
                            <button
                                className='border px-3 py-1'
                                disabled={page === 0}
                                onClick={() => setPage(prev => prev - 1)}
                            >
                                Prev
                            </button>

                            <span>Page {page + 1}</span>

                            <button
                                className='border px-3 py-1'
                                disabled={page + 1 >= totalPages}
                                onClick={() => setPage(prev => prev + 1)}
                            >
                                Next
                            </button>
                        </div>
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