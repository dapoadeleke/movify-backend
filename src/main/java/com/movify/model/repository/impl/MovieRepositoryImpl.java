package com.movify.model.repository.impl;

import com.movify.dto.DataTableRequest;
import com.movify.model.Movie;
import com.movify.model.User;
import com.movify.model.repository.MovieRepository;
import com.movify.utils.Utility;
import io.ebean.EbeanServer;
import io.ebean.ExpressionList;
import io.ebean.PagedList;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.util.stream.Collectors;

public class MovieRepositoryImpl implements MovieRepository {

    @Inject
    EbeanServer store;

    @Override
    public Movie findById(Long Id) {
        return this.store.find(Movie.class).where().idEq(Id).findOne();
    }

    @Override
    public Movie findBySlug(String slug) {
        return this.store.find(Movie.class).where().eq("slug", slug).findOne();
    }

    @Override
    public PagedList<Movie> data(DataTableRequest req) {
        ExpressionList<Movie> expressionList = this.store.find(Movie.class).where();

        if(req.getFilter() != null && StringUtils.isNotBlank(req.getFilter())) {
            expressionList = expressionList
                    .or().ilike("title", "%" + req.getFilter() + "%");
//                    .or().ilike("tagline", "%" + req.getFilter() + "%");
        }

        if(req.getSortField() != null && StringUtils.isNotBlank(req.getSortField())) {
            if (req.getSortOrder().equals("ASC")) {
                expressionList.order().asc(req.getSortField());
            }  else {
                expressionList.order().desc(req.getSortField());
            }
        }

        expressionList.setFirstRow(req.getOffset()).setMaxRows(req.getLimit());

        return expressionList.findPagedList();
    }
}
