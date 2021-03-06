package 구름;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class 술래가원하는숫자만들기 {
	static int[] num;
	static int[] data;
	static int numSize;
	static boolean[] visit;
	static int target;
	static TreeSet<String> treeSet;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String readline = sc.nextLine();
		String[] numData = readline.split(" ");
		numSize = numData.length;
		num = new int[numSize];
		visit = new boolean[numSize];
		data = new int[3];
		for(int i=0;i<numSize;i++) num[i] = Integer.parseInt(numData[i]);
		target = sc.nextInt();
		Arrays.sort(num);
		treeSet = new TreeSet<String>();
		nCr(0,0);
		Iterator<String> iter = treeSet.iterator();
		if(treeSet.size()==0) System.out.println("NO");
		else {
			while(iter.hasNext()){
				System.out.println(iter.next());
			}
		}
	}
	private static void nCr(int idx, int cnt){
		if(cnt==3){
			if((data[0]+data[1]+data[2])==target) treeSet.add(data[0]+" "+data[1]+" "+data[2]);
			return ;
		}
		for(int i=idx;i<numSize;i++){
			data[cnt] = num[i];
			nCr(i+1,cnt+1);
		}
	}
}
