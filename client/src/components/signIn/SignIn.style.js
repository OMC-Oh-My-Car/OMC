import styled from 'styled-components';

export const Template = styled.div`
  border: solid 1px black;
  border-radius: 10px;
  padding: 15px;
  h1 {
    font-size: 27px;
    margin-bottom: 40px;
  }
  .signInHead {
    display: flex;
    justify-content: center;
    .signInIcon {
      cursor: pointer;
      margin-right: 10px;
    }
  }
  .idCheckBox {
    font-size: 15px;
    float: right;
    margin-top: -25px;
    margin-right: 11px;
  }
`;

export const SignInButton = styled.div`
  display: flex;
  justify-content: center;
`;
export const GoogleButtonTemplate = styled(SignInButton)`
  margin-top: 20px;
`;
export const KakaoButtonTemplate = styled(GoogleButtonTemplate)``;
