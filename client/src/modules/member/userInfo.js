import axiosInstance from '..';

// axiosInstance.defaults.headers.common['Authorization'] = window.sessionStorage.getItem('Authorization');
export const getUserInfo = () => {
  console.log('회원정보 조회');
  console.log(window.sessionStorage.getItem('Authorization'));
  return axiosInstance.get('/member/detail', {
    headers: {
      Authorization: window.sessionStorage.getItem('Authorization'),
    },
  });
};

export const editUserInfo = (data) => {
  console.log('회원정보 수정');
  console.log(window.sessionStorage.getItem('Authorization'));
  return axiosInstance.patch('/member/modify', {
    headers: {
      Authorization: window.sessionStorage.getItem('Authorization'),
    },
  });
};
