drop procedure if exists Insert_Student;
drop table if exists prerequisite;
drop table if exists student_class_relationship;
drop table if exists major_class_relationship;
drop table if exists assignment;
drop table if exists grade;
drop table if exists class;
drop table if exists instructor;
drop table if exists student;
drop table if exists major;

create table major (
 id int primary key auto_increment,
 description varchar(20),
 minimum_sat int
);

create table student (
 id int primary key auto_increment,
 first_name varchar(30),
 last_name varchar(30),
 gpa decimal(5.1),
 sat int,
 major_id int
-- foreign key (major_id) references major(id)
);

create table instructor (
 id int primary key auto_increment,
 first_name varchar(30),
 last_name varchar(30),
 major_id int,
 years_experience int,
 tenure tinyint,
 foreign key (major_id) references major(id)
);

create table class (
 id int primary key auto_increment,
 description varchar(20),
 instructor_id int,
 foreign key (instructor_id) references instructor(id)
);

create table grade (
 id int primary key auto_increment,
 description varchar(30)
);

create table assignment (
 id int primary key auto_increment,
 student_id int,
 assignment_nbr int,
 grade_id int,
 class_id int,
 foreign key (student_id) references student(id),
 foreign key (grade_id) references grade(id),
 foreign key (class_id) references class(id)
);

create table major_class_relationship (
 id int primary key auto_increment,
 major_id int,
 class_id int,
 foreign key (major_id) references major(id),
 foreign key (class_id) references class(id)
);

create table student_class_relationship (
 id int primary key auto_increment,
 student_id int,
 class_id int,
 foreign key (student_id) references student(id),
 foreign key (class_id) references class(id)
);

