package DP;

public class 오르막수_OEIS {
	static String number = "000000";
	public static void main(String[] args) {
		int count = 0;
		while(true) {
			char a = number.charAt(0);
			char b = number.charAt(1);
			char c = number.charAt(2);
			char d = number.charAt(3);
			char e = number.charAt(4);
			char f = number.charAt(5);
			if(a<=b && b<=c && c<=d && d<=e && e<=f) {
				count++;
				System.out.println(number);
			}
			int num = Integer.parseInt(number);
			if(num==1000000) break;
			num++;
			String num_string = Integer.toString(num);
			if(num_string.length()!=6) {
				int fill = 6-num_string.length();
				String zero="";
				for(int i=0;i<fill;i++) {
					zero+="0";
				}
				number = zero+num_string;
			}else number = num_string;
		}
		int answer= 0;
		int n = 6;
		answer = (int) ((8064 + 26060*(Math.pow(n, 2)) + 5985*(Math.pow(n, 4)) + 210*(Math.pow(n, 6)) + (Math.pow(n, 8))) / 4032);
		System.out.println(count);
		System.out.println(answer);
	}
}
