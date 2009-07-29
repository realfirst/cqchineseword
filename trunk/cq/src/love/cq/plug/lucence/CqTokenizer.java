package love.cq.plug.lucence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import love.cq.splitWord.GetWords;
import love.cq.splitWord.impl.GetWordsImpl;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.Tokenizer;

public class CqTokenizer extends Tokenizer {
	
	private GetWordsImpl gwi = null ;
	private BufferedReader reader = null ;
	private String temp ;
	private String word ;
	
	public CqTokenizer(Reader reader ,boolean flag){
		this.reader = new BufferedReader(reader) ;
		if(gwi==null)gwi = new GetWordsImpl() ;
		setNext() ;
	}

	@Override
	public Token next() throws IOException {
			while((word=gwi.allWords())!=null){
				return new Token(word, gwi.offe, gwi.offe+word.length());
			}
			if(setNext()){
				return next() ;
			}
		return null ;
	}
	
	public boolean setNext(){
		try {
			while((temp=reader.readLine())!=null){
				gwi.setStr(temp) ;
				return true ;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	

	public void close() throws IOException {
		// TODO Auto-generated method stub
		reader.close();
	}

}
