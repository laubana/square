CREATE USER square IDENTIFIED BY square;
GRANT CONNECT, RESOURCE, CREATE VIEW TO square;

ALTER SYSTEM SET PROCESSES=500 SCOPE=SPFILE;