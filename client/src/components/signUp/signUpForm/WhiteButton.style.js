import styled from 'styled-components';

export const WhiteButtonStyle = styled.button`
  width: ${(props) => (props.width ? props.width : 'auto')};
  height: ${(props) => (props.height ? props.height : 'auto')};
  border: 1px solid orange;
  border-radius: 15px;
  background-color: #fffafa;
  &:hover {
    background-color: #ffa500;
  }
  &:active {
    background-color: darkgray;
  }
  cursor: pointer;
  font-size: 20px;
  color: black;
`;
