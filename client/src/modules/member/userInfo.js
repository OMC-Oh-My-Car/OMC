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

export const editUserInfo = (file, data) => {
  console.log('회원정보 수정');
  let frm = new FormData();
  const userInfo = JSON.stringify({
    email: data.email,
    nickname: data.nickname,
    address: data.address,
    username: data.username,
    phone: data.phone,
  });
  const userInfoBlob = new Blob([userInfo], { type: 'application/json' });
  frm.append('userInfo', userInfoBlob);

  for (let i = 0; i < file.length; i++) {
    frm.append('imgUrl', file[i]);
  }
  console.log(file);
  console.log(data);
  // return axiosInstance.patch('/member/modify', {
  //   headers: {
  //     Authorization: window.sessionStorage.getItem('Authorization'),
  //   },
  // });
};
