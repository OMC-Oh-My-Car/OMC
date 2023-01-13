// import React from 'react';
import { KakaoLoginTemplate } from './Kakao.style';
import ImgKakao from '../../../assets/images/kakao_login_medium_narrow.png';
const KakaoButton = () => {
  // const clickToKakao = () => {
  //   window.location.replace(`${KAKAO_AUTH_URL}`);
  // };
  return (
    <>
      <KakaoLoginTemplate>
        <img className="kakaoLogin" src={ImgKakao} alt={ImgKakao} />
      </KakaoLoginTemplate>
    </>
  );
};
export default KakaoButton;
