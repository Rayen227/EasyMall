package easymall.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.Products;

@Repository
@Mapper
public interface ProductsDao {
	public List<String> allcategories();
	public List<Products> prodlist(Map<String, Object> map);
	public Products prodInfo(String pid);
	//public Products oneProduct(String product_id);
	public Object findByImgurl(String imgurl);
	public void save(Products products);
	public void delprod(String id);
}
