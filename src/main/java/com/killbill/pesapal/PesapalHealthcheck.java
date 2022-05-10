package com.killbill.pesapal;

import java.util.Map;

import javax.annotation.Nullable;

import org.killbill.billing.osgi.api.Healthcheck;
import org.killbill.billing.tenant.api.Tenant;

public class PesapalHealthcheck implements Healthcheck {

	@Override
	public HealthStatus getHealthStatus(@Nullable final Tenant tenant, @Nullable final Map properties) {
		// TODO Auto-generated method stub
		return HealthStatus.healthy();
	}

}
