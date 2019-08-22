package be.tree.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect(fieldVisibility=Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ChatInfo {
	
	private String channel;
	
	private String user;
	
	private String ts;
	
	
	private int year;
	
	
	private int month;
	
	
	private int day;
	
	
	private int hour;
	
	
	private int minute;
	
	
	private int second;
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year =year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month2) {
		this.month = month2;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int dayOfWeek) {
		this.day = dayOfWeek;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	
	
	
	
	

}
