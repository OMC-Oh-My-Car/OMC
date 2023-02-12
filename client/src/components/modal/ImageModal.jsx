// import React from 'react';
import { ImageModalArea } from './ImageModal.style';
import { getProductDetail } from '../../modules/userProduct/userProductDetail';
import { useQuery } from 'react-query';

const ImageModal = () => {
  let productId = window.sessionStorage.getItem('lastPath').split('/')[2];
  // eslint-disable-next-line no-unused-vars
  const { data } = useQuery(['productDetail', productId], async () => {
    const data = await getProductDetail(productId);
    return data;
  });

  return (
    <>
      <ImageModalArea>
        <img
          className="image1"
          src="https://www.mpva.go.kr/site/hogug/images/contents/cts617_img1.jpg"
          alt="상품 이미지1"
        />
      </ImageModalArea>
      <ImageModalArea>
        <img
          className="image1"
          src="https://a0.muscache.com/im/pictures/c9f06d73-58bf-46c8-a104-85e8ddb06c2d.jpg?im_w=720"
          alt="상품 이미지1"
        />
      </ImageModalArea>
      <ImageModalArea>
        <img
          className="image1"
          src="https://a0.muscache.com/im/pictures/19515b3a-ccea-4cfe-b137-63a2fa2733cd.jpg?im_w=720"
          alt="상품 이미지1"
        />
      </ImageModalArea>
    </>
  );
};

export default ImageModal;
