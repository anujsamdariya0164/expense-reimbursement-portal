import { Toaster } from "react-hot-toast"
import { createBrowserRouter, createRoutesFromElements, Navigate, Route, RouterProvider } from "react-router-dom"
import { Home, Layout, Login, PageNotFound, SignUp } from "./components"

function App() {
  const authUser = {
    id: 1
  }

  const router = createBrowserRouter(
    createRoutesFromElements(
      <Route path="/" element={<Layout/>}>
        <Route path="" element={<Home/>} />
        <Route path="/login" element={<Login/>} />
        <Route path="/signup" element={<SignUp/>} />
        <Route path="*" element={<PageNotFound/>} />
      </Route>
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
