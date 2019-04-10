DROP TABLE TABLE_UH;
DROP TABLE TABLE_GH;
DROP TABLE TABLE_VA;
DROP TABLE TABLE_IA;
DROP TABLE TABLE_ESUS;
DROP TABLE TABLE_ESVF;
DROP TABLE TABLE_ESV;
DROP TABLE TABLE_ESIF;
DROP TABLE TABLE_ESI;
DROP TABLE TABLE_ESA;
DROP TABLE TABLE_ES;
DROP TABLE TABLE_EA;
DROP TABLE TABLE_ECO;
DROP TABLE TABLE_E;
DROP TABLE TABLE_GA;
DROP TABLE TABLE_GCO;
DROP TABLE TABLE_G;
DROP TABLE TABLE_GCA;
DROP TABLE TABLE_U;

CREATE TABLE TABLE_U
(
    USER_ID VARCHAR2(4000) PRIMARY KEY,
    PASSWORD VARCHAR2(4000) NOT NULL,
    NAME VARCHAR2(4000) NOT NULL,
    IMAGE_ID VARCHAR2(4000),
    REGION VARCHAR2(4000) NOT NULL,
    CONTENT VARCHAR2(4000),
    INPUT_DATE NUMBER DEFAULT ((SYSDATE - TO_DATE('1970-01-01', 'YYYY-MM-DD')) * 24 * 60 * 60 * 1000) - 32400000
);
CREATE TABLE TABLE_GCA
(
    GROUP_CATEGORY_ID NUMBER PRIMARY KEY,
    NAME VARCHAR2(4000) NOT NULL UNIQUE
);
DROP SEQUENCE GCA_SEQ;
CREATE SEQUENCE GCA_SEQ;
CREATE TABLE TABLE_G
(
    GROUP_ID NUMBER PRIMARY KEY,
    GROUP_CATEGORY_ID NUMBER NOT NULL,
    REGION VARCHAR2(4000) NOT NULL,
    USER_ID VARCHAR2(4000) NOT NULL,
    NAME VARCHAR2(4000) NOT NULL,
    CONTENT VARCHAR2(4000) NOT NULL,
    GROUP_LOGO VARCHAR2(4000) UNIQUE,
    GROUP_IMAGE VARCHAR2(4000) UNIQUE,
    INPUT_DATE NUMBER DEFAULT ((SYSDATE - TO_DATE('1970-01-01', 'YYYY-MM-DD')) * 24 * 60 * 60 * 1000) - 32400000,
    CONSTRAINT G_UI_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID),
    CONSTRAINT G_GCI_FK FOREIGN KEY(GROUP_CATEGORY_ID) REFERENCES TABLE_GCA(GROUP_CATEGORY_ID)
);
DROP SEQUENCE G_SEQ;
CREATE SEQUENCE G_SEQ;
CREATE TABLE TABLE_GCO
(
	GROUP_COMMENT_ID NUMBER PRIMARY KEY,
	GROUP_ID NUMBER NOT NULL,
	USER_ID VARCHAR2(4000) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	INPUT_DATE NUMBER DEFAULT ((SYSDATE - TO_DATE('1970-01-01', 'YYYY-MM-DD')) * 24 * 60 * 60 * 1000) - 32400000,
	CONSTRAINT GCO_GI FOREIGN KEY(GROUP_ID) REFERENCES TABLE_G(GROUP_ID),
	CONSTRAINT GCO_UI FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID)
);
DROP SEQUENCE GCO_SEQ;
CREATE SEQUENCE GCO_SEQ;
CREATE TABLE TABLE_GA
(
    USER_ID VARCHAR2(4000) NOT NULL,
    GROUP_ID NUMBER NOT NULL,
    BLIND NUMBER DEFAULT 1,
    INPUT_DATE NUMBER DEFAULT ((SYSDATE - TO_DATE('1970-01-01', 'YYYY-MM-DD')) * 24 * 60 * 60 * 1000) - 32400000,
    CONSTRAINT GA_UI_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID),
    CONSTRAINT GA_GI_FK FOREIGN KEY(GROUP_ID) REFERENCES TABLE_G(GROUP_ID),
    CONSTRAINT GA_PK PRIMARY KEY(USER_ID, GROUP_ID)
);
CREATE TABLE TABLE_E
(
	EVENT_ID NUMBER PRIMARY KEY,
	NAME VARCHAR2(4000) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	GROUP_ID NUMBER NOT NULL,
	USER_ID VARCHAR2(4000) NOT NULL,
	IMAGE_ID VARCHAR2(4000),
	INPUT_DATE NUMBER DEFAULT ((SYSDATE - TO_DATE('1970-01-01', 'YYYY-MM-DD')) * 24 * 60 * 60 * 1000) - 32400000,
	CONSTRAINT E_GI_FK FOREIGN KEY(GROUP_ID) REFERENCES TABLE_G(GROUP_ID),
	CONSTRAINT E_UI_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID)
);
DROP SEQUENCE E_SEQ;
CREATE SEQUENCE E_SEQ;
CREATE TABLE TABLE_ECO
(
	EVENT_COMMENT_ID NUMBER PRIMARY KEY,
	EVENT_ID NUMBER NOT NULL,
	USER_ID VARCHAR2(4000) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	INPUT_DATE NUMBER DEFAULT ((SYSDATE - TO_DATE('1970-01-01', 'YYYY-MM-DD')) * 24 * 60 * 60 * 1000) - 32400000,
	CONSTRAINT ECO_EI FOREIGN KEY(EVENT_ID) REFERENCES TABLE_E(EVENT_ID),
	CONSTRAINT ECO_UI FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID)
);
DROP SEQUENCE GCO_SEQ;
CREATE SEQUENCE GCO_SEQ;
CREATE TABLE TABLE_EA
(
	EVENT_ID NUMBER NOT NULL,
	USER_ID VARCHAR2(4000) NOT NULL,
	INPUT_DATE NUMBER DEFAULT ((SYSDATE - TO_DATE('1970-01-01', 'YYYY-MM-DD')) * 24 * 60 * 60 * 1000) - 32400000,
	CONSTRAINT EA_EI_FK FOREIGN KEY(EVENT_ID) REFERENCES TABLE_E(EVENT_ID),
	CONSTRAINT EA_UI_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID),
    CONSTRAINT EA_PK PRIMARY KEY(EVENT_ID, USER_ID)
);
CREATE TABLE TABLE_ES
(
	EVENT_SCHEDULE_ID NUMBER PRIMARY KEY,
	TITLE VARCHAR2(4000) NOT NULL,
	PLACE VARCHAR2(4000),
	START_DATE NUMBER NOT NULL,
	END_DATE NUMBER NOT NULL,
	EVENT_ID NUMBER NOT NULL,
	INPUT_DATE NUMBER DEFAULT ((SYSDATE - TO_DATE('1970-01-01', 'YYYY-MM-DD')) * 24 * 60 * 60 * 1000) - 32400000,
	CONSTRAINT ES_EI_FK FOREIGN KEY(EVENT_ID) REFERENCES TABLE_E(EVENT_ID)
);
DROP SEQUENCE ES_SEQ;
CREATE SEQUENCE ES_SEQ;
CREATE TABLE TABLE_ESCO
(
	EVENT_SCHEDULE_COMMENT_ID NUMBER PRIMARY KEY,
	EVENT_SCHEDULE_ID NUMBER NOT NULL,
	USER_ID VARCHAR2(4000) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	INPUT_DATE NUMBER DEFAULT ((SYSDATE - TO_DATE('1970-01-01', 'YYYY-MM-DD')) * 24 * 60 * 60 * 1000) - 32400000,
	CONSTRAINT ESCO_ESI FOREIGN KEY(EVENT_SCHEDULE_ID) REFERENCES TABLE_ES(EVENT_SCHEDULE_ID),
	CONSTRAINT ESCO_UI FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID)
);
DROP SEQUENCE ESCO_SEQ;
CREATE SEQUENCEESCO_SEQ;
CREATE TABLE TABLE_ESA
(
    EVENT_SCHEDULE_ID NUMBER NOT NULL,
    USER_ID VARCHAR2(4000),
    INPUT_DATE NUMBER DEFAULT ((SYSDATE - TO_DATE('1970-01-01', 'YYYY-MM-DD')) * 24 * 60 * 60 * 1000) - 32400000,
    CONSTRAINT ESA_ESI_FK FOREIGN KEY(EVENT_SCHEDULE_ID) REFERENCES TABLE_ES(EVENT_SCHEDULE_ID),
    CONSTRAINT ESA_UI_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID)
);
CREATE TABLE TABLE_ESV
(
	EVENT_SCHEDULE_VIDEO_ID VARCHAR2(4000) PRIMARY KEY,
	FILENAME VARCHAR2(4000) NOT NULL,
	EXT VARCHAR2(4000) NOT NULL,
	DETECT_DATE NUMBER,
	USER_ID VARCHAR2(4000) NOT NULL,
	EVENT_SCHEDULE_ID NUMBER,
	REGION VARCHAR2(4000),
	LATITUDE VARCHAR2(4000),
	LONGITUDE VARCHAR2(4000),
	INPUT_DATE NUMBER DEFAULT ((SYSDATE - TO_DATE('1970-01-01', 'YYYY-MM-DD')) * 24 * 60 * 60 * 1000) - 32400000,
	CONSTRAINT ESV_UI_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID),
	CONSTRAINT ESV_ESI_FK FOREIGN KEY(EVENT_SCHEDULE_ID) REFERENCES TABLE_ES(EVENT_SCHEDULE_ID)
);
CREATE TABLE TABLE_ESVF
(
	EVENT_SCHEDULE_VIDEO_FACE_ID VARCHAR2(4000) PRIMARY KEY,
	EVENT_SCHEDULE_VIDEO_IMAGE_ID VARCHAR2(4000) NOT NULL,
	EVENT_SCHEDULE_VIDEO_ID NOT NULL,
	CONSTRAINT ESVF_ECVI_FK FOREIGN KEY(EVENT_SCHEDULE_VIDEO_ID) REFERENCES TABLE_ESV(EVENT_SCHEDULE_VIDEO_ID)
);
CREATE TABLE TABLE_ESI
(
	EVENT_SCHEDULE_IMAGE_ID VARCHAR2(4000) PRIMARY KEY,
	FILENAME VARCHAR2(4000) NOT NULL,
	EXT VARCHAR2(4000) NOT NULL,
	DETECT_DATE NUMBER,
	USER_ID VARCHAR2(4000) NOT NULL,
	EVENT_SCHEDULE_ID NUMBER,
	REGION VARCHAR2(4000),
	LATITUDE VARCHAR2(4000),
	LONGITUDE VARCHAR2(4000),
	INPUT_DATE NUMBER DEFAULT ((SYSDATE - TO_DATE('1970-01-01', 'YYYY-MM-DD')) * 24 * 60 * 60 * 1000) - 32400000,
	CONSTRAINT ESI_UI_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID),
	CONSTRAINT ESI_ESI_FK FOREIGN KEY(EVENT_SCHEDULE_ID) REFERENCES TABLE_ES(EVENT_SCHEDULE_ID)
);
CREATE TABLE TABLE_ESIF
(
	EVENT_SCHEDULE_IMAGE_FACE_ID VARCHAR2(4000) PRIMARY KEY,
	TOP NUMBER NOT NULL,
	LEFT NUMBER NOT NULL,
	WIDTH NUMBER NOT NULL,
	HEIGHT NUMBER NOT NULL,
	EVENT_SCHEDULE_IMAGE_ID VARCHAR2(4000) NOT NULL,
	CONSTRAINT ESIF_ECII_FK FOREIGN KEY(EVENT_SCHEDULE_IMAGE_ID) REFERENCES TABLE_ESI(EVENT_SCHEDULE_IMAGE_ID)
);
CREATE TABLE TABLE_ESUS
(
	USER_SCHEDULE_ID NUMBER PRIMARY KEY,
	USER_ID VARCHAR2(4000) NOT NULL,
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
	CONSTRAINT UH_UI_FK FOREIGN KEY(USER_ID) REFERENCES TABLE_U(USER_ID),
	CONSTRAINT UH_PK PRIMARY KEY(HASHTAG, USER_ID)
);
CREATE TABLE TABLE_GH
(
	HASHTAG VARCHAR2(2000) NOT NULL,
	GROUP_ID NUMBER NOT NULL,
	CONSTRAINT GH_GI_FK FOREIGN KEY(GROUP_ID) REFERENCES TABLE_G(GROUP_ID),
	CONSTRAINT GH_PK PRIMARY KEY(HASHTAG, GROUP_ID)
);

