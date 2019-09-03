package com.spring.comakeit.application.utility;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class UserGenerator {
	
	public static String generateID(String username) {
		String result ="";
		result +=  username.charAt(0);
		result =result.toUpperCase();
		Random random = new Random(); 
		Integer value = random.nextInt(10000000);
		result += value.toString();
		return result;
		
	}
	public static ArrayList<LocalDate> getDatesBetween(LocalDate startDate,LocalDate endDate){
		ArrayList<LocalDate> result = new ArrayList<LocalDate>();
		
		LocalDate date = startDate;
		while(!date.isEqual(endDate)) {
			if((!(date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY))))
				result.add(date);
			date = date.plusDays(1);
		}
		if(!(date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY)))
			result.add(endDate);
		
		return result;
	}
	public static ArrayList<LocalDate> getNextMondays(Integer noOfMondays,LocalDate startDate){
		ArrayList<LocalDate> mondays = new ArrayList<LocalDate>();
		Integer mondaysCount = 1;
		LocalDate date = startDate;
		while(mondaysCount<=noOfMondays) {
			
			if(date.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
				mondays.add(date);
				mondaysCount += 1;
			}
			date = date.plusDays(1);
			
		}
		return mondays;
	}
	
}
