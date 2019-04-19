DROP TABLE TABLE_VAP;
DROP TABLE TABLE_CT;
DROP TABLE TABLE_UH;
DROP TABLE TABLE_GH;
DROP TABLE TABLE_VA;
DROP TABLE TABLE_IA;
DROP TABLE TABLE_ESUS;
DROP TABLE TABLE_ESVF;
DROP TABLE TABLE_ESV;
DROP TABLE TABLE_ESIF;
DROP TABLE TABLE_ESIC;
DROP TABLE TABLE_ESID;
DROP TABLE TABLE_ESIT;
DROP TABLE TABLE_ESI;
DROP TABLE TABLE_ESCOT;
DROP TABLE TABLE_ESCO;
DROP TABLE TABLE_ESA;
DROP TABLE TABLE_ES;
DROP TABLE TABLE_EU;
DROP TABLE TABLE_ECOT;
DROP TABLE TABLE_ECO;
DROP TABLE TABLE_EA;
DROP TABLE TABLE_E;
DROP TABLE TABLE_GCOT;
DROP TABLE TABLE_GCO;
DROP TABLE TABLE_GA;
DROP TABLE TABLE_G;
DROP TABLE TABLE_GCA;
DROP TABLE TABLE_U;

CREATE TABLE TABLE_U
(
    USER_ID VARCHAR2(2000) PRIMARY KEY,
    PASSWORD VARCHAR2(2000) NOT NULL,
    NAME VARCHAR2(2000) NOT NULL,
    IMAGE_ID VARCHAR2(2000),
    REGION VARCHAR2(2000) NOT NULL,
    CONTENT VARCHAR2(2000),
    INPUT_DATE NUMBER DEFAULT (CAST(SYSTIMESTAMP AS DATE) - DATE '1970-01-01')*24*60*60*1000 + MOD( EXTRACT( SECOND FROM SYSTIMESTAMP ), 1 ) * 1000 - 32400000
);
CREATE TABLE TABLE_GCA
(
    GROUP_CATEGORY_ID NUMBER PRIMARY KEY,
    FILENAME VARCHAR2(2000),
    NAME VARCHAR2(2000) NOT NULL UNIQUE
);
DROP SEQUENCE GCA_SEQ;
CREATE SEQUENCE GCA_SEQ;
CREATE TABLE TABLE_G
(
    GROUP_ID NUMBER PRIMARY KEY,
    GROUP_CATEGORY_ID NUMBER NOT NULL,
    REGION VARCHAR2(2000) NOT NULL,
    USER_ID VARCHAR2(2000) NOT NULL,
    NAME VARCHAR2(2000) NOT NULL,
    CONTENT VARCHAR2(2000) NOT NULL,
    GROUP_LOGO VARCHAR2(2000) UNIQUE,
    GROUP_IMAGE VARCHAR2(2000) UNIQUE,
    INPUT_DATE NUMBER DEFAULT (CAST(SYSTIMESTAMP AS DATE) - DATE '1970-01-01')*24*60*60*1000 + MOD( EXTRACT( SECOND FROM SYSTIMESTAMP ), 1 ) * 1000 - 32400000,
    CONSTRAINT G_UI_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID),
    CONSTRAINT G_GCI_FK FOREIGN KEY(GROUP_CATEGORY_ID) REFERENCES TABLE_GCA(GROUP_CATEGORY_ID)
);
DROP SEQUENCE G_SEQ;
CREATE SEQUENCE G_SEQ START WITH 100;
CREATE TABLE TABLE_GA
(
    USER_ID VARCHAR2(2000) NOT NULL,
    GROUP_ID NUMBER NOT NULL,
    BLIND NUMBER DEFAULT 1,
    INPUT_DATE NUMBER DEFAULT (CAST(SYSTIMESTAMP AS DATE) - DATE '1970-01-01')*24*60*60*1000 + MOD( EXTRACT( SECOND FROM SYSTIMESTAMP ), 1 ) * 1000 - 32400000,
    CONSTRAINT GA_UI_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID),
    CONSTRAINT GA_GI_FK FOREIGN KEY(GROUP_ID) REFERENCES TABLE_G(GROUP_ID),
    CONSTRAINT GA_PK PRIMARY KEY(USER_ID, GROUP_ID)
);
CREATE TABLE TABLE_GCO
(
	GROUP_COMMENT_ID NUMBER PRIMARY KEY,
	GROUP_ID NUMBER NOT NULL,
	USER_ID VARCHAR2(2000) NOT NULL,
	CONTENT VARCHAR2(2000) NOT NULL,
	INPUT_DATE NUMBER DEFAULT (CAST(SYSTIMESTAMP AS DATE) - DATE '1970-01-01')*24*60*60*1000 + MOD( EXTRACT( SECOND FROM SYSTIMESTAMP ), 1 ) * 1000 - 32400000,
	CONSTRAINT GCO_GI FOREIGN KEY(GROUP_ID) REFERENCES TABLE_G(GROUP_ID),
	CONSTRAINT GCO_UI FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID)
);
DROP SEQUENCE GCO_SEQ;
CREATE SEQUENCE GCO_SEQ;
CREATE TABLE TABLE_GCOT
(
	GROUP_COMMENT_ID NUMBER NOT NULL,
	TAG VARCHAR2(2000) NOT NULL,
	INPUT_DATE NUMBER DEFAULT (CAST(SYSTIMESTAMP AS DATE) - DATE '1970-01-01')*24*60*60*1000 + MOD( EXTRACT( SECOND FROM SYSTIMESTAMP ), 1 ) * 1000 - 32400000,
	CONSTRAINT GCOT_GCOI FOREIGN KEY(GROUP_COMMENT_ID) REFERENCES TABLE_GCO(GROUP_COMMENT_ID),
	CONSTRAINT GCOT_PK PRIMARY KEY(GROUP_COMMENT_ID, TAG)
);
CREATE TABLE TABLE_E
(
	EVENT_ID NUMBER PRIMARY KEY,
	NAME VARCHAR2(2000) NOT NULL,
	CONTENT VARCHAR2(2000) NOT NULL,
	GROUP_ID NUMBER NOT NULL,
	USER_ID VARCHAR2(2000) NOT NULL,
	IMAGE_ID VARCHAR2(2000),
	INPUT_DATE NUMBER DEFAULT (CAST(SYSTIMESTAMP AS DATE) - DATE '1970-01-01')*24*60*60*1000 + MOD( EXTRACT( SECOND FROM SYSTIMESTAMP ), 1 ) * 1000 - 32400000,
	CONSTRAINT E_GI_FK FOREIGN KEY(GROUP_ID) REFERENCES TABLE_G(GROUP_ID),
	CONSTRAINT E_UI_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID)
);
DROP SEQUENCE E_SEQ;
CREATE SEQUENCE E_SEQ;
CREATE TABLE TABLE_EA
(
	EVENT_ID NUMBER NOT NULL,
	USER_ID VARCHAR2(2000) NOT NULL,
	INPUT_DATE NUMBER DEFAULT (CAST(SYSTIMESTAMP AS DATE) - DATE '1970-01-01')*24*60*60*1000 + MOD( EXTRACT( SECOND FROM SYSTIMESTAMP ), 1 ) * 1000 - 32400000,
	CONSTRAINT EA_EI_FK FOREIGN KEY(EVENT_ID) REFERENCES TABLE_E(EVENT_ID),
	CONSTRAINT EA_UI_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID),
    CONSTRAINT EA_PK PRIMARY KEY(EVENT_ID, USER_ID)
);
CREATE TABLE TABLE_ECO
(
	EVENT_COMMENT_ID NUMBER PRIMARY KEY,
	EVENT_ID NUMBER NOT NULL,
	USER_ID VARCHAR2(2000) NOT NULL,
	CONTENT VARCHAR2(2000) NOT NULL,
	INPUT_DATE NUMBER DEFAULT (CAST(SYSTIMESTAMP AS DATE) - DATE '1970-01-01')*24*60*60*1000 + MOD( EXTRACT( SECOND FROM SYSTIMESTAMP ), 1 ) * 1000 - 32400000,
	CONSTRAINT ECO_EI FOREIGN KEY(EVENT_ID) REFERENCES TABLE_E(EVENT_ID),
	CONSTRAINT ECO_UI FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID)
);
DROP SEQUENCE ECO_SEQ;
CREATE SEQUENCE ECO_SEQ;
CREATE TABLE TABLE_ECOT
(
	EVENT_COMMENT_ID NUMBER NOT NULL,
	TAG VARCHAR2(2000) NOT NULL,
	INPUT_DATE NUMBER DEFAULT (CAST(SYSTIMESTAMP AS DATE) - DATE '1970-01-01')*24*60*60*1000 + MOD( EXTRACT( SECOND FROM SYSTIMESTAMP ), 1 ) * 1000 - 32400000,
	CONSTRAINT ECOT_ECOI FOREIGN KEY(EVENT_COMMENT_ID) REFERENCES TABLE_ECO(EVENT_COMMENT_ID),
	CONSTRAINT ECOT_PK PRIMARY KEY(EVENT_COMMENT_ID, TAG)
);
CREATE TABLE TABLE_EU
(
	EVENT_ID NOT NULL,
	GROUP_ID NOT NULL,
	CONSTRAINT EU_EI_FK FOREIGN KEY(EVENT_ID) REFERENCES TABLE_E(EVENT_ID),
	CONSTRAINT EU_GI_FK FOREIGN KEY(GROUP_ID) REFERENCES TABLE_G(GROUP_ID),
	CONSTRAINT EU_PK PRIMARY KEY(EVENT_ID, GROUP_ID)
);
CREATE TABLE TABLE_ES
(
	EVENT_SCHEDULE_ID NUMBER PRIMARY KEY,
	NAME VARCHAR2(2000) NOT NULL,
	CONTENT VARCHAR2(2000),
	REGION VARCHAR2(2000),
	ADDRESS VARCHAR2(2000).
	LATITUDE VARCHAR2(2000),
	LONGITUDE VARCHAR2(2000),
	START_DATE NUMBER,
	END_DATE NUMBER,
	GROUP_ID NUMBER NOT NULL,
	EVENT_ID NUMBER NOT NULL,
	INPUT_DATE NUMBER DEFAULT (CAST(SYSTIMESTAMP AS DATE) - DATE '1970-01-01')*24*60*60*1000 + MOD( EXTRACT( SECOND FROM SYSTIMESTAMP ), 1 ) * 1000 - 32400000,
	CONSTRAINT ES_GI_FK FOREIGN KEY(GROUP_ID) REFERENCES TABLE_G(GROUP_ID),
	CONSTRAINT ES_EI_FK FOREIGN KEY(EVENT_ID) REFERENCES TABLE_E(EVENT_ID)
);
DROP SEQUENCE ES_SEQ;
CREATE SEQUENCE ES_SEQ;
CREATE TABLE TABLE_ESA
(
    EVENT_SCHEDULE_ID NUMBER NOT NULL,
    USER_ID VARCHAR2(2000),
    INPUT_DATE NUMBER DEFAULT (CAST(SYSTIMESTAMP AS DATE) - DATE '1970-01-01')*24*60*60*1000 + MOD( EXTRACT( SECOND FROM SYSTIMESTAMP ), 1 ) * 1000 - 32400000,
    CONSTRAINT ESA_ESI_FK FOREIGN KEY(EVENT_SCHEDULE_ID) REFERENCES TABLE_ES(EVENT_SCHEDULE_ID),
    CONSTRAINT ESA_UI_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID)
);
CREATE TABLE TABLE_ESCO
(
	EVENT_SCHEDULE_COMMENT_ID NUMBER PRIMARY KEY,
	EVENT_SCHEDULE_ID NUMBER NOT NULL,
	USER_ID VARCHAR2(2000) NOT NULL,
	CONTENT VARCHAR2(2000) NOT NULL,
	INPUT_DATE NUMBER DEFAULT (CAST(SYSTIMESTAMP AS DATE) - DATE '1970-01-01')*24*60*60*1000 + MOD( EXTRACT( SECOND FROM SYSTIMESTAMP ), 1 ) * 1000 - 32400000,
	CONSTRAINT ESCO_ESI FOREIGN KEY(EVENT_SCHEDULE_ID) REFERENCES TABLE_ES(EVENT_SCHEDULE_ID),
	CONSTRAINT ESCO_UI FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID)
);
DROP SEQUENCE ESCO_SEQ;
CREATE SEQUENCE ESCO_SEQ;
CREATE TABLE TABLE_ESCOT
(
	EVENT_SCHEDULE_COMMENT_ID NUMBER NOT NULL,
	TAG VARCHAR2(2000) NOT NULL,
	INPUT_DATE NUMBER DEFAULT (CAST(SYSTIMESTAMP AS DATE) - DATE '1970-01-01')*24*60*60*1000 + MOD( EXTRACT( SECOND FROM SYSTIMESTAMP ), 1 ) * 1000 - 32400000,
	CONSTRAINT ESCOT_ESCOI FOREIGN KEY(EVENT_SCHEDULE_COMMENT_ID) REFERENCES TABLE_ESCO(EVENT_SCHEDULE_COMMENT_ID),
	CONSTRAINT ESCOT_PK PRIMARY KEY(EVENT_SCHEDULE_COMMENT_ID, TAG)
);
CREATE TABLE TABLE_ESV
(
	EVENT_SCHEDULE_VIDEO_ID VARCHAR2(2000) PRIMARY KEY,
	FILENAME VARCHAR2(2000) NOT NULL,
	EXT VARCHAR2(2000) NOT NULL,
	DETECT_DATE NUMBER,
	USER_ID VARCHAR2(2000) NOT NULL,
	EVENT_SCHEDULE_ID NUMBER,
	REGION VARCHAR2(2000),
	LATITUDE VARCHAR2(2000),
	LONGITUDE VARCHAR2(2000),
	INPUT_DATE NUMBER DEFAULT (CAST(SYSTIMESTAMP AS DATE) - DATE '1970-01-01')*24*60*60*1000 + MOD( EXTRACT( SECOND FROM SYSTIMESTAMP ), 1 ) * 1000 - 32400000,
	CONSTRAINT ESV_UI_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID),
	CONSTRAINT ESV_ESI_FK FOREIGN KEY(EVENT_SCHEDULE_ID) REFERENCES TABLE_ES(EVENT_SCHEDULE_ID)
);
CREATE TABLE TABLE_ESVF
(
	EVENT_SCHEDULE_VIDEO_FACE_ID VARCHAR2(2000) PRIMARY KEY,
	EVENT_SCHEDULE_VIDEO_IMAGE_ID VARCHAR2(2000) NOT NULL,
	EVENT_SCHEDULE_VIDEO_ID NOT NULL,
	CONSTRAINT ESVF_ECVI_FK FOREIGN KEY(EVENT_SCHEDULE_VIDEO_ID) REFERENCES TABLE_ESV(EVENT_SCHEDULE_VIDEO_ID)
);
CREATE TABLE TABLE_ESI
(
	EVENT_SCHEDULE_IMAGE_ID VARCHAR2(2000) PRIMARY KEY,
	FILENAME VARCHAR2(2000) NOT NULL,
	EXT VARCHAR2(2000) NOT NULL,
	DETECT_DATE NUMBER,
	USER_ID VARCHAR2(2000) NOT NULL,
	EVENT_SCHEDULE_ID NUMBER,
	REGION VARCHAR2(2000),
	LATITUDE VARCHAR2(2000),
	LONGITUDE VARCHAR2(2000),
	INPUT_DATE NUMBER DEFAULT (CAST(SYSTIMESTAMP AS DATE) - DATE '1970-01-01')*24*60*60*1000 + MOD( EXTRACT( SECOND FROM SYSTIMESTAMP ), 1 ) * 1000 - 32400000,
	CONSTRAINT ESI_UI_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID),
	CONSTRAINT ESI_ESI_FK FOREIGN KEY(EVENT_SCHEDULE_ID) REFERENCES TABLE_ES(EVENT_SCHEDULE_ID)
);
CREATE TABLE TABLE_ESIT
(
	EVENT_SCHEDULE_IMAGE_ID VARCHAR2(2000) NOT NULL,
	TAG VARCHAR2(2000),
    INPUT_DATE NUMBER DEFAULT (CAST(SYSTIMESTAMP AS DATE) - DATE '1970-01-01')*24*60*60*1000 + MOD( EXTRACT( SECOND FROM SYSTIMESTAMP ), 1 ) * 1000 - 32400000,
	CONSTRAINT ESIT_ESII_FK FOREIGN KEY(EVENT_SCHEDULE_IMAGE_ID) REFERENCES TABLE_ESI(EVENT_SCHEDULE_IMAGE_ID),
	CONSTRAINT ESIT_PK PRIMARY KEY(EVENT_SCHEDULE_IMAGE_ID, TAG)
);
CREATE TABLE TABLE_ESID
(
	EVENT_SCHEDULE_IMAGE_ID VARCHAR2(2000) NOT NULL,
	DESCRIPTION VARCHAR2(2000),
	INPUT_DATE NUMBER DEFAULT (CAST(SYSTIMESTAMP AS DATE) - DATE '1970-01-01')*24*60*60*1000 + MOD( EXTRACT( SECOND FROM SYSTIMESTAMP ), 1 ) * 1000 - 32400000,
	CONSTRAINT ESID_ESII_FK FOREIGN KEY(EVENT_SCHEDULE_IMAGE_ID) REFERENCES TABLE_ESI(EVENT_SCHEDULE_IMAGE_ID),
	CONSTRAINT ESID_PK PRIMARY KEY(EVENT_SCHEDULE_IMAGE_ID, DESCRIPTION)
);
CREATE TABLE TABLE_ESIC
(
	EVENT_SCHEDULE_IMAGE_ID VARCHAR2(2000) NOT NULL,
	CATEGORY VARCHAR2(2000),
	INPUT_DATE NUMBER DEFAULT (CAST(SYSTIMESTAMP AS DATE) - DATE '1970-01-01')*24*60*60*1000 + MOD( EXTRACT( SECOND FROM SYSTIMESTAMP ), 1 ) * 1000 - 32400000,
	CONSTRAINT ESIC_ESII_FK FOREIGN KEY(EVENT_SCHEDULE_IMAGE_ID) REFERENCES TABLE_ESI(EVENT_SCHEDULE_IMAGE_ID),
	CONSTRAINT ESIC_PK PRIMARY KEY(EVENT_SCHEDULE_IMAGE_ID, CATEGORY)
);
CREATE TABLE TABLE_ESIF
(
	EVENT_SCHEDULE_IMAGE_FACE_ID VARCHAR2(2000) PRIMARY KEY,
	TOP NUMBER NOT NULL,
	LEFT NUMBER NOT NULL,
	WIDTH NUMBER NOT NULL,
	HEIGHT NUMBER NOT NULL,
	EVENT_SCHEDULE_IMAGE_ID VARCHAR2(2000) NOT NULL,
	CONSTRAINT ESIF_ECII_FK FOREIGN KEY(EVENT_SCHEDULE_IMAGE_ID) REFERENCES TABLE_ESI(EVENT_SCHEDULE_IMAGE_ID)
);
CREATE TABLE TABLE_ESUS
(
	USER_SCHEDULE_ID NUMBER PRIMARY KEY,
	USER_ID VARCHAR2(2000) NOT NULL,
	EVENT_SCHEDULE_ID NUMBER NOT NULL,
	START_DATE NUMBER NOT NULL,
	END_DATE NUMBER NOT NULL,
	TYPEOF NUMBER DEFAULT 1,
	CONSTRAINT ESUS_UI_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID),
	CONSTRAINT ESUS_ESI_FK FOREIGN KEY(EVENT_SCHEDULE_ID) REFERENCES TABLE_ES(EVENT_SCHEDULE_ID)
);
DROP SEQUENCE ESUS_SEQ;
CREATE SEQUENCE ESUS_SEQ;
CREATE TABLE TABLE_IA
(
	EVENT_SCHEDULE_IMAGE_ID VARCHAR2(2000) NOT NULL,
	USER_ID VARCHAR2(2000) NOT NULL,
	BLIND NUMBER DEFAULT 1,
	SELF NUMBER DEFAULT 0,
	CONSTRAINT IA_ESII_FK FOREIGN KEY(EVENT_SCHEDULE_IMAGE_ID) REFERENCES TABLE_ESI(EVENT_SCHEDULE_IMAGE_ID),
	CONSTRAINT IA_UI_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID),
    CONSTRAINT IA_PK PRIMARY KEY(EVENT_SCHEDULE_IMAGE_ID, USER_ID)
);
CREATE TABLE TABLE_VA
(
	EVENT_SCHEDULE_VIDEO_ID VARCHAR2(2000) NOT NULL,
	USER_ID VARCHAR2(2000) NOT NULL,
	BLIND NUMBER DEFAULT 1,
	SELF NUMBER DEFAULT 0,
	CONSTRAINT VA_ESVI_FK FOREIGN KEY(EVENT_SCHEDULE_VIDEO_ID) REFERENCES TABLE_ESV(EVENT_SCHEDULE_VIDEO_ID),
	CONSTRAINT VA_UI_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID),
    CONSTRAINT VA_PK PRIMARY KEY(EVENT_SCHEDULE_VIDEO_ID, USER_ID)
);
CREATE TABLE TABLE_UH
(
	HASHTAG VARCHAR2(2000) NOT NULL,
	USER_ID VARCHAR2(2000) NOT NULL,
	INPUT_DATE NUMBER DEFAULT (CAST(SYSTIMESTAMP AS DATE) - DATE '1970-01-01')*24*60*60*1000 + MOD( EXTRACT( SECOND FROM SYSTIMESTAMP ), 1 ) * 1000 - 32400000,
	CONSTRAINT UH_UI_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID),
	CONSTRAINT UH_PK PRIMARY KEY(HASHTAG, USER_ID)
);
CREATE TABLE TABLE_GH
(
	HASHTAG VARCHAR2(2000) NOT NULL,
	GROUP_ID NUMBER NOT NULL,
	INPUT_DATE NUMBER DEFAULT (CAST(SYSTIMESTAMP AS DATE) - DATE '1970-01-01')*24*60*60*1000 + MOD( EXTRACT( SECOND FROM SYSTIMESTAMP ), 1 ) * 1000 - 32400000,
	CONSTRAINT GH_GI_FK FOREIGN KEY(GROUP_ID) REFERENCES TABLE_G(GROUP_ID),
	CONSTRAINT GH_PK PRIMARY KEY(HASHTAG, GROUP_ID)
);
CREATE TABLE TABLE_CT
(
	USER_ID VARCHAR2(2000) NOT NULL,
	TAG VARCHAR2(2000) NOT NULL,
	CONSTRAINT CT_UI_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID),
	CONSTRAINT CT_PK PRIMARY KEY(USER_ID, TAG)
);
CREATE TABLE TABLE_VAP
(
	FACE_ID VARCHAR2(2000) NOT NULL,
	START_TIME NUMBER NOT NULL,
	END_TIME NUMBER NOT NULL,
	CONSTRAINT VAP_II_FK FOREIGN KEY(FACE_ID) REFERENCES TABLE_ESVF(EVENT_SCHEDULE_VIDEO_FACE_ID)
);

