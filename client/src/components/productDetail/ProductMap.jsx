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
        <h3>속초시, 강원도, 한국</h3>
      </ProductMapArea>
    </>
  );
};

export default ProductMap;
