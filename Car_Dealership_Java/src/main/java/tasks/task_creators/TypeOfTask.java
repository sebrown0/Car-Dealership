/**
 * 
 */
package tasks.task_creators;

/**
 * @author Steve Brown
 *
 *         The different type of tasks that can be run. 1. ATOMIC: Run once and
 *         immediately. 2. SCHEDULED: Run once at a specified time. 3.
 *         REPEATABLE: Run immediately for a specified number of times; 4.
 *         SCHEDULED_REPEATABLE: Run at the scheduled time for a specified
 *         number of times; 5. REPEAT: Run continuously and immediately.
 */
public enum TypeOfTask {

	ATOMIC, SCHEDULED, REPEATABLE, SCHEDULED_REPEATABLE, REPEAT;
}
