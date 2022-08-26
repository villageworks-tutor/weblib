package la.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import la.bean.MemberBean;

/**
 * サインイン済ユーザを比較するカスタムMatcher
 * @author tutor
 */
public class EqualToMember extends TypeSafeMatcher<MemberBean> {

	/** 期待値 */
	 private MemberBean expected;
	
	 /** 異なる値 */
	private String difference;
	
	/**
	 * コンストラクタ
	 * @param expected SigninBeanの期待値オブジェクト
	 */
	public EqualToMember(MemberBean expected) {
		this.expected = expected;
	}
	
	/**
	 * 期待値と実行値を比較する。
	 * @param actual SigninBeanの実行値
	 * @return 期待値と実行値がすべて一致している場合はtrue、それ以外はfalse
	 */
	@Override
	protected boolean matchesSafely(MemberBean actual) {
		if (!actual.getCard().equals(expected.getCard())) {
			difference = "利用者カード番号";
			return false;
		}
		if (!actual.getName().equals(expected.getName())) {
			difference = "氏名";
			return false;
		}
		if (!actual.getZipcode().equals(expected.getZipcode())) {
			difference = "郵便番号";
			return false;
		}
		if (!actual.getAddress().equals(expected.getAddress())) {
			difference = "住所";
			return false;
		}
		if (!actual.getPhone().equals(expected.getPhone())) {
			difference = "電話番号";
			return false;
		}
		if (!actual.getEmail().equals(expected.getEmail())) {
			difference = "電子メールアドレス";
			return false;
		}
		if (!actual.getBirthday().equals(expected.getBirthday())) {
			difference = "生年月日";
			return false;
		}
		if (actual.getPriviledgeCode() != expected.getPriviledgeCode()) {
			difference = "権限コード";
			return false;
		}
		
		return true;
	}

	/**
	 * 期待値と実行値が異なる場合のメッセージを生成する。
	 * @param description 期待値と実行値が異なっているフィールド名
	 */
	@Override
	public void describeTo(Description description) {
		description.appendValue(expected);
		description.appendText(difference).appendText("が異なっています。");
	}

}
