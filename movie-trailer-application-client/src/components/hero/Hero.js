import React, { useState } from 'react';
import './Hero.css';
import Carousel from 'react-material-ui-carousel';
import { Paper } from '@mui/material';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCirclePlay, faHeart } from '@fortawesome/free-solid-svg-icons';
import { Link, useNavigate } from 'react-router-dom';
import Button from 'react-bootstrap/Button';
import { useAuth } from '../authentication/AuthProvider';
import useFavorites from '../favorites/useFavorites';
import axiosConfig from '../../api/axiosConfig';

const Hero = ({ movies }) => {
  const navigate = useNavigate();
  const { isLoggedIn } = useAuth();
  const { favorites, error } = useFavorites(); // Use the custom hook
  const [isGridView, setIsGridView] = useState(false);

  const toggleViewMode = () => {
    setIsGridView((prev) => !prev);
  };

  const addToFavorites = async (movie) => {
    try {``
      if (!isLoggedIn) {
        navigate('/login');
        return;
      }

      // Check if the movie is already in favorites
      const isMovieInFavorites = favorites.some((favorite) => favorite.imdbId === movie.imdbId);

      if (isMovieInFavorites) {
        // Display an alert if the movie is already in favorites
        alert('This movie is already in your list of favorite movies.');
      } else {
        // Proceed with adding the movie to favorites
        console.log('Adding to favorites:', movie);
        const response = await axiosConfig.post('/add-favorite', {
          imdbId: movie.imdbId,
          title: movie.title,
          poster: movie.poster,
        });

        // Display an alert for successful addition
        alert('Movie added to favorites!');
        console.log('Movie added to favorites:', response.data);
      }
    } catch (error) {
      console.error('Error adding movie to favorites:', error);
    }
  };

  function reviews(movieId) {
    navigate(`Reviews/${movieId}`);
  }

  return (
    <div className="movie-carousel-container">
      <div className="view-toggle-button-container">
        <Button variant="outline-secondary" onClick={toggleViewMode}>
          {isGridView ? 'Switch to Carousel' : 'Switch to Grid View'}
        </Button>
      </div>

      {isGridView ? (
        <div className="movie-grid-container">
          {movies?.map((movie) => (
            <div key={movie.imdbId} className="movie-grid-item">
              <img src={movie.poster} alt={movie.title} />
              <h4>{movie.title}</h4>
              {isLoggedIn && (
                <FontAwesomeIcon
                  icon={faHeart}
                  className="heart-icon"
                  onClick={() => addToFavorites(movie)}
                />
              )}
            </div>
          ))}
        </div>
      ) : (
        <Carousel>
          {movies?.map((movie) => (
            <Paper key={movie.imdbId}>
              <div className="movie-card-container">
                <div className="movie-card" style={{ '--img': `url(${movie.backdrops[0]})` }}>
                  <div className="movie-detail">
                    <div className="movie-poster">
                      <img src={movie.poster} alt={movie.title} />
                    </div>
                    <div className="movie-title">
                      <h4>{movie.title}</h4>
                    </div>
                    <div className="movie-buttons-container">
                      <Link to={`Trailer/${movie.trailerLink.substring(movie.trailerLink.length - 11)}`}>
                        <div className="play-button-icon-container">
                          <FontAwesomeIcon className="play-button-icon" icon={faCirclePlay} />
                        </div>
                      </Link>
                      {isLoggedIn && (
                        <FontAwesomeIcon
                          icon={faHeart}
                          className="heart-icon"
                          onClick={() => addToFavorites(movie)}
                        />
                      )}
                      <div className="movie-review-button-container">
                        <Button variant="info" onClick={() => reviews(movie.imdbId)}>
                          Reviews
                        </Button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </Paper>
          ))}
        </Carousel>
      )}
    </div>
  );
};


export default Hero;
