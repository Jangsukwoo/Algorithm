package SWExpertAcademy;

import java.util.ArrayList;
import java.util.Scanner;
/*
 * 해결 전
 * 2진수가 가능한 모든 값을 저장할 리스트 
 * 3진수가 가능한 모든 값을 저장할 리스트
 * 두 리스트에 속한 값을 비교하면서 같은 값을 찾음
 * 
 * 해결 후
 * if(Arraylist<Long> == Arraylist<Long>)으로 하는 경우 오답이 떴음.
 * 그 중에 정답이 되는 것도 있길래 의아점이 생김
 * Arraylist에 Long,Long,Long,Long,Long,Long으로 각 객체별로 저장이 되어있기 때문에
 * 당연히 Arraylist<Long> == Arraylist<Long>는 false가 나와야함
 * 특정 값은 true가 나옴
 * 검사해본 결과
 * 값이 127까지는 true로 해주고 그 이상 값부터는 false
 * 즉,
 * long a = 127
 * long b = 127
 * a==b 는 true
 * 
 * long a = 128
 * long b = 128
 * 
 * a==b 는 flase
 * 
 * Long value가 127이면 왜????
 * 
 * 
 * 내부적으로 127이란 숫자는 의미가 있나봄
 * 
 */
public class 정식이의은행업무{
    static ArrayList<Long> two;
    static ArrayList<Long> three;
    static char[] data1;
    static char[] data2;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int testcase=1;testcase<=T;testcase++) {
            data1 = sc.next().toCharArray();
            data2 = sc.next().toCharArray();
            two = new ArrayList<>();
            three = new ArrayList<>();
            possibleValue1();//2진수가 가능한 모든 값 저장
            possibleValue2();//3진수가 가능한 모든 값 저장
            System.out.println("#"+testcase+" "+Compare());
        }
    }
 
    private static void possibleValue1() {
        int twoSize = data1.length;
        int dataIndex = data1.length-1;
        for(int i=0;i<twoSize;i++) {
            char[] value = data1.clone();
            long Sum=0;
            switch (value[i]) {
            case '1':               
                value[i]='0';
                for(int j=dataIndex;j>=0;j--) {
                    long v = value[dataIndex-j]-'0';
                    Sum+=v*Math.pow(2,j);           
                }
                break;
            case '0':
                value[i]='1';
                for(int j=dataIndex;j>=0;j--) {
                    long v = value[dataIndex-j]-'0';
                    Sum+=v*Math.pow(2,j);   
                }
                break;
            }
            two.add(Sum);
        }
    }
    private static void possibleValue2() {
        int threeSize = data2.length;
        int dataIndex = data2.length-1;
        for(int i=0;i<threeSize;i++) {
            char[] value = data2.clone();
            long Sum =0;
            switch (value[i]) {
            case '2':
                value[i]='1';
                for(int j=dataIndex;j>=0;j--) {
                    long v = value[dataIndex-j]-'0';
                    Sum+=v*Math.pow(3,j);
                }
                three.add(Sum);
                Sum=0;
                value[i]='0';
                for(int j=dataIndex;j>=0;j--) {
                    long v = value[dataIndex-j]-'0';
                    Sum+=v*Math.pow(3,j);
                }
                three.add(Sum);
                break;
            case '1':
                value[i]='2';
                for(int j=dataIndex;j>=0;j--) {
                    long v = value[dataIndex-j]-'0';
                    Sum+=v*Math.pow(3,j);
                }
                three.add(Sum);
                Sum=0;
                value[i]='0';
                for(int j=dataIndex;j>=0;j--) {
                    long v = value[dataIndex-j]-'0';
                    Sum+=v*Math.pow(3,j);
                }
                three.add(Sum);
                break;
            case '0':
                value[i]='2';
                for(int j=dataIndex;j>=0;j--) {
                    long v = value[dataIndex-j]-'0';
                    Sum+=v*Math.pow(3,j);
                }
                three.add(Sum);
                Sum=0;
                value[i]='1';
                for(int j=dataIndex;j>=0;j--) {
                    long v = value[dataIndex-j]-'0';
                    Sum+=v*Math.pow(3,j);
                }
                three.add(Sum);
                break;
            }
        }
    }
    private static long Compare() {
        for(int i=0;i<two.size();i++)
            for(int j=0;j<three.size();j++) {//??????????????
                if(two.get(i).equals(three.get(j))) {
//                  System.out.println((two.get(i)==three.get(j)));
//                  System.out.println(two.get(i));
//                  System.out.println(three.get(j));
                    return two.get(i);
                }
            }
        return Long.MAX_VALUE;
    }
}