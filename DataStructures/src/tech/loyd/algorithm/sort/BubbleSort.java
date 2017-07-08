package tech.loyd.algorithm.sort;

public class BubbleSort {

	public static <AnyType extends Comparable<? super AnyType>> void bubbleSort(AnyType[] array){
		for(int i=array.length-1;i>0;i--){
			boolean isHaveExchange = false;
			
			for(int j=0;j<=i-1;j++){
				if(array[j].compareTo(array[j+1]) > 0){
					AnyType temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
					isHaveExchange = true;
				}
			}
			
			if(!isHaveExchange)
				break;
			
		}
	}
}
