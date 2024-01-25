ALTER TABLE doctors
ADD CONSTRAINT fk.user_id
FOREIGN KEY (user_id)
REFERENCES users(id);