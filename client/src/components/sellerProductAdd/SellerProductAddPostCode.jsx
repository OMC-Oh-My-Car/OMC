import { SellerProductAddPostCodeArea } from './SellerProductAddPostCode.style';

const SellerProductAddPostCode = ({ address, zipCode, postCodeHandler }) => {
  return (
    <>
      <SellerProductAddPostCodeArea>
        <h2>주소 입력하기</h2>
        <input type="text" onClick={postCodeHandler} name="Address" id="address" value={address} readOnly />
        <input type="text" onClick={postCodeHandler} name="ZipCode" id="zipCode" value={zipCode} readOnly />
      </SellerProductAddPostCodeArea>
    </>
  );
};

export default SellerProductAddPostCode;
