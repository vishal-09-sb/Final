package com.movie.book.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.book.model.Movie;

@Repository
@Transactional 
public interface MovieRepository extends JpaRepository<Movie, Integer> {

	Optional <Movie> findByMovieName(String movieName);


}
