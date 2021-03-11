CREATE SEQUENCE RECORDSEQ;
DROP SEQUENCE RECORDSEQ;
DROP TABLE RECORD;
CREATE TABLE RECORD( 
	RECORD_SEQ NUMBER(10) NOT NULL,	--글번호
	USER_ID VARCHAR2(100) NOT NULL,     --아이디
	RECORD_BOOKNAME VARCHAR2(1000),     --책이름
	RECORD_READINGPAGE VARCHAR2(1000),    --읽은페이지
	RECORD_READINGTIME NUMBER(10),   --소요시간
	RECORD_READINGCHARACTER NUMBER(10),  --읽은글자
    RECORD_READINGAVG NUMBER(10),     --평균값(읽은글자/소요시간)
	RECORD_READINGDATE DATE DEFAULT SYSDATE,  --읽은날짜
	CONSTRAINT PK_RECORD_RECORD_SEQ PRIMARY KEY(RECORD_SEQ),
	CONSTRAINT FK_RECORD_USER_ID FOREIGN KEY(USER_ID) REFERENCES MEMBER(USER_ID) ON DELETE CASCADE
		
);

select * from member;

INSERT INTO RECORD
VALUES(RECORDSEQ.NEXTVAL,'GUEST12','코로나를위한책','50,51',1,1000,12000,sysdate);

select * from record order by record_readingdate desc;
SELECT *FROM MEMBER;
SELECT B.* 
		FROM (SELECT A.*, ROWNUM AS RNUM
		FROM (SELECT * FROM RECORD ORDER BY RECORD_SEQ DESC) A) B
		WHERE RNUM BETWEEN 1 AND 10 AND USER_ID = 'GUEST';

