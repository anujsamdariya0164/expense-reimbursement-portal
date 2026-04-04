import { create } from 'zustand';

export const useStore = create((set, get) => ({
    authUser: null,
    error: null,
}))