create table prerequisite (
 id int primary key auto_increment,
 class_id int,
 prereq int,
 foreign key (class_id) references class(id),
 foreign key (prereq) references class(id)
);

 insert major (description, minimum_sat) values ('General Business', 800);
 insert major (description, minimum_sat) values ('Accounting', 1000);
 insert major (description, minimum_sat) values ('Finance', 1100);
 insert major (description, minimum_sat) values ('Math', 1300);
 insert major (description, minimum_sat) values ('Engineering', 1350);
 insert major (description, minimum_sat) values ('Education', 900);
 insert major (description, minimum_sat) values ('General Studies', 500);

 insert student (first_name, last_name, gpa, sat, major_id) values ('Eric', 'Ephram', 3.0, 1300, 1);
 insert student (first_name, last_name, gpa, sat, major_id) values ('Greg', 'Gould', 4.0, 1400, 2);
 insert student (first_name, last_name, gpa, sat, major_id) values ('Adam', 'Ant', 3.5, 1200, 3);
 insert student (first_name, last_name, gpa, sat, major_id) values ('Howard', 'Hess', 2.5, 1350, 4);
 insert student (first_name, last_name, gpa, sat, major_id) values ('Charles', 'Caldwell', 2.0, 1250, 5);
 insert student (first_name, last_name, gpa, sat, major_id) values ('James', 'Joyce', 3.3, 1100, 6);
 insert student (first_name, last_name, gpa, sat, major_id) values ('Doug', 'Dumas', 1.3, 500, 7);
 insert student (first_name, last_name, gpa, sat, major_id) values ('Kevin', 'Kraft', 3.8, 900, 1);
 insert student (first_name, last_name, gpa, sat, major_id) values ('Frank', 'Fountain', 4.0, 1500, 2);
 insert student (first_name, last_name, gpa, sat, major_id) values ('Brian', 'Biggs', 3.0, 1200, 3);

 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Bobby', 'Businessman', 1, 3, 0);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Billy', 'Bizkid', 1, 9, 1);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Adam', 'Accountant', 2, 2, 0);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Alice', 'Accountsalot', 2, 12, 1);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Fred', 'Financeton', 3, 4, 0);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Franz', 'Financand', 3, 11, 1);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Mark', 'Mathenstein', 4, 4, 0);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Marla', 'Mathsmerrily', 4, 9, 1);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Edgar', 'Enginons', 5, 3, 0);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Ella', 'Engino', 5, 3, 0);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Edward', 'Educato', 6, 3, 0);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Eelman', 'Eelman', 6, 13, 1);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Gena', 'General', 7, 3, 0);
 insert instructor (first_name, last_name, major_id, years_experience, tenure) 
	values ('Solomon', 'Studies', 7, 10, 1);

 insert class (description, instructor_id) values ('English 101', 13);
 insert class (description, instructor_id) values ('English 102', 13);
 insert class (description, instructor_id) values ('English 103', 13);
 insert class (description, instructor_id) values ('English 201', 14);
 insert class (description, instructor_id) values ('English 202', 14);
 insert class (description, instructor_id) values ('English 203', 14);
 insert class (description, instructor_id) values ('English 301', 13);
 insert class (description, instructor_id) values ('English 302', 14);
 insert class (description, instructor_id) values ('English 303', 13);
 insert class (description, instructor_id) values ('Math 201', 1);
 insert class (description, instructor_id) values ('Math 202', 1);
 insert class (description, instructor_id) values ('Math 203', 2);
 insert class (description, instructor_id) values ('Math 204', 2);
 insert class (description, instructor_id) values ('Math 301', 7);
 insert class (description, instructor_id) values ('Math 302', 7);
 insert class (description, instructor_id) values ('Math 303', 8);
 insert class (description, instructor_id) values ('Math 304', 8);
 insert class (description, instructor_id) values ('History 101', 11);
 insert class (description, instructor_id) values ('History 201', 11);
 insert class (description, instructor_id) values ('History 301', 12);
 insert class (description, instructor_id) values ('Computer Science 311', 9);
 insert class (description, instructor_id) values ('Computer Science 312', 9);
 insert class (description, instructor_id) values ('Computer Science 313', 9);
 insert class (description, instructor_id) values ('Computer Science 441', 10);
 insert class (description, instructor_id) values ('Computer Science 442', 10);
 insert class (description, instructor_id) values ('Computer Science 443', 10);
 insert class (description, instructor_id) values ('Psychology 101', 1);
 insert class (description, instructor_id) values ('Psychology 102', 2);
 insert class (description, instructor_id) values ('Psychology 231', 3);
 insert class (description, instructor_id) values ('Psychology 232', 13);
 insert class (description, instructor_id) values ('Education 221', 3);
 insert class (description, instructor_id) values ('Education 222', 3);
 insert class (description, instructor_id) values ('Education 223', 4);
 insert class (description, instructor_id) values ('Education 351', 4);
 insert class (description, instructor_id) values ('Education 352', 5);
 insert class (description, instructor_id) values ('Education 353', 6);

 insert grade (description) values ('Incomplete');
 insert grade (description) values ('Complete and Unsatisfactory');
 insert grade (description) values ('Complete and Satisfactory');
 insert grade (description) values ('Exceeds Expectations');
 insert grade (description) values ('Not Graded');

 insert assignment (student_id, assignment_nbr, grade_id, class_id) values (1, 1, 1, 2);
 insert assignment (student_id, assignment_nbr, grade_id, class_id) values (2, 1, 2, 1);
 insert assignment (student_id, assignment_nbr, grade_id, class_id) values (3, 1, 3, 4);
 insert assignment (student_id, assignment_nbr, grade_id, class_id) values (4, 1, 4, 14);
 insert assignment (student_id, assignment_nbr, grade_id, class_id) values (5, 1, 5, 21);
 insert assignment (student_id, assignment_nbr, grade_id, class_id) values (6, 1, 4, 34);
 insert assignment (student_id, assignment_nbr, grade_id, class_id) values (7, 1, 3, 27);
 insert assignment (student_id, assignment_nbr, grade_id, class_id) values (8, 1, 2, 31);
 insert assignment (student_id, assignment_nbr, grade_id, class_id) values (9, 1, 1, 10);
 insert assignment (student_id, assignment_nbr, grade_id, class_id) values (10, 1, 5, 23);

 insert major_class_relationship (major_id, class_id) values (1, 1);
 insert major_class_relationship (major_id, class_id) values (2, 1);
 insert major_class_relationship (major_id, class_id) values (3, 1);
 insert major_class_relationship (major_id, class_id) values (4, 1);
 insert major_class_relationship (major_id, class_id) values (5, 1);
 insert major_class_relationship (major_id, class_id) values (6, 1);
 insert major_class_relationship (major_id, class_id) values (7, 1);
 insert major_class_relationship (major_id, class_id) values (1, 2);
 insert major_class_relationship (major_id, class_id) values (7, 3);
 insert major_class_relationship (major_id, class_id) values (2, 4);
 insert major_class_relationship (major_id, class_id) values (1, 5);
 insert major_class_relationship (major_id, class_id) values (7, 6);
 insert major_class_relationship (major_id, class_id) values (7, 7);
 insert major_class_relationship (major_id, class_id) values (7, 8);
 insert major_class_relationship (major_id, class_id) values (7, 9);
 insert major_class_relationship (major_id, class_id) values (2, 10);
 insert major_class_relationship (major_id, class_id) values (2, 11);
 insert major_class_relationship (major_id, class_id) values (3, 12);
 insert major_class_relationship (major_id, class_id) values (3, 13);
 insert major_class_relationship (major_id, class_id) values (4, 14);
 insert major_class_relationship (major_id, class_id) values (4, 15);
 insert major_class_relationship (major_id, class_id) values (4, 16);
 insert major_class_relationship (major_id, class_id) values (4, 17);
 insert major_class_relationship (major_id, class_id) values (7, 18);
 insert major_class_relationship (major_id, class_id) values (7, 19);
 insert major_class_relationship (major_id, class_id) values (7, 20);
 insert major_class_relationship (major_id, class_id) values (5, 21);
 insert major_class_relationship (major_id, class_id) values (5, 22);
 insert major_class_relationship (major_id, class_id) values (3, 23);
 insert major_class_relationship (major_id, class_id) values (3, 24);
 insert major_class_relationship (major_id, class_id) values (5, 25);
 insert major_class_relationship (major_id, class_id) values (5, 26);
 insert major_class_relationship (major_id, class_id) values (7, 27);
 insert major_class_relationship (major_id, class_id) values (7, 28);
 insert major_class_relationship (major_id, class_id) values (7, 29);
 insert major_class_relationship (major_id, class_id) values (7, 30);
 insert major_class_relationship (major_id, class_id) values (1, 31);
 insert major_class_relationship (major_id, class_id) values (1, 32);
 insert major_class_relationship (major_id, class_id) values (2, 33);
 insert major_class_relationship (major_id, class_id) values (6, 34);
 insert major_class_relationship (major_id, class_id) values (6, 35);
 insert major_class_relationship (major_id, class_id) values (6, 36);

 insert student_class_relationship (student_id, class_id) values (1, 2);
 insert student_class_relationship (student_id, class_id) values (1, 5);
 insert student_class_relationship (student_id, class_id) values (2, 1);
 insert student_class_relationship (student_id, class_id) values (2, 4);
 insert student_class_relationship (student_id, class_id) values (3, 12);
 insert student_class_relationship (student_id, class_id) values (3, 13);
 insert student_class_relationship (student_id, class_id) values (4, 14);
 insert student_class_relationship (student_id, class_id) values (4, 15);
 insert student_class_relationship (student_id, class_id) values (5, 21);
 insert student_class_relationship (student_id, class_id) values (5, 22);
 insert student_class_relationship (student_id, class_id) values (6, 34);
 insert student_class_relationship (student_id, class_id) values (6, 35);
 insert student_class_relationship (student_id, class_id) values (7, 27);
 insert student_class_relationship (student_id, class_id) values (7, 28);
 insert student_class_relationship (student_id, class_id) values (8, 31);
 insert student_class_relationship (student_id, class_id) values (8, 32);
 insert student_class_relationship (student_id, class_id) values (9, 10);
 insert student_class_relationship (student_id, class_id) values (9, 11);
 insert student_class_relationship (student_id, class_id) values (10, 23);
 insert student_class_relationship (student_id, class_id) values (10, 24);

