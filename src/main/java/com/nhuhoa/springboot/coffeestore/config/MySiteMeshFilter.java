package com.nhuhoa.springboot.coffeestore.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.stereotype.Component;

@Component
public class MySiteMeshFilter extends ConfigurableSiteMeshFilter {
	
	 @Override
	  protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) { 
	    builder.addDecoratorPath("/register/**", "/WEB-INF/decorators/register.jsp")
	    		.addDecoratorPath("/admin/login**", "/WEB-INF/decorators/login-admin.jsp")
	    		.addDecoratorPath("/login/**", "/WEB-INF/decorators/login.jsp")
	    		.addDecoratorPath("/*", "/WEB-INF/decorators/web.jsp")
	    		.addDecoratorPath("/admin*", "/WEB-INF/decorators/admin.jsp");
	  }

}
