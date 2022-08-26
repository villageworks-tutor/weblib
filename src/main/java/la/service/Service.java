package la.service;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import la.dao.DAOException;
import la.parameters.Parameters;

public abstract class Service {

	/** 
	 * 【抽象メソッド】サービスに含まれる各種の処理を実行する。
	 * @trturn 遷移先URL
	 * @throws DAOException
	 */
	public abstract String execute() throws DAOException;
	
	/**
	 * 【抽象メソッド】パラメータの妥当性検査を行う。
	 * @return エラーメッセージ：エラーがない場合は空文字列
	 */
	abstract List<String> validate();

	/**
	 * クラスフィールド
	 */
	protected HttpServletRequest request;
	protected Parameters parameters;
	
	/**
	 * コンストラクタ
	 * @param parameters リクエストパラメータ
	 */
	public Service(HttpServletRequest request) {
		// リクエストをクラスフィールドに設定
		this.request = request;
		// リクエストパラメータをparametersフィールドへの入れ替え
		this.parameters = new Parameters();
		Enumeration<String> keys = request.getParameterNames();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			this.parameters.set(key, request.getParameter(key));
		}
	}
	
	/**
	 * リクエストパラメータを設定する。
	 * @param parameters リクエストパラメータ
	 */
	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}
	
	/**
	 * リクエストパラメータを取得する。
	 * @return リクエストパラメータ
	 */
	public Parameters getParameters() {
		return this.parameters;
	}
	
}
