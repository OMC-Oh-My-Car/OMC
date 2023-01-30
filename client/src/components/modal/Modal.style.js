import styled from 'styled-components';

export const ModalArea = styled.div`
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  padding: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(0, 0, 0, 0.6);
  .modalInner {
    width: 100%;
    height: 100%;
    border-radius: 15px;
    padding: 25px 0px 25px 25px;
    max-width: ${(props) => (props.width ? `${props.width}` : 'auto')};
    max-height: ${(props) => (props.height ? `${props.height}` : 'auto')};
    background-color: white;
    .closeIcon {
      font-size: 25px;
      cursor: pointer;
      margin-bottom: 30px;
    }
  }
`;
