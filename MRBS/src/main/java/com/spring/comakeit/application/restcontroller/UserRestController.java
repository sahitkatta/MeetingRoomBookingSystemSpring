package com.spring.comakeit.application.restcontroller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.comakeit.application.entity.MeetingRequest;
import com.spring.comakeit.application.entity.MeetingRoom;
import com.spring.comakeit.application.entity.Resource;
import com.spring.comakeit.application.service.UserService;

@RestController
@RequestMapping("user")
public class UserRestController {
	@Autowired
	private UserService userService;

	
	//GET
	/*
	 * getAllRequest
	 * getPastHistory
	 * getMeetingRoomList
	 * getResourceList
	 * */
	@RequestMapping("getAllRequest/{username}")
	@GetMapping
	public ArrayList<MeetingRequest> getAllRequest(@PathVariable("username") String username){
		return userService.getAllRequest(username);
	}
	@RequestMapping("getPastHistory/{username}")
	@GetMapping
	public ArrayList<MeetingRequest> getPastHistory(@PathVariable("username") String username){
		return userService.getPastHistory(username);
	}
	@RequestMapping("getMeetingRoomList")
	@GetMapping
	public ArrayList<MeetingRoom> getMeetingRoomList(){
		return userService.getMeetingRoomList();
	}
	@RequestMapping("getResourceList")
	@GetMapping
	public ArrayList<Resource> getResourceList(){
		return userService.getResourceList();
	}
	
	//POST
	/*
	 * sendRequest
	 * sendBulkRequest
	 * */
	@RequestMapping("sendRequest")
	@PostMapping
	public MeetingRequest sendRequest(@RequestBody MeetingRequest request) {
		userService.sendRequest(request);
		return request;
	}
	@RequestMapping("sendBulkRequest")
	@PostMapping
	public ArrayList<MeetingRequest> sendBulkRequest(@RequestBody ArrayList<MeetingRequest> requests) {
		userService.sendBulkRequests(requests);
		return requests;
	}
	
	//PUT
	/*
	 * cancelRequest
	 * cancelBulkRequests
	 * */
	@RequestMapping("cancelRequest")
	@PutMapping
	public MeetingRequest cancelRequest(@RequestBody MeetingRequest request) {

		return userService.cancelRequest(request.getRequestId());
	}

	@RequestMapping("cancelBulkRequests/{startDate}/{endDate}")
	@PutMapping
	public void cancelBulkRequests(@RequestBody MeetingRequest request,@PathVariable("startDate") String startDate,@PathVariable("endDate") String endDate) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		userService.cancelBulkRequests(request, LocalDate.parse(startDate, dateFormatter), LocalDate.parse(endDate, dateFormatter));
	}
}
