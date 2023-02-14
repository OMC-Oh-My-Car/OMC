// import React from 'react';
import { FacilityModalArea } from './FacilityModal.style';
import { getProductDetail } from '../../modules/userProduct/userProductDetail';
import { useQuery } from 'react-query';
import { useLocation } from 'react-router-dom';

const FacilityModal = () => {
  const location = useLocation();
  const productId = location.pathname.split('/')[2];

  const { data } = useQuery(['productDetail', productId], async () => {
    const data = await getProductDetail(productId);
    return data;
  });
  console.log(data);
  return (
    <>
      <FacilityModalArea>
        <h2>숙소 편의시설</h2>
        <ul className="facilityList">
          {data &&
            data.data.data.facilities.map((el, index) => {
              return (
                <>
                  <li key={index} className="facilityItem">
                    {el}
                  </li>
                </>
              );
            })}
        </ul>
      </FacilityModalArea>
    </>
  );
};

export default FacilityModal;
