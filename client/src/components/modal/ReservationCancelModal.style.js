import styled from 'styled-components';

export const ReservationCancelModalArea = styled.div`
  width: 100%;
  height: 90%;
  overflow-y: scroll;
  flex-direction: column;
  padding-right: 25px;
  h2 {
    font-size: 24px;
    font-weight: 700;
    margin-bottom: 13px;
  }
  span {
    width: 100%;
    font-weight: 300;
    line-height: 24px;
    word-break: break-word;
  }
  .explain {
    width: 100%;
    display: inline-block;
    color: ${(props) => props.theme.red};
    margin-bottom: 20px;
    font-size: 19px;
  }
`;
