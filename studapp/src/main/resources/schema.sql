DROP TABLE IF EXISTS students;

CREATE TABLE Students (
  firstName VARCHAR(250) NOT NULL,
  lastName VARCHAR(250) NOT NULL ,
  dateOfBirth DATE NOT NULL,
  jmbag VARCHAR(250) PRIMARY KEY NOT NULL,
  numberOfEcts INTEGER DEFAULT NULL
) ;

DROP TABLE IF EXISTS courses;

CREATE TABLE Courses (
  COURSE_ID INTEGER PRIMARY KEY NOT NULL,
  COURSE_NAME VARCHAR(250) NOT NULL,
  COURSE_ECTS INTEGER DEFAULT NULL
);

DROP TABLE IF EXISTS student_course;

CREATE TABLE student_course (
  COURSE_ID INTEGER NOT NULL,
  student_jmbag VARCHAR(250) NOT NULL
);

CREATE TABLE if not exists user (
  id identity,
  username varchar(100) not null,
  password varchar(250) not null,
  first_name varchar(250) not null,
  last_name varchar(250) not null
);

create table if not exists authority (
  id identity,
  name varchar(100) not null
);

create table if not exists user_authority (
  user_id bigint not null,
  authority_id bigint not null,
  constraint fk_user foreign key (user_id) references user(id),
  constraint fk_authority foreign key (authority_id) references authority(id)
);