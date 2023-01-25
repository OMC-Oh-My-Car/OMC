// import React from 'react';
import { SellerProductAddTitleArea } from './SellerProductAddTitle.style';
const SellerProductAddTitle = () => {
  return (
    <>
      <SellerProductAddTitleArea>
        <h2>제목 입력하기</h2>
        <input type="text" />
        <div className="count">
          <span>남은 글자 수 : 50글자</span>
        </div>
      </SellerProductAddTitleArea>
    </>
  );
};

export default SellerProductAddTitle;
