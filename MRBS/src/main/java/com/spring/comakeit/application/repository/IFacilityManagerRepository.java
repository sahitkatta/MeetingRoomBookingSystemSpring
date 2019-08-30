package com.spring.comakeit.application.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.spring.comakeit.application.entity.Login;
import com.spring.comakeit.application.entity.MeetingRequest;
import com.spring.comakeit.application.entity.MeetingRoom;
import com.spring.comakeit.application.entity.Resource;


//Just a reference to know the functionalilties
public interface IFacilityManagerRepository {
	public MeetingRoom addMeetingRoom(MeetingRoom meetingRoom);
	public Resource addResource(Resource resource);
	public Login createUser(String username,String password);
	public Login deleteUser(String username);
	public List<Login> viewUsers();
	public List<MeetingRequest> getAllPendingRequest();
	public List<MeetingRequest> getAllRequest();
	public void acceptRequest(String requestID);
	public void acceptHRRequest(String requestID);
	public void rejectRequest(String requestID);
	public Boolean isMeetinRoomAvailable(MeetingRequest pendingRequest);
	public List<MeetingRequest> getAllRequestForGivenDay(LocalDate date);
	public List<String> getMRFrequency();
	public List<String> getResourceFrequency();
	public String mostResourceUsed();
	public List<MeetingRequest> requestHR(ArrayList<MeetingRequest> HRrequests);
}
