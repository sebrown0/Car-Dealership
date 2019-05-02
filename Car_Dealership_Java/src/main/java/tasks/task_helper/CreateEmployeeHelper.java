/**
 * 
 */
package tasks.task_helper;

import java.sql.ResultSet;

import enums.HRDeptSP;

/**
 * @author Steve Brown
 *
 */
public interface CreateEmployeeHelper {
	ResultSet getEmployeesFromDB(String deptID, HRDeptSP storedProc);
	void updateTeam(ResultSet empRs);
	void addDepartmentManager(ResultSet empRs);
}
