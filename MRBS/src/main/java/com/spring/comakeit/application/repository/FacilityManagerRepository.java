package com.spring.comakeit.application.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.comakeit.application.entity.Login;
import com.spring.comakeit.application.entity.MeetingRequest;

@Repository
public interface FacilityManagerRepository extends JpaRepository<MeetingRequest, String> {

	@Query("SELECT login FROM Login login order by login.username")
	public List<Login> viewUsers();

	@Query("SELECT request FROM MeetingRequest request where ((request.status='NEW')) order by request.requestedOn")
	public List<MeetingRequest> getAllPendingRequest();

	@Query("SELECT request FROM MeetingRequest request")
	public List<MeetingRequest> getAllRequest();

	@Query("SELECT request from MeetingRequest request where "
			+ "((request.requestId != :requestId) and ((request.meetingRoom.meetingRoomName= :meetingRoomNumber)or(request.resource.resourceName = :resource)) "
			+ "and (request.date= :date) and (request.status='ACCEPT') and ( :startTime between (request.startTime) and (request.endTime)))")
	public List<MeetingRequest> isMeetinRoomAvailableForStartTime(@Param("requestId") String requestId,
			@Param("meetingRoomNumber") String meetingRoomNumber, @Param("resource") String resource,
			@Param("date") LocalDate date, @Param("startTime") LocalTime startTime);

	@Query("SELECT request from MeetingRequest request where "
			+ "((request.requestId != :requestId) and ((request.meetingRoom.meetingRoomName= :meetingRoomNumber)or(request.resource.resourceName = :resource)) "
			+ "and (request.date= :date) and (request.status='ACCEPT') and ( :endTime between (request.startTime) and (request.endTime)))")
	public List<MeetingRequest> isMeetinRoomAvailableForEndTime(@Param("requestId") String requestId,
			@Param("meetingRoomNumber") String meetingRoomNumber, @Param("resource") String resource,
			@Param("date") LocalDate date, @Param("endTime") LocalTime endTime);

	@Query("SELECT request FROM MeetingRequest request where request.date= :date")
	public List<MeetingRequest> getAllRequestForGivenDay(@Param("date") LocalDate date);

	@Query("SELECT COUNT(r.meetingRoom.id) FROM MeetingRequest r  where r.status='ACCEPT' GROUP BY r.meetingRoom.id")
	public List<Number> getMRFrequencyCount();

	@Query("SELECT r.meetingRoom.meetingRoomName FROM MeetingRequest r  where r.status='ACCEPT' GROUP BY r.meetingRoom.id")
	public List<String> getMRFrequencyMR();

	@Query("SELECT COUNT(r.resource.id) FROM MeetingRequest r  where r.status='ACCEPT' GROUP BY r.resource.id")
	public List<Number> getResourceFrequencyCount();

	@Query("SELECT r.resource.resourceName FROM MeetingRequest r  where r.status='ACCEPT' GROUP BY r.resource.id")
	public List<String> getResourceFrequencyResource();

}
