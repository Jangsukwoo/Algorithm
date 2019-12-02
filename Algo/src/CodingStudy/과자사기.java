package CodingStudy;

import java.util.Scanner;

/*
 * 과자 종류 S,N,U
 * 봉지당 가격,무게
 * 쿠폰 : 5000원 이상일 때 500원 깎아줌
 * 쿠폰 할인은 한번만
 * 가성비 : 과자 10봉지 무게의 합 / 쿠폰 사용했을때 10봉지 사는데 필요한 돈
 * 가성비가 제일 좋은 과자 사기
 * 
 * 간단하면서도 의외로 어려웠던 문제..
 * 
 */
public class 과자사기 {
	static int S,N,U;
	static int[][] snack = new int[3][2];//i: 가격, j: 무게
	static int answer;
	static boolean[] possibleSnack = new boolean[3];
	static double costEffectiveness=-1;
	static int possibleSnackCount;
	static int maxIndex;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int i=0;i<3;i++) {
			snack[i][0] = sc.nextInt(); //가격
			snack[i][1] = sc.nextInt(); //무게
		}
		for(int i=0;i<3;i++) {
			if((snack[i][0]*10)>=5000) {
				possibleSnack[i] = true;
				possibleSnackCount++; //쿠폰 가능한 후보 개수
			}
		}
		choiceSnack();
		switch (maxIndex) {
		case 0:		
			System.out.println("S");
			break;
		case 1:
			System.out.println("N");
			break;
		case 2:
			System.out.println("U");
			break;
		}
	}
	private static void choiceSnack() {
		switch (possibleSnackCount) {
		case 0: //할인 될 수 있는 경우가 없음
			noCoupon();
			break;
		case 1: 
			noCoupon();
			useCoupon();
			break;
		case 2:
			noCoupon();
			useCoupon();
			useCoupon();
			break;
		case 3:
			noCoupon();
			useCoupon();
			useCoupon();
			useCoupon();
			break;
		}
	}
	private static void useCoupon() {
		//System.out.println(Arrays.toString(possibleSnack));
		boolean useCoupon = false;
		for(int i=0;i<3;i++) {
			double weight = snack[i][1]*10;
			double price = snack[i][0]*10;
			if(possibleSnack[i] && useCoupon==false){//쿠폰 대상이면
				price-=500; //할인된값
				possibleSnack[i]=false;//이 케이스는 봤으니 false 처리
				useCoupon = true; //쿠폰 사용했다는 flag
			}
			double temp = weight/price;
			if(temp>costEffectiveness) {
				costEffectiveness=temp; //이게 반대로 되어있어서 계속 틀렸다.. 덤벙댔음 ㅠ
				maxIndex = i;
			}
		}
	}
	private static void noCoupon() {
		for(int i=0;i<3;i++) {
			double weight = snack[i][1]*10;
			double price = snack[i][0]*10;
			double temp = weight/price;
			if(temp>costEffectiveness) {
				costEffectiveness= temp;
				maxIndex = i;
			}
		}
	}
}
