CREATE TABLE stadium (
    id int not null auto_increment,
    stadium_name VARCHAR(40),
    hometeam_id VARCHAR(10),
    seat_count INTEGER,
    address VARCHAR(60),
    ddd VARCHAR(10),
    tel VARCHAR(10),
    PRIMARY KEY(id)
    )DEFAULT CHARSET=utf8;
);

CREATE TABLE schedule (
    id int not null auto_increment,
    gubun VARCHAR(10),
    hometeam_id VARCHAR(10),
    awayteam_id VARCHAR(10),
    home_score INTEGER,
    away_score INTEGER,
    stadium_id INT,
    PRIMARY KEY(id),
    FOREIGN KEY(stadium_id) REFERENCES stadium(id)
    )DEFAULT CHARSET=utf8;
);

CREATE TABLE team (
    id int not null auto_increment,
    region_name VARCHAR(10),
    team_name VARCHAR(40),
    e_team_name VARCHAR(50),
    orig_yyyy VARCHAR(10),
    zip_code1 VARCHAR(10),
    zeip_code2 VARCHAR(10),
    address VARCHAR(80),
    ddd VARCHAR(10),
    tel VARCHAR(10),
    fax VARCHAR(10),
    homepage VARCHAR(50),
    owner VARCHAR(10),
    stadium_id INT,
    PRIMARY KEY(id),
    FOREIGN KEY(stadium_id) REFERENCES stadium(id)
    )DEFAULT CHARSET=utf8;

);

CREATE TABLE player (
    id int not null auto_increment,
    player_name VARCHAR(20),
    e_player_name VARCHAR(40),
    nickname VARCHAR(30),
    join_yyyy VARCHAR(10),
    position VARCHAR(10),
    back_no INTEGER,
    nation VARCHAR(20),
    birth_date VARCHAR(10),
    solar VARCHAR(10),
    height INTEGER,
    weight VARCHAR(20),
    team_id INT,
    PRIMARY KEY(id),
    FOREIGN KEY(team_id) REFERENCES team(id)
    )DEFAULT CHARSET=utf8;
);