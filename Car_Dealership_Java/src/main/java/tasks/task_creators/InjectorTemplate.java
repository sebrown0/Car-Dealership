
/**
 * @author Steve Brown
 *
 *	**TEMPLATE FOR INJECTOR** 
 *	**UNCOMMENT AND REPLACE THE INJECTOR, TASK AND DEPT**
 *	**KEEP ONE OF THE METHODS DEPENDING ON THE IMPLEMENTATION CHOSEN**
 *
 *  Return a new task TASK for the DEPT Department. 
 * 	To be executed at the scheduled time.
 */
//public class InjectorTemplate implements INJECTOR {
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see tasks.TaskInjector#getNewTask(depts.Department, timer.TimeFormatter)
//	 */
//	@Override
//	public TaskConsumer getNewTask(Department department) {
//		// TODO - Handle cast exception is wrong department is passed.
//		return new Task(new TASK((DEPT) department, TypeOfTask.ATOMIC));
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see tasks.TaskInjector#getNewTask(depts.Department, timer.TimeFormatter,
//	 * tasks.TypeOfTask)
//	 */
//	@Override
//	public TaskConsumer getNewTask(Department department, TypeOfTask typeOfTask, TaskSchedule scheduledTime) {
//		// TODO - Handle cast exception is wrong department is passed.
//		return new Task(new TASK((DEPT) department, typeOfTask, scheduledTime));
//	}
//}
