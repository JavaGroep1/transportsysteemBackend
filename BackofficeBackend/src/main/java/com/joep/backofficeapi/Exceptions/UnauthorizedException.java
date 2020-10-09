package com.joep.backofficeapi.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "You do not have the required permissions to view this data")
public class UnauthorizedException extends Exception{
}