INSERT INTO TABLE_U(USER_ID, PASSWORD, NAME, IMAGE_ID, REGION, CONTENT) VALUES('id1@gmail.com', 'password', 'Joy', 'id1@gmail.com.jpg', '광주', 'id1content');
INSERT INTO TABLE_U(USER_ID, PASSWORD, NAME, IMAGE_ID, REGION, CONTENT) VALUES('id2@gmail.com', 'password', 'Arwen', 'id2@gmail.com.jpg', '광주', 'id2content');
INSERT INTO TABLE_U(USER_ID, PASSWORD, NAME, IMAGE_ID, REGION, CONTENT) VALUES('id3@gmail.com', 'password', 'Aragorn', 'id3@gmail.com.jpg', '광주', 'id3content');
INSERT INTO TABLE_U(USER_ID, PASSWORD, NAME, IMAGE_ID, REGION, CONTENT) VALUES('id4@gmail.com', 'password', 'Eowyn', 'id4@gmail.com.jpg', '광주', 'id4content');
INSERT INTO TABLE_U(USER_ID, PASSWORD, NAME, IMAGE_ID, REGION, CONTENT) VALUES('id5@gmail.com', 'password', 'Saruman', 'id5@gmail.com.jpg', '광주', 'id5content');
INSERT INTO TABLE_U(USER_ID, PASSWORD, NAME, IMAGE_ID, REGION, CONTENT) VALUES('id6@gmail.com', 'password', 'Sauron', 'id6@gmail.com.jpg', '광주', 'id6content');
INSERT INTO TABLE_U(USER_ID, PASSWORD, NAME, IMAGE_ID, REGION, CONTENT) VALUES('id7@gmail.com', 'password', 'Nancy', 'id7@gmail.com.jpg', '광주', 'id7content');
INSERT INTO TABLE_U(USER_ID, PASSWORD, NAME, IMAGE_ID, REGION, CONTENT) VALUES('id8@gmail.com', 'password', 'Emma', 'id8@gmail.com.jpg', '광주', 'id8content');
INSERT INTO TABLE_U(USER_ID, PASSWORD, NAME, IMAGE_ID, REGION, CONTENT) VALUES('id9@gmail.com', 'password', 'Emma', 'id9@gmail.com.jpg', '광주', 'id9content');

