CREATE SEQUENCE hibernate_sequence INCREMENT 1;


CREATE TABLE profile (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	abstract_m varchar(255) NOT NULL,
	id_user int NOT NULL,
	CONSTRAINT uk_9jbqnak1yu5orjiwxoqxvlxff UNIQUE (id_user)
);

 CREATE TABLE experience (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	position_m varchar(255) NOT NULL,
	job_type varchar(255) NOT NULL,
	company varchar(255) NOT NULL,
	locality varchar(255) NOT NULL,
	current_job bool NOT NULL,
	start_date date not null,
	end_date date null,
	abstract_m varchar(255) NOT NULL,
	id_profile int NOT NULL,
	CONSTRAINT co_id_profile FOREIGN KEY (id_profile) REFERENCES profile(id)
 );
 
 
 CREATE TABLE academic_education (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	abstract_m varchar(255) NOT NULL,
	activity_and_groups varchar(255) NULL,
	educational_institution varchar(255) NOT NULL,
	end_date date NULL,
	formation varchar(255) NULL,
	note varchar(255) NULL,
	start_date date NOT NULL,
	study_area varchar(255) NULL,
	id_profile int NOT NULL,
	CONSTRAINT co_id_profile_a_e FOREIGN KEY (id_profile) REFERENCES profile(id)
);

-- CREATE TABLE new_table_sample (
--	id BIGSERIAL PRIMARY KEY NOT NULL,
--	value_e float(53) NOT NULL,
--	date_registration date not null,
--	difenrenttable_id int NULL REFERENCES difenrenttable(id)
-- );

