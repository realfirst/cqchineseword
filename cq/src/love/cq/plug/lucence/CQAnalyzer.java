package love.cq.plug.lucence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

import love.cq.splitWord.impl.GetWordsImpl;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;

public class CQAnalyzer extends Analyzer{

	@Override
	public TokenStream tokenStream(String fieldName, Reader reader) {
		// TODO Auto-generated method stub
		return new CqTokenizer(reader, true);
	}
	

}
