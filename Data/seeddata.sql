use jobsSystem;

#Companies Already Enabled
insert into Login(email,password,type,enable)values('jobs@intel.com','001',1,127);
insert into Login(email,password,type,enable)values('software@avantica.com','002',1,127);
insert into Login(email,password,type,enable)values('vdi@vmware.com','003',1,127);

#Offerers Already Enabled
insert into Login(email,password,type,enable)values('estefa.mr97@gmail.com','1122',2,127);
insert into Login(email,password,type,enable)values('andres0305@gmail.com','andres',2,127);
insert into Login(email,password,type,enable)values('liss@gmail.com','liss',2,127);

#Disabled Offerers
insert into Login(email,password,type,enable)values('juancastillo@gmail.com',null,2,-128);
insert into Login(email,password,type,enable)values('mariacorrales@outlook.com',null,2,-128);
insert into Login(email,password,type,enable)values('jesuscastro@yahoo.com',null,2,-128);


#Disabled companies
insert into Login(email,password,type,enable)values('jobs@huawei.com',null,1,-128);
insert into Login(email,password,type,enable)values('recruitment@ibm.com',null,1,-128);
insert into Login(email,password,type,enable)values('oportunities@oracle.com',null,1,-128);
insert into Login(email,password,type,enable)values('jobs@microsoft.com',null,1,-128);
insert into Login(email,password,type,enable)values('recruitment@apple.com',null,1,-128);


#Profiles enabled companies
insert into Company(name,description,phoneNumber,longitude,latitude,Login_idLogin)
values('Intel',
'Intel invents at the boundaries of technology to make amazing experiences possible for business and society, and for every person on Earth.'
,'22986000',-84.175798,9.997084,2);

insert into Company(name,description,phoneNumber,longitude,latitude,Login_idLogin)
values('Avantica','We believe in the beauty of synergy in the development of outstanding solutions that help world-class companies achieve their goals.'
,'22839100',-84.064248,9.982494,3);

insert into Company(name,description,phoneNumber,longitude,latitude,Login_idLogin)
values('VmWare','We build software that has the power to unlock new opportunities for people and our planet.'
,'22449103',9.930790,-84.050889,4);


#Profiles disabled companies
insert into Company(name,description,phoneNumber,longitude,latitude,Login_idLogin)
values('Huawei','Mobile phones and networking devices.','23245678',-84.173398,9.117084,5);
insert into Company(name,description,phoneNumber,longitude,latitude,Login_idLogin)
values('IBM','International Business Machines','29342557',-84.223398,9.447084,6);
insert into Company(name,description,phoneNumber,longitude,latitude,Login_idLogin)
values('Oracle','Databases products management','21241616',-84.222398,9.411084,7);
insert into Company(name,description,phoneNumber,longitude,latitude,Login_idLogin)
values('Microsoft','Windows: The best OS','22556777',-84.222111,9.411222,8);
insert into Company(name,description,phoneNumber,longitude,latitude,Login_idLogin)
values('Apple','iPhones, iPads and more','23233434',-84.227711,9.417722,9);


#Offeres enabled profiles
insert into offerer(idOfferer,name,lastname,originCountry,phoneNumber,Login_idLogin)
values('117000387','Estefania','Murillo','Costa Rica',84839429,5);
insert into offerer(idOfferer,name,lastname,originCountry,phoneNumber,Login_idLogin)
values('402300958','Andres','Romero','Costa Rica',86234057,6);
insert into offerer(idOfferer,name,lastname,originCountry,phoneNumber,Login_idLogin)
values('116630669','Lisandra','Aguero','Costa Rica',83367306,7);

