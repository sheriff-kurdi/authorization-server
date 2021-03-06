package com.kurdi.authorizationserver.requests;

import com.kurdi.authorizationserver.entities.Authority;
import lombok.Data;

import java.util.Set;

@Data
public class AddAuthoritiesRequest {
    int userId;
    Set<Authority> authorities;
}
