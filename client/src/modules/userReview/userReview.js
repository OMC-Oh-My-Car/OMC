import axiosInstance from '..';

export const getUserReview = (reservationId) => {
  // 리뷰 정보 가져오기
  // 예약 아이디 필요
  let params = {
    reservationId,
  };
  console.log(params);
  console.log('리뷰 Loading');
  return axiosInstance.get(`/reservation/${reservationId}/review`);
};

export const addUserReview = (page) => {
  // 작성 리뷰 정보 가져오기
  // 예약 아이디 필요
  let params = {
    page,
  };
  console.log(params);
  console.log('리뷰 추가');
  return axiosInstance.get('/reservation');
};
