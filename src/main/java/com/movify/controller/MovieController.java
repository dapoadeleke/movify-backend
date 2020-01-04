package com.movify.controller;

import com.movify.dto.DataTableRequest;
import com.movify.dto.ServiceResponse;
import com.movify.service.MovieService;
import com.movify.utils.Utility;
import org.jooby.mvc.Body;
import org.jooby.mvc.GET;
import org.jooby.mvc.POST;
import org.jooby.mvc.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 *
 * Everything about Movies.
 */
@Path("/api/v1/internal/movies")
public class MovieController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @Inject
    MovieService movieService;

    @Inject
    Utility utility;

    @GET
    @Path("/{id}")
    public ServiceResponse find(Long id) {
        return this.movieService.find(id);
    }

    @GET
    @Path("/slug/{slug}")
    public ServiceResponse findBySlug(String slug) {
        return this.movieService.findBySlug(slug);
    }

    @GET
    @Path("")
    public ServiceResponse list(DataTableRequest req) {
        this.utility.validateRequest(req);
        return this.movieService.list(req);
    }

}
