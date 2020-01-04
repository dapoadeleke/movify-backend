package com.movify.service;

import com.movify.dto.DataTableRequest;
import com.movify.dto.ServiceResponse;
import com.movify.dto.UserCreationRequest;

public interface MovieService {

    ServiceResponse find(Long id);

    ServiceResponse findBySlug(String slug);

    ServiceResponse list(DataTableRequest req);

}
