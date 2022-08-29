package la.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.File;
import java.sql.Date;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import la.bean.MemberBean;
import la.matcher.EqualToMember;

class MemberDaoTest extends DBUnitTest {

	private static String PATH_MEMBER_WITH_PK  = "src/test/java/la/dao/_fixtures/member/default_pk.xml";
	private static String PATH_UPDATE_DEFAULT  = "src/test/java/la/dao/_fixtures/member/update_default.xml";
	private static String PATH_UPDATE_EXPECTED = "src/test/java/la/dao/_fixtures/member/update_expected.xml";
	// 比較から除外するフィールド名配列
	private static String[] EXCLUSIVE_FIELDS = {"created_at", "updated_at", "erasured_at", "id"};
	
	/** テスト対象うクラス：system under test */
	private MemberDAO sut;
	
	@BeforeEach
	void setUp() throws Exception {
		// テスト対象クラスのインスタンス化
		this.sut = new MemberDAO();
		// サンプルレコードの登録
		loadDefaultRecords(PATH_MEMBER_WITH_PK);
	}

	@AfterEach
	void tearDown() throws Exception {
		// 対象テーブルのサンプルレコードの全削除
		DatabaseOperation.DELETE_ALL.execute(connection, dataset);
	}
	
	@Nested
	@DisplayName("MemberDAO#update(MemberBean)メソッドのテストクラス")
	class UpdateTest {
		@BeforeEach
		void setUp() throws Exception {
			loadDefaultRecords(PATH_UPDATE_DEFAULT);
		}
		@AfterEach
		void tearDown() throws Exception {
			DatabaseOperation.DELETE_ALL.execute(connection, dataset);
		}
		
		@Test
		@DisplayName("【Test-22】住所を変更した利用者ID「41」の利用者は「古橋 正美」である。")
		void test_22() throws Exception {
			// setup
			int target = 41;
			MemberBean expected = new MemberBean();
			expected.setId(41);
			expected.setCard("12057327");
			expected.setName("古橋 正美");
			expected.setZipcode("504-0000");
			expected.setAddress("滋賀県各務原市");
			expected.setPhone("090-4613-0336");
			expected.setEmail("yoshiko_shimada@ztvzw.frig.fhbl");
			expected.setBirthday("1970-10-08");
			expected.setPriviledgeCode(0);
			
			// execute
			sut.update(expected);
			MemberBean actual = sut.findByPrimaryKey(target);
			
			// verify
			assertThat(actual, is(new EqualToMember(expected)));
		}
		@Test
		@DisplayName("【Test-21】利用者ID「41」の住所を「岐阜県各務原市」に変更できる")
		void test_21() throws Exception {
			// setup
			MemberBean target = new MemberBean();
			target.setId(41);
			target.setCard("12057327");
			target.setName("古橋 正美");
			target.setZipcode("504-0000");
			target.setAddress("滋賀県各務原市");
			target.setPhone("090-4613-0336");
			target.setEmail("yoshiko_shimada@ztvzw.frig.fhbl");
			target.setBirthday("1970-10-08");
			target.setPriviledgeCode(0);
			// 期待値XMLから期待値を取得
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File(PATH_UPDATE_EXPECTED));
			ITable expected = expectedDataSet.getTable("member");
			// execute
			sut.update(target);
			// 実行地の取得
			ITable actual = null;
			actual = connection.createDataSet().getTable("member");
			actual = DefaultColumnFilter.excludedColumnsTable(actual, EXCLUSIVE_FIELDS);
			
			// verify
			Assertion.assertEquals(expected, actual);
		}
	}
	
	@Nested
	@DisplayName("MemberDAO#findByPK(int)メソッドのテストクラス")
	class FindByPkTest {
		@Test
		@DisplayName("【Test-12】未登録の利用者ID「-1」の利用者を取得できない（nullである）")
		void test_12() throws Exception {
			// setup
			int target = -1;
			// execute
			MemberBean actual = sut.findByPrimaryKey(target);
			// verify
			assertThat(actual, is(nullValue()));
		}
		@Test
		@DisplayName("【Test-11】登録済の利用者ID「124」の利用者は「浜口 秋雄」である")
		void test_11() throws Exception {
			// setup
			int target = 124;
			MemberBean expected = new MemberBean();
			expected.setId(target);
			expected.setCard("12058021");
			expected.setName("浜口 秋雄");
			expected.setZipcode("259-0201");
			expected.setAddress("神奈川県足柄下郡真鶴町真鶴3-20-8");
			expected.setPhone("080-4751-9498");
			expected.setEmail("fujio_tsuchiya@yfqkvmrmfr.geq.bbl");
			expected.setBirthday(Date.valueOf("1987-11-15"));
			expected.setPriviledgeCode(1);
			
			// execute
			MemberBean actual = sut.findByPrimaryKey(target);
			// verify
			assertThat(actual, is(new EqualToMember(expected)));
		}
	}

	@Nested
	@DisplayName("MemberDAO#findByEmail(String)メソッドのテストクラス")
	class FineByEmailTest {
		@Test
		@DisplayName("【Test-02】未登録の利用者カード番号「@@@」に該当する利用者を取得できない（nullである）")
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
