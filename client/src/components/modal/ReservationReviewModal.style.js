import styled from 'styled-components';

export const ReservationReviewModalArea = styled.div`
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
  .review {
    width: 100%;
    display: inline-block;
    font-weight: 300;
    line-height: 24px;
    margin-bottom: 30px;
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
  }
  .active {
    color: ${(props) => props.theme.yellow};
  }
`;
