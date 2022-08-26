package la.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.sql.Date;

import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import la.bean.MemberBean;
import la.matcher.EqualToMember;

class MemberDaoTest extends DBUnitTest {

	/** テスト補助定数 */
	private static String PATH_DEFAULT = "src/test/java/la/dao/_fixtures/default.xml";
	// 比較から除外するフィールド名配列
	private static String[] EXCLUSIVE_FIELDS = {"created_at", "updated_at", "erasured_at", "id"};
	
	/** テスト対象うクラス：system under test */
	private MemberDAO sut;
	
	@BeforeEach
	void setUp() throws Exception {
		// テスト対象クラスのインスタンス化
		this.sut = new MemberDAO();
		// サンプルレコードの登録
		loadDefaultRecords(PATH_DEFAULT);
	}

	@AfterEach
	void tearDown() throws Exception {
		// 対象テーブルのサンプルレコードの全削除
		DatabaseOperation.DELETE_ALL.execute(connection, dataset);
	}

	@Nested
	@DisplayName("MemberDAO#findByEmail(String)メソッドのテストクラス")
	class FineByEmailTest {
		@Test
		@DisplayName("【Test-02】未登録の利用者カード番号「@@@」に該当する利用者はいない（nullである）")
		void test_02() throws Exception {
			// setup
			String target = "@@@";
			// execute
			MemberBean actual = sut.findByCard(target);
			// verify
			assertThat(actual, is(nullValue()));
		}
		@Test
		@DisplayName("【Test-01】登録済の両者カード番号「12058021」の利用者は「浜口 秋雄」である")
		void test01() throws Exception {
			// setup
			String target = "12058021";
			MemberBean expected = new MemberBean();
			expected.setCard("12058021");
			expected.setName("浜口 秋雄");
			expected.setZipcode("259-0201");
			expected.setAddress("神奈川県足柄下郡真鶴町真鶴3-20-8");
			expected.setPhone("080-4751-9498");
			expected.setEmail("fujio_tsuchiya@yfqkvmrmfr.geq.bbl");
			expected.setBirthday(Date.valueOf("1987-11-15"));
			expected.setPriviledgeCode(1);
			
			// execute
			MemberBean actual = sut.findByCard(target);
			
			// verify
			assertThat(actual, is(new EqualToMember(expected)));
			
		}
	}
	
}
