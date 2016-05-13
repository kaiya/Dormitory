package com.kyawn.entity;

public class student {

	private Long studentid;
	private String sid;
	private String StudentName;
	private String StudentSex;
	private String StudentPhone;
	private String Bname;
	private String Dname;
	public String getBname() {
		return Bname;
	}

	public void setBname(String bname) {
		Bname = bname;
	}

	public String getDname() {
		return Dname;
	}

	public void setDname(String dname) {
		Dname = dname;
	}

	private String StudentMajor;
	private String StudentClass;
	private Long year;
	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	private String StudentDepartment;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public Long getStudentid() {
		return studentid;
	}

	public void setStudentid(Long studentid) {
		this.studentid = studentid;
	}

	public String getStudentName() {
		return StudentName;
	}

	public void setStudentName(String studentName) {
		StudentName = studentName;
	}

	public String getStudentSex() {
		return StudentSex;
	}

	public void setStudentSex(String studentSex) {
		StudentSex = studentSex;
	}

	public String getStudentPhone() {
		return StudentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		StudentPhone = studentPhone;
	}

	public String getStudentMajor() {
		return StudentMajor;
	}

	public void setStudentMajor(String studentMajor) {
		StudentMajor = studentMajor;
	}

	public String getStudentClass() {
		return StudentClass;
	}

	public void setStudentClass(String studentClass) {
		StudentClass = studentClass;
	}

	public String getStudentDepartment() {
		return StudentDepartment;
	}

	public void setStudentDepartment(String studentDepartment) {
		StudentDepartment = studentDepartment;
	}

}
