package easymall.service;

import java.util.List;

import easymall.po.OrderItem;
import easymall.po.Orders;

public interface OrderService {

	public void addOrder(String cartIds, Orders myOrder);

	public List<Orders> findOrderByUserId(Integer userId);

	public List<OrderItem> orderitem(String id);

	public void delorder(String id);

	public void payorder(String id);
	
}
