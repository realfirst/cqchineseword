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
		if(isNameStopHave(chars[index + 1])){
			return 0 ;
		}
		if(isNameStopHave(chars[index + 2])){
			return 1 ;
		}
		if(isNameStopHave(chars[index + 3])){
			return 2 ;
		}
		return 2 ;
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
				"方网记者靳慧、张海盈、实习生霍世杰5月20日报道：今天上午10点，同济大学建校100周年庆典大会在同济大学大礼堂隆重举行。中共中央总书记、国家主席胡锦涛发来贺信。全国人大常委会副委员长李铁映，全国人大常委会副委员长顾秀莲，国务委员陈至立，中共上海市委书记习近平，德国前总理施罗德，中央纪委副书记张惠新，教育部部长周济，建设部部长汪光焘，中共上海市委副书记、市长韩正，科技部党组书记、副部长李学勇等出席庆典大会。　　庆典大会上首先宣读胡锦涛总书记贺信，之后科技部部长、同济大学校长万钢从“大学对社会的承诺”的角度讲述百年同济的辉煌历史和未来发展。来自德国柏林工业大学的校长库茨勒、中国科学院院士吴孟超、同济大学交通运输工程学院孙立军等也将作为友好学校代表、校友代表、师生代表先后致辞。　今天早晨8点开始，同济大学的师生、老校友们就陆续进入庆典大会会场，会场大屏幕上滚动播放着建校100周年的宣传片。庆典大会开始前举行了同济老校友互动活动，现场十分感人。　同济大学是教育部直属重点大学，创建于1907年，早期为德国医生在上海创办的德文医学堂，取名'同济'意蕴合作共济。1912年增设工学堂，1923年被批准改名为大学，1927年正式定为国立同济大学。抗战期间曾内迁经浙、赣、滇入川，1946年回迁上海并发展为以拥有理、工、医、文、法五大学院著称海内外的综合性大学。1952年院系调整后，同济大学成为国内土木建筑领域最大、专业最全的工科大学。　　1978年经中央批准恢复对德交流，在中科院学部委员李国豪校长领导下实行“两个转变”，即由土木为主的理工科大学向理工为主的多科性大学转变，由国内普通高校向作为中外文化交流“窗口”之一的国际性大学转变，从而迅速恢复和发展成为一所以工为主、理工结合，经、管、文、法各具特色的多科性大学。");
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
