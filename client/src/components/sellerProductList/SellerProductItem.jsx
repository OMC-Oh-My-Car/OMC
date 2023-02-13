import { SellerProductItemArea } from './SellerProductItem.style';
import { useNavigate } from 'react-router-dom';

const SellerProductItem = () => {
  const navigate = useNavigate();
  const productId = 1;
  const handleClick = (productId) => {
    navigate(`/seller/12/product/${productId}/reservation`);
  };
  const editProductInfo = (productId) => {
    navigate(`/seller/12/product/${productId}/edit`);
  };
  return (
    <>
      <SellerProductItemArea>
        <div className="imageArea">
          <img src="https://cdn.thescoop.co.kr/news/photo/202107/51410_73168_3149.jpg" alt="상품사진" />
        </div>
        <div className="productInfo">
          <span className="productTitle">전통한옥 단독주택</span>
          <button onClick={() => editProductInfo(productId)} className="button buttonRed">
            상품 정보 수정
          </button>
          <button onClick={() => handleClick(productId)} className="button buttonYellow">
            예약자 확인
          </button>
        </div>
      </SellerProductItemArea>
    </>
  );
};

export default SellerProductItem;
