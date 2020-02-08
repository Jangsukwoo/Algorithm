package 순열조합;

public class 중복순열 {
	static int cnt;
	public static void main(String[] args) {
		dfs(0,"");
		System.out.println(cnt);
	}

	private static void dfs(int depth, String malcase) {
		if(depth==10) {
			cnt++;
			return;
		}
		for(int i=1;i<=4;i++) {
			dfs(depth+1,malcase+Integer.toString(i));
		}
	}
}
