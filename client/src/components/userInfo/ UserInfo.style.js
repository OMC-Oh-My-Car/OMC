import styled from 'styled-components';

export const Template = styled.div`
  border-radius: 12px;
  border: solid 1px black;
  padding: 15px;
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 35%;
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
  }
`;
export const ChangeButton = styled.button`
  margin-top: 25px;
  width: 370px;
  height: 40px;
  border-radius: 12px;
  background-color: #0095f6;
  cursor: pointer;
  font-size: 20px;
  color: white;
  :hover {
    background-color: orange;
  }
`;
