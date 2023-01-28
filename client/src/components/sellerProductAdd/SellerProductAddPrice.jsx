// import React from 'react';
import { SellerProductAddPriceArea } from './SellerProductAddPrice.style';

const SellerProductAddPrice = ({ price, handlePrice }) => {
  return (
    <>
      <SellerProductAddPriceArea>
        <h2>가격 입력하기</h2>
        <div className="addPrice">
          <span>₩</span>
          <input type="text" value={price} onChange={handlePrice} />
        </div>
      </SellerProductAddPriceArea>
    </>
  );
};

export default SellerProductAddPrice;
