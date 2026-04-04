import { Outlet } from 'react-router-dom'
import Navbar from './Navbar/Navbar';
import Footer from './Footer/Footer';

const Layout = () => {
  return (
    <div className='flex flex-col h-svh'>
        <Navbar />
        <div className='flex-1'>
        <Outlet />
        </div>
        <Footer />
    </div>
  )
}

export default Layout;
{/* overflow-y-auto */}