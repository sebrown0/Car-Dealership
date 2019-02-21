/**
 * 
 */
package hr_department;

import department.Department;
import sales_department.SalesDept;

/**
 * @author Brown
 *
 */
public interface StaffDuty {

	void performDuty(Department department);

	void performDuty(SalesDept department);
}
