package com.orangeandbronze.controller;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.orangeandbronze.service.BalanceEntryDTO;
import com.orangeandbronze.service.BalanceEntryService;
import com.orangeandbronze.service.BalanceEntryServiceImpl;

@WebListener
public class ContextLoaderListener implements ServletContextListener{
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		BalanceEntryService service = new BalanceEntryServiceImpl();
		sce.getServletContext().setAttribute("service", service);
		List<BalanceEntryDTO> entries = service.findAll();
		sce.getServletContext().setAttribute("entries", entries);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//Do Nothing
	}

}
