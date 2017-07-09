package tech.loyd.algorithm.sort;

public class ShellSort {
	
	public static <AnyType extends Comparable<? super AnyType>> void shellSort(AnyType[] array){
		int j;
		//增量序列的选择是使用希尔增量
		for(int gap = array.length/2;gap>0;gap/=2){			
			for(int i=gap;i<array.length;i++){
				//插入排序
				AnyType temp = array[i];
				for(j=i;j>=gap && temp.compareTo(array[j-gap]) < 0;j-=gap){
					array[j] = array[j-gap];
				}
				array[j] = temp;
			}
		}
		
	}
	
}
