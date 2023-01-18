import styled from 'styled-components';

export const UserReservationListArea = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 10px 0px 30px;
  h1 {
    font-size: 27px;
    font-weight: 700;
    border-bottom: 1px solid ${(props1) => props1.theme.gray};
    padding-bottom: 30px;
    margin-bottom: 30px;
  }
  .reservationList {
    width: 100%;
    display: grid;
    gap: 25px;
    grid-template-columns: repeat(4, 1fr);
    /* vw 길이 - padding 길이 - gap 길이* + 텍스트 길이 */
    /* grid-auto-rows: minmax(auto, 400px); */
  }
  @media (max-width: 1180px) {
    .reservationList {
      grid-template-columns: repeat(3, 1fr);
      /* vw 길이 - padding 길이 - gap 길이) / 컬럼 갯수 + 텍스트 길이 */
    }
  }
  @media (max-width: 950px) {
    .reservationList {
      grid-template-columns: repeat(2, 1fr);
      /* vw 길이 - padding 길이 - gap 길이) / 컬럼 갯수 + 텍스트 길이 */
    }
  }
  @media (max-width: 550px) {
    .reservationList {
      grid-template-columns: repeat(1, 1fr);
      /* vw 길이 - padding 길이 - gap 길이) / 컬럼 갯수 + 텍스트 길이 */
    }
  }
`;
