package CodingTest;

interface I1{
	  void m1();    
	}
	interface I2 extends I1{
	  void m2();
	}
	interface I3{
	  void m3();
	}
	class A implements I3{
	    public void m3(){}
	}
	class B extends A implements I2{
	    public void m1(){}
	    public void m2(){}
	}

	public class Ex4 {
	    public static void main(String[] args) {
	        A a = new A();
	        B b = new B();

	        I1 i1 = b; 
	        I2 i2 = a;
	        I3 i3 = b;
	        a = b;
	    }
	}