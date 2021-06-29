package com.te.empwebapp.service;

import java.util.List;

import com.te.empwebapp.beans.ProductInfoBean;

public interface ProductService {

	public ProductInfoBean getProductData(int pid);

	public boolean deleteProductData(int pid);

	public boolean addProduct(ProductInfoBean productInfoBean);

	public boolean updateRecord(ProductInfoBean productInfoBean);

	public List<ProductInfoBean> getAllProduct();
}