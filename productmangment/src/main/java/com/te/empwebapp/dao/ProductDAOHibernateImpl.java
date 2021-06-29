package com.te.empwebapp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.te.empwebapp.beans.ProductInfoBean;

@Repository
public class ProductDAOHibernateImpl implements ProductDAO {

	@Override
	public ProductInfoBean getProductData(int pid) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		ProductInfoBean infoBean = manager.find(ProductInfoBean.class, pid);
		manager.close();
		factory.close();
		return infoBean;
	}

	@Override
	public boolean addProduct(ProductInfoBean productInfoBean) {
		boolean isInserted = false;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		try {
			transaction.begin();
			manager.persist(productInfoBean);
			transaction.commit();
			isInserted = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}

		return isInserted;
	}

	@Override
	public boolean updateRecord(ProductInfoBean productInfoBean) {
		boolean isUpdated = false;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			ProductInfoBean actualInfo = manager.find(ProductInfoBean.class, productInfoBean.getPid());

			if (productInfoBean.getPname() != null && productInfoBean.getPname() != "") {
				actualInfo.setPname(productInfoBean.getPname());
			}

			transaction.commit();
			isUpdated = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}

		return isUpdated;

	}

	@Override
	public List<ProductInfoBean> getAllProduct() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("from ProductInfoBean");
		ArrayList<ProductInfoBean> productInfoBean = new ArrayList<ProductInfoBean>();
		try {
			productInfoBean = (ArrayList<ProductInfoBean>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			productInfoBean = null;
		}

		return productInfoBean;
	}

	@Override
	public boolean deleteProductData(int pid) {
		boolean isDeleted = false;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			ProductInfoBean infoBean = manager.find(ProductInfoBean.class, pid);
			manager.remove(infoBean);
			transaction.commit();
			isDeleted = true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}

		return isDeleted;
	}

}
