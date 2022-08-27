package la.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
			int id = this.parameters.getIntValue("id");
			if (UtilsCore.isEmpty(mode)) {
				// 利用者IDから利用者を取得：主キー検索を実行
				MemberDAO dao = new MemberDAO();
				MemberBean bean = dao.findByPrimaryKey(id);
				// リクエストスコープに登録
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
