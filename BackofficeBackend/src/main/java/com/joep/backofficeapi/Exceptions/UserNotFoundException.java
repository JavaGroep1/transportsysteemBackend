package com.joep.backofficeapi.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Username/Password combination not found")
public class UserNotFoundException  extends Exception{
}
