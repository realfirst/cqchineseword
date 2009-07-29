package love.cq.demo;

import java.io.BufferedReader;
import java.io.IOException;

import love.cq.splitWord.impl.GetWordsImpl;
import love.cq.util.IOUtil;

public class TxtGetWords {
	private static String pathIn = "test/in.txt" ;
	private static String pathTo = "test/to.txt" ;
	public static void main(String[] args) {
		try {
			long start = System.currentTimeMillis() ;
			txtMaxFrontGetWords(pathIn, pathTo) ;
			System.out.println("�ı��ִ�������ϼ��شʵ乲��ʱ:"+(System.currentTimeMillis()-start)+"ms");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * �ı�ȫ��ȫ��ȫƥ��
	 * @param path �����ı���·��
	 * @throws IOException 
	 */
	public static void txtAllGetWords(String pathIn ,String pathTo) throws IOException{
		BufferedReader reader = IOUtil.getReader(pathIn, "UTF-8") ;
		String temp = null ;
		StringBuilder sb = new StringBuilder() ;
		GetWordsImpl gwi = new GetWordsImpl() ;
		String word = null ;
		while((temp=reader.readLine())!=null){
			gwi.setStr(temp) ;
			while((word=gwi.allWords())!=null){
				sb.append(word) ;
				sb.append('\n') ;
			}
		}
		reader.close() ;
		IOUtil.Writer(pathTo, "GBK", sb.toString()) ;
	}
	/**
	 * ��������ı��ʻ�ȡ
	 * @param path �����ı���·��
	 * @throws IOException 
	 */
	public static void txtMaxFrontGetWords(String pathIn ,String pathTo) throws IOException{
		BufferedReader reader = IOUtil.getReader(pathIn, "UTF-8") ;
		String temp = null ;
		StringBuilder sb = new StringBuilder() ;
		GetWordsImpl gwi = new GetWordsImpl() ;
		String word = null ;
		while((temp=reader.readLine())!=null){
			gwi.setStr(temp) ;
//			while((word=gwi.maxFrontWords())!=null){
//				sb.append(word) ;
//				sb.append('\n') ;
//			}
		}
		reader.close() ;
		IOUtil.Writer(pathTo, "GBK", sb.toString()) ;
	}
	/**
	 * ������С�ı��ʻ�ȡ
	 * @param path �����ı���·��
	 * @throws IOException 
	 */
	public static void txtMinFrontWords(String pathIn ,String pathTo) throws IOException{
		BufferedReader reader = IOUtil.getReader(pathIn, "UTF-8") ;
		String temp = null ;
		StringBuilder sb = new StringBuilder() ;
		GetWordsImpl gwi = new GetWordsImpl() ;
		String word = null ;
		while((temp=reader.readLine())!=null){
			gwi.setStr(temp) ;
//			while((word=gwi.minFrontWords())!=null){
//				sb.append(word) ;
//				sb.append('\n') ;
//			}
		}
		reader.close() ;
		IOUtil.Writer(pathTo, "GBK", sb.toString()) ;
	}
	
}
