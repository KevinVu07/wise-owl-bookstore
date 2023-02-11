package model;

import java.util.Date;
import java.util.List;

public class CheckoutDetail {
	private List<OrderItemModel> orderItemList;
	private double subTotal;
	private double shippingFee;
	private double tax;
	private double total;
	private Date checkOutDate;
	
	public CheckoutDetail() {}

	public List<OrderItemModel> getOrderList() {
		return orderItemList;
	}

	public void setOrderList(List<OrderItemModel> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public String getSubTotal() {
		return String.format("%.2f", subTotal);
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public String getShippingFee() {
		return String.format("%.2f", shippingFee);
	}

	public void setShippingFee(double shippingFee) {
		this.shippingFee = shippingFee;
	}

	public String getTax() {
		return String.format("%.2f", tax);
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public String getTotal() {
		return String.format("%.2f", total);
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	};
	
	
}
