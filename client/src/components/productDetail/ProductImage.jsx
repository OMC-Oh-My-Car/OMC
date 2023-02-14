// import React from 'react';
import { ProductImageArea } from './ProductImage.style';

const ProductImage = ({ data, modalController }) => {
  return (
    <>
      <ProductImageArea onClick={() => modalController('image', '100vw', '100vh', 'product_image')}>
        {data ? (
          data.data.data.img.map((el, index) => {
            return <img key={index} className={`image${index + 1}`} src={el} alt={`상품 이미지${index + 1}`} />;
          })
        ) : (
          <></>
        )}
      </ProductImageArea>
    </>
  );
};

export default ProductImage;
