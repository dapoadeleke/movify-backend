package com.movify.model.repository;

import com.movify.dto.DataTableRequest;
import com.movify.model.Movie;
import com.movify.model.User;
import io.ebean.PagedList;

public interface MovieRepository {

    Movie findById(Long Id);

    Movie findBySlug(String slug);

    PagedList<Movie> data(DataTableRequest req);

}
