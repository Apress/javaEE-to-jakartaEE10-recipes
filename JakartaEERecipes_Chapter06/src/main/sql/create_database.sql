/*
 * Create the database for the JakartaEERecipes application prior to deploying.
 * To create the database in Apache NetBeans IDE, utilize the "Services" tab
 * to add a new JavaDB database (Apache Derby).
 *
 * Database Name: acme (may refer to it as acmedb within JNDI connections)
 * Database User: acme
 *
 * Once the database has been created, execute the following script to created
 * the objects and populate the tables.
 */
create table author_work(
    id          numeric,
    author_id   numeric,
    book_id     numeric,
    PRIMARY KEY (ID));

create table book (
    id          numeric,
    title       varchar(150),
    image       varchar(500),
    num_chapters numeric,
    num_pages       numeric,
    description     varchar(2000),
    publish_date    date,
    PRIMARY KEY (ID)
);

create table book_author (
    id              numeric,
    lastname        varchar(150),
    firstname       varchar(150),
    bio             varchar(2500),
    PRIMARY KEY (ID)
);

create table book_category (
    id              numeric,
    store_id        numeric,
    name            varchar(150),
    PRIMARY KEY (ID)
);

create table book_store (
    id              numeric,
    name            varchar(150),
    location_city    varchar(150),
    location_state  varchar(150),
    PRIMARY KEY (ID)
);

create table chapter (
     id              numeric,
     chapter_numeric  numeric,
     title           varchar(500),
     description     varchar(2500),
     book_id         numeric,
     PRIMARY KEY (ID)
 );

create table contact (
     id              numeric,
     firstname       varchar(150),
     lastname        varchar(150),
     password        varchar(30),
     email           varchar(150),
     decription      varchar(2500),
     occupation      varchar(150),
     receivenotifications varchar(1),
     gender          varchar(1),
     PRIMARY KEY (ID)
 );

create table employee (
    id              numeric,
    firstname           varchar(150),
    lastname            varchar(150),
    age             numeric,
    job_id          numeric,
    status          numeric,
    PRIMARY KEY (ID)
);

create table it_category (
    genre           varchar(150),
    description     varchar(2500)
 );

create table jobs (
    job_id              numeric,
    title           varchar(150),
    division        varchar(150),
    salary          numeric,
    PRIMARY KEY (JOB_ID)
);

ALTER TABLE CHAPTER ADD CONSTRAINT FK_CHAPTER_BOOK_ID FOREIGN KEY (BOOK_ID) REFERENCES BOOK (ID);
ALTER TABLE BOOK_CATEGORY ADD CONSTRAINT FK_BOOK_CATEGORY_STORE_ID FOREIGN KEY (STORE_ID) REFERENCES BOOK_STORE (ID);
ALTER TABLE AUTHOR_WORK ADD CONSTRAINT FK_AUTHOR_WORK_AUTHOR_ID FOREIGN KEY (AUTHOR_ID) REFERENCES BOOK_AUTHOR (ID);
ALTER TABLE AUTHOR_WORK ADD CONSTRAINT FK_AUTHOR_WORK_BOOK_ID FOREIGN KEY (BOOK_ID) REFERENCES BOOK (ID);

create sequence author_work_s
    start with 1
    increment by 1;

create sequence book_s
    start with 1
    increment by 1;

create sequence book_author_s
    start with 1
    increment by 1;

create sequence book_category_s
    start with 1
    increment by 1;

create sequence book_store_s
    start with 1
    increment by 1;

create sequence chapter_s
    start with 1
    increment by 1;

create sequence contact_s
    start with 1
    increment by 1;

create sequence employee_s
    start with 1
    increment by 1;

create sequence jobs_s
    start with 1
    increment by 1;

-- Insert some data
insert into book_author (ID, BIO, FIRSTNAME, LASTNAME) values(
    next value for book_author_s,
    'Author of Apress Books',
    'Josh',
    'Juneau');

insert into book_author (ID, BIO, FIRSTNAME, LASTNAME) values(
    next value for book_author_s,
    'JavaFX Guru',
    'Carl',
    'Dea');

insert into book_author (ID, BIO, FIRSTNAME, LASTNAME) values(
    next value for book_author_s,
    'Apress Editor',
    'Jonathan',
    'Gennick');

insert into book (ID, DESCRIPTION, IMAGE, TITLE, NUM_CHAPTERS, NUM_PAGES, PUBLISH_DATE) values(
    next value for book_s,
    'Tips and Techniques for Beginner, Intermediate, or Advanced Developers for Java 9.',
    '',
    'Java 9 Recipes',
    20,
    700,
    '2017-01-01');

insert into book (ID, DESCRIPTION, IMAGE, TITLE, NUM_CHAPTERS, NUM_PAGES, PUBLISH_DATE) values(
    next value for book_s,
    'Java EE 8 Recipes takes an example-based approach in showing how to ' ||
    'program Enterprise Java applications for many different scenarios, and ' ||
    'using the very latest in frameworks and technologies that are available ' ||
    'in the Java EE 7 platform.',
    20,
    700,
    '2017-01-01');
