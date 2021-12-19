package easymall.service;

import java.util.List;

import easymall.po.Cart;
import easymall.pojo.MyCart;

public interface CartService {

	public Cart findCart(Cart cart) ;

	public void addCart(Cart cart) ;

	public void updateCart(Cart cart);

	public List<MyCart> showcart(int id);

	public void updateBuyNum(Cart cart);

	public void delCart(Integer cartID);

	public MyCart findByCartID(Integer cartID);

}
