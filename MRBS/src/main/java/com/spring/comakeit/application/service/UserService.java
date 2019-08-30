package com.spring.comakeit.application.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.comakeit.application.constants.Status;
import com.spring.comakeit.application.entity.MeetingRequest;
import com.spring.comakeit.application.entity.MeetingRoom;
import com.spring.comakeit.application.entity.Resource;
import com.spring.comakeit.application.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public ArrayList<MeetingRequest> getAllRequest(String username) {

		return new ArrayList<MeetingRequest>(userRepository.getAllRequest(username));
	}

	public MeetingRequest cancelRequest(String requestID) {
		Optional<MeetingRequest> request = userRepository.findById(requestID);
		MeetingRequest cancelRequest = new MeetingRequest();
		if(request.isPresent()) {
			cancelRequest = request.get();
			cancelRequest.setStatus(Status.CANCEL);
			userRepository.save(cancelRequest);
		}
		return cancelRequest;
	}


	public ArrayList<MeetingRequest> sendBulkRequests(ArrayList<MeetingRequest> requests) {
		for(MeetingRequest request: requests) {
			userRepository.save(request);
		}
		return requests;
	}


	public MeetingRequest sendRequest(MeetingRequest request) {
		userRepository.save(request);
		return request;
	}


	public ArrayList<MeetingRequest> getPastHistory(String username) {
		return new ArrayList<MeetingRequest>(userRepository.getPastHistory(username));

	}


	public ArrayList<MeetingRoom> getMeetingRoomList() {

		return new ArrayList<MeetingRoom> (userRepository.getMeetingRoomList());
	}


	public ArrayList<Resource> getResourceList() {

		return new ArrayList<Resource>(userRepository.getResourceList());
	}
	public void cancelBulkRequests(MeetingRequest request,LocalDate startDate, LocalDate endDate) {
		ArrayList<MeetingRequest> requests = new  ArrayList<MeetingRequest>(userRepository.cancelBulkRequests(startDate, endDate, request.getMeetingRoom().getMeetingRoomName(), request.getResource().getResourceName(), request.getUser().getUsername(), request.getStartTime(), request.getEndTime()));
		for(MeetingRequest req : requests) {
			req.setStatus(Status.CANCEL);
			userRepository.save(req);
		}
	}


}
