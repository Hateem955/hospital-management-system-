INSERT INTO app_user (user_id, password, role, username) VALUES
(2, '$2a$10$h9KnXL68zfTWXXhJyOyBIu2zkPGj3/xNUuSzrZ8g67TYyCSFGm.R.', 'ROLE_DOCTOR',  'Bob'),
(3, '$2a$10$qlQG3I1xCiRdTcVy9neem.CTjQrFhv5t70IWnzpfIrqWJfJuL2uHa', 'ROLE_DOCTOR', 'Steve');

INSERT INTO doctor (doc_id, name, specialization, years_of_experience, user_id) VALUES
(1, 'Dr. Bob Johnson',   'Pediatrics', 10, 2),
(2, 'Dr. Steve Williams','Dermatology', 8, 3);

INSERT INTO time_slot (id, end_time, is_booked, start_time, doc_id) VALUES
(1, '2025-12-01 10:00:00', FALSE, '2025-12-01 09:00:00', 1),
(2,  '2025-12-01 11:00:00', FALSE, '2025-12-01 10:00:00', 2);