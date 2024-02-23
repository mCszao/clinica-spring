CREATE TABLE schedule (
id SERIAL PRIMARY KEY,
id_doctor INTEGER NOT NULL,
id_patient INTEGER NOT NULL,
date TIMESTAMP NOT NULL,

CONSTRAINT fk_schedule_id_doctor FOREIGN KEY(id_doctor) REFERENCES doctors(id),
CONSTRAINT fk_schedule_id_id_patient FOREIGN KEY(id_patient) REFERENCES patients(id)
);