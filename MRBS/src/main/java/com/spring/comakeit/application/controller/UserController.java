package com.spring.comakeit.application.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.spring.comakeit.application.constants.Constants;
import com.spring.comakeit.application.constants.Status;
import com.spring.comakeit.application.entity.Login;
import com.spring.comakeit.application.entity.MeetingRequest;
import com.spring.comakeit.application.entity.MeetingRoom;
import com.spring.comakeit.application.entity.Resource;
import com.spring.comakeit.application.utility.UserGenerator;

@Controller
public class UserController {
	@RequestMapping("User")
	ModelAndView user(HttpServletRequest request) {
		String userBaseURL = Constants.baseURL + "user/";
		String operation = request.getParameter("operation");
		HttpSession session = request.getSession();
		RestTemplate restTemplate = new RestTemplate();
		Login user = (Login) session.getAttribute("user");
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m");
		ArrayList<MeetingRoom> meetingRoomList = (ArrayList<MeetingRoom>) session.getAttribute("meetingRoomList");
		ArrayList<Resource> resourceList = (ArrayList<Resource>) session.getAttribute("resourceList");
		if (operation != null) {
			if (operation.equals("requests")) {
				LocalDate startDate = LocalDate.parse(request.getParameter("startDate"), dateFormatter);
				LocalDate endDate = LocalDate.parse(request.getParameter("endDate"), dateFormatter);
				ArrayList<LocalDate> dates = UserGenerator.getDatesBetween(startDate, endDate);
				ArrayList<MeetingRequest> meetingRequests = new ArrayList<MeetingRequest>();
				for (LocalDate date : dates) {
					MeetingRequest meetingRequest = new MeetingRequest();
					meetingRequest.setDate(date);
					meetingRequest.setEndTime(LocalTime.parse(request.getParameter("endTime"), timeFormatter));
					meetingRequest.setRequestId(UserGenerator.generateID(user.getUsername()));
					meetingRequest.setResource(UserController
							.getResource(Integer.parseInt(request.getParameter("resource")), resourceList));
					meetingRequest.setStartTime(LocalTime.parse(request.getParameter("startTime"), timeFormatter));
					meetingRequest.setStatus(Status.NEW);
					meetingRequest.setUser(user);
					meetingRequest.setMeetingRoom(UserController.getMeetingRoom(
							Integer.parseInt(request.getParameter("meetingRoomNumber")), meetingRoomList));
					meetingRequest.setRequestedOn(LocalDateTime.now());
					meetingRequests.add(meetingRequest);
				}
				String sendBulkRequestURL = "sendBulkRequest/";
				restTemplate.postForObject(userBaseURL + sendBulkRequestURL, meetingRequests, ArrayList.class);
				return new ModelAndView("User?operation=view");

			} else if (operation.equals("view")) {
				String getAllRequestURL = "getAllRequest/" + user.getUsername();
				ResponseEntity<ArrayList<MeetingRequest>> response = restTemplate.exchange(
						userBaseURL + getAllRequestURL, HttpMethod.GET, null,
						new ParameterizedTypeReference<ArrayList<MeetingRequest>>() {
						});
				ArrayList<MeetingRequest> requestList = response.getBody();
				session.setAttribute("requestList", requestList);
				return new ModelAndView("User.jsp?operation=cancel");

			} else if (operation.equals("cancel")) {
				String id = request.getParameter("id");
				MeetingRequest meetingRequest = new MeetingRequest();
				meetingRequest.setRequestId(id);
				String cancelRequestURL = "cancelRequest/";
				restTemplate.put(userBaseURL + cancelRequestURL, meetingRequest);
				return new ModelAndView("User?operation=view");

			} else if (operation.equals("history")) {
				String getPastHistoryURL = "getPastHistory/" + user.getUsername();
				ResponseEntity<ArrayList<MeetingRequest>> response = restTemplate.exchange(
						userBaseURL + getPastHistoryURL, HttpMethod.GET, null,
						new ParameterizedTypeReference<ArrayList<MeetingRequest>>() {
						});
				ArrayList<MeetingRequest> historyList = response.getBody();
				session.setAttribute("historyList", historyList);
				return new ModelAndView("User.jsp?operation=history");

			} else if (operation.equals("cancelBulkRequests")) {
				String cancelBulkRequestsURL = "cancelBulkRequests/" + request.getParameter("startDate") + "/"
						+ request.getParameter("endDate");
				MeetingRequest meetingRequest = new MeetingRequest();
				meetingRequest.setEndTime(LocalTime.parse(request.getParameter("endTime"), timeFormatter));
				meetingRequest.setResource(
						UserController.getResource(Integer.parseInt(request.getParameter("resource")), resourceList));
				meetingRequest.setStartTime(LocalTime.parse(request.getParameter("startTime"), timeFormatter));
				meetingRequest.setUser(user);
				meetingRequest.setMeetingRoom(UserController
						.getMeetingRoom(Integer.parseInt(request.getParameter("meetingRoomNumber")), meetingRoomList));
				restTemplate.put(userBaseURL + cancelBulkRequestsURL, meetingRequest);
				return new ModelAndView("User?operation=view");

			}
		}
		return new ModelAndView("User.jsp");
	}

	public static Resource getResource(Integer resourceId, ArrayList<Resource> resources) {

		for (Resource resource : resources) {
			if (resource.getId() == resourceId) {
				return resource;
			}
		}
		return null;
	}

	public static MeetingRoom getMeetingRoom(Integer resourceId, ArrayList<MeetingRoom> meetingRooms) {

		for (MeetingRoom meetingRoom : meetingRooms) {
			if (meetingRoom.getId() == resourceId) {
				return meetingRoom;
			}
		}
		return null;
	}
}
