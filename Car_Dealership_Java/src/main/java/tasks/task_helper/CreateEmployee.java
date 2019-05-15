package tasks.task_helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.StoredProcedure;
import database.StoredProcedure.QueryBuilder;
import departments.department.Department;
import departments.department.EmployeeDetails;
import departments.department.ObjectDetails;
import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;
import enums.HRDeptSP;

/**
 * 
 * @author Steve Brown
 *
 *  A helper to return a result set with either a department's manager 
 *  or its employees.
 */
public class CreateEmployee implements CreateEmployeeHelper{

	private Department dept;
	
	public CreateEmployee(Department dept) {
		this.dept = dept;
	}

	@Override
	public ResultSet getEmployeesFromDB(String deptID, HRDeptSP storedProc) {
		String stmnt = QueryBuilder.build(deptID, storedProc.value());
		StoredProcedure emp = dept.database().executeSP(stmnt);
		return emp.getRs();
	}
	
	@Override
	public void updateTeam(ResultSet empRs) {
		parseEmployees(empRs);
	}

	@Override
	public void addDepartmentManager(ResultSet empRs) {
		parseEmployees(empRs);
	}
	
	private void parseEmployees(ResultSet empRs) {
		try {
			while(empRs.next()) {
				EmployeeDetails emp = getEmployeeFromRS(empRs);
				if(emp != null)
					addStaffMember(emp, dept);
			}
		} catch (SQLException e) {
			ErrorHandler.checkError(ErrorCodes.TASK_ROLL_CALL, e.getMessage(), dept.log());
		}
	}

	private EmployeeDetails getEmployeeFromRS(ResultSet empRs) {
		EmployeeDetails emp = new ObjectDetails();
		
		try {
			emp.setDeptID(empRs.getString("dept_id"));
			emp.setLastName(empRs.getString("last_name"));
			emp.setID(empRs.getLong("hr_emp_id"));
			emp.setSeniority(empRs.getString("seniority"));
			emp.setRole(empRs.getString("role_name"));
			emp.setFirstName(empRs.getString("first_name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}
	
	private void addStaffMember(EmployeeDetails emp, Department dept) {
		if(emp.getSeniority().matches("Manager"))
			dept.setDeptManager(emp);
		else
			dept.addDeptStaffMember(emp);	
	}
	
}
