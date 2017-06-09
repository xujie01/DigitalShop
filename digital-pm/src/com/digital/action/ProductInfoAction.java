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

//ʹ��@Controllerע����Spring������ע��ProductInfoActionʵ��
//ʹ��@Scope("prototype")ָ��ԭ��ģʽ
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

	private String code; // ��Ʒ����
	private String name; // ��Ʒ����
	private String brand; // ��ƷƷ��
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
	 * ��Ʒ�б�
	 */
	@Action(value = "/productList")
	public void productList() throws Exception {
		// SearchProductInfo spi=new SearchProductInfo();
		// BeanUtils.copyProperties(this, spi);
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		int pageIndex = 1;
		// ��ȡ��datagrid�ؼ��д�������ҳ��
		if (page != null) {
			pageIndex = Integer.parseInt(page);
		}
		// ��ȡ��datagrid�ؼ��д���������ѡ��ÿҳ��ʾ��¼
		int pageSize = Integer.parseInt(rows);
		// ��ȡ��Ʒ�ܼ�¼��
		int totalCount = productInfoService.getTotalCount(pi);
		// ����ҳ���ҳ��С��ȡ��ǰҳ��Ʒ�б�
		List<ProductInfo> piList = productInfoService.getByPage(pageIndex,
				pageSize, pi);
		// ����Ʒ�б�תΪjson��ʽ
		String jsonPiList = JSON.toJSONString(piList,
				SerializerFeature.DisableCircularReferenceDetect);
		// ���json�ַ���
		out.println("{\"total\":" + totalCount + ",\"rows\":" + jsonPiList
				+ "}");

	}

	/*
	 * ɾ����Ʒ,������Ʒ״̬����Ϊ0
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
			str = "{\"success\":\"true\",\"message\":\"ɾ���ɹ���\"}";
		else
			str = "{\"success\":\"false\",\"message\":\"ɾ��ʧ�ܣ�\"}";
		out.write(str);
	}

	private File pic; // ��װ�ϴ��ļ�������
	private String picFileName; // ��װ�ϴ��ļ�����������
	private String picContentType; // ��װ�ϴ��ļ�����������

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

	private File bigPic; // ��װ�ϴ��ļ�������
	private String bigPicFileName; // ��װ�ϴ��ļ�����������
	private String bigPicContentType; // ��װ�ϴ��ļ�����������

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
	 * ������Ʒ
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
			str = "{\"success\":\"false\",\"message\":\"ͼƬ��ʽ����ȷ��\"}";
		}else{
			if (picFileName != null) { // �ж��Ƿ�ѡ�����ϴ�СͼͼƬ
				// �õ���ǰweb�����µ�uploadĿ¼���ڱ����ľ���·�������û������ļ�����ᴴ��
				String targetDirectory = ServletActionContext.getServletContext()
						.getRealPath("/product_images");
				// �������ϴ��ļ�
				String targetFileName = generateFileName(picFileName, false);
				// ��ָ��Ŀ¼�����ļ�
				File target = new File(targetDirectory, targetFileName);
				// ��Ҫ�ϴ����ļ�copy��ȥ
				FileUtils.copyFile(pic, target);
				pi.setPic(targetFileName);
			}
			if (bigPicFileName != null) { // �ж��Ƿ�ѡ�����ϴ���ͼͼƬ
				// �õ���ǰweb�����µ�uploadĿ¼���ڱ����ľ���·�������û������ļ�����ᴴ��
				String targetDirectory = ServletActionContext.getServletContext()
						.getRealPath("/product_big_images");
				// �������ϴ��ļ�
				String targetFileName = generateFileName(bigPicFileName, true);
				// ��ָ��Ŀ¼�����ļ�
				File target = new File(targetDirectory, targetFileName);
				// ��Ҫ�ϴ����ļ�copy��ȥ
				FileUtils.copyFile(bigPic, target);
				pi.setBigpic(targetFileName);
			}
			int result = productInfoService.addProductInfo(pi);			
			if (result > 0)
				str = "{\"success\":\"true\",\"message\":\"��ӳɹ���\"}";
			else
				str = "{\"success\":\"false\",\"message\":\"���ʧ�ܣ�\"}";
		}
		out.write(str);
	}

	// �������ϴ��ļ�
	public String generateFileName(String fileName, boolean isBig) {
		String extension = getExtension(fileName);
		if (isBig) {
			return "b" + pi.getCode() + extension;
		} else {
			return pi.getCode() + extension;
		}
	}

	// ��ȡ�ϴ��ļ���չ��
	public String getExtension(String fileName) {
		int position = fileName.lastIndexOf(".");
		return fileName.substring(position);
	}

	/**
	 * �޸Ĳ�Ʒ��Ϣ
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
		if (picFileName != null) { // �ж��Ƿ�ѡ�����ϴ�СͼͼƬ
			// �õ���ǰweb�����µ�uploadĿ¼���ڱ����ľ���·�������û������ļ�����ᴴ��
			String targetDirectory = ServletActionContext.getServletContext()
					.getRealPath("/product_images");
			// �������ϴ��ļ�
			String targetFileName = generateFileName(picFileName, false);
			// ��ָ��Ŀ¼�����ļ�
			File target = new File(targetDirectory, targetFileName);
			// ��Ҫ�ϴ����ļ�copy��ȥ
			FileUtils.copyFile(pic, target);
			editPi.setPic(targetFileName);
		}
		if (bigPicFileName != null) { // �ж��Ƿ�ѡ�����ϴ���ͼͼƬ
			// �õ���ǰweb�����µ�uploadĿ¼���ڱ����ľ���·�������û������ļ�����ᴴ��
			String targetDirectory = ServletActionContext.getServletContext()
					.getRealPath("/product_big_images");
			// �������ϴ��ļ�
			String targetFileName = generateFileName(bigPicFileName, true);
			// ��ָ��Ŀ¼�����ļ�
			File target = new File(targetDirectory, targetFileName);
			// ��Ҫ�ϴ����ļ�copy��ȥ
			FileUtils.copyFile(bigPic, target);
			editPi.setBigpic(targetFileName);
		}
		String str = "";
		try {
			productInfoService.updateProductInfo(editPi);
			str = "{\"success\":\"true\",\"message\":\"�޸ĳɹ���\"}";
		} catch (Exception e) {
			str = "{\"success\":\"false\",\"message\":\"�޸�ʧ�ܣ�\"}";
		}
		out.write(str);
	}

}
