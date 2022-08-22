package la.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import la.bean.AuthBean;
import la.bean.SigninBean;

/**
 * ユーザ認証に関わるデータにアクセスするDAO
 * @author tutor
 */
public class AuthDAO extends BaseDAO {
	
	/**
	 * クラス定数
	 */
	private static final String SQL_FIND_AUTH = "SELECT auth.id, auth.card, auth.password, member.priviledge FROM auth, member "
											  + "WHERE auth.card = member.card AND (auth.card = ? AND auth.password = ?)";
	private static final String SQL_UPDATE_PASSWORD = "UPDATE auth set password= ? WHERE card = ?";
	/**
	 * コンストラクタ
	 * @throws DAOExeption
	 */
	public AuthDAO() throws DAOExeption {
		super();
	}

	/**
	 * 利用者カード番号とパスワードから利用者を取得する。
	 * @param  auth 利用者カード番号とパスワードを設定した認証クラスのインスタンス
	 * @return 該当する利用者がある場合は利用者クラスのインスタンス、それ以外の場合はnull
	 * @throws DAOExeption
	 */
	public SigninBean findMemberByCardAndPassword(AuthBean auth) throws DAOExeption {
		try (// SQL実行オブエクトの取得
		     PreparedStatement pstmt = this.conn.prepareStatement(SQL_FIND_AUTH);) {
			// パラメータバインディング
			pstmt.setString(1, auth.getCard());
			pstmt.setString(2, auth.getPassword());
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

	/**
	 * パスワードを更新する。
	 * @param auth 更新対象となる利用者の利用者カード番号と更新するパスワード（ハッシュ化されたパスワード）
	 * @throws DAOExeption
	 */
	public void update(AuthBean auth) throws DAOExeption {
		try (// SQLオブジェクトを取得
			 PreparedStatement pstmt = this.conn.prepareStatement(SQL_UPDATE_PASSWORD);) {
			// パラメータバインディング
			pstmt.setString(1, auth.getPassword());
			pstmt.setString(2, auth.getCard());
			// SQLを実行
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOExeption("レコードの操作に失敗しました。");
		}
	}

}
