package me.somefood.bimovie.repository;

import lombok.extern.slf4j.Slf4j;
import me.somefood.bimovie.entity.Movie;
import me.somefood.bimovie.entity.Poster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void testInsert() {

        Movie movie = Movie.builder().title("극학직업").build();

        Poster poster1 = Poster.builder().fname("poster1.jpg").build();
        Poster poster2 = Poster.builder().fname("poster2.jpg").build();

        movie.addPoster(poster1);
        movie.addPoster(poster2);

        movieRepository.save(movie);

        log.info("{}", movie.getMno());
    }

    @Test
    @Transactional
    @Commit
    void testAddPoster() {
        Movie movie = movieRepository.getById(1L);

        movie.addPoster(Poster.builder().fname("poster3.jpg").build());

        movieRepository.save(movie);
    }

    @Test
    @Transactional
    @Commit
    void testRemovePoster() {
        Movie movie = movieRepository.getById(1L);

        movie.removePoster(2L);

        movieRepository.save(movie);
    }

    @Test
    void insertMovies() {
        IntStream.rangeClosed(10, 100).forEach(i -> {
            Movie movie = Movie.builder().title("세게명작" + i).build();

            movie.addPoster(Poster.builder().fname("세계명작" + i + "포스터1.jpg").build());
            movie.addPoster(Poster.builder().fname("세계명작" + i + "포스터2.jpg").build());

            movieRepository.save(movie);
        });
    }

    @Test
    void testPaging1() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Movie> result = movieRepository.findAll(pageable);

        result.getContent().forEach(m -> {
            log.info("{}", m.getMno());
            log.info("{}", m.getTitle());
            log.info("{}", m.getPosterList().size());
            log.info("=====================");
        });
    }

    @Test
    void testPaging2All() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Movie> result = movieRepository.findAll2(pageable);

        result.getContent().forEach(m -> {
            log.info("{}", m.getMno());
            log.info("{}", m.getTitle());
            log.info("{}", m.getPosterList().size());
            log.info("=====================");
        });
    }

    @Test
    void testPaging3All() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Object[]> result = movieRepository.findAll3(pageable);

        result.getContent().forEach(arr -> {
            log.info(Arrays.toString(arr));

            log.info("---------------------");
        });
    }
}