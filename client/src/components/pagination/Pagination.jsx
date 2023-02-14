import ReactPaginate from 'react-paginate';
import { PaginationArea } from './Pagination.style';

const Paginate = ({ data, itemChange }) => {
  return (
    <>
      <PaginationArea>
        <ReactPaginate
          pageCount={data ? data.data.pageInfo.page : 5}
          pageRangeDisplayed={6}
          marginPagesDisplayed={0}
          breakLabel={''}
          previousLabel={'<'}
          nextLabel={'>'}
          onPageChange={itemChange}
          containerClassName={'pagination-ul'}
          pageClassName={'pageButton'}
          activeClassName={'currentPage'}
          previousClassName={'switchPage'}
          nextClassName={'switchPage'}
        />
      </PaginationArea>
    </>
  );
};

export default Paginate;
