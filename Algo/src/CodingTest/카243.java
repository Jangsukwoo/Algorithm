package CodingTest;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 카243 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int n,s;
	static int[] arr;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		String input = br.readLine();
		st = new StringTokenizer(input);
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		arr = new int[n];
		input = br.readLine();
		st = new StringTokenizer(input);

		for(int i=0;i<n;i++) arr[i] = Integer.parseInt(st.nextToken());
		
		
		for(int i=0;i<n;i++) {
			
			int minValue = arr[i];
			int minValueIdx = i;
			
			for(int j=i+1;j<n;j++) {
				if(minValue>arr[j]) { //작으면
					minValue = arr[j];
					minValueIdx = j;
				}
			}
			
			int temp = arr[minValueIdx];
			arr[minValueIdx] = arr[i];
			arr[i] = temp;
			if((i+1)==s) break;
		}

		for(int i=0;i<n;i++) bw.write(arr[i]+" ");
		bw.flush();
		bw.close();
	}
}
