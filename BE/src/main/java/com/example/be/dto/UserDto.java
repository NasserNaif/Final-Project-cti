package com.example.be.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Serializable {

    private static final long serialVersionUID = 7932290403882937946L;

    private Integer id;

    private String email;

    private String firstName;

    private String lastName;

    private String  roles;

    private String token;

    public String getName() {
        return firstName + " " + lastName;
    }

}