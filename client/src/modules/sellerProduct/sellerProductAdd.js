import axiosInstance from '..';

export const createNewProduct = (content) => {
  let params = {
    content,
  };
  console.log(params);
  console.log('판매자 상품 등록');
  return axiosInstance.post('/product');
};
