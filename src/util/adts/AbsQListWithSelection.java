
package util.adts;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Data Structure composed by an ArrayList that lets an element be selected
 * @author fc58199 - Daniela Camarinha
 * @author fc58193 - Rafael Ribeiro
 * @param <E>
 */
public abstract class AbsQListWithSelection<E> implements QListWithSelection<E>{


	protected ArrayList<E> list;
	protected int selectedIndex;



	protected AbsQListWithSelection() {
		this.list = createList();
		this.selectedIndex = -1;
	}

	protected AbsQListWithSelection(ArrayList<E> list, int i) {
		this.list = list;
		this.selectedIndex = i;


	}

	/**
	 * Creates a new empty ArrayList and returns it
	 * @return new empty ArrayList
	 * @requires 0 <= i < size()
	 */
	protected ArrayList<E> createList(){
		return new ArrayList<>();
	}


	/**
	 * Selects the element in position i of the list
	 * @param i = index of position on the list
	 * @requires 0 <= i < size()
	 */
	@Override
	public void select(int i) {
		if(i >= 0 && i < this.list.size()){
			this.selectedIndex = i;
		}

	}

	/**
	 * Indicates if there is an element selected on the list
	 * @return true = if there is an element selected/ false = otherwise
	 *
	 */
	@Override
	public boolean someSelected() {

		return (this.selectedIndex != -1);

	}

	/**
	 * Returns the number of elements in the list
	 * 
	 * @return the number of elements in the list
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Returns the element at position i
	 * 
	 * @param i the position of the element to return
	 * @requires 0 <= i < size()
	 * @return the element at position i
	 */
	@Override
	public E get(int i) {
		return list.get(i);
	}

	/**
	 * Returns an iterator over the elements in the list
	 * 
	 * @return  an iterator over the elements in this list in proper sequence.
	 */
	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}

	/**
	 * Adds an element at the end of the list
	 * 	
	 * @param e the element to be added
	 */
	@Override
	public void add(E e) {
		list.add(e);
		this.selectedIndex = list.size()-1;

	}

	/**
	 * Assuming someSelected(), returns the position of the selected element
	 * @return i = index of selected position
	 *
	 */
	@Override
	public int getIndexSelected() {
		return this.selectedIndex;
	}


	/**
	 * deletes the selected element if someSelected() otherwise does nothing
	 * 
	 */
	@Override
	public void remove() {
		if(selectedIndex != -1)
			list.remove(selectedIndex);


	}

	/**
	 * Assuming someSelected(), returns it
	 * @return E e = selected element
	 *
	 */
	@Override
	public E getSelected() {
		return list.get(selectedIndex);
	}

	/**
	 * Selects the next element after the selected position
	 * 
	 *
	 */
	@Override
	public void next() {
		if(this.getIndexSelected() < this.size()-1) {
			this.selectedIndex++;
		}else {
			this.selectedIndex = -1;
		}

	}

	/**
	 * Selects the previous element before the selected position
	 * 
	 *
	 */
	@Override
	public void previous() {
		if(this.getIndexSelected() > 0) {

			this.selectedIndex--;
		}else {
			this.selectedIndex = -1;
		}

	}






}
