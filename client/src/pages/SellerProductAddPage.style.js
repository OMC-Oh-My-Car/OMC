import styled from 'styled-components';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
`;
export const MainContainer = styled.main`
  position: relative;
  display: flex;
  flex-direction: column;
  width: 100%;
  max-width: 1320px;
  padding: 50px 80px;
  .button {
    display: flex;
    justify-content: flex-end;
    width: 100%;
    max-width: 520px;
    gap: 20px;
    button {
      padding: 10px 20px;
      font-size: 16px;
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
    button:hover {
      border: 1px solid ${(props) => props.theme.yellow};
      color: ${(props) => props.theme.yellow};
      background-color: white;
    }
    .red {
      background-color: ${(props) => props.theme.red};
      border: 1px solid ${(props) => props.theme.red};
    }
    .red:hover {
      border: 1px solid ${(props) => props.theme.red};
      color: ${(props) => props.theme.red};
      background-color: white;
    }
  }
  h1 {
    width: 100%;
    font-size: 27px;
    font-weight: 700;
    border-bottom: 1px solid ${(props1) => props1.theme.gray};
    padding-bottom: 30px;
    margin-bottom: 30px;
  }
  @media (max-width: 1128px) {
    padding: 40px 40px;
  }
  /*모바일 */
  @media (max-width: 744px) {
    padding: 20px 25px;
  }
`;
