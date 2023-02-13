import axiosInstance from '..';

export const getProductDetail = (productId) => {
  let params = {
    productId,
  };
  console.log(params);
  console.log('상품 디테일 Loading');
  return axiosInstance.get(`/product/${productId}`);
};
