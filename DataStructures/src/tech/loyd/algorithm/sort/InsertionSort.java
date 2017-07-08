package tech.loyd.algorithm.sort;

public class InsertionSort {

	public static <AnyType extends Comparable<? super AnyType>> void inserttionSort(AnyType[] array){
		for(int i=1;i<array.length;i++){
			int j;
			AnyType temp = array[i];
			for(j=i;j>0 && temp.compareTo(array[j-1])<0 ;j--){
				array[j] = array[j-1];
			}
			array[j] = temp;
		}
	}
	
}
