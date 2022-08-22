package la.bean;

/**
 * 認証済ユーザを管理するJaaBean
 * @author tutor
 */
public class SigninBean extends AuthBean {

	/**
	 * クラスフィールド
	 */
	/*
	private String card;    // 利用者カード番号
	private int priviledge; // 権限コード
	*/
	
	/**
	 * デフォルトコンストラクタ
	 */
	public SigninBean() {}

	/**
	 * コンストラクタ
	 * @param card       利用者カード番号
	 * @param priviledge 権限コード
	 */
	public SigninBean(String card, int priviledge) {
		this.card = card;
		this.priviledge = priviledge;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SigninBean [");
		builder.append("card=" + card + ", ");
		builder.append("priviledge=" + priviledge + "]");
		return builder.toString();
	}
	

}
