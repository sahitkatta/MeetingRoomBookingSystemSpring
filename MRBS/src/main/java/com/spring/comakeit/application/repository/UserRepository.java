package com.spring.comakeit.application.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.comakeit.application.entity.MeetingRequest;
import com.spring.comakeit.application.entity.MeetingRoom;
import com.spring.comakeit.application.entity.Resource;

@Repository
public interface UserRepository extends JpaRepository<MeetingRequest, String> {
	@Query("SELECT request FROM MeetingRequest request where request.user.username = :username and ((request.status='NEW') or (request.status='ACCEPT')) order by request.date")
	public List<MeetingRequest> getAllRequest(@Param("username") String username);// view requested slot

	@Query("SELECT r from MeetingRequest as r WHERE r.date between :startDate and :endDate AND (r.meetingRoom.meetingRoomName = :meetingRoomName) and (r.resource.resourceName = :resourceName)  and( r.user.username= :username) and (r.startTime =:startTime) and (r.endTime = :endTime)")
	public List<MeetingRequest> cancelBulkRequests(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate, @Param("meetingRoomName") String meetingRoomName,
			@Param("resourceName") String resourceName, @Param("username") String username,
			@Param("startTime") LocalTime startTime, @Param("endTime") LocalTime endTime);

	@Query("SELECT request FROM MeetingRequest request where request.user.username= :username  order by request.date")
	public List<MeetingRequest> getPastHistory(@Param("username") String username);// requesthistory

	@Query("SELECT room FROM MeetingRoom room")
	public List<MeetingRoom> getMeetingRoomList();

	@Query("SELECT resource FROM Resource resource")
	public List<Resource> getResourceList();

}
