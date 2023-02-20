import { useState, useEffect } from 'react';
import { SearchInputArea } from './SearchInput.style';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { setSearch } from '../../../../redux/slice/ProductSearch';
import { useSelector, useDispatch } from 'react-redux';

const SearchInput = ({ type }) => {
  const dispatch = useDispatch();

  const [inputSearch, setInputSearch] = useState('');
  const search = useSelector((state) => state.search.q);
  console.log(search);
  return (
    <>
      <SearchInputArea type={type}>
        <input
          type="text"
          value={inputSearch}
          onChange={(e) => setInputSearch(e.target.value)}
          onKeyPress={(e) => {
            if (e.charCode === 13) {
              dispatch(setSearch(inputSearch));
            }
          }}
        />
        <div className="searchIconArea">
          <FontAwesomeIcon className="searchIcon" icon={faMagnifyingGlass} />
        </div>
      </SearchInputArea>
    </>
  );
};

export default SearchInput;
