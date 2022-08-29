package la.bean;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

import jp.villageworks.dateutil.DateConverter;

public class MemberBean {

	/**
	 * クラスフィールド
	 */
	private int    id;             // 利用者ID
	private String card;           // 利用者カード番号
	private String name;           // 氏名
	private String zipcode;        // 郵便番号
	private String address;        // 住所
	private String phone;          // 電話番号
	private String email;          // 電子メールアドレス
	private Date   birthday;       // 生年月日
	private int    priviledgeCode; // 権限コード
	private String priviledgeName; // 権限名
	private Timestamp createdAt;   // 登録日
	private Timestamp updatedAt;   // 更新日
	private Timestamp erasuredAt;  // 削除日
	
	/**
	 * デフォルトコンストラクタ
	 */
	public MemberBean() {}
	
	/**
	 * コンストラクタ
	 * @param card           利用者カード番号
	 * @param name           氏名
	 * @param zipcode        郵便番号
	 * @param address        住所
	 * @param phone          電話番号
	 * @param email          電子メールアドレス
	 * @param birthday       生年月日
	 * @param priviledgeCode 権限コード
	 * @param priviledgeName 権限名
	 */
	public MemberBean(String card, String name, String zipcode, String address, String phone, String email,
			Date birthday, int priviledgeCode, String priviledgeName) {
		this.card = card;
		this.name = name;
		this.zipcode = zipcode;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.birthday = birthday;
		this.priviledgeCode = priviledgeCode;
		this.priviledgeName = priviledgeName;
	}


	/**
	 * コンストラクタ
	 * @param card           利用者カード番号
	 * @param name           氏名
	 * @param zipcode        郵便番号
	 * @param address        住所
	 * @param phone          電話番号
	 * @param email          電子メールアドレス
	 * @param birthday       生年月日
	 * @param priviledgeCode 権限コード
	 * @param priviledgeName 権限名
	 * @param createdAt      登録日時
	 * @param updatedAt      更新日時
	 * @param erasuredAt     削除日時
	 */
	public MemberBean(String card, String name, String zipcode, String address, String phone, String email,
			Date birthday, int priviledgeCode, String priviledgeName, Timestamp createdAt, Timestamp updatedAt, Timestamp erasuredAt) {
		this(card, name, zipcode, address, phone, email, birthday, priviledgeCode, priviledgeName);
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.erasuredAt = erasuredAt;
	}

	/**
	 * コンストラクタ
	 * @param id             利用者ID
	 * @param card           利用者カード番号
	 * @param name           氏名
	 * @param zipcode        郵便番号
	 * @param address        住所
	 * @param phone          電話番号
	 * @param email          電子メールアドレス
	 * @param birthday       生年月日
	 * @param priviledgeCode 権限コード
	 * @param priviledgeName 権限名
	 */
	public MemberBean(int id, String card, String name, String zipcode, String address, String phone, String email,
			Date birthday, int priviledgeCode, String priviledgeName, Timestamp createdAt, Timestamp updatedAt, Timestamp erasuredAt) {
		this(card, name, zipcode, address, phone, email, birthday, priviledgeCode, priviledgeName, createdAt, updatedAt, erasuredAt);
		this.id = id;
	}

	/**
	 * 利用者IDを取得する。
	 * @return id 利用者ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * 利用者IDを設定する。
	 * @param id 設定する利用者ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 利用者カード番号を取得する。
	 * @return card 利用者カード番号
	 */
	public String getCard() {
		return card;
	}

	/**
	 * 利用者カード番号を設定する。
	 * @param card 設定する利用者カード番号
	 */
	public void setCard(String card) {
		this.card = card;
	}