INSERT INTO TABLE_U(USER_ID, PASSWORD, NAME, IMAGE_ID, REGION, CONTENT) VALUES('id1@gmail.com', 'password', 'Joy', 'id1@gmail.com.jpg', '광주', 'id1content');
INSERT INTO TABLE_U(USER_ID, PASSWORD, NAME, IMAGE_ID, REGION, CONTENT) VALUES('id2@gmail.com', 'password', 'Arwen', 'id2@gmail.com.jpg', '광주', 'id2content');
INSERT INTO TABLE_U(USER_ID, PASSWORD, NAME, IMAGE_ID, REGION, CONTENT) VALUES('id3@gmail.com', 'password', 'Aragorn', 'id3@gmail.com.jpg', '광주', 'id3content');
INSERT INTO TABLE_U(USER_ID, PASSWORD, NAME, IMAGE_ID, REGION, CONTENT) VALUES('id4@gmail.com', 'password', 'Eowyn', 'id4@gmail.com.jpg', '광주', 'id4content');
INSERT INTO TABLE_U(USER_ID, PASSWORD, NAME, IMAGE_ID, REGION, CONTENT) VALUES('id5@gmail.com', 'password', 'Saruman', 'id5@gmail.com.jpg', '광주', 'id5content');
INSERT INTO TABLE_U(USER_ID, PASSWORD, NAME, IMAGE_ID, REGION, CONTENT) VALUES('id6@gmail.com', 'password', 'Sauron', 'id6@gmail.com.jpg', '광주', 'id6content');
INSERT INTO TABLE_U(USER_ID, PASSWORD, NAME, IMAGE_ID, REGION, CONTENT) VALUES('id7@gmail.com', 'password', 'Nancy', 'id7@gmail.com.jpg', '광주', 'id7content');
INSERT INTO TABLE_U(USER_ID, PASSWORD, NAME, IMAGE_ID, REGION, CONTENT) VALUES('id8@gmail.com', 'password', 'Emma', 'id8@gmail.com.jpg', '광주', 'id8content');
INSERT INTO TABLE_GCA(GROUP_CATEGORY_ID, NAME) VALUES(1, 'IT');
INSERT INTO TABLE_GCA(GROUP_CATEGORY_ID, NAME) VALUES(2, '가족');
INSERT INTO TABLE_GCA(GROUP_CATEGORY_ID, NAME) VALUES(3, '동물');
INSERT INTO TABLE_GCA(GROUP_CATEGORY_ID, NAME) VALUES(4, '요리');
INSERT INTO TABLE_GCA(GROUP_CATEGORY_ID, NAME) VALUES(5, '레저');
INSERT INTO TABLE_GCA(GROUP_CATEGORY_ID, NAME) VALUES(6, '음악');
INSERT INTO TABLE_G(GROUP_ID, GROUP_CATEGORY_ID, REGION, USER_ID, NAME, CONTENT, GROUP_LOGO, GROUP_IMAGE) VALUES(1, 1, '광주', 'id1@gmail.com', 'Web 개발자 모임', 'group1content', '1.jpg', '1.jpg');
INSERT INTO TABLE_G(GROUP_ID, GROUP_CATEGORY_ID, REGION, USER_ID, NAME, CONTENT) VALUES(2, 1, '광주', 'id2@gmail.com', '백엔드 개발자 모임', 'group2content');
INSERT INTO TABLE_G(GROUP_ID, GROUP_CATEGORY_ID, REGION, USER_ID, NAME, CONTENT, GROUP_LOGO) VALUES(3, 1, '광주', 'id1@gmail.com', '프론트엔드 개발자 모임', 'group3content', '3.jpg');
INSERT INTO TABLE_GCO(GROUP_COMMENT_ID, GROUP_ID, USER_ID, CONTENT, INPUT_DATE) VALUES(1, 1, 'id1@gmail.com', 'group1boardcontent1', 1554681600000);
INSERT INTO TABLE_GCO(GROUP_COMMENT_ID, GROUP_ID, USER_ID, CONTENT, INPUT_DATE) VALUES(2, 1, 'id3@gmail.com', 'group1boardcontent2', 1554685200000);
INSERT INTO TABLE_GCO(GROUP_COMMENT_ID, GROUP_ID, USER_ID, CONTENT, INPUT_DATE) VALUES(3, 1, 'id8@gmail.com', 'group1boardcontent3', 1554688800000);
INSERT INTO TABLE_GCO(GROUP_COMMENT_ID, GROUP_ID, USER_ID, CONTENT, INPUT_DATE) VALUES(4, 1, 'id1@gmail.com', 'group1boardcontent4', 1554692400000);
INSERT INTO TABLE_GCO(GROUP_COMMENT_ID, GROUP_ID, USER_ID, CONTENT, INPUT_DATE) VALUES(5, 1, 'id3@gmail.com', 'group1boardcontent5', 1554696000000);
INSERT INTO TABLE_GCO(GROUP_COMMENT_ID, GROUP_ID, USER_ID, CONTENT, INPUT_DATE) VALUES(6, 2, 'id7@gmail.com', 'group2boardcontent1', 1554681600000);
INSERT INTO TABLE_GCO(GROUP_COMMENT_ID, GROUP_ID, USER_ID, CONTENT, INPUT_DATE) VALUES(7, 2, 'id2@gmail.com', 'group2boardcontent2', 1554685200000);
INSERT INTO TABLE_GCO(GROUP_COMMENT_ID, GROUP_ID, USER_ID, CONTENT, INPUT_DATE) VALUES(8, 2, 'id4@gmail.com', 'group2boardcontent3', 1554688800000);
INSERT INTO TABLE_GCO(GROUP_COMMENT_ID, GROUP_ID, USER_ID, CONTENT, INPUT_DATE) VALUES(9, 2, 'id2@gmail.com', 'group2boardcontent4', 1554692400000);
INSERT INTO TABLE_GCO(GROUP_COMMENT_ID, GROUP_ID, USER_ID, CONTENT, INPUT_DATE) VALUES(10, 2, 'id7@gmail.com', 'group2boardcontent5', 1554696000000);
INSERT INTO TABLE_GCO(GROUP_COMMENT_ID, GROUP_ID, USER_ID, CONTENT, INPUT_DATE) VALUES(11, 3, 'id2@gmail.com', 'group3boardcontent1', 1554681600000);
INSERT INTO TABLE_GCO(GROUP_COMMENT_ID, GROUP_ID, USER_ID, CONTENT, INPUT_DATE) VALUES(12, 3, 'id1@gmail.com', 'group3boardcontent2', 1554685200000);
INSERT INTO TABLE_GCO(GROUP_COMMENT_ID, GROUP_ID, USER_ID, CONTENT, INPUT_DATE) VALUES(13, 3, 'id5@gmail.com', 'group3boardcontent3', 1554688800000);
INSERT INTO TABLE_GCO(GROUP_COMMENT_ID, GROUP_ID, USER_ID, CONTENT, INPUT_DATE) VALUES(14, 3, 'id6@gmail.com', 'group3boardcontent4', 1554692400000);
INSERT INTO TABLE_GCO(GROUP_COMMENT_ID, GROUP_ID, USER_ID, CONTENT, INPUT_DATE) VALUES(15, 3, 'id1@gmail.com', 'group3boardcontent5', 1554696000000);
INSERT INTO TABLE_GA(USER_ID, GROUP_ID, BLIND) VALUES('id1@gmail.com', 1, 0);
INSERT INTO TABLE_GA(USER_ID, GROUP_ID) VALUES('id3@gmail.com', 1);
INSERT INTO TABLE_GA(USER_ID, GROUP_ID) VALUES('id8@gmail.com', 1);
INSERT INTO TABLE_GA(USER_ID, GROUP_ID) VALUES('id7@gmail.com', 2);
INSERT INTO TABLE_GA(USER_ID, GROUP_ID) VALUES('id2@gmail.com', 2);
INSERT INTO TABLE_GA(USER_ID, GROUP_ID) VALUES('id4@gmail.com', 2);
INSERT INTO TABLE_GA(USER_ID, GROUP_ID) VALUES('id2@gmail.com', 3);
INSERT INTO TABLE_GA(USER_ID, GROUP_ID, BLIND) VALUES('id1@gmail.com', 3, 0);
INSERT INTO TABLE_GA(USER_ID, GROUP_ID) VALUES('id5@gmail.com', 3);
INSERT INTO TABLE_GA(USER_ID, GROUP_ID) VALUES('id6@gmail.com', 3);
INSERT INTO TABLE_E(EVENT_ID, NAME, CONTENT, GROUP_ID, USER_ID, IMAGE_ID) VALUES(1, 'group1event1', 'group1event1content', 1, 'id1@gmail.com', '1.jpg');
INSERT INTO TABLE_EA(EVENT_ID, USER_ID) VALUES(1, 'id1@gmail.com');
INSERT INTO TABLE_E(EVENT_ID, NAME, CONTENT, GROUP_ID, USER_ID, IMAGE_ID) VALUES(2, 'group1event2', 'group1event2content', 1, 'id3@gmail.com', '2.jpg');
INSERT INTO TABLE_EA(EVENT_ID, USER_ID) VALUES(1, 'id3@gmail.com');
INSERT INTO TABLE_E(EVENT_ID, NAME, CONTENT, GROUP_ID, USER_ID, IMAGE_ID) VALUES(3, 'group1event3', 'group1event3content', 1, 'id8@gmail.com', '3.jpg');
INSERT INTO TABLE_EA(EVENT_ID, USER_ID) VALUES(1, 'id8@gmail.com');
INSERT INTO TABLE_E(EVENT_ID, NAME, CONTENT, GROUP_ID, USER_ID) VALUES(4, 'group2event1', 'group2event1content', 2, 'id7@gmail.com');
INSERT INTO TABLE_E(EVENT_ID, NAME, CONTENT, GROUP_ID, USER_ID) VALUES(5, 'group2event2', 'group2event2content', 2, 'id2@gmail.com');
INSERT INTO TABLE_E(EVENT_ID, NAME, CONTENT, GROUP_ID, USER_ID) VALUES(6, 'group2event3', 'group2event3content', 2, 'id4@gmail.com');
INSERT INTO TABLE_E(EVENT_ID, NAME, CONTENT, GROUP_ID, USER_ID) VALUES(7, 'group3event1', 'group3event1content', 3, 'id6@gmail.com');
INSERT INTO TABLE_E(EVENT_ID, NAME, CONTENT, GROUP_ID, USER_ID) VALUES(8, 'group3event2', 'group3event2content', 3, 'id5@gmail.com');
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, TITLE, PLACE, START_DATE, END_DATE, EVENT_ID) VALUES(1, 'event_schedule1', '광주', 0, 0, 1);
INSERT INTO TABLE_ES(EVENT_SCHEDULE_ID, TITLE, PLACE, START_DATE, END_DATE, EVENT_ID) VALUES(2, 'event_schedule2', '광주', 0, 0, 2);
INSERT INTO TABLE_ESA(EVENT_SCHEDULE_ID, USER_ID) VALUES(1, 'id1@gmail.com');
INSERT INTO TABLE_ESV(EVENT_SCHEDULE_VIDEO_ID, FILENAME, EXT, USER_ID, EVENT_SCHEDULE_ID) VALUES('3a9c7198af', '3a9c7198af.mp4', 'mp4', 'id1@gmail.com', 1);
INSERT INTO TABLE_ESI(EVENT_SCHEDULE_IMAGE_ID, FILENAME, EXT, USER_ID, EVENT_SCHEDULE_ID) VALUES('event_schedule1_image1.jpg', 'event_schedule1_image1.jpg', 'jpg', 'id1@gmail.com', 1);
INSERT INTO TABLE_ESI(EVENT_SCHEDULE_IMAGE_ID, FILENAME, EXT, USER_ID, EVENT_SCHEDULE_ID) VALUES('event_schedule1_image2.jpg', 'event_schedule1_image2.jpg', 'jpg', 'id1@gmail.com', 1);
INSERT INTO TABLE_ESI(EVENT_SCHEDULE_IMAGE_ID, FILENAME, EXT, USER_ID, EVENT_SCHEDULE_ID) VALUES('event_schedule1_image3.jpg', 'event_schedule1_image3.jpg', 'jpg', 'id1@gmail.com', 1);
INSERT INTO TABLE_ESI(EVENT_SCHEDULE_IMAGE_ID, FILENAME, EXT, USER_ID, EVENT_SCHEDULE_ID) VALUES('event_schedule1_image4.jpg', 'event_schedule1_image4.jpg', 'jpg', 'id1@gmail.com', 1);
INSERT INTO TABLE_ESUS(USER_SCHEDULE_ID, USER_ID, EVENT_SCHEDULE_ID, START_DATE, END_DATE) VALUES(1, 'id1@gmail.com', 1, 1554681600000, 1554692400000);
INSERT INTO TABLE_ESUS(USER_SCHEDULE_ID, USER_ID, EVENT_SCHEDULE_ID, START_DATE, END_DATE) VALUES(2, 'id1@gmail.com', 1, 1554696000000, 1554724800000);
INSERT INTO TABLE_ESUS(USER_SCHEDULE_ID, USER_ID, EVENT_SCHEDULE_ID, START_DATE, END_DATE) VALUES(3, 'id1@gmail.com', 1, 1554681600000, 1554685200000);
INSERT INTO TABLE_ESUS(USER_SCHEDULE_ID, USER_ID, EVENT_SCHEDULE_ID, START_DATE, END_DATE) VALUES(4, 'id1@gmail.com', 1, 1554724800000, 1554728400000);
INSERT INTO TABLE_UH(HASHTAG, USER_ID) VALUES('id1hashtag1', 'id1@gmail.com');
INSERT INTO TABLE_UH(HASHTAG, USER_ID) VALUES('id1hashtag2', 'id1@gmail.com');
INSERT INTO TABLE_UH(HASHTAG, USER_ID) VALUES('id2hashtag1', 'id2@gmail.com');
INSERT INTO TABLE_UH(HASHTAG, USER_ID) VALUES('id2hashtag2', 'id2@gmail.com');
INSERT INTO TABLE_GH(HASHTAG, GROUP_ID) VALUES('Web', 1);
INSERT INTO TABLE_GH(HASHTAG, GROUP_ID) VALUES('개발', 1);
COMMIT;