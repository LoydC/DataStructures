package tech.loyd.algorithm.sort;

public class Test {
	public static <AnyType> void print(AnyType[] array){
		for(AnyType x : array){
			System.out.print(x + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		Integer[] array = {52,23,101,33,34,26,15,98};
		print(array);
		
		//测试插入排序
		//InsertionSort.inserttionSort(array);
		
		//测试冒泡排序
		//BubbleSort.bubbleSort(array);
		
		//测试选择排序
		//SelectionSort.selectionSort(array);
		
		//测试希尔排序
		//ShellSort.shellSort(array);
		
		//测试堆排序
		HeapSort.heapSort(array);
		
		//print(array);
	}
}
