import axiosInstance from '..';

export const getUserReservationList = (page) => {
  let params = {
    page,
  };
  console.log(params);
  console.log('예약 리스트 Loading');
  return axiosInstance.get('/reservation');
};

export const getUserReservationDetail = (page) => {
  // 예약 아이디 필요
  let params = {
    page,
  };
  console.log(params);
  console.log('예약 리스트 Loading');
  return axiosInstance.get('/reservation');
};

export const addUserReservation = (page) => {
  // 예약 정보 가져와야 함
  let params = {
    page,
  };
  console.log(params);
  console.log('유저 예약 추가 Loading');
  return axiosInstance.get('/reservation');
};

export const cancelUserReservation = (page) => {
  // 취소 사유 가져와야 함
  let params = {
    page,
  };
  console.log(params);
  console.log('유저 예약 추가 Loading');
  return axiosInstance.get('/reservation');
};

export const getCancelUserReason = (page) => {
  // 취소 사유 가져와야 함
  let params = {
    page,
  };
  console.log(params);
  console.log('유저 예약 추가 Loading');
  return axiosInstance.get('/reservation');
};
