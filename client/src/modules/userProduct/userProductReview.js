import axiosInstance from '..';

export const getProductReview = (productId) => {
  let params = {
    productId,
  };
  console.log(params);
  console.log('상품 리뷰 Loading');
  return axiosInstance.get(`/review/${productId}`);
};
