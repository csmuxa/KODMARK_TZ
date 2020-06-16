package com.project.tz.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@JsonSerialize
@Getter
@Setter
@NoArgsConstructor
public class GetAllUsersDto {

    private long id;


    private String username;


    private String password;


    private String name;


}
