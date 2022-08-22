package la.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import jp.villageworks.passwordutils.api.PasswordUtilsApi;
import la.bean.SigninBean;
import la.matcher.EqualToSignin;

class AuthDaoTest extends DBUnitTest {
	
	/** テスト補助定数 */
	private static String PATH_AUTH_DEFAULT = "src/test/java/la/dao/_fixtures/auth/auth_default.xml";
	
	/** テスト対象うクラス：system under test */
	private AuthDAO sut;
	
	@BeforeEach
	void setUp() throws Exception {
		this.sut = new AuthDAO();
		loadDefaultRecords(PATH_AUTH_DEFAULT);
	}
		
	@Nested
	@DisplayName("AuthDAO#findMemberByCardAndPasswor(String, String)メソッドのテストクラス")
	class FindMemberTest {
		@AfterAll
		static void tearDownClass() throws Exception {
			DatabaseOperation.DELETE_ALL.execute(connection, dataset);
		}
		
		@Test
		@DisplayName("【Test-03】利用者カード番号「12056692」平文パスワード「password」の利用者はサインインできない")
		void test_03() throws Exception {
			// execute
			SigninBean actual = sut.findMemberByCardAndPassword("12056692", PasswordUtilsApi.createPassword("password", "12056692"));
			// verify
			assertThat(actual, is(nullValue()));
		}
		@Test
		@DisplayName("【Test-02】利用者カード番号「12056692」平文パスワード「QnLmqZ9b」の利用者はサインインできる")
		void test_02() throws Exception {
			// setup
			SigninBean expected = new SigninBean("12056692", 0);
			// execute
			SigninBean actual = sut.findMemberByCardAndPassword("12056692", PasswordUtilsApi.createPassword("QnLmqZ9b", "12056692"));
			// verify
			assertThat(actual, is(new EqualToSignin(expected)));
		}
		@Test
		@DisplayName("【Test-01】利用者カード番号「12056692」ハッシュ化されたパスワード「7ccb68e74ce41f7db7943a342890bbd43712cae0cf690705e706bd0eb56779c2」の利用者はサインインできる")
		void test_01() throws Exception {
			// setup
			SigninBean expected = new SigninBean("12056692", 0);
			// execute
			SigninBean actual = sut.findMemberByCardAndPassword("12056692", "7ccb68e74ce41f7db7943a342890bbd43712cae0cf690705e706bd0eb56779c2");
			// verify
			assertThat(actual, is(new EqualToSignin(expected)));
		}
	}

}
