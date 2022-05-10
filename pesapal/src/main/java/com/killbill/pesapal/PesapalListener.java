package com.killbill.pesapal;

import org.killbill.billing.account.api.Account;
import org.killbill.billing.account.api.AccountApiException;
import org.killbill.billing.notification.plugin.api.ExtBusEvent;
import org.killbill.billing.osgi.libs.killbill.OSGIKillbillAPI;
import org.killbill.billing.osgi.libs.killbill.OSGIKillbillEventDispatcher;
import org.killbill.billing.plugin.api.PluginTenantContext;
import org.killbill.billing.util.callcontext.TenantContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PesapalListener implements OSGIKillbillEventDispatcher.OSGIKillbillEventHandler{

	private static final Logger logger = LoggerFactory.getLogger(PesapalListener.class);

    private final OSGIKillbillAPI osgiKillbillAPI;

    public PesapalListener(final OSGIKillbillAPI killbillAPI) {
        this.osgiKillbillAPI = killbillAPI;
    }
	
	@Override
	public void handleKillbillEvent(ExtBusEvent killbillEvent) {
		// TODO Auto-generated method stub
		logger.info("Received event {} for object id {} of type {}",
                killbillEvent.getEventType(),
                killbillEvent.getObjectId(),
                killbillEvent.getObjectType());

    final TenantContext context = new PluginTenantContext(killbillEvent.getAccountId(), killbillEvent.getTenantId());
    switch (killbillEvent.getEventType()) {
        //
        // Handle ACCOUNT_CREATION and ACCOUNT_CHANGE only for demo purpose and just print the account
        //
        case ACCOUNT_CREATION:
        case ACCOUNT_CHANGE:
            try {
                final Account account = osgiKillbillAPI.getAccountUserApi().getAccountById(killbillEvent.getAccountId(), context);
                logger.info("Account information: " + account);
            } catch (final AccountApiException e) {
                logger.warn("Unable to find account", e);
            }
            break;

        // Nothing
        default:
            break;

    }
	}

}
