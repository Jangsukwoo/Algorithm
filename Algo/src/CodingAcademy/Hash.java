package CodingAcademy;

public class Hash {
	public static void main(String[] args) {
		int[] data = new int[] {38,14,10,12,3,26,8,7,5,18};
		for(int i=0;i<data.length;i++) {
			h(data[i]);
		}
		System.out.println("이중해싱");
		System.out.println((hPrime(18)));
		//System.out.println("이차조사법");
		//hPrime2(18);
	}
	private static void hPrime2(int k){
		for(int inc=1;inc<=5;inc++){
			System.out.println("inc값 "+inc);
			System.out.println((h(k)+Math.pow(inc,2))%13);
		}
	}
	private static int hPrime(int k) {
		//System.out.println(5-(k%5));
		return (5-(k%5));
	}
	private static int h(int k){
		System.out.println(k%13);
		return k%13; 
	}
}