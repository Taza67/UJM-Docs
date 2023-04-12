DROP TABLE IF EXISTS documents;
DROP TABLE IF EXISTS utilisateur;

CREATE TABLE IF NOT EXISTS utilisateur (
       id INT UNSIGNED NOT NULL AUTO_INCREMENT,
       pseudo VARCHAR(255) NOT NULL,
       mot_de_passe VARCHAR(255) NOT NULL,
       PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS documents (
       id INT UNSIGNED NOT NULL AUTO_INCREMENT,
       id_utilisateur INT UNSIGNED NOT NULL,
       date_de_modification DATE,
       PRIMARY KEY (id),
       FOREIGN KEY(id_utilisateur) REFERENCES utilisateur(id)
       ON DELETE RESTRICT ON UPDATE RESTRICT
);
