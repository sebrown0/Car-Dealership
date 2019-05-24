/**
 * 
 */
package tasks.details;

import tasks.abstract_tasks.Task;

/**
 * @author Steve Brown
 *
 */
public interface ManagerReportWithFollowOnTask extends ManagersTaskReport{
	Task getFollowOnTask();
}
