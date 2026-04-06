import { create } from 'zustand';
import { axiosInstance } from '../lib/axios';

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
        }

        set({isLoading: false})
    },
}))