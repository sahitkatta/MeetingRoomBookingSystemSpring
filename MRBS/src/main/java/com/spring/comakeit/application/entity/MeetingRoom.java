package com.spring.comakeit.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MeetingRoom")
public class MeetingRoom {

	@Id
	private Integer id;
	@Column(length=32)
	private String meetingRoomName;

	public Integer getId() {
		return id;
	}

	public String getMeetingRoomName() {
		return meetingRoomName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMeetingRoomName(String meetingRoomName) {
		this.meetingRoomName = meetingRoomName;
	}

}
