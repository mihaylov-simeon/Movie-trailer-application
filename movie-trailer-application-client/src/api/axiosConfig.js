import axios from 'axios';

const axiosConfig = axios.create({
  baseURL: 'http://localhost:8080',
  withCredentials: true,
});

// Request interceptor
axiosConfig.interceptors.request.use(
  (config) => {
    // Exclude the password from the logs
    if (config.data && config.data.password) {
      const sanitizedConfig = { ...config };
      sanitizedConfig.data = {
        ...sanitizedConfig.data,
        password: '****** (hidden)',
      };
      console.log('Request Interceptor:', sanitizedConfig);
    } else {
      console.log('Request Interceptor:', config);
    }
    return config;
  },
  (error) => {
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
