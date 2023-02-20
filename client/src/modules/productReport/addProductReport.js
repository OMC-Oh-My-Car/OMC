import axiosInstance from '..';

// axiosInstance.defaults.headers.common['Authorization'] = window.sessionStorage.getItem('Authorization');
// axiosInstance.defaults.headers.common['Content-Type'] = 'application/json';
// axiosInstance.defaults.headers.common['Content-Type'] = 'multipart/form-data';

export const addProductReport = (content, productId) => {
  let frm = new FormData();

  const contentBlob = new Blob([JSON.stringify(content)], { type: 'application/json' });

  frm.append('report', contentBlob);
  console.log('상품 신고');
  // return axiosInstance.post(`/report/${productId}`, frm);
  return axiosInstance.post(`/report/${productId}`, {
    frm,
    headers: {
      Authorization: window.sessionStorage.getItem('Authorization'),
      'Content-Type': 'multipart/form-data',
    },
  });
};
