/**
 * 
 */
package tasks.abstract_tasks;

/**
 * @author Steve Brown
 *
 * A task that has a follow on task is extendible.
 * i.e. Customer requirements follows New Lead.
 */
public interface IsAnExtendibleTask {
	default boolean isTaskExtendible() { return true; }
	void setFollowOnTask();
}