INSERT INTO TABLE_GCA(GROUP_CATEGORY_ID, FILENAME, NAME) VALUES(1, '1.jpg', 'IT');
INSERT INTO TABLE_GCA(GROUP_CATEGORY_ID, FILENAME, NAME) VALUES(2, '2.jpg', 'Family');
INSERT INTO TABLE_GCA(GROUP_CATEGORY_ID, FILENAME, NAME) VALUES(3, '3.jpg', 'Pet');
INSERT INTO TABLE_GCA(GROUP_CATEGORY_ID, FILENAME, NAME) VALUES(4, '4.jpg', 'Cooking');
INSERT INTO TABLE_GCA(GROUP_CATEGORY_ID, FILENAME, NAME) VALUES(5, '5.jpg', 'Leisure');
INSERT INTO TABLE_GCA(GROUP_CATEGORY_ID, FILENAME, NAME) VALUES(6, '6.jpg', 'Music');

INSERT INTO TABLE_G(GROUP_ID, GROUP_CATEGORY_ID, REGION, USER_ID, NAME, CONTENT, GROUP_LOGO, GROUP_IMAGE) VALUES(1, 1, '광주', 'id1@gmail.com', 'Java Developer Group', 'Javaを使ってプログラミングをするグループです。', '1.jpg', '1.jpg');
INSERT INTO TABLE_G(GROUP_ID, GROUP_CATEGORY_ID, REGION, USER_ID, NAME, CONTENT, GROUP_LOGO, GROUP_IMAGE) VALUES(2, 1, '광주', 'id4@gmail.com', 'Front-end Developer Group', 'group2content', '2.jpg', '2.jpg');
INSERT INTO TABLE_G(GROUP_ID, GROUP_CATEGORY_ID, REGION, USER_ID, NAME, CONTENT, GROUP_LOGO, GROUP_IMAGE) VALUES(3, 1, '광주', 'id7@gmail.com', 'Back-end Developer Group', 'group3content', '3.jpg', '3.jpg');
INSERT INTO TABLE_G(GROUP_ID, GROUP_CATEGORY_ID, REGION, USER_ID, NAME, CONTENT, GROUP_LOGO, GROUP_IMAGE) VALUES(4, 1, '광주', 'id1@gmail.com', 'C Developer Group', 'group4content', '4.jpg', '4.jpg');
INSERT INTO TABLE_G(GROUP_ID, GROUP_CATEGORY_ID, REGION, USER_ID, NAME, CONTENT, GROUP_LOGO, GROUP_IMAGE) VALUES(5, 1, '광주', 'id4@gmail.com', 'Database Developer Group', 'group5content', '5.jpg', '5.jpg');
INSERT INTO TABLE_G(GROUP_ID, GROUP_CATEGORY_ID, REGION, USER_ID, NAME, CONTENT, GROUP_LOGO, GROUP_IMAGE) VALUES(6, 1, '광주', 'id7@gmail.com', 'Web Developer Group', 'group6content', '6.jpg', '6.jpg');