	/**
	 * 氏名を取得する。
	 * @return name 氏名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 氏名を設定する。
	 * @param name 設定する氏名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 郵便番号を取得する。
	 * @return zipcode 郵便番号
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * 郵便番号を設定する。
	 * @param zipcode 設定する郵便番号
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * 住所を取得する。
	 * @return address 住所
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 住所を設定する。
	 * @param address 設定する住所
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 電話番号を取得する。
	 * @return phone 電話番号
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 電話番号を設定する。
	 * @param phone 設定する電話番号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 電子メールアドレスを取得する。
	 * @return email 電子メールアドレス
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 電子メールアドレスを設定する。
	 * @param email 設定する電子メールアドレス
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 生年月日を取得する。
	 * @return birthday 生年月日
	 */
	public Date getBirthday() {
		return birthday;
	}
	
	/**
	 * 生年月日文字列を取得する。
	 * @return birthday 生年月日文字列：書式「yyyy-MM-dd」
	 */
	public String getBirthdayStr() {
		return this.birthday.toString();
	}

	/**
	 * 生年月日を設定する。
	 * @param birthday 設定する生年月日
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	/**
	 * 生年月日文字列から生年月日を設定する。
	 * @param birthday 設定する生年月日文字列：書式「yyyy-MM-dd」
	 */
	public void setBirthday(String birthday) {
		LocalDate day = DateConverter.strToLocalDate(birthday);
		this.birthday = DateConverter.localDateToSql(day);
	}
	
	/**
	 * 整数型の年、月、日をもとに生年月日を設定する。
	 * @param year  設定する年
	 * @param month 設定する月
	 * @param day   設定する日
	 */
	public void setBirthday(int year, int month, int day) {
		this.birthday = DateConverter.localDateToSql(LocalDate.of(year, month, day)); 
	}

	/**
	 * 権限コードを取得する。
	 * @return priviledge 権限コード
	 */
	public int getPriviledgeCode() {
		return priviledgeCode;
	}

	/**
	 * 権限コードを設定する。
	 * @param priviledge 設定する権限コード
	 */
	public void setPriviledgeCode(int priviledgeCode) {
		this.priviledgeCode = priviledgeCode;
	}

	/**
	 * 権限名を取得する。
	 * @return priviledgeName 権限名
	 */
	public String getPriviledgeName() {
		return priviledgeName;
	}

	/**
	 * 権限名を設定する。
	 * @param priviledgeName 設定する権限名
	 */
	public void setPriviledgeName(String priviledgeName) {
		this.priviledgeName = priviledgeName;
	}

	/**
	 * 登録日時を取得する。
	 * @return createdAt 登録日時
	 */
	public Date getCreatedAt() {
		LocalDate date = DateConverter.timestampToLocalDate(this.createdAt);
		return DateConverter.localDateToSql(date);
	}
	
	/**
	 * 登録日時を設定する。
	 * @param createdAt 登録日時
	 */
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * 更新日時を取得する。
	 * @return updatedAt 更新日時
	 */
	public Date getUpdatedAt() {
		LocalDate date = DateConverter.timestampToLocalDate(this.updatedAt);
		return DateConverter.localDateToSql(date);
	}

	/**
	 * 更新日時を設定する。
	 * @param createdAt 更新日時
	 */
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	/**
	 * 削除日時を取得する。
	 * @return erasuredAt 削除日時
	 */
	public Date getErasuredAt() {
		LocalDate date = DateConverter.timestampToLocalDate(this.erasuredAt);
		return DateConverter.localDateToSql(date);
	}
	
	/**
	 * 削除日時を設定する。
	 * @param createdAt 削除日時
	 */
	public void setErasuredAt(Timestamp erasuredAt) {
		this.erasuredAt = erasuredAt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberBean [");
		builder.append("id=" + this.id + ", ");
		builder.append("card=" + this.card + ", ");
		builder.append("name=" + this.name + ", ");
		builder.append("zipcode=" + this.zipcode + ", ");
		builder.append("address=" + this.address + ", ");
		builder.append("phone=" + this.phone + ", ");
		builder.append("email=" + this.email + ", ");
		builder.append("birthday=" + this.birthday + ", ");
		builder.append("priviledgeCode=" + this.priviledgeCode + ", ");
		builder.append("priviledgeName=" + this.priviledgeName + "]");
		return builder.toString();
	}
	

}
