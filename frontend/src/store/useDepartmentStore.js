import { create } from 'zustand';
import { axiosInstance } from '../lib/axios';
import toast from 'react-hot-toast';

export const useDepartmentStore = create((set, get) => ({
    department: {},
    departments: [],
    error: null,
    isLoading: false,

    getAllDepartments: async (id) => {
        set({isLoading: true})

        try {
            const response = await axiosInstance.get(`/departments`)
            set({departments: response.data, error: null})
        } catch (error) {
            set({departments: [], error: error.response.data})
            console.log(error.response.data)
        }

        set({isLoading: false})
    },

    getDepartmentById: async (id) => {
        set({isLoading: true})

        try {
            const response = await axiosInstance.get(`/departments/${id}`)
            set({department: response.data, error: null})
        } catch (error) {
            set({department: null, error: error.response.data})
            console.log(error.response.data)
        }

        set({isLoading: false})
    },

    createDepartment: async (formData) => {
        set({isLoading: true})

        try {
            const response = await axiosInstance.post(`/departments`, formData)
            set({department: response.data, error: null})
            toast.success('Department created successfully!')
        } catch (error) {
            set({department: null, error: error.response.data})
            console.log(error.response.data)
        }

        set({isLoading: false})
    },

    updateDepartmentById: async (id) => {
        set({isLoading: true})

        try {
            const response = await axiosInstance.put(`/departments/${id}`, formData)
            set({department: response.data, error: null})
        } catch (error) {
            set({department: null, error: error.response.data})
            console.log(error.response.data)
        }

        set({isLoading: false})
    },

    deleteDepartmentById: async (id) => {
        set({isLoading: true})

        try {
            await axiosInstance.delete(`/departments/${id}`)
            set({error: null})
        } catch (error) {
            set({department: null, error: error.response.data})
            console.log(error.response.data)
        }

        set({isLoading: false})
    }
}))