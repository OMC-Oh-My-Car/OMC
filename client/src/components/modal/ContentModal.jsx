// import React from 'react';
import { ContentModalArea } from './ContentModal.style';
import { getProductDetail } from '../../modules/userProduct/userProductDetail';
import { useQuery } from 'react-query';
import { useLocation } from 'react-router-dom';

const ContentModal = () => {
  const location = useLocation();
  const productId = location.pathname.split('/')[2];

  const { data } = useQuery(['productDetail', productId], async () => {
    const data = await getProductDetail(productId);
    return data;
  });
  return (
    <>
      <ContentModalArea>
        <h2>숙소 설명</h2>
        <span>{data && data.data.data.description}</span>
      </ContentModalArea>
    </>
  );
};

export default ContentModal;
