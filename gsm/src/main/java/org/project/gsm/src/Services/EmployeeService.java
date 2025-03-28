package org.project.gsm.src.Services;

import java.util.ArrayList;

import org.project.gsm.src.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	public ArrayList<Employee> getAllEmployees() {
		// TODO: Replace stub with select call
		return null;

	}

	public Employee getEmployeeById(String id) {
		int parsedId = Integer.parseInt(id);
		// TODO: Replace stub with select call
		return null;
	}

	public boolean createEmployee(Employee employee) {
		// TODO: replace stub with insert call
		return true;

	}

	public boolean updateEmployeeInDB(String id, Employee employee) {
		// TODO: Implement database update
		return true;
	}

	public boolean deleteEmployeeFromDB(String id) {
		// TODO: Implement database delete
		return true;
	}

}
