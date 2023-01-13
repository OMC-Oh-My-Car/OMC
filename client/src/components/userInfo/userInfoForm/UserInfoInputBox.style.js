import styled from 'styled-components';

export const InputContainer = styled.div`
  width: 350px;
  .inputLabel {
    height: 20px;
    display: flex;
    margin-left: 3px;
    margin-bottom: 5px;
    margin-top: 50px;
    color: ${(props) => props.theme.gray};
  }
  .inputBox {
    width: 340px;
    border: none;
    border-bottom: 3px solid darkgray;
    padding-left: 3px;
    word-break: break-all;
    padding-bottom: 5px;
  }
`;
