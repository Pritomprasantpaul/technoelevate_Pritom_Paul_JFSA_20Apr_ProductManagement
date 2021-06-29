package com.te.empwebapp.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.te.empwebapp.beans.EmployeeInfoBean;
import com.te.empwebapp.beans.ProductInfoBean;
import com.te.empwebapp.service.ProductService;

@Controller
public class ProductController {

	@Autowired(required = false)
	private ProductService service;

	@GetMapping("/searchPage")
	public String getSearchPage(HttpSession session, ModelMap map) {
		ProductInfoBean infoBean = (ProductInfoBean) session.getAttribute("loggedIn");
		System.out.println(infoBean);
		if (infoBean != null) {
			System.out.println("valid");
			return "productSearchPage";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "empLogin";
		}
	}// getSearchPage

	@GetMapping("/search")
	public String getProductData(int pid,
			@SessionAttribute(name = "loggedIn", required = false) ProductInfoBean infoBean, ModelMap map) {
		if (infoBean != null) {
			ProductInfoBean productInfoBean = service.getProductData(pid);
			if (productInfoBean != null) {
				map.addAttribute("productData", productInfoBean);
			} else {
				map.addAttribute("errMsg", "Data not Found");
			}
			return "productSearchPage";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "empLogin";
		}
	}

	@GetMapping("/getDeleteForm")
	public String getDeleteForm(@SessionAttribute(name = "loggedIn", required = false) ProductInfoBean infoBean,
			ModelMap map) {
		if (infoBean != null) {
			return "deleteProduct";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "empLogin";
		}
	}//

	@GetMapping("/delete")
	public String deleteData(int pid, @SessionAttribute(name = "loggedIn", required = false) EmployeeInfoBean infoBean,
			ModelMap map) {
		if (infoBean != null) {
			if (service.deleteProductData(pid)) {
				map.addAttribute("msg", "Data Deleted successfully for id : " + pid);
			} else {
				map.addAttribute("msg", "Could not find Record for id : " + pid);
			}
			return "deleteEmp";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "empLogin";
		}
	}
	@GetMapping("/addProduct")
	public String getAddFrom(@SessionAttribute(name = "loggedIn", required = false) ProductInfoBean infoBean,
			ModelMap map) {
		if (infoBean != null) {
			return "insertProduct";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "empLogin";
		}

	}//

	@PostMapping("/add")
	public String addProduct(ProductInfoBean productInfoBean,
			@SessionAttribute(name = "loggedIn", required = false) ProductInfoBean infoBean, ModelMap map) {
		if (infoBean != null) {
			if (service.addProduct(productInfoBean)) {
				map.addAttribute("msg", "Successfully Inserted");
			} else {
				map.addAttribute("msg", "Failed to Insert");
			}
			return "insertProduct";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "empLogin";
		}

	}// add Product

	@GetMapping("/updateProduct")
	public String getUpadatePage(@SessionAttribute(name = "loggedIn", required = false) ProductInfoBean infoBean,
			ModelMap map) {
		if (infoBean != null) {
			map.addAttribute("id", infoBean);
			return "updateProduct";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "empLogin";

		}
	}//

	@PostMapping("/update")
	public String updateProductData(@SessionAttribute(name = "loggedIn", required = false) ProductInfoBean infoBean,
			ModelMap map, ProductInfoBean productInfoBean) {
		if (infoBean != null) {
			if (service.updateRecord(productInfoBean)) {
				map.addAttribute("msg", "Updated Successfully");
				map.addAttribute("id", productInfoBean);
			} else {
				map.addAttribute("msg", "Updation Failed");
				map.addAttribute("id", infoBean);
			}
			return "updateProduct";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "empLogin";
		}
	}//

	@GetMapping("/getAll")
	public String getAllRecords(@SessionAttribute(name = "loggedIn", required = false) ProductInfoBean infoBean,
			ModelMap map) {
		if (infoBean != null) {
			List<ProductInfoBean> productInfoBeans = service.getAllProduct();
			if (productInfoBeans != null) {

				map.addAttribute("infos", productInfoBeans);
			}else {
				map.addAttribute("errMsg", "No Records Found");
			}
			map.addAttribute("name", infoBean.getPname());	
			return "Home";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "empLogin";
		}
	}

}
