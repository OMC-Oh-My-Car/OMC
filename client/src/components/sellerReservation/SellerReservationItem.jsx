// import React from 'react';
import { SellerReservationItemArea } from './SellerReservationItem.style';

const SellerReservationItem = () => {
  return (
    <>
      <SellerReservationItemArea>
        <div className="image">
          <img src="https://cdn.thescoop.co.kr/news/photo/202107/51410_73168_3149.jpg" alt="상품사진" />
        </div>
        <div className="flexRight">
          <div className="reservationInfo">
            <span className="reservationNumber">예약 번호: 2022-1220-0874</span>
            <span className="reservationDate">예약 기간: 2023년 1월 12일 ~ 2023년 1월 13일</span>
            <span className="userEmail">이메일: shinker1002@naver.com</span>
          </div>
          <div className="button">
            <button className="buttonLeft">예약 정보</button>
            <button className="buttonRight">예약 정보</button>
          </div>
        </div>
      </SellerReservationItemArea>
    </>
  );
};

export default SellerReservationItem;
