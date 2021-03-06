package BAEKJOON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 국영수 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static ArrayList<Student> students = new ArrayList<Student>();
	static int N;
	static class Student{
		String name;
		int korea;
		int english;
		int math;
		public Student(String name, int korea, int english, int math) {
			this.name = name;
			this.korea = korea;
			this.english = english;
			this.math = math;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력
		setData();
		//로직
		sorting();
		//출력
		setOutputData();
		
		bw.flush();
		bw.close();
	}
	private static void setOutputData() throws IOException {
		for(Student student : students) {
			bw.write(student.name+"\n");
		}
	}
	private static void sorting() {
	
		Collections.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				if(o1.korea==o2.korea) {
					
					if(o1.english==o2.english) {
						
						if(o1.math==o2.math) {
							
							return o1.name.compareTo(o2.name);
						}
						
						return -Integer.compare(o1.math,o2.math);
					}
					
					return Integer.compare(o1.english,o2.english);
				}
				
				
				return -Integer.compare(o1.korea,o2.korea);
			}
			
		});
	}
	private static void setData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int k = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			students.add(new Student(name, k, e, m));
		}
	}
}
