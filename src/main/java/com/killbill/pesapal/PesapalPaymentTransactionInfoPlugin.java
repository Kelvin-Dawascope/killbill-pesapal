package com.killbill.pesapal;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;
import org.killbill.billing.catalog.api.Currency;
import org.killbill.billing.payment.api.PluginProperty;
import org.killbill.billing.payment.api.TransactionType;
import org.killbill.billing.payment.plugin.api.PaymentPluginStatus;
import org.killbill.billing.payment.plugin.api.PaymentTransactionInfoPlugin;

public class PesapalPaymentTransactionInfoPlugin implements PaymentTransactionInfoPlugin {

	public PesapalPaymentTransactionInfoPlugin(UUID kbPaymentId, UUID kbTransactionPaymentPaymentId,
			TransactionType transactionType, BigDecimal amount, Currency currency, PaymentPluginStatus pluginStatus,
			String gatewayError, String gatewayErrorCode, String firstPaymentReferenceId,
			String secondPaymentReferenceId, DateTime createdDate, DateTime effectiveDate,
			List<PluginProperty> properties) {
		super();
	}

	@Override
	public UUID getKbPaymentId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UUID getKbTransactionPaymentId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionType getTransactionType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getAmount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Currency getCurrency() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DateTime getCreatedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DateTime getEffectiveDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentPluginStatus getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGatewayError() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGatewayErrorCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFirstPaymentReferenceId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSecondPaymentReferenceId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PluginProperty> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

}
