package easymall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.Cart;
import easymall.pojo.MyCart;
@Repository("cartDao")
@Mapper
public interface CartDao {

	public Cart findCart(Cart cart);

	public void addCart(Cart cart);

	public void updateCart(Cart cart);

	public List<MyCart> showcart(int id);
	
	public void updateBuyNum(Cart cart);
	
	public void delCart(Integer cartID);

	public MyCart findByCartID(Integer cartID);

}
