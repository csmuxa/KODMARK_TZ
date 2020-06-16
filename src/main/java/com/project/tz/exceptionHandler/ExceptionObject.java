package com.project.tz.exceptionHandler;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.FieldError;

import java.lang.reflect.Field;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionObject {

    private boolean success;

    private List<String> errors;

}
