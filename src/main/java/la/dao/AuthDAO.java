package la.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import la.bean.SigninBean;

public class AuthDAO extends BaseDAO {
	
	/**
	 * クラス定数
	 */
	private static final String SQL_FUND_AUTH = "SELECT auth.id, auth.card, auth.password, member.priviledge FROM auth, member "
											  + "WHERE auth.card = member.card AND (auth.card = ? AND auth.password = ?)";
	/**
	 * コンストラクタ
	 * @throws DAOExeption
	 */
	public AuthDAO() throws DAOExeption {
		super();
	}

	/**
	 * 利用者カード番号とパスワードから利用者を取得する。
	 * @param card     利用者カード番号
	 * @param password パスワード
	 * @return 該当する利用者がある場合は利用者クラスのインスタンス、それ以外の場合はnull
	 * @throws DAOExeption
	 */
	public SigninBean findMemberByCardAndPassword(String card, String password) throws DAOExeption {
		try (// SQL実行オブエクトの取得
		     PreparedStatement pstmt = this.conn.prepareStatement(SQL_FUND_AUTH);) {
			// パラメータバインディング
			pstmt.setString(1, card);
			pstmt.setString(2, password);
			try (// SQLの実行と結果セットの取得
				 ResultSet rs = pstmt.executeQuery();) {
				// 結果セットから認証クラスのインスタンスを生成
				SigninBean bean = null;
				if (rs.next()) {
					bean = new SigninBean();
					bean.setCard(rs.getString("card"));
					bean.setPriviledge(rs.getInt("priviledge"));
				}
				return bean;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOExeption("レコードの取得に失敗しました。");
		}
	}
	
	

}
