import axiosInstance from '..';

axiosInstance.defaults.headers.common['Authorization'] = window.sessionStorage.getItem('Authorization');

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

export const addUserReview = (data, reservationId) => {
  console.log(data);
  console.log('리뷰 추가');
  return axiosInstance.post(`/review/${reservationId}`, {
    content: data.content,
    starAccuracy: data.starAccuracy,
    starCleanliness: data.starCleanliness,
    starCostEffective: data.starCostEffective,
    starLocation: data.starLocation,
    totalStar: data.totalStar,
  });
};
