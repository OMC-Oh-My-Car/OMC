/*
  Axios Instance import 해서 사용하기
*/
import axios from 'axios';
console.log(process.env.REACT_APP_API_URL);
const axiosInstance = axios.create({
  headers: {
    'Access-Control-Allow-Origin': 'https://c683-220-123-236-194.jp.ngrok.io',
    'ngrok-skip-browser-warning': '69420',
    'Access-Control-Allow-Headers': '*',
    'Content-Type': 'application/json',
    // 등등의 header
  },
  baseURL: process.env.REACT_APP_API_URL, // baseUrl
});
axiosInstance.defaults['withCredentials'] = true;

export default axiosInstance;
