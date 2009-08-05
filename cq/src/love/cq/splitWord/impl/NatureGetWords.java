package love.cq.splitWord.impl;

import java.util.Arrays;

import love.cq.domain.Forest;
import love.cq.domain.WoodInterface;
import love.cq.library.InitDictionary;
import love.cq.library.MakeArrayLibrary;
import love.cq.util.MyStaticValue;

public class NatureGetWords {
	private static final Forest FOREST = MakeArrayLibrary.getNatureForest();
	private char[] chars;
	/**
	 * 当前返还单词的偏移量
	 */
	public int offe;
	/**
	 * 记录上当前传入字符串的偏移量
	 */
	private int charsOffe;
	/**
	 * 记录上一次的偏移量
	 */
	private int tempOffe;


	public NatureGetWords(String content) {
		chars = content.toCharArray();
	}

	public NatureGetWords() {
		chars = new char[0];
	}

	// 当前词语状态
	byte status = 0;
	// 词语分枝
	WoodInterface branch = FOREST;
	// 起始根
	int root = 0;
	int i = root;
	boolean isBack = false;
	public int nature;
	int tempIndex = 0;
	int beginIndexTemp = 0;
	int endIndexTemp = 0;
	int index = 0;
	int end = 0;

	public String getStringWords() {
		// 结束词数
		end = 1;
		// 预知结束词
		index = 1;
		int i = charsOffe;
		for (i = charsOffe; i < chars.length; i++) {
			this.calculate(chars[i]);
			// 0 reset .1 go 2 tagGo 3.stop 4 .endTagGo 5 .back
			switch (status) {
			case 1:
				index++;
				break;
			case 2:
				end = index;
				index++;
				isBack = true;
				nature = branch.getNature();
				break;
			case 3:
				offe = charsOffe + tempOffe;
				charsOffe = charsOffe + index;
				isBack = false;
				nature = branch.getNature();
				branch = FOREST;
				beginIndexTemp = 0;
				setWords(charsOffe - index, nature, 3);
				if (tempIndex > 0) {
					return new String(chars, beginIndexTemp, endIndexTemp);
				} else {
					end = 1;
					index = 1;
				}
				break;
			case 4:
				branch = FOREST;
				offe = charsOffe + tempOffe;
				charsOffe = charsOffe + end;
				isBack = false;
				beginIndexTemp = 0;
				setWords(charsOffe - 1, nature, 2);
				if (tempIndex > 0) {
					return new String(chars, beginIndexTemp, endIndexTemp);
				} else {
					end = 1;
					index = 1;
					i = charsOffe - 1;
				}
				break;
			default:
				charsOffe++;
				branch = FOREST;
				break;
			}

		}
		charsOffe = 0;
		tempOffe += chars.length;
		return null;
	}

	public void setWords(int index, int nature, int status) {
		tempIndex = 0;
		switch (nature) {
		case 5:
			findNumberWords(index);
			if (tempIndex > 0) {
				setNumberBeginEnd(status);
			}
			break;
		case 7:
			tempIndex = findNameWords(index);

			if (tempIndex > 0) {
				setNameBeginEnd(status);
			}
			break;
		}
	}

	public void setNameBeginEnd(int status) {
		switch (status) {
		case 2:
			endIndexTemp = end + tempIndex;
			beginIndexTemp = charsOffe - 1;
			break;
		case 3:
			endIndexTemp = index + tempIndex;
			beginIndexTemp = charsOffe - index;
			charsOffe+=tempIndex ;
			break;
		}
	}

	public int findNameWords(int index) {
		if(index+2<=chars.length&&isNameStopHave(chars[index + 1])){
			return 0 ;
		}
		
		if(index+3<=chars.length&&isNameStopHave(chars[index + 2])){
			return 1 ;
		}
		if(index+3<=chars.length){
			return 2 ;
		}else{
			return 1 ;
		}
	}

	public boolean isNameStopHave(char c) {
		int m = Arrays.binarySearch(MyStaticValue.NAMESTOPALL, c);
		if (m > -1) {
			return true;
		}
		return false;
	}

	public void setNumberBeginEnd(int status) {
		switch (status) {
		case 2:
			endIndexTemp = end + tempIndex;
			beginIndexTemp = charsOffe - 1 - tempIndex;
			break;
		case 3:
			endIndexTemp = index + tempIndex;
			beginIndexTemp = charsOffe - index - tempIndex;
			break;
		}
	}

	public void findNumberWords(int index) {
		if (index > 0 && isNumberHave(chars[--index])) {
			tempIndex++;
			findNumberWords(index);
		}
	}

	public boolean isNumberHave(char c) {
		int m = Arrays.binarySearch(MyStaticValue.NUMBERALL, c);
		if (m > -1) {
			return true;
		}
		return false;
	}

	public void setChars(char[] chars) {
		this.chars = chars;
	}

	public void reset() {
		tempOffe = 0;
		branch = FOREST;
	}

	// 此处定义规则
	// 0 reset .1 go 2 tagGo 3.stop 4 .back
	// status 此字的状态1，继续 2，是个词语但是还可以继续 ,3确定
	public void calculate(char c) {
		if ((branch = branch.get(c)) != null) {
			status = branch.getStatus();
		} else {
			if (isBack) {
				status = 4;
			} else {
				status = 0;
			}
		}
	}

	public static void main(String[] args) {
		String temp = null;
		InitDictionary.init();
		StringBuilder sb = new StringBuilder();

		NatureGetWords word = new NatureGetWords(
				"方网记者靳慧、张海盈");
//		 NatureGetWords word = new NatureGetWords("国家主席胡锦涛发") ;
		long start = System.currentTimeMillis();
		while ((temp = word.getStringWords()) != null) {
			System.out.println(temp);
		}

		System.out.println(System.currentTimeMillis() - start);
		// System.out.println(sb);
		// InitDictionary.init() ;

	}
}
