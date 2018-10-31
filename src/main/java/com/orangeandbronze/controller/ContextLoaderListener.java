package com.orangeandbronze.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.orangeandbronze.service.BalanceEntryService;
import com.orangeandbronze.service.BalanceEntryServiceImpl;

@WebListener
public class ContextLoaderListener implements ServletContextListener{
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		BalanceEntryService service = new BalanceEntryServiceImpl();
		sce.getServletContext().setAttribute("service", service);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//Do Nothing
	}

}
