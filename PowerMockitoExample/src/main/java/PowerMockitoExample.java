import java.util.Scanner;

public class PowerMockitoExample {
	
	public static void main(String []args) {
		 Scanner sc=new Scanner(System.in);
		SecondaryClass secondaryClass=new SecondaryClass ();
		System.out.println("Enter number ");
		int a=sc.nextInt();
		System.out.println(secondaryClass.addNumber(a));
		sc.close();
	}
	

	
}