#Features
insert into feature(name,Feature_idFeature)values('Programming Languages',null); 	#1 
insert into feature(name,Feature_idFeature)values('SQL DataBases',null);  			#2
insert into feature(name,Feature_idFeature)values('Operative Systems',null);  		#3
insert into feature(name,Feature_idFeature)values('PHP',1); 						#4
insert into feature(name,Feature_idFeature)values('Oracle',2); 						#5
insert into feature(name,Feature_idFeature)values('Java',1); 						#6
insert into feature(name,Feature_idFeature)values('MySQL',2); 						#7
insert into feature(name,Feature_idFeature)values('Python',1); 						#8
insert into feature(name,Feature_idFeature)values('Javascript',1); 					#9
insert into feature(name,Feature_idFeature)values('Ruby',1); 						#10
insert into feature(name,Feature_idFeature)values('Windows',3); 					#11
insert into feature(name,Feature_idFeature)values('Linux',3); 						#12
insert into feature(name,Feature_idFeature)values('Angular',9); 					#13
insert into feature(name,Feature_idFeature)values('Windows Server 2008',11); 		#14
insert into feature(name,Feature_idFeature)values('Windows Server 2012',11); 		#15
insert into feature(name,Feature_idFeature)values('RedHat',12); 					#16
insert into feature(name,Feature_idFeature)values('Linux Mint',12); 				#17

insert into Position (name,publishDate,salary,publicPos,Company_idCompany,enable)
values('Apps Development Intern','2018-03-05',3000,127,1,127);

insert into Position (name,publishDate,salary,publicPos,Company_idCompany,enable)
values('Software Engineering Intern','2018-04-15',6000,127,1,127);

insert into Position (name,publishDate,salary,publicPos,Company_idCompany,enable)
values('Front-End engineer','2018-02-22',1200,127,2,127);

insert into Position (name,publishDate,salary,publicPos,Company_idCompany,enable)
values('Back-End Engineer','2018-03-25',1500,127,2,127);

insert into Position (name,publishDate,salary,publicPos,Company_idCompany,enable)
values('QA Engineer','2018-05-19',1000,127,2,127);

insert into Position (name,publishDate,salary,publicPos,Company_idCompany,enable)
values('Windows Administrator','2018-02-10',2500,127,3,127);

insert into Position (name,publishDate,salary,publicPos,Company_idCompany,enable)
values('Linux Administrator','2018-06-01',4000,127,3,127);

#APPS DEVELOPMENT INTERN:
#Level NULL, Feature: Programming Languages
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(0,1,1);

#Level NULL, Feature: DataBases
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(0,1,3);

#Level 50%, Feature: PHP
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(40,1,2);

#Level 50%, Feature: Oracle
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(50,1,4);


#SOFTWARE ENGINEERING INTERN
#Level NULL, Feature: Programming Languages
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(0,2,1);

#Level 65%, Feature: Java
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(65,2,5);

#Level 50%, Feature: Angular
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(50,2,9);


#FRONT_END ENGINEER
#Level NULL, Feature: Programming Languages
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(0,3,1);

#Level NULL, Feature: JavaScript Frameworks
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(0,3,8);

#Level 70%, Feature: Angular
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(70,3,9);

#Level 60%, Feature: Java
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(60,3,5);

#Level 60%, Feature: Ruby
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(60,3,10);


#BACK_END ENGINEER
#Level NULL, Feature: Programming Languages
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(0,4,1);

#Level 85%, Feature: Java
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(85,4,5);

#Level NULL, Feature: JavaScript Frameworks
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(0,4,8);

#Level 50%, Feature: Angular
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(50,4,9);


#QA ENGINEER
#Level NULL, Feature: Programming Languages
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(0,5,1);

#Level NULL, Feature: DataBases
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(0,5,3);

#Level 60%, Feature: Java
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(60,5,5);

#Level 80%, Feature: MySQL
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(80,5,6);


#Windows Administrator
#Level NULL, Feature: Operative systems
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(0,6,3);

#Level 60%, Feature: Windows
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(0,6,11);

#Level 60%, Feature: Windows Server 2008
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(60,6,14);

#Level 80%, Feature: Windows Server 2008
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(80,6,15);


#Linux Administrator
#Level NULL, Feature: Operative systems
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(0,7,3);

#Level 60%, Feature: Windows
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(0,7,12);

#Level 60%, Feature: RedHat
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(60,7,16);

#Level 80%, Feature: Linux Mint
insert into PositionFeature(level,Position_idPosition,Feature_idFeature)
values(80,7,17);