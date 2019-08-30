package com.spring.comakeit.application.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.spring.comakeit.application.constants.Constants;
import com.spring.comakeit.application.entity.Login;
import com.spring.comakeit.application.entity.MeetingRoom;
import com.spring.comakeit.application.entity.Resource;

@Controller
public class LoginController {
	
	
	@RequestMapping("/Login")
	@PostMapping
	ModelAndView authenticate(HttpServletRequest request, Model model) {
		String baseURL = Constants.baseURL;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		RestTemplate restTemplate = new RestTemplate();
		Login user = new Login();
		user.setUsername(username);
		user.setPassword(password);
		System.out.println(baseURL);
		user = restTemplate.postForObject(baseURL + "login/authN", user, Login.class);
		HttpSession session = request.getSession();

		String meetingRoomListURL = "user/getMeetingRoomList";
		ResponseEntity<ArrayList<MeetingRoom>> meetingRoomListRespose = restTemplate.exchange(
				baseURL + meetingRoomListURL, HttpMethod.GET, null,
				new ParameterizedTypeReference<ArrayList<MeetingRoom>>() {
				});
		ArrayList<MeetingRoom> meetingRoomList = meetingRoomListRespose.getBody();
		String resourceListURL = "user/getResourceList";
		ResponseEntity<ArrayList<Resource>> resourceListRespose = restTemplate.exchange(baseURL + resourceListURL,
				HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Resource>>() {
				});

		ArrayList<Resource> resourceList = resourceListRespose.getBody();
		session.setAttribute("meetingRoomList", meetingRoomList);
		session.setAttribute("resourceList", resourceList);

		if (user != null) {
			session.setAttribute("user", user);
			if (user.getRole().equals("FM")) {
				return new ModelAndView("forward:FacilityManager.jsp");
			} else if (user.getRole().equals("user")) {
				return new ModelAndView("forward:" + "User.jsp");

			}
		}
		return new ModelAndView("redirect:index.jsp?operation=error");

	}
}
