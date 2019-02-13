package order_deptartment;

import customer.Customer;
import enums.OrderStatus;
import stock_department.CarDetails;

/*
 * Wrapper class for orders: OrderListTable.
 * 
 */
public class Order {

	private OrderListTable orderListTable = new OrderListTable();
	private CarDetails carDetails;
	private Customer customer;
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setOrderListTable(OrderListTable orderListTable) {
		this.orderListTable = orderListTable;
	}

	public OrderListTable getOrderListTable() {
		return orderListTable;
	}

	public CarDetails getCarDetails() {
		return carDetails;
	}

	private String createModelVin(long customerId, long saleId, long orderId) { 
		return ("Order." + customerId  + "." + saleId + "." + orderId);
	}
	
	/*
	 * Concrete order has been placed.
	 * 		Create unique order id.
	 * 		Set the status of the order.
	 * 		Update the interested parties, i.e. customer and salesperson. 
	 */
	public void updateOrder(long orderId) {
		long customerId = this.customer.getId();
		long saleId = this.customer.getSalesPerson().getId();
		String modelVin = createModelVin(customerId, saleId , orderId);
	
		this.carDetails.updateModelVin(modelVin);
	
		this.orderListTable.setModel_id(modelVin);
		this.orderListTable.setCustomer_id(customerId);
		this.orderListTable.setOrder_status_id(OrderStatus.ORDER.ordinal()); 
		this.orderListTable.setSales_id(saleId);		
	}

	public Order order(Customer customer) {
		this.carDetails = customer.getCustomerRequirements().getCarDetails();
		this.customer = customer;
		
		return this;
	}
		
	public static class OrderListTable{
		private long order_status_id;
		private long customer_id;
		private long sales_id;
		private String model_id;
		
		public long getOrder_status_id() {
			return order_status_id;
		}
		public void setOrder_status_id(long order_status_id) {
			this.order_status_id = order_status_id;
		}
		public long getCustomer_id() {
			return customer_id;
		}
		public void setCustomer_id(long customer_id) {
			this.customer_id = customer_id;
		}
		public long getSales_id() {
			return sales_id;
		}
		public void setSales_id(long sales_id) {
			this.sales_id = sales_id;
		}
		public String getModel_id() {
			return model_id;
		}
		public void setModel_id(String model_id) {
			this.model_id = model_id;
		}	
	}
	
}
