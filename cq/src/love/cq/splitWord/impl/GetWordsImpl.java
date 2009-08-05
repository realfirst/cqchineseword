package love.cq.splitWord.impl;

import love.cq.library.InitDictionary;
import love.cq.splitWord.GetWords;
import love.cq.util.WordAlert;

public class GetWordsImpl implements GetWords {

	/**
	 * base: 数组用来存放单词的转换..其实就是一个DFA转换过程
	 */
	private static int[] base = null;
	/**
	 * check: 数组用来验证里面存储的是上一个状态的位置
	 */
	private static int[] check = null;
	/**
	 * status: 用来判断一个单词的状态 1.为不成词.处于过度阶段 2.成次也可能是词语的一部分. 3.词语结束 example: 中 1 中华
	 * 2 中华人 1 中华人民 3
	 */
	public static byte[] status = null;
	/**
	 * words : 数组所在位置的词
	 */
	public static String[] words = null;
	/**
	 * 记录上一次的偏移量
	 */
	private int tempOffe ;
	/**
	 * offe : 当前词的偏移量
	 */
	public int offe ;

	/**
	 * 构造方法，同时加载词典,传入词语相当于同时调用了setStr() ;
	 */
	public GetWordsImpl(String str) {
		InitDictionary.init();
		base = InitDictionary.base;
		check = InitDictionary.check;
		status = InitDictionary.status;
		words = InitDictionary.words ;
		chars = str.toCharArray();
	}

	/**
	 * 构造方法，同时加载词典
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
	}
	
	public void setStr(char[] chars) {
		this.chars = chars;
	}

	public char[] chars;
	private int charHashCode;
	private int start = 0;
	public int end = 0;
	private int baseValue = 0;
	private int checkValue = 0;
	private int tempBaseValue = 0 ;
	public int i = 0;
	private String str;

	public String allWords() {
		for (; i < chars.length; i++) {
			charHashCode = chars[i] ;
			end++;
			switch (getStatement()) {
			case 0:
				i = start;
				start++;
				end = 0;
				baseValue = 0;
				break;
			case 2:
				i++;
				offe = tempOffe+start ;
				return words[baseValue] ;
			case 3:
				offe = tempOffe+start ;
				start++;
				i = start;
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
				offe = tempOffe+start ;
				str = WordAlert.alertEnglish(chars, start, end);
				start = start+end ;
				i = start ;
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
				offe = tempOffe+start ;
				str = WordAlert.alertNumber(chars, start, end);
				start = start+end ;
				i = start ;
				end = 0 ;
				baseValue = 0;
				return str ;
			}
		}
		if(start++!=i){
			i = start;
			return allWords() ;
		}
		tempOffe += chars.length ; 
		start = 0;
		end = 0;
		baseValue = 0;
		i = 0;
		return null;
	}


	/**
	 * 根据用户传入的c得到单词的状态. 0.代表这个字不在词典中 1.继续 2.是个词但是还可以继续 3.停止已经是个词了
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
	 * 重设分词
	 */
	public void reset(){
		checkValue = 0;
		tempOffe = 0 ;
	}
	
	public static void main(String[] args) {
		String str = "大法师说的１２３４５６４９８７大法师说的12323123上半年城镇在岗职ｓｆａｓｄｆｓｄａＨＧＵＫＨＩＡＳＤＨＪＨＤＳＫｈｕｉ工平均工资14638元 同比增12%" ;
		GetWordsImpl gwi = new GetWordsImpl();
		long start = System.currentTimeMillis();
		String temp = null;
		for (int i = 0; i < 1; i++) {
			gwi.setStr(str) ;
			while ((temp = gwi.allWords()) != null) {
				System.out.println(temp+gwi.offe);
			}
		}
		System.out.println(System.currentTimeMillis() - start);
	}

}
