package com.orangeandbronze.controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orangeandbronze.service.BalanceEntryService;
import com.orangeandbronze.service.BalanceEntryServiceImpl;

public class AddEntryController extends HttpServlet {

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
		String firstname = returnStringOrDefault(req.getParameter("firstname"), "");
		String lastname = returnStringOrDefault(req.getParameter("lastname"), "");
		String money = returnStringOrDefault(req.getParameter("money"), "0");
		
		service.save(firstname, lastname, new BigDecimal(money));
		
		resp.sendRedirect("/list");
	}
	
	private String returnStringOrDefault(String string, String defaultString){
		return string != null ? string : defaultString;
	}
}
