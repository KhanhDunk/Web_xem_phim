	Create database Web_dat_ve_xem_phim ;
	use Web_dat_ve_xem_phim ;


	create table Role
    (
    RoleId int Primary Key Not null ,
    RoleName NVARCHAR(100) Not Null 
    ) ; 


	Create table Users (
	 UserId int Primary Key  AUTO_INCREMENT Not Null , 
     UserName nvarchar(255) not null , 
	 FullName NVARCHAR(255) Not Null , 
	 Email NVARCHAR(255) Not Null , 
	 Password NVARCHAR(255) Not Null , 
	 Phone VARCHAR(20) Not Null , 
     BirthDay Date null , 
	 CreateAt DateTime Not Null , 
     Avatar VarChar(255) null  , 
     Point int null , 
     Gender NVARCHAR(3) null , 
	 Roleid Int Not Null 
	 );

	create table Movies(
	MovieId int Primary Key  AUTO_INCREMENT Not Null ,
	MovieName NVARCHAR(255) Not Null  , 
	Type NVARCHAR(100) Not Null , 
	Duration int Null, 
	ReleaseDate Date Not null ,
	Description NVARCHAR(255) Null , 
	Language NVARCHAR(50) Null , 
	Director NVARCHAR(100) Not Null , 
	Poster NVARCHAR(255) Null , 
	Trailer NVARCHAR(255) Null 	, 
    ShowTimeId int 
	);
	 
	 
	 Create table Cinema 
	 (
	 CinemaId int Primary Key  AUTO_INCREMENT Not Null ,  
	 CinemaName NVARCHAR(255) Not Null , 
	 Location NVARCHAR(255) Null, 
	 Phone VARCHAR(20) Null , 
     RoomId int Null 
	 );
	 
	 
	 Create table Rooms (
	 RoomId int Primary Key  AUTO_INCREMENT Not Null ,  
	 RoomNumber int not null , 
	 Capacity int not null ,
     ShowTimeId int not null 
	 );
	 
	 
	 Create table Seats 
	 (
	 SeatId int Primary Key  AUTO_INCREMENT Not Null ,   
	 SeatNumber VARCHAR(10) not null  , 
	 SeatType NVARCHAR(50) not null , 
     RoomId int Not null 
	 );
	 
	 Create table ShowTimes 
	 (
	 ShowTimeId int Primary Key  AUTO_INCREMENT Not Null ,
	 StartTime DateTime Not Null ,
	 EndTime DateTime Not Null ,
	 MovieId int not null , 
     BookingDetailId Int not null 
	 );
	 
	 
	Create table Booking (
	BookingId int Primary Key  AUTO_INCREMENT Not Null ,  
	BookingDate DateTime Null  , 
	TotalPrice Decimal(10,2) Null , 
	StatusBooking NVARCHAR(50) Null , 
    UserId int not null 
	); 

	Create table BookingDetail 
	(
		BookingDetailId  int Primary Key  AUTO_INCREMENT Not Null ,   
		Price Decimal(10,2) not null ,
        BookingId int not null 
	);

	 



	-- Thêm khóa ngoại cho bảng Users
ALTER TABLE Users
ADD CONSTRAINT FK_Users_Role
FOREIGN KEY (RoleId) REFERENCES Role(RoleId);

-- Thêm khóa ngoại cho bảng Movies
ALTER TABLE Movies
ADD CONSTRAINT FK_Movies_ShowTimes
FOREIGN KEY (ShowTimeId) REFERENCES ShowTimes(ShowTimeId);

-- Thêm khóa ngoại cho bảng Cinema
ALTER TABLE Cinema
ADD CONSTRAINT FK_Cinema_Rooms
FOREIGN KEY (RoomId) REFERENCES Rooms(RoomId);

-- Thêm khóa ngoại cho bảng Rooms
ALTER TABLE Rooms
ADD CONSTRAINT FK_Rooms_ShowTimes
FOREIGN KEY (ShowTimeId) REFERENCES ShowTimes(ShowTimeId);

-- Thêm khóa ngoại cho bảng Seats
ALTER TABLE Seats
ADD CONSTRAINT FK_Seats_Rooms
FOREIGN KEY (RoomId) REFERENCES Rooms(RoomId);

-- Thêm khóa ngoại cho bảng ShowTimes
ALTER TABLE ShowTimes
ADD CONSTRAINT FK_ShowTimes_Movies
FOREIGN KEY (MovieId) REFERENCES Movies(MovieId);

ALTER TABLE ShowTimes
ADD CONSTRAINT FK_ShowTimes_BookingDetail
FOREIGN KEY (BookingDetailId) REFERENCES BookingDetail(BookingDetailId);

-- Thêm khóa ngoại cho bảng Booking
ALTER TABLE Booking
ADD CONSTRAINT FK_Booking_Users
FOREIGN KEY (UserId) REFERENCES Users(UserId);

-- Thêm khóa ngoại cho bảng BookingDetail
ALTER TABLE BookingDetail
ADD CONSTRAINT FK_BookingDetail_Booking
FOREIGN KEY (BookingId) REFERENCES Booking(BookingId);




