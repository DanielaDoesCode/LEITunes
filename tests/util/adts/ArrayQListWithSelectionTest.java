/**
 * 
 */
package util.adts;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author fc58199 Daniela Camarinha
 * @author fc58193 Rafael Ribeiro
 *
 * Tests for the ArrayQListWithSelection Class
 */
class ArrayQListWithSelectionTest {
	
	private ArrayList<Integer> intList;
	private ArrayQListWithSelection<Integer> list;

	/**
	 * Method to quickly set up the integer list for testing
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		intList = new ArrayList<>();
		/*Adding Elements the list that will serve as expected output*/
		for(int i = 0; i < 10; i++) {
			intList.add(i);
		}
	}
	
	/**
	 * Tests if an empty ArrayQListWithSelection has a size of 0
	 */
	@Test
	@DisplayName("Checking if an empty list has an element selected")
	void emptyConstructorSize() {
		list = new ArrayQListWithSelection<>();
		assertEquals(list.size(), 0);
	}

	/**
	 * Tests if an empty ArrayQListWithSelection has a selected index
	 */
	@Test
	@DisplayName("Checking if an empty list has an element selected")
	void emptyConstructorIndexTest() {
		list = new ArrayQListWithSelection<>();
		assertTrue(!list.someSelected());
	}
	
	/**
	 * Tests if an empty ArrayQListWithSelection has a next index 
	 */
	@Test
	@DisplayName("Checking if an empty list has a next Index")
	void emptyConstructorNextTest() {
		list = new ArrayQListWithSelection<>();
		list.next();
		assertTrue(!list.someSelected());
	}
	
	/**
	 * Tests if an empty ArrayQListWithSelection has a previous index
	 */
	@Test
	@DisplayName("Checking if an empty list has a previous Index")
	void emptyConstructorPreviousTest() {
		list = new ArrayQListWithSelection<>();
		list.previous();
		assertTrue(!list.someSelected());
	}
	
	
	/**
	 * Tests if an ArrayQListWithSelection constructed with a list with size 10
	 * has a size of 10
	 */
	@Test
	@DisplayName("Checking if a list with 10 elements has size 10")
	void listSizeTest() {
		list = new ArrayQListWithSelection<>(intList, 0);
		assertEquals(list.size(), 10);
	}
	
	/**
	 * Tests if an ArrayQListWithSelection selects the index specified
	 */
	@Test
	@DisplayName("Checking if a list with 10 elements selects the specified index")
	void listIndexSelectedTest() {
		list = new ArrayQListWithSelection<>(intList, 3);
		assertEquals(list.getIndexSelected(), 3);
	}
	
	/**
	 * Tests if an ArrayQListWithSelection gets the element on specified index
	 */
	@Test
	@DisplayName("Checking if a list with 10 elements returns the element at a specified index")
	void listGetSelectedTest() {
		list = new ArrayQListWithSelection<>(intList, 3);
		assertEquals(list.getSelected(), 3);
	}
	
	

}
