// import React from 'react';
import { UserReservationEmptyArea } from './UserReservationEmpty.style';

const ReservationUserEmpty = () => {
  return (
    <>
      <UserReservationEmptyArea>
        <h1>나의 일정</h1>
        <span className="boldSpan">아직 예약된 일정이 없습니다!</span>
        <span className="text">여행 가방에 쌓인 먼지를 털어내고 다음 여행 계획을 세워보세요.</span>
        <button className="addTravel">일정 만들기</button>
      </UserReservationEmptyArea>
    </>
  );
};

export default ReservationUserEmpty;
