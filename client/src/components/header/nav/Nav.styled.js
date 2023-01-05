import styled from 'styled-components';

export const NavArea = styled.nav`
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 15px;
  width: 100%;
  height: 100%;
  max-width: ${(props) => (props.type === 'short' ? '1280px' : '1600px')};
  padding: 10px;
  border: 1px solid black;
`;
