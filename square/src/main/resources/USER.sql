ALTER system SET processes=100 scope=spfile;
shutdown immediate;
startup;



CREATE USER square IDENTIFIED BY square;
GRANT CONNECT, RESOURCE, CREATE VIEW TO square;