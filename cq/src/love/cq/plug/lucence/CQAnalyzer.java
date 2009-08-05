package love.cq.plug.lucence;

import java.io.Reader;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;

public class CQAnalyzer extends Analyzer{

	@Override
	public TokenStream tokenStream(String fieldName, Reader reader) {
		// TODO Auto-generated method stub
		return new CqTokenizer(reader, true);
	}
	

}
