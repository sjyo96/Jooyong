/**********************************/
/* Table Name: MEMBER */
/**********************************/
CREATE TABLE MEMBER(
		id VARCHAR(20) 			NOT NULL,
		pw VARCHAR(20) 			NOT NULL,
		name VARCHAR(50) 		NOT NULL	CONSTRAINT name_check check(2 < name && 50 > name),
		gender CHAR(1) 			NOT NULL	CONSTRAINT gender_check check(gender in('M','F')),
		email VARCHAR(50) 		UNIQUE,
		mobile VARCHAR(13)		UNIQUE,
		phone VARCHAR(13),
		zip CHAR(5),
		address VARCHAR(200),
		birthday DATE 			NOT NULL,
		joindate DATE 
);


ALTER TABLE MEMBER ADD CONSTRAINT IDX_MEMBER_PK PRIMARY KEY (id);

