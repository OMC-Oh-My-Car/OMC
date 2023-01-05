import styled from 'styled-components';

export const LogoArea = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 5px;
  height: 100%;
  cursor: pointer;
  .logoIcon {
    width: 48px;
  }
  .logo {
    font-size: 30px;
    font-weight: 700;
    color: ${(props) => props.theme.red};
  }
`;
