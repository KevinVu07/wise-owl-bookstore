package payment.util;

import java.util.*;

import javax.servlet.http.HttpSession;

import com.paypal.api.payments.*;
import com.paypal.base.rest.*;

import model.CheckoutDetail;
import model.OrderItemModel;


public class PaymentServices {
    private static final String CLIENT_ID = "AXQERl-l44oErZwKmGBpTl5nxcYW_fPhVJzEwDQn4B3yiyaNPt5qdHaDOuo9zXDEYnOyIx7Evdf__bDH";
    private static final String CLIENT_SECRET = "EC08RPCLXcpFqYe95ZNQxlZKeSrk71KmSBklaoHLUhrC_R-yI8jwR9KIhTNko36SpBh15GJeOb_nvR8V";
    private static final String MODE = "sandbox";
 
    public String authorizePayment(CheckoutDetail checkoutDetail, String firstName, String lastName, String email)        
            throws PayPalRESTException {     
    	
        Payer payer = getPayerInformation(firstName, lastName, email);
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(checkoutDetail);
         
        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");
 
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
 
        Payment approvedPayment = requestPayment.create(apiContext);
 
        return getApprovalLink(approvedPayment);
 
    }
     
    private Payer getPayerInformation(String firstName, String lastName, String email) {
    	
    	
    	Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
         
        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("firstName")
                 .setLastName("lastName")
                 .setEmail("email");
         
        payer.setPayerInfo(payerInfo);
         
        return payer;
    }
     
    private RedirectUrls getRedirectURLs() {
    	
    	RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://www.wiseowlbookshop.com/order-summary");
        redirectUrls.setReturnUrl("http://www.wiseowlbookshop.com/review-payment");
         
        return redirectUrls;
    }
     
    private List<Transaction> getTransactionInformation(CheckoutDetail checkoutDetail) {
    	Details details = new Details();
        details.setShipping(checkoutDetail.getShippingFee());
        details.setSubtotal(checkoutDetail.getSubTotal());
        details.setTax(checkoutDetail.getTax());
     
        Amount amount = new Amount();
        amount.setCurrency("AUD");
        
        amount.setTotal(checkoutDetail.getTotal());
        
        amount.setDetails(details);
     
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("Purchase from wise owl bookstore");
         
        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();
         
        Item item = new Item();
        item.setCurrency("AUD");
        item.setName("Book purchase from Wise Owl Bookstore");
        item.setPrice(checkoutDetail.getSubTotal());
        item.setTax(checkoutDetail.getTax());
        item.setQuantity("1");
         
        items.add(item);
        itemList.setItems(items);
        transaction.setItemList(itemList);
         
        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(transaction);  
         
        return listTransaction;
    }
     
    private String getApprovalLink(Payment approvedPayment) {
    	 List<Links> links = approvedPayment.getLinks();
    	    String approvalLink = null;
    	     
    	    for (Links link : links) {
    	        if (link.getRel().equalsIgnoreCase("approval_url")) {
    	            approvalLink = link.getHref();
    	            break;
    	        }
    	    }      
    	     
    	    return approvalLink;
    }
    
    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return Payment.get(apiContext, paymentId);
    }
    
    public Payment executePayment(String paymentId, String payerId)
            throws PayPalRESTException {
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
     
        Payment payment = new Payment().setId(paymentId);
     
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
     
        return payment.execute(apiContext, paymentExecution);
    }
}
