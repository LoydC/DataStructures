package tech.loyd.algorithm.sort;

public class Test {
	private static <AnyType> void print(AnyType[] array){
		for(AnyType x : array){
			System.out.print(x + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		Double[] array = {3.14,3.1,4.56,1.0,4.38,0.0,9.5,2.34,7.23};
		print(array);
		
		//测试插入排序
		//InsertionSort.inserttionSort(array);
		
		//测试冒泡排序
		BubbleSort.bubbleSort(array);
		print(array);
	}
}
