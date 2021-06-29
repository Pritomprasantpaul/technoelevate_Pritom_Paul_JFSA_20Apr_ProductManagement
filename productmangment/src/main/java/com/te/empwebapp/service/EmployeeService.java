package com.te.empwebapp.service;

import com.te.empwebapp.beans.EmployeeInfoBean;

public interface EmployeeService {

	public EmployeeInfoBean authenticate(int id, String pwd);
}
