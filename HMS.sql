create table Patient
(
id int not null,
name nvarchar(50) not null,
dateOfBirth date,
address nvarchar(100), 
sex int, 
phone nvarchar(20), 
biographicHealth nvarchar(2000),
check (sex = 0 or sex = 1),
primary key (id)
);

insert into Patient values(1,'Nguyễn Văn Long',2000,'12 Lê Lai',1,'','None');
insert into Patient values(2,'Trương Kim Thanh',1993,'3/4 Trần Quang Khải',0,'','');
insert into Patient values(3,'Hà Ngọc Quốc Vương','21/12/2000','Biên Hòa',1,'','');
insert into Patient values(4,'Lê Mạnh Giác','30/04/1998','Sài Gòn',1,'','');
insert into Patient values(5,'Hồ Khải Phong','23/03/1978','Q.1, SG',1,'','');
insert into Patient values(6,'Nguyễn Hữu Hùng',1994,'4 Phan Tôn',1,0903351857,'None');

create table Physician
(
id int not null,
name nvarchar(50) not null,
role nvarchar(20), 
available boolean, 
primary key (id)
);

insert into Physician values(100,'Trần Thế Hiển','Doctor',1);
insert into Physician values(101,'Phạm Minh Khiêm','Doctor',1);
insert into Physician values(102,'Nguyễn Thị Hồng Hạnh','Nurse',1);
insert into Physician values(103,'Hà Thị Hoa Mai','Nurse',1);
insert into Physician values(104,'Võ Thị Anh Thi','Nurse',1);
insert into Physician values(105,'Hồ Thụy Hương Thủy','Nurse',1);

create table PhysicianAssignment
(
id int not null,
patientId int,
physicianId int,
startDate date,
endDate date,
primary key (id),
foreign key (patientId) references Patient(id),
foreign key (physicianId) references Physician(id)
);

insert into PhysicianAssignment values(3,1,105,'28/03/2012 02:07:51','28/03/2012 02:07:51');
insert into PhysicianAssignment values(4,3,103,'28/03/2012 02:08:45','28/03/2012 02:08:45');
insert into PhysicianAssignment values(5,6,102,'28/03/2012 02:08:51','28/03/2012 02:08:51');
insert into PhysicianAssignment values(6,1,104,'04/04/2012 04:11:05','04/04/2012 04:11:05');
insert into PhysicianAssignment values(7,1,104,'04/04/2012 04:11:10','04/04/2012 04:11:10');

create table MedicalRecord 
(
id int not null,
patientId int,
dateAffect date,
detail nvarchar(200),
primary key (id),
foreign key (patientId) references Patient(id)
);

insert into MedicalRecord values(1,1,'28/03/2012 02:07:51','abc');
insert into MedicalRecord values(2,1,'28/03/2012 02:07:51','abc');
insert into MedicalRecord values(3,1,'04/04/2012 14:56:57','');
insert into MedicalRecord values(4,1,'04/04/2012 14:56:57','');
insert into MedicalRecord values(5,1,'04/04/2012 14:56:57','');
insert into MedicalRecord values(6,1,'04/04/2012 14:56:57','');
insert into MedicalRecord values(7,1,'12/05/2010 14:56:57','aaa');
insert into MedicalRecord values(8,1,'12/05/2010 12:12:12','aaa');
insert into MedicalRecord values(9,1,'12/12/2012 12:12:12','aaa');
insert into MedicalRecord values(10,1,'12/12/2012 00:00:00','aaa');
insert into MedicalRecord values(11,1,'12/03/2012 12:12:12','aaa');
insert into MedicalRecord values(12,2,'12/03/2012 00:00:00','aaa');
insert into MedicalRecord values(13,2,'12/03/2000 00:00:00','aaa');
insert into MedicalRecord values(14,1,'12/03/2000 00:00:00','qqq');
insert into MedicalRecord values(15,1,'12/03/2000 00:00:00','test');
insert into MedicalRecord values(16,1,'12/03/2000 00:00:00','ttt');

create table User 
(
id int not null,
name nvarchar(50) not null,
password nvarchar(50) not null,
email nvarchar(100),
role nvarchar(20),
primary key (id)
);

insert into User values(1,'nhphat',123,'phatpt01@gmail.com','Receptionist');
insert into User values(2,'lxhoan',123,'lxhoan@gmail.com','Doctor');
insert into User values(3,'tlnquynh',123,'bachthuconuong@gmail.com','Nurse');
insert into User values(4,'nmathu',123,'ngominhanhthu@gmail.com','Nurse');

create table Schedule 
(
id int not null,
physicianId int,
startDate date,
endDate date,
available boolean, 
primary key (id),
foreign key (physicianId) references Physician(id)
);

insert into Schedule values(1, 100, '05/04/2012 15:47:15', '05/04/2012 15:47:15', 1);
