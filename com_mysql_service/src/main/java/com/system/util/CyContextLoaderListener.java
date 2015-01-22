package com.system.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.ContextLoaderListener;

import com.system.prg.util.SystemCache;

/**
 * 系统启动时加载
 * @version 1.0
 */
public class CyContextLoaderListener extends ContextLoaderListener implements ServletContextListener {
	@Override
	public void contextDestroyed(ServletContextEvent event) { 
		super.contextDestroyed(event);
	} 
	 
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		ServletContext servletContext = event.getServletContext();
		if (servletContext != null){
			ObjectFactory.getInstance(servletContext);
		}
		SystemCache.cacheAllDict(); 
	}
}
