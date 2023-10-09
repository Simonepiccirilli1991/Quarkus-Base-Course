package com.quark.model.request;

import lombok.Data;

@Data
public class AddPersonRequest {

    private String name;
    private String surname;
    private String number;
}
