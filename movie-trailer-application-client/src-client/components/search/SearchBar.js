import React, { useState } from 'react';
import "./search.css"

const SearchBar = ({ handleSearch }) => {
  const [searchQuery, setSearchQuery] = useState('');

  const handleInputChange = (e) => {
    const newSearchQuery = e.target.value;
    setSearchQuery(newSearchQuery);

    // Call the search function when the input value changes
    handleSearch(newSearchQuery);
  };

  return (
    <div id='mainDivSearch'>
      <input id='inputSearch'
        type="text"
        placeholder="Search movies..."
        value={searchQuery}
        onChange={handleInputChange}
      />
    </div>
  );
};

export default SearchBar;
