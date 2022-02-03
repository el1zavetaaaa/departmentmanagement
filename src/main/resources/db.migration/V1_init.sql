DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;

create table departments
(
    id   bigserial primary key,
    name text unique not null
);

insert into departments (id, name)
VALUES (default, 'department 1'),
       (default, 'department 2');

create table employees
(
    id            bigserial primary key,
    email         text unique not null,
    salary        integer     not null,
    hire_date     date        not null,
    department_id integer,
    foreign key (department_id) references departments (id) on delete cascade
);

insert into employees (id, email, salary, hire_date, department_id)
VALUES (default, 'employee1@gmail.com', 500, '2022-02-01', 1),
       (default, 'employee2@gmail.com', 450, '2021-05-15', 2);

