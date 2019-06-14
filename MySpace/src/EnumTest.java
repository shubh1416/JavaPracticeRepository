import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnumTest {
public static void main(String []args) {
	List<Tea> teaList= Arrays.asList(Tea.values());
	Map<String,Integer> mp=new HashMap<>();
	
	for(Tea l : teaList) {
		Integer a=(l.getConsumptionAmount()+l.getWasteAmount())*2;
		mp.put(l.getMaterial(), a);
	}
	System.out.println(mp);
}
}
