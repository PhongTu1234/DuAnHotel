IF DB_ID('DataHotel_new') IS NOT NULL
BEGIN
    USE master;
    ALTER DATABASE DataHotel_new SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE DataHotel_new;
END
CREATE DATABASE DataHotel_new
go
USE DataHotel_new
go

--Bảng địa điểm
CREATE TABLE Place (
    place_id INT IDENTITY(1,1) PRIMARY KEY,			--ID duy nhất cho địa điểm thuê
	place_name NVARCHAR(100),			-- Tên địa điểm thuê
);
go
--Bảng dịch vụ
CREATE TABLE [Services] (
	service_id INT IDENTITY(1,1) PRIMARY KEY,		--ID duy nhất cho dịch vụ
	service_name nvarchar(50),		--Tên dịch vụ
);

go

-- Bảng Khách sạn
CREATE TABLE Hotels (
    hotel_id INT IDENTITY(1,1) PRIMARY KEY,               -- ID duy nhất cho khách sạn
    hotel_name NVARCHAR(100) NOT NULL,      -- Tên khách sạn
    [address] NVARCHAR(200),                -- Địa chỉ của khách sạn
    phone_number VARCHAR(20),               -- Số điện thoại liên hệ của khách sạn
	Email_hotel varchar(50),				-- Email của khách sạn
    [description] nvarchar(MAX),            -- Mô tả về khách sạn
	place_id INT,							-- Địa điểm thuê khách sạn
	hotel_level INT NOT NULL,				--Mức độ khách sạn
	FOREIGN KEY (place_id) REFERENCES Place(place_id) ON DELETE CASCADE,
);
go
CREATE TABLE IMG_HOTEL(
	img_hotel_id INT IDENTITY(1,1) PRIMARY KEY,
	img_name varchar(50),
	hotel_id int,
	FOREIGN KEY (hotel_id) REFERENCES Hotels(hotel_id) ON DELETE CASCADE
);
go
-- Bảng Loại Phòng
CREATE TABLE RoomTypes (
    room_type_id INT IDENTITY(1,1) PRIMARY KEY,           -- ID duy nhất cho loại phòng
    room_type_name NVARCHAR(50) NOT NULL,   -- Tên loại phòng (ví dụ: Standard, Deluxe, Suite)
);
go

-- Bảng Phòng
CREATE TABLE Rooms (
    room_id INT IDENTITY(1,1) PRIMARY KEY,                -- ID duy nhất cho phòng
    hotel_id INT NOT NULL,                  -- Khóa ngoại liên kết với bảng Khách sạn
    room_type_id INT,                       -- Khóa ngoại liên kết với bảng Loại Phòng
    roomname VARCHAR(50),					-- Số phòng
	rating FLOAT,							-- Điểm đánh giá của phòng
	price DECIMAL(10, 2),					-- Giá tiền của loại phòng
	soluongphong int,
	soluongchocheckin int,
	soluongtrong int,
	soluongdangthue int,
    --status nvarchar(50),                    -- Trạng thái phòng (đã đặt, trống)
    description TEXT,                       -- Mô tả về phòng
    FOREIGN KEY (room_type_id) REFERENCES RoomTypes(room_type_id) ON DELETE CASCADE,
	FOREIGN KEY (hotel_id) REFERENCES Hotels(hotel_id) ON DELETE CASCADE,
);
go
CREATE TABLE img_room(
	img_room_id INT IDENTITY(1,1) PRIMARY KEY,
	img_name varchar(50),
	room_id int,
	FOREIGN KEY (room_id) REFERENCES Rooms(room_id) ON DELETE CASCADE
);
go
CREATE TABLE service_rooms(
	id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	room_id INT NOT NULL,
	service_id INT NOT NULL,
	FOREIGN KEY (room_id) REFERENCES Rooms(room_id) ON DELETE CASCADE,
	FOREIGN KEY (service_id) REFERENCES [Services](service_id) ON DELETE CASCADE
);
go
-- Bảng Thanh toán
CREATE TABLE Payments (
    payment_id INT IDENTITY(1,1) PRIMARY KEY,			    -- ID duy nhất cho thanh toán
    payment_date DATE,                      -- Ngày thanh toán
    total_amount DECIMAL(10, 2),			-- Tổng tiền thanh toán
    payment_method NVARCHAR(50),            -- Phương thức thanh toán
);
go

