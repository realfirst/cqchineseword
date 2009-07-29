package love.cq.library;

import java.io.BufferedReader;

import love.cq.util.IOUtil;

public class InitDictionary {
	/**
	 * arrayLength : ��¼������Ĵ�С
	 */
	private static final int ARRAYLENGTH = 562940;
	/**
	 * base: ����������ŵ��ʵ�ת��..��ʵ����һ��DFAת������
	 */
	public static int[] base = new int[ARRAYLENGTH];
	/**
	 * check: ����������֤����洢������һ��״̬��λ��
	 */
	public static int[] check = new int[ARRAYLENGTH];
	/**
	 * status: �����ж�һ�����ʵ�״̬ 1.Ϊ���ɴ�.���ڹ��Ƚ׶� 2.�ɴ�Ҳ�����Ǵ����һ����. 3.������� example: �� 1 �л�
	 * 2 �л��� 1 �л����� 3
	 */
	public static byte[] status = new byte[ARRAYLENGTH];
	/**
	 * words : ��������λ�õĴ�
	 */
	public static String[] words = new String[ARRAYLENGTH];
	/**
	 * arraysPath: base�����Ӳ��λ��
	 */
	public static String arraysPath = "library/arrays.dic";
	/**
	 * arraysPath: base�����Ӳ��λ��
	 */
	public static String arraysNumberPath = "library/numberLibrary.dic";
	/**
	 * arraysPath: base�����Ӳ��λ��
	 */
	public static String arraysEnglishPath = "library/english/englishLibrary.dic";
	/**
	 * �����ʵ���ַ�����
	 */
	private static String charEncoding = "GBK";

	/**
	 * �жϴʵ��Ƿ���ع�
	 */
	private static boolean isInit = false;

	public static void init() {
		if (!isInit) {
			long start = System.currentTimeMillis();
			try {
				initArrays();
				isInit = true ;
				System.out.println("�ʵ���������ʱ:"
						+ (System.currentTimeMillis() - start) + "����");
			} catch (Exception e) {
				e.printStackTrace();
				System.err.print("�ʵ���س���");
			}
		}
	}

	/**
	 * ����base,check,natrue,status�ļ���
	 * 
	 * @throws Exception
	 */
	public static void initArrays() throws Exception {
		BufferedReader reader = IOUtil.getReader(arraysPath, charEncoding);
		initArrays(reader) ;
	}
	
	public static void initArrays(BufferedReader reader) throws Exception{
		String temp = null;
		String[] strs = null;
		int num = 0;
		while ((temp = reader.readLine()) != null) {
			strs = temp.split("	");
			num = Integer.parseInt(strs[0]);
			words[num] = strs[1] ;
			base[num] = Integer.parseInt(strs[2]);
			check[num] = Integer.parseInt(strs[3]);
			status[num] = Byte.parseByte(strs[4]);
		}
	}


	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		init();
		System.out.println(System.currentTimeMillis() - start);
	}
}
