import { Toaster } from "react-hot-toast"
import { createBrowserRouter, createRoutesFromElements, Navigate, Outlet, Route, RouterProvider } from "react-router-dom"
import { AddBudget, AddClaim, AddEmployee, AdminDashboard, AssignManager, DepartmentDetails, EmployeeDetails, Home, Layout, Login, ManagerDashboard, PageNotFound, SignUp, UpdateClaimStatus } from "./components"
import { useEffect } from "react"
import { useAuthStore } from "./store/useAuthStore"

function App() {
  const {authUser, isCheckingAuth, checkAuth} = useAuthStore()

  useEffect(() => {
    checkAuth()
  }, [checkAuth])

  useEffect(() => {
    console.log(authUser)
  }, [authUser])

  if (isCheckingAuth && !authUser) {
    return (
      <div className="h-screen flex justify-center items-center">
        <h1>Loading ...</h1>
      </div>
    )
  }

  const router = createBrowserRouter(
    createRoutesFromElements(
      <Route path="/" element={<Layout/>}>
        <Route
          path=""
          element={
            authUser ? (
              authUser.role === "EMPLOYEE" ? (
                <Home />
              ) : authUser.role === "MANAGER" ? (
                <Navigate to="/dashboard" />
              ) : (
                <Navigate to="/admin/dashboard" />
              )
            ) : (
              <Navigate to="/login" />
            )
          }
        />
        <Route path="/login" element={!authUser ? <Login/> : <Navigate to='/'/>}/>
        <Route path="/signup" element={!authUser ? <SignUp/> : <Navigate to='/'/>} />
        <Route path="claim">
          <Route
            path="add"
            element={
              authUser ? (
                authUser.role === "EMPLOYEE" ? (
                  <AddClaim />
                ) : (
                  <Navigate to="/" />
                )
              ) : (
                <Navigate to="/login" />
              )
            }
          />

          <Route
            path=":id"
            element={
              authUser ? (
                authUser.role === "ADMIN" || authUser.role === "MANAGER" ? (
                  <UpdateClaimStatus />
                ) : (
                  <Navigate to="/" />
                )
              ) : (
                <Navigate to="/login" />
              )
            }
          />
        </Route>
        <Route
          path="dashboard"
          element={
            authUser ? (
              authUser.role === "MANAGER" ? (
                <ManagerDashboard />
              ) : (
                <Navigate to="/" />
              )
            ) : (
              <Navigate to="/login" />
            )
          }
        />
        <Route
          path="admin"
          element={
            authUser ? (
              authUser.role === "ADMIN" ? (
                <Outlet />
              ) : (
                <Navigate to="/" />
              )
            ) : (
              <Navigate to="/login" />
            )
          }
        >
          <Route path="dashboard">
            <Route path="" element={<AdminDashboard />} />
            <Route path="department/:id" element={<DepartmentDetails />} />
            
            <Route path="employee">
              <Route path="add/:departmentId" element={<AddEmployee />} />
              <Route path=":id" element={<EmployeeDetails />} />
            </Route>

            <Route path="budget/add/:id" element={<AddBudget />} />
            <Route path="manager/assign/:departmentId" element={<AssignManager />} />
          </Route>
        </Route>
        <Route path="*" element={authUser ? <PageNotFound/> : <Navigate to='/login'/>} />
      </Route>

      // <Route path="/" element={<Layout/>}>
      //   <Route path="" element={authUser ? <Home/> : <Navigate to='/login'/>} />
      //   <Route path="/login" element={!authUser ? <Login/> : <Navigate to='/'/>}/>
      //   <Route path="/signup" element={!authUser ? <SignUp/> : <Navigate to='/'/>} />
      //   <Route path="claim">
      //     <Route path="add" element={<AddClaim/>} />
      //     <Route path=":id" element={<UpdateClaimStatus/>} />
      //   </Route>
      //   <Route path="dashboard" element={<ManagerDashboard/>} />
      //   <Route path="admin">
      //     <Route path="dashboard">
      //       <Route path="" element={<AdminDashboard />} />
      //       <Route path="department/:id" element={<DepartmentDetails />} />
      //         <Route path="employee">
      //         <Route path="add/:departmentId" element={<AddEmployee />} />
      //         <Route path=":id" element={<EmployeeDetails />} />
      //       </Route>
      //       <Route path="budget/add/:id" element={<AddBudget/>} />
      //       <Route path="manager/assign/:departmentId" element={<AssignManager/>} />
      //     </Route>
      //   </Route>
      //   <Route path="*" element={authUser ? <PageNotFound/> : <Navigate to='/login'/>} />
      // </Route>
    )
  )

  return (
    <>
      <Toaster position="bottom-right" />
      <RouterProvider router={router} />
    </>
  )
}

export default App
