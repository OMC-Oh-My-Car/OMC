import styled from 'styled-components';

export const NavArea = styled.nav`
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 35px;
  padding: ${(props) => (props.type === 'short' ? '10px 300px' : '10px 100px')};
  width: 100%;
  height: 100%;
  @media (max-width: 1425px) {
    padding: ${(props) => (props.type === 'short' ? '10px 300px' : '10px 50px')};
  }
  /*모바일 */
  @media (max-width: 744px) {
    padding: ${(props) => (props.type === 'short' ? '10px 300px' : '10px 25px')};
  }
`;
