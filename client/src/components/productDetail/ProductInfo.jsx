// import React from 'react';
import { ProductInfoArea } from './ProductInfo.style';
import { faStar } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import ProductReservation from './ProductReservation';
const ProductInfo = () => {
  return (
    <>
      <ProductInfoArea>
        <div className="productInfo">
          <h1>전통한옥 단독주택</h1>
          <div className="prductDescription">
            <FontAwesomeIcon className="starIcon" icon={faStar} />
            <span>4.69 · </span>
            <span className="productInfoReviewCount">후기 32개</span>
            <span> · </span>
            <span className="productInfoPlace">전라북도 전주시</span>
          </div>
          <div className="content">
            <span>
              한옥스테이 감성하루는 전주한옥마을 태조로중심가에 위치하고 있습니다. 주변 관광지 (오목대, 전동성당, 경기
              전, 향교) 등 5분 거리에 있으며 먹걸이가 풍성한 메인도로옆에 위치해있으며 깨끗하고 예쁜 단독 한옥스테이
              입니다. 숙소 저희숙소는 105년된 전통 한옥가옥을 새로운감성으로 현대인의 생활에편리하도록 바꾸었습니다 저희
              숙소의 장점은 한옥마을 관광지, 유명 맛집이 있는메인도로 거리에 위치해있으며 한복 ,교복.경성의상.사진촬영시
              안내 도와드리며 저희 숙소는 이벤트하기좋은 숙소로 커플, 나 홀로여행족, 출장자, 친구,가족분들에게
              힐링할수있는 감성공간입니다. 게스트 이용 가능 공간/시설 독체숙소로 객실안에 있는 모든시설물은
              사용가능합니다 기타 주의사항 체크인ㅡ오후3시이며 체크아웃ㅡ오전11시입니다 애완견 동반시 무료퇴실
              하셔야합니다~ 힐링할수있는 감성공간입니다. 게스트 이용 가능 공간/시설 독체숙소로 객실안에 있는
              모든시설물은 모든시설물은 모든시설물은 사용가능합니다 기타 주의사항 체크인ㅡ오후3시이며
              체크아웃ㅡ오전11시입니다 애완견 하셔야합니다~
            </span>
          </div>
          <div className="facilities">
            <h2>숙소 편의시설</h2>
            <div className="facilityList">
              <div className="facilityLeft">
                <div className="facilityItem">
                  <div>해변과 인접 - 해변</div>
                </div>
                <div className="facilityItem">
                  <div>무선 인터넷</div>
                </div>
                <div className="facilityItem">
                  <div>온수 욕조</div>
                </div>
                <div className="facilityItem">
                  <div>세탁기</div>
                </div>
                <div className="facilityItem">
                  <div>에어컨</div>
                </div>
              </div>
              <div className="facilityRight">
                <div className="facilityItem">
                  <div>주방</div>
                </div>
                <div className="facilityItem">
                  <div>TV</div>
                </div>
                <div className="facilityItem">
                  <div>건물 내 무료 주차</div>
                </div>
                <div className="facilityItem">
                  <div>건조기</div>
                </div>
                <div className="facilityItem">
                  <div>숙소 내 보안 카메라</div>
                </div>
              </div>
            </div>
            <button className="more">편의시설 모두 보기</button>
          </div>
        </div>
        <div className="reservation">
          <ProductReservation />
        </div>
      </ProductInfoArea>
    </>
  );
};

export default ProductInfo;
