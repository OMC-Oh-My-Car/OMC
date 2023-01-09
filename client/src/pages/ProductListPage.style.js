import styled from 'styled-components';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  .productFilter {
    padding: 50px 100px 0px;
    display: flex;
    justify-content: end;
    align-items: center;
    gap: 10px;
    width: 100%;
    height: 40px;
    margin-bottom: 10px;
    /* border: 1px solid black; */
    .productFilterItem {
      cursor: pointer;
      font-size: 15px;
      padding: 7px 10px;
      outline: none;
      border: none;
      background-color: white;
      border: 2px solid white;
      margin: 0;
    }
    .productFilterItem.active {
      color: ${(props) => props.theme.yellow};
      font-weight: 700;
      border: 2px solid ${(props) => props.theme.yellow};
      border-radius: 20px;
    }
    .productFilterItem:hover {
      color: ${(props) => props.theme.red};
    }
  }
  @media (max-width: 1425px) {
    .productFilter {
      padding: 50px 50px 0px;
    }
  }
  @media (max-width: 744px) {
    .productFilter {
      padding: 50px 25px 0px;
    }
  }
`;
