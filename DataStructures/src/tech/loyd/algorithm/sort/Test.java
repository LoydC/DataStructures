package tech.loyd.algorithm.sort;

public class Test {
	public static <AnyType> void print(AnyType[] array){
		for(AnyType x : array){
			System.out.print(x + " ");
		}
		System.out.println();
	}
	
	public static <AnyType> void print(AnyType[] array,int start,int end){
		for(int i = start;i<=end;i++){
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		Integer[] array = {329,457,657,839,436,720,355};
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
		//HeapSort.heapSort(array);
		
		//测试归并排序
		//MergeSort.mergeSort(array);
		
		//测试快速排序
		//QuickSort.quickSort(array);
		
		//测试桶排序
		//BucketSort.bucketSort(array,100);
		
		RadixSort.radixSort(array, 3);
		
		//print(array);
	}
}
