package com.orangeandbronze.controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orangeandbronze.service.BalanceEntryService;

@WebServlet("/add")
public class AddEntryController extends HttpServlet {

	private BalanceEntryService service;

	@Override
	public void init() throws ServletException {
		super.init();
		if(service == null){
			service = (BalanceEntryService) getServletContext().getAttribute("service");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstname = returnStringOrDefault(req.getParameter("firstname"), "");
		String lastname = returnStringOrDefault(req.getParameter("lastname"), "");
		String money = returnStringOrDefault(req.getParameter("money"), "0");
		
		service.save(firstname, lastname, new BigDecimal(money));
		
		resp.sendRedirect("/");
	}
	
	private String returnStringOrDefault(String string, String defaultString){
		return string != null ? string : defaultString;
	}
}
