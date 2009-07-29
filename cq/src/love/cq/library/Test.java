package love.cq.library;

public class Test {
	public static void main(String[] args) {
		String str = "ansj	java	1" ;
		String[] temps = str.split("	")  ;
		for (int i = 0; i < temps.length; i++) {
			System.out.println(temps[i]+"	"+i);
		}
	}
}
