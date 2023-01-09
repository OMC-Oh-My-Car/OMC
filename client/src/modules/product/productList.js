import axiosInstance from '..';

export const getProductList = () => {
  console.log('상품 리스트 Loading');
  return axiosInstance.get('/product');
};
// export const getProductList = (filter, page, search) => {
//   let params = {
//     filter: filter,
//     page: page,
//     search: search,
//   };
//   return axiosInstance.get('/product', { params: params });
// };
