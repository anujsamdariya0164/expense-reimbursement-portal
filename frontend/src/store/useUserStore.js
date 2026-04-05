import { create } from 'zustand';
import { axiosInstance } from '../lib/axios';
import toast from 'react-hot-toast';

export const useUserStore = create((set, get) => ({
    user: {},
    users: [],
    error: null,
    isLoading: false,

    getUserById: async (id) => {
        set({isLoading: true})

        try {
            const response = await axiosInstance.get(`/users/${id}`)
            set({user: response.data, error: null})
        } catch (error) {
            set({user: null, error: error.response.data.message})
            console.log(error.response.data)
        }

        set({isLoading: false})
    },

    createUser: async (formData) => {
        set({isLoading: true})

        try {
            const response = await axiosInstance.post(`/users`, formData)
            set({user: response.data, error: null})
            toast.success("User created successfully!")
        } catch (error) {
            set({user: null, error: error.response.data.message})
            console.log(error.response.data)
        }

        set({isLoading: false})
    },
}))