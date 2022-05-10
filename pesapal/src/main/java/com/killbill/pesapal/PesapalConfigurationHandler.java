package com.killbill.pesapal;

import java.util.Properties;

import org.killbill.billing.osgi.libs.killbill.OSGIKillbillAPI;
import org.killbill.billing.plugin.api.notification.PluginTenantConfigurableConfigurationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PesapalConfigurationHandler extends PluginTenantConfigurableConfigurationHandler<Properties> {

	private static final Logger logger = LoggerFactory.getLogger(PesapalConfigurationHandler.class);

    private final String region;
	
	public PesapalConfigurationHandler(final String region, String pluginName, OSGIKillbillAPI osgiKillbillAPI) {
		super(pluginName, osgiKillbillAPI);
		// TODO Auto-generated constructor stub
		this.region = region;
	}

	@Override
	protected Properties createConfigurable(Properties properties) {
		// TODO Auto-generated method stub
		logger.info("New properties for region {}: {}", region, properties);
        return properties;
	}

}
