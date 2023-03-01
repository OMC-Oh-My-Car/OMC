import axiosInstance from '..';

axiosInstance.defaults.headers.common['Authorization'] = window.sessionStorage.getItem('Authorization');

export const getUserReservationList = (page) => {
  let params = {
    page,
  };
  console.log(params);
  console.log('예약 리스트 Loading');
  return axiosInstance.get('/reservation', {
    headers: {
      Authorization: window.sessionStorage.getItem('Authorization'),
    },
  });
};

export const getUserReservationDetail = (reservationId) => {
  // 예약 아이디 필요
  let params = {
    reservationId,
  };
  console.log(params);
  console.log('예약 리스트 Loading');
  return axiosInstance.get(`/reservation/${reservationId}`, {
    headers: {
      Authorization: window.sessionStorage.getItem('Authorization'),
      'Content-Type': 'application/json',
    },
  });
};

// 결제 API 와 같이 구현 예정.
export const addUserReservation = (paymentData, reservation) => {
  console.log(reservation);
  console.log(paymentData);
  // 예약 정보 가져와야 함
  console.log('유저 예약 추가 Loading');
  return axiosInstance.post(
    '/payment/success',
    JSON.stringify({
      ...paymentData,
      ...reservation,
    }),
    {
      headers: {
        'Content-Type': 'application/json',
      },
    },
  );
};

export const cancelUserReservation = (content, reservationId) => {
  console.log('유저 예약 취소');
  let params = {
    content,
    reservationId,
  };
  console.log(params);
  return axiosInstance.patch(`/reservation/cancel/${reservationId}`, {
    headers: {
      Authorization: window.sessionStorage.getItem('Authorization'),
    },
    cancelReason: content,
  });
};

export const getCancelUserReason = (reservationId) => {
  // 취소 사유 가져와야 함
  let params = {
    reservationId,
  };
  console.log(params);
  console.log('유저 예약 추가 Loading');
  return axiosInstance.get(`/reservation/${reservationId}/cancel-reason`, {
    headers: {
      Authorization: window.sessionStorage.getItem('Authorization'),
    },
  });
};
