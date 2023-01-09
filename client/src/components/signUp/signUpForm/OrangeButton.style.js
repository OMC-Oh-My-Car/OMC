import styled from 'styled-components';

export const OrangeButtonStyle = styled.button`
  width: ${(props) => (props.width ? props.width : 'auto')};
  height: ${(props) => (props.height ? props.height : 'auto')};
  border: 1px solid black;
  border-radius: 15px;
  background-color: #ff6c02;
  &:hover {
    background-color: #ffa500;
  }
  &:active {
    background-color: darkgray;
  }
  cursor: pointer;
  font-size: 20px;
  color: white;
`;
