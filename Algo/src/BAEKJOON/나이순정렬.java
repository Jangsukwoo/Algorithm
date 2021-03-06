package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * 가입 순서대로 주어짐
 * 나이가 증가하는 순, 
 * 나이가 같으면 가입한 사람이 먼저 앞에.
 * 
 */
public class 나이순정렬 {
	public static int N;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringTokenizer st;
	public static ArrayList<Person> persons = new ArrayList<Person>();
	public static class Person implements Comparable<Person>{
		int memberNo;
		int age;
		String name;
		public Person(int memberNo, int age, String name){
			this.memberNo = memberNo;
			this.age = age;
			this.name = name;
		}
		@Override
		public int compareTo(Person o) {
			if(this.age==o.age) return Integer.compare(this.memberNo,o.memberNo);
			else return Integer.compare(this.age,o.age);
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		setData();
		sorting();
		setPrintdata();

		bw.flush();
		bw.close();
	}
	private static void setPrintdata() throws IOException {
		for(Person person : persons) bw.write(person.age+" "+person.name+"\n");
	}
	private static void sorting() {//정렬 조건
		/*
		 * 회원 나이가 증가하는 순
		 * 나이가 같으면 먼저 가입한 사람.
		 */

		//		Collections.sort(persons, new Comparator<Person>() {
		//			@Override
		//			public int compare(Person o1, Person o2){//o1, o2 비교할껀데 니가 써라 
		//				if(o1.age==o2.age) return Integer.compare(o1.memberNo,o2.memberNo);
		//				else return Integer.compare(o1.age,o2.age);
		//			}
		//		});
		Collections.sort(persons);
	}
		
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		for(int no=1;no<=N;no++) {
			st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			persons.add(new Person(no,age,name));
		}
	}
}
