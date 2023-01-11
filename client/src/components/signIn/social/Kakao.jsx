// import React from 'react';
import { KakaoLoginTemplate } from './Kakao.style';
import ImgKakao from '../../../assets/images/kakao_login_medium_narrow.png';
const KakaoButton = () => {
  // const API_URL_JSKEY = process.env.REACT_APP_API_URL_JSKEY;
  // const API_URL_REST_API_KEY= process.env.REACT_APP_API_URL_REST_API_KEY;
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
