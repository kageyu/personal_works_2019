package jpe.co.sekappy.www.test;

public class Test {


	protected static void HalloWorld(int i) {

		System.out.println("hallo World　" + String.valueOf(i) + "回目");

	}


	public static void main(String[] args) {

		for(int i=0 ; 10>i ; i++) {

			HalloWorld(i+1);

		}

	}

}
