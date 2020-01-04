package com.movify.dto;

import lombok.Data;

import java.util.List;

@Data
public class DataTableListResponse {
    private List<?> data;
    private int draw;//the NO.of requests
    private int length;
    private long recordsTotal;
    private long recordsFiltered;
}
