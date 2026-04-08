import { create } from 'zustand';
import { axiosInstance } from '../lib/axios';
import toast from 'react-hot-toast';

export const useAuditLogsStore = create((set, get) => ({
    auditLogs: [],
    totalPages: 0,
    currentPage: 0,
    error: null,
    isLoading: false,

    getAllLogs: async (formData) => {
        set({isLoading: true})

        try {
            const response = await axiosInstance.get(`/auditlogs`, {
                params: {
                    page: formData.page,
                    size: formData.size
                }
            })
            set({
                auditLogs: response.data.content,
                totalPages: response.data.totalPages,
                currentPage: response.data.number
            })
        } catch (error) {
            set({auditLogs: [], error: error.response.data})
            console.log(error.response.data)
            toast.error(error.response.data.message)
        }

        set({isLoading: false})
    },
}))