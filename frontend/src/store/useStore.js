import { create } from 'zustand';
import { axiosInstance } from '../lib/axios';

export const useStore = create((set, get) => ({
    authUser: null,
    error: null,
    setIsCheckingAuth: false,

    checkAuth: async () => {
        set({isCheckingAuth: true})

        try {
            const response = await axiosInstance.get('/auth/current')
            set({authUser: response.data, error: null})
        } catch (error) {
            set({authUser: null, error: error.response.data.message})
            console.log(error.response.data.message)
        }

        set({isCheckingAuth: false})
    },
}))