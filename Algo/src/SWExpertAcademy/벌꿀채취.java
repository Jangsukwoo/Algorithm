package SWExpertAcademy;

import java.util.Scanner;

public class 벌꿀채취 {
	public static int size, bottle, limit, anw, firstMax, secondMax;
	public static int[][] map;
	public static int[] first, second;
	private static void findFirst() {

		for (int i = 0; i < size; i++) {
			for (int j = 0; j <= size-bottle; j++) {
				//병에담기
				int index = 0;
				for (int k = j; k < j+bottle; k++) {
					first[index++] = map[i][k];
				}

				//해당 경우에 대한 두번째 사람 프로세스가 필요
				findSecond(i, j+bottle);
			}
		}

	}

	private static void findSecond(int posI, int posJ) {


		if(posJ + bottle <= size) {

			for (int j = posJ; j <= size-bottle; j++) {
				int index = 0;
				for (int k = j; k < j+bottle; k++) {
					second[index++] = map[posI][k];
				}
			}

			//계산해보기
			checkPrice();
		}

		for (int i = posI+1; i < size; i++) {
			for (int j = 0; j <= size-bottle; j++) {
				//병에담기
				int index = 0;
				for (int k = j; k < j+bottle; k++) {
					second[index++] = map[i][k];
				}

				//계산해보기
				checkPrice();
			}
		}

	}

	private static void checkPrice() {

		firstMax = 0;
		secondMax = 0;
		
		goDfs1(0, 0, 0);
		goDfs2(0, 0, 0);
		
		anw = Math.max(anw, firstMax + secondMax);
	}

	
	private static void goDfs1(int index, int count, int value) {
		
		if(index == bottle) {
			if(count <= limit) {
				firstMax = Math.max(firstMax, value);
			}
			return;
		}
		
		if(count > limit) return;
		
		goDfs1(index+1, count+first[index], value+first[index]*first[index]);
		goDfs1(index+1, count, value);
	}
	
	private static void goDfs2(int index, int count, int value) {
		
		if(index == bottle) {
			if(count <= limit) {
				secondMax = Math.max(secondMax, value);
			}
			return;
		}
		
		if(count > limit) return;
		
		goDfs2(index+1, count+second[index], value+second[index]*second[index]);
		goDfs2(index+1, count, value);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int test = 1; test <= T; test++) {


			size = sc.nextInt();
			bottle = sc.nextInt();
			limit = sc.nextInt();

			anw = 0;
			map = new int[size][size];

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			first  = new int[bottle];
			second = new int[bottle];

			findFirst();
			
			System.out.println("#" + test + " " + anw);
		}
	}
}
