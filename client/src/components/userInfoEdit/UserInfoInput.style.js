import styled from 'styled-components';

export const UserInfoInputArea = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  width: 100%;
  height: 55px;
  border-radius: 30px;
  background-color: rgb(230, 232, 245);
  margin-bottom: 30px;
  label {
    position: absolute;
    left: 20px;
    color: rgb(109, 109, 109);
  }
  input {
    border: none;
    outline: none;
    margin-left: 75px;
    background-color: rgb(230, 232, 245);
  }
`;

export const UserInfoDisableInputArea = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  width: 100%;
  height: 55px;
  border-radius: 30px;
  background-color: rgb(230, 232, 245);
  margin-bottom: 30px;

  label {
    position: absolute;
    left: 20px;
    color: rgb(109, 109, 109);
  }
  input {
    border: none;
    outline: none;
    margin-left: 75px;
    background-color: rgb(230, 232, 245);
    font-size: 18px;
  }
`;
