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
import { Dropdown } from 'react-bootstrap';
import MemoizedDropdownItem from './MemorizedDropdownItem';

const Hero = ({ movies }) => {
  const navigate = useNavigate();
  const { isLoggedIn } = useAuth();
  const { favorites, setFavorites, error, updateFavorites } = useFavorites();
  const [isGridView, setIsGridView] = useState(false);
  const [selectedGenre, setSelectedGenre] = useState('All');

  const toggleViewMode = () => {
    setIsGridView((prev) => !prev);
  };

  const handleGenreSelect = (genre) => {
    console.log("Selected genre: ", genre)
    setSelectedGenre(genre);
  };

  const filterMoviesByGenre = () => {
    if (selectedGenre === 'All') {
      return movies;
    } else {
      console.log('Selected Genre:', selectedGenre);
      const filteredMovies = movies.filter((movie) => movie.genres.includes(selectedGenre));
      console.log('Filtered Movies:', filteredMovies);
      return filteredMovies;
    }
  };

  const addToFavorites = async (movie) => {
    try {
      if (!isLoggedIn) {
        navigate('/login');
        return;
      }
      const isMovieInFavorites = favorites.some((favorite) => favorite.imdbId === movie.imdbId);

      if (isMovieInFavorites) {
        await axiosConfig.delete(`/remove-favorite/${movie.imdbId}`);
        console.log('Movie removed from favorites:', movie.imdbId);
        alert('Movie removed from favorites!');
      } else {
        const response = await axiosConfig.post('/add-favorite', {
          imdbId: movie.imdbId,
          title: movie.title,
          poster: movie.poster,
        });

        setFavorites((prevFavorites) => [...prevFavorites, response.data]);
        updateFavorites();
        console.log('Movie added to favorites:', response.data);
      }
    } catch (error) {
      console.error('Error updating favorites:', error);
    }
  };

  function reviews(movieId) {
    navigate(`Reviews/${movieId}`);
  }

  const genres = ['All', 'Action', 'Comedy', 'Adventure', 'Fantasy', 'Family', 'Science Fiction', 'Horror', 'Drama'];

  return (
    <div className="movie-carousel-container">
      <div className="view-toggle-button-container">
        <Button variant="outline-secondary" onClick={toggleViewMode}>
          {isGridView ? 'Switch to Carousel' : 'Switch to Grid View'}
        </Button>
        <Dropdown>
            <Dropdown.Toggle variant="outline-secondary" id="dropdown-basic">
              Filter by Genre: {selectedGenre}
            </Dropdown.Toggle>

            <Dropdown.Menu>
              {genres.map((genre) => (
                <MemoizedDropdownItem key={genre} onClick={handleGenreSelect} genre={genre} />
              ))}
        </Dropdown.Menu>
      </Dropdown>
      </div>


      {isGridView ? (
        <div className="movie-grid-container">
          {filterMoviesByGenre().map((movie) => (
            <div key={movie.imdbId} className="movie-grid-item">
              <img src={movie.poster} alt={movie.title} />
              <h4>{movie.title}</h4>
              {isLoggedIn && (
                <FontAwesomeIcon
                  icon={faHeart}
                  className="heart-icon"
                  onClick={() => addToFavorites(movie)}
                  style={{
                    color: isLoggedIn && favorites.some((favorite) => favorite.imdbId === movie.imdbId)
                      ? 'red'
                      : '#ffffff',
                  }}
                />
              )}
            </div>
          ))}
        </div>
      ) : (
        <Carousel>
          {filterMoviesByGenre().map((movie) => (
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
                          style={{
                            color: isLoggedIn && favorites.some((favorite) => favorite.imdbId === movie.imdbId)
                              ? 'red'
                              : '#ffffff',
                          }}
                        />
                      )}
                      <div className="movie-review-button-container">
                        {isLoggedIn && (
                          <Button variant="info" onClick={() => reviews(movie.imdbId)}>
                            Reviews
                          </Button>
                        )}
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
