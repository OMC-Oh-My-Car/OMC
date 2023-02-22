import axiosInstance from '..';

axiosInstance.defaults.headers.common['Content-Type'] = 'multipart/form-data';

export const editProduct = (data, productId) => {
  let frm = new FormData();
  const product = JSON.stringify({
    subject: data.subject,
    description: data.description,
    address: data.address,
    zipcode: data.zipcode,
    facilities: data.facilities,
    telephone: data.telephone,
    price: data.price,
    checkIn: data.checkIn,
    checkOut: data.checkOut,
  });
  const productBlob = new Blob([product]);
  frm.append('product', productBlob);

  for (let i = 0; i < data.image.length; i++) {
    frm.append('imgUrl', data.image[i]);
  }

  console.log('판매자 상품 수정');
  return axiosInstance.patch(`/product/${productId}`, {
    frm,
    headers: {
      'Content-Type': 'multipart/form-data',
      Authorization: window.sessionStorage.getItem('Authorization'),
    },
  });
};

export const getProductInfo = (productId) => {
  let params = {
    productId,
  };
  console.log(params);
  console.log('판매자 상품 등록 정보 가져오기');
  return axiosInstance.get(`/product/${productId}`);
};
