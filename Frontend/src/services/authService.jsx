import axiosInstance from "./axiosInstance";

// Login API
export const login = async (email, password) => {
    try {
        const response = await axiosInstance.post("/auth/login", { email, password });
        return response.data;
    } catch (error) {
        throw error.response?.data || "Login failed";
    }
};

// Signup API
export const signup = async (name, email, password) => {
    try {
        const response = await axiosInstance.post("/auth/signup", { name, email, password });
        return response.data;
    } catch (error) {
        throw error.response?.data || "Signup failed";
    }
};
