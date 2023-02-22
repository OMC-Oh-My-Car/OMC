import styled from 'styled-components';

export const Template = styled.div`
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  max-width: 500px;
  margin-top: 50px;
  padding: 0px 30px;
  margin-bottom: 50px;
  h1 {
    font-size: 30px;
    margin-bottom: 40px;
    font-weight: 700;
    color: #5c4b29;
  }
`;

export const UserInputButton = styled.button`
  width: 100%;
  border-radius: 25px;
  padding: 13px 20px;
  font-size: 18px;
  font-weight: 600;
  outline: none;
  transition: all 0.2s;
  font-family: sans-serif;
  background-color: ${(props) => props.theme.yellow};
  border: 1px solid ${(props) => props.theme.yellow};
  color: white;
  cursor: pointer;
  margin-bottom: 10px;
  :hover {
    border: 1px solid ${(props) => props.theme.yellow};
    color: ${(props) => props.theme.yellow};
    background-color: white;
  }
  &.red {
    background-color: ${(props) => props.theme.red};
    border: 1px solid ${(props) => props.theme.red};
    :hover {
      border: 1px solid ${(props) => props.theme.red};
      color: ${(props) => props.theme.red};
    }
  }
`;
