package com.spring.comakeit.application.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
public class FacilityManagerController {
	@RequestMapping("FacilityManager")
	ModelAndView user(HttpServletRequest request) {
		String facilityManagerBaseURL = Constants.baseURL + "facilityManager/";
		String operation = request.getParameter("operation");
		HttpSession session = request.getSession();
		ArrayList<MeetingRoom> meetingRoomList = (ArrayList<MeetingRoom>) session.getAttribute("meetingRoomList");
		ArrayList<Resource> resourceList = (ArrayList<Resource>) session.getAttribute("resourceList");
		RestTemplate restTemplate = new RestTemplate();
		if (operation != null) {
			if (operation.equals("viewUsers")) {
				String viewUsersURL = "viewUsers/";
				ResponseEntity<ArrayList<Login>> response = restTemplate.exchange(facilityManagerBaseURL + viewUsersURL,
						HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Login>>() {
						});
				ArrayList<Login> users = response.getBody();
				session.setAttribute("viewUsers", users);
				return new ModelAndView("FacilityManager.jsp?operation=viewUsers");

			} else if (operation.equals("getAllPendingRequest")) {
				String getAllPendingRequestURL = "getAllPendingRequest/";
				ResponseEntity<ArrayList<MeetingRequest>> response = restTemplate.exchange(
						facilityManagerBaseURL + getAllPendingRequestURL, HttpMethod.GET, null,
						new ParameterizedTypeReference<ArrayList<MeetingRequest>>() {
						});
				ArrayList<MeetingRequest> requests = response.getBody();
				session.setAttribute("getAllPendingRequest", requests);
				return new ModelAndView("FacilityManager.jsp?operation=getAllPendingRequest");

			} else if (operation.equals("getAllRequest")) {
				String getAllRequestURL = "getAllRequest/";
				ResponseEntity<ArrayList<MeetingRequest>> response = restTemplate.exchange(
						facilityManagerBaseURL + getAllRequestURL, HttpMethod.GET, null,
						new ParameterizedTypeReference<ArrayList<MeetingRequest>>() {
						});
				ArrayList<MeetingRequest> requests = response.getBody();
				session.setAttribute("getAllRequest", requests);
				return new ModelAndView("FacilityManager.jsp?operation=getAllRequest");

			} else if (operation.equals("getAllRequestForGivenDay")) {
				String date = request.getParameter("date");
				String getAllRequestForGivenDayURL = "getAllRequestForGivenDay/" + date;
				ResponseEntity<ArrayList<MeetingRequest>> response = restTemplate.exchange(
						facilityManagerBaseURL + getAllRequestForGivenDayURL, HttpMethod.GET, null,
						new ParameterizedTypeReference<ArrayList<MeetingRequest>>() {
						});
				ArrayList<MeetingRequest> requests = response.getBody();
				session.setAttribute("getAllRequestForGivenDay", requests);
				return new ModelAndView("FacilityManager.jsp?operation=allRequestForGivenDay");

			} else if (operation.equals("getMRFrequency")) {
				String getMRFrequencyURL = "getMRFrequency/";
				ResponseEntity<ArrayList<String>> response = restTemplate.exchange(
						facilityManagerBaseURL + getMRFrequencyURL, HttpMethod.GET, null,
						new ParameterizedTypeReference<ArrayList<String>>() {
						});
				ArrayList<String> frequencyList = response.getBody();
				session.setAttribute("getMRFrequency", frequencyList);
				return new ModelAndView("FacilityManager.jsp?operation=getMRFrequency");

			} else if (operation.equals("getResourceFrequency")) {
				String getResourceFrequencyURL = "getResourceFrequency/";
				ResponseEntity<ArrayList<String>> response = restTemplate.exchange(
						facilityManagerBaseURL + getResourceFrequencyURL, HttpMethod.GET, null,
						new ParameterizedTypeReference<ArrayList<String>>() {
						});
				ArrayList<String> frequencyList = response.getBody();
				session.setAttribute("getResourceFrequency", frequencyList);
				return new ModelAndView("FacilityManager.jsp?operation=getResourceFrequency");

			} else if (operation.equals("mostResourceUsed")) {
				String mostResourceUsedURL = "mostResourceUsed/";
				ResponseEntity<String> response = restTemplate.exchange(facilityManagerBaseURL + mostResourceUsedURL,
						HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
						});
				String mostResourceUsed = response.getBody();
				session.setAttribute("mostResourceUsed", mostResourceUsed);
				return new ModelAndView("FacilityManager.jsp?operation=mostResourceUsed");

			} else if (operation.equals("addMeetingRoom")) {
				String addMeetingRoomURL = "addMeetingRoom/";
				Integer id = Integer.parseInt(request.getParameter("id"));
				String meetingRoomName = request.getParameter("meetingRoom");
				MeetingRoom newMeetingRoom = new MeetingRoom();
				newMeetingRoom.setId(id);
				newMeetingRoom.setMeetingRoomName(meetingRoomName);
				restTemplate.postForObject(facilityManagerBaseURL + addMeetingRoomURL, newMeetingRoom,
						MeetingRoom.class);
				return new ModelAndView("FacilityManager.jsp");

			} else if (operation.equals("addResource")) {
				String addResourceURL = "addResource/";
				Integer id = Integer.parseInt(request.getParameter("id"));
				String resourceName = request.getParameter("resource");
				Resource newResource = new Resource();
				newResource.setId(id);
				newResource.setResourceName(resourceName);
				restTemplate.postForObject(facilityManagerBaseURL + addResourceURL, newResource, Resource.class);
				return new ModelAndView("FacilityManager.jsp");

			} else if (operation.equals("createUser")) {
				String createUserURL = "createUser/";
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				Login newUser = new Login();
				newUser.setUsername(username);
				newUser.setPassword(password);
				restTemplate.postForObject(facilityManagerBaseURL + createUserURL, newUser, Login.class);
				return new ModelAndView("FacilityManager.jsp");

			} else if (operation.equals("deleteUser")) {
				String username = request.getParameter("username");
				String deleteUserURL = "deleteUser/" + username;
				restTemplate.delete(facilityManagerBaseURL + deleteUserURL);
				return new ModelAndView("FacilityManager.jsp");

			} else if (operation.equals("acceptRequest")) {
				String acceptRequestURL = "acceptRequest/";
				String requestId = request.getParameter("id");
				MeetingRequest meetingRequest = new MeetingRequest();
				meetingRequest.setRequestId(requestId);
				String requestState = restTemplate.postForObject(facilityManagerBaseURL + acceptRequestURL,
						meetingRequest, String.class);
				if (requestState.equals("accepted")) {
					return new ModelAndView("FacilityManager?operation=getAllPendingRequest");
				} else {
					return new ModelAndView("FacilityManager?error=declined&operation=getAllPendingRequest");
				}
			} else if (operation.equals("requestHR")) {
				String requestHRURL = "requestHR/";
				Integer noOfMondays = 4;
				ArrayList<LocalDate> mondays = UserGenerator.getNextMondays(noOfMondays, LocalDate.now());
				ArrayList<MeetingRequest> HRrequests = new ArrayList<MeetingRequest>();
				for (LocalDate eachMonday : mondays) {
					MeetingRequest HRrequest = new MeetingRequest();
					HRrequest.setDate(eachMonday);
					HRrequest.setEndTime(LocalTime.of(11, 0));
					HRrequest.setMeetingRoom(meetingRoomList.get(0));
					HRrequest.setRequestId(UserGenerator.generateID("HR"));
					HRrequest.setResource(resourceList.get(0));
					HRrequest.setStartTime(LocalTime.of(10, 0));
					HRrequest.setStatus(Status.NEW);
					HRrequest.setRequestedOn(LocalDateTime.now());
					Login userHR = new Login();
					userHR.setRole("HR");
					userHR.setUsername("HR");
					userHR.setPassword("HR");
					HRrequest.setUser(userHR);
					HRrequests.add(HRrequest);
				}
				restTemplate.postForObject(facilityManagerBaseURL + requestHRURL, HRrequests, ArrayList.class);
				return new ModelAndView("FacilityManager?operation=getAllPendingRequest");
			} else if (operation.equals("rejectRequest")) {

				String rejectRequestURL = "rejectRequest/";
				String requestId = request.getParameter("id");
				MeetingRequest meetingRequest = new MeetingRequest();
				meetingRequest.setRequestId(requestId);
				restTemplate.put(facilityManagerBaseURL + rejectRequestURL, meetingRequest);
				return new ModelAndView("FacilityManager?operation=getAllPendingRequest");
			}

		}
		return new ModelAndView("FacilityManager.jsp");
	}
}
