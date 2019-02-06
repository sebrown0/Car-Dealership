/**
 * 
 */
package containers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brown
 * Generic container(s) for the Application.
 */
public class AppContainers {
	
	/*
	 * Accepts a generic type (T) and adds it to a new list.
	 */
	public static class ListContainer<T>{
		
		List<T> aList;

		public ListContainer(T list) {
			aList = new ArrayList<T>();
			aList.add(list);
		}
		
		public List<T> getList() {
			return this.aList;
		}
		
	}

}
