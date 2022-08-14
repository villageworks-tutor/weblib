DROP TABLE IF EXISTS reserve CASCADE;
DROP TABLE IF EXISTS status CASCADE;
DROP TABLE IF EXISTS auth CASCADE;
DROP TABLE IF EXISTS activity_log CASCADE;
DROP TABLE IF EXISTS book_ledger CASCADE;
DROP TABLE IF EXISTS book_catalogue CASCADE;
DROP TABLE IF EXISTS publisher CASCADE;
DROP TABLE IF EXISTS category CASCADE;
DROP TABLE IF EXISTS member CASCADE;
DROP TABLE IF EXISTS priviledge CASCADE;
DROP TABLE IF EXISTS base_codes CASCADE;
DROP TABLE IF EXISTS base_table  CASCADE;

/**********************************/
/* �}�X�^�e�[�u���p���e�[�u�� */
/**********************************/
CREATE TABLE base_codes (
	code INTEGER NOT NULL,
	name VARCHAR(20)
);

/**********************************/
/* �e�[�u����: ���ރ}�X�^ */
/**********************************/
CREATE TABLE category () INHERITS (base_codes);
ALTER TABLE category ADD CONSTRAINT IDX_category_PK PRIMARY KEY (code);

/**********************************/
/* �e�[�u����: �����}�X�^ */
/**********************************/
CREATE TABLE priviledge () INHERITS (base_codes);
ALTER TABLE priviledge ADD CONSTRAINT IDX_priviledge_PK PRIMARY KEY (code);

/**********************************/
/* �e�[�u����: �o�ŎЃ}�X�^ */
/**********************************/
CREATE TABLE publisher () INHERITS (base_codes);
ALTER TABLE publisher ADD CONSTRAINT IDX_publisher_PK PRIMARY KEY (code);

/**********************************/
/* �e�[�u����: �󋵃}�X�^ */
/**********************************/
CREATE TABLE status () INHERITS (base_codes);
ALTER TABLE status ADD CONSTRAINT IDX_status_PK PRIMARY KEY (code);

/**********************************/
/* �Ǘ��e�[�u���p���e�[�u�� */
/**********************************/
CREATE TABLE base_table (
		created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
		updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
		erasured_at TIMESTAMP
);

/**********************************/
/* �e�[�u����: ���p�҃}�X�^ */
/**********************************/
CREATE TABLE member(
		id         SERIAL, -- nextval('member_id_seq')
		card       CHAR(8)     NOT NULL,
		name       VARCHAR(50) NOT NULL,
		zipcode    CHAR(8),
		address    VARCHAR(100),
		phone      VARCHAR(14),
		email      VARCHAR(255),
		birthday   CHAR(10),
		priviledge INTEGER NOT NULL
) INHERITS (base_table);
ALTER TABLE member ADD CONSTRAINT IDX_member_PK        PRIMARY KEY (id);
ALTER TABLE member ADD CONSTRAINT IDX_member_FK        FOREIGN KEY (priviledge) REFERENCES priviledge (code);
ALTER TABLE member ADD CONSTRAINT UNQ_member_card_KEY  UNIQUE (card);
ALTER TABLE member ADD CONSTRAINT UNQ_member_email_KEY UNIQUE (email); -- �d�q���[���A�h���X�ŗ��p�Ҍ������s��

/**********************************/
/* �e�[�u����: �����ژ^ */
/**********************************/
CREATE TABLE book_catalogue(
		id           SERIAL, -- nextval('book_catalogue_id_seq')
		isbn         CHAR(14)     NOT NULL,
		category_cd  INTEGER      NOT NULL,
		title        VARCHAR(255) NOT NULL,
		author       VARCHAR(255),
		publisher_cd INTEGER NOT NULL,
		published_at DATE NOT NULL
) INHERITS (base_table);
ALTER TABLE book_catalogue ADD CONSTRAINT IDX_book_catalogue_PK       PRIMARY KEY (id);
ALTER TABLE book_catalogue ADD CONSTRAINT IDX_book_catalogue_ccd_FK   FOREIGN KEY (category_cd) REFERENCES category (code);
ALTER TABLE book_catalogue ADD CONSTRAINT IDX_book_catalogue_pcd_FK   FOREIGN KEY (publisher_cd) REFERENCES publisher (code);
ALTER TABLE book_catalogue ADD CONSTRAINT UNQ_book_catalogue_isbn_KEY UNIQUE (isbn);

/**********************************/
/* �e�[�u����: �����䒠 */
/**********************************/
CREATE TABLE book_ledger(
		id SERIAL, -- nextval('book_ledger_id_seq')
		isbn        CHAR(14) NOT NULL,
		arraival_at DATE     DEFAULT CURRENT_DATE,
		disposal_at DATE,
		description TEXT
);
ALTER TABLE book_ledger ADD CONSTRAINT IDX_book_ledger_PK PRIMARY KEY (id);
ALTER TABLE book_ledger ADD CONSTRAINT IDX_book_ledger_FK FOREIGN KEY (isbn) REFERENCES book_catalogue (isbn);

/**********************************/
/* �e�[�u����: �ݏo�䒠 */
/**********************************/
CREATE TABLE activity_log(
		id          SERIAL, -- nextval('activity_log_id_seq')
		uid         CHAR(8)                        NOT NULL,
		bid         INT                            NOT NULL,
		borrow_at   DATE DEFAULT CURRENT_DATE      NOT NULL,
		returned_at DATE,
		due_date    DATE                           NOT NULL,
		description TEXT
);
ALTER TABLE activity_log ADD CONSTRAINT IDX_activity_log_PK      PRIMARY KEY (id);
ALTER TABLE activity_log ADD CONSTRAINT IDX_activity_log_user_FK FOREIGN KEY (uid) REFERENCES member (card);
ALTER TABLE activity_log ADD CONSTRAINT IDX_activity_log_book_FK FOREIGN KEY (bid) REFERENCES book_ledger (id);

/**********************************/
/* �e�[�u����: �F�� */
/**********************************/
CREATE TABLE auth(
		id          SERIAL, -- nextval('auth_id_seq')
		card        CHAR(8)  NOT NULL,
		password    CHAR(64) NOT NULL
) INHERITS (base_table);
ALTER TABLE auth ADD CONSTRAINT IDX_auth_PK       PRIMARY KEY (id);
ALTER TABLE auth ADD CONSTRAINT IDX_auth_FK       FOREIGN KEY (card) REFERENCES member (card);
ALTER TABLE auth ADD CONSTRAINT UNQ_auth_card_KEY UNIQUE (card);
ALTER TABLE auth ADD CONSTRAINT UNQ_auth_KEY      UNIQUE(card, password); -- ���p�҃J�[�h�ԍ��ƃp�X���[�h�̑g�ݍ��킹���d�����Ă͂����Ȃ��I

/**********************************/
/* �e�[�u����: �\��䒠 */
/**********************************/
CREATE TABLE reserve(
		id           SERIAL, -- nextval('reserve_iq_seq')
		uid          INT NOT NULL,
		bid          INT NOT NULL,
		reserved_at  DATE,
		status       SMALLINT,
		descrioption TEXT,
		code         INTEGER
);
ALTER TABLE reserve ADD CONSTRAINT IDX_reserve_FK FOREIGN KEY (status) REFERENCES status (code);

