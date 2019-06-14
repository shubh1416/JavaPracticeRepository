import java.util.ArrayList;

public class NewClass {

	public static void main(String[] args) {
		ArrayList<Integer> al=new ArrayList<>();
		al.add(1);
		al.add(2);
		al.forEach(n->System.out.println(n));

	}

}
