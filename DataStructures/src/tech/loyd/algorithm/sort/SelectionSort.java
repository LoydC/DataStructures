package tech.loyd.algorithm.sort;

public class SelectionSort {
	
	public static <AnyType extends Comparable<? super AnyType>> void selectionSort(AnyType[] array) {
		if (array == null)
			return;

		AnyType min;
		int minPos;

		for (int i = 0; i < array.length - 1; i++) {
			min = array[i];
			minPos = i;

			for (int j = i; j < array.length; j++) {
				if (min.compareTo(array[j]) > 0) {
					min = array[j];
					minPos = j;
				}
			}

			if (minPos != i) {
				AnyType temp = array[i];
				array[i] = array[minPos];
				array[minPos] = temp;
			}
		}

	}
}
