import styled from 'styled-components';

export const Template = styled.div`
  border: solid 1px black;
  height: 100%;
  padding: 15px;
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
  align-items: center;
`;
export const SignUpButton = styled.div`
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
`;
export const SignInComment = styled(SignUpButton)`
  .goLogin {
    padding-left: 20px;
    cursor: pointer;
    color: blue;
    text-decoration: underline;
    &:hover {
      color: darkgray;
    }
  }
`;
