import { Navigate, Outlet } from "react-router-dom"

const ProtectedRoute = ({isAllowed, redirectTo, isLoading, children}) => {
    if (isLoading) return <>Loading...</>

    if (!isAllowed) return <Navigate to={redirectTo} replace />

    return children ? children : <Outlet />
}

export default ProtectedRoute