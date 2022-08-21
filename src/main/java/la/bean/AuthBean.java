package la.bean;

public class AuthBean {

	private int id;
	protected int priviledge;
	protected String card;
	private String password;
	
	/**
	 * デフォルトコンストラクタ
	 */
	public AuthBean() {}

	/**
	 * コンストラクタ
	 * @param id 台帳ID
	 * @param priviledge 権限コード
	 * @param card 利用者カード番号
	 * @param password パスワード
	 */
	public AuthBean(int id, int priviledge, String card, String password) {
		this.id = id;
		this.priviledge = priviledge;
		this.card = card;
		this.password = password;
	}

	/**
	 * 台帳IDを取得する。
	 * @return id 台帳ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * 台帳IDを設定する。
	 * @param id 設定する台帳ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 権限コードを取得する。
	 * @return priviledge 権限コード
	 */
	public int getPriviledge() {
		return priviledge;
	}

	/**
	 * 権限コードを設定する。
	 * @param priviledge 設定する権限コード
	 */
	public void setPriviledge(int priviledge) {
		this.priviledge = priviledge;
	}

	/**
	 * 利用者カード番号を取得する。
	 * @return card 莉桜者カード番号
	 */
	public String getCard() {
		return card;
	}

	/**
	 * 利用者カード番号を設定する。
	 * @param card 設定する利用者カード番号
	 */
	public void setCard(String card) {
		this.card = card;
	}

	/**
	 * パスワードを取得する。
	 * @return password パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * パスワードを設定する。
	 * @param password 設定するパスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthBean [");
		builder.append("id=" + id + ", ");
		builder.append("priviledge=" + priviledge + ", ");
		builder.append("card=" + card  + "]");
		return builder.toString();
	}
	
	

}
