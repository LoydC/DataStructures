package tech.loyd.algorithm.sort;

public class BucketSort {

	public static void bucketSort(Integer[] array, int max) {

		int[] bucket = new int[max];

		for (int i = 0; i < array.length; i++) {
			bucket[array[i]]++;
		}

		for (int i = 0, j = 0; i < max; i++) {
			while ((bucket[i]--) > 0) {
				array[j++] = i;
			}
		}

	}
}
