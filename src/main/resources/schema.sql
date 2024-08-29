CREATE TABLE IF NOT EXISTS Run (
    id INT NOT NULL,
    title VARCHAR(250) NOT NULL,
    started_on TIMESTAMP,
    completed_on TIMESTAMP,
    distance INT NOT NULL,
    location VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);
