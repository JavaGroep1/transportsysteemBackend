package com.joep.backofficeapi.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Order is not found")
public class OrderNotFoundException extends Exception{
}
