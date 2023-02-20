import axiosInstance from '..';

// axiosInstance.defaults.headers.common['Authorization'] = window.sessionStorage.getItem('Authorization');
export const logout = () => {
  console.log('로그아웃');
  console.log(window.sessionStorage.getItem('Authorization'));
  return axiosInstance.post('/member/logout', {
    headers: {
      Authorization: window.sessionStorage.getItem('Authorization'),
    },
  });
};
