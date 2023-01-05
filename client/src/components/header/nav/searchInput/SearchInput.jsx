// import React from 'react';
import { SearchInputArea } from './SearchInput.styled';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';

const SearchInput = ({ type }) => {
  return (
    <>
      <SearchInputArea type={type}>
        <input type="text" />
        <div className="searchIconArea">
          <FontAwesomeIcon className="searchIcon" icon={faMagnifyingGlass} />
        </div>
      </SearchInputArea>
    </>
  );
};

export default SearchInput;
