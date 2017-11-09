CREATE DATABASE IF NOT EXISTS Sanglier;
USE Sanglier;

CREATE TABLE IF NOT EXISTS Account(
	id_user INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	pseudo CHAR(20) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL
	/*mail CHAR(50),
	zip_code INT,
	city CHAR(50),
	adress CHAR(50)*/
);

CREATE TABLE IF NOT EXISTS Card(
	id_card INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	card_name CHAR(40) NOT NULL,
	card_description CHAR(150)
);

CREATE TABLE IF NOT EXISTS Inventory
(
	id_user INT NOT NULL,
	id_item INT NOT NULL,
	FOREIGN KEY (id_user) REFERENCES User(id_user),
	FOREIGN KEY (id_item) REFERENCES Card(id_item),
	PRIMARY KEY(id_user,id_item)
);

CREATE TABLE IF NOT EXISTS Sport(
	id_sport INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name_sport char(20)
);

CREATE TABLE IF NOT EXISTS Team (
	id_team INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id_sport INT NOT NULL,
	name_team CHAR(20),
	FOREIGN KEY (id_sport) REFERENCES Sport(id_sport)
);

CREATE TABLE IF NOT EXISTS Encounter(
	id_encounter INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id_sport INT NOT NULL,
	id_team_1 INT NOT NULL,
	id_team_2 INT NOT NULL,
	score_team_1 INT NOT NULL,
	score_team_2 INT NOT NULL,
	state_encounter ENUM('Soon','Current', 'Over'),
	date_encounter DATETIME,
	FOREIGN KEY (id_sport) REFERENCES Sport(id_sport),
	FOREIGN KEY (id_team_1) REFERENCES Team(id_team),
	FOREIGN KEY (id_team_2) REFERENCES Team(id_team),
	CHECK (id_team_1!=id_team_2)
);

CREATE TABLE IF NOT EXISTS Bet_On_Win(
	id_bet INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id_encounter INT NOT NULL,
	id_creator INT NOT NULL,
	id_oppenent INT,
	id_card_creator INT,
	id_card_oppenent INT,
	service_creator CHAR(100),
	service_oppenent CHAR(100),
	state_bet ENUM('BEGIN', 'WAITING', 'VALID', 'OVER'),
	FOREIGN KEY (id_encounter) REFERENCES Encounter(id_encounter),
	FOREIGN KEY (id_card_creator) REFERENCES Card(id_card),
	FOREIGN KEY (id_card_oppenent) REFERENCES Card(id_card),
	FOREIGN KEY (id_creator) REFERENCES Account(id_user),
	FOREIGN KEY (id_oppenent) REFERENCES Account(id_user),
	CHECK (id_creator!=id_oppenent),
	CHECK (service_oppenent!=null || id_card_oppenent!=null),
	CHECK (id_card_creator!=null || service_creator!=null)
);

/*
CREATE OR REPLACE FUNCTION checkSport()
RETURNS boolean
AS BEGIN(    
 IF  SPORT de encounter = team1.sport + team2.sport
       RETURN TRUE;
  ELSE
       RETURN FALSE;
  END IF;
) END 

ALTER TABLE Encounter ADD CONSTRAINT EcounterConstraint CHECK (checkSport());*/

/*
CREATE OR REPLACE FUNCTION checkInventory()
RETURNS boolean
AS BEGIN(    
 IF  item mis en jeu inclus dans son inventaire
       RETURN TRUE;
  ELSE
       RETURN FALSE;
  END IF;
) END 

ALTER TABLE Bet_On_Win ADD CONSTRAINT InventoryConstraint CHECK (checkInventory());*/
