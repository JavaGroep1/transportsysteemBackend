package com.joep.backofficeapi.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "This order is not valid, please try again")
public class OrderInvalidException extends Exception {
}
