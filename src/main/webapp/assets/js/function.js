/**
 * optionタグを出力する。
 * @param int 表示するoptionタグの開始インデックス
 * @param int 表示するoptionタグの個数
 * @param int 選択されたvalue値
 */
function createOptions(lower = 1, upper = 12, selected = 1) {
	for (i = lower; i <= upper; i++) {
		if (i == selected) {
			document.write("<option value\"" + i + "\" selected>" + i + "</option>");
		} else {
			document.write("<option value\"" + i + "\">" + i + "</option>");
		}
	}
}

/**
 * 年選択セレクトボックスの選択肢タグを出力する。
 * @parama int 終了インデックス 
 * @parama int 選択されたvalue値
 */
function createYearOptions(upper, selected) {
	createOptions(1900, upper, selected);
}

/**
 * 月年選択セレクトボックスの選択肢タグを出力する。
 * @parama int 選択されたvalue値
 */
function createMonthOptions(selected) {
	createOptions(1, 12, selected);
}

/**
 * 日選択セレクトボックスの選択肢タグを出力する。
 * @parama int 選択されたvalue値
 */
function createDayOptions(selected) {
	createOptions(1, 31, selected);
}


