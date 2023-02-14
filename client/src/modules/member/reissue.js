import axiosInstance from '..';

export const reissue = () => {
  console.log('토큰 재발급');
  return axiosInstance.get('/member/reissue');
};
