package com.killbill.pesapal;

import java.util.Hashtable;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;

import org.killbill.billing.osgi.api.Healthcheck;
import org.killbill.billing.osgi.api.OSGIPluginProperties;
import org.killbill.billing.osgi.libs.killbill.KillbillActivatorBase;
import org.killbill.billing.osgi.libs.killbill.OSGIKillbillEventDispatcher;
import org.killbill.billing.osgi.libs.killbill.OSGIKillbillEventDispatcher.OSGIFrameworkEventHandler;
import org.killbill.billing.payment.plugin.api.PaymentPluginApi;
import org.killbill.billing.plugin.api.notification.PluginConfigurationEventHandler;
import org.killbill.billing.plugin.core.config.PluginEnvironmentConfig;
import org.killbill.billing.plugin.core.resources.jooby.PluginApp;
import org.killbill.billing.plugin.core.resources.jooby.PluginAppBuilder;
import org.osgi.framework.BundleContext;

public class PesapalActivator extends KillbillActivatorBase {

	public static final String PLUGIN_NAME = "killbill-pesapal-plugin";
	
	private PesapalConfigurationHandler pesapalConfigurationHandler;
    private OSGIKillbillEventDispatcher.OSGIKillbillEventHandler killbillEventHandler;
    
    
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);

        final String region = PluginEnvironmentConfig.getRegion(configProperties.getProperties());

        // Register an event listener for plugin configuration (optional)
        pesapalConfigurationHandler = new PesapalConfigurationHandler(region, PLUGIN_NAME, killbillAPI);
        final Properties globalConfiguration = pesapalConfigurationHandler.createConfigurable(configProperties.getProperties());
        pesapalConfigurationHandler.setDefaultConfigurable(globalConfiguration);

        // Register an event listener (optional)
        killbillEventHandler = new PesapalListener(killbillAPI);

        // As an example, this plugin registers a PaymentPluginApi (this could be changed to any other plugin api)
        final PaymentPluginApi paymentPluginApi = new PesapalPaymentPluginApi();
        registerPaymentPluginApi(context, paymentPluginApi);

        // Expose a healthcheck (optional), so other plugins can check on the plugin status
        final Healthcheck healthcheck = new PesapalHealthcheck();
        registerHealthcheck(context, healthcheck);

        // Register a servlet (optional)
        final PluginApp pluginApp = new PluginAppBuilder(PLUGIN_NAME,
                                                         killbillAPI,
                                                         dataSource,
                                                         super.clock,
                                                         configProperties).withRouteClass(PesapalServlet.class)
                                                                          .withRouteClass(PesapalHealthcheckServlet.class)
                                                                          .withService(healthcheck)
                                                                          .build();
        final HttpServlet httpServlet = PluginApp.createServlet(pluginApp);
        registerServlet(context, httpServlet);

        registerHandlers();
    }
    
    @Override
    public void stop(final BundleContext context) throws Exception {
        super.stop(context);
        // Do additional work on shutdown (optional)
    }
    
    private void registerHandlers() {
        final PluginConfigurationEventHandler configHandler = new PluginConfigurationEventHandler(pesapalConfigurationHandler);

        dispatcher.registerEventHandlers(configHandler,
                                         (OSGIFrameworkEventHandler) () -> dispatcher.registerEventHandlers(killbillEventHandler));
    }
    
    private void registerServlet(final BundleContext context, final Servlet servlet) {
        final Hashtable<String, String> props = new Hashtable<String, String>();
        props.put(OSGIPluginProperties.PLUGIN_NAME_PROP, PLUGIN_NAME);
        registrar.registerService(context, Servlet.class, servlet, props);
    }
    
    private void registerPaymentPluginApi(final BundleContext context, final PaymentPluginApi api) {
        final Hashtable<String, String> props = new Hashtable<String, String>();
        props.put(OSGIPluginProperties.PLUGIN_NAME_PROP, PLUGIN_NAME);
        registrar.registerService(context, PaymentPluginApi.class, api, props);
    }
    
    private void registerHealthcheck(final BundleContext context, final Healthcheck healthcheck) {
        final Hashtable<String, String> props = new Hashtable<String, String>();
        props.put(OSGIPluginProperties.PLUGIN_NAME_PROP, PLUGIN_NAME);
        registrar.registerService(context, Healthcheck.class, healthcheck, props);
    }
}
