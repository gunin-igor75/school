-- liquibase formatted sql

-- changeset gil:1
CREATE INDEX st_name_index ON student (name)

-- changeset gil:2
CREATE INDEX fac_name_color_index ON faculty (nane, color)

-- changeset gil:3
CREATE INDEX avatar_index ON avatar (student_id)

