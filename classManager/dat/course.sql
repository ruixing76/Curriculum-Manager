create table course
(	No smallint primary key,
	courseID varchar(10),
	teachingClass varchar(10),
	timeID Smallint check(timeID between 1 and 20),
	classRoom varchar(10),
	major varchar(20)
);