-- English prereqs
 insert prerequisite (class_id, prereq) values (1, NULL);
 insert prerequisite (class_id, prereq) values (2, 1); 
 insert prerequisite (class_id, prereq) values (3, 1); 
 insert prerequisite (class_id, prereq) values (3, 2);
 insert prerequisite (class_id, prereq) values (4, 1);
 insert prerequisite (class_id, prereq) values (4, 2);
 insert prerequisite (class_id, prereq) values (4, 3);
 insert prerequisite (class_id, prereq) values (5, 1);
 insert prerequisite (class_id, prereq) values (5, 2);
 insert prerequisite (class_id, prereq) values (5, 3);
 insert prerequisite (class_id, prereq) values (5, 4);
 insert prerequisite (class_id, prereq) values (6, 1);
 insert prerequisite (class_id, prereq) values (6, 2);
 insert prerequisite (class_id, prereq) values (6, 3);
 insert prerequisite (class_id, prereq) values (6, 4);
 insert prerequisite (class_id, prereq) values (6, 5);
 insert prerequisite (class_id, prereq) values (7, 1);
 insert prerequisite (class_id, prereq) values (7, 2);
 insert prerequisite (class_id, prereq) values (7, 3);
 insert prerequisite (class_id, prereq) values (7, 4);
 insert prerequisite (class_id, prereq) values (7, 5);
 insert prerequisite (class_id, prereq) values (7, 6);
 insert prerequisite (class_id, prereq) values (8, 1);
 insert prerequisite (class_id, prereq) values (8, 2);
 insert prerequisite (class_id, prereq) values (8, 3);
 insert prerequisite (class_id, prereq) values (8, 4);
 insert prerequisite (class_id, prereq) values (8, 5);
 insert prerequisite (class_id, prereq) values (8, 6);
 insert prerequisite (class_id, prereq) values (8, 7);
 insert prerequisite (class_id, prereq) values (9, 1);
 insert prerequisite (class_id, prereq) values (9, 2);
 insert prerequisite (class_id, prereq) values (9, 3);
 insert prerequisite (class_id, prereq) values (9, 4);
 insert prerequisite (class_id, prereq) values (9, 5);
 insert prerequisite (class_id, prereq) values (9, 6);
 insert prerequisite (class_id, prereq) values (9, 7);
 insert prerequisite (class_id, prereq) values (9, 8);

