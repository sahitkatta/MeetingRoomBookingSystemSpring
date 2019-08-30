package com.spring.comakeit.application.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.comakeit.application.constants.Status;
import com.spring.comakeit.application.entity.Login;
import com.spring.comakeit.application.entity.MeetingRequest;
import com.spring.comakeit.application.entity.MeetingRoom;
import com.spring.comakeit.application.entity.Resource;
import com.spring.comakeit.application.repository.FacilityManagerRepository;
import com.spring.comakeit.application.repository.LoginRepository;
import com.spring.comakeit.application.repository.MeetingRoomRepository;
import com.spring.comakeit.application.repository.ResourceRepository;


@Service
public class FacilityManagerService {
	
	@Autowired
	private FacilityManagerRepository facilityManagerRepository;
	@Autowired
	private MeetingRoomRepository meetingRoomRepository;
	@Autowired
	private ResourceRepository resourceRepository;
	@Autowired
	private LoginRepository loginRepository;

	public MeetingRoom addMeetingRoom(MeetingRoom meetingRoom) {
		meetingRoomRepository.save(meetingRoom);
		return meetingRoom;
	}

	public Resource addResource(Resource resource) {
		resourceRepository.save(resource);
		return resource;
	}

	public Login createUser(String username, String password) {
		String role ="user";
		Login user = new Login();
		user.setPassword(password);
		user.setRole(role);
		user.setUsername(username);
		loginRepository.save(user);
		return user;
	}

	public Login deleteUser(String username) {
		Optional<Login> user = loginRepository.findById(username);
		if(user.isPresent()) {
			loginRepository.delete(user.get());
			return user.get();
		}
		return new Login();
	}

	public ArrayList<Login> viewUsers() {

		return new ArrayList<Login>(facilityManagerRepository.viewUsers());
	}

	public ArrayList<MeetingRequest> getAllPendingRequest() {

		return new ArrayList<MeetingRequest>(facilityManagerRepository.getAllPendingRequest());
	}

	public ArrayList<MeetingRequest> getAllRequest() {

		return new ArrayList<MeetingRequest>(facilityManagerRepository.getAllRequest());
	}

	public String acceptRequest(String requestID) {
		MeetingRequest request = facilityManagerRepository.findById(requestID).get();
		if(isMeetinRoomAvailable(request)) {
			request.setStatus(Status.ACCEPT);
			facilityManagerRepository.save(request);
			return "accepted";
		}
		return "declined";
	}

	public String acceptHRRequest(String requestID) {
		MeetingRequest HRrequest = facilityManagerRepository.findById(requestID).get();
		if(isMeetinRoomAvailable(HRrequest)) {
			HRrequest.setStatus(Status.ACCEPT);
			facilityManagerRepository.save(HRrequest);
			return "accepted";
		}
		return "declined";
	}

	public MeetingRequest rejectRequest(String requestID) {
		MeetingRequest request = new MeetingRequest();
		Optional<MeetingRequest> getRequest = facilityManagerRepository.findById(requestID);
				if(getRequest.isPresent()) {
					request = getRequest.get();
					request.setStatus(Status.REJECT);
					facilityManagerRepository.save(request);
					return request;
				}
			return request;

	}
	public ArrayList<MeetingRequest> getAllRequestForGivenDay(LocalDate date) {

		return new ArrayList<MeetingRequest>(facilityManagerRepository.getAllRequestForGivenDay(date));
	}

	public ArrayList<String> getMRFrequency() {
		List<Number> count = facilityManagerRepository.getMRFrequencyCount();
		List<String> mr = facilityManagerRepository.getMRFrequencyMR();
		ArrayList<String> frequency =  new ArrayList<String>();
		for(Integer i =0;i<count.size();i++) {
			frequency.add(mr.get(i)+","+count.get(i));
		}
		return frequency;
	}

	public ArrayList<String> getResourceFrequency() {
		List<Number> count = facilityManagerRepository.getResourceFrequencyCount();
		List<String> resource = facilityManagerRepository.getResourceFrequencyResource();
		ArrayList<String> frequency =  new ArrayList<String>();
		for(Integer i =0;i<count.size();i++) {
			frequency.add(resource.get(i)+","+count.get(i));
		}
		return frequency;
	}

	public String mostResourceUsed() {
		String mostResourceUsed= "";
		List<Number> count = facilityManagerRepository.getResourceFrequencyCount();
		List<String> resource = facilityManagerRepository.getResourceFrequencyResource();
		if(count.size()!=0) {
			Number max = count.get(0);
			mostResourceUsed = resource.get(0);
			for(Integer i=0;i<count.size();i++) {
				if(max.intValue()<count.get(i).intValue()) {
					max = count.get(i);
					mostResourceUsed = resource.get(i);
				}
			}
		}
		return mostResourceUsed;
	}


	public ArrayList<MeetingRequest> requestHR(ArrayList<MeetingRequest> HRrequests) {
		for(MeetingRequest HRrequest:HRrequests) {
			facilityManagerRepository.save(HRrequest);
		}
		return HRrequests;
	}
	public Boolean isMeetinRoomAvailable(MeetingRequest pendingRequest) {
		Boolean checkStartTime=true,checkEndTime=true;

		if(facilityManagerRepository.isMeetinRoomAvailableForStartTime(pendingRequest.getRequestId(), pendingRequest.getMeetingRoom().getMeetingRoomName(), pendingRequest.getResource().getResourceName(), pendingRequest.getDate(), pendingRequest.getStartTime()).size()>0) {
			checkStartTime = false;
		}
		if(facilityManagerRepository.isMeetinRoomAvailableForEndTime(pendingRequest.getRequestId(), pendingRequest.getMeetingRoom().getMeetingRoomName(), pendingRequest.getResource().getResourceName(), pendingRequest.getDate(), pendingRequest.getEndTime()).size()>0) {
			checkEndTime = false;
		}
		return checkStartTime && checkEndTime;
	}

}
