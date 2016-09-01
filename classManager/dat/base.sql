create table courseManager
(	
	No smallint primary key,	
	courseID varchar(10),
	courseName varchar(20),
	major varchar(20),
	teachingClass varchar(10),
	theoryTime smallint check(theoryTime between 1 and 3),
	experimentTime smallint,
	teacherID varchar(10),
	teacherName varchar(10),
);