import { create } from 'zustand';
import { axiosInstance } from '../lib/axios';
import toast from 'react-hot-toast';

export const useAuthStore = create((set, get) => ({
    authUser: null,
    error: null,
    setIsCheckingAuth: false,
    isLoading: false,

    checkAuth: async () => {
        set({isCheckingAuth: true})

        try {
            const response = await axiosInstance.get('/auth/current')
            set({authUser: response.data, error: null})
        } catch (error) {
            set({authUser: null, error: error.response.data.message})
            console.log(error.response.data)
        }

        set({isCheckingAuth: false})
    },

    login: async (formData) => {
        set({isLoading: true})

        try {
            const response = await axiosInstance.post('/auth/login', formData)
            set({authUser: response.data, error: null})
            toast.success('Logged in successfully!')
        } catch (error) {
            set({authUser: null, error: error.response.data.message})
            toast.error(error.response.data.message)
            console.log(error)
        }

        set({isLoading: false})
    },

    logOut: async () => {
        set({isLoading: true})

        try {
            await axiosInstance.post('/auth/logout')
            set({authUser: null, error: null})

            toast.success('Logged out successfully!')
        } catch (error) {
            set({authUser: null, error: error.response.data.message})
            toast.error(error.response.data.message)
            console.log(error)
        }

        set({isLoading: false})
    }
}))