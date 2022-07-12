package com.my.hr.presentation;

public enum Job {
	EXIT, LIST, ADD, FIX, DEL;
	
	public static int length() {
		return values().length;
	}
	
	public static Job toJob(int ordinal) {		
		return values()[ordinal];
	}
}
