package love.cq.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import love.cq.splitWord.impl.GetWordsImpl;

public class CmdDemo {
	public static void main(String[] args) {
		System.out.println("verison 0.1.0");
		System.out.println("�汾���������ݽṹ\"˫����tire��\",Ӧ����Ŀǰ�������ķִ��㷨.�汾ʵ���˶����Ĵ���.���ƥ�����Сƥ��������з֣�"
				+ "��ֻ��һ�����԰汾.û�ж��ٿ����õļ�ֵ.��������һ�����м����������ִ�.�Զ���ʵ�."
				+ "����,�����ȵ�ʶ��.������лл��Щ�������ҵ���.�������и��õĽ��������һ��������ϵ��!"
				+ "�ҵ���ϵ��ʽ��ansj-sun@163.com");
		System.out.println("�ʵ������.......");
		GetWordsImpl gwi = new GetWordsImpl();
		while (true) {
			System.out.println("************************");
			System.out.println("1.	ȫ�����������");
			System.out.println("2.	ȫ��������С���");
			System.out.println("3.	ȫ��ȫƥ��ȡ��");
			System.out.println("4.	ȫ���������ȡ��");
			System.out.println("5.	ȫ��������Сȡ��");
			System.out.println("************************");
			System.out.print("��ѡ����Ҫ���еĲ���(1-5):");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));
			String txt = null;
			try {
				while ((txt = reader.readLine()) != null) {
					int k = Integer.parseInt(txt);
					if (k < 1 || k > 5)
						throw new Exception();
					System.out.println("��������Ҫ��ֵľ���:");
					txt = reader.readLine() ;
					long start = System.currentTimeMillis();
					System.out.println("----------ƥ����------------");
					switch (k) {
					case 1:
						break;
					case 2:
						break;
					case 3:
						gwi.setStr(txt);
						while ((txt = gwi.allWords()) != null) {
							System.out.println(txt);
						}
						break;
					case 4:
						gwi.setStr(txt);
//						while ((txt = gwi.maxFrontWords()) != null) {
//							System.out.println(txt);
//						}
						break;
					case 5:
						gwi.setStr(txt);
//						while ((txt = gwi.minFrontWords()) != null) {
//							System.out.println(txt);
//						}
						break;

					}
					System.out.println("------------------------------");
					System.out.println("��������,����ʱ:"
							+ (System.currentTimeMillis() - start) + "ms.���س���������.....");
					reader.readLine(); 
					System.out.println("************************");
					System.out.println("1.	ȫ�����������");
					System.out.println("2.	ȫ��������С���");
					System.out.println("3.	ȫ��ȫƥ��ȡ��");
					System.out.println("4.	ȫ���������ȡ��");
					System.out.println("5.	ȫ��������Сȡ��");
					System.out.println("************************");
					System.out.print("��ѡ����Ҫ���еĲ���(1-5):");
				}
			} catch (Exception e) {
				System.out.println("����ѡ������.������ѡ��!");
			}
		}
	}
}
