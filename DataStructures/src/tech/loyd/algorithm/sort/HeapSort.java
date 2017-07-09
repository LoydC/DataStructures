package tech.loyd.algorithm.sort;

public class HeapSort {

	public static <AnyType extends Comparable<? super AnyType>> void heapSort(AnyType[] array) {
		//建立最大堆
		for(int i=array.length/2-1;i>=0;i--){
			percolateDown(array,i,array.length);
		}
		AnyType temp;
		//将最大的那个元素和数组随后一个元素交换，在重新调整0位置
		for(int i=array.length-1;i>0;i--){
			temp = array[i];
			array[i] = array[0];
			array[0] = temp;
			percolateDown(array, 0, i);
		}
	}

	/*
	 * 用于调整堆的顺序
	 * @param array 待调整的数组
	 * @param i 待调整的位置
	 * @param n 堆的逻辑大小
	 */
	private static <AnyType extends Comparable<? super AnyType>> void percolateDown(AnyType[] array,int i,int n) {
		int child;
		AnyType temp;
		
		for(temp = array[i];leftChild(i) < n;i=child){
			child = leftChild(i);
			//如果存在右孩子，且左孩子小于右孩子，就将child指向右孩子的位置
			if(child != n-1 && array[child].compareTo(array[child+1])<0)
				child++;
			//比较该节点和孩子节点的大小，若小于，将孩子节点的值赋给该节点
			if(temp.compareTo(array[child]) < 0)
				array[i] = array[child];
			else
				break;
		}
		array[i] = temp;
		Test.print(array);
	}

	/*
	 * 返回最孩子的位置
	 */
	private static int leftChild(int n){
		return 2 * n + 1;
	}
	
}
