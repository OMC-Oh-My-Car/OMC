// import React from 'react';
import { ProductItemArea } from './ProductItem.style';

const ProductItem = ({ item }) => {
  console.log(item);
  return (
    <>
      <ProductItemArea>
        <img
          src="https://www.hyundai.co.kr/image/upload/asset_library/MDA00000000000005326/9c99a0fb7e254d338ba6d92c7eb5d874.jpg"
          alt=""
        />
        {/* <div>123</div> */}
      </ProductItemArea>
    </>
  );
};

export default ProductItem;
