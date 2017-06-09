package com.digital.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.digital.entity.ProductInfo;
import com.digital.service.ProductInfoService;
import com.opensymphony.xwork2.ActionSupport;

//使用@Controller注解在Spring容器中注册ProductInfoAction实例
//使用@Scope("prototype")指定原型模式
@Controller
@Scope("prototype")
public class ProductInfoAction extends ActionSupport implements RequestAware,
		ServletRequestAware, ServletResponseAware, SessionAware {

	ProductInfo pi;

	public ProductInfo getPi() {
		return pi;
	}

	public void setPi(ProductInfo pi) {
		this.pi = pi;
	}

	Map<String, Object> request;
	Map<String, Object> session;
	HttpServletRequest req;
	HttpServletResponse resp;

	@Override
	public void setServletResponse(HttpServletResponse resp) {
		this.resp = resp;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Autowired
	ProductInfoService productInfoService;

	String page;
	String rows;

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	private String code; // 产品编码
	private String name; // 产品名称
	private String brand; // 产品品牌
	private double priceFrom;
	private double priceTo;
	private int tid;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(double priceFrom) {
		this.priceFrom = priceFrom;
	}

	public double getPriceTo() {
		return priceTo;
	}

	public void setPriceTo(double priceTo) {
		this.priceTo = priceTo;
	}

	/*
	 * 产品列表
	 */
	@Action(value = "/productList")
	public void productList() throws Exception {
		// SearchProductInfo spi=new SearchProductInfo();
		// BeanUtils.copyProperties(this, spi);
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		int pageIndex = 1;
		// 获取从datagrid控件中传递来的页码
		if (page != null) {
			pageIndex = Integer.parseInt(page);
		}
		// 获取从datagrid控件中传递来的所选的每页显示记录
		int pageSize = Integer.parseInt(rows);
		// 获取商品总记录数
		int totalCount = productInfoService.getTotalCount(pi);
		// 根据页码和页大小获取当前页商品列表
		List<ProductInfo> piList = productInfoService.getByPage(pageIndex,
				pageSize, pi);
		// 将商品列表转为json格式
		String jsonPiList = JSON.toJSONString(piList,
				SerializerFeature.DisableCircularReferenceDetect);
		// 输出json字符串
		out.println("{\"total\":" + totalCount + ",\"rows\":" + jsonPiList
				+ "}");

	}

	/*
	 * 删除商品,即将商品状态设置为0
	 */
	@Action(value = "/updateStatus")
	public void updateStatus() throws Exception {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		String id = req.getParameter("id");
		id = "(" + id.substring(0, id.length() - 1) + ")";
		int result = productInfoService.updateStatus(id);
		String str = "";
		if (result > 0)
			str = "{\"success\":\"true\",\"message\":\"删除成功！\"}";
		else
			str = "{\"success\":\"false\",\"message\":\"删除失败！\"}";
		out.write(str);
	}

	private File pic; // 封装上传文件的属性
	private String picFileName; // 封装上传文件的名称属性
	private String picContentType; // 封装上传文件的类型属性

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public String getPicContentType() {
		return picContentType;
	}

	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}

	private File bigPic; // 封装上传文件的属性
	private String bigPicFileName; // 封装上传文件的名称属性
	private String bigPicContentType; // 封装上传文件的类型属性

	public File getBigPic() {
		return bigPic;
	}

	public void setBigPic(File bigPic) {
		this.bigPic = bigPic;
	}

	public String getBigPicFileName() {
		return bigPicFileName;
	}

	public void setBigPicFileName(String bigPicFileName) {
		this.bigPicFileName = bigPicFileName;
	}

	public String getBigPicContentType() {
		return bigPicContentType;
	}

	public void setBigPicContentType(String bigPicContentType) {
		this.bigPicContentType = bigPicContentType;
	}

	/**
	 * 新增产品
	 * 
	 * @throws IOException
	 */
	@Action(value = "/addProduct")
	public void addProduct() throws IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		String picFileExtName = getExtension(picFileName);
		String bigPicFileExtName = getExtension(bigPicFileName);
		String str = "";
		boolean flag=(!".png".equals(picFileExtName) && !".jpeg".equals(picFileExtName)
				&& !".jpg".equals(picFileExtName) && !".gif".equals(picFileExtName))
				|| (!".png".equals(bigPicFileExtName) && !".jpeg".equals(bigPicFileExtName)
						&& !".jpg".equals(bigPicFileExtName) && !".gif".equals(bigPicFileExtName));
		if (flag) {
			str = "{\"success\":\"false\",\"message\":\"图片格式不正确！\"}";
		}else{
			if (picFileName != null) { // 判断是否选择了上传小图图片
				// 得到当前web工程下的upload目录的在本机的绝对路径，如果没有这个文件夹则会创建
				String targetDirectory = ServletActionContext.getServletContext()
						.getRealPath("/product_images");
				// 重命名上传文件
				String targetFileName = generateFileName(picFileName, false);
				// 在指定目录创建文件
				File target = new File(targetDirectory, targetFileName);
				// 把要上传的文件copy过去
				FileUtils.copyFile(pic, target);
				pi.setPic(targetFileName);
			}
			if (bigPicFileName != null) { // 判断是否选择了上传大图图片
				// 得到当前web工程下的upload目录的在本机的绝对路径，如果没有这个文件夹则会创建
				String targetDirectory = ServletActionContext.getServletContext()
						.getRealPath("/product_big_images");
				// 重命名上传文件
				String targetFileName = generateFileName(bigPicFileName, true);
				// 在指定目录创建文件
				File target = new File(targetDirectory, targetFileName);
				// 把要上传的文件copy过去
				FileUtils.copyFile(bigPic, target);
				pi.setBigpic(targetFileName);
			}
			int result = productInfoService.addProductInfo(pi);			
			if (result > 0)
				str = "{\"success\":\"true\",\"message\":\"添加成功！\"}";
			else
				str = "{\"success\":\"false\",\"message\":\"添加失败！\"}";
		}
		out.write(str);
	}

	// 重命名上传文件
	public String generateFileName(String fileName, boolean isBig) {
		String extension = getExtension(fileName);
		if (isBig) {
			return "b" + pi.getCode() + extension;
		} else {
			return pi.getCode() + extension;
		}
	}

	// 获取上传文件扩展名
	public String getExtension(String fileName) {
		int position = fileName.lastIndexOf(".");
		return fileName.substring(position);
	}

	/**
	 * 修改产品信息
	 * 
	 * @throws IOException
	 */
	@Action(value = "/updateProduct")
	public void updateProduct() throws IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		ProductInfo editPi = productInfoService.getById(pi.getId());
		editPi.setType(pi.getType());
		editPi.setName(pi.getName());
		editPi.setCode(pi.getCode());
		editPi.setBrand(pi.getBrand());
		editPi.setNum(pi.getNum());
		editPi.setPrice(pi.getPrice());
		editPi.setIntro(pi.getIntro());
		editPi.setStatus(pi.getStatus());
		if (picFileName != null) { // 判断是否选择了上传小图图片
			// 得到当前web工程下的upload目录的在本机的绝对路径，如果没有这个文件夹则会创建
			String targetDirectory = ServletActionContext.getServletContext()
					.getRealPath("/product_images");
			// 重命名上传文件
			String targetFileName = generateFileName(picFileName, false);
			// 在指定目录创建文件
			File target = new File(targetDirectory, targetFileName);
			// 把要上传的文件copy过去
			FileUtils.copyFile(pic, target);
			editPi.setPic(targetFileName);
		}
		if (bigPicFileName != null) { // 判断是否选择了上传大图图片
			// 得到当前web工程下的upload目录的在本机的绝对路径，如果没有这个文件夹则会创建
			String targetDirectory = ServletActionContext.getServletContext()
					.getRealPath("/product_big_images");
			// 重命名上传文件
			String targetFileName = generateFileName(bigPicFileName, true);
			// 在指定目录创建文件
			File target = new File(targetDirectory, targetFileName);
			// 把要上传的文件copy过去
			FileUtils.copyFile(bigPic, target);
			editPi.setBigpic(targetFileName);
		}
		String str = "";
		try {
			productInfoService.updateProductInfo(editPi);
			str = "{\"success\":\"true\",\"message\":\"修改成功！\"}";
		} catch (Exception e) {
			str = "{\"success\":\"false\",\"message\":\"修改失败！\"}";
		}
		out.write(str);
	}

}
