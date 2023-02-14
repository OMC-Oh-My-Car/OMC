import { useEffect, useRef } from 'react';
import { KakaoMapArea } from './KakaoMap.style';

const KakaoMap = ({ productId, data }) => {
  const mapContainer = useRef(null);
  const { kakao } = window;
  const position = new kakao.maps.LatLng(33.450701, 126.570667);
  const mapOptions = {
    center: position, // 지도의 중심좌표
    level: 4, // 지도의 확대 레벨
  };
  useEffect(() => {
    const map = new kakao.maps.Map(mapContainer.current, mapOptions);
    const mapTypeControl = new kakao.maps.MapTypeControl();
    map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

    const geocoder = new kakao.maps.services.Geocoder();
    const zoomControl = new kakao.maps.ZoomControl();
    map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

    geocoder.addressSearch(`${data.data.data.address}`, function (result, status) {
      if (status === kakao.maps.services.Status.OK) {
        const coords = new kakao.maps.LatLng(result[0].y, result[0].x);
        const marker = new kakao.maps.Marker({
          map: map,
          position: coords,
        });
        marker.setMap(map);
        map.setCenter(coords);
      }
    });
  }, []);

  return (
    <>
      <KakaoMapArea id="map" ref={mapContainer} />
    </>
  );
};

export default KakaoMap;
