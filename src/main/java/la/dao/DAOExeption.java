package la.dao;

/**
 * DAOクラスで発生する例外を共通した処理を行うために変換する独自例外
 * @author tutor
 */
public class DAOExeption extends Exception {

	/**
	 * コンストラクタ
	 * @param message 例外メッセージ
	 */
	public DAOExeption(String message) {
		super(message);
	}

}
