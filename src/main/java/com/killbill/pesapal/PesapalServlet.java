package com.killbill.pesapal;

import java.util.Optional;

import javax.inject.Named;

import org.jooby.mvc.GET;
import org.jooby.mvc.Local;
import org.killbill.billing.tenant.api.Tenant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PesapalServlet {

	 private static final Logger logger = LoggerFactory.getLogger(PesapalServlet.class);

	    public PesapalServlet() {
	    }

	    /**
	     * Kill Bill automatically injects Tenant object in this method when this end point is accessed with the X-Killbill-ApiKey and X-Killbill-ApiSecret headers 
	     * @param tenant
	     */
	    @GET
	    public void hello(@Local @Named("killbill_tenant") final Optional<Tenant> tenant) {
	        // Find me on http://127.0.0.1:8080/plugins/hello-world-plugin
	        logger.info("Hello world");
	        if(tenant != null && tenant.isPresent() ) {
	        	logger.info("tenant is available");
	        	Tenant t1 = tenant.get();
	        	logger.info("tenant id:"+t1.getId());
	        }
	        else {
	        	logger.info("tenant is not available");
	        }
	    }
}
