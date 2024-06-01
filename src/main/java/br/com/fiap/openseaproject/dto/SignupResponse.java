package br.com.fiap.openseaproject.dto;

import br.com.fiap.openseaproject.models.User;

public record SignupResponse(
    Long id,
    String username,
    String email
) {
    public static SignupResponse fromUser(User user) {
        return new SignupResponse(user.getId(), user.getUsername(), user.getEmail());
    }
}
