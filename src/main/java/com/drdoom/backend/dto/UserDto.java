package com.drdoom.backend.dto;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    String fullName;
    String role;
}
