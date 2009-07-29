package love.cq.splitWord.impl;

import love.cq.library.InitDictionary;
import love.cq.splitWord.GetWords;

public class GetWordsImpl implements GetWords {

	/**
	 * base: ����������ŵ��ʵ�ת��..��ʵ����һ��DFAת������
	 */
	public static int[] base = null;
	/**
	 * check: ����������֤����洢������һ��״̬��λ��
	 */
	public static int[] check = null;
	/**
	 * status: �����ж�һ�����ʵ�״̬ 1.Ϊ���ɴ�.���ڹ��Ƚ׶� 2.�ɴ�Ҳ�����Ǵ����һ����. 3.������� example: �� 1 �л�
	 * 2 �л��� 1 �л����� 3
	 */
	public static byte[] status = null;
	/**
	 * words : ��������λ�õĴ�
	 */
	public static String[] words = null;
	/**
	 * index : �ַ���ƫ����
	 */
	public static int index = 0;
	
	/**
	 * tempIndex : �ַ���һ�ε�ƫ����
	 */
	public static int tempIndex ;
	
	/**
	 * offe : ��ǰ�ʵ�ƫ����
	 */
	public int offe ;

	/**
	 * ���췽����ͬʱ���شʵ�,��������൱��ͬʱ������setStr() ;
	 */
	public GetWordsImpl(String str) {
		InitDictionary.init();
		base = InitDictionary.base;
		check = InitDictionary.check;
		status = InitDictionary.status;
		words = InitDictionary.words ;
		chars = str.toCharArray();
		index += tempIndex ;
		tempIndex = chars.length ;
	}

	/**
	 * ���췽����ͬʱ���شʵ�
	 */
	public GetWordsImpl() {
		InitDictionary.init();
		base = InitDictionary.base;
		check = InitDictionary.check;
		status = InitDictionary.status;
		words = InitDictionary.words ;
		chars = new char[0];
	}

	public void setStr(String str) {
		chars = str.toCharArray();
		index += tempIndex ;
		tempIndex = chars.length ;
	}

	private char[] chars;
	private int charHashCode;
	private int start = 0;
	public int end = 0;
	private int tempEnd = 0;
	private int baseValue = 0;
	private int checkValue = 0;
	private boolean hasEnd = false;
	private int tempBaseValue = 0 ;
	private int i = 0;
	private String str;

	public String allWords() {
		for (; i < chars.length; i++) {
			end++;
			charHashCode = chars[i];
			switch (getStatement()) {
			case 0:
				i = start;
				start++;
				end = 0;
				baseValue = 0;
				break;
			case 2:
				i++;
				offe = index +start ;
				return words[baseValue] ;
			case 3:
				start++;
				i = start;
				tempEnd = end;
				end = 0;
				tempBaseValue = baseValue ;
				baseValue = 0;
				return words[tempBaseValue];
			case 4:
				while(status[chars[i++]]==4&&i<chars.length){
					end++ ;
				}
				if(i!=chars.length){
					end-- ;
				}
				str = new String(chars, start, end).toLowerCase();
				start = start+end ;
				end = 0 ;
				baseValue = 0;
				return str ;
			case 5:
				while(status[chars[i++]]==5&&i<chars.length){
					end++ ;
				}
				if(i!=chars.length){
					end-- ;
				}
				str = new String(chars, start, end);
				start = start+end ;
				end = 0 ;
				baseValue = 0;
				return str ;
			}
		}
		if(start++!=i){
			i = start;
			return allWords() ;
		}
		start = 0;
		end = 0;
		baseValue = 0;
		i = 0;
		return null;
	}


	/**
	 * �����û������c�õ����ʵ�״̬. 0.��������ֲ��ڴʵ��� 1.���� 2.�Ǹ��ʵ��ǻ����Լ��� 3.ֹͣ�Ѿ��Ǹ�����
	 * 
	 * @param c
	 * @return
	 */
	private int getStatement() {
		if (charHashCode < 1) {
			return 0;
		}
		checkValue = baseValue;
		baseValue = base[checkValue] + charHashCode;
		if (check[baseValue] == checkValue || check[baseValue] == -1) {
			return status[baseValue];
		}
		return 0;
	}

	/**
	 * ����ִ�
	 */
	public void reset(){
		tempEnd = 0;
		checkValue = 0;
		offe = 0 ;
		index = 0 ;
	}
	
	public static void main(String[] args) {
		String str = "�ϰ�������ڸ�ְ��ƽ������14638Ԫ ͬ����12%" ;
		GetWordsImpl gwi = new GetWordsImpl();
		long start = System.currentTimeMillis();
		String temp = null;
		for (int i = 0; i < 1; i++) {
			gwi.setStr(str) ;
			while ((temp = gwi.allWords()) != null) {
				System.out.println(temp);
			}
		}
		System.out.println(System.currentTimeMillis() - start);
	}

}
