package easymall.poiexcel;

import java.util.Date;

public class person {
	private String name;
	private double price;
	private int sellNum;
	private int storeNum;
	
	
	public person(String name, double price, int sellNum, int storeNum) {
		super();
		this.name = name;
		this.price = price;
		this.sellNum = sellNum;
		this.storeNum = storeNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSellNum() {
		return sellNum;
	}
	public void setSellNum(int sellNum) {
		this.sellNum = sellNum;
	}
	public int getStoreNum() {
		return storeNum;
	}
	public void setStoreNum(int storeNum) {
		this.storeNum = storeNum;
	}
	@Override
	public String toString() {
		return "person [name=" + name + ", price=" + price + ", sellNum=" + sellNum + ", storeNum=" + storeNum + "]";
	}
}
