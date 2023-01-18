import axiosInstance from '..';

export const getUserReservation = (page) => {
  let params = {
    page,
  };
  console.log(params);
  console.log('예약 리스트 Loading');
  return axiosInstance.get('/reservation');
};
