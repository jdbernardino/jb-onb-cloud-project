package com.orangeandbronze.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.orangeandbronze.service.BalanceEntryDTO;
import com.orangeandbronze.service.BalanceEntryService;
import com.orangeandbronze.service.BalanceEntryServiceImpl;

@WebServlet("/list")
public class HomeController extends HttpServlet{
	
	private BalanceEntryService service;
	
	@Override
	public void init() throws ServletException {
		super.init();
		if(service == null){
			service = new BalanceEntryServiceImpl();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		List<BalanceEntryDTO> entries = service.findAll();
		session.setAttribute("entries", entries);
		req.getRequestDispatcher("/WEB-INF/list.jsp").forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
