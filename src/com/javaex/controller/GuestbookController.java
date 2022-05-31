package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;


@WebServlet("/gbc")
public class GuestbookController extends HttpServlet {
	//필드
	private static final long serialVersionUID = 1L;
    
	//생성자(default)
	
	//메소드 gs
	
	//메소드 일반
	//get방식으로 요청시 호출 메서드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//코드작성
		System.out.println("controller");
		
		
		//포스트 방식일때 한글깨짐 방지
		request.setCharacterEncoding("UTF-8");
		
		//action파라미터 꺼내기
		String action = request.getParameter("action");
		System.out.println(action);
		
		
		if("addList".equals(action)) { //리스트일때
			//데이터 가져오기
			GuestbookDao gDao = new GuestbookDao();
			List<GuestbookVo> gList = gDao.getList();
			System.out.println(gList);
			
			//request에 데이터 추가
			request.setAttribute("gList", gList);
			
			//데이터 + html --> jsp 시킨다
			WebUtil.forward(request, response, "/WEB-INF/addList.jsp");
			
			/*
			RequestDispatcher rd = request.getRequestDispatcher("/gList.jsp");
			rd.forward(request, response);
			*/
		}else if("add".equals(action)) { //등록일때

			//파라미터에서 값 꺼내기(name, hp ,company)
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//vo만들어서 값 초기화
			GuestbookVo guestbookVo = new GuestbookVo(name, hp, company);
			System.out.println(guestbookVo);
			
			
			GuestbookDao gDao = new GuestbookDao();
			int count = gDao.guestbookInsert(guestbookVo);
			System.out.println(count);
			
			//리다이렉트 list
			WebUtil.redirect(request, response, "./gbc?action=list");
			/*
			response.sendRedirect("./gbc?action=list");
			*/
			
		}else if("delete".equals(action)) { //삭제일때
			
			//파라미터에서 id값을 꺼낸다
			int id = Integer.parseInt(request.getParameter("id"));
			
			//phoneDao.personDelete()를 통해 삭제하기
			GuestbookDao guestbookDao = new GuestbookDao();
			int count = gDao.guestbookDelete(id);
			
			//리다이렉트 list
			WebUtil.redirect(request, response, "./gbc?action=list");
			/*
			response.sendRedirect("./gbc?action=list");
			*/
			
		}else if("deleteform".equals(action)) { //삭제폼
			int count = Integer.parseInt(request.getParameter("count"));
			
			request.setAttribute("count", count);
			
			RequestDispatcher rd = request.getRequestDispatcher("/deleteForm.jsp");
			rd.forward(request, response);
		}else {
			System.out.println("action 파라미터 없음");
		}
		
	}
	
	//post방식으로 요청시 호출 메소드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("여기는 post");
		
		doGet(request, response);
	}

}
