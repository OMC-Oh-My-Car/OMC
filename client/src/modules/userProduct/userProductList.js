import axiosInstance from '..';

export const getProductList = (filter, page, search) => {
  let params = {
    filter,
    page,
    search,
  };
  console.log(params);
  console.log('상품 리스트 Loading');
  return axiosInstance.get('/product');
};

export const recommendProduct = (productId) => {
  console.log('상품 추천');
  return axiosInstance.post(`/product/like/${productId}`);
};
// export const getProductList = (filter, page, search) => {
//   let params = {
//     filter: filter,
//     page: page,
//     search: search,
//   };
//   return axiosInstance.get('/product', { params: params });
// };
