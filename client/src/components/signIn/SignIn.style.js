import styled from 'styled-components';

export const Template = styled.div`
  border-radius: 10px;
  padding: 15px;
  margin-top: 150px;
  width: 100%;
  max-width: 370px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  h1 {
    display: flex;
    justify-content: center;
    width: 100%;
    font-size: 30px;
    margin-bottom: 50px;
    color: ${(props) => props.theme.yellow};
    font-weight: 700;
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
    margin-top: -15px;
    margin-right: 11px;
  }
  .btn {
    width: 100%;
    margin-bottom: 10px;
    padding: 10px 20px;
    font-size: 18px;
    font-weight: 600;
    outline: none;
    border-radius: 5px;
    transition: all 0.2s;
    font-family: sans-serif;
    background-color: ${(props) => props.theme.yellow};
    border: 1px solid ${(props) => props.theme.yellow};
    color: white;
    cursor: pointer;
  }
  .btn:hover {
    border: 1px solid ${(props) => props.theme.yellow};
    color: ${(props) => props.theme.yellow};
    background-color: white;
  }
  .signupLink {
    font-size: 14px;
    margin-top: 10px;
    margin-bottom: 20px;
    display: flex;
    justify-content: center;
    a {
      margin-left: 10px;
      color: rgb(0, 138, 169);
    }
  }
  .flexBox {
    width: 40%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 50px;
    img {
      width: 40px;
      cursor: pointer;
    }
  }
`;

export const SignInButton = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
`;
