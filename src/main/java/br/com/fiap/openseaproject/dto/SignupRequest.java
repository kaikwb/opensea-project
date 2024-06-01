package br.com.fiap.openseaproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record SignupRequest(
    @NotEmpty
    @Size(min = 3, max = 20)
    String username,

    @Email
    String email,

    @NotEmpty
    @Size(min = 6, max = 40)
    String password
) {
}
