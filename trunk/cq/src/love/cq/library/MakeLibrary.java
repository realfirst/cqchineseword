package love.cq.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import love.cq.domain.Str;
import love.cq.util.IOUtil;

public class MakeLibrary {
	private static String charEncoding = "GBK";
	private static String path = "library/library.dic" ;
	private static final String ENGLISH = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJK" +
			"LMNOPQRSTUVWXYZ'" ;
	private static final String NUMBER = "0123456789��������������������.%" ;

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		sortLibrary();
		englishLibrary() ;
		numberLibrary() ;
		System.out.println(System.currentTimeMillis() - start);
	}

	/**
	 * ����򵥵�Ӣ��ʵ� status:4
	 * @throws Exception
	 */
	public static void englishLibrary() throws Exception{
		String[] stringArray = ENGLISH.split("") ;
		String str = natureLibrary(stringArray,4) ;
		IOUtil.Writer(InitDictionary.arraysEnglishPath, "UTF-8", str);
	}
	/**
	 * ����򵥵����ִʵ� status:5
	 * @throws Exception
	 */
	public static void numberLibrary() throws Exception{
		String[] stringArray = NUMBER.split("") ;
		String str = natureLibrary(stringArray,5) ;
		IOUtil.Writer(InitDictionary.arraysNumberPath, "UTF-8", str);
	}
	/**
	 * ����򵥵ľ���ͨ�ô��ԵĴʵ�,��������.Ӣ��...
	 * @throws Exception
	 */
	public static String natureLibrary(String[] StringArray , int nature) {
		StringBuilder sb = new StringBuilder() ;
		for (int i = 0; i < StringArray.length; i++) {
			if(StringArray[i]!=null&&StringArray[i].trim().length()>0){
				sb.append(StringArray[i] + "	" + nature) ;
				sb.append('\n') ;
			}
		}
		return sb.toString() ;
	}
	/**
	 * �ع��ʵ�.���ݽṹ��Ĺ���base����ģ�͹������� baseģ�͵Ĺ������Ȱ�˳������.��ΰ�hashCode����
	 * 
	 * @throws IOException
	 */
	public static void sortLibrary() throws IOException {
		BufferedReader reader = IOUtil.getReader(path, charEncoding);
		String temp = null;
		StringBuilder sb = new StringBuilder();
		TreeMap<Str, Str> treeMap = new TreeMap<Str, Str>();
		Str str = null;
		Str tempStr;
		String line;
		String[] temps;
		Str strTemp = null;
		while ((line = reader.readLine()) != null) {
			temps = line.split("	");
			temp = temps[0];
			int length = temp.length() + 1;
			for (int i = 1; i < length; i++) {
				tempStr = new Str(temp.substring(0, i));
				str = new Str(tempStr.getStr());
				if ((strTemp = treeMap.get(tempStr)) != null) {
					if (i == length - 1) {
						if (strTemp.getStatu() == 2) {
							if (temps.length > 2)
								strTemp.setNature(temps[2]);
						} else {
							if (temps.length > 2)
								strTemp.setNature(temps[2]);
						}
						if (strTemp.getStatu() == 1) {
							strTemp.setStatu(2);
						}
					} else {
						if (strTemp.getStatu() == 3) {
							strTemp.setStatu(2);
						}
					}
					continue;
				} else {
					if (i == length - 1) {
						if (temps.length > 2)
							str.setNature(temps[2]);
						str.setStatu(3);
					} else {
						str.setStatu(1);
					}
				}
				treeMap.put(tempStr, str);
			}
		}
		Iterator<Str> it = treeMap.values().iterator();
		while (it.hasNext()) {
			sb.append(it.next());
			sb.append("\n");
		}
		sb.append("��������	3");
		IOUtil.Writer("library/sortLibrary.dic", "UTF-8", sb.toString());
	}

}
