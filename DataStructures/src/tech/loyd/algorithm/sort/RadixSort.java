package tech.loyd.algorithm.sort;

public class RadixSort {

	/*
	 * 基数排序，digits是位数
	 */
	public static void radixSort(Integer[] array,int digits){
		int[] output = new int[array.length];//存储被排序的临时数据
		int[] bucket = new int[10];
		
		int divisor = 1;//对个位数排序
		while(digits > 0){
			
			//将桶全部置零
			for(int i=0;i<bucket.length;i++){
				bucket[i] = 0;
			}
			
			//存储数据出现的次数
			for(int i=0;i<array.length;i++){
				 bucket[(array[i] / divisor) % 10]++;
			}
			
			//让更改后的bucket[i]的值，是该数据在output[]中的位置。
			for(int i=1;i<bucket.length;i++){
				bucket[i] += bucket[i-1];
			}
			
			//将数据存储到临时数组output[]中
			for(int i=array.length-1;i>=0;i--){
				output[bucket[(array[i] / divisor) % 10]-1] = array[i];
				bucket[(array[i] / divisor) % 10]--;
			}
			
			//将排序好的数据赋值给array[]
			for(int i=0;i<array.length;i++){
				array[i] = output[i];
			}
			
			divisor *= 10;
			digits--;
			
			//打印排序的过程
			Test.print(array);
		}
	}
	
}
