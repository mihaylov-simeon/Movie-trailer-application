package com.moveiapp.final_exam_projectmovieapplication;

import com.moveiapp.final_exam_projectmovieapplication.model.entities.FavoriteMovie;
import com.moveiapp.final_exam_projectmovieapplication.model.entities.User;
import com.moveiapp.final_exam_projectmovieapplication.repositories.FavoriteMovieRepository;
import com.moveiapp.final_exam_projectmovieapplication.repositories.UserRepository;
import com.moveiapp.final_exam_projectmovieapplication.service.LoggedUser;
import com.moveiapp.final_exam_projectmovieapplication.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
class RemoveFavoriteMovieTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private LoggedUser loggedUser;

    @Mock
    private FavoriteMovieRepository favoriteMovieRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void removeFavoriteMovieTest() {
        when(loggedUser.getEmail()).thenReturn("test@example.com");

        User mockUser = new User();
        mockUser.setEmail("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(mockUser);

        String imdbId = "tt123456";

        FavoriteMovie mockFavoriteMovie = new FavoriteMovie();
        mockFavoriteMovie.setUser(mockUser);
        mockFavoriteMovie.setImdbId(imdbId);
        when(favoriteMovieRepository.findByImdbIdAndUser(imdbId, mockUser)).thenReturn(mockFavoriteMovie);

        userService.removeFavoriteMovie(imdbId);

        verify(favoriteMovieRepository, times(1)).delete(mockFavoriteMovie);
    }
}
