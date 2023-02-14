import axiosInstance from '..';

export const getSellerProductList = (page) => {
  let params = {
    page,
  };
  console.log(params);
  console.log('판매자 상품 리스트 Loading');
  return axiosInstance.get('/product/my');
};
