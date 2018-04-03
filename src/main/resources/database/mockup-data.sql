
INSERT INTO todoapp_user(id, email, password, permission, username) VALUES
    (1, 'davidcbsi@gmail.com', '$2a$10$5wmSEbr3aUOmMtikPkKt/OV4G5B5nUzBJNk08xjEEUE2N9qmOtloW', 1, 'davidwillianx');

INSERT INTO todoapp_task(id, created, description, label, status, creator_id) VALUES
(1,now(), 'Not been a dummass everyday', 'Not DUmm',0, 1);
INSERT INTO todoapp_task(id, created, description, label, status, creator_id) VALUES
(2,now(), 'Clean my BadRoom', 'Clean Up',0, 1);

-- INSERT INTO todoapp_task(id, created, description, label, creator_id) VALUES
-- (1,now(), 'Not been a dummass everyday', 'Not DUmm', 1),
-- (2,now(), 'Clean my BadRoom', 'Clean Up', 1),
-- (3,now(), 'Do the dish', 'Do dish', 1),
-- (4,now(), 'Just a lorem', 'Ipsum','Lorem ip', 1)
-- (5,now(), 'Tasking someting', 'tasking something', 1);
