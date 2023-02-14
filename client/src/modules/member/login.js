import axiosInstance from '..';

export const login = (data) => {
  console.log('로그인');
  return axiosInstance.post('/member/login', data);
};
