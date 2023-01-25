// import React from 'react';
import { SellerProductAddPriceArea } from './SellerProductAddPrice.style';

const SellerProductAddPrice = () => {
  return (
    <>
      <SellerProductAddPriceArea>
        <h2>가격 입력하기</h2>
        <div className="addPrice">
          <span>₩</span>
          <input type="text" />
        </div>
      </SellerProductAddPriceArea>
    </>
  );
};

export default SellerProductAddPrice;
