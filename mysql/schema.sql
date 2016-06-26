DROP TABLE IF EXISTS reports;
CREATE TABLE reports (
	id bigint NOT NULL AUTO_INCREMENT,
	start_date date NOT NULL,
	end_date date NOT NULL,
	performer varchar(255) not null,
	activity varchar(255) not null,
	PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;