-- Bảng Phân quyền
CREATE TABLE Roles(
	role_id nvarchar (10) PRIMARY KEY NOT NULL,
	[Name] nvarchar(50) NOT NULL,
);
go
-- Bảng Người dùng
CREATE TABLE Users (
    username NVARCHAR(50),				-- Tên người dùng
    email VARCHAR(100),                -- Địa chỉ email của người dùng
	cmt VARCHAR(20) PRIMARY KEY NOT NULL,		-- Chứng minh thư của người dùng
    [password] VARCHAR(100),			-- Mật khẩu (được lưu dưới dạng mã hóa)
    phone_number VARCHAR(20),          -- Số điện thoại của người dùng
	Token nvarchar(50),	
	Changedpass bit,

);

CREATE TABLE Authorities(
	Id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	cmt varchar(20) NOT NULL,
	role_id nvarchar(10) NOT NULL,
	FOREIGN KEY (role_id) REFERENCES Roles(role_id) ON DELETE CASCADE,
	FOREIGN KEY (Cmt) REFERENCES Users(cmt) ON DELETE CASCADE
);
go

--Bảng feedback
CREATE TABLE Feedback (
    feed_back_id INT IDENTITY(1,1) PRIMARY KEY,			 -- ID duy nhất cho đánh giá
    description nvarchar(255),               -- Mô tả về đánh giá
	room_id INT,
	cmt VARCHAR(20),
	FOREIGN KEY (room_id) REFERENCES Rooms(room_id) ON DELETE CASCADE,
	FOREIGN KEY (cmt) REFERENCES Users(cmt) ON DELETE CASCADE
);
go

-- Bảng Đặt phòng
CREATE TABLE Bookings (
    booking_id INT IDENTITY(1,1) PRIMARY KEY,             -- ID duy nhất cho đặt phòng
    cmt VARCHAR(20),                        -- Khóa ngoại liên kết với bảng Người dùng
	 payment_status bit,                     -- Trạng thái thanh toán
	booking_date DATE,                      -- Ngày đặt phòng
    checkin_date DATE,                      -- Ngày nhận phòng
    checkout_date DATE,                     -- Ngày trả phòng
	payment_id INT,						    -- ID duy nhất cho thanh toán
    FOREIGN KEY (cmt) REFERENCES Users(cmt) ON DELETE CASCADE,
	FOREIGN KEY (payment_id) REFERENCES Payments(payment_id) ON DELETE CASCADE,
	
);
go


--Bảng Hóa đơn
CREATE TABLE Booking_room (	
	bookingroom_id INT IDENTITY(1,1) primary key,
	booking_id INT,							-- ID duy nhất cho đặt phòng 
	room_id INT,			                -- ID duy nhất cho phòng	
	[Count] INT,							-- Số lượng phòng
	hotel_id INT,							--ID của khách sạn
    FOREIGN KEY (room_id) REFERENCES Rooms(room_id) ON DELETE CASCADE,
	FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id) ON DELETE CASCADE,
	FOREIGN KEY (hotel_id) REFERENCES Hotels(hotel_id) 
);
go

-- Tạo bảng blog
CREATE TABLE blogs (
    id INT IDENTITY(1,1) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
	description VARCHAR(MAX) NOT NULL,
    author VARCHAR(100),
<<<<<<< HEAD
	image_name varchar(50) NOT NULL,
);
=======
	image_id INT NOT NULL,
	FOREIGN KEY (image_id) REFERENCES Images(image_id) ON DELETE CASCADE
);
>>>>>>> 3c1a9a0af692b48d06954bd8ae0497cd835f9891
