package com.project.tz.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
@JsonSerialize
public class CreateUpdateUserDto {

    private long id;


    @NotEmpty(message = "Username can not be empty.")
    private String username;


    @Size(min = 6,max=16,message = "Length must be from 6 to 16")
    @Pattern.List({
            @Pattern(regexp = "(?=.*[0-9]).+", message = "Password must contain one digit."),
            @Pattern(regexp = "(?=.*[A-Z]).+", message = "Password must contain one upper letter."),
            @Pattern(regexp = "(?=\\S+$).+", message = "Password must contain no whitespace.")
    })
    private String password;

    @NotEmpty(message = "Name can not be empty.")
    private String name;


    private ArrayList<Integer> ids;
}
