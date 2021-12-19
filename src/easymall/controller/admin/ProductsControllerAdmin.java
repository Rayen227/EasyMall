package easymall.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import easymall.controller.BaseController;
import easymall.po.Products;
import easymall.pojo.MyProducts;
import easymall.service.ProductsService;

@Controller
@RequestMapping("/admin")
public class ProductsControllerAdmin extends BaseController {
	@Autowired
	private ProductsService productsService;
	@RequestMapping("/prodlist")
	public String prodlist(Model model) {
		double _minPrice=0;
		double _maxPrice=Double.MAX_VALUE;
		Map<String, Object> map=new HashMap<>();
		map.put("name", "");
		map.put("category", "");
		map.put("minprice", _minPrice);
		map.put("maxprice", _maxPrice);
		List<Products> products=productsService.prodlist(map);
		System.out.println(products.size());
		model.addAttribute("products",products);
		return "/admin/prod_list";
	}
	@RequestMapping("/addprod")
	public String addprod(Model model) {
		List<String> categories=productsService.allcategories();
		model.addAttribute("categories",categories);
		model.addAttribute("myproducts",new MyProducts());
		return "/admin/add_prod";
	}
	@RequestMapping("/save")
	public String save(@Valid @ModelAttribute MyProducts myproducts, 
			HttpServletRequest request, Model model)
			throws Exception {
		String msg=productsService.save(myproducts,request);
		model.addAttribute("msg",msg);
		return "redirect:/admin/addprod";
	}
	@RequestMapping("/delprod")
	public String delprod(String id) {
		productsService.delprod(id);
		return "redirect:/admin/prodlist";
	}

}
