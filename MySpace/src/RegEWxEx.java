import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEWxEx {

	public static void main(String []args) {
		String inputString="fdgfdg";
		String []inputAfterSpliting=null;
		if(inputString.startsWith("//")) {
			String delimiter=Character.toString(inputString.charAt(2));
			String StringAfterRemovingDelimiter= inputString.substring(3).trim();
			inputAfterSpliting=StringAfterRemovingDelimiter.split(delimiter);
		}
	else {
		inputAfterSpliting=inputString.split(",|\n",-1);
	}
		for(String argument : inputAfterSpliting) {
		}
		
	}
}
