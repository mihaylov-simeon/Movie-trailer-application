// MemoizedDropdownItem.jsx
import React, { memo } from 'react';
import Dropdown from 'react-bootstrap/Dropdown';

const MemoizedDropdownItem = memo(({ genre, onClick }) => {
  const handleItemClick = () => {
    onClick(genre);
  };

  return <Dropdown.Item onClick={handleItemClick}>{genre}</Dropdown.Item>;
}, (prevProps, nextProps) => {
  return prevProps.genre === nextProps.genre;
});

export default MemoizedDropdownItem;
