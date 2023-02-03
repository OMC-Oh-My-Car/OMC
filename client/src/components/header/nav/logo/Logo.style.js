import styled from 'styled-components';

export const LogoArea = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  cursor: pointer;
  .logoIcon {
    width: 48px;
    margin-right: 10px;
  }
  .logo {
    font-size: 30px;
    font-weight: 700;
    color: ${(props) => props.theme.red};
  }
  @media (max-width: 744px) {
    .logo {
      display: none;
    }
    .logoIcon {
      margin-right: 0px;
    }
  }
  @media (max-width: 540px) {
    .logoIcon {
      display: none;
    }
  }
`;
