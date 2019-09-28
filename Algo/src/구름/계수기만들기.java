package 구름;

import java.util.Scanner;

public class 계수기만들기 {
	static int m;
	static int[] num;
	static int[] max;
	static int count;
	static boolean check;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();
		num = new int[m];
		max = new int[m];
		for(int i=0;i<m;i++) max[i] = sc.nextInt();
		for(int i=0;i<m;i++) num[i] = sc.nextInt();
		count = sc.nextInt();


		int value=0;
		int carry=0;
		
		for(int i=0;i<m;i++) if(num[i]>max[i]) check=true;
		if(check) System.out.println("-1");
		else {
			for(int press=0;press<count;press++){
				carry=0;
				for(int j=(m-1);j>=0;j--){
					value = num[j]+1;
					if(carry==1) {
						if(value==(max[j]+1)){
							num[j]=0;
							carry=1;
						}else {
							num[j]=value;
							carry=0;
							break;
						}
					}else {
						if(value==(max[j]+1)){
							num[j]=0;
							carry=1;
						}else {
							num[j]=value;
							carry=0;
							break;
						}
					}
				
				}
			}

			for(int j=0;j<m;j++){
				System.out.print(num[j]);
			}		
		}
		
	
	}
}

