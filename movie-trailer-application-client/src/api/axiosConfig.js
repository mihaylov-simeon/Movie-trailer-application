import axios from 'axios';

const axiosConfig = axios.create({
  baseURL: 'http://localhost:8080',
  withCredentials: true,
});

axiosConfig.interceptors.request.use(
  (config) => {
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

axiosConfig.interceptors.response.use(
  (response) => {
    console.log('Response Interceptor:', response);
    return response;
  },
  (error) => {
    console.error('Response Interceptor Error:', error);
    return Promise.reject(error);
  }
);

export default axiosConfig;
