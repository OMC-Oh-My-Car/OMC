import { SellerProductItemArea } from './SellerProductItem.style';
import { useNavigate } from 'react-router-dom';

const SellerProductItem = ({ item }) => {
  const navigate = useNavigate();
  console.log(item);
  const handleClick = () => {
    navigate(`/seller/12/product/${item.id}/reservation`);
  };
  const editProductInfo = () => {
    navigate(`/seller/12/product/${item.id}/edit`);
  };
  return (
    <>
      <SellerProductItemArea>
        <div className="imageArea">
          <img src={item.img[0]} alt="상품사진" />
        </div>
        <div className="productInfo">
          <span className="productTitle">{item.subject}</span>
          <button onClick={() => editProductInfo()} className="button buttonRed">
            상품 정보 수정
          </button>
          <button onClick={() => handleClick()} className="button buttonYellow">
            예약자 확인
          </button>
        </div>
      </SellerProductItemArea>
    </>
  );
};

export default SellerProductItem;
