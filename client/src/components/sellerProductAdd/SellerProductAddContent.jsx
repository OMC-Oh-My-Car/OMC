// import React from 'react';
import { SellerProductAddContentArea } from './SellerProductAddContent.style';
const SellerProductAddContent = ({ content, handleContent }) => {
  return (
    <>
      <SellerProductAddContentArea>
        <h2>내용 입력하기</h2>
        <textarea type="text" rows="15" cols="15" value={content} onChange={handleContent} />
        <div className="count">
          <span>남은 글자 수 : 300글자</span>
        </div>
      </SellerProductAddContentArea>
    </>
  );
};

export default SellerProductAddContent;
