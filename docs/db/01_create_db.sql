/**
 * �y�f�[�^�x�[�X�E�f�[�^�x�[�X���[�U�̍쐬�z
 * 		�� �f�[�^�x�[�X�ڑ���� ��
 * 			JDBC�h���C�o�Forg.postgresql.Driver
 * 			URL�Fjdbc:postgreql:weblibdb
 *             �@jdbc:postgresql://localhost:5432/weblibdb	
 * 			USER�Flibrarian
 * 			PASSSWORD�Fhimitu
 */

DROP DATABASE IF EXISTS weblibdb;
DROP USER IF EXISTS librarian;

CREATE USER librarian WITH PASSWORD 'himitu';
CREATE DATABASE weblibdb OWNER librarian ENCODING 'utf8';
