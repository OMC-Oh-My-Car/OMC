import { SellerReservationListEmptyArea } from './SellerReservationListEmpty.style';

const SellerReservationListEmpty = () => {
  return (
    <>
      <SellerReservationListEmptyArea>
        <h1>예약 관리</h1>
        <span className="boldSpan">아직 등록된 예약이 없습니다!</span>
        <span className="text">장소를 정리하며 새로운 예약을 기다리세요.</span>
        <button className=" editProduct">상품 수정하기</button>
      </SellerReservationListEmptyArea>
    </>
  );
};

export default SellerReservationListEmpty;
