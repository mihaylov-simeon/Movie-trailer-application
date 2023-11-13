import React, { useState, useEffect } from 'react';
import './Favorites.css';
import axiosConfig from '../../api/axiosConfig';

const Favorites = () => {
  const [favorites, setFavorites] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchFavorites = async () => {
      try {
        const response = await axiosConfig.get('/favorites');
        setFavorites(response.data);
      } catch (error) {
        console.error('Error fetching favorites:', error);
        setError(error.message || 'An error occurred while fetching favorites.');
      }
    };

    fetchFavorites();
  }, []);

  if (error) {
    return <p>Error: {error}</p>;
  }

  return (
    <div className="favorites-container">
      {favorites.length > 0 ? (
        favorites?.map((favorite) => (
          <div key={favorite.imdbId} className="favorite-grid-item">
            <img src={favorite.poster} alt={favorite.title} />
            <h4>{favorite.title}</h4>
          </div>
        ))
      ) : (
        <p>No favorites yet.</p>
      )}
    </div>
  );
};

export default Favorites;
