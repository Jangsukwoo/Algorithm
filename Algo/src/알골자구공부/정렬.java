package 알골자구공부;

import java.util.Arrays;

public class 정렬 {
	public static void main(String[] args) {
		int[] data = new int[] {5,4,3,2,1};
		int[] data2 = new int[] {5,6,1,3,2,5};
		bubbleSort(data2);
		selectionSort(data2);
	}
	/*
	 * 선택정렬
	 */
	private static void selectionSort(int[] tempData) {
		int[] data = copy(tempData);
		int minPosition=0;
		for(int i=0;i<data.length-1;i++){ //넣을 위치
			minPosition = i;
			for(int j=i+1;j<data.length;j++) {
				if(data[minPosition]>data[j]){//제일 작은값 찾기
					minPosition = j;
				}
			}
			//swap
			int temp = data[i];
			data[i] = data[minPosition];
			data[minPosition] = temp;
		}
		System.out.println("선택정렬");
		System.out.println(Arrays.toString(data));
	}

	/*
	 * 버블정렬
	 */
	private static void bubbleSort(int[] tempData){
		int[] data = copy(tempData);
		for(int i=0;i<data.length;i++) {
			for(int j=0;j<(data.length-1);j++) {
				if(data[j]>data[j+1]){//swap
					int temp = data[j];
					data[j] = data[j+1];
					data[j+1] = temp;
				}
			}
		}
		System.out.println("버블정렬");
		System.out.println(Arrays.toString(data));
	}

	private static int[] copy(int[] tempData){
		int[] copyData = new int[tempData.length];
		for(int i=0;i<tempData.length;i++) {
			copyData[i] = tempData[i];
		}
		return copyData;
	}
	
}
