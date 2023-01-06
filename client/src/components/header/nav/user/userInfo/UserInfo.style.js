import styled from 'styled-components';

export const UserInfoArea = styled.div`
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100px;
  height: 100%;
  border: 1px solid #94989c;
  border-radius: 30px;
  padding: 0px 15px;
  cursor: pointer;
  :hover {
    box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.1), 0 1px 4px hsla(0, 0%, 0%, 0.1), 0 2px 8px hsla(0, 0%, 0%, 0.1);
  }
  .hambergerIcon {
    position: absolute;
    left: 18px;
    font-size: 22px;
    color: ${(props) => props.theme.gray};
  }
  .userIcon {
    position: absolute;
    right: 10px;
    font-size: 33px;
    color: ${(props) => props.theme.yellow};
  }
`;