-- Math prereqs
 insert prerequisite (class_id, prereq) values (10, NULL);
 insert prerequisite (class_id, prereq) values (11, 10); 
 insert prerequisite (class_id, prereq) values (12, 10); 
 insert prerequisite (class_id, prereq) values (12, 11);
 insert prerequisite (class_id, prereq) values (13, 10);
 insert prerequisite (class_id, prereq) values (13, 11);
 insert prerequisite (class_id, prereq) values (13, 12);
 insert prerequisite (class_id, prereq) values (14, 10);
 insert prerequisite (class_id, prereq) values (14, 11);
 insert prerequisite (class_id, prereq) values (14, 12);
 insert prerequisite (class_id, prereq) values (14, 13);
 insert prerequisite (class_id, prereq) values (15, 10);
 insert prerequisite (class_id, prereq) values (15, 11);
 insert prerequisite (class_id, prereq) values (15, 12);
 insert prerequisite (class_id, prereq) values (15, 13);
 insert prerequisite (class_id, prereq) values (15, 14);
 insert prerequisite (class_id, prereq) values (16, 10);
 insert prerequisite (class_id, prereq) values (16, 11);
 insert prerequisite (class_id, prereq) values (16, 12);
 insert prerequisite (class_id, prereq) values (16, 13);
 insert prerequisite (class_id, prereq) values (16, 14);
 insert prerequisite (class_id, prereq) values (16, 15);
 insert prerequisite (class_id, prereq) values (17, 10);
 insert prerequisite (class_id, prereq) values (17, 11);
 insert prerequisite (class_id, prereq) values (17, 12);
 insert prerequisite (class_id, prereq) values (17, 13);
 insert prerequisite (class_id, prereq) values (17, 14);
 insert prerequisite (class_id, prereq) values (17, 15);
 insert prerequisite (class_id, prereq) values (17, 16);

