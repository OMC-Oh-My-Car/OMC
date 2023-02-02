// import React from 'react';
import { ReservationCancelModalAddArea } from './ReservationCancelAddModal.style';

const ReservationCancelAddModal = () => {
  return (
    <>
      <ReservationCancelModalAddArea>
        <h2>예약 취소하기</h2>
        <span className="explain">이러한 사유로 예약을 취소합니다.</span>
        <textarea type="text" rows="10" cols="15" />
        <button className="more">제출하기</button>
      </ReservationCancelModalAddArea>
    </>
  );
};

export default ReservationCancelAddModal;
