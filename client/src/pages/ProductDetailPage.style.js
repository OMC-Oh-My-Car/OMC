import styled from 'styled-components';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
`;
export const MainContainer = styled.main`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  width: 100%;
  max-width: 1320px;
  padding: 50px 80px;
  @media (max-width: 1128px) {
    padding: 50px 40px;
  }
  /*모바일 */
  @media (max-width: 744px) {
    padding: 20px 25px;
  }
`;
