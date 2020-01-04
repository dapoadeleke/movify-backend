package com.movify.service.impl;

import com.movify.dto.DataTableRequest;
import com.movify.dto.MovieDTO;
import com.movify.dto.ServiceResponse;
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
//        if(req.getDepartmentId() != null) {
//            if (this.departmentRepository.findById(req.getDepartmentId()) == null) {
//                return res.setMessage(String.format(Message.NOT_FOUND, "Department"));
//            }
//        }
//
//        if(req.getWorkgroupId() != null) {
//            if (this.workgroupRepository.findById(req.getWorkgroupId()) == null) {
//                return res.setMessage(String.format(Message.NOT_FOUND, "Workgroup"));
//            }
//        }
//
//        if(req.getRoleId() != null) {
//            if (this.roleRepository.findById(req.getRoleId()) == null) {
//                return res.setMessage(String.format(Message.NOT_FOUND, "Role"));
//            }
//        }
//
//        req.setCompanyId(initiator.getCompany().getId());
//
//        DataTableListResponse listResponse = new DataTableListResponse();
//
//        PagedList<Customer> data = this.customerRepository.data(req);
//        listResponse.setData(data.getList().stream().map(this::generateCustomerMiniDTO).collect(Collectors.toList()));
//        listResponse.setDraw(req.getOffset());
//        listResponse.setLength(data.getPageSize());
//        listResponse.setRecordsFiltered(data.getTotalCount());
//        listResponse.setRecordsTotal(data.getTotalCount());
//        res.setCode(Message.SUCCESS).setMessage(Message.GENERAL_SUCCESS_MESSAGE).setData(listResponse);
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
        dto.setVoteCount(movie.getVoteCount());
        dto.setCompanies(movie.getCompanies().stream().map(Company::getName).collect(Collectors.toList()));
        dto.setCountries(movie.getCountries().stream().map(Country::getName).collect(Collectors.toList()));
        dto.setGenres(movie.getGenres().stream().map(Genre::getName).collect(Collectors.toList()));
        dto.setLanguages(movie.getLanguages().stream().map(Language::getName).collect(Collectors.toList()));

        return dto;
    }

}
