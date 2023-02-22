import axiosInstance from '..';

// axiosInstance.defaults.headers.common['Authorization'] = window.sessionStorage.getItem('Authorization');
axiosInstance.defaults.headers.common['Content-Type'] = 'multipart/form-data';

export const createNewProduct = (content) => {
  let frm = new FormData();
  const product = JSON.stringify({
    subject: content.subject,
    description: content.description,
    address: content.address,
    zipcode: content.zipcode,
    facilities: content.facilities,
    telephone: content.telephone,
    price: content.price,
    checkIn: content.checkIn,
    checkOut: content.checkOut,
  });

  const productBlob = new Blob([product], { type: 'application/json' });
  frm.append('product', productBlob);

  for (let i = 0; i < content.image.length; i++) {
    frm.append('imgUrl', content.image[i]);
  }

  console.log(content.image[0].name);
  console.log('판매자 상품 등록');
  return axiosInstance.post('/product', {
    frm,
    headers: {
      Authorization: window.sessionStorage.getItem('Authorization'),
      'Content-Type': 'multipart/form-data',
    },
  });
};
