import { create } from 'zustand';
import { axiosInstance } from '../lib/axios';
import toast from 'react-hot-toast';

export const useClaimStore = create((set, get) => ({
    claim: {},
    claims: [],
    claimsAmountByEmployee: [],
    error: null,
    isLoading: false,

    getClaimsAmountByEmployee: (claims) => {
        let claimsAmountByEmployee = {}
        if (claims) {
            claims.map(claim => {
                if (claim.status === 'PAID') {
                    if (!Object.keys(claimsAmountByEmployee).includes(claim.employeeName)) {
                        claimsAmountByEmployee[claim.employeeName] = claim.amount
                    } else {
                        claimsAmountByEmployee[claim.employeeName] += claim.amount
                    }
                }
            })
        }
        set({claimsAmountByEmployee: claimsAmountByEmployee})
    },

    getClaimsByDepartment: async (id) => {
        set({isLoading: true})

        try {
            const response = await axiosInstance.get(`/claims/department/${id}`)
            set({claims: response.data, error: null})
        } catch (error) {
            set({claims: [], error: error.response.data.message})
            console.log(error.response.data)
            toast.error(error.response.data.message)
        }

        set({isLoading: false})
    },

    getClaimsByEmployee: async (id) => {
        set({isLoading: true})

        try {
            const response = await axiosInstance.get(`/claims/employee/${id}`)
            set({claims: response.data, error: null})
        } catch (error) {
            set({claims: [], error: error.response.data.message})
            console.log('ERROR', error.response.data)
            toast.error(error.response.data.message)
        }

        set({isLoading: false})
    },

    getClaimById: async (id) => {
        set({isLoading: true})

        try {
            const response = await axiosInstance.get(`/claims/${id}`)
            set({claim: response.data, error: null})
        } catch (error) {
            set({claim: {}, error: error.response.data.message})
            console.log('ERROR', error.response.data)
            toast.error(error.response.data.message)
        }

        set({isLoading: false})
    },

    createClaim: async (formData) => {
        set({isLoading: true})

        try {
            const response = await axiosInstance.post(`/claims`, formData)
            set({claim: response.data, error: null})
            if (response.data.status === 'REJECTED') {
                toast.success('Claim auto-rejected!')
            } else {
                toast.success('Claim created successfully!')
            }
            return true
        } catch (error) {
            set({claim: {}, error: error.response.data.message})
            console.log(error.response.data)
            toast.error(error.response.data.message)
            return false
        } finally {
            set({isLoading: false})
        }
    },

    updateStatus: async (id, formData) => {
        set({isLoading: true})

        try {
            const response = await axiosInstance.put(`/claims/${id}`, formData)
            set({claim: response.data, error: null})
            toast.success('Status updated successfully!')
        } catch (error) {
            set({claim: {}, error: error.response.data.message})
            toast.error(error.response.data.message)
            console.log(error.response.data)
        } finally {
            set({isLoading: false})
        }
    },
}))