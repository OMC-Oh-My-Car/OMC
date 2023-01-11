import styled from 'styled-components';

export const NavArea = styled.nav`
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 35px;
  padding: ${(props) => (props.type === 'short' ? '10px 80px' : '10px 100px')};
  width: 100%;
  max-width: ${(props) => (props.type === 'short' ? '1320px' : '100%')};
  height: 100%;
  @media (max-width: 1128px) {
    padding: ${(props) => (props.type === 'short' ? '10px 40px' : '10px 50px')};
  }
  /*모바일 */
  @media (max-width: 744px) {
    padding: ${(props) => (props.type === 'short' ? '10px 25px' : '10px 25px')};
  }
`;
