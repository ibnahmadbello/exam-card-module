package com.spring.deptproject.entities;

import java.util.Map;
import java.util.Set;

public class StudentDetail {
	private String firstName;
	private String lastName;
	private String level;
	private Set<Map<String, String>> courses;
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
	public Set<Map<String, String>> getCourses() {
		return courses;
	}
	public void setCourses(Set<Map<String, String>> courses) {
		this.courses = courses;
	}
	@Override
	public String toString() {
		return "StudentDetail \n[firstName=" + firstName + "\n lastName=" + lastName + "\n level=" + level + "\n courses="
				+ courses + "]";
	}
}
