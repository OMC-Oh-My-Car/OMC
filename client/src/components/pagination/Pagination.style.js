import styled from 'styled-components';

export const PaginationArea = styled.div`
  .pagination-ul {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 30px;
    margin: 40px 0;
  }
  .pageButton {
    color: rgb(173, 173, 173);
    cursor: pointer;
    &:hover {
      color: var(--color-blue);
    }
  }
  .currentPage {
    color: var(--color-blue);
    font-weight: 600;
    cursor: pointer;
  }
  .switchPage {
    color: var(--color-blue);
    cursor: pointer;
    border: 1px solid rgb(205, 205, 205);
    width: 40px;
    height: 40px;
    text-align: center;
    font-weight: 600;
    border-radius: 50%;
    padding: 10px;
    &:hover {
      background-color: var(--color-blue);
      color: var(--color-white);
    }
  }
`;
