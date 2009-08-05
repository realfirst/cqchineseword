package love.cq.demo;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import love.cq.plug.lucence.CqTokenizer;
import love.cq.util.IOUtil;

import org.apache.lucene.analysis.Token;

public class Demo {
	public static void main(String[] args) throws IOException {
		StringReader sr = new StringReader(
				"方网记者靳慧、张海盈、实习生霍世杰5月20日报道：今天上午10点，同济大" +
				"学建校100周年庆典大会在同济大学大礼堂隆重举行。中共中央总书记、国家主" +
				"席胡锦涛发来贺信。全国人大常委会副委员长李铁映，全国人大常委会副委员长顾" +
				"秀莲，国务委员陈至立，中共上海市委书记习近平，德国前总理施罗德，中央纪委副" +
				"书记张惠新，教育部部长周济，建设部部长汪光焘，中共上海市委副书记、市长韩正，科" +
				"技部党组书记、副部长李学勇等出席庆典大会。　　庆典大会上首先宣读胡锦涛总书记贺信" +
				"，之后科技部部长、同济大学校长万钢从“大学对社会的承诺”的角度讲述百年同济的辉煌历" +
				"史和未来发展。来自德国柏林工业大学的校长库茨勒、中国科学院院士吴孟超、同济大学交通运输" +
				"工程学院孙立军等也将作为友好学校代表、校友代表、师生代表先后致辞。　今天早晨8点开始，同" +
				"济大学的师生、老校友们就陆续进入庆典大会会场，会场大屏幕上滚动播放着建校100周年的宣传片" +
				"。庆典大会开始前举行了同济老校友互动活动，现场十分感人。　同济大学是教育部直属重点大学，" +
				"创建于1907年，早期为德国医生在上海创办的德文医学堂，取名'同济'意蕴合作共济。1912年增设" +
				"工学堂，1923年被批准改名为大学，1927年正式定为国立同济大学。抗战期间曾内迁经浙、赣、滇入" +
				"川，1946年回迁上海并发展为以拥有理、工、医、文、法五大学院著称海内外的综合性大学。1952" +
				"年院系调整后，同济大学成为国内土木建筑领域最大、专业最全的工科大学。　　1978年经中央批" +
				"准恢复对德交流，在中科院学部委员李国豪校长领导下实行“两个转变”，即由土木为主的理工科大" +
				"学向理工为主的多科性大学转变，由国内普通高校向作为中外文化交流“窗口”之一的国际性大学转" +
				"变，从而迅速恢复和发展成为一所以工为主、理工结合，经、管、文、法各具特色的多科性大学。孙健");
		System.out.println();
		CqTokenizer cq = new CqTokenizer(sr,false);
		Token token = null;
		StringBuilder sb = new StringBuilder() ;
		long start = System.currentTimeMillis() ;
		while ((token = cq.next()) != null) {
			sb.append(token) ;
			sb.append('\n') ;
		}
		System.out.println(System.currentTimeMillis()-start);
		System.out.println(sb);
	}
}
