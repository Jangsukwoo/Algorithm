package CodingAcademy;


/*
 * Heap Sort(nlogn)
 */
public class 변리사_3번 {
	public static void main(String[] args) {
		int[] data = new int[] {0,9,8,7,6,5,4,3,2,1};
		int[] data2 = new int[] {0,9,2,3,4,5,6,7,8,1};
		int[] data3 = new int[] {0,9,8,2,8,3,7,4,6,5};
		int[] data4 = new int[] {0,9,6,5,8,3,5,2,1,6};
		int[] data5 = new int[] {0,10,7,6,2,3,4,5,2,6};
		int size = data5.length;
		AAASort(data5,size);
		
	}

	private static void AAASort(int[] a, int size){
		int n = size-1;
		int temp=0;
		for(int i = n/2; i>=2 ;i--) {//최초  Max heap
			BBB(a,i,n);
		}
		for(int i=n;i>=2;i--) {
			BBB(a,1,i);
			temp = a[1]; //1은 root고 힙구조를 만들었으므로 가장 꼭대기는 항상 최대값이 유지된다.
		
			/*
			 * To write pseudo code
			 */
			a[1] = a[i]; // (가-1)
			a[i] = temp; // (가-2)
		}
	}

	private static void BBB(int[] a, int h, int m) { 
		//Max Heap을 만든다. root부터 자식노드의 방향으로 하향식으로 Heap을 만든다.
		int imsi = a[h];//부모의 값
		int j = 0;
		for(j=2*h;j<=m;j=j*2){ //자식 확인 
			if(j<m) {
				if(a[j]<a[j+1]) {//왼쪽 자식과 오른쪽 자식 중 더 큰 자식을 포인팅 
					j++;
				}
			}
			if(imsi>=a[j]) break;//부모보다 작거나 같으면 끝 
			else {//부모보다 크면 Swap
		
				/*
				 * To write pseudo code
				 */
				
				// (나) 
				a[h]=a[j];
				h=j;
			}
		}
		a[j/2]=imsi;
	}
}
