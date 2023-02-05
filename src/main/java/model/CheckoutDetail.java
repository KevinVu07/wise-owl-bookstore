package model;

import java.util.Date;
import java.util.List;

public class CheckoutDetail {
	private List<OrderListModel> orderList;
	private double subTotal;
	private double shippingFee;
	private double tax;
	private double total;
	private Date checkOutDate;
	
	public CheckoutDetail() {}

	public List<OrderListModel> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderListModel> orderList) {
		this.orderList = orderList;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(double shippingFee) {
		this.shippingFee = shippingFee;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getTotal() {
		return total;
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
