package 디자인패턴;

class Singleton{
	private static Singleton instance = new Singleton(); // 정적필드 / 인스턴스 생성 
	private Singleton(){} // private 생성자
	public static Singleton getInstance(){ // getInstance 메서드 정의
		return instance; // instance 객체 리턴
	}
}

public class 싱글톤 {
	public static void main(String[] args) {
		Singleton st1 = Singleton.getInstance(); // 싱글톤 인스턴스 호출
		Singleton st2 = Singleton.getInstance();
//		Singleton st3 = new Singleton(); // 생성자 이용 인스턴스 생성 불가
		if(st1 == st2){
			System.out.println("동일 객체");
		}else{
			System.out.println("다른 객체");
		}
	}
}
