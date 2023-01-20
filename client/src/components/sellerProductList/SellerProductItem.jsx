import { SellerProductItemArea } from './SellerProductItem.style';

const SellerProductItem = () => {
  return (
    <>
      <SellerProductItemArea>
        <div className="imageArea">
          <img src="https://cdn.thescoop.co.kr/news/photo/202107/51410_73168_3149.jpg" alt="상품사진" />
        </div>
        <div className="productInfo">
          <span className="productTitle">전통한옥 단독주택</span>
          <button className="button buttonRed">상품 정보 수정</button>
          <button className="button buttonYellow">예약자 확인</button>
        </div>
      </SellerProductItemArea>
    </>
  );
};

export default SellerProductItem;
