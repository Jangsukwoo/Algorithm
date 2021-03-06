package 순열조합;
import java.util.Arrays;
/*
백준의 "다음순열" 문제
C++의 Next_permutation 동작 원리 파악 

1.
DATA를 오른쪽에서 왼쪽으로 읽으면서
최초로 data[i-1] < data[i]가 발견되는 i값을 찾는다. 

2.
다시 오른쪽에서 왼쪽으로 읽는데 j>=i인 부분까지만 읽는다. 즉 1번에서 정한 i값 기준 오른쪽 영역만을 읽음
여기서 data[j] >data[i-1]를 만족하는 최초의 j를 찾는다.

3.
data[j]와 data[i-1]을 Swap하고
오른쪽 영역을 오름차순으로 정렬한다.


 */
import java.util.Scanner;

public class Next_permutation {
	static int N;
	static int criteriaIndex;
	static int swapTargetDataIndex;
	static int[] data;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		data = new int[N];
		for(int i=0;i<N;i++) data[i] = sc.nextInt();// Data 입력받음.
	
		/*
		 * 1.
		 * DATA를 오른쪽에서 왼쪽으로 읽으면서
		 * 최초로 data[i-1] < data[i]가 발견되는 i값을 찾는다.
		 */
		for(int i=(N-1);i>=1;i--) {
			if(data[i]>data[i-1]){
				criteriaIndex = i; //발견된 i값이 criteriaIndex가 된다.
				break;//최초발견 기준값 저장 후 break;
			}
		}
		
		/*
		 *2.
		 *다시 오른쪽에서 왼쪽으로 읽는데 j>=criteriaIndex인 부분까지만 읽는다. 즉 1번에서 정한 criteriaIndex값의 오른쪽 영역만을 읽음.
		 *여기서 data[j] >data[criteriaIndex-1]를 만족하는 최초의 j를 찾는다. 
		 */
		
		for(int j=(N-1);j>=criteriaIndex;j--){//오른쪽 영역만.criteria까지.
			if(data[j]>data[criteriaIndex-1]){
				swapTargetDataIndex =j;
				break;
			}
		}
		/*
		 * 3. data[j]와 data[i-1]을 Swap하고
		 * 오른쪽 영역을 오름차순으로 정렬한다.
		 */
		//Swap
		int tmp = data[criteriaIndex-1];
		data[criteriaIndex-1] = data[swapTargetDataIndex];
		data[swapTargetDataIndex] = tmp; 
		
		//우측 영역 오름차순 정렬
		Arrays.sort(data, criteriaIndex, N); //toIndex는 N이여야한다..ㄷㄷ
		
		//출력
		for(int i=0;i<N;i++) System.out.print(data[i]+" ");
	}
	
}
