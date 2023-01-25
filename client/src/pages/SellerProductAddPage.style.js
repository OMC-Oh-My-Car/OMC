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
  align-items: center;
  width: 100%;
  max-width: 1320px;
  padding: 50px 80px;
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
