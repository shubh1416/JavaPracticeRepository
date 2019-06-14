import java.util.Scanner;

public class A {

	public static void main(String []args) {
		Scanner sc=new Scanner(System.in);
		int input=1;
		while(input!=2) {
			callMethod();
			input=sc.nextInt();
		}
		System.out.println("System exit ");
		sc.close();
	}

	private static void callMethod() {
		// TODO Auto-generated method stub
		System.out.println("method gets called");
	}
}
