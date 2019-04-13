package com.simbirsoft.itplace.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Denis on 13.04.2019.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Element not found")  // 404
public class ElementNotFoundException extends RuntimeException {
}
