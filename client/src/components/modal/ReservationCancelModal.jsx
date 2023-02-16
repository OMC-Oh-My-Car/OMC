// import React from 'react';
import { ReservationCancelModalArea } from './ReservationCancelModal.style';
import { useQuery } from 'react-query';
import { getCancelUserReason } from '../../modules/userReservation/userReservation';

const ReservationCancelModal = () => {
  const params = new URLSearchParams(location.search);
  let reservationId = params.get('reservation_id');

  const { data } = useQuery(['reservationCancelReason', reservationId], async () => {
    const data = await getCancelUserReason(reservationId);
    return data;
  });
  console.log(data);
  return (
    <>
      <ReservationCancelModalArea>
        <h2>취소된 예약</h2>
        <span className="explain">이러한 사유로 예약을 취소합니다.</span>
        <span>{data && data.data.data.cancelReason}</span>
      </ReservationCancelModalArea>
    </>
  );
};

export default ReservationCancelModal;
