import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;


public class IOTest {
	public static void main(String[] args) throws IOException {
		ResourceBundle rb = ResourceBundle.getBundle("library") ;
		File f = new File(rb.getString("library")) ;
		byte[] bytes = new byte[10000] ;
		InputStream is = new FileInputStream(f) ;
		long start = System.currentTimeMillis() ;
		is = new FileInputStream(f) ;
		for (int i = 0; i < 125; i++) {
			while(is.read(bytes)!=-1) ;
		}
		System.out.println(System.currentTimeMillis()-start);
	}
}
