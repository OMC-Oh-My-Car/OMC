import { useState } from 'react';
import { SellerProductAddPostCodeArea } from './SellerProductAddPostCode.style';
import { useDaumPostcodePopup } from 'react-daum-postcode';

const SellerProductAddPostCode = () => {
  const [address, setAddress] = useState('');
  const [zipCode, setZipCode] = useState('');

  const open = useDaumPostcodePopup();

  const addressPostHandler = (data) => {
    let fullAddress = data.address;
    let extraAddress = '';

    if (data.addressType === 'R') {
      if (data.bname !== '') {
        extraAddress += data.bname;
      }
      if (data.buildingName !== '') {
        extraAddress += extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName;
      }
      fullAddress += extraAddress !== '' ? ` (${extraAddress})` : '';
    }
    setAddress(fullAddress);
    setZipCode(data.zonecode);
  };

  const postCodeHandler = (e) => {
    e.preventDefault();
    open({ onComplete: addressPostHandler });
  };

  return (
    <>
      <SellerProductAddPostCodeArea>
        <h2>주소 입력하기</h2>
        <input type="text" onClick={postCodeHandler} name="Address" id="address" value={address} />
        <input type="text" onClick={postCodeHandler} name="ZipCode" id="zipCode" value={zipCode} />
      </SellerProductAddPostCodeArea>
    </>
  );
};

export default SellerProductAddPostCode;
