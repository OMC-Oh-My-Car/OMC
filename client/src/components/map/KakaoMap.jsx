import { useEffect, useRef } from 'react';

const KakaoMap = () => {
  const mapContainer = useRef(null);
  const { kakao } = window;
  const position = new kakao.maps.LatLng(33.450701, 126.570667);
  const mapOptions = {
    center: position, // 지도의 중심좌표
    level: 4, // 지도의 확대 레벨
  };
  useEffect(() => {
    const map = new kakao.maps.Map(mapContainer.current, mapOptions);
    console.log(map);
  });

  return (
    <>
      <div id="map" ref={mapContainer} style={{ width: '100%', height: '100%', display: 'block' }}></div>
    </>
  );
};

export default KakaoMap;
