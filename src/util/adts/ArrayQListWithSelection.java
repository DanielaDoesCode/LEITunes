/**
 * 
 */
package util.adts;

import java.util.ArrayList;
/**
 * @author fc58199 - Daniela Camarinha
 * @author fc58193 - Rafael Ribeiro
 * @param <E>
 *
 */
public class ArrayQListWithSelection<E> extends AbsQListWithSelection<E> {

	
	public ArrayQListWithSelection(ArrayList<E> list, int selectedIndex) { 
	    super(list, selectedIndex);
	}
	
	
	
	public ArrayQListWithSelection() {
		super();
	}


	
	
	

}
