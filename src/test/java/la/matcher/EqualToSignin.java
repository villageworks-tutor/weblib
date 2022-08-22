package la.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import la.bean.SigninBean;

/**
 * サインイン済ユーザを比較するカスタムMatcher
 * @author tutor
 */
public class EqualToSignin extends TypeSafeMatcher<SigninBean> {

	/** 期待値 */
	 private SigninBean expected;
	
	 /** 異なる値 */
	private String difference;
	
	/**
	 * コンストラクタ
	 * @param expected SigninBeanの期待値オブジェクト
	 */
	public EqualToSignin(SigninBean expected) {
		this.expected = expected;
	}
	
	/**
	 * 期待値と実行値を比較する。
	 * @param actual SigninBeanの実行値
	 * @return 期待値と実行値がすべて一致している場合はtrue、それ以外はfalse
	 */
	@Override
	protected boolean matchesSafely(SigninBean actual) {
		if (!actual.getCard().equals(expected.getCard())) {
			difference = "利用者カード番号";
			return false;
		} else if (actual.getPriviledge() != expected.getPriviledge()) {
			difference = "権限コード";
		} else {
			return true;
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
