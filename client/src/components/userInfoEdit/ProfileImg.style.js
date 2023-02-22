import styled from 'styled-components';

export const Form = styled.div`
  margin-bottom: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  .imgtemplate {
    border-radius: 50%;
    width: 300px;
    height: 300px;
    background-color: #cecece;
    margin-bottom: 10px;
  }
  .inputLabel {
    margin: 5px 0 20px 0;
    font-weight: bold;
    font-size: 13px;
    color: #0095f6;
    cursor: pointer;
  }
  .imageInput {
    display: none;
  }
`;
