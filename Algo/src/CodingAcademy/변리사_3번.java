package CodingAcademy;

import java.util.Arrays;

/*
 * Heap Sort(nlogn)
 */
public class 변리사_3번 {
	public static void main(String[] args) {
		int[] data = new int[] {0,9,8,7,6,5,4,3,2,1};
		int size = data.length;
		AAASort(data,size);
	}

	private static void AAASort(int[] a, int size){
		System.out.println("받은 배열");
		System.out.println(Arrays.toString(a));
		int n = size-1;
		int temp=0;
		for(int i = n/2; i>=2 ;i--) {
			BBB(a,i,n);
			System.out.println(Arrays.toString(a));
		}
		System.out.println("이후");
		System.out.println(Arrays.toString(a));
		for(int i=n;i>=2;i--) {
			BBB(a,1,i-1);
			temp = a[1];
			/*
			 * To write pseudo code
			 */
			a[1] = a[i];
			a[i] = temp;
			System.out.println(Arrays.toString(a));
		}
		
	}

	private static void BBB(int[] a, int h, int m) {
		int imsi = a[h];
		int j = 0;
		for(j=2*h;j<=m;j*=2){ //위치 찾기
			if(j<m) {
				if(a[j]<a[j+1]) {
					j+=1;
				}
			}
			if(imsi>=a[j]) break;
			else {
				/*
				 * To write pseudo code
				 */
				a[h]=a[j];
				h=j;
			}
		}
		a[j/2]=imsi;
	}
}
