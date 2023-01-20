import axiosInstance from '..';

export const getSellerReservationList = (filter, page) => {
  let params = {
    filter,
    page,
  };
  console.log(params);
  console.log('판매자 상품 예약 리스트 Loading');
  return axiosInstance.get('/reservationProduct');
};
