CREATE DATABASE IF NOT EXISTS Sanglier 
	DEFAULT CHARACTER SET utf8
  	DEFAULT COLLATE utf8_general_ci;
USE Sanglier;

CREATE TABLE IF NOT EXISTS Account(
	id_user INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	pseudo CHAR(20) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	mail CHAR(50),
	zip_code INT,
	city CHAR(50),
	adress CHAR(50)
);


CREATE TABLE IF NOT EXISTS Deck
(
	id_deck INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name CHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Card(
	id_card INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id_deck INT,
	number INT DEFAULT 0,
	card_name CHAR(40) NOT NULL,
	card_description VARCHAR(500),
	FOREIGN KEY (id_deck) REFERENCES Deck(id_deck)
);

CREATE TABLE IF NOT EXISTS Inventory
(
	id_user INT,
	id_card INT,
	quantity INT DEFAULT 1,
	PRIMARY KEY (id_user,id_card),
	FOREIGN KEY (id_user) REFERENCES Account(id_user),
	FOREIGN KEY (id_card) REFERENCES Card(id_card)
);

CREATE TABLE IF NOT EXISTS Sport(
	id_sport INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name_sport char(20)
);

/**
 * Ajout id team de l'api
 */
CREATE TABLE IF NOT EXISTS Team (
	id_team INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id_team_api CHAR(50) NOT NULL,
	id_sport INT NOT NULL,
	name_team CHAR(70) NOT NULL,
	FOREIGN KEY (id_sport) REFERENCES Sport(id_sport)
);


CREATE TABLE IF NOT EXISTS Encounter(
	id_encounter INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id_encounter_api CHAR(50) NOT NULL,
	id_sport INT NOT NULL,
	id_team_1 INT NOT NULL,
	id_team_2 INT NOT NULL,
	score_team_1 INT NOT NULL,
	score_team_2 INT NOT NULL,
	state_encounter ENUM('scheduled', 'inprogress','closed'),
	date_encounter DATETIME,
	FOREIGN KEY (id_sport) REFERENCES Sport(id_sport),
	FOREIGN KEY (id_team_1) REFERENCES Team(id_team),
	FOREIGN KEY (id_team_2) REFERENCES Team(id_team),
	CHECK (id_team_1!=id_team_2)
);

CREATE TABLE IF NOT EXISTS Bet(
	id_bet INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id_encounter INT NOT NULL,
	id_creator INT NOT NULL,
	id_opponent INT,
	id_card_creator INT,
	id_card_opponent INT,
	service_creator CHAR(100),
	service_opponent CHAR(100),
	state_bet ENUM('BEGIN', 'WAITING', 'VALID', 'OVER'),
	FOREIGN KEY (id_encounter) REFERENCES Encounter(id_encounter),
	FOREIGN KEY (id_card_creator) REFERENCES Card(id_card),
	FOREIGN KEY (id_card_opponent) REFERENCES Card(id_card),
	FOREIGN KEY (id_creator) REFERENCES Account(id_user),
	FOREIGN KEY (id_opponent) REFERENCES Account(id_user),
	CHECK (id_creator!=id_oppenent),
	CHECK (service_opponent!=null || id_card_opponent!=null),
	CHECK (id_card_creator!=null || service_creator!=null)
);

INSERT INTO deck VALUES (1,"FFXIV");

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

