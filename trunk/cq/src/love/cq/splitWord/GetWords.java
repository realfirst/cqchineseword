package love.cq.splitWord;

public interface GetWords {
	/**
	 * �������ȡ��
	 * @param str �������Ҫȡ�ʵľ���
	 * @return
	 */
//	public String maxFrontWords() ;
	/**
	 * ������Сƥ��ȡ��
	 * @param str �������Ҫȡ�ʵľ���
	 * @return ����ȡ�õ�һ����
	 */
//	public String minFrontWords() ;
	/**
	 * �������ƥ����
	 * @param str �������Ҫ�ִʵľ���
	 * @return ��������ʺ�ľ���
	 */
//	public String maxConverseWords(String str) ;
	/**
	 * ������Сƥ����
	 * @param str �������Ҫ�ִʵľ���
	 * @return ��������ʺ�ľ���
	 */
//	public String minConverseWords(String str) ;
	/**
	 * ȫ��ȫ��ȫƥ��
	 * @param str �������Ҫ�ִʵľ���
	 * @return ��������ʺ�ľ���
	 */
	public String allWords() ;
	/**
	 * ͬһ�����������
	 * @param temp ����ľ���
	 */
	public void setStr(String temp);
	/**
	 * ����ִ�
	 */
	public void reset() ;
}
