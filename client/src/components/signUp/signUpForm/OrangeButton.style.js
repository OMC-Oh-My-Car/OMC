import styled from 'styled-components';
export const OrangeButtonStyle = styled.button`
  width: ${(props) => (props.width ? props.width : 'auto')};
  height: ${(props) => (props.height ? props.height : 'auto')};
  border: none;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 15px;
  background-color: ${(props) => props.theme.orange};
  &:hover {
    background-color: ${(props) => props.theme.darkorange};
  }
  &:active {
    background-color: ${(props) => props.theme.peru};
  }
  cursor: pointer;
  font-size: 20px;
  color: white;
`;
