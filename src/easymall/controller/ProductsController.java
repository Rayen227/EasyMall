package easymall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import easymall.po.Products;
import easymall.service.ProductsService;

@Controller
public class ProductsController {
	@Autowired
	private ProductsService productsService;
	
	//��ʾ��Ʒ
	@RequestMapping("/prodlist")
	public String prodlist(HttpServletRequest request,Model model) {
		List<String> allcategories=productsService.allcategories();
		model.addAttribute("categories", allcategories);
		
		String name=request.getParameter("name");
		String category=request.getParameter("category");
		String minprice=request.getParameter("minprice");
		String maxprice=request.getParameter("maxprice");
		
		//Ϊ��������Ĭ��ֵ
		double _minPrice=0;
		double _maxPrice=Double.MAX_VALUE;
		
		String reg="^\\d+$";//ֻ����������
		if(minprice!=null && !"".equals(minprice) &&minprice.matches(reg)) {
			_minPrice=Double.parseDouble(minprice);
		}
		if(maxprice!=null && !"".equals(maxprice) &&maxprice.matches(reg)) {
			double maxp=Double.parseDouble(maxprice);
			if(maxp>=_minPrice) _maxPrice=maxp;
		}
		Map<String, Object> map=new HashMap<>();
		map.put("name", name);
		map.put("category", category);
		map.put("minprice", _minPrice);
		map.put("maxprice", _maxPrice);
		
		List<Products> prodlist=productsService.prodlist(map);
		model.addAttribute("prodlist", prodlist);
		
		return "prod_list";
	}
	
	@RequestMapping("/prodInfo")
	public String prodInfo(String pid,Model model) {
		
		Products product=productsService.prodInfo(pid);
		model.addAttribute("product", product);
		
		return "prod_info";
	}
	
	@RequestMapping("prodclass/{category}")
	public String prodclass(@PathVariable String category,Model model) {
		List<String> allcategories=productsService.allcategories();
		model.addAttribute("categories", allcategories);
		
		double _minPrice=0;
		double _maxPrice=Double.MAX_VALUE;
		
		Map<String, Object> map=new HashMap<>();
		map.put("category", category);
		map.put("minprice", _minPrice);
		map.put("maxprice", _maxPrice);
		List<Products> products=productsService.prodlist(map);
		model.addAttribute("prodlist", products);
		return "prod_list";
	}
}
