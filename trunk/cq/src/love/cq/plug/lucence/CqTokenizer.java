package love.cq.plug.lucence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import love.cq.splitWord.impl.GetWordsImpl;
import love.cq.splitWord.impl.NatureGetWords;
import love.cq.splitWord.impl.UserDefinedGetWords;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.Tokenizer;

public class CqTokenizer extends Tokenizer {
	/**
	 * GetWordsImpl : 双数组tire树的分词词典
	 */
	private GetWordsImpl gwi = null;
	/**
	 * udf : 用户自定义词典.
	 */
	private UserDefinedGetWords udf = null;
	/**
	 * ngw : 词性词典.5:量词 7:姓 11:机构(全部为值数将来以备拆解)
	 */
	NatureGetWords ngw = null;
	private BufferedReader reader = null;
	private String temp;
	private String word;
	private char[] chars;

	public CqTokenizer(Reader reader, boolean flag) {
		this.reader = new BufferedReader(reader);
		if (gwi == null)
			gwi = new GetWordsImpl();
		if (udf == null)
			udf = new UserDefinedGetWords();
		if (ngw == null)
			ngw = new NatureGetWords();
		setNext();
	}

	int step = 0;

	@SuppressWarnings("deprecation")
	public Token next() throws IOException {
		switch (step) {
		case 0:
			while ((word = gwi.allWords()) != null) {
				return new Token(word, gwi.offe, gwi.offe + word.length());
			}
			step++ ;
		case 1:
			while ((word = udf.getStringWords()) != null) {
				return new Token(word, udf.offe, udf.offe + word.length());
			}
			step++ ;
		case 2:
			while ((word = ngw.getStringWords()) != null) {
				return new Token(word, ngw.offe, ngw.offe + word.length());
			}
		}

		if (setNext()) {
			step = 0 ;
			return next();
		}
		return null;
	}

	public boolean setNext() {
		try {
			while ((temp = reader.readLine()) != null) {
				chars = temp.toCharArray();
				gwi.setStr(chars);
				udf.setChars(chars);
				ngw.setChars(chars);
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void close() throws IOException {
		// TODO Auto-generated method stub
		gwi.reset();
		udf.reset();
		ngw.reset();
		reader.close();
	}

}
