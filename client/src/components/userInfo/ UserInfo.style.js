import styled from 'styled-components';

export const Template = styled.div`
  border-radius: 12px;
  border: solid 1px black;
  padding: 15px;
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 50%;
  h1 {
    font-size: 27px;
    margin-bottom: 40px;
  }
  .userInfoHead {
    display: flex;
    justify-content: center;
    .userInfoIcon {
      cursor: pointer;
      margin-right: 10px;
    }
  }
`;
export const UserInfoForm = styled.div`
  .inputHead {
    display: flex;
    justify-content: space-between;
  }
  .changeButtonStyle {
    margin-top: 65px;
    margin-left: 30px;
  }
`;
export const ChangeButton = styled.button`
  width: 45px;
  height: 40px;
  border: solid 1px black;
  border-radius: 12px;
  text-decoration: underline;
  background-color: black;
  cursor: pointer;
  color: white;
  :hover {
    background-color: darkgray;
  }
`;
