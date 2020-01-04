package com.movify.dto;

import com.movify.enums.Message;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Setter
@Getter
public class ServiceResponse {
    int code;
    String message;
    Object data;

    public ServiceResponse(){}

    public ServiceResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ServiceResponse newErrorResponse(String message){
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setCode(Message.ERROR);
        serviceResponse.setMessage(message);
        return serviceResponse;
    }

    public static ServiceResponse newSuccessResponse(String message){
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setCode(Message.SUCCESS);
        serviceResponse.setMessage(message);
        return serviceResponse;
    }

}
