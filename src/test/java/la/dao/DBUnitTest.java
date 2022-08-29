package la.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

class DBUnitTest {
	
	/** テスト補助定数 */
	private static final String DB_PROPERTY = "properties.db";
	private static final String JDBC_DRIVER = "JDBC_DRIVER";
	private static final String DB_URL      = "DB_URL";
	private static final String DB_USER     = "DB_USER";
	private static final String DB_PASSWORD = "DB_PASSWORD";
	
	/** テスト補助変数 */
	private static Connection jdbcConnection;      // JDBCベースのコネクション
	static IDatabaseConnection connection; // DBUnitベースのデータベース接続オブジェクト
	static IDataSet dataset;               // データセット

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// データベース設定ファイルの読み込み：ResourceBundleを利用
		ResourceBundle db = ResourceBundle.getBundle(DB_PROPERTY);
		// データベース接続情報
		String driver   = db.getString(JDBC_DRIVER);
		String url      = db.getString(DB_URL);
		String user     = db.getString(DB_USER);
		String password = db.getString(DB_PASSWORD);
		// テスト用データベースに接続
		Class.forName(driver);
		jdbcConnection = DriverManager.getConnection(url, user, password); // JDBCベースのコネクション
		connection = new DatabaseConnection(jdbcConnection);               // DBUnitベースのコネクション
	}
	
	@AfterAll
	static void tearDownClass() throws Exception {
		// 全レコードのレコードを削除
		DatabaseOperation.DELETE_ALL.execute(connection, dataset);
	}
	
	static void loadDefaultRecords(String targetPath) throws Exception {
		dataset = new FlatXmlDataSetBuilder().build(new FileInputStream(targetPath));
		DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);		
	}
	
}
