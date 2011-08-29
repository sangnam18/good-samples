CREATE TABLE visitor (
id varchar(20),
nickname varchar(30)
);

INSERT INTO visitor VALUES ('mathical', 'so~cool');
INSERT INTO visitor VALUES ('minslovey', 'yomanman');
INSERT INTO visitor VALUES ('whoareu', 'im not a girl');
INSERT INTO visitor VALUES ('thanks', 'PPPPoooPPPP');
INSERT INTO visitor VALUES ('humanp', 'humanp');

CREATE TABLE article (
title varchar(50),
contents varchar(30),
createdate date,
hit int
);

INSERT INTO article VALUES ('my blog good', 'abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabc', now(), 1234);
INSERT INTO article VALUES ('my blog good1', 'contents * 999 * contentscontents * 999 * contentscontents * 999 * contentscontents * 999 * contentscontents * 999 * contentscontents * 999 * contentscontents * 999 * contentscontents * 999 * contentscontents * 999 * contentscontents * 999 * contentscontents * 999 * contents', now(), 1234);
INSERT INTO article VALUES ('my blog good2', 'contents * 999 * contents', now(), 1234);
INSERT INTO article VALUES ('my blog good3', 'contents * 999 * contents', now(), 1234);
INSERT INTO article VALUES ('my blog good4', 'contents * 999 * contents', now(), 1234);
INSERT INTO article VALUES ('my blog good5', 'contents * 999 * contents', now(), 1234);
INSERT INTO article VALUES ('my blog good6', 'contents * 999 * contents', now(), 1234);
INSERT INTO article VALUES ('my blog good7', 'contents * 999 * contents', now(), 1234);
INSERT INTO article VALUES ('my blog good8', 'contents * 999 * contents', now(), 1234);
INSERT INTO article VALUES ('my blog good9', 'contents * 999 * contents', now(), 1234);

CREATE TABLE profile (
name varchar(30),
age int,
hobby varchar(50)
);

INSERT INTO profile VALUES ('Min Cha', 32, 'Reading book');

CREATE TABLE comment (
comment varchar(50),
createdate date
);

INSERT INTO comment VALUES ('good contents!', now());
INSERT INTO comment VALUES ('good contents!', now());
INSERT INTO comment VALUES ('good contents!', now());
INSERT INTO comment VALUES ('good contents!', now());
INSERT INTO comment VALUES ('good contents!', now());
INSERT INTO comment VALUES ('good contents!', now());
INSERT INTO comment VALUES ('good contents!', now());
INSERT INTO comment VALUES ('good contents!', now());
INSERT INTO comment VALUES ('good contents!', now());
INSERT INTO comment VALUES ('good contents!', now());
INSERT INTO comment VALUES ('good contents!', now());