-- History prereqs
 insert prerequisite (class_id, prereq) values (18, NULL);
 insert prerequisite (class_id, prereq) values (19, 18);
 insert prerequisite (class_id, prereq) values (20, 18);
 insert prerequisite (class_id, prereq) values (20, 19);

-- Computer Science prereqs
 insert prerequisite (class_id, prereq) values (21, NULL);
 insert prerequisite (class_id, prereq) values (22, 21);  
 insert prerequisite (class_id, prereq) values (23, 21);
 insert prerequisite (class_id, prereq) values (23, 22);
 insert prerequisite (class_id, prereq) values (24, 21);
 insert prerequisite (class_id, prereq) values (24, 22);
 insert prerequisite (class_id, prereq) values (24, 23);
 insert prerequisite (class_id, prereq) values (25, 21);
 insert prerequisite (class_id, prereq) values (25, 22);
 insert prerequisite (class_id, prereq) values (25, 23);
 insert prerequisite (class_id, prereq) values (25, 24);
 insert prerequisite (class_id, prereq) values (26, 21);
 insert prerequisite (class_id, prereq) values (26, 22);
 insert prerequisite (class_id, prereq) values (26, 23);
 insert prerequisite (class_id, prereq) values (26, 24);
 insert prerequisite (class_id, prereq) values (26, 25);

-- Psychology prereqs
 insert prerequisite (class_id, prereq) values (27, NULL);
 insert prerequisite (class_id, prereq) values (28, 27);
 insert prerequisite (class_id, prereq) values (29, 28);
 insert prerequisite (class_id, prereq) values (29, 28);
 insert prerequisite (class_id, prereq) values (30, 29);
 insert prerequisite (class_id, prereq) values (30, 29);
 insert prerequisite (class_id, prereq) values (30, 29);

-- Math prereqs
 insert prerequisite (class_id, prereq) values (31, NULL);
 insert prerequisite (class_id, prereq) values (32, 31);  
 insert prerequisite (class_id, prereq) values (33, 31);
 insert prerequisite (class_id, prereq) values (33, 32);
 insert prerequisite (class_id, prereq) values (34, 31);
 insert prerequisite (class_id, prereq) values (34, 32);
 insert prerequisite (class_id, prereq) values (34, 33);
 insert prerequisite (class_id, prereq) values (35, 31);
 insert prerequisite (class_id, prereq) values (35, 32);
 insert prerequisite (class_id, prereq) values (35, 33);
 insert prerequisite (class_id, prereq) values (35, 34);
 insert prerequisite (class_id, prereq) values (36, 31);
 insert prerequisite (class_id, prereq) values (36, 32);
 insert prerequisite (class_id, prereq) values (36, 33);
 insert prerequisite (class_id, prereq) values (36, 34);
 insert prerequisite (class_id, prereq) values (36, 35);



