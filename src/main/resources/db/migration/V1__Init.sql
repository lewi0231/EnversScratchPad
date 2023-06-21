CREATE SEQUENCE revisions_seq INCREMENT 1 MINVALUE 1 START 1;

CREATE TABLE revisions (
    revision SERIAL PRIMARY KEY,
    timestamp BIGINT NOT NULL
);

CREATE TABLE student (
    student_id SERIAL PRIMARY KEY,
    student_name VARCHAR(255),
    is_smart BOOLEAN,
    age INTEGER
);

CREATE TABLE student_audit (
    student_id INTEGER NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    student_name VARCHAR(255),
    is_smart BOOLEAN,
    PRIMARY KEY (student_id, rev),
    FOREIGN KEY (rev) REFERENCES revisions(revision)
);

-- Script to create the `laptop` table
CREATE TABLE laptop (
    laptop_id SERIAL PRIMARY KEY,
    model VARCHAR(255),
    brand VARCHAR(255),
    student_student_id INT,
    FOREIGN KEY (student_student_id) REFERENCES student(student_id)
);

-- Script to create the `laptop_audit` table
CREATE TABLE laptop_audit (
    laptop_id INT NOT NULL,
    rev INT NOT NULL,
    revtype SMALLINT,
    brand VARCHAR(255),
    student_student_id INT,
    PRIMARY KEY (laptop_id, rev),
    FOREIGN KEY (rev) REFERENCES revisions(revision),
    FOREIGN KEY (student_student_id) REFERENCES student(student_id)
);

-- Script to create the `enrolment` table
CREATE TABLE enrolment (
    enrolment_id SERIAL PRIMARY KEY,
    is_enrolled BOOLEAN NOT NULL,
    enrolment_date TIMESTAMP WITH TIME ZONE,
    student_student_id INT,
    FOREIGN KEY (student_student_id) REFERENCES student(student_id)
);

-- Script to create the `enrolment_audit` table
CREATE TABLE enrolment_audit (
    enrolment_id INT NOT NULL,
    rev INT NOT NULL,
    revtype SMALLINT,
    is_enrolled BOOLEAN NOT NULL,
    enrolment_date TIMESTAMP WITH TIME ZONE,
    student_student_id INT,
    PRIMARY KEY (enrolment_id, rev),
    FOREIGN KEY (rev) REFERENCES revisions(revision),
    FOREIGN KEY (student_student_id) REFERENCES student(student_id)
);