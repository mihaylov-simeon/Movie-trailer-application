import React, { useState, useEffect } from 'react';
import './Favorites.css';
import axiosConfig from '../../api/axiosConfig';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart } from '@fortawesome/free-solid-svg-icons';

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

  const removeFromFavorites = async (imdbId) => {
    try {
      // Remove the movie from favorites
      await axiosConfig.delete(`/remove-favorite/${imdbId}`);

      // Update the state to reflect the changes
      setFavorites((prevFavorites) => prevFavorites.filter((favorite) => favorite.imdbId !== imdbId));
    } catch (error) {
      console.error('Error removing from favorites:', error);
      // Handle error as needed
    }
  };

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
            {/* Heart icon for removing from favorites */}
            <FontAwesomeIcon
              icon={faHeart}
              className="heart-icon"
              onClick={() => removeFromFavorites(favorite.imdbId)}
            />
          </div>
        ))
      ) : (
        <p>No favorites yet.</p>
      )}
    </div>
  );
};

export default Favorites;
