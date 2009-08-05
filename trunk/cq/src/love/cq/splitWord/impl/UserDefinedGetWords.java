package love.cq.splitWord.impl;


import love.cq.domain.Forest;
import love.cq.domain.WoodInterface;
import love.cq.library.MakeArrayLibrary;


public class UserDefinedGetWords {
	public static final int MORESTRING = 1;
	public static final int MOREFILE = 0;
	private static final Forest FOREST = MakeArrayLibrary.getForest();
	private char[] chars;
	private String str ;
	/**
	 * 当前返还单词的偏移量
	 */
	public int offe ;
	/**
	 * 记录上一次的偏移量
	 */
	private int tempOffe ;

	

	public UserDefinedGetWords(String content) {
		chars = content.toCharArray();
	}
	public UserDefinedGetWords() {
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

	
	public String getStringWords() {
		if(!isBack||i==chars.length-1){
			i = root-1; 
		}
		for (i++; i < chars.length; i++) {
			branch = branch.get(chars[i]) ;
			if(branch==null){
				root++ ;
				branch = FOREST ;
				i = root -1 ;
				isBack = false;
				continue ;
			}
			switch (branch.getStatus()) {
			case 2:
				isBack = true ;
				offe = tempOffe + root ;
				return new String(chars, root, i-root+1);
			case 3:
				offe = tempOffe + root ;
				str = new String(chars, root , i-root+1) ;
				branch = FOREST;
				isBack = false;
				root++;
				return str ;
			}
		}
		tempOffe += chars.length ;
		return null;
	}
	
	public void setChars(char[] chars){
		this.chars = chars ;
	}

	public void reset(){
		tempOffe = 0 ;
		branch = FOREST ;
	}

	

	// 此处定义规则
	// status 此字的状态1，继续 2，是个词语但是还可以继续 ,3确定
	// 0继续,1,回退,2是个词但是可以继续,3确定


	public static void main(String[] args) {
		String temp = null;
		StringBuilder sb = new StringBuilder();

		UserDefinedGetWords word = new UserDefinedGetWords("用户自定义词典孙健");
		long start = System.currentTimeMillis();
		 while((temp=word.getStringWords())!=null){
			 sb.append(temp+word.offe+"\n") ;
		 }
		
		System.out.println(System.currentTimeMillis() - start);
		System.out.println(sb);
	}
}
