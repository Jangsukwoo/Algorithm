package CodingAcademy;

public class 부분집합구하기 {
	static int[] set = new int[] {1,2,3};
	static int answer;
	static boolean[] include;
	public static void main(String[] args){
		include = new boolean[3];
		System.out.println(numSubsets(3,2));
	}
	private static int numSubsets(int n, int k) {
		if(k==-1) {
			for(int i=0;i<n;i++) {
				if(include[i]) {
					System.out.print(set[i]+" ");
				}
			}
			System.out.println();
			return 1;
		}
		include[k-1] = true;
		numSubsets(n, k-1);
		include[k-1] = false;
		numSubsets(n, k-1);
		return 0;
		
	}
}
