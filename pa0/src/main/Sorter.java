/*
 * Name:
 * Student ID #:
 */

/*
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public class Sorter implements ISorter {
	public Sorter() { ; }

	/*
	 * Reference Materials:
	 * - Quick Sort: https://www.geeksforgeeks.org/quick-sort/
	 * - Bubble Sort: https://www.geeksforgeeks.org/bubble-sort/


	 * We are going to be using bubble sort for the ascending and descending functions!

	 */

	/**
	 *
	 * @param a: int[]
	 * @return int[] in *ascending* order
	 */
	@Override
	public int[] ascending(int[] a) {
		int length = a.length;
		boolean swapped;
		int temp;

		for (int i = 0; i < length - 1; i++){
			swapped = false;

			for(int j = 0; j < length - i - 1; j++){
				if(a[j] > a[j + 1]){
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
					swapped = true;
				}
			}

			if (!swapped) break;
		}

		return a;
	}

	/**
	 *
	 * @param a: int[]
	 * @return int[] in *descending* order
	 */
	@Override
	public int[] descending(int[] a) {
		int length = a.length;
		boolean swapped;
		int temp;

		for (int i = 0; i < length - 1; i++){
			swapped = false;

			for(int j = 0; j < length - i - 1; j++){
				if(a[j] < a[j + 1]){
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
					swapped = true;
				}
			}

			if (!swapped) break;
		}

		return a;
	}
}
