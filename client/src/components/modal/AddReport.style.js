import styled from 'styled-components';

export const AddReportArea = styled.div`
  width: 100%;
  height: 90%;
  flex-direction: column;
  display: flex;
  align-items: flex-end;
  padding-right: 25px;
  h2 {
    width: 100%;
    font-size: 24px;
    font-weight: 700;
    margin-bottom: 10px;
  }
  .title {
    width: 100%;
    display: flex;
    font-size: 19px;
    margin-bottom: 10px;
  }
  .content {
    width: 100%;
    display: flex;
    font-size: 19px;
    margin-bottom: 10px;
  }
  input {
    width: 100%;
    height: 40px;
    padding: 10px 5px;
    margin-bottom: 30px;
    font-size: 17px;
    outline: none;
    font-weight: 300;
    border: 1px solid ${(props) => props.theme.gray};
  }
  textarea {
    width: 100%;
    padding: 5px;
    font-size: 17px;
    border: 1px solid ${(props) => props.theme.gray};
    outline: none;
    margin-bottom: 15px;
  }
  .explain {
    width: 100%;
    font-weight: 300;
    width: 100%;
    display: inline-block;
    color: ${(props) => props.theme.red};
    margin-bottom: 25px;
    font-size: 19px;
  }
  .more {
    width: 100%;
    padding: 10px 25px;
    font-size: 19px;
    font-weight: 600;
    outline: none;
    border-radius: 5px;
    transition: all 0.2s;
    font-family: sans-serif;
    background-color: ${(props) => props.theme.yellow};
    border: 1px solid ${(props) => props.theme.yellow};
    color: white;
    cursor: pointer;
  }
  .more:hover {
    border: 1px solid ${(props) => props.theme.yellow};
    color: ${(props) => props.theme.yellow};
    background-color: white;
  }
`;
