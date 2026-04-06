import { create } from 'zustand';
import { axiosInstance } from '../lib/axios';
import toast from 'react-hot-toast';

export const useBudgetStore = create((set, get) => ({
    budget: {},
    error: null,
    isLoading: false,

    createBudget: async (formData) => {
        set({isLoading: true})

        try {
            const response = await axiosInstance.post(`/budgets`, formData)
            set({budget: response.data, error: null})
            toast.success('Budget created successfully!')
        } catch (error) {
            set({budget: null, error: error.response.data.message})
            console.log('ERROR', error.response.data)
        }

        set({isLoading: false})
    }
}))