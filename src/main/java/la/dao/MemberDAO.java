package la.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import la.bean.MemberBean;

public class MemberDAO extends BaseDAO {
	
	private static final String SQL_FIND_BY_CARD = "SELECT member.id, member.card, member.name, member.zipcode, member.address, member.phone, member.email, member.birthday, member.priviledge AS priviledge_code, priviledge.name AS priviledge_name, created_at, updated_at, erasured_at"
												 + " FROM member"
												 + " JOIN priviledge ON member.priviledge = priviledge.code AND member.erasured_at IS NULL"
												 + " WHERE member.card = ?";
	private static final String SQL_FIND_BY_PK   = "SELECT member.id, member.card, member.name, member.zipcode, member.address, member.phone, member.email, member.birthday, member.priviledge AS priviledge_code, priviledge.name AS priviledge_name, created_at, updated_at, erasured_at"
												 + " FROM member"
												 + " JOIN priviledge ON member.priviledge = priviledge.code AND member.erasured_at IS NULL"
												 + " WHERE member.id = ?";
	private static final String SQL_UPDATE       = "UPDATE member SET"
												 + " card = ?, name = ?, zipcode = ?, address = ?, phone = ?, email = ?, birthday = ?,"
												 + " priviledge = ?, updated_at = current_timestamp"
												 + " WHERE id = ?";

	/**
	 * コンストラクタ
	 * @throws DAOException
	 */
	public MemberDAO() throws DAOException {
		super();
	}

	/**
	 * 指定された利用者カード番号に該当する利用者を取得する。<br />
	 * memberテーブルの利用者カード番号フィールドの一意性制約により該当する利用者は一意に決定できることが前提。
	 * @param card 電子メールアドレス
	 * @return 電子メールアドレスに該当する利用者がある場合はMemberBeanのインスタンス、それ以外はnull
	 * @throws DAOException
	 */
	public MemberBean findByCard(String card) throws DAOException {
		try (// SQL実行オブジェクトを取得
			 PreparedStatement pstmt = this.conn.prepareStatement(SQL_FIND_BY_CARD);) {
			// パラメータバインディング
			pstmt.setString(1, card);
			try (// SQLの実行と結果セットの取得
				 ResultSet rs = pstmt.executeQuery();) {
				// 結果セットから利用者のインスタンスを生成
				MemberBean bean = null;
				if (rs.next()) {
					bean = new MemberBean();
					bean.setId(rs.getInt("id"));
					bean.setCard(rs.getString("card"));
					bean.setName(rs.getString("name"));
					bean.setZipcode(rs.getString("zipcode"));
					bean.setAddress(rs.getString("address"));
					bean.setPhone(rs.getString("phone"));
					bean.setEmail(rs.getString("email"));
					bean.setBirthday(rs.getDate("birthday"));
					bean.setPriviledgeCode(rs.getInt("priviledge_code"));
					bean.setPriviledgeName(rs.getString("priviledge_name"));
				}
				// 利用者のインスタンスを返却
				return bean;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	/**
	 * 指定された主キーに対応する利用者を取得する。
	 * @param id 利用者ID：memberテーブルの主キー
	 * @return 指定された主キーに対応する利用者が存在する場合は利用者クラスのインスタンス、それ以外はnull
	 * @throws DAOException
	 */
	public MemberBean findByPrimaryKey(int id) throws DAOException {
		try (// SQL実行オブジェクトを取得
			 PreparedStatement pstmt = this.conn.prepareStatement(SQL_FIND_BY_PK);) {
			// パラメータバインディング
			pstmt.setInt(1, id);
			try (// SQLの実行と結果セットの取得
				 ResultSet rs = pstmt.executeQuery();) {
				// 結果セットから利用者のインスタンスを生成
				MemberBean bean = null;
				if (rs.next()) {
					bean = new MemberBean();
					bean.setId(rs.getInt("id"));
					bean.setCard(rs.getString("card"));
					bean.setName(rs.getString("name"));
					bean.setZipcode(rs.getString("zipcode"));
					bean.setAddress(rs.getString("address"));
					bean.setPhone(rs.getString("phone"));
					bean.setEmail(rs.getString("email"));
					bean.setBirthday(rs.getDate("birthday"));
					bean.setPriviledgeCode(rs.getInt("priviledge_code"));
					bean.setPriviledgeName(rs.getString("priviledge_name"));
					bean.setCreatedAt(rs.getTimestamp("created_at"));
					bean.setUpdatedAt(rs.getTimestamp("updated_at"));
				}
				// 利用者のインスタンスを返却
				return bean;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	/**
	 * 利用者の情報を更新する。
	 * @param member 更新情報を含んだ利用者クラスのインスタンス
	 * @return 処理に成功した場合は1、それ以外はDAO例外
	 * @throws DAOException
	 */
	public int update(MemberBean member) throws DAOException {
		try (// SQL実行オブジェクトを取得
			 PreparedStatement pstmt = this.conn.prepareStatement(SQL_UPDATE)) {
			// パラメータバインディング
			pstmt.setString(1, member.getCard());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getZipcode());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getBirthdayStr());
			pstmt.setInt(8, member.getPriviledgeCode());
			pstmt.setInt(9, member.getId());
			// SQLの実行
			int row = pstmt.executeUpdate();
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

}
