import { useState, useEffect } from 'react';
import axiosConfig from '../../api/axiosConfig';

const useFavorites = () => {
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

  return { favorites, error };
};

export default useFavorites;
