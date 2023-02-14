import styled from 'styled-components';

export const ButtonArea = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 180px;
  height: 100%;
  padding: 2px 0px;
  button {
    width: 85px;
    height: 100%;
    font-weight: 500;
    font-size: 15px;
    border-radius: 20px;
    transition: all 0.2s;
    cursor: pointer;
  }
  .buttonRed {
    background-color: ${(props) => props.theme.red};
    border: 1px solid ${(props) => props.theme.red};
    color: white;
  }
  .buttonRed:hover {
    background-color: white;
    border: 1px solid ${(props) => props.theme.red};
    color: ${(props) => props.theme.red};
  }
  .buttonYellow {
    background-color: ${(props) => props.theme.yellow};
    border: 1px solid ${(props) => props.theme.yellow};
    color: white;
  }
  .buttonYellow:hover {
    background-color: white;
    border: 1px solid ${(props) => props.theme.yellow};
    color: ${(props) => props.theme.yellow};
  }
`;
