package 프로그래머스;

public class 스킬체크1_2 {
	public static void main(String[] args) {
		solution(5,new int[] {9,20,28,18,11}, new int[] {30,1,21,17,28});
	}
	  public static String[] solution(int n, int[] arr1, int[] arr2) {
	      String[] answer = {};
	      answer = new String[n];
	      for(int i=0;i<answer.length;i++) answer[i] = "";
	      for(int row=0;row<n;row++){
	    	  String tempbinary1 = Integer.toBinaryString(arr1[row]);
	    	  String tempbinary2 = Integer.toBinaryString(arr2[row]);
	    	  String binary1 = "";
	    	  String binary2 = "";
	    	  if(tempbinary1.length()!=n) {
	    		  for(int i=0;i<n-tempbinary1.length();i++) binary1+="0";
	    		  binary1+=tempbinary1;
	    	  }else   binary1+=tempbinary1;
	    	  if(tempbinary2.length()!=n) {
	    		  for(int i=0;i<n-tempbinary2.length();i++) binary2+="0";
	    		  binary2+=tempbinary2;
	    	  }else binary2+=tempbinary2;
	    	  for(int col=0;col<n;col++) {
	    		  if(binary1.charAt(col)=='1' || binary2.charAt(col)=='1') {
	    			  answer[row]+="#";
	    		  }else answer[row]+=" ";
	    	  }
	      }
	      return answer;
	  }
}
