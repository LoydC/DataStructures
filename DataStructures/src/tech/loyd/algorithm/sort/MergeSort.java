package tech.loyd.algorithm.sort;

public class MergeSort {
	
	public static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] array){
		AnyType[] tempArray = (AnyType[]) new Comparable[array.length];
		mergeSort(array, tempArray, 0, array.length-1);
	}
	
	//递归的分为两部分后合并
	public static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] array,AnyType[] tempArray,int left,int right){
		if(left < right){
			int center = (left + right) / 2;
			mergeSort(array, tempArray, left, center);
			mergeSort(array, tempArray, center + 1,right);
			merge(array, tempArray, left, center+1, right);
			Test.print(array);
		}
	}
	
	/*
	 * 将两个有序的序列合并
	 */
	public static <AnyType extends Comparable<? super AnyType>> void merge(AnyType[] array,AnyType[] tempArray,int leftPos,int rightPos,int rightEnd){
		int leftEnd = rightPos-1;
		int tempPos = leftPos;
		int numElements = rightEnd - leftPos + 1;
		
		while(leftPos <= rightPos && rightPos <= rightEnd){
			if(array[leftPos].compareTo(array[rightPos]) <= 0){
				tempArray[tempPos++] = array[leftPos++];
			}else{
				tempArray[tempPos++] = array[rightPos++];
			}
		}
		
		while(leftPos <= leftEnd){
			tempArray[tempPos++] = array[leftPos++];
		}
		
		while(rightPos <= rightEnd){
			tempArray[tempPos++] = array[rightPos++];
		}
		
		//将tempArray中排序的值赋给array中
		for(int i=0;i<numElements;i++,rightEnd--){
			array[rightEnd] = tempArray[rightEnd];
		}
	}
}
