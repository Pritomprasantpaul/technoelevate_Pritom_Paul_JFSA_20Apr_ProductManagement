package com.te.empwebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.te.empwebapp.beans.ProductInfoBean;
import com.te.empwebapp.dao.ProductDAO;

public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDAO pdao;

	@Override
	public ProductInfoBean getProductData(int pid) {
		if (pid <= 0) {
			return null;
		}
		return pdao.getProductData(pid);
	}

	@Override
	public boolean deleteProductData(int pid) {
		return pdao.deleteProductData(pid);
	}

	@Override
	public boolean addProduct(ProductInfoBean productInfoBean) {
		return pdao.addProduct(productInfoBean);
	}

	@Override
	public boolean updateRecord(ProductInfoBean productInfoBean) {
		return pdao.updateRecord(productInfoBean);
	}

	@Override
	public List<ProductInfoBean> getAllProduct() {
		return pdao.getAllProduct();
	}

}
