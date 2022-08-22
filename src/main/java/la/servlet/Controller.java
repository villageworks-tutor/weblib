package la.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.dao.DAOExeption;
import la.parameters.Parameters;
import la.service.AuthService;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	
	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの文字コードを設定
		request.setCharacterEncoding("utf-8");
		// リクエストパラメータのserviceキーを取得
		String serviceKey = request.getParameter("service");
		// serviceキーによるサーブレットの分岐
		String nextPage = "pages/signin.jsp";
		if (serviceKey == null || serviceKey.isEmpty()) {
			// 未送信または未入力である場合
		} else if (serviceKey.equals("authenticate")) {
			// ユーザ認証の場合
			AuthService service = new AuthService(request);
			try {
				nextPage = service.execute();
			} catch (DAOExeption e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		
		// 次の画面に遷移
		this.gotoPage(request, response, nextPage);
	}
	
	private Parameters createParameters(HttpServletRequest request) {
		Parameters parameters = new Parameters();
		Enumeration<String> keys = request.getParameterNames();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			parameters.set(key,  request.getParameter(key));
		}
		return parameters;
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String nextPage) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}

}
