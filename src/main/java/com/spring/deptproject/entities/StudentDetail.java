package com.spring.deptproject.entities;

import java.util.Map;
import java.util.Set;

public class StudentDetail {
	private String firstName;
	private String lastName;
	private String level;
	private Map<String, Object> courses;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Map<String, Object> getCourses() {
		return courses;
	}
	public void setCourses(Map<String, Object> courses) {
		this.courses = courses;
	}
	@Override
	public String toString() {
		return "StudentDetail \n[firstName=" + firstName + "\n lastName=" + lastName + "\n level=" + level + "\n courses="
				+ courses + "]";
	}
}
