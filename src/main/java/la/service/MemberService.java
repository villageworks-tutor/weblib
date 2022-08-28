package la.service;

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
			}
		}
		
		return nextPage;
	}

	@Override
	List<String> validate() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
