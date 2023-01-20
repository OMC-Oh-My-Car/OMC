import { SellerProductListEmptyArea } from './SellerProductListEmpty.style';

const SellerProductListEmpty = () => {
  return (
    <>
      <SellerProductListEmptyArea>
        <h1>등록 상품</h1>
        <span className="boldSpan">아직 등록된 상품이 없습니다!</span>
        <span className="text">새로운 상품을 등록하여 호스팅을 시작하세요!</span>
        <button className="addProduct">상품 등록하기</button>
      </SellerProductListEmptyArea>
    </>
  );
};

export default SellerProductListEmpty;
