// import React from 'react';
import { SellerReservationItemSkeletonArea } from './SellerReservationItemSkeleton.style';

const SellerReservationItemSkeleton = () => {
  return (
    <>
      <SellerReservationItemSkeletonArea>
        <div className="image">
          <div className="dummy"></div>
        </div>
        <div className="flexRight">
          <div className="reservationInfo">
            <span className="reservationNumber"></span>
            <span className="reservationDate"></span>
            <span className="userEmail"></span>
          </div>
          <div className="button">
            <div className="buttonLeft" />
            <div className="buttonRight" />
          </div>
        </div>
      </SellerReservationItemSkeletonArea>
    </>
  );
};

export default SellerReservationItemSkeleton;
