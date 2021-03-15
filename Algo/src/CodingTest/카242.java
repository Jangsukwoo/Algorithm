package CodingTest;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 카242 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws Exception {

		String input = br.readLine();

		int size = Integer.parseInt(input); 

		int maxlen = 1+(2*(size-1));

		int half = maxlen/2;

		for(int floor = 0; floor<size; floor++) { 

			for(int j = 0; j<maxlen; j++) {

				int leftSide = half - floor;
				int rightSide = half + floor;
				if(rightSide<j) break; //별 다찍었으면 break;
				
				if(leftSide<=j && j<=rightSide) bw.write("*");
				else bw.write(" ");

			}
			bw.write("\n");
		}

		bw.flush();
		bw.close();
	}
}
