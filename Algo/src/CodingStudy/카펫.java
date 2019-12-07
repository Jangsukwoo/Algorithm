package CodingStudy;


/*
 * 카펫의 형태는 격자
 * 중앙은 빨간색,모서리는 갈색
 * 갈색 격자, 빨간색 격자 수가 입력으로 주어질 때
 * 카펫의 행,열은 몇 by 몇인지? (가로,세로)
 * 단, 카펫의가로길이는 세로길이와 같거나 세로 길이보다 길다. 
 * 
 * 가로가 더 길다는 조건 때문에
 * N by N에 대한 매트릭스의 가로값은 
 * 적어도 루트 N까지는 봐야한다.
 * 
 * 
 */
public class 카펫 {
	public static void main(String[] args) {
		solution(24,24);
	}
	 public static int[] solution(int brown, int red) {
	        int[] answer = new int[2];
	        int size = brown + red;
	        int sqrtValue = (int) Math.sqrt(size);
	        for(int width=size;width>=sqrtValue;width--){//가로길이가 전체 사이즈의 루트 값이 될 때 까지 본다.
	        	if((size%width)==0){//나눠지면 N by M이 가능
	        		int height = size/width; //세로 
	        		int boundary = (width*2)+(height*2)-4;//테두리의 개수
	        		if(boundary==brown) {//테두리의 개수와 brown 값과 같으면 정답 발견
	        			answer[0] = width;
	        			answer[1] = height;
	        			break;
	        		}
	        	}
	        }
	        return answer;
	    }
}
