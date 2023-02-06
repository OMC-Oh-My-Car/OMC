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
  input[type='number']::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
  }
  width: 510px;
  height: 200px;
  border: 1px solid white;
  border-radius: 10px;
  padding: 1%;
  z-index: 1;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
  .inputTitle {
    border: solid 1px green;
  }
  .modalInputBox {
  }
  .closeIcon {
    cursor: pointer;
  }
`;
export const HeaderDiv = styled.div`
  display: flex;
  justify-content: space-between;
  height: 10%;
  min-height: 15px;
  padding: 5px;
  margin-bottom: 15px;
`;
