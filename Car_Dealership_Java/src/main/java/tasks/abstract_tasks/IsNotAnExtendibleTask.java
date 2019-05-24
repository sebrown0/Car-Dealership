/**
 * 
 */
package tasks.abstract_tasks;

/**
 * @author Steve Brown
 *
 * A task that has a no follow on task is not extendible.
 * 
 * This is the default for all tasks. It implemented by Task 
 * and therefore inherited by all tasks. 
 * 
 * To make a task extendible it should implement IsAnExtendibleTask.  
 */
public interface IsNotAnExtendibleTask {
	default boolean isTaskExtendible() { return false; }
}
