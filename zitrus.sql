CREATE DATABASE zitrus;

CREATE TABLE zitrus.proceduresql (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              name VARCHAR(255) NOT NULL
);

INSERT INTO zitrus.proceduresql (id, name) VALUES (1, '1234');
INSERT INTO zitrus.proceduresql (id, name) VALUES (2, '4567');
INSERT INTO zitrus.proceduresql (id, name) VALUES (3, '6789');

CREATE TABLE zitrus.rule (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      procedureid BIGINT NOT NULL,
                      age INT NOT NULL,
                      gender VARCHAR(1) NOT NULL,
                      CONSTRAINT fk_rule_proceduresql FOREIGN KEY (procedureid) REFERENCES proceduresql(id)
);

INSERT INTO zitrus.rule (id, procedureid, age, gender) VALUES (1, 2, 20, 'M');
INSERT INTO zitrus.rule (id, procedureid, age, gender) VALUES (2, 3, 10, 'M');
INSERT INTO zitrus.rule (id, procedureid, age, gender) VALUES (3, 2, 30, 'F');
INSERT INTO zitrus.rule (id, procedureid, age, gender) VALUES (4, 1, 20, 'M');

CREATE TABLE zitrus.authorization (
                               id BIGINT PRIMARY KEY AUTO_INCREMENT,
                               name VARCHAR(255) NOT NULL,
                               procedureid BIGINT NOT NULL,
                               allowed VARCHAR(255) NOT NULL,
                               age INT NOT NULL,
                               gender VARCHAR(1) NOT NULL,
                               CONSTRAINT fk_authorization_proceduresql FOREIGN KEY (procedureid) REFERENCES proceduresql(id)
);

INSERT INTO zitrus.authorization (id, name, procedureid, allowed, age, gender) VALUES (1, '123 1', 2, 'A', 25, 'F');
INSERT INTO zitrus.authorization (id, name, procedureid, allowed, age, gender) VALUES (2, '4567 2', 3, 'N', 35, 'M');
INSERT INTO zitrus.authorization (id, name, procedureid, allowed, age, gender) VALUES (3, '6789 3', 2, 'C', 45, 'F');
