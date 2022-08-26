package la.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.villageworks.datautils.api.Validator;
import jp.villageworks.datautils.core.UtilsCore;
import jp.villageworks.passwordutils.api.PasswordUtilsApi;
import la.bean.AuthBean;
import la.bean.SigninBean;
import la.dao.AuthDAO;
import la.dao.DAOException;

/**
 * ユーザ認証機能を実行するサービスクラス
 * @author tutor
 */
public class AuthService extends Service {

	/**
	 * コンストラクタ
	 * @param parameters リクエストパラメータ
	 */
	public AuthService(HttpServletRequest request) {
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
		} else if (action.equals("signin")) {
			// サインインの実行
			// リクエストパラメータを取得
			this.parameters.set("card", request.getParameter("card"));
			this.parameters.set("password", request.getParameter("password"));
			
			// 入力値チェック
			List<String> messages = this.validate();
			if (messages.size() > 0) {
				this.request.setAttribute("errors", messages);
				return "pages/signin.jsp";
			}
			
			// 認証クラスをインスタンス化
			AuthBean auth = new AuthBean();
			auth.setCard(this.parameters.getValue("card"));
			auth.setPassword(PasswordUtilsApi.createPassword(this.parameters.getValue("password"), this.parameters.getValue("card")));
			
			// ユーザ認証を実行
			AuthDAO dao = new AuthDAO();
			SigninBean bean = dao.findMemberByCardAndPassword(auth);
			// SigninBeanによって処理の分岐
			if (UtilsCore.isNull(bean)) {
				// SigninBeanがnullの場合：認証に失敗 ⇒ 自画面遷移（ユーザ認証ページに戻る）
				this.request.setAttribute("errors", "ユーザー認証に失敗しました。");
				return "pages/signin.jsp";
			}
			
			// セッションを取得
			HttpSession session = this.request.getSession();
			SigninBean signin = (SigninBean) session.getAttribute("signin");
			if (!UtilsCore.isNull(signin)) {
				// セッションがある場合は不正とみなす：エラーページに遷移
				session.removeAttribute("signin");
				this.request.setAttribute("message", "不正な操作をしました。");
				return "pages/error.jsp";
			}
			
			// セッションスコープに登録
			session.setAttribute("signin", bean);
			// 遷移先の設定
			nextPage = "pages/top.jsp";
		} else if (action.equals("signout")) {
			HttpSession session = this.request.getSession(false);
			if (session == null) {
				// セッションがない場合：タイムアウトと判断
				this.request.setAttribute("message", "タイムアウトしました。初めから操作をやり直して下さい。");
				return "pages/error.jsp";
			}
			SigninBean signin = (SigninBean) session.getAttribute("signin");
			if (signin == null) {
				// サインインユーザ情報がない場合：不正な操作と判断
				this.request.setAttribute("message", "不正な操作をしました。");
				return "pages/error.jsp";
			}
			// サインインユーザの情報を削除
			session.removeAttribute("signin");
			nextPage = "pages/signin.jsp";
		}
		return nextPage;
	}
	
	@Override
	List<String> validate() {
		List<String> messages = new ArrayList<>();
		if (!Validator.isRequired(this.parameters.getValue("card"))) {
			messages.add("利用者カード番号を入力して下さい。");
		}
		if (!Validator.isRequired(this.parameters.getValue("password"))) {
			messages.add("パスワードを入力して下さい。");
		}
		return messages;
	}

}

