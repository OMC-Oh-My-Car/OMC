import styled from 'styled-components';

export const ModalBackground = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.5);
`;
export const ContainerDiv = styled.div`
  width: 500px;
  height: 450px;
  border: 1px solid white;
  border-radius: 10px;
  padding: 3%;
  z-index: 1050;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
  .userIconTemplate {
    display: flex;
    justify-content: center;
    font-size: 100px;
    margin-bottom: 20px;
  }
  .inputTitle {
    border: solid 1px green;
  }
  .modalInputBox {
  }
  .handIcon {
    cursor: pointer;
  }
  .signupTitle {
    display: flex;
    justify-content: center;
    font-size: 23px;
    margin-bottom: 20px;
  }
  .signupBody {
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 30px;
    margin-bottom: 30px;
    color: darkgray;
  }
`;
export const HeaderDiv = styled.div`
  display: flex;
  justify-content: center;
  height: 10%;
  min-height: 15px;
  padding: 5px;
  margin-bottom: 15px;
`;
export const ButtonTemplate = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
`;
