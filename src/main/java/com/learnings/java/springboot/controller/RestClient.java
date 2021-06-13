package com.learnings.java.springboot.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.learnings.java.springboot.model.User;

public class RestClient {
	
	private static final String GET_ALL_USERS_API = "http://localhost:8080/users";
	private static final String GET_USER_BY_ID_API = "http://localhost:8080/users/{id}";
	private static final String CREATE_USER_API = "http://localhost:8080/users";
	private static final String UPDATE_USER_API = "http://localhost:8080/users/{id}";
	private static final String DELETE_USER_API = "http://localhost:8080/users/{id}";

	static RestTemplate restTemplate = new RestTemplate();
	public static void main(String[] args) {
		//callGetAllUsersAPI();
		//callGetUserByIdAPI();
		//callCreateUserAPI();
		//callUpdateUserAPI();
		callDeleteUserAPI();
		
	}
	
	private static void callGetAllUsersAPI() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity =new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> result = restTemplate.exchange(GET_ALL_USERS_API, HttpMethod.GET, entity, String.class);
		System.out.println(result);
		
	}
	private static void callGetUserByIdAPI() {
		
		Map<String, Integer> map = new HashMap<>();
		map.put("id", 2);
		
		User user=restTemplate.getForObject(GET_USER_BY_ID_API, User.class, map);
		System.out.println(user.getFirstName());
		System.out.println(user.getLastName());
		System.out.println(user.getEmailId());
		
	}
	private static void callCreateUserAPI() {
		User user = new User("del", "del","del@gmail.com");
		
		ResponseEntity<User> savedUser = restTemplate.postForEntity(CREATE_USER_API, user, User.class);
		
		System.out.println(savedUser);
	}
	private static void callUpdateUserAPI() {
		Map<String, Integer> map = new HashMap<>();
		map.put("id",8);
		
		User user = new User("delete","delete","delete@gmail.com");
		restTemplate.put(UPDATE_USER_API,user,map);
	}
	private static void callDeleteUserAPI() {
		Map<String, Integer> map = new HashMap<>();
		map.put("id",8);
		restTemplate.delete(DELETE_USER_API, map);
	}
	
	
}
