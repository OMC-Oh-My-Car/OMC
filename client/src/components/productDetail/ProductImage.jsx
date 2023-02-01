// import React from 'react';
import { ProductImageArea } from './ProductImage.style';

const ProductImage = ({ openModalController }) => {
  return (
    <>
      <ProductImageArea onClick={() => openModalController({ type: 'image', width: '100vw', height: '100vh' })}>
        <img
          className="image1"
          src="https://www.mpva.go.kr/site/hogug/images/contents/cts617_img1.jpg"
          alt="상품 이미지1"
        />
        <img
          className="image2"
          src="https://www.mpva.go.kr/site/hogug/images/contents/cts617_img1.jpg"
          alt="상품 이미지2"
        />
        <img
          className="image3"
          src="https://www.mpva.go.kr/site/hogug/images/contents/cts617_img1.jpg"
          alt="상품 이미지3"
        />
        <img
          className="image4"
          src="https://www.mpva.go.kr/site/hogug/images/contents/cts617_img1.jpg"
          alt="상품 이미지4"
        />
        <img
          className="image5"
          src="https://www.mpva.go.kr/site/hogug/images/contents/cts617_img1.jpg"
          alt="상품 이미지5"
        />
      </ProductImageArea>
    </>
  );
};

export default ProductImage;
