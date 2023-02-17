// import React from 'react';
import { SellerReservationListArea } from './SellerReservationList.style';
import SellerReservationItem from './SellerReservationItem';
import SellerReservationItemSkeleton from './SellerReservationItemSkeleton';

const SellerReservationList = ({ data, isLoading, isError, filter, setFilter, modalController }) => {
  const skeletonMapArr = Array.from({ length: 8 }, (v, i) => i);
  if (isLoading) {
    return (
      <>
        <SellerReservationListArea>
          <h1>예약 관리</h1>
          <ul className="filter">
            <li role="presentation" onClick={() => setFilter(0)}>
              일정 임박
            </li>
            <li role="presentation" onClick={() => setFilter(1)}>
              신규 예약
            </li>
            <li role="presentation" onClick={() => setFilter(2)}>
              취소된 예약
            </li>
          </ul>
          <div className="reservationList">
            {skeletonMapArr.map((el) => {
              return <SellerReservationItemSkeleton key={el} />;
            })}
          </div>
        </SellerReservationListArea>
      </>
    );
  }

  if (isError) {
    return (
      <>
        <div>err</div>
      </>
    );
  }
  return (
    <>
      <SellerReservationListArea>
        <h1>예약 관리</h1>
        <ul className="filter">
          <li role="presentation" className={`${filter === 0 ? ' active' : ''}`} onClick={() => setFilter(0)}>
            일정 임박
          </li>
          <li role="presentation" className={`${filter === 1 ? ' active' : ''}`} onClick={() => setFilter(1)}>
            신규 예약
          </li>
          <li role="presentation" className={`${filter === 2 ? ' active' : ''}`} onClick={() => setFilter(2)}>
            취소된 예약
          </li>
        </ul>
        <div className="reservationList">
          {data &&
            data.data.data.map((item) => {
              return <SellerReservationItem key={item.reservationId} item={item} modalController={modalController} />;
            })}
        </div>
      </SellerReservationListArea>
    </>
  );
};

export default SellerReservationList;
