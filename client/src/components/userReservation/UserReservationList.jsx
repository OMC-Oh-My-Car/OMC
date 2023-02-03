// import React from 'react';
import { UserReservationListArea } from './UserReservationList.style';
import UserReservationItem from './UserReservationItem';
import UserReservationItemSkeleton from './UserReservationItemSkeleton';
const userReservationList = ({ data, isLoading, isError, openModalController }) => {
  const skeletonMapArr = Array.from({ length: 8 }, (v, i) => i);

  if (isLoading) {
    return (
      <>
        <UserReservationListArea>
          <h1>나의 일정</h1>
          <div className="reservationList">
            {skeletonMapArr.map((el) => {
              return <UserReservationItemSkeleton key={el} />;
            })}
          </div>
        </UserReservationListArea>
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
      <UserReservationListArea>
        <h1>나의 일정</h1>
        <div className="reservationList">
          {data &&
            data.data
              .filter((el) => el.reservationId)
              .map((item) => {
                return <UserReservationItem key={item.reservationId} openModalController={openModalController} />;
              })}
        </div>
      </UserReservationListArea>
    </>
  );
};

export default userReservationList;
