import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class XorTest {
	private static int i = 0 ;
	public static void main(String[] args) {
		long start = System.currentTimeMillis() ;
		String str ="sdfsdfafdsafadfasdfassadfasdf222" ;
		int length = str.length() ;
		char[] chars = str.toCharArray() ;
		Map<String,Integer> map = new HashMap<String,Integer>() ;
		String temp = null ;
		int tempNum = 0 ;
		int max = 0 ;
		for (int i = 0; i < length; i++) {
			for (int j = length-i; j >-1&&j>0 ; j--) {
				temp = new String(chars,i,j) ;
				if(map.containsKey(temp)){
					tempNum = map.get(temp)+1 ;
					map.put(temp, tempNum) ;
					if(max<tempNum){
						max= tempNum ;
					}
				}else{
					map.put(temp, 0) ;
				}
			}
		}
		System.out.println(":"+(max+1)+"他们都是:");
		
		Entry<String, Integer> ent = null ;
		Set<Entry<String, Integer>> set = map.entrySet() ;
		Iterator<Entry<String, Integer>> it = set.iterator() ;
		
		while(it.hasNext()){
			ent = it.next() ;
			if(ent.getValue()==max){
				System.out.println(ent.getKey());
			}
		}
		System.out.println(System.currentTimeMillis()-start);
	}
	public static char fun(char[] chars){
		return chars[i--] ;
	}

	public static int findPGE(int[]... ias){
		for (int i = 0; i < ias[1].length; i++) {
			if(Arrays.binarySearch(ias[0], ias[1][i])<0){
				return ias[1][i] ;
			}
		}
		return 0 ;
	}
	public static int xorArray(int[]... ias) {
		int result = 0;
		for (int i = 0; i < ias.length; i++) {
			result ^= xorArray(ias[i]);
		}
		return result;
	}

	public static int xorArray(int[] ia) {
		int result = 0;
		for (int i = 0; i < ia.length; i++) {
			result ^= ia[i];
		}
		return result;
	}
}