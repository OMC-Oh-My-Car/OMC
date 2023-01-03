/*
  Axios Instance import 해서 사용하기
*/
import axios from 'axios';

const axiosInstance = axios.create({
  headers: {
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Headers': 'Content-Type',
    'Content-Type': 'application/json',
    withCredentials: true,

    // 등등의 header
  },
  baseURL: process.env.REACT_APP_API_URL, // baseUrl
});

export default axiosInstance;
