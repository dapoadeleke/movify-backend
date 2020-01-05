package com.movify.service.impl;

import com.movify.dto.*;
import com.movify.model.*;
import com.movify.model.repository.MovieRepository;
import com.movify.service.MovieService;
import com.movify.utils.Message;
import io.ebean.PagedList;

import javax.inject.Inject;
import java.util.stream.Collectors;

public class MovieServiceImpl implements MovieService {

    @Inject
    MovieRepository movieRepository;

    @Override
    public ServiceResponse find(Long id) {
        ServiceResponse response = new ServiceResponse(Message.ERROR, Message.GENERAL_ERROR_MESSAGE);
        Movie movie = this.movieRepository.findById(id);
        if (movie == null) {
            return response.setMessage(String.format(Message.NOT_FOUND, "Movie"));
        }
        MovieDTO dto = this.generateMovieDtoFromMovie(movie);

        return response.setCode(Message.SUCCESS).setMessage(Message.GENERAL_SUCCESS_MESSAGE).setData(dto);
    }

    @Override
    public ServiceResponse findBySlug(String slug) {
        ServiceResponse response = new ServiceResponse(Message.ERROR, Message.GENERAL_ERROR_MESSAGE);
        Movie movie = this.movieRepository.findBySlug(slug);
        if (movie == null) {
            return response.setMessage(String.format(Message.NOT_FOUND, "Movie"));
        }
        MovieDTO dto = this.generateMovieDtoFromMovie(movie);

        return response.setCode(Message.SUCCESS).setMessage(Message.GENERAL_SUCCESS_MESSAGE).setData(dto);
    }

    @Override
    public ServiceResponse list(DataTableRequest req) {
        ServiceResponse res = new ServiceResponse(Message.ERROR, Message.GENERAL_ERROR_MESSAGE);

        DataTableListResponse listResponse = new DataTableListResponse();

        PagedList<Movie> data = this.movieRepository.data(req);
        listResponse.setData(data.getList().stream().map(this::generateMovieMiniDtoFromMovie).collect(Collectors.toList()));
        listResponse.setDraw(req.getOffset());
        listResponse.setLength(data.getPageSize());
        listResponse.setRecordsFiltered(data.getTotalCount());
        listResponse.setRecordsTotal(data.getTotalCount());
        res.setCode(Message.SUCCESS).setMessage(Message.GENERAL_SUCCESS_MESSAGE).setData(listResponse);
        return res;
    }

    private MovieDTO generateMovieDtoFromMovie(Movie movie) {
        MovieDTO dto  = new MovieDTO();
        dto.setId(movie.getId());
        dto.setSlug(movie.getSlug());
        dto.setTitle(movie.getTitle());
        dto.setTagline(movie.getTagline());
        dto.setOverview(movie.getOverview());
        dto.setReleaseDate(movie.getReleaseDate().toString());
        dto.setRuntime(movie.getRuntime());
        dto.setVoteAverage(movie.getVoteAverage());
        dto.setCompanies(movie.getCompanies().stream().map(Company::getName).collect(Collectors.toList()));
        dto.setCountries(movie.getCountries().stream().map(Country::getName).collect(Collectors.toList()));
        dto.setGenres(movie.getGenres().stream().map(Genre::getName).collect(Collectors.toList()));
        dto.setLanguages(movie.getLanguages().stream().map(Language::getName).collect(Collectors.toList()));

        return dto;
    }

    private MovieMiniDTO generateMovieMiniDtoFromMovie(Movie movie) {
        MovieMiniDTO dto  = new MovieMiniDTO();
        dto.setId(movie.getId());
        dto.setSlug(movie.getSlug());
        dto.setTitle(movie.getTitle());
        dto.setTagline(movie.getOverview());
        dto.setReleaseDate(movie.getReleaseDate().toString());
        dto.setVoteAverage(movie.getVoteAverage());
        dto.setGenres(movie.getGenres().stream().map(Genre::getName).collect(Collectors.toList()));
        return dto;
    }

}
