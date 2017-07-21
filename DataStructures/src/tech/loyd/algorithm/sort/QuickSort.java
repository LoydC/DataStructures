package tech.loyd.algorithm.sort;

public class QuickSort {

	public static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType[] array) {
		quickSort(array,0,array.length-1);
	}

	private static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType[] array, int start, int end) {
		
		if (start < end) {
			
			Test.print(array,start,end);
			
			int i = start;
			int j = end;

			AnyType povit = array[start];

			while (i < j) {
				// 从右向左找第一个小于枢纽元的数
				while (i < j && array[j].compareTo(povit) > 0)
					j--;
				if (i < j)
					array[i++] = array[j];
				// 从左向右找第一个大于枢纽元的数
				while (i < j && array[i].compareTo(povit) < 0)
					i++;
				if (i < j)
					array[j--] = array[i];
			}
			array[i] = povit;
			quickSort(array,start,i-1);
			quickSort(array,i+1,end);
		}

	}
	
	
}
