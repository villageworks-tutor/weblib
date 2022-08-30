package la.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.villageworks.datautils.core.UtilsCore;
import la.bean.MemberBean;
import la.dao.DAOException;
import la.dao.MemberDAO;

public class MemberService extends Service {

	public MemberService(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String execute() throws DAOException {
		// 遷移先URLの初期化
		String nextPage = this.request.getContextPath();
		// actionキーを取得
		String action = this.parameters.getValue("action");
		if (action == null || action.isEmpty()) {
			// 強制的に認証ページに遷移
			nextPage = this.request.getContextPath();
		} else if (action.equals("search")) {
			// リクエストパラメータを取得
			String mode = this.getParameters().getValue("mode");
			if (mode == null || mode.isEmpty() || mode.equals("search")) {
				// 利用者検索画面の表示
				nextPage = "pages/member/searchView.jsp";
			} else if (mode.equals("result")) {
				// 検索キーワード（利用者カード番号）から利用者を取得：検索キーワードが未入力の場合は全件検索なのでチェックはなし
				MemberDAO dao = new MemberDAO();
				MemberBean bean = dao.findByCard(this.parameters.getValue("key"));
				// リクエストスコープに利用者リストを登録
				this.request.setAttribute("member", bean);
				this.request.setAttribute("key", this.parameters.getValue("key"));
				// 遷移先URLを設定
				nextPage = "pages/member/searchResultView.jsp";
			}
		} else if (action.equals("edit")) {
			// リクエストパラメータを取得
			String mode = this.parameters.getValue("mode");
			int id = this.parameters.getValueToInt("id");
			if (UtilsCore.isEmpty(mode)) {
				// 利用者IDから利用者を取得：主キー検索を実行
				MemberDAO dao = new MemberDAO();
				MemberBean bean = dao.findByPrimaryKey(id);
				// リクエストスコープに登録
				this.request.setAttribute("member", bean);
				// 遷移先URLを設定
				nextPage = "pages/member/updateView.jsp";
			} else if (mode.equals("confirm")) {
				// リクエストパラメータから利用者をインスタンス化
				MemberBean bean = new MemberBean();
				bean.setId(this.parameters.getValueToInt("id"));
				bean.setCard(this.parameters.getValue("card"));
				bean.setName(this.parameters.getValue("name"));
				bean.setZipcode(this.parameters.getValue("zipcode"));
				bean.setAddress(this.parameters.getValue("address"));
				bean.setPhone(this.parameters.getValue("phone"));
				bean.setEmail(this.parameters.getValue("email"));
				int year = this.parameters.getValueToInt("year");
				int month = this.parameters.getValueToInt("month");
				int day = this.parameters.getValueToInt("day");
				bean.setBirthday(year, month, day);
				bean.setPriviledgeCode(this.parameters.getValueToInt("priviledgeCode"));
				bean.setPriviledgeName(this.parameters.getValue("priviledgeName"));
				bean.setCreatedAt(this.parameters.getValueToTimestamp("createdAt"));
				bean.setUpdatedAt(this.parameters.getValueToTimestamp("updatedAt"));
				
				// 入力値の妥当性検査
				List<String> messages = this.validate();
				if (messages.size() > 0) {
					this.request.setAttribute("messages", messages);
					this.request.setAttribute("member", bean);
					nextPage = "pages/member/updateView.jsp";
					return nextPage;
				}
				
				// リクエストスコープに登録
				HttpSession session = this.request.getSession();
				// 利用者のインスタンスをセッションスコープに登録
				session.setAttribute("member", bean);
				// 遷移先URLを設定
				nextPage = "pages/member/updateConfirmView.jsp";
			} else if (mode.equals("entry")) {
				// セッションから利用者を取得
				HttpSession session = this.request.getSession(false);
				if (session == null) {
					this.request.setAttribute("message", "タイム・アウトしています。最初から操作し直してください。");
					nextPage = "pages/error.jsp";
				}
				MemberBean bean = (MemberBean) session.getAttribute("member");
				if (bean == null) {
					this.request.setAttribute("message", "不正な操作なので、最初から操作し直してください。");
					nextPage = "pages/error.jsp";
				}
				// 利用者をリクエストスコープに登録
				this.request.setAttribute("member", bean);
				// 遷移先URLを設定
				nextPage = "pages/member/updateView.jsp";
			} else if (mode.equals("complete")) {
				// セッションから莉桜者を取得
				HttpSession session = this.request.getSession(false);
				if (session == null) {
					this.request.setAttribute("message", "タイム・アウトしています。最初から操作し直してください。");
					nextPage = "pages/error.jsp";
				}
				MemberBean bean = (MemberBean) session.getAttribute("member");
				if (bean == null) {
					this.request.setAttribute("message", "不正な操作なので、最初から操作し直してください。");
					nextPage = "pages/error.jsp";
				}
				// memberテーブルに利用者を登録
				MemberDAO dao = new MemberDAO();
				dao.update(bean);
				// リクエストスコープに登録
				this.request.setAttribute("card", bean.getCard());
				this.request.setAttribute("name", bean.getName());
				// 遷移先URLを設定
				nextPage = "pages/member/updateCompleteView.jsp";
			}
		} else if (action.equals("erasure")) {
			// リクエストパラメータを取得
			String mode = this.parameters.getValue("mode");
			int id = this.parameters.getValueToInt("id");
			String card = this.parameters.getValue("key");
			if (mode == null || mode.isEmpty() || mode.equals("confirm")) {
				// 送信された利用者IDの利用者インスタンスを取得
				MemberDAO dao = new MemberDAO();
				MemberBean bean = dao.findByPrimaryKey(id);
				// リクエストスコープに利用者インスタンスを登録
				this.request.setAttribute("member", bean);
				// 遷移先URLを設定
				nextPage = "pages/member/deleteConfirmView.jsp";
			} else if (mode.equals("complete")) {
				// MemberDAoを取得
				MemberDAO dao = new MemberDAO();
				// 削除を実行
				dao.delete(id);
				// リクエストスコープに登録
				this.request.setAttribute("card", card);
				// 遷移先URLを設定
				nextPage = "pages/member/deleteCompleteView.jsp";
			}
		}
		
		return nextPage;
	}

	/**
	 * リクエストパラメータの入力値の妥当性検査
	 * @return エラーメッセージ
	 */
	@Override
	List<String> validate() {
		List<String> messages = new ArrayList<>();
		// 氏名フィールドの妥当性検査
		if (!this.parameters.isRequired("name")) {
			// 必須入力検査
			messages.add("氏名を入力して下さい。");
		} else if (!this.parameters.isInLength("name", 50)) {
			// 文字数上限検査
			messages.add("氏名は50文字以下で入力して下さい。");
		}
		// 郵便番号フィールドの妥当性検査
		if (this.parameters.isRequired("zipcode")) {
			if (!this.parameters.isLength("zipcode", 8)) {
				// 文字数一致検査
				messages.add("郵便番号を入力する場合は8文字固定で入力して下さい。");
			}
		}
		// 住所フィールドの妥当性検査
		if (this.parameters.isRequired("address")) {
			if (!this.parameters.isInLength("address", 100)) {
				// 文字数上限検査
				messages.add("住所を入力する場合は100文字以下で入力して下さい。");
			}
		}
		// 電話番号フィールドの妥当性検査
		if (this.parameters.isRequired("phone")) {
			if (!this.parameters.isInRange("phone", 12, 14)) {
				// 文字数範囲検査
				messages.add("電話番号を入力する場合は12文字以上14文字以下で入力して下さい。");
			}
		}
		// 電子メールアドレスフィールドの妥当性検査
		if (this.parameters.isRequired("email")) {
			if (!this.parameters.isInLength("email", 255)) {
				// 文字数上限検査
				messages.add("電子メールアドレスを入力する場合は255文字以下で入力して下さい。");
			}
		}
		// 生年月日フィールドの妥当性検査
		if (this.parameters.isRequired("birthday")) {
			if (!this.parameters.isLength("birthday", 10)) {
				// 文字数上限検査
				messages.add("生年月日を入力する場合は10文字以下で入力して下さい。");
			}
		}
		return messages;
	}

}
