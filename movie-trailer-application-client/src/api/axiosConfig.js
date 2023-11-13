import axios from 'axios';

const axiosConfig = axios.create({
  baseURL: 'http://localhost:8080',
  withCredentials: true,
  headers: {"skip-browser-warning": "true"}
});

// Request interceptor
axiosConfig.interceptors.request.use(
  (config) => {
    // log request data into the console
    console.log('Request Interceptor:', config);
    return config;
  },
  (error) => {
    // Handle request errors
    console.error('Request Interceptor Error:', error);
    return Promise.reject(error);
  }
);

// Response interceptor
axiosConfig.interceptors.response.use(
  (response) => {
    // log response data into the console
    console.log('Response Interceptor:', response);
    return response;
  },
  (error) => {
    // Handle response errors
    console.error('Response Interceptor Error:', error);
    return Promise.reject(error);
  }
);

export default axiosConfig;
