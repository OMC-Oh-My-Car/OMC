import styled from 'styled-components';

export const Template = styled.div`
  padding: 15px;
  margin: 40px 0px;
  .success {
    color: blue;
  }
  .error {
    color: red;
  }
  h1 {
    font-size: 27px;
    margin-bottom: 40px;
  }
  .signUpHead {
    display: flex;
    justify-content: center;
    .signUpIcon {
      cursor: pointer;
      margin-right: 10px;
    }
  }
`;
export const SignUpForm = styled.div`
  display: flex;
`;
export const SignUpButton = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 30px;
  margin-bottom: 20px;
  border: none;
`;
export const SignInComment = styled(SignUpButton)``;
export const EmailButton = styled.div`
  display: flex;
  margin-top: 30px;
`;
export const GoLogin = styled.a`
  padding-left: 20px;
  cursor: pointer;
  color: blue;
  text-decoration: underline;
  &:hover {
    color: lightskyblue;
  }
`;
