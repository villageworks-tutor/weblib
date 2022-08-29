package la.parameters;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jp.villageworks.datautils.api.Validator;
import jp.villageworks.dateutil.DateConverter;

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
	 * @param key 対象となるパラメータのキー
	 * @return 数値に変換できる場合はその整数値、それ以外は0
	 */
	public int getValueToInt(String key) {
		try {
			int value = Integer.parseInt(this.params.get(key));
			return value;
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	/**
	 * 【文字列-日付変換】指定されたキーの値をjava.sql.Dateクラスのインスタンスに変換する。
	 * @param key 対象となるパラメータのキー
	 * @return java.sql.Dateクラスのインスタンス
	 */
	public Date getValueToDate(String key) {
		LocalDate date = DateConverter.strToLocalDate(this.params.get(key));
		return DateConverter.localDateToSql(date);
	}
	
	/**
	 * 【文字列-日時変換】指定されたキーの値をTimestampクラスのインスタンスに変換する。
	 * @param key 対象となるパラメータのキー
	 * @return Timestampクラスのインスタンス
	 */
	public Timestamp getValueToTimestamp(String key) {
		LocalDate date = DateConverter.strToLocalDate(this.params.get(key));
		return DateConverter.localDateToTimestamp(date);
	}

	/**
	 * 【必須入力検査】指定されたキーのパラメータが必須入力であるかどうかを検査する。
	 * @param key 検査対象キー
	 * @return 対象パラメータが空文字列またはnullである場合はfalse、それ以外はtrue
	 */
	public boolean isRequired(String key) {
		return Validator.isRequired(this.params.get(key));
	}
	
	/**
	 * 【文字数上限検査】指定されたキーのパラメータの文字数が指定された文字数以下であるかどうかを検査する。
	 * @param key 検査対象キー
	 * @param upper 文字数の上限
	 * @return 対象パラメータの文字数が指定された文字数以下である場合はtrue、それ以外はfalse
	 */
	public boolean isInLength(String key, int upper) {
		return Validator.isUnderLimit(this.params.get(key), upper);
	}

	/**
	 * 【文字数一致検査】指定されたキーのパラメータの文字数が指定された文字数と同じであるかどうかを検査する。
	 * @param key 検査対象キー
	 * @param length 文字数
	 * @return 対象パラメータの文字数が指定された文字数と同じである場合はtrue、それ以外はfalse
	 */
	public boolean isLength(String key, int length) {
		return Validator.isInRange(this.params.get(key), length, length);
	}

	/**
	 * 【文字数範囲検査】指定されたキーのパラメータの文字数が指定された文字数の下限と上限の範囲に入っているかどうかを検査する。
	 * @param key 検査対象キー
	 * @param lower 文字数の下限
	 * @param upper 文字数の上限
	 * @return 対象パラメータの文字数が指定された文字数の下限と上限の範囲に入っている場合はtrue、それ以外はfalse
	 */
	public boolean isInRange(String key, int lower, int upper) {
		return Validator.isInRange(this.params.get(key), lower, upper);
	}
	

}
