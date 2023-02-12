// import React from 'react';
import { FacilityModalArea } from './FacilityModal.style';
import { getProductDetail } from '../../modules/userProduct/userProductDetail';
import { useQuery } from 'react-query';

const FacilityModal = () => {
  let productId = window.sessionStorage.getItem('lastPath').split('/')[2];
  console.log(productId);
  // eslint-disable-next-line no-unused-vars
  const { data } = useQuery(['productDetail', productId], async () => {
    const data = await getProductDetail(productId);
    return data;
  });
  return (
    <>
      <FacilityModalArea>
        <h2>숙소 편의시설</h2>
        <ul className="facilityList">
          <li className="facilityItem">헤어드라이어</li>
          <li className="facilityItem">헤어드라이어</li>
          <li className="facilityItem">헤어드라이어</li>
          <li className="facilityItem">헤어드라이어</li>
          <li className="facilityItem">헤어드라이어</li>
          <li className="facilityItem">헤어드라이어</li>
          <li className="facilityItem">헤어드라이어</li>
          <li className="facilityItem">헤어드라이어</li>
          <li className="facilityItem">헤어드라이어</li>
          <li className="facilityItem">헤어드라이어</li>
          <li className="facilityItem">헤어드라이어</li>
          <li className="facilityItem">헤어드라이어</li>
          <li className="facilityItem">헤어드라이어</li>
        </ul>
      </FacilityModalArea>
    </>
  );
};

export default FacilityModal;
