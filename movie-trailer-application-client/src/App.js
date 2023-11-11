import React, { useState, useEffect } from 'react';
import './App.css';
import api from './api/axiosConfig';
import { Routes, Route, useNavigate } from 'react-router-dom';
import Layout from './components/Layout';
import Home from './components/home/Home';
import Header from './components/header/Header';
import Trailer from './components/trailer/Trailer';
import Reviews from './components/reviews/Reviews';
import NotFound from './components/notFound/NotFound';
import LoginJSS from './components/login/LoginJSS';
import SearchBar from './components/search/SearchBar';
import AboutUs from './components/aboutus/AboutUs';

function App() {
  const [movies, setMovies] = useState([]);
  const [movie, setMovie] = useState();
  const [reviews, setReviews] = useState([]);
  const [searchResults, setSearchResults] = useState([]);
  const navigate = useNavigate();

  const getMovies = async () => {
    try {
      const response = await api.get('/api/movies');
      setMovies(response.data);
    } catch (err) {
      console.log(err);
    }
  }

  const getMovieData = async (movieId) => {
    try {
      const response = await api.get(`/api/movies/${movieId}`);
      const singleMovie = response.data;
      setMovie(singleMovie);
      setReviews(singleMovie.reviews);
    } catch (error) {
      console.error(error);
    }
  }

  const handleSearch = (query) => {
    const results = movies.filter((movie) =>
      movie.title.toLowerCase().includes(query.toLowerCase())
    );
    setSearchResults(results);
    navigate('/'); // Navigate back to the home page
  }

  useEffect(() => {
    getMovies();
  }, [])

  return (
    <div className="App">
      <Header />
      <SearchBar handleSearch={handleSearch} />
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route path="/" element={<Home movies={searchResults.length > 0 ? searchResults : movies} />} />
          <Route path="/Trailer/:ytTrailerId" element={<Trailer />} />
          <Route
            path="/Reviews/:movieId"
            element={
            <Reviews 
            getMovieData={getMovieData} 
            movie={movie} 
            reviews={reviews} 
            setReviews={setReviews} 
            />}
          />
          <Route path="*" element={<NotFound />} />
          <Route path="/login" element={<LoginJSS />} />
          <Route path="/aboutUs" element={<AboutUs />} />
        </Route>
      </Routes>
    </div>
  );
}

export default App;
