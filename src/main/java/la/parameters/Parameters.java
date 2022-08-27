package la.parameters;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Parameters {
	
	/**
	 * クラスフィールド
	 */
	private Map<String, String> params;

	/**
	 * コンストラクタ
	 */
	public Parameters() {
		this.params = new HashMap<>();
	}
	
	/**
	 * パラメータフィールドに新たな要素を追加する。
	 * @param key   追加するキー
	 * @param value 追加するキーに対応する値
	 */
	public void set(String key, String value) {
		this.params.put(key, value);
	}
	
	/**
	 * 指定されtキーに対応する値を取得する。
	 * @param key キー
	 * @return 指定されたキーに対応する値
	 */
	public String getValue(String key) {
		return this.params.get(key);
	}
	
	/**
	 * キーリストを取得する。
	 * @return　クラスフィールドのキーセット
	 */
	public Set<String> getKeys() {
		return this.params.keySet();
	}

	/**
	 * 指定されたキーの値を整数に変換する。
	 * @param target 対象となる値のキー
	 * @return 数値に変換できる場合はその整数値、それ以外は0
	 */
	public int getIntValue(String target) {
		try {
			int value = Integer.parseInt(this.params.get(target));
			return value;
		} catch (NumberFormatException e) {
			return 0;
		}
	}

}