INSERT INTO TABLE_GA(USER_ID, GROUP_ID, BLIND) VALUES('id1@gmail.com', 1, 0);
INSERT INTO TABLE_GA(USER_ID, GROUP_ID) VALUES('id2@gmail.com', 1);
INSERT INTO TABLE_GA(USER_ID, GROUP_ID) VALUES('id3@gmail.com', 1);
INSERT INTO TABLE_GA(USER_ID, GROUP_ID) VALUES('id4@gmail.com', 2);
INSERT INTO TABLE_GA(USER_ID, GROUP_ID) VALUES('id5@gmail.com', 2);
INSERT INTO TABLE_GA(USER_ID, GROUP_ID) VALUES('id6@gmail.com', 2);
INSERT INTO TABLE_GA(USER_ID, GROUP_ID) VALUES('id7@gmail.com', 3);
INSERT INTO TABLE_GA(USER_ID, GROUP_ID) VALUES('id8@gmail.com', 3);
INSERT INTO TABLE_GA(USER_ID, GROUP_ID) VALUES('id9@gmail.com', 3);
INSERT INTO TABLE_GA(USER_ID, GROUP_ID) VALUES('id1@gmail.com', 4);
INSERT INTO TABLE_GA(USER_ID, GROUP_ID) VALUES('id4@gmail.com', 5);
INSERT INTO TABLE_GA(USER_ID, GROUP_ID) VALUES('id7@gmail.com', 6);

INSERT INTO TABLE_GCO(GROUP_COMMENT_ID, GROUP_ID, USER_ID, CONTENT) VALUES(1, 1, 'id1@gmail.com', 'C, Java, Javascript, CSS, HTML5, SQLを勉強したいです。');
INSERT INTO TABLE_GCO(GROUP_COMMENT_ID, GROUP_ID, USER_ID, CONTENT) VALUES(2, 1, 'id2@gmail.com', 'Javascriptができる方いますか？');
INSERT INTO TABLE_GCO(GROUP_COMMENT_ID, GROUP_ID, USER_ID, CONTENT) VALUES(3, 1, 'id3@gmail.com', 'SQLは簡単ですよ。');

INSERT INTO TABLE_E(EVENT_ID, NAME, CONTENT, GROUP_ID, USER_ID, IMAGE_ID) VALUES(1, 'Database Modeling', 'Oracleを使うDatabase Modeling', 1, 'id1@gmail.com', '1.jpg');
INSERT INTO TABLE_E(EVENT_ID, NAME, CONTENT, GROUP_ID, USER_ID, IMAGE_ID) VALUES(2, 'Web Programming', 'HTML5, Javascriptを使うWeb Programming', 1, 'id2@gmail.com', '2.jpg');
INSERT INTO TABLE_E(EVENT_ID, NAME, CONTENT, GROUP_ID, USER_ID, IMAGE_ID) VALUES(3, 'AI Programming', 'Machine leanringを使うAI Programming', 1, 'id3@gmail.com', '3.jpg');
INSERT INTO TABLE_E(EVENT_ID, NAME, CONTENT, GROUP_ID, USER_ID, IMAGE_ID) VALUES(4, 'group2event1', 'group2event1content', 2, 'id4@gmail.com', '1.jpg');
INSERT INTO TABLE_E(EVENT_ID, NAME, CONTENT, GROUP_ID, USER_ID, IMAGE_ID) VALUES(5, 'group2event2', 'group2event2content', 2, 'id5@gmail.com', '2.jpg');
INSERT INTO TABLE_E(EVENT_ID, NAME, CONTENT, GROUP_ID, USER_ID, IMAGE_ID) VALUES(6, 'group2event3', 'group2event3content', 2, 'id6@gmail.com', '3.jpg');
INSERT INTO TABLE_E(EVENT_ID, NAME, CONTENT, GROUP_ID, USER_ID, IMAGE_ID) VALUES(7, 'group3event1', 'group3event1content', 3, 'id7@gmail.com', '1.jpg');
INSERT INTO TABLE_E(EVENT_ID, NAME, CONTENT, GROUP_ID, USER_ID, IMAGE_ID) VALUES(8, 'group3event2', 'group3event2content', 3, 'id8@gmail.com', '2.jpg');
INSERT INTO TABLE_E(EVENT_ID, NAME, CONTENT, GROUP_ID, USER_ID, IMAGE_ID) VALUES(9, 'group3event3', 'group3event3content', 3, 'id9@gmail.com', '3.jpg');

