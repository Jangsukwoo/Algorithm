package CodingTest;

public class 네하상코딜4 {
	public static void main(String[] args) {
		solution(new int[] {1});
	}
	public static int solution(int [] A) {
	      int result = 0;
	      
	      int mmin = Integer.MAX_VALUE;
	      int firstsliceidx = -1;
	      
	      int mmax = Integer.MIN_VALUE;
	      int lastsliceidx = -1;
	      
	      /*
	       * 최대 최소 찾기
	       */
	      
	      for(int i = 0; i<A.length; i++) {
	         if(A[i] >= mmax) {
	            mmax = A[i];
	            lastsliceidx = i;
	         }
	         if(A[i] <= mmin) {
	            mmin = A[i];
	            firstsliceidx = i;
	         }
	      }
	   
	      if(firstsliceidx > lastsliceidx) {//데이터 중 가장 큰 숫자가 가장 작은 숫자보다 앞에 있으면
	         result = 1;
	      }else {
	         result = 2;
	         int firstmmax = Integer.MIN_VALUE;
	         int lastmmin = Integer.MAX_VALUE;
	         int firstidx = -1;
	         int lastidx = -1;
	         
	         for(int i = 0; i<=firstsliceidx; i++) {
	            if(firstmmax < A[i]) {
	               A[i]=firstmmax;
	            }
	         }
	         
	         for(int j = A.length-1; j>=lastsliceidx; j--) {
	            if(lastmmin > A[j]) {
	               lastmmin=A[j];
	            }
	         }
	         
	         for(int k = firstsliceidx+1; k<lastsliceidx; k++) {
	            if(A[k] < firstmmax) {
	               firstidx = k;
	            }
	         }
	         
	         for(int k = lastsliceidx-1; k>firstsliceidx; k--) {
	            if(A[k]>lastmmin) {
	               lastidx = k;
	            }
	         }
	         
	         if(firstidx == -1) {
	            firstidx = firstsliceidx;
	         }
	         if(lastidx == -1) {
	            lastidx = lastsliceidx;
	         }
	         
	         
	         int before = A[firstidx+1];
	         for(int a = firstidx+2; a<=lastidx; a++) {
	            if(before < A[a]) {
	               result++;
	               before = A[a];
	            }
	         }
	      }
	      System.out.println(result);
	      return result;
	    }
}
