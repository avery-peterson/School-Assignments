public class A5Exercises {

	// PART 1

	/*
	 * Purpose: get a count of the number of elements in the array
	 * Parameters: int[] arr
	 * Returns: int - the number of elements
	 * Post-condition - the array contents are unchanged
	 */
	public static int countMultiples(int[] arr, int x) {
		if (arr.length == 0) {
			return 0;
		}
		return countMultiplesRec(arr, x, arr.length-1); // so it compiles
	}
		
	/*
	 * Purpose: get a count of the number of elements in 
	 *	the array up to index i
	 * Parameters: int[] arr - the array
	 *             int x - the number to search for multiples of
	 *             int i - index in the array 
	 * Returns: int - the number of elements
	 * Post-condition - the array contents are unchanged
	 */	
	public static int countMultiplesRec(int[] arr, int x, int i) {
		if (i < 0) {
			return 0;
		} else if (arr[i]%x == 0) {
			return 1 + countMultiplesRec(arr, x, i-1);
		} else {
			return countMultiplesRec(arr, x, i-1);
		}
	}
	
	/*
	 * Purpose: double all values in the given array
	 * Parameters: int[] array - the array to modify
	 * Returns: void - nothing
	 */
	public static void doubleAll(int[] array) {
		if (array.length == 0) {
			return;
		}
		array = doubleAllRec(array, array.length-1);
	}
	
	/*
	 * Purpose: double all values in the given array
	 * Parameters: int[] array - the array to modify
	 *             int i - index to modify element at
	 * Returns: void - nothing
	 */
	public static int[] doubleAllRec(int[] array, int i) {
		if (i < 0) {
			return array;
		}
		array[i] = array[i]*2;
		return doubleAllRec(array, i-1);
	}
	
	/*
	 * Purpose: get the minimum value found in the array
	 * Parameters: int[] array - the array to search
	 * Returns: int - minimum value found in the array
	 *                or -1 if the array is empty
	 * Post-condition - the array contents are unchanged
	 */
	public static int getMinimum(int[] array) {
		if (array.length == 0) {
			return -1; // so it compiles
		}
		return getMinimumRec(array, array.length-1, array[array.length-1]);
	}
	
	/*
	 * Purpose: get the minimum value found in the array
	 *             until i < 0 
	 * Parameters: int[] array - the array to search
	 * Returns: int - minimum value found in the array
	 *                or -1 if the array is empty
	 * Post-condition - the array contents are unchanged
	 */
	public static int getMinimumRec(int[] array, int i, int result) {
		if (i < 0) {
			return result; // so it compiles
		}
		if (array[i] < result) {
			result = array[i];
		}
		return getMinimumRec(array, i-1, result);
	}
	



	// PART II

	/*
	 * Purpose: get the total number of books in s
	 * Parameters: Stack<Book> s - the stack of books
	 * Returns: int - the total number of books
	 * Post-condition: s is not modified
	 */
	public static int totalBooks(Stack<Book> s) {
		if (s.isEmpty()) {
			return 0; // so it compiles
		}
		int count = 0;
		Book top = s.pop();
		count++;
		int countRest = totalBooks(s);
		s.push(top);
		return count + countRest;
		
	}
	


	/*
	 * Purpose: get the total number of pages of all 
	 *          books in the stack
	 * Parameters: Stack<Book> s - the stack of books
	 * Returns: int - the total number of pages
	 * Post-condition: s is not modified
	 */
	public static int totalPages(Stack<Book> s) {
		if (s.isEmpty()) {
			return 0; // so it compiles
		}
		Book top = s.pop();
		int pages = top.getPages();
		int restOfPages = totalPages(s);
		
		s.push(top);
		return pages + restOfPages;
	}
	
	
	/*
	 * Purpose: get the average number of pages of books in s
	 * Parameters: Stack<Book> s - the stack of books
	 * Returns: double - the average number of pages
	 *                   0.0 if there are no books in s
	 * Post-condition: s is not modified
	 */
	public static double averagePages(Stack<Book> s) {
		// You don't need to change this, if you have
		// completed the previous two exercises
		// correctly, it should pass all the tests
		if (s.isEmpty()) {
			return 0.0;
		} else {
			double sum = totalPages(s);
			int count = totalBooks(s);
			return sum/count;
		}
	}

	/*
	 * Purpose: determine whether toFind is contained in s
	 * Parameters: Stack<Book> s - the stack of books
	 *             Book toFind - the book to search for
	 * Returns: boolean - true if s contains toFind, false otherwise
	 * Post-condition: s is not modified
	 */
	public static boolean containsBook(Stack<Book> s, Book toFind) {
		boolean found = false;
		if (s.isEmpty()) {
			return false;
		}
		Book top = s.pop();
		if (top.equals(toFind)) {
			found = true;
		} else {
			found = containsBook(s, toFind);
		}
		s.push(top);
		return found;
	}
	
	
	

	/*
	 * Purpose: determine the books in s are stacked correctly
	 *          (ie. there is never a book stacked on top of 
	 *               another book with fewer pages)
	 * Parameters: Stack<Book> s - the stack of books
	 * Returns: boolean - true if books in s are stacked correctly
	 * Post-condition: s is not modified
	 */
	public static boolean stackedCorrectly(Stack<Book> s) {
		if (s.isEmpty()) {
			return true;
		} 
		Book cur = s.pop();
		boolean result = true;
		if (s.top() != null && cur.getPages() > s.top().getPages()) {
			result = false;
		} else {
			result = stackedCorrectly(s);
		}
		s.push(cur);
		return result;
	}
	
}