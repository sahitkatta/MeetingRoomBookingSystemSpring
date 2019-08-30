package com.spring.comakeit.application.restcontroller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.comakeit.application.entity.Login;
import com.spring.comakeit.application.entity.MeetingRequest;
import com.spring.comakeit.application.entity.MeetingRoom;
import com.spring.comakeit.application.entity.Resource;
import com.spring.comakeit.application.service.FacilityManagerService;

@RestController
@RequestMapping("facilityManager") // F -> small f
public class FacilityManagerRestController {
	@Autowired
	private FacilityManagerService facilityManagerService;

	//GET
	/* viewUsers
	 * getAllPendingRequest
	 * getAllRequest
	 * getAllRequestForGivenDay
	 * getMRFrequency
	 * getResourceFrequency
	 * mostResourceUsed --PlainText
	 * 
	 * */
	@RequestMapping("viewUsers")
	@GetMapping

	public ArrayList<Login> viewUsers(){
		return facilityManagerService.viewUsers();
	}
	@RequestMapping("getAllPendingRequest")
	@GetMapping

	public ArrayList<MeetingRequest> getAllPendingRequest(){
		return facilityManagerService.getAllPendingRequest();
	}
	@RequestMapping("getAllRequest")
	@GetMapping

	public ArrayList<MeetingRequest> getAllRequest(){
		return facilityManagerService.getAllRequest();
	}
	@RequestMapping("getAllRequestForGivenDay/{date}")
	@GetMapping

	public ArrayList<MeetingRequest> getAllRequestForGivenDay(@PathVariable("date") String date){
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return facilityManagerService.getAllRequestForGivenDay(LocalDate.parse(date,dateFormatter));
	}
	@RequestMapping("getMRFrequency")
	@GetMapping

	public ArrayList<String> getMRFrequency(){
		return facilityManagerService.getMRFrequency();
	}
	@RequestMapping("getResourceFrequency")
	@GetMapping

	public ArrayList<String> getResourceFrequency(){
		return facilityManagerService.getResourceFrequency();
	}
	@RequestMapping("mostResourceUsed")
	@GetMapping

	public String mostResourceUsed(){
		return facilityManagerService.mostResourceUsed();
	}

	//POST
	/* addMeetingRoom
	 * addResource
	 * createUser
	 * requestHR
	 * acceptRequest
	 * acceptHRRequest
	 * 
	 * */
	@RequestMapping("createUser")
	@PostMapping

	public Login createUser(@RequestBody Login user){
		return facilityManagerService.createUser(user.getUsername(),user.getPassword());
	}
	@RequestMapping("addMeetingRoom")
	@PostMapping

	public MeetingRoom addMeetingRoom(@RequestBody MeetingRoom meetingRoom){
		return facilityManagerService.addMeetingRoom(meetingRoom);
	}
	@RequestMapping("addResource")
	@PostMapping

	public Resource addResource(@RequestBody Resource resource){
		return facilityManagerService.addResource(resource);
	}
	@RequestMapping("requestHR")
	@PostMapping

	public ArrayList<MeetingRequest> requestHR(@RequestBody ArrayList<MeetingRequest> HRrequests){
		return facilityManagerService.requestHR(HRrequests);
	}
	@RequestMapping("acceptRequest")
	@PostMapping

	public String acceptRequest(@RequestBody MeetingRequest request){
		
		return facilityManagerService.acceptRequest(request.getRequestId());
	}
	@RequestMapping("acceptHRRequest")
	@PostMapping

	public String  acceptHRRequest(@RequestBody MeetingRequest request){
		return facilityManagerService.acceptHRRequest(request.getRequestId());
	}

	//PUT
	/* 
	 * rejectRequest
	 * */
	
	@RequestMapping("rejectRequest")
	@PutMapping

	public MeetingRequest rejectRequest(@RequestBody MeetingRequest request){
		return facilityManagerService.rejectRequest(request.getRequestId());
	}

	//DELETE
	/* deleteUser
	 * */
	@RequestMapping("deleteUser/{username}")
	@DeleteMapping

	public Login deleteUser(@PathVariable("username")String username){
		return facilityManagerService.deleteUser(username);
	}
}
