import styled from 'styled-components';

export const InputContainer = styled.div`
  width: 100%;
  height: 80px;
  margin-bottom: 5px;
  font-size: 17px;
  .formInput {
    width: 100%;
    padding: 0.6em 0.7em;
    border: none;
    border-bottom: 1px solid #5c4b29;
    border-radius: 3px;
    margin-top: 10px;
    font-size: 17px;
  }
  .formInput:focus {
    border: 1px solid #53baff;
    outline: 3px solid #b5deff;
  }
  .formLabel {
    width: auto;
    height: 30px;
    font-weight: 600;
    color: ${(props) => props.theme.yellow};
    cursor: pointer;
  }
`;
