package la.parameters;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PrametersTest {
	
	private Parameters sut;

	@BeforeEach
	void setUp() throws Exception {
		this.sut = new Parameters();
	}

	@Nested
	@DisplayName("MemberDAOに関連したパラメータのテストクラス")
	class MemberTest {
		@BeforeEach
		void setUp() {
			sut.set("id", "41");
			sut.set("card", "12057327");
			sut.set("name", "古橋 正美");
			sut.set("zipcode", "504-0000");
			sut.set("address", "滋賀県各務原市");
			sut.set("phone", "090-4613-0336");
			sut.set("email", "yoshiko_shimada@ztvzw.frig.fhbl");
			sut.set("birthday", "1970-10-08");
			sut.set("priviledge", "0");
		}
		
		@Nested
		@DisplayName("Parameters#isLength(Strng,int)メソッドのテストクラス")
		class IsLengthTest {
			@Test
			@DisplayName("【Test-22】キー「zipcode」のパラメータは7文字ではない")
			void test_22() {
				// setup
				String target = "zipcode";
				int length = 7;
				// execute
				// verify
				assertFalse(sut.isLength(target, length));
			}
			@Test
			@DisplayName("【Test-21】キー「zipcode」のパラメータは8文字である")
			void test_21() {
				// setup
				String target = "zipcode";
				int length = 8;
				// execute
				// verify
				assertTrue(sut.isLength(target, length));
			}
		}
		
		@Nested
		@DisplayName("Parameters#isInLength(String, int)メソッドのテストクラス")
		class IsNumericTest {
			@Test
			@DisplayName("【Test-12】キー「name」のパラメータは4文字以下ではない")
			void test_12() {
				// setup
				String target = "name";
				int upper = 4;
				// ececute
				// verify
				assertFalse(sut.isInLength(target, upper));
			}
			@Test
			@DisplayName("【Test-11】キー「name」のパラメータは5文字以下である")
			void test_11() {
				// setup
				String target = "name";
				int upper = 5;
				// execute
				// verify
				assertTrue(sut.isInLength(target, upper));
			}
		}
		
		@Nested
		@DisplayName("Parameters#isRequested(String)メソッドのテストクラス")
		class IsRequireedTest {
			@Test
			@DisplayName("【Test-03】キー「zipcode」がnullである場合は入力されていない")
			void test_03() {
				// setup
				String target = "zipcode";
				sut.set("zipcode", null);
				// execute
				// verify
				assertFalse(sut.isRequired(target));
			}
			@Test
			@DisplayName("【test-02】キー「name」が空文字の場合は入力されていない")
			void test_02() {
				// setup
				String target = "name";
				sut.set("name", "");
				// execute
				// verify
				assertFalse(sut.isRequired(target));
			}
			@Test
			@DisplayName("【Test-01】キー「card」は入力されている")
			void test_01() {
				// setup
				String target = "card";
				// verify
				assertTrue(sut.isRequired(target));
			}
		}
	}

}
