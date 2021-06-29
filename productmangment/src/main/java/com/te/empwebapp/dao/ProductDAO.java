package com.te.empwebapp.dao;

import java.util.List;

import com.te.empwebapp.beans.ProductInfoBean;

public interface ProductDAO {

		public ProductInfoBean getProductData(int pid);

		public boolean deleteProductData(int pid);

		public boolean addProduct(ProductInfoBean productInfoBean);

		public boolean updateRecord(ProductInfoBean productInfoBean);

		public List<ProductInfoBean> getAllProduct();
}
