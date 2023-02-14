import styled from 'styled-components';

export const UserInfoArea = styled.button`
  position: relative;
  display: flex;
  background-color: white;
  justify-content: center;
  align-items: center;
  width: 100px;
  height: 100%;
  border: 1px solid #94989c;
  border-radius: 30px;
  padding: 0px 15px;
  cursor: pointer;
  color: ${(props) => props.theme.gray};
  box-shadow: ${(props) =>
    props.isOpen && '0 1px 2px hsla(0, 0%, 0%, 0.1), 0 1px 4px hsla(0, 0%, 0%, 0.1), 0 2px 8px hsla(0, 0%, 0%, 0.1)'};
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
  img {
    position: absolute;
    right: 10px;
    width: 33px;
    height: 33px;
    border-radius: 50%;
  }
`;
