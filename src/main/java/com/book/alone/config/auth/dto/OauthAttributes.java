package com.book.alone.config.auth.dto;

import java.util.Map;

import com.book.alone.domain.Role;
import com.book.alone.domain.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OauthAttributes {

	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String name;
	private String email;
	private String picture;

	@Builder
	public OauthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email,
		String picture) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.picture = picture;
	}

	public static OauthAttributes of (String registrationId, String userNameAttributeName,
		Map<String, Object> attributes) {

		if ("naver".equals(registrationId)) {
			return ofNaver("id", attributes);
		}

		return ofGoogle(userNameAttributeName, attributes);
	}

	private static OauthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
		Map<String, Object> response = (Map<String, Object>)attributes.get("response");

		return OauthAttributes.builder()
			.name(String.valueOf(response.get("name")))
			.email(String.valueOf(response.get("email")))
			.picture(String.valueOf(response.get("profile_image")))
			.attributes(response)
			.nameAttributeKey(userNameAttributeName)
			.build();
	}

	private static OauthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
		return OauthAttributes.builder()
			.name(String.valueOf(attributes.get("name")))
			.email(String.valueOf(attributes.get("email")))
			.picture(String.valueOf(attributes.get("picture")))
			.attributes(attributes)
			.nameAttributeKey(userNameAttributeName)
			.build();
	}

	public User toEntity() {
		return User.builder()
			.name(name)
			.email(email)
			.picture(picture)
			.role(Role.GUEST)
			.build();
	}
}
