// import React from 'react';
import { ImageModalArea } from './ImageModal.style';
import { getProductDetail } from '../../modules/userProduct/userProductDetail';
import { useQuery } from 'react-query';
import { useLocation } from 'react-router-dom';

const ImageModal = () => {
  const location = useLocation();
  const productId = location.pathname.split('/')[2];

  const { data } = useQuery(['productDetail', productId], async () => {
    const data = await getProductDetail(productId);
    return data;
  });

  return (
    <>
      {data &&
        data.data.data.img.map((el, index) => {
          return (
            <>
              <ImageModalArea>
                <img className={`image${index + 1}`} src={el} alt={`상품 이미지${index + 1}`} />
              </ImageModalArea>
            </>
          );
        })}
    </>
  );
};

export default ImageModal;
