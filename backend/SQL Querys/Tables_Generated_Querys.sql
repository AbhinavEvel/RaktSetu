create database raktsetu;
show databases;
use raktsetu;
create table users(user_id int auto_increment primary key,name varchar(50) not null, email varchar(40) not null,phone varchar(15),role enum ('Donor','Patient','Admin'), is_verified enum ('Pending','Reject','Accept') default 'Pending',Create_date_at timestamp default current_timestamp);

create table patients(patient_id int auto_increment primary key, user_id int  not null,blood_group_need enum ('A+','A-','AB+','AB-','B+','B-','O+','O-') , age varchar(3), city varchar(50),state varchar(40),address text, foreign key (user_id) references users(user_id));

create table blood_requests(requet_id int auto_increment primary key,patient_id int not null,blood_group enum ('A+','A-','AB+','AB-','B+','B-','O+','O-'),Component enum('Whole_Blood','RBC','Plasma','Platelets') not null, unit_need int not null,status enum ('Pending','FullFilled','Cancelled') default 'Pending', urgency enum('Normal' ,'Emergency') default 'Normal', Contact_Number varchar(15) not null,Location_detail text,additional_note text, Create_date_at timestamp default current_timestamp , foreign key (patient_id ) references patients(patient_id));

show tables;
desc blood_requests;




