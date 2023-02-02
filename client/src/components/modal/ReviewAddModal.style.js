import styled from 'styled-components';

export const ReviewAddModalArea = styled.div`
  width: 100%;
  height: 90%;
  overflow-y: scroll;
  flex-direction: column;
  padding-right: 25px;
  h2 {
    font-size: 24px;
    font-weight: 700;
    margin-bottom: 20px;
  }
  .reviewText {
    width: 100%;
    display: inline-block;
    font-weight: 300;
    margin-bottom: 10px;
    font-size: 20px;
  }
  textarea {
    width: 100%;
    padding: 5px;
    font-size: 17px;
    border: 1px solid ${(props) => props.theme.gray};
    outline: none;
    margin-bottom: 10px;
  }
  .count {
    width: 100%;
    max-width: 520px;
    display: flex;
    justify-content: flex-end;
    margin-bottom: 20px;
    span {
      color: ${(props) => props.theme.gray};
    }
  }
  .gradeText {
    width: 100%;
    display: inline-block;
    font-size: 20px;
    margin: 15px 0px 8px;
  }
  .explain {
    width: 100%;
    display: inline-block;
    color: ${(props) => props.theme.gray};
    margin-bottom: 15px;
    font-size: 14px;
  }
  .starIcon {
    font-size: 25px;
    color: ${(props) => props.theme.gray};
    margin-right: 3px;
    margin-bottom: 30px;
    cursor: pointer;
  }
  .active {
    color: ${(props) => props.theme.yellow};
  }
`;
