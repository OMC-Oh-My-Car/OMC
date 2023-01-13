// import React from 'react';
import { ProductMapArea } from './ProductMap.style';
import KakaoMap from '../map/KakaoMap';

const ProductMap = () => {
  return (
    <>
      <ProductMapArea>
        <h2>위치</h2>
        <div className="kakaoMap">
          <KakaoMap />
        </div>
      </ProductMapArea>
    </>
  );
};

export default ProductMap;
