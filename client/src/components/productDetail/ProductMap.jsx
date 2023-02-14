// import React from 'react';
import { ProductMapArea } from './ProductMap.style';
import KakaoMap from '../map/KakaoMap';

const ProductMap = ({ productId, data }) => {
  return (
    <>
      <ProductMapArea>
        <h2>위치</h2>
        <div className="kakaoMap">
          <KakaoMap productId={productId} data={data} />
        </div>
        <h3>속초시, 강원도, 한국</h3>
      </ProductMapArea>
    </>
  );
};

export default ProductMap;
