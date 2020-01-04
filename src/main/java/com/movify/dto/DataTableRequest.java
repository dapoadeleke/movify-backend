package com.movify.dto;

import lombok.Data;

@Data
public class DataTableRequest {
    int offset = 0;
    int limit = 10;
    String sortField = "id";
    String sortOrder = "DESC";
    String filter;
}
