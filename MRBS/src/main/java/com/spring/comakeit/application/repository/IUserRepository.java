package com.spring.comakeit.application.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.spring.comakeit.application.entity.MeetingRequest;
import com.spring.comakeit.application.entity.MeetingRoom;
import com.spring.comakeit.application.entity.Resource;


// just for reference
public interface IUserRepository {
	public List<MeetingRequest> getAllRequest(String username);//view requested slot
	public void cancelRequest(String requestID);//view requested slot
	public void cancelBulkRequests(MeetingRequest request,LocalDate startDate,LocalDate endDate);
	public ArrayList<MeetingRequest> sendBulkRequests(ArrayList<MeetingRequest> requests);//request bulk slot
	public MeetingRequest sendRequest(MeetingRequest request);//request a slot
	public List<MeetingRequest> getPastHistory(String username);//requesthistory
	public List<MeetingRoom> getMeetingRoomList();
	public List<Resource> getResourceList();
}
