package love.cq.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import love.cq.splitWord.impl.GetWordsImpl;

public class CmdDemo {
	public static void main(String[] args) {
		System.out.println("verison 0.1.0");
		System.out.println("版本描述：数据结构\"双数组tire树\",应该是目前最快的中文分词算法.版本实现了对中文词组.最大匹配和最小匹配的完整切分，"
				+ "这只是一个尝试版本.没有多少可以用的价值.打算在下一版中中加入根据语义分词.自定义词典."
				+ "人名,地名等的识别.在这里谢谢那些帮助过我的人.如果大家有更好的建议或者想一起做就联系我!"
				+ "我的联系方式：ansj-sun@163.com");
		System.out.println("词典加载中.......");
		GetWordsImpl gwi = new GetWordsImpl();
		while (true) {
			System.out.println("************************");
			System.out.println("1.	全文正向最大标记");
			System.out.println("2.	全文正向最小标记");
			System.out.println("3.	全文全匹配取词");
			System.out.println("4.	全文正向最大取词");
			System.out.println("5.	全文正向最小取词");
			System.out.println("************************");
			System.out.print("您选择您要进行的操作(1-5):");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));
			String txt = null;
			try {
				while ((txt = reader.readLine()) != null) {
					int k = Integer.parseInt(txt);
					if (k < 1 || k > 5)
						throw new Exception();
					System.out.println("请输入您要拆分的句子:");
					txt = reader.readLine() ;
					long start = System.currentTimeMillis();
					System.out.println("----------匹配结果------------");
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
					System.out.println("操作结束,共用时:"
							+ (System.currentTimeMillis() - start) + "ms.按回车键键继续.....");
					reader.readLine(); 
					System.out.println("************************");
					System.out.println("1.	全文正向最大标记");
					System.out.println("2.	全文正向最小标记");
					System.out.println("3.	全文全匹配取词");
					System.out.println("4.	全文正向最大取词");
					System.out.println("5.	全文正向最小取词");
					System.out.println("************************");
					System.out.print("您选择您要进行的操作(1-5):");
				}
			} catch (Exception e) {
				System.out.println("您的选择有误.请重新选择!");
			}
		}
	}
}
