package com.quark.model.request;

import lombok.Data;

public record UpdatePersonRequest(String surname,String name, String number){}
