package com.javaex.dao;

public class DaoTest {

	public static void main(String[] args) {
		
		GuestbookDao dao = new GuestbookDao();
		System.out.println(dao.getList().toString());
			
	}

}