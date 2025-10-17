package com.gd.storeapi.dto;

import lombok.Data;

@Data
public abstract class ErrorResponse {

    protected String message;
}