INSERT INTO TABLE_EA(EVENT_ID, USER_ID) VALUES(1, 'id1@gmail.com');
INSERT INTO TABLE_EA(EVENT_ID, USER_ID) VALUES(1, 'id2@gmail.com');
INSERT INTO TABLE_EA(EVENT_ID, USER_ID) VALUES(2, 'id2@gmail.com');
INSERT INTO TABLE_EA(EVENT_ID, USER_ID) VALUES(2, 'id3@gmail.com');
INSERT INTO TABLE_EA(EVENT_ID, USER_ID) VALUES(3, 'id3@gmail.com');
INSERT INTO TABLE_EA(EVENT_ID, USER_ID) VALUES(3, 'id1@gmail.com');
INSERT INTO TABLE_EA(EVENT_ID, USER_ID) VALUES(4, 'id4@gmail.com');
INSERT INTO TABLE_EA(EVENT_ID, USER_ID) VALUES(4, 'id5@gmail.com');
INSERT INTO TABLE_EA(EVENT_ID, USER_ID) VALUES(5, 'id5@gmail.com');
INSERT INTO TABLE_EA(EVENT_ID, USER_ID) VALUES(5, 'id6@gmail.com');
INSERT INTO TABLE_EA(EVENT_ID, USER_ID) VALUES(6, 'id6@gmail.com');
INSERT INTO TABLE_EA(EVENT_ID, USER_ID) VALUES(6, 'id4@gmail.com');
INSERT INTO TABLE_EA(EVENT_ID, USER_ID) VALUES(7, 'id7@gmail.com');
INSERT INTO TABLE_EA(EVENT_ID, USER_ID) VALUES(7, 'id8@gmail.com');
INSERT INTO TABLE_EA(EVENT_ID, USER_ID) VALUES(8, 'id8@gmail.com');
INSERT INTO TABLE_EA(EVENT_ID, USER_ID) VALUES(8, 'id9@gmail.com');
INSERT INTO TABLE_EA(EVENT_ID, USER_ID) VALUES(9, 'id9@gmail.com');
INSERT INTO TABLE_EA(EVENT_ID, USER_ID) VALUES(9, 'id7@gmail.com');

INSERT INTO TABLE_ECO(EVENT_COMMENT_ID, EVENT_ID, USER_ID, CONTENT) VALUES(1, 1, 'id1@gmail.com', 'Databaseのアップデートができませんが。');
INSERT INTO TABLE_ECO(EVENT_COMMENT_ID, EVENT_ID, USER_ID, CONTENT) VALUES(2, 1, 'id2@gmail.com', 'Serverは作動していますか？');

INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID, REGION, LATITUDE, LONGITUDE) VALUES(1, '1番目のMeeting', 'event1_event_schedule1content', 1, 1, '東京　京橋駅', '35.6693907' ,'139.76803390000004');
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID, REGION, LATITUDE, LONGITUDE) VALUES(2, '2番目のMeeting', 'event1_event_schedule2content', 1, 1,'名古屋　愛知県', '35.6691329' , '139.7693181');
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID, REGION, LATITUDE, LONGITUDE) VALUES(3, 'event2_event_schedule1', 'event2_event_schedule1content', 1, 2,'大阪市 大正区', '35.6691329' , '139.7693181');
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID, REGION, LATITUDE, LONGITUDE) VALUES(4, 'event2_event_schedule2', 'event2_event_schedule2content', 1, 2,'名古屋　愛知県', '35.6721329' , '139.7623181');
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID, REGION, LATITUDE, LONGITUDE) VALUES(5, 'event3_event_schedule1', 'event3_event_schedule1content', 1, 3,'東京', '35.6681329' , '139.7603181');
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID, REGION, LATITUDE, LONGITUDE) VALUES(6, 'event3_event_schedule2', 'event3_event_schedule2content', 1, 3,'東京', '35.6701329' , '139.7653181');
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID) VALUES(7, 'event4_event_schedule1', 'event4_event_schedule1content', 2, 4);
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID) VALUES(8, 'event4_event_schedule2', 'event4_event_schedule2content', 2, 4);
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID) VALUES(9, 'event5_event_schedule1', 'event5_event_schedule1content', 2, 5);
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID) VALUES(10, 'event5_event_schedule2', 'event5_event_schedule2content', 2, 5);
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID) VALUES(11, 'event6_event_schedule1', 'event6_event_schedule1content', 2, 6);
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID) VALUES(12, 'event6_event_schedule2', 'event6_event_schedule2content', 3, 6);
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID) VALUES(13, 'event7_event_schedule1', 'event7_event_schedule1content', 3, 7);
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID) VALUES(14, 'event7_event_schedule2', 'event7_event_schedule2content', 3, 7);
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID) VALUES(15, 'event8_event_schedule1', 'event8_event_schedule1content', 3, 8);
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID) VALUES(16, 'event8_event_schedule2', 'event8_event_schedule2content', 3, 8);
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID) VALUES(17, 'event9_event_schedule1', 'event9_event_schedule1content', 3, 9);
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID) VALUES(18, 'event9_event_schedule2', 'event9_event_schedule2content', 3, 9);
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID, REGION, LATITUDE, LONGITUDE) VALUES(19, '3番目のMeeting', 'event1_event_schedule3content', 1, 1,'大阪市 大正区', '35.6685256','139.7679124');
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID, REGION, LATITUDE, LONGITUDE) VALUES(20, '4番目のMeeting', 'event1_event_schedule4content', 1, 1,'仙台市　宮城県', '35.67016907' ,'76203390000004');
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID, REGION, LATITUDE, LONGITUDE) VALUES(21, '5番目のMeeting', 'event1_event_schedule5content', 1, 1,'名古屋　愛知県', '35.67002907', '139.7685339000003');
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID, REGION, LATITUDE, LONGITUDE) VALUES(22, '6番目のMeeting', 'event1_event_schedule6content', 1, 1,'東京都中央区京橋','35.67106907' ,'139.762133900004');
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID, REGION, LATITUDE, LONGITUDE) VALUES(23, '7番目のMeeting', 'event1_event_schedule7content', 1, 1,' 東京都千代田区日比谷公園','35.6759907','139.7707339000004');
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID, REGION, LATITUDE, LONGITUDE) VALUES(24, '8番目のMeeting', 'event1_event_schedule8content', 1, 1,'東京都中央区八重洲','35.6766907','77013380004');
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID, REGION, LATITUDE, LONGITUDE) VALUES(25, '9番目のMeeting', 'event1_event_schedule9content', 1, 1,'東京','35.67556907','139.7699033257');
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID, REGION, LATITUDE, LONGITUDE) VALUES(26, '10番目のMeeting', 'event1_event_schedule10content', 1, 1,'東京','35.67606907','139.77113941000004');
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID, REGION, LATITUDE, LONGITUDE) VALUES(27, '11番目のMeeting', 'event1_event_schedule11content', 1, 1,'東京','35.67506907','139.77044100004');
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID, REGION, LATITUDE, LONGITUDE) VALUES(28, '12番目のMeeting', 'event1_event_schedule12content', 1, 1,'東京','35.67526907','139.76835000004');
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID, REGION, LATITUDE, LONGITUDE) VALUES(29, '13番目のMeeting', 'event1_event_schedule13content', 1, 1,'東京','35.6681907','139.7601033333004');
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID, REGION, LATITUDE, LONGITUDE) VALUES(30, '14番目のMeeting', 'event1_event_schedule14content', 1, 1,'東京','35.66726907','139.7598539004');
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID, REGION, LATITUDE, LONGITUDE) VALUES(31, '15番目のMeeting', 'event1_event_schedule15content', 1, 1,'東京','35.66956907','139.76103390000004');
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, NAME, CONTENT, GROUP_ID, EVENT_ID, REGION, LATITUDE, LONGITUDE) VALUES(32, '16番目のMeeting', 'event1_event_schedule16content', 1, 1,'東京','35.66676907','139.757390000004');


INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(1, 'id1@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(2, 'id1@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(2, 'id2@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(3, 'id2@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(4, 'id2@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(4, 'id3@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(5, 'id3@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(6, 'id3@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(6, 'id1@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(7, 'id4@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(8, 'id4@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(8, 'id5@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(9, 'id5@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(10, 'id5@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(10, 'id6@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(11, 'id6@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(12, 'id6@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(12, 'id4@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(13, 'id7@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(14, 'id7@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(14, 'id8@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(15, 'id8@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(16, 'id8@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(16, 'id9@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(17, 'id9@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(18, 'id9@gmail.com');
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(18, 'id7@gmail.com');

INSERT INTO TABLE_ESCO(EVENT_SCHEDULE_COMMENT_ID, EVENT_SCHEDULE_ID, USER_ID, CONTENT) VALUES(1, 1, 'id1@gmail.com', 'Javaプログラミング楽しかったです!');
INSERT INTO TABLE_ESCO(EVENT_SCHEDULE_COMMENT_ID, EVENT_SCHEDULE_ID, USER_ID, CONTENT) VALUES(2, 2, 'id1@gmail.com', 'いつがいいですか?');
INSERT INTO TABLE_ESCO(EVENT_SCHEDULE_COMMENT_ID, EVENT_SCHEDULE_ID, USER_ID, CONTENT) VALUES(3, 2, 'id2@gmail.com', '土曜日はどうですか?');

INSERT INTO TABLE_ESV(EVENT_SCHEDULE_VIDEO_ID, FILENAME, EXT, USER_ID, EVENT_SCHEDULE_ID) VALUES('e37ca75893', 'e37ca75893.mp4', 'mp4', 'id1@gmail.com', 1);

INSERT INTO TABLE_ESI(EVENT_SCHEDULE_IMAGE_ID, FILENAME, EXT, USER_ID, EVENT_SCHEDULE_ID) VALUES('event_schedule1_image1.jpg', 'event_schedule1_image1.jpg', 'jpg', 'id1@gmail.com', 1);
INSERT INTO TABLE_ESI(EVENT_SCHEDULE_IMAGE_ID, FILENAME, EXT, USER_ID, EVENT_SCHEDULE_ID) VALUES('event_schedule2_image1.jpg', 'event_schedule2_image1.jpg', 'jpg', 'id1@gmail.com', 2);
INSERT INTO TABLE_ESI(EVENT_SCHEDULE_IMAGE_ID, FILENAME, EXT, USER_ID, EVENT_SCHEDULE_ID) VALUES('event_schedule2_image2.jpg', 'event_schedule2_image2.jpg', 'jpg', 'id2@gmail.com', 2);
INSERT INTO TABLE_ESI(EVENT_SCHEDULE_IMAGE_ID, FILENAME, EXT, USER_ID, EVENT_SCHEDULE_ID) VALUES('event_schedule3_image1.jpg', 'event_schedule3_image1.jpg', 'jpg', 'id3@gmail.com', 3);
INSERT INTO TABLE_ESI(EVENT_SCHEDULE_IMAGE_ID, FILENAME, EXT, USER_ID, EVENT_SCHEDULE_ID) VALUES('event_schedule4_image1.jpg', 'event_schedule4_image1.jpg', 'jpg', 'id3@gmail.com', 4);
INSERT INTO TABLE_ESI(EVENT_SCHEDULE_IMAGE_ID, FILENAME, EXT, USER_ID, EVENT_SCHEDULE_ID) VALUES('event_schedule4_image2.jpg', 'event_schedule4_image2.jpg', 'jpg', 'id4@gmail.com', 4);
INSERT INTO TABLE_ESI(EVENT_SCHEDULE_IMAGE_ID, FILENAME, EXT, USER_ID, EVENT_SCHEDULE_ID) VALUES('event_schedule5_image1.jpg', 'event_schedule5_image1.jpg', 'jpg', 'id4@gmail.com', 5);
INSERT INTO TABLE_ESI(EVENT_SCHEDULE_IMAGE_ID, FILENAME, EXT, USER_ID, EVENT_SCHEDULE_ID) VALUES('event_schedule6_image1.jpg', 'event_schedule6_image1.jpg', 'jpg', 'id4@gmail.com', 6);
INSERT INTO TABLE_ESI(EVENT_SCHEDULE_IMAGE_ID, FILENAME, EXT, USER_ID, EVENT_SCHEDULE_ID) VALUES('event_schedule6_image2.jpg', 'event_schedule6_image2.jpg', 'jpg', 'id1@gmail.com', 6);

INSERT INTO TABLE_ESUS(USER_SCHEDULE_ID, USER_ID, EVENT_SCHEDULE_ID, START_DATE, END_DATE) VALUES(1, 'id1@gmail.com', 1, 1554681600000, 1554692400000);
INSERT INTO TABLE_ESUS(USER_SCHEDULE_ID, USER_ID, EVENT_SCHEDULE_ID, START_DATE, END_DATE) VALUES(2, 'id1@gmail.com', 1, 1554696000000, 1554724800000);
INSERT INTO TABLE_ESUS(USER_SCHEDULE_ID, USER_ID, EVENT_SCHEDULE_ID, START_DATE, END_DATE) VALUES(3, 'id1@gmail.com', 1, 1554681600000, 1554685200000);
INSERT INTO TABLE_ESUS(USER_SCHEDULE_ID, USER_ID, EVENT_SCHEDULE_ID, START_DATE, END_DATE) VALUES(4, 'id1@gmail.com', 1, 1554724800000, 1554728400000);

INSERT INTO TABLE_UH(HASHTAG, USER_ID) VALUES('id1hashtag1', 'id1@gmail.com');
INSERT INTO TABLE_UH(HASHTAG, USER_ID) VALUES('id1hashtag2', 'id1@gmail.com');
INSERT INTO TABLE_UH(HASHTAG, USER_ID) VALUES('id2hashtag1', 'id2@gmail.com');
INSERT INTO TABLE_UH(HASHTAG, USER_ID) VALUES('id2hashtag2', 'id2@gmail.com');

INSERT INTO TABLE_GH(HASHTAG, GROUP_ID) VALUES('Java', 1);
INSERT INTO TABLE_GH(HASHTAG, GROUP_ID) VALUES('Javascript', 1);
INSERT INTO TABLE_GH(HASHTAG, GROUP_ID) VALUES('Javascript', 2);
INSERT INTO TABLE_GH(HASHTAG, GROUP_ID) VALUES('Java', 2);
INSERT INTO TABLE_GH(HASHTAG, GROUP_ID) VALUES('HTML5', 2);
INSERT INTO TABLE_GH(HASHTAG, GROUP_ID) VALUES('CSS', 2);
INSERT INTO TABLE_GH(HASHTAG, GROUP_ID) VALUES('C', 3);
INSERT INTO TABLE_GH(HASHTAG, GROUP_ID) VALUES('Java', 3);
INSERT INTO TABLE_GH(HASHTAG, GROUP_ID) VALUES('SQL', 3);
INSERT INTO TABLE_GH(HASHTAG, GROUP_ID) VALUES('C', 4);
INSERT INTO TABLE_GH(HASHTAG, GROUP_ID) VALUES('SQL', 5);
INSERT INTO TABLE_GH(HASHTAG, GROUP_ID) VALUES('Oracle', 5);
INSERT INTO TABLE_GH(HASHTAG, GROUP_ID) VALUES('MySQL', 5);
INSERT INTO TABLE_GH(HASHTAG, GROUP_ID) VALUES('HTML5', 6);
INSERT INTO TABLE_GH(HASHTAG, GROUP_ID) VALUES('Javascript', 6);
INSERT INTO TABLE_GH(HASHTAG, GROUP_ID) VALUES('CSS', 6);
COMMIT;