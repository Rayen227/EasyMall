package easymall.dao;

import java.util.List;

import easymall.po.OrderItem;

public interface OrderItemDao {

	public void addOrderItem(OrderItem orderItem);

	public List<OrderItem> orderitem(String id);

	public void delorderitem(String id);

}
