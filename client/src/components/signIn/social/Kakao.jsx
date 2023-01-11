// import React from 'react';
import { KakaoLoginTemplate } from './Kakao.style';
import ImgKakao from '../../../assets/images/kakao_login_medium_narrow.png';
const KakaoButton = () => {
  // const jsKey = '8680d8b6ff30ce31730f770c6805143d';
  // const REST_API_KEY= '76dddc7960ffdce4e922290d549e9b91';
  // const REDIRECT_URI = 'http://localhost:3000/auth/kakao/callback';
  // const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;

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
