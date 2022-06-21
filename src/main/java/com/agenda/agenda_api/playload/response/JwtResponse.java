package com.agenda.agenda_api.playload.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String login;
	private String email;
	private List<String> roles;

	public JwtResponse(String accessToken, Long id, String login, String email, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.login = login;
		this.email = email;
		this.roles = roles;
	}

}
