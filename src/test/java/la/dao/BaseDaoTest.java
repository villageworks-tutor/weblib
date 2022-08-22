package la.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.lang.reflect.Field;
import java.sql.Connection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BaseDaoTest {

	@Nested
	@DisplayName("BaseDAO#constructorメソッドのテストクラス")
	class ConstructorTest {
		@Test
		@DisplayName("【Test-02】BaseDAOのconnフィールドはSQL実行オブジェクトである")
		void test_02() throws Exception {
			// setup：privateフィールドにアクセスするための前準備
			BaseDAO target = new BaseDAO();
			Class<? extends BaseDAO> clazz = target.getClass();
			Field targetField = clazz.getDeclaredField("conn");
			targetField.setAccessible(true);
			// execute
			Object actual = targetField.get(target);
			// verify
			assertThat(actual, is(instanceOf(Connection.class)));
		}
		@Test
		@DisplayName("【Test-01】BaseDAOはインスタンス化できる")
		void test_01() throws Exception {
			// execute
			Object actual = new BaseDAO();
			// verify
			assertThat(actual, is(instanceOf(BaseDAO.class)));
		}
	}

}
