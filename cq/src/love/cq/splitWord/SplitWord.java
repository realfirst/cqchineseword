package love.cq.splitWord;

public interface SplitWord {
	/**
	 * ���������
	 * @param str �������Ҫ�ִʵľ���
	 * @return
	 */
	public String tagMaxFront(String str) ;
	/**
	 * ������Сƥ����
	 * @param str �������Ҫ�ִʵľ���
	 * @return ��������ʺ�ľ���
	 */
	public String tagMinFront(String str) ;
	/**
	 * �������ƥ����
	 * @param str �������Ҫ�ִʵľ���
	 * @return ��������ʺ�ľ���
	 */
	public String tagMaxConverse(String str) ;
	/**
	 * ������Сƥ����
	 * @param str �������Ҫ�ִʵľ���
	 * @return ��������ʺ�ľ���
	 */
	public String tagMinConverse(String str) ;

}
