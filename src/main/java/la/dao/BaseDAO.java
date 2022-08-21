package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * すべてのDAOクラスが継承する基底クラス
 * @author tutor
 */
public class BaseDAO {
	
	/**
	 * クラス定数：データベース接続情報文字列項目名
	 */
	private static final String DB_PROPERTIY = "properties.db";
	private static final String JDBC_DRIVER = "JDBC_DRIVER";
	private static final String DB_URL = "DB_URL";
	private static final String DB_USER = "DB_USER";
	private static final String DB_PASSWORD = "DB_PASSWORD";
	
	/**
	 * クラスフィールド：データベース接続オブジェクト
	 */
	protected Connection conn;

	/**
	 * コンストラクタ
	 * @throws DAOExeption 
	 */
	public BaseDAO() throws DAOExeption {
		// データベース設定ファイルの読み込み：ResourceBundleを利用
		ResourceBundle db = ResourceBundle.getBundle(DB_PROPERTIY);
		// データベース接続情報
		String driver   = db.getString(JDBC_DRIVER);
		String url      = db.getString(DB_URL);
		String user     = db.getString(DB_USER);
		String password = db.getString(DB_PASSWORD);
		// データベースへの接続
		try {
			Class.forName(driver);
			this.conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DAOExeption("データベースへの接続に失敗しました。");
		}
	}

}
