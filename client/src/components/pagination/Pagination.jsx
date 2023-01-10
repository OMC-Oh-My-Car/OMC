import ReactPaginate from 'react-paginate';
import { PaginationArea } from './Pagination.style';

const Paginate = ({ itemChange }) => {
  return (
    <>
      <PaginationArea>
        <ReactPaginate
          pageCount={10}
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
