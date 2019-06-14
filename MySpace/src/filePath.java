import java.io.File;
import java.io.IOException;

public class filePath {

	public static void main(String []arg) throws IOException {
		File file=new File("C:\\Users\\shubham.patidar\\Downloads\\containerStock.json");
			System.out.println("Absolute Path: " + file.getAbsolutePath());
			System.out.println("Canonical Path: " + file.getCanonicalPath());
			    System.out.println(file.getPath());
			System.out.println("Path: " + file.getPath());
		}
	}

