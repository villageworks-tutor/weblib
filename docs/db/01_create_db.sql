/**
 * 【データベース・データベースユーザの作成】
 * 		★ データベース接続情報 ★
 * 			JDBCドライバ：org.postgresql.Driver
 * 			URL：jdbc:postgreql:weblibdb
 *             　jdbc:postgresql://localhost:5432/weblibdb	
 * 			USER：librarian
 * 			PASSSWORD：himitu
 */

DROP DATABASE IF EXISTS weblibdb;
DROP USER IF EXISTS librarian;

CREATE USER librarian WITH PASSWORD 'himitu';
CREATE DATABASE weblibdb OWNER librarian ENCODING 'utf8';
