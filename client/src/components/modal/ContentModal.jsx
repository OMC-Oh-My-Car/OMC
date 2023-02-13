// import React from 'react';
import { ContentModalArea } from './ContentModal.style';
import { getProductDetail } from '../../modules/userProduct/userProductDetail';
import { useQuery } from 'react-query';
import { useSelector } from 'react-redux';

const ContentModal = () => {
  const productId = useSelector((state) => state.modal.lastPath).split('/')[2];

  if (productId) {
    const { data } = useQuery(['productDetail', productId], async () => {
      const data = await getProductDetail(productId);
      return data;
    });
  }
  return (
    <>
      <ContentModalArea>
        <h2>숙소 설명</h2>
        <span>
          재미의 한가운데에 있는 당신의 작은 홈 스매싱. 스타일리시하고 필수 편의시설이 완비된 해안가에 위치한 저희 쇼어2
          주소는 이번 2022년에 완벽한 스테이케이션 주소가 될 것입니다. 산뜻한 침구와 깨끗한 수건, 새로워진 인테리어와
          주방용품에 이르기까지, 집의 중간 지점에서 모든 것이 마음에 드실 거예요. 수영과 레저는 별개다. 쇼어2와 쇼어
          유명 풀 중 하나를 선택하세요. 쇼어 아울렛에서 쇼핑하고 식사를 하거나 아시아 몰 (Mall of Asia) 로 잠시 산책을
          떠나보세요. 지금 예약하세요.
        </span>
      </ContentModalArea>
    </>
  );
};

export default ContentModal;