-- Procedure for ensuring SAT is between 400 & 1600 and GPA is between 0.0 and 5.0

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `Insert_Student`(
	IN FirstName varchar(30), 
	IN LastName varchar(30), 
	IN SAT int, 
	IN GPA Decimal(5,1), 
	IN MajorDescription varchar(50)
)
BEGIN
    DECLARE MajorId int;
	-- Check SAT between 400 and 1600 inclusive
	IF SAT < 400 OR SAT > 1600 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'SAT is out of range (400 <= x <= 1600)';
	END IF;
    -- Check GPA between 0 <= x <= 5.0
    IF GPA < 0.0 OR GPA > 5.0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'GPA is out of range (0 <= x <= 5.0)';
	END IF;
    -- Get the major.id from major where major.description is eq MajorDescription
    SELECT id into MajorId from major where description = MajorDescription;
    -- All data edited, insert the student
    INSERT student (first_name, last_name, sat, gpa, major_id)
		VALUES (FirstName, LastName, SAT, GPA, MajorId);
END
//
DELIMITER ;








-- Procedure for ensuring a student can not enroll in a class unless the prerequisites have been met


DROP PROCEDURE IF EXISTS Enroll_For_Class;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `Enroll_For_Class`(
	IN StudentId int,  
	IN ClassDescription varchar(50)
)
BEGIN
    DECLARE ClassId int;


	IF (select count(*) from(
	select prereq from (
	select prereq from prerequisite where class_id in (select id from class where class.description = ClassDescription)) a
	where a.prereq not in
	(select class_id from student_class_relationship where student_class_relationship.student_id = StudentId)) b) > 0

			THEN
				SIGNAL SQLSTATE '45000'
					SET MESSAGE_TEXT = 'Prerequisites needed for that class';
	END IF;
	SELECT class.id into ClassId from class where class.description = ClassDescription;
	INSERT student_class_relationship (student_id, class_id) 
		VALUES (StudentId, ClassId);
	END
//

DELIMITER ;




-- Queries all classes in a student's major
select concat(student.first_name, ' ', student.last_name) as "Student", major.description as "Major", class.description as "Required Classes" from student
	join major on student.major_id = major.id
	join major_class_relationship on major.id = major_class_relationship.major_id
	join class on major_class_relationship.class_id = class.id 
	where student.id = 2;  

-- Queries which classes a student has taken
select concat(student.first_name, ' ', student.last_name) as "Student", major.description as "Major", class.description as "Required Classes Taken" from student
	join major on student.major_id = major.id
	join major_class_relationship on major.id = major_class_relationship.major_id
	join class on major_class_relationship.class_id = class.id 
	where student.id = 2 and class_id in (select class_id from student_class_relationship where student_id = 2);  

-- Queries which classes a student still needs to take for their major
select concat(student.first_name, ' ', student.last_name) as "Student", major.description as "Major", class.description as "Required Classes Still Needed" from student
	join major on student.major_id = major.id
	join major_class_relationship on major.id = major_class_relationship.major_id
	join class on major_class_relationship.class_id = class.id 
	where student.id = 2 and class_id not in (select class_id from student_class_relationship where student_id = 2);  


-- Queries which classes are required for a given major
select major.description as "Major", class.description as "Needed Classes" from major
	join major_class_relationship on major.id = major_class_relationship.major_id
	join class on major_class_relationship.class_id = class.id 
	where major.description = "Accounting";

-- Queries which majors require a certain class
select class.description as "Class", major.description as "Major" from class
	join major_class_relationship on class.id = major_class_relationship.class_id
	join major on major_class_relationship.major_id = major.id
	where class.description = "English 101";

-- Queries which Prerequisites are required for a given class
select t1.description as "Searched Class", t2.description as "Prerequisites"  from class t1
	join prerequisite on t1.id = prerequisite.class_id
	join class t2 on prerequisite.prereq = t2.id
	where t1.description  = "English 303";


-- Tests Insert_Student, once successfully, twice failing
call Insert_Student('Willy','Works',1200,3.0,'Accounting');
call Insert_Student('Sally','SatTooLow',200,3.0,'Accounting');
call Insert_Student('G.P.','Faker',1200,7.0,'Accounting');

-- Tests Enroll_For_Class, once successfully, once failing
call Enroll_For_Class(10, 'English 101');
call Enroll_For_Class(10, 'English 103');

