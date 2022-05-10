package com.killbill.pesapal;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;
import org.killbill.billing.ObjectType;
import org.killbill.billing.catalog.api.Currency;
import org.killbill.billing.osgi.libs.killbill.OSGIKillbillAPI;
import org.killbill.billing.payment.api.PaymentMethodPlugin;
import org.killbill.billing.payment.api.PluginProperty;
import org.killbill.billing.payment.api.TransactionType;
import org.killbill.billing.payment.plugin.api.GatewayNotification;
import org.killbill.billing.payment.plugin.api.HostedPaymentPageFormDescriptor;
import org.killbill.billing.payment.plugin.api.PaymentMethodInfoPlugin;
import org.killbill.billing.payment.plugin.api.PaymentPluginApi;
import org.killbill.billing.payment.plugin.api.PaymentPluginApiException;
import org.killbill.billing.payment.plugin.api.PaymentPluginStatus;
import org.killbill.billing.payment.plugin.api.PaymentTransactionInfoPlugin;
import org.killbill.billing.util.callcontext.CallContext;
import org.killbill.billing.util.callcontext.TenantContext;
import org.killbill.billing.util.customfield.CustomField;
import org.killbill.billing.util.entity.Pagination;
import org.killbill.clock.Clock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PesapalPaymentPluginApi implements PaymentPluginApi {
	
	private static final Logger logger = LoggerFactory.getLogger(PesapalPaymentPluginApi.class);
	private OSGIKillbillAPI killbillAPI;
	private Clock clock;
	private static String GC_ACCESS_TOKEN_PROPERTY = "GC_ACCESS_TOKEN";
	
	@Override
	public PaymentTransactionInfoPlugin authorizePayment(UUID kbAccountId, UUID kbPaymentId, UUID kbTransactionId,
			UUID kbPaymentMethodId, BigDecimal amount, Currency currency, Iterable<PluginProperty> properties,
			CallContext context) throws PaymentPluginApiException {
		// TODO Auto-generated method stub
		PaymentTransactionInfoPlugin paymentTransactionInfoPlugin = new PesapalPaymentTransactionInfoPlugin(kbPaymentId, kbTransactionId,
				TransactionType.AUTHORIZE, amount, currency, PaymentPluginStatus.CANCELED, null, 
				null, null, null, new DateTime(), null, null);
		return paymentTransactionInfoPlugin;
	}
	@Override
	public PaymentTransactionInfoPlugin capturePayment(UUID kbAccountId, UUID kbPaymentId, UUID kbTransactionId,
			UUID kbPaymentMethodId, BigDecimal amount, Currency currency, Iterable<PluginProperty> properties,
			CallContext context) throws PaymentPluginApiException {
		// TODO Auto-generated method stub
		PaymentTransactionInfoPlugin paymentTransactionInfoPlugin = new PesapalPaymentTransactionInfoPlugin(kbPaymentId, kbTransactionId,
				TransactionType.CAPTURE, amount, currency, PaymentPluginStatus.CANCELED, null, 
				null, null, null, new DateTime(), null, null);
		return paymentTransactionInfoPlugin;
	}
	@Override
	public PaymentTransactionInfoPlugin purchasePayment(UUID kbAccountId, UUID kbPaymentId, UUID kbTransactionId,
			UUID kbPaymentMethodId, BigDecimal amount, Currency currency, Iterable<PluginProperty> properties,
			CallContext context) throws PaymentPluginApiException {
		// TODO Auto-generated method stub
		logger.info("purchasePayment, kbAccountId=" + kbAccountId);
		PaymentTransactionInfoPlugin paymentTransactionInfoPlugin;
		String mandate = getMandateId(kbAccountId, context); // retrieve mandateId from Kill Bill tables
		logger.info("MandateId="+mandate);
		
		return null;
	}
	
	/**
	 * Retrieves mandateId from Kill Bill tables
	 * 
	 * @param kbAccountId
	 * @param context
	 * @return
	 */
	private String getMandateId(UUID kbAccountId, TenantContext context) {
		final List<CustomField> customFields = killbillAPI.getCustomFieldUserApi()
				.getCustomFieldsForAccountType(kbAccountId, ObjectType.ACCOUNT, context);
		String mandateId = null;
		for (final CustomField customField : customFields) {
			if (customField.getFieldName().equals("PESAPAL_MANDATE_ID")) {
				mandateId = customField.getFieldValue();
				break;
			}
		}
		return mandateId;

	}
	
	@Override
	public PaymentTransactionInfoPlugin voidPayment(UUID kbAccountId, UUID kbPaymentId, UUID kbTransactionId,
			UUID kbPaymentMethodId, Iterable<PluginProperty> properties, CallContext context)
			throws PaymentPluginApiException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PaymentTransactionInfoPlugin creditPayment(UUID kbAccountId, UUID kbPaymentId, UUID kbTransactionId,
			UUID kbPaymentMethodId, BigDecimal amount, Currency currency, Iterable<PluginProperty> properties,
			CallContext context) throws PaymentPluginApiException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PaymentTransactionInfoPlugin refundPayment(UUID kbAccountId, UUID kbPaymentId, UUID kbTransactionId,
			UUID kbPaymentMethodId, BigDecimal amount, Currency currency, Iterable<PluginProperty> properties,
			CallContext context) throws PaymentPluginApiException {
				return null;
		// TODO Auto-generated method stub
        
	}
	@Override
	public List<PaymentTransactionInfoPlugin> getPaymentInfo(UUID kbAccountId, UUID kbPaymentId,
			Iterable<PluginProperty> properties, TenantContext context) throws PaymentPluginApiException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Pagination<PaymentTransactionInfoPlugin> searchPayments(String searchKey, Long offset, Long limit,
			Iterable<PluginProperty> properties, TenantContext context) throws PaymentPluginApiException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addPaymentMethod(UUID kbAccountId, UUID kbPaymentMethodId, PaymentMethodPlugin paymentMethodProps,
			boolean setDefault, Iterable<PluginProperty> properties, CallContext context)
			throws PaymentPluginApiException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deletePaymentMethod(UUID kbAccountId, UUID kbPaymentMethodId, Iterable<PluginProperty> properties,
			CallContext context) throws PaymentPluginApiException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public PaymentMethodPlugin getPaymentMethodDetail(UUID kbAccountId, UUID kbPaymentMethodId,
			Iterable<PluginProperty> properties, TenantContext context) throws PaymentPluginApiException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setDefaultPaymentMethod(UUID kbAccountId, UUID kbPaymentMethodId, Iterable<PluginProperty> properties,
			CallContext context) throws PaymentPluginApiException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<PaymentMethodInfoPlugin> getPaymentMethods(UUID kbAccountId, boolean refreshFromGateway,
			Iterable<PluginProperty> properties, CallContext context) throws PaymentPluginApiException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Pagination<PaymentMethodPlugin> searchPaymentMethods(String searchKey, Long offset, Long limit,
			Iterable<PluginProperty> properties, TenantContext context) throws PaymentPluginApiException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void resetPaymentMethods(UUID kbAccountId, List<PaymentMethodInfoPlugin> paymentMethods,
			Iterable<PluginProperty> properties, CallContext context) throws PaymentPluginApiException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public HostedPaymentPageFormDescriptor buildFormDescriptor(UUID kbAccountId, Iterable<PluginProperty> customFields,
			Iterable<PluginProperty> properties, CallContext context) throws PaymentPluginApiException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GatewayNotification processNotification(String notification, Iterable<PluginProperty> properties,
			CallContext context) throws PaymentPluginApiException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
