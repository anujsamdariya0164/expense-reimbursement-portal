import { create } from 'zustand';
import { axiosInstance } from '../lib/axios';
import toast from 'react-hot-toast';

export const useAuditLogsStore = create((set, get) => ({
    auditLogs: [],
    error: null,
    isLoading: false,

    getAllLogs: async () => {
        set({isLoading: true})

        try {
            const response = await axiosInstance.get(`/auditlogs`)
            set({auditLogs: response.data, error: null})
        } catch (error) {
            set({auditLogs: [], error: error.response.data})
            console.log(error.response.data)
            toast.error(error.response.data.message)
        }

        set({isLoading: false})
    },
}))