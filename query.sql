USE mediscreen;

CREATE TABLE patient (
  patient_id tinyint(4) NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(125),
  last_name VARCHAR(125),
  birth_date DATE,
  gender VARCHAR(15),
  address VARCHAR(125),
  telephone VARCHAR(125),
  PRIMARY KEY (patient_id)
);

INSERT INTO patient(first_name, last_name, birth_date, gender, address, telephone) values("John", "Doe", "2023-01-01", "M", "123 Rue de la Place 12345 Paris", "0123456789");
INSERT INTO patient(first_name, last_name, birth_date, gender, address, telephone) values("Jeff", "Brian", "2023-01-01", "M", "122 Rue de la Place 12345 Paris", "0123456789");
