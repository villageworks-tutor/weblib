package la.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.File;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import jp.villageworks.passwordutils.api.PasswordUtilsApi;
import la.bean.AuthBean;
import la.bean.SigninBean;
import la.matcher.EqualToSignin;

class AuthDaoTest extends DBUnitTest {
	
	/** テスト補助定数 */
	private static String PATH_AUTH_DEFAULT         = "src/test/java/la/dao/_fixtures/auth/auth_default.xml";
	private static String PATH_AUTH_UPDATE_EXPECTED = "src/test/java/la/dao/_fixtures/auth/auth_update_expected.xml";
	
	private static String[] EXCLUSIVE_FIELDS = {"created_at", "updated_at", "erasured_at", "id"};
	
	/** テスト対象うクラス：system under test */
	private AuthDAO sut;
	
	@BeforeEach
	void setUp() throws Exception {
		this.sut = new AuthDAO();
		loadDefaultRecords(PATH_AUTH_DEFAULT);
	}
	
	@Nested
	@DisplayName("AuthDAO#update(String, String)メソッドのテストクラス")
	class UpdateTest {
		@AfterAll
		static void testTearDownClass() throws Exception {
			DatabaseOperation.DELETE_ALL.execute(connection, dataset);
		}
		
		@Test
		@DisplayName("【Test-11】利用者カード番号「12057327」パスワード「qklRBB6q」のパスワードを「user0004」に変更できる")
		void test_11() throws Exception {
			// setup
			AuthBean target = new AuthBean();
			target.setCard("12057327");
			target.setPassword(PasswordUtilsApi.createPassword("user0004", target.getCard())); // パスワードのハッシュ化はビジネスロジックであるためサービス側で行う
			// 期待値XMLから期待値を取得
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File(PATH_AUTH_UPDATE_EXPECTED));
			ITable expected = expectedDataSet.getTable("auth");
			// execute
			sut.update(target);
			// 実行値の取得
			ITable actual = null;
			actual = connection.createDataSet().getTable("auth");
			actual = DefaultColumnFilter.excludedColumnsTable(actual, EXCLUSIVE_FIELDS);
			// verify
			Assertion.assertEquals(expected, actual);
		}
	}
	
	@Nested
	@DisplayName("AuthDAO#findMemberByCardAndPasswor(String, String)メソッドのテストクラス")
	class FindMemberTest {
		@AfterAll
		static void tearDownClass() throws Exception {
			DatabaseOperation.DELETE_ALL.execute(connection, dataset);
		}
		
		@Test
		@DisplayName("【Test-04】利用者カード番号「12056692」平文パスワード「password」の利用者はサインインできない")
		void test_04() throws Exception {
			// setup
			AuthBean target = new AuthBean();
			target.setCard("12056692");
			target.setPassword(PasswordUtilsApi.createPassword("password", "12056692")); // パスワードのハッシュ化はビジネスロジックであるためサービス側で行う
			// execute
			SigninBean actual = sut.findMemberByCardAndPassword(target);
			// verify
			assertThat(actual, is(nullValue()));
		}
		@Test
		@DisplayName("【Test-03】利用者カード番号「12057327」平文パスワード「qklRBB6q」の利用者はサインインできる")
		void test_03() throws Exception {
			AuthBean target = new AuthBean();
			target.setCard("12057327");
			target.setPassword(PasswordUtilsApi.createPassword("qklRBB6q", "12057327"));
			// setup
			SigninBean expected = new SigninBean("12057327", 0);
			// execute
			SigninBean actual = sut.findMemberByCardAndPassword(target);
			// verify
			assertThat(actual, is(new EqualToSignin(expected)));
		}
		@Test
		@DisplayName("【Test-02】利用者カード番号「12056692」平文パスワード「QnLmqZ9b」の利用者はサインインできる")
		void test_02() throws Exception {
			// setup
			AuthBean target = new AuthBean();
			target.setCard("12056692");
			target.setPassword(PasswordUtilsApi.createPassword("QnLmqZ9b", "12056692")); // パスワードのハッシュ化はビジネスロジックであるためサービス側で行う
			SigninBean expected = new SigninBean(target.getCard(), 0);
			// execute
			SigninBean actual = sut.findMemberByCardAndPassword(target);
			// verify
			assertThat(actual, is(new EqualToSignin(expected)));
		}
		@Test
		@DisplayName("【Test-01】利用者カード番号「12056692」ハッシュ化されたパスワード「7ccb68e74ce41f7db7943a342890bbd43712cae0cf690705e706bd0eb56779c2」の利用者はサインインできる")
		void test_01() throws Exception {
			// setup
			AuthBean target = new AuthBean();
			target.setCard("12056692");
			target.setPassword("7ccb68e74ce41f7db7943a342890bbd43712cae0cf690705e706bd0eb56779c2");
			SigninBean expected = new SigninBean("12056692", 0);
			// execute
			SigninBean actual = sut.findMemberByCardAndPassword(target);
			// verify
			assertThat(actual, is(new EqualToSignin(expected)));
		}
	}

}
