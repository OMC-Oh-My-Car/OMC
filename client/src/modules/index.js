/*
  Axios Instance import 해서 사용하기
*/
import axios from 'axios';
console.log(process.env.REACT_APP_API_URL);
const axiosInstance = axios.create({
  headers: {
    'Access-Control-Allow-Origin': 'https://5a26-49-142-61-236.jp.ngrok.io',
    // 'Access-Control-Allow-Headers': 'Content-Type, Authorization',
    'Access-Control-Allow-Headers': '*',
    // Authorization: localStorage.getItem('Authorization'),
    Authorization: JSON.parse(localStorage.getItem('Authorization')),
    // 'Access-Control-Expose-Headers': '*',
    'Content-Type': 'application/json',
    // 등등의 header
  },
  baseURL: process.env.REACT_APP_API_URL, // baseUrl
});
axiosInstance.defaults['withCredentials'] = true;

export default axiosInstance;
