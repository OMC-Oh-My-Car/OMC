import { UserReservationItemSkeletonArea } from './UserReservationItemSkeleton.style';

const UserReservationItemSkeleton = () => {
  return (
    <>
      <UserReservationItemSkeletonArea>
        <div className="imageArea" />
        <div className="productInfo">
          <span className="productTitle" />
          <span className="reservationPeriod" />
          <div className="button buttonRed" />
          <div className="button buttonYellow" />
        </div>
      </UserReservationItemSkeletonArea>
    </>
  );
};

export default UserReservationItemSkeleton;
