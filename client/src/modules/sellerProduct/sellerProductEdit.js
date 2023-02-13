import axiosInstance from '..';

export const editProduct = (item, productId) => {
  let params = {
    item,
    productId,
  };
  console.log(params);
  console.log('판매자 상품 수정');
  return axiosInstance.patch(`/product/${productId}`);
};

export const getProductInfo = (productId) => {
  let params = {
    productId,
  };
  console.log(params);
  console.log('판매자 상품 등록 정보 가져오기');
  return axiosInstance.get(`/product/${productId}`);
};
