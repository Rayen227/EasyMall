package easymall.pojo;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class MyProducts {
	@NotNull(message="商品名称不能为空")
	private String name;
	@NotNull(message="商品价格不能为空")
	private Double price;
	private String category;
	private Integer pnum;
	private MultipartFile imgurl;	
	private String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getPnum() {
		return pnum;
	}
	public void setPnum(Integer pnum) {
		this.pnum = pnum;
	}
	public MultipartFile getImgurl() {
		return imgurl;
	}
	public void setImgurl(MultipartFile imgurl) {
		this.imgurl = imgurl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
