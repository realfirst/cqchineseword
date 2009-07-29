package love.cq.library;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import love.cq.util.IOUtil;

public class MakeArray {
	// �ռ�Ŀ��
	public static int base[] = new int[1000000];
	// ��֤����
	public static int check[] = new int[1000000];
	// ����״̬
	public static int status[] = new int[1000000];
	// ��ǰ����Ĵ�
	public static String words[] = new String[1000000];

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		makeBaseArray();
		System.out.println(System.currentTimeMillis() - start);
	}

	/**
	 * path ����ôʵ��·�� haspath �ַ������ֵ��·�� arrayPath �����ֵ��·�� charEncoding
	 * �����ֵ�����ɴʵ���ַ���������
	 */
	private static String path = "library/sortLibrary.dic";
	private static String charEncoding = "GBK";


	/**
	 * �ֵ�Ĺ���˫����tire��
	 * 
	 * @throws Exception
	 */

	private static int previous;
	private static Map<String, String> tempStringMap = new HashMap<String, String>();

	public static void makeBaseArray() throws Exception {
		BufferedReader reader = IOUtil.getReader(InitDictionary.arraysNumberPath, charEncoding);
		makeBaseArray(reader) ;
		reader = IOUtil.getReader(InitDictionary.arraysEnglishPath, charEncoding);
		makeBaseArray(reader) ;
		reader = IOUtil.getReader(path, charEncoding);
		makeBaseArray(reader) ;
		writeLibrary();
	}
	/**
	 * ���������
	 */
	public static void makeBaseArray(BufferedReader reader) throws Exception{
		char[] chars = null;
		int length = 0;
		String tempValue;
		String tempValueResult;
		String[] strs = null;
		int tempBase = 0;
		String temp = null;
		while ((tempValue = reader.readLine()) != null) {
			System.out.println(tempValue);
			strs = tempValue.split("	");
			temp = strs[0];
			chars = temp.toCharArray();
			length = chars.length;
			if (length == 1) {
				base[chars[0]] = 40863;
				check[chars[0]] = -1;
				status[chars[0]] = Integer.parseInt(strs[1]);
				words[chars[0]] = temp;
			} else {
				int previousCheck = getBaseNum(chars);
				if (previous == previousCheck) {
					tempStringMap.put(temp, tempValue);
					continue;
				}
				if (tempStringMap.size() > 0) {
					setBaseValue(tempStringMap, previous);
					it = tempStringMap.values().iterator();
					while (it.hasNext()) {
						tempValueResult = it.next();
						strs = tempValueResult.split("	");
						chars = strs[0].toCharArray();
						tempBase = base[previous]
								+ chars[chars.length - 1];
						base[tempBase] = tempBase;
						check[tempBase] = previous;
						status[tempBase] = Integer.parseInt(strs[1]);
						words[tempBase] = strs[0];
					}
				}
				previous = previousCheck;
				tempStringMap = new HashMap<String, String>();
				tempStringMap.put(temp, tempValue);

			}
		}
	}
	/**
	 * �����ɵ�����д�ɴʵ��ļ�
	 */
	public static void writeLibrary(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < base.length; i++) {
			if (base[i] > 0) {
				sb.append(i+"	"+words[i] + "	" + base[i] + "	"
						+ check[i] + "	" + status[i]);
				sb.append("\n");
			}
		}
		IOUtil.Writer(InitDictionary.arraysPath, charEncoding, sb.toString());
	}
	/**
	 * ����base�����еĸ�ֵ,ʹ���Ը���ͷ�ĵ��ζ��ܷŵ�������
	 * 
	 * @param tempString
	 *            �Ը���ͷ���ʵ�ȫ������
	 */
	private static Iterator<String> it;
	private static char[] chars = null;;

	public static void setBaseValue(Map<String, String> tempStringMap,
			int tempBase) {
		it = tempStringMap.keySet().iterator();
		while (it.hasNext()) {
			chars = it.next().toCharArray();
			if (!isHave(base[tempBase] + chars[chars.length - 1])) {
				base[tempBase]++;
				it = tempStringMap.keySet().iterator();
			}
		}
	}

	/**
	 * �ҵ����ַ�����һ����λ���ַ�����һ����λ��
	 * 
	 * @param chars
	 *            ������ַ���char����
	 * @return
	 */
	public static int getBaseNum(char[] chars) {
		int tempBase = 0;
		if (chars.length == 2) {
			return chars[0];
		}
		for (int i = 0; i < chars.length - 1; i++) {
			if (i == 0) {
				tempBase += chars[i];
			} else {
				tempBase = base[tempBase] + chars[i];
			}
		}
		return tempBase;
	}

	/**
	 * �ж���base���������λ���Ƿ����������
	 * 
	 * @param num
	 *            base�����е�λ��
	 * @return
	 */
	public static boolean isHave(int num) {
		if (base[num] > 0) {
			return false;
		} else {
			return true;
		}
	}
}
