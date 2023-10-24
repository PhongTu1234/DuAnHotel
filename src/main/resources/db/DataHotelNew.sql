USE [master]
go
CREATE DATABASE DataHotel_new
go
USE DataHotel_new
go

--Bảng địa điểm
CREATE TABLE Place (
    place_id INT PRIMARY KEY,		--ID duy nhất cho địa điểm thuê
	place_name NVARCHAR(100),		-- Tên địa điểm thuê
	created_at BIGINT,				--Thời gian tạo
	updated_at BIGINT,				--Thời gian chỉnh sửa
);
go

--Bảng Banner
CREATE TABLE Images (
    image_id INT PRIMARY KEY,              -- ID duy nhất cho banner
	Url nvarchar(Max),						-- Link banner
	created_at BIGINT,						--Thời gian tạo
	updated_at BIGINT,						--Thời gian chỉnh sửa
);
go

--Bảng dịch vụ
CREATE TABLE [Services] (
	service_id INT PRIMARY KEY,
	service_name nvarchar(50),
	image_id INT,
	created_at BIGINT,	
	updated_at BIGINT,	
	FOREIGN KEY (image_id) REFERENCES Images(image_id),			
);
						 
--Loại khách sạn
CREATE TABLE HOTELTYPES (
    hotel_type_id INT PRIMARY KEY NOT NULL,				--ID duy nhất cho loại khách sạn
	hotel_level Float NOT NULL,							--Mức độ khách sạn
	created_at BIGINT,									--Thời gian tạo
	updated_at BIGINT,									--Thời gian chỉnh sửa
);
go



-- Bảng Khách sạn
CREATE TABLE Hotels (
    hotel_id INT PRIMARY KEY,               -- ID duy nhất cho khách sạn
    hotel_name NVARCHAR(100) NOT NULL,      -- Tên khách sạn
    address NVARCHAR(200),                  -- Địa chỉ của khách sạn
    phone_number VARCHAR(20),               -- Số điện thoại liên hệ của khách sạn
	Email_hotel varchar(50),				--Email của khách sạn
    description TEXT,                       -- Mô tả về khách sạn
    amenities TEXT,                         -- Tiện nghi tại khách sạn
    rating FLOAT,							-- Điểm đánh giá của khách sạn
	place_id INT,							--Địa điểm thuê khách sạn
	hotel_type_id INT,						--Mức độ khách sạn
	image_id INT,							--ID banner cho loại phòng
	--time_sale_id int,						--ID thời gian sale
	created_at BIGINT,						--Thời gian tạo
	updated_at BIGINT,						--Thời gian chỉnh sửa
	FOREIGN KEY (place_id) REFERENCES Place(place_id),
	FOREIGN KEY (Hotel_type_id) REFERENCES HotelTypes(Hotel_type_id),
	FOREIGN KEY (image_id) REFERENCES Images(image_id)
);
go


-- Bảng Loại Phòng
CREATE TABLE RoomTypes (
    room_type_id INT PRIMARY KEY,           -- ID duy nhất cho loại phòng
    room_type_name NVARCHAR(50) NOT NULL,   -- Tên loại phòng (ví dụ: Standard, Deluxe, Suite)
    price DECIMAL(10, 2),					-- Giá tiền của loại phòng
	created_at BIGINT,						--Thời gian tạo
	updated_at BIGINT,						--Thời gian chỉnh sửa
);
go
-- Bảng Phòng
CREATE TABLE Rooms (
    room_id INT PRIMARY KEY,                -- ID duy nhất cho phòng
    hotel_id INT NOT NULL,                           -- Khóa ngoại liên kết với bảng Khách sạn
    room_type_id INT,                       -- Khóa ngoại liên kết với bảng Loại Phòng
    room_number VARCHAR(10),                -- Số phòng
    status nvarchar(50),                    -- Trạng thái phòng (đã đặt, trống)
    description TEXT,                       -- Mô tả về phòng
	image_id INT,							--ID banner cho loại phòng
	service_id NVARCHAR(50),				--ID dịch vụ
	created_at BIGINT,						--Thời gian tạo
	updated_at BIGINT,						--Thời gian chỉnh sửa
    FOREIGN KEY (room_type_id) REFERENCES RoomTypes(room_type_id),
	FOREIGN KEY (hotel_id) REFERENCES Hotels(hotel_id),
	FOREIGN KEY (image_id) REFERENCES Images(image_id)
);
go


-- Bảng Thanh toán
CREATE TABLE Payments (
    payment_id INT PRIMARY KEY,			    -- ID duy nhất cho thanh toán
    payment_date DATE,                      -- Ngày thanh toán
    total_amount DECIMAL(10, 2),			-- Tổng tiền thanh toán
    payment_method NVARCHAR(50),            -- Phương thức thanh toán
	created_at BIGINT,						--Thời gian tạo
	updated_at BIGINT,						--Thời gian chỉnh sửa
);
go

-- Bảng Phân quyền
CREATE TABLE Roles(
	Id nvarchar (10) PRIMARY KEY NOT NULL,
	[Name] nvarchar(50) NOT NULL,
)
go
-- Bảng Người dùng
CREATE TABLE Users (
    username VARCHAR(50) NOT NULL,          -- Tên người dùng
    email VARCHAR(100),                     -- Địa chỉ email của người dùng
	cmt VARCHAR(20) PRIMARY KEY,			-- Chứng minh thư của người dùng
    [password] VARCHAR(100),                -- Mật khẩu (được lưu dưới dạng mã hóa)
    phone_number VARCHAR(20),               -- Số điện thoại của người dùng
	Token nvarchar(50) NOT NULL,
	created_at BIGINT,						--Thời gian tạo
	updated_at BIGINT,						--Thời gian chỉnh sửa
);

CREATE TABLE Authorities(
	Id int PRIMARY KEY NOT NULL,
	Cmt varchar(20) NOT NULL,
	RoleId nvarchar(10) NOT NULL,
	FOREIGN KEY (RoleId) REFERENCES Roles(Id),
	FOREIGN KEY (Cmt) REFERENCES Users(cmt)
)
go
-- Bảng Đặt phòng
CREATE TABLE Bookings (
    booking_id INT PRIMARY KEY,             -- ID duy nhất cho đặt phòng
    CMT VARCHAR(20),                            -- Khóa ngoại liên kết với bảng Người dùng
    booking_date DATE,                      -- Ngày đặt phòng
    checkin_date DATE,                      -- Ngày nhận phòng
    checkout_date DATE,                     -- Ngày trả phòng
    payment_status bit,                     -- Trạng thái thanh toán
    total_price DECIMAL(10, 2),             -- Tổng giá đặt phòng
	created_at BIGINT,						--Thời gian tạo
	updated_at BIGINT,						--Thời gian chỉnh sửa
	payment_id INT,						    -- ID duy nhất cho thanh toán
	hotel_id INT,							--ID của khách sạn
    FOREIGN KEY (CMT) REFERENCES Users(CMT),
	FOREIGN KEY (payment_id) REFERENCES Payments(payment_id),
	FOREIGN KEY (hotel_id) REFERENCES Hotels(hotel_id)
);
go


--Bảng Hóa đơn
CREATE TABLE Booking_room (	
	booking_id INT,							-- ID duy nhất cho đặt phòng
	room_id INT,			                -- ID duy nhất cho phòng					    
	created_at BIGINT,						--Thời gian tạo
	updated_at BIGINT,						--Thời gian chỉnh sửa
	Primary key (room_id, booking_id),
    FOREIGN KEY (room_id) REFERENCES Rooms(room_id),
	FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id)
);
go

-- Tạo bảng blog
CREATE TABLE blogs (
    id INT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    short_description VARCHAR(255) NOT NULL,
    author VARCHAR(100),
    created_at BIGINT,
	updated_at BIGINT,
	image_id INT NOT NULL,
	FOREIGN KEY (image_id) REFERENCES Images(image_id)
);

insert into Place values (1, N'Đà Lạt', 1694272166, 1694272231),
						 (2, N'Nha Trang', 1694272166, 1694272231),
						 (3, N'Phan Thiết', 1694272166, 1694272231),
						 (4, N'Vũng Tàu', 1694272166, 1694272231),
						 (5, N'Phú Quốc', 1694272166, 1694272231),
						 (6, N'Huế', 1694272166, 1694272231),
						 (7, N'Đà Nẵng', 1694272166, 1694272231),
						 (8, N'Sapa', 1694272166, 1694272231),
						 (9, N'Ninh Bình', 1694272166, 1694272231),
						 (10, N'Hạ Long', 1694272166, 1694272231)

insert into Images values   (1, 'https://maciek-design.com/wp-content/uploads/2019/03/banner-cua-mot-khach-san-dang-tai-tren-website.jpg', 1694272166, 1694272231),
							(2, 'https://maciek-design.com/wp-content/uploads/2019/03/banner-cua-mot-khach-san-dang-tai-tren-website.jpg', 1694272166, 1694272231),
							(3, 'https://maciek-design.com/wp-content/uploads/2019/03/banner-cua-mot-khach-san-dang-tai-tren-website.jpg', 1694272166, 1694272231),
							(4, 'https://maciek-design.com/wp-content/uploads/2019/03/banner-cua-mot-khach-san-dang-tai-tren-website.jpg', 1694272166, 1694272231),
							(5, 'https://maciek-design.com/wp-content/uploads/2019/03/banner-cua-mot-khach-san-dang-tai-tren-website.jpg', 1694272166, 1694272231)

insert into [Services] values (1, N'Wifi-Free', 1, 1694272166, 1694272231),
							  (2, N'Sercurity', 4, 1694272166, 1694272231),
							  (3, N'Washing', 3, 1694272166, 1694272231),
							  (4, N'Bar', 5, 1694272166, 1694272231),
							  (5, N'Spa', 2, 1694272166, 1694272231)

insert into HOTELTYPES values   (1, 1.2, 1694272166, 1694272231),
								(2, 1.7, 1694272166, 1694272231),
								(3, 2.2, 1694272166, 1694272231),
								(4, 3.0, 1694272166, 1694272231),
								(5, 4.9, 1694272166, 1694272231),
								(6, 4.0, 1694272166, 1694272231),
								(7, 3.6, 1694272166, 1694272231),
								(8, 4.4, 1694272166, 1694272231),
								(9, 5.0, 1694272166, 1694272231),
								(10, 3.8, 1694272166, 1694272231)

insert into Hotels values   (1, 'Thuy Van Hotel', N'10 Quang Trung phường 1 thành phố Đà Lạt', '012-345-6789', 'tuannhps21725@gmail.com', N'Sang Trong, đẹp đẽ', N'Free wifi, security', 4.5, 1, 2, 1, 1694272166, 1694272231),
							(2, 'Grand Hotel', '123 Main Street, City', '123-456-7890', 'hoangvu07102003@gmail.com', 'A luxurious hotel in the heart of the city', 'Spa, Gym, Restaurant', 4.5, 1, 2, 3, 1694272166, 1694272231),
							(3, 'Seaside Resort', '456 Beach Road, Coastal Town', '987-654-3210', 'lethingocnga770@gmail.com', 'A relaxing resort by the sea', 'Private Beach, Pool, Water Sports', 4.2, 2, 1, 1, 1694272166, 1694272231),
							(4, 'Mountain Lodge', '789 Mountain View, Hillside', '555-123-4567', 'tuannhps21725@gmail.com', 'Cozy lodge nestled in the mountains', 'Fireplace, Hiking Trails', 4.0, 1,  1, 3, 1694272166, 1694272231),
							(5, 'Urban Oasis', '100 Downtown Avenue, City', '555-987-6543', 'tuannhps21725@gmail.com', 'Modern hotel with city views', 'Rooftop Bar, Fitness Center', 4.3, 1, 4, 1,	1694272166, 1694272231),
							(6, 'Lakeview Retreat', '200 Lakeside Drive, Countryside', '888-555-1234', 'tuannhps21725@gmail.com', 'Tranquil retreat with lakeside views', 'Fishing, Kayaking, Spa', 4.7, 9, 1, 1,	1694272166, 1694272231),
							(7, 'Historic Inn', '300 Old Town Street, Village', '555-789-4561', 'tuannhps21725@gmail.com', 'Charming inn in a historic village', 'Antique Decor, Tea Room', 4.1, 1, 1, 4, 1694272166, 1694272231),
							(8, 'Tropical Paradise Resort', '789 Palm Beach, Tropical Island', '123-555-7890', 'tuannhps21725@gmail.com', 'Exotic resort surrounded by palm trees', 'Beachfront, Poolside Cabanas', 4.6, 1, 1, 1, 1694272166, 1694272231),
							(9, 'Alpine Chalet', '400 Snowpeak Road, Mountains', '555-333-7777', 'tuannhps21725@gmail.com', 'Cozy chalet in the alpine mountains', 'Skiing, Fireplace, Sauna', 4.4, 1, 1, 5, 1694272166, 1694272231),
							(10, 'Luxury Suites', '500 Highrise Avenue, City', '555-888-2222', 'tuannhps21725@gmail.com', 'Elegant suites with city skyline views', 'Spa, Gourmet Dining', 4.8, 10, 10, 1,	1694272166, 1694272231),
							(11, 'Countryside Farmstay', '600 Farm Road, Countryside', '555-222-4444', 'tuannhps21725@gmail.com', 'Experience farm life in the countryside', 'Farm Tours, Fresh Produce', 4.2, 9, 10, 1, 1694272166, 1694272231),
							(12, 'Sunset Beach Resort', '700 Sunset Boulevard, Coastal Town', '555-444-6666', 'tuannhps21725@gmail.com', 'Picturesque resort with stunning sunsets', 'Beachfront, Beach Bar', 4.9, 9, 8, 2,	1694272166, 1694272231),
							(13, 'Rustic Cabins', '800 Woodsy Lane, Forest', '555-666-8888', 'tuannhps21725@gmail.com', 'Rustic cabins nestled in the forest', 'Nature Trails, Campfire', 3.9, 7, 7, 5,	1694272166, 1694272231),
							(14, 'City Center Suites', '900 Central Avenue, City', '555-777-5555', 'tuannhps21725@gmail.com', 'Modern suites in the heart of downtown', 'Business Center, Rooftop Pool', 4.5, 6, 6, 3,	1694272166, 1694272231),
							(15, 'Coastal Clifftop Villas', '1000 Clifftop Road, Coastal Town', '555-111-3333', 'tuannhps21725@gmail.com', 'Villas with breathtaking coastal views', 'Clifftop Pool, Seafood Restaurant', 4.7, 10, 1, 1,	1694272166, 1694272231),
							(16, 'Historic Mansion', '1100 Heritage Street, Historic District', '555-999-1111', 'tuannhps21725@gmail.com', 'Stay in a luxurious historic mansion', 'Antique Furnishings, Garden', 4.6, 7, 3, 4,	1694272166, 1694272231),
							(17, 'Mountain Adventure Lodge', '1200 Adventure Road, Mountains', '555-222-4444', 'tuannhps21725@gmail.com', 'Lodge for outdoor enthusiasts', 'Hiking, Rock Climbing', 4.2, 7, 7, 5, 1694272166, 1694272231),
							(18, 'Desert Oasis Resort', '1300 Oasis Boulevard, Desert', '555-444-6666', 'tuannhps21725@gmail.com', 'Resort in the middle of the desert', 'Swimming Pool, Desert Safari', 4.3, 4, 7, 3, 1694272166, 1694272231),
							(19, 'Riverfront Retreat', '1400 River Road, Riverside', '555-666-8888', 'tuannhps21725@gmail.com', 'Relax by the river at this peaceful retreat', 'Fishing, Riverside Dining', 4.1, 3, 7, 4, 1694272166, 1694272231),
							(20, 'Modern Art Hotel', '1500 Art Street, Artsy District', '555-777-5555', 'tuannhps21725@gmail.com', 'Hotel with a creative and artsy vibe', 'Art Gallery, Art-Themed Rooms', 4.4, 4, 3, 1, 1694272166, 1694272231),
							(21, 'Ski Lodge', '1600 Snowy Peaks Road, Ski Resort', '555-888-2222', 'tuannhps21725@gmail.com', 'Ideal lodge for skiers and snowboarders', 'Ski Rentals, Après-Ski Bar', 4.0, 3, 4, 3, 1694272166, 1694272231),
							(22, 'Tranquil Hideaway', '1700 Serene Lane, Countryside', '555-222-4444', 'tuannhps21725@gmail.com', 'Escape to a peaceful hideaway', 'Nature Walks, Meditation', 4.6, 7, 4, 3, 1694272166, 1694272231),
							(23, 'Mediterranean Paradise', '1900 Oceanfront Road, Coastal Town', '555-666-8888', 'tuannhps21725@gmail.com', 'Experience the beauty of the Mediterranean', 'Infinity Pool, Beachfront Dining', 4.7, 2, 2, 2, 1694272166, 1694272231),
							(24, 'Rustic Lakeside Cabins', '2000 Lakeside Lane, Lake District', '555-888-2222', 'tuannhps21725@gmail.com', 'Quaint cabins by the lakeside', 'Fishing, Lakeside Campfire', 4.1, 2, 3, 4, 1694272166, 1694272231),
							(25, 'Historic Riverside Inn', '2100 Riverside Street, Historic District', '555-111-3333', 'tuannhps21725@gmail.com', 'Stay in a charming riverside inn', 'Riverside Garden, Vintage Decor', 4.5, 3, 4, 5, 1694272166, 1694272231),
							(26, 'Golf Resort', '2200 Fairway Drive, Golf Course', '555-999-1111', 'tuannhps21725@gmail.com', 'Resort with stunning golf course views', '18-Hole Golf Course, Golf Lessons', 4.8, 4, 5, 5, 1694272166, 1694272231),
							(27, 'Zen Retreat', '2300 Zen Garden Road, Tranquil Village', '555-222-4444', 'tuannhps21725@gmail.com', 'Find peace and relaxation in this zen retreat', 'Meditation Classes, Yoga Studio', 4.4, 5, 6, 4,	1694272166, 1694272231),
							(28, 'Tropical Jungle Resort', '2400 Jungle Avenue, Rainforest', '555-444-6666', 'tuannhps21725@gmail.com', 'Immerse yourself in the tropical rainforest', 'Jungle Treks, Canopy Walk', 4.2, 6, 7, 1,	1694272166, 1694272231),
							(29, 'Country Cottage Inn', '2500 Cottage Road, Countryside', '555-666-8888', 'tuannhps21725@gmail.com', 'Quaint country cottages for a cozy stay', 'Homemade Breakfast, Country Views', 4.0, 7, 8, 3, 1694272166, 1694272231),
							(30, 'Skyline Suites', '2600 Skyline Tower, City', '555-888-2222', 'tuannhps21725@gmail.com', 'Luxurious suites with stunning skyline views', 'Sky Lounge, Rooftop Pool', 4.9, 1, 1, 1,	1694272166, 1694272231),
							(31, 'Eco-Friendly Retreat', '2700 Eco Lane, Nature Reserve', '555-111-3333', 'tuannhps21725@gmail.com', 'Stay in harmony with nature at this eco-retreat', 'Solar Power, Organic Garden', 4.5, 5, 4, 3, 1694272166, 1694272231),
							(32, 'Harborfront Hotel', '2800 Harbor Drive, Waterfront', '555-999-1111', 'tuannhps21725@gmail.com', 'Hotel with picturesque harbor views', 'Waterfront Dining, Marina', 4.3, 4, 3, 2,	1694272166, 1694272231),
							(33, 'Countryside Vineyard Estate', '2900 Vineyard Road, Wine Country', '555-222-4444', 'tuannhps21725@gmail.com', 'Experience the charm of a vineyard estate', 'Wine Tasting, Vineyard Tours', 4.6, 3, 2, 1, 1694272166, 1694272231),
							(34, 'Modern Urban Apartments', '3000 Urban Street, City', '555-444-6666', 'tuannhps21725@gmail.com', 'Contemporary apartments in the heart of the city', 'Modern Amenities, Urban Living', 4.1, 1, 3, 5, 1694272166, 1694272231),
							(35, 'Serenity Spa Retreat', '3100 Spa Lane, Tranquil Oasis', '555-666-8888', 'tuannhps21725@gmail.com', 'Relax and rejuvenate at this spa retreat', 'Spa Treatments, Meditation Garden', 4.7, 2, 3, 5, 1694272166, 1694272231),
							(36, 'Savannah Colonial Inn', '3200 Colonial Avenue, Historic District', '555-888-2222', 'tuannhps21725@gmail.com', 'Stay in a colonial-style inn in historic Savannah', 'Southern Hospitality, Colonial Decor', 4.2, 2, 3, 4, 1694272166, 1694272231),
							(37, 'Beachfront Bungalows', '3300 Beachfront Road, Coastal Paradise', '555-111-3333', 'tuannhps21725@gmail.com', 'Bungalows right on the sandy beach', 'Beachfront Hammocks, Sea Views', 4.8, 2, 4, 5, 1694272166, 1694272231),
							(38, 'Mystical Mountain Lodge', '3400 Enchanted Road, Mystical Mountains', '555-999-1111', 'tuannhps21725@gmail.com', 'Lodge surrounded by the magic of the mountains', 'Crystal Shop, Mystical Workshops', 4.4, 1, 1, 2, 1694272166, 1694272231),
							(39, 'City Skyline Hotel', '3500 Skyscraper Avenue, City', '555-222-4444', 'tuannhps21725@gmail.com', 'Hotel with stunning views of the city skyline', 'Skyline Restaurant, Sky Lounge', 4.3, 5, 1, 1,	1694272166, 1694272231),
							(40, 'Elegant Manor House', '3600 Manor Road, Countryside', '555-444-6666', 'tuannhps21725@gmail.com', 'Elegant manor house in the countryside', 'Manicured Gardens, High Tea', 4.5, 4, 1, 3, 1694272166, 1694272231);


insert into RoomTypes values (1, N'Standard', 100.00, 1694272166, 1694272231),
							 (2, 'Beachfront', 220.00, 1694272166, 1694272231),
							 (3, 'Deluxe', 150.00, 1694272166, 1694272231),
							 (4, 'Suite', 250.00, 1694272166, 1694272231),
							 (5, 'Family', 180.00, 1694272166, 1694272231),
							 (6, 'Executive', 200.00, 1694272166, 1694272231),
							 (7, 'Bungalow', 180.00, 1694272166, 1694272231),
							 (8, 'Penthouse', 350.00, 1694272166, 1694272231),
							 (9, 'Villa', 300.00, 1694272166, 1694272231),
							 (10, 'Cottage', 130.00, 1694272166, 1694272231)
	
insert into Rooms values(1, 1, 1, '101', N'Trống',	    N'Phòng rộng rãi, thoáng mát',			    1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(2, 1, 2, '102', N'Đang thuê',	 'Deluxe room with king-sized bed',	   	    2, N'2, 3, 4, 5', 1694272166, 1694272231),
						(3, 1, 3, '103', N'Trống',		 'Luxurious suite with living area',        3, N'1, 3, 4, 5', 1694272166, 1694272231),
						(4, 2, 2, '201', N'Đang thuê',   'Spacious deluxe room with ocean view',    4, N'1, 2, 4, 5', 1694272166, 1694272231),
						(5, 2, 4, '202', N'Trống',       'Family room with two double beds',        5, N'1, 2, 3, 5', 1694272166, 1694272231),
						(6, 2, 6, '203', N'Đang thuê',   'Private bungalow near the beach',         4, N'1, 2, 3, 4', 1694272166, 1694272231),
						(7, 3, 1, '301', N'Trống',       'Standard room with mountain view',        3, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(8, 3, 3, '302', N'Đang thuê',   'Luxury suite with fireplace',             2, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(9, 3, 5, '303', N'Trống',       'Executive room with spa access',          1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(10, 4, 2, '401', N'Đã đặt',     'Deluxe room with city skyline view',      5, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(11, 4, 7, '402', N'Đang thuê',  'Penthouse suite with panoramic city view',5, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(12, 4, 8, '403', N'Trống',      'Spacious villa with garden',              1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(13, 5, 1, '501', N'Đang thuê',  'Standard room with lake view',            3, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(14, 5, 4, '502', N'Trống',      'Family room with lakeside view',          4, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(15, 5, 10, '503', N'Đang thuê', 'Beachfront room with private balcony',    5, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(16, 6, 1, '101A', N'Trống', 'Cozy standard room with vintage decor',       2, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(17, 6, 5, '102A', N'Đang thuê', 'Executive room with antique furnishings', 4, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(18, 6, 9, '103A', N'Trống', 'Charming cottage-style room',                 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(19, 7, 3, '201A', N'Đang thuê', 'Luxurious suite with ocean view',         4, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(20, 7, 6, '202A', N'Đã đặt', 'Private bungalow steps from the beach',      5, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(21, 8, 2, '301A', N'Trống', 'Deluxe room with mountain view',              2, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(22, 8, 4, '302A', N'Đang thuê', 'Family room with fireplace',              3, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(23, 8, 8, '303A', N'Trống', 'Spacious villa with panoramic views',         1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(24, 9, 1, '401A', N'Đang thuê', 'Standard room with city view',            5, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(25, 9, 3, '402A', N'Trống', 'Luxury suite with modern amenities',          2, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(26, 9, 10, '403A', N'Đang thuê', 'Beachfront room with stunning sunset views', 4, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(27, 10, 2, '501A', N'Trống', 'Deluxe room with garden view', 3, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(28, 10, 5, '502A', N'Đang thuê', 'Executive room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(29, 10, 9, '503A', N'Trống', 'Quaint cottage room surrounded by nature', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(30, 4, 2, '301A', N'Đã đặt', 'Deluxe room with mountain view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(31, 4, 6, '302A', N'Đang thuê', 'Private bungalow near the beach', 2, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(32, 4, 8, '303A', N'Trống', 'Spacious villa with garden', 4, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(33, 5, 1, '401A', N'Đang thuê', 'Standard room with city view', 5, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(34, 5, 3, '402A', N'Trống', 'Luxury suite with modern amenities', 3, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(35, 5, 10, '403A', N'Đang thuê', 'Beachfront room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(36, 6, 2, '101B', N'Trống', 'Deluxe room with ocean view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(37, 6, 5, '102B', N'Đang thuê', 'Executive room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(38, 6, 9, '103B', N'Trống', 'Quaint cottage room surrounded by nature', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(39, 7, 3, '201B', N'Đang thuê', 'Luxurious suite with ocean view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(40, 7, 6, '202B', N'Đã đặt', 'Private bungalow steps from the beach', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(41, 7, 10, '203B', N'Trống', 'Beachfront room with stunning sunset views', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(42, 8, 1, '301B', N'Đang thuê', 'Standard room with city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(43, 8, 4, '302B', N'Trống', 'Family room with lakeside view', 3, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(44, 8, 10, '303B', N'Đang thuê', 'Beachfront room with private balcony', 5, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(45, 9, 2, '401B', N'Trống', 'Deluxe room with city skyline view', 4, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(46, 9, 7, '402B', N'Đang thuê', 'Penthouse suite with panoramic city view', 3, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(47, 9, 8, '403B', N'Trống', 'Spacious villa with private garden', 2, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(48, 10, 1, '501B', N'Đang thuê', 'Standard room with city view', 5, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(49, 10, 3, '502B', N'Trống', 'Luxury suite with modern amenities', 5, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(50, 10, 10, '503B', N'Đã đặt', 'Beachfront room with private balcony', 4, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(51, 11, 2, '101C', N'Đang thuê', 'Deluxe room with garden view', 5, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(52, 11, 5, '102C', N'Trống', 'Executive room with private balcony', 2, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(53, 11, 9, '103C', N'Đang thuê', 'Quaint cottage room surrounded by nature', 3, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(54, 12, 3, '201C', N'Trống', 'Luxurious suite with ocean view', 3, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(55, 12, 6, '202C', N'Đang thuê', 'Private bungalow steps from the beach', 2, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(56, 12, 10, '203C', N'Trống', 'Beachfront room with stunning sunset views', 5, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(57, 13, 1, '301C', N'Đang thuê', 'Standard room with city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(58, 13, 4, '302C', N'Trống', 'Family room with lakeside view', 2, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(59, 13, 10, '303C', N'Đang thuê', 'Beachfront room with private balcony', 2, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(60, 14, 2, '401C', N'Đã đặt', 'Deluxe room with city skyline view', 2, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(61, 14, 7, '402C', N'Trống', 'Penthouse suite with panoramic city view', 4, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(62, 14, 8, '403C', N'Đang thuê', 'Spacious villa with private garden', 5, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(63, 15, 1, '501C', N'Trống', 'Standard room with city view', 4, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(64, 15, 3, '502C', N'Đang thuê', 'Luxury suite with modern amenities', 3, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(65, 15, 10, '503C', N'Trống', 'Beachfront room with private balcony', 3, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(66, 16, 2, '101D', N'Đang thuê', 'Deluxe room with garden view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(67, 16, 5, '102D', N'Trống', 'Executive room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(68, 16, 9, '103D', N'Đang thuê', 'Quaint cottage room surrounded by nature', 2, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(69, 17, 3, '201D', N'Trống', 'Luxurious suite with ocean view', 4, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(70, 17, 6, '202D', N'Đã đặt', 'Private bungalow steps from the beach', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(71, 17, 10, '203D', N'Đang thuê', 'Beachfront room with stunning sunset views', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(72, 18, 1, '301D', N'Trống', 'Standard room with city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(73, 18, 4, '302D', N'Đang thuê', 'Family room with lakeside view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(74, 18, 10, '303D', N'Trống', 'Beachfront room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(75, 19, 2, '401D', N'Đang thuê', 'Deluxe room with city skyline view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(76, 19, 7, '402D', N'Trống', 'Penthouse suite with panoramic city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(77, 19, 8, '403D', N'Đang thuê', 'Spacious villa with private garden', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(78, 20, 1, '501D', N'Trống', 'Standard room with city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(79, 20, 3, '502D', N'Đang thuê', 'Luxury suite with modern amenities', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(80, 20, 10, '503D', N'Đã đặt', 'Beachfront room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(81, 21, 2, '101E', N'Trống', 'Deluxe room with garden view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(82, 21, 5, '102E', N'Đang thuê', 'Executive room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(83, 21, 9, '103E', N'Trống', 'Quaint cottage room surrounded by nature', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(84, 22, 3, '201E', N'Đang thuê', 'Luxurious suite with ocean view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(85, 22, 6, '202E', N'Trống', 'Private bungalow steps from the beach', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(86, 22, 10, '203E', N'Đang thuê', 'Beachfront room with stunning sunset views', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(87, 23, 1, '301E', N'Trống', 'Standard room with city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(88, 23, 4, '302E', N'Đang thuê', 'Family room with lakeside view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(89, 23, 10, '303E', N'Trống', 'Beachfront room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(90, 24, 2, '401E', N'Đã đặt', 'Deluxe room with city skyline view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(91, 24, 7, '402E', N'Trống', 'Penthouse suite with panoramic city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(92, 24, 8, '403E', N'Đang thuê', 'Spacious villa with private garden', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(93, 25, 1, '501E', N'Trống', 'Standard room with city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(94, 25, 3, '502E', N'Đang thuê', 'Luxury suite with modern amenities', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(95, 25, 10, '503E', N'Trống', 'Beachfront room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(96, 26, 2, '101F', N'Đang thuê', 'Deluxe room with garden view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(97, 26, 5, '102F', N'Trống', 'Executive room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(98, 26, 9, '103F', N'Đang thuê', 'Quaint cottage room surrounded by nature', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(99, 27, 3, '201F', N'Trống', 'Luxurious suite with ocean view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(100, 27, 6, '202F', N'Đã đặt', 'Private bungalow steps from the beach', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(101, 27, 10, '203F', N'Đang thuê', 'Beachfront room with stunning sunset views', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(102, 28, 1, '301F', N'Trống', 'Standard room with city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(103, 28, 4, '302F', N'Đang thuê', 'Family room with lakeside view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(104, 28, 10, '303F', N'Trống', 'Beachfront room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(105, 29, 2, '401F', N'Đang thuê', 'Deluxe room with city skyline view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(106, 29, 7, '402F', N'Trống', 'Penthouse suite with panoramic city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(107, 29, 8, '403F', N'Đang thuê', 'Spacious villa with private garden', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(108, 30, 1, '501F', N'Trống', 'Standard room with city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(109, 30, 3, '502F', N'Đang thuê', 'Luxury suite with modern amenities', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(110, 30, 10, '503F', N'Đã đặt', 'Beachfront room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(111, 11, 5, '110B', N'Trống', 'Executive room with spa access', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(112, 12, 2, '201B', N'Đang thuê', 'Deluxe room with ocean view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(113, 12, 6, '202B', N'Trống', 'Private bungalow steps from the beach', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(114, 12, 10, '203B', N'Đang thuê', 'Beachfront room with stunning sunset views', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(115, 13, 1, '301B', N'Trống', 'Standard room with city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(116, 13, 4, '302B', N'Đang thuê', 'Family room with lakeside view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(117, 13, 10, '303B', N'Trống', 'Beachfront room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(118, 14, 2, '401B', N'Đang thuê', 'Deluxe room with city skyline view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(119, 14, 7, '402B', N'Trống', 'Penthouse suite with panoramic city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(120, 14, 8, '403B', N'Đã đặt', 'Spacious villa with private garden', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(121, 15, 1, '501B', N'Đang thuê', 'Standard room with city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(122, 15, 3, '502B', N'Trống', 'Luxury suite with modern amenities', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(123, 15, 10, '503B', N'Đang thuê', 'Beachfront room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(124, 16, 2, '101C', N'Trống', 'Deluxe room with garden view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(125, 16, 5, '102C', N'Đang thuê', 'Executive room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(126, 16, 9, '103C', N'Trống', 'Quaint cottage room surrounded by nature', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(127, 17, 3, '201C', N'Đang thuê', 'Luxurious suite with ocean view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(128, 17, 6, '202C', N'Trống', 'Private bungalow steps from the beach', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(129, 17, 10, '203C', N'Đang thuê', 'Beachfront room with stunning sunset views', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(130, 18, 1, '301C', N'Đã đặt', 'Standard room with city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(131, 18, 4, '302C', N'Trống', 'Family room with lakeside view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(132, 18, 10, '303C', N'Đang thuê', 'Beachfront room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(133, 19, 2, '401C', N'Trống', 'Deluxe room with city skyline view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(134, 19, 7, '402C', N'Đang thuê', 'Penthouse suite with panoramic city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(135, 19, 8, '403C', N'Trống', 'Spacious villa with private garden', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(136, 20, 1, '501C', N'Đang thuê', 'Standard room with city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(137, 20, 3, '502C', N'Trống', 'Luxury suite with modern amenities', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(138, 20, 10, '503C', N'Đang thuê', 'Beachfront room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(139, 28, 9, '104E', N'Trống', 'Quaint cottage room surrounded by nature', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(140, 29, 3, '202E', N'Đã đặt', 'Luxurious suite with ocean view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(141, 29, 6, '203E', N'Trống', 'Private bungalow steps from the beach', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(142, 29, 10, '204E', N'Đang thuê', 'Beachfront room with stunning sunset views', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(143, 30, 1, '302E', N'Trống', 'Standard room with city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(144, 30, 4, '303E', N'Đang thuê', 'Family room with lakeside view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(145, 30, 10, '304E', N'Trống', 'Beachfront room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(146, 31, 2, '102F', N'Đang thuê', 'Deluxe room with city skyline view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(147, 31, 7, '103F', N'Trống', 'Penthouse suite with panoramic city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(148, 31, 8, '104F', N'Đang thuê', 'Spacious villa with private garden', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(149, 32, 1, '202F', N'Trống', 'Standard room with city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(150, 32, 3, '203F', N'Đã đặt', 'Luxury suite with modern amenities', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(151, 32, 10, '204F', N'Đang thuê', 'Beachfront room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(152, 33, 2, '302F', N'Trống', 'Deluxe room with garden view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(153, 33, 5, '303F', N'Đang thuê', 'Executive room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(154, 33, 9, '304F', N'Trống', 'Quaint cottage room surrounded by nature', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(155, 34, 3, '102A', N'Đang thuê', 'Luxurious suite with ocean view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(156, 34, 6, '103A', N'Trống', 'Private bungalow steps from the beach', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(157, 34, 10, '104A', N'Đang thuê', 'Beachfront room with stunning sunset views', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(158, 35, 1, '202A', N'Trống', 'Standard room with city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(159, 35, 4, '203A', N'Đang thuê', 'Family room with lakeside view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(160, 35, 10, '204A', N'Đã đặt', 'Beachfront room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(161, 36, 2, '302A', N'Trống', 'Deluxe room with city skyline view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(162, 36, 7, '303A', N'Đang thuê', 'Penthouse suite with panoramic city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(163, 36, 8, '304A', N'Trống', 'Spacious villa with private garden', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(164, 37, 1, '102B', N'Đang thuê', 'Standard room with city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(165, 37, 3, '103B', N'Trống', 'Luxury suite with modern amenities', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(166, 37, 10, '104B', N'Đang thuê', 'Beachfront room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(167, 38, 2, '202B', N'Trống', 'Deluxe room with garden view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(168, 38, 5, '203B', N'Đang thuê', 'Executive room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(169, 38, 9, '204B', N'Trống', 'Quaint cottage room surrounded by nature', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(170, 39, 3, '302B', N'Đã đặt', 'Luxurious suite with ocean view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(171, 16, 1, '101B', N'Trống', 'Cozy standard room with forest view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(172, 16, 6, '102B', N'Đang thuê', 'Private bungalow surrounded by nature', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(173, 17, 2, '201B', N'Trống', 'Deluxe room with mountain view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(174, 17, 4, '202B', N'Đang thuê', 'Family room with fireplace', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(175, 17, 8, '203B', N'Trống', 'Spacious villa with panoramic views', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(176, 18, 1, '301B', N'Đang thuê', 'Standard room with river view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(177, 18, 3, '302B', N'Trống', 'Luxury suite with modern amenities', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(178, 18, 10, '303B', N'Đang thuê', 'Beachfront room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(179, 19, 2, '401B', N'Trống', 'Deluxe room with garden view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(180, 19, 5, '402B', N'Đã đặt', 'Executive room with private balcony', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(181, 19, 9, '403B', N'Đang thuê', 'Quaint cottage room surrounded by nature', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(182, 20, 1, '501B', N'Trống', 'Standard room with city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(183, 20, 4, '502B', N'Đang thuê', 'Family room with lakeside view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(184, 20, 10, '503B', N'Trống', 'Beachfront room with stunning sunset views', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(185, 20, 9, '301D', N'Đang thuê', 'Quaint cottage room surrounded by nature', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(186, 20, 10, '302D', N'Trống', 'Beachfront room with stunning sunset views', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(187, 21, 1, '401D', N'Đang thuê', 'Standard room with city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(188, 21, 2, '402D', N'Trống', 'Deluxe room with king-sized bed', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(189, 21, 4, '403D', N'Đang thuê', 'Family room with two double beds', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(190, 22, 2, '101E', N'Đã đặt', 'Spacious deluxe room with ocean view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(191, 22, 6, '102E', N'Đang thuê', 'Private bungalow near the beach', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(192, 22, 8, '103E', N'Trống', 'Spacious villa with garden', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(193, 23, 1, '201E', N'Đang thuê', 'Standard room with mountain view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(194, 23, 3, '202E', N'Trống', 'Luxury suite with fireplace', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(195, 23, 5, '203E', N'Đang thuê', 'Executive room with spa access', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(196, 24, 2, '301E', N'Trống', 'Deluxe room with city skyline view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(197, 24, 7, '302E', N'Đang thuê', 'Penthouse suite with panoramic city view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(198, 24, 9, '303E', N'Trống', 'Charming cottage-style room', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(199, 25, 3, '401E', N'Đang thuê', 'Luxurious suite with ocean view', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231),
						(200, 25, 6, '402E', N'Đã đặt', 'Private bungalow steps from the beach', 1, N'1, 2, 3, 4, 5', 1694272166, 1694272231);
insert into Payments values (1, '2023-09-05', 500.00, 'credit_card', 1694272166, 1694272231),
							(2, '2023-09-06', 600.00, 'paypal', 1694272166, 1694272231),
							(3, '2023-09-07', 700.00, 'credit_card', 1694272166, 1694272231),
							(4, '2023-09-08', 800.00, 'paypal', 1694272166, 1694272231),
							(5, '2023-09-09', 900.00, 'credit_card', 1694272166, 1694272231),
							(6, '2023-09-10', 1000.00, 'paypal', 1694272166, 1694272231),
							(7, '2023-09-11', 1100.00, 'credit_card', 1694272166, 1694272231),
							(8, '2023-09-12', 1200.00, 'paypal', 1694272166, 1694272231),
							(9, '2023-09-13', 1300.00, 'credit_card', 1694272166, 1694272231),
							(10, '2023-09-14', 1400.00, 'paypal', 1694272166, 1694272231),
							(11, '2023-09-15', 1500.00, 'credit_card', 1694272166, 1694272231),
							(12, '2023-09-16', 1600.00, 'paypal', 1694272166, 1694272231),
							(13, '2023-09-17', 1700.00, 'credit_card', 1694272166, 1694272231),
							(14, '2023-09-18', 1800.00, 'paypal', 1694272166, 1694272231),
							(15, '2023-09-19', 1900.00, 'credit_card', 1694272166, 1694272231),
							(16, '2023-09-20', 2000.00, 'paypal', 1694272166, 1694272231),
							(17, '2023-09-21', 2100.00, 'credit_card', 1694272166, 1694272231),
							(18, '2023-09-22', 2200.00, 'paypal', 1694272166, 1694272231),
							(19, '2023-09-23', 2300.00, 'credit_card', 1694272166, 1694272231),
							(20, '2023-09-24', 2400.00, 'paypal', 1694272166, 1694272231),
							(21, '2023-09-25', 2500.00, 'credit_card', 1694272166, 1694272231),
							(22, '2023-09-26', 2600.00, 'paypal', 1694272166, 1694272231),
							(23, '2023-09-27', 2700.00, 'credit_card', 1694272166, 1694272231),
							(24, '2023-09-28', 2800.00, 'paypal', 1694272166, 1694272231),
							(25, '2023-09-29', 2900.00, 'credit_card', 1694272166, 1694272231),
							(26, '2023-09-30', 3000.00, 'paypal', 1694272166, 1694272231),
							(27, '2023-10-01', 3100.00, 'credit_card', 1694272166, 1694272231),
							(28, '2023-10-02', 3200.00, 'paypal', 1694272166, 1694272231),
							(29, '2023-10-03', 3300.00, 'credit_card', 1694272166, 1694272231),
							(30, '2023-10-04', 3400.00, 'paypal', 1694272166, 1694272231),
							(31, '2023-10-05', 3500.00, 'credit_card', 1694272166, 1694272231),
							(32, '2023-10-06', 3600.00, 'paypal', 1694272166, 1694272231),
							(33, '2023-10-07', 3700.00, 'credit_card', 1694272166, 1694272231),
							(34, '2023-10-08', 3800.00, 'paypal', 1694272166, 1694272231),
							(35, '2023-10-09', 3900.00, 'credit_card', 1694272166, 1694272231),
							(36, '2023-10-10', 4000.00, 'paypal', 1694272166, 1694272231),
							(37, '2023-10-11', 4100.00, 'credit_card', 1694272166, 1694272231),
							(38, '2023-10-12', 4200.00, 'paypal', 1694272166, 1694272231),
							(39, '2023-10-13', 4300.00, 'credit_card', 1694272166, 1694272231),
							(40, '2023-10-14', 4400.00, 'paypal', 1694272166, 1694272231),
							(41, '2023-10-15', 4500.00, 'credit_card', 1694272166, 1694272231),
							(42, '2023-10-16', 4600.00, 'paypal', 1694272166, 1694272231),
							(43, '2023-10-17', 4700.00, 'credit_card', 1694272166, 1694272231),
							(44, '2023-10-18', 4800.00, 'paypal', 1694272166, 1694272231),
							(45, '2023-10-19', 4900.00, 'credit_card', 1694272166, 1694272231),
							(46, '2023-10-20', 5000.00, 'paypal', 1694272166, 1694272231),
							(47, '2023-10-21', 5100.00, 'credit_card', 1694272166, 1694272231),
							(48, '2023-10-22', 5200.00, 'paypal', 1694272166, 1694272231),
							(49, '2023-10-23', 5300.00, 'credit_card', 1694272166, 1694272231),
							(50, '2023-10-24', 5400.00, 'paypal', 1694272166, 1694272231);
insert into Roles values	(N'CUST', N'Customers'),
							(N'STAF', N'Staffs'),
							(N'DIRE', N'Directors');

insert into Users values('user0', 'user1@example.com', '081456789370', 'hashed_password_1', '1234567890', N'token', 1694272166, 1694272231),
						('user1', 'user1@example.com', '081456789375', 'hashed_password_1', '1234567890', N'token', 1694272166, 1694272231),
						('user2', 'user2@example.com', '012456789375', 'hashed_password_2', '2345678901', N'token', 1694272166, 1694272231),
						('user3', 'user3@example.com', '083456789375', 'hashed_password_3', '3456789012', N'token', 1694272166, 1694272231),
						('user4', 'user4@example.com', '084456789375', 'hashed_password_4', '4567890123', N'token', 1694272166, 1694272231),
						('user5', 'user5@example.com', '085456789375', 'hashed_password_5', '5678901234', N'token', 1694272166, 1694272231),
						('user6', 'user6@example.com', '086456789375', 'hashed_password_6', '6789012345', N'token', 1694272166, 1694272231),
						('user7', 'user7@example.com', '087456789375', 'hashed_password_7', '7890123456', N'token', 1694272166, 1694272231),
						('user8', 'user8@example.com', '088456789375', 'hashed_password_8', '8901234567', N'token', 1694272166, 1694272231),
						('user9', 'user9@example.com', '089456789375', 'hashed_password_9', '9012345678', N'token', 1694272166, 1694272231),
						('user10', 'user10@example.com', '082156789375', 'hashed_password_10', '0123456789', N'token', 1694272166, 1694272231),
						('user11', 'user11@example.com', '082436789375', 'hashed_password_11', '1234567890', N'token', 1694272166, 1694272231),
						('user12', 'user12@example.com', '082446789375', 'hashed_password_12', '2345678901', N'token', 1694272166, 1694272231),
						('user13', 'user13@example.com', '032456789375', 'hashed_password_13', '3456789012', N'token', 1694272166, 1694272231),
						('user14', 'user14@example.com', '082466789375', 'hashed_password_14', '4567890123', N'token', 1694272166, 1694272231),
						('user15', 'user15@example.com', '082476789375', 'hashed_password_15', '5678901234', N'token', 1694272166, 1694272231),
						('user16', 'user16@example.com', '082486789375', 'hashed_password_16', '6789012345', N'token', 1694272166, 1694272231),
						('user17', 'user17@example.com', '082496789375', 'hashed_password_17', '7890123456', N'token', 1694272166, 1694272231),
						('user18', 'user18@example.com', '082451789375', 'hashed_password_18', '8901234567', N'token', 1694272166, 1694272231),
						('user19', 'user19@example.com', '082452789375', 'hashed_password_19', '9012345678', N'token', 1694272166, 1694272231),
						('user20', 'user20@example.com', '082453789375', 'hashed_password_20', '0123456789', N'token', 1694272166, 1694272231),
						('user21', 'user21@example.com', '082454789375', 'hashed_password_21', '1234567890', N'token', 1694272166, 1694272231),
						('user22', 'user22@example.com', '082455789375', 'hashed_password_22', '2345678901', N'token', 1694272166, 1694272231),
						('user23', 'user23@example.com', '042456789375', 'hashed_password_23', '3456789012', N'token', 1694272166, 1694272231),
						('user24', 'user24@example.com', '082457789375', 'hashed_password_24', '4567890123', N'token', 1694272166, 1694272231),
						('user25', 'user25@example.com', '082458789375', 'hashed_password_25', '5678901234', N'token', 1694272166, 1694272231),
						('user26', 'user26@example.com', '082459789375', 'hashed_password_26', '6789012345', N'token', 1694272166, 1694272231),
						('user27', 'user27@example.com', '082456189375', 'hashed_password_27', '7890123456', N'token', 1694272166, 1694272231),
						('user28', 'user28@example.com', '082456289375', 'hashed_password_28', '8901234567', N'token', 1694272166, 1694272231),
						('user29', 'user29@example.com', '082456389375', 'hashed_password_29', '9012345678', N'token', 1694272166, 1694272231),
						('user30', 'user30@example.com', '082456489375', 'hashed_password_30', '0123456789', N'token', 1694272166, 1694272231),
						('user31', 'user31@example.com', '082456589375', 'hashed_password_31', '1234567890', N'token', 1694272166, 1694272231),
						('user32', 'user32@example.com', '082456689375', 'hashed_password_32', '2345678901', N'token', 1694272166, 1694272231),
						('user33', 'user33@example.com', '052456789375', 'hashed_password_33', '3456789012', N'token', 1694272166, 1694272231),
						('user34', 'user34@example.com', '082456889375', 'hashed_password_34', '4567890123', N'token', 1694272166, 1694272231),
						('user35', 'user35@example.com', '082456989375', 'hashed_password_35', '5678901234', N'token', 1694272166, 1694272231),
						('user36', 'user36@example.com', '082456719375', 'hashed_password_36', '6789012345', N'token', 1694272166, 1694272231),
						('user37', 'user37@example.com', '082456729375', 'hashed_password_37', '7890123456', N'token', 1694272166, 1694272231),
						('user38', 'user38@example.com', '082456739375', 'hashed_password_38', '8901234567', N'token', 1694272166, 1694272231),
						('user39', 'user39@example.com', '082456749375', 'hashed_password_39', '9012345678', N'token', 1694272166, 1694272231),
						('user40', 'user40@example.com', '082456759375', 'hashed_password_40', '0123456789', N'token', 1694272166, 1694272231),
						('user41', 'user41@example.com', '082456769375', 'hashed_password_41', '1234567890', N'token', 1694272166, 1694272231),
						('user42', 'user42@example.com', '082456779375', 'hashed_password_42', '2345678901', N'token', 1694272166, 1694272231),
						('user43', 'user43@example.com', '062456789375', 'hashed_password_43', '3456789012', N'token', 1694272166, 1694272231),
						('user44', 'user44@example.com', '082456799375', 'hashed_password_44', '4567890123', N'token', 1694272166, 1694272231),
						('user45', 'user45@example.com', '082456781375', 'hashed_password_45', '5678901234', N'token', 1694272166, 1694272231),
						('user46', 'user46@example.com', '082456782375', 'hashed_password_46', '6789012345', N'token', 1694272166, 1694272231),
						('user47', 'user47@example.com', '082456783375', 'hashed_password_47', '7890123456', N'token', 1694272166, 1694272231),
						('user48', 'user48@example.com', '082456784375', 'hashed_password_48', '8901234567', N'token', 1694272166, 1694272231),
						('user49', 'user49@example.com', '082456785375', 'hashed_password_49', '9012345678', N'token', 1694272166, 1694272231),
						('user50', 'user50@example.com', '082456786375', 'hashed_password_50', '0123456789', N'token', 1694272166, 1694272231),
						('user51', 'user51@example.com', '082456787375', 'hashed_password_51', '1234567890', N'token', 1694272166, 1694272231),
						('user52', 'user52@example.com', '082456788375', 'hashed_password_52', '2345678901', N'token', 1694272166, 1694272231),
						('user53', 'user53@example.com', '072456789375', 'hashed_password_53', '3456789012', N'token', 1694272166, 1694272231),
						('user54', 'user54@example.com', '082456789175', 'hashed_password_54', '4567890123', N'token', 1694272166, 1694272231),
						('user55', 'user55@example.com', '082456789275', 'hashed_password_55', '5678901234', N'token', 1694272166, 1694272231),
						('user56', 'user56@example.com', '092456789375', 'hashed_password_56', '6789012345', N'token', 1694272166, 1694272231),
						('user57', 'user57@example.com', '082456789475', 'hashed_password_57', '7890123456', N'token', 1694272166, 1694272231),
						('user58', 'user58@example.com', '082456789575', 'hashed_password_58', '8901234567', N'token', 1694272166, 1694272231),
						('user59', 'user59@example.com', '082456789675', 'hashed_password_59', '9012345678', N'token', 1694272166, 1694272231),
						('user60', 'user60@example.com', '082456789775', 'hashed_password_60', '0123456789', N'token', 1694272166, 1694272231),
						('user61', 'user61@example.com', '082456789875', 'hashed_password_61', '1234567890', N'token', 1694272166, 1694272231),
						('user62', 'user62@example.com', '082456789975', 'hashed_password_62', '2345678901', N'token', 1694272166, 1694272231),
						('user63', 'user63@example.com', '082456789315', 'hashed_password_63', '3456789012', N'token', 1694272166, 1694272231),
						('user64', 'user64@example.com', '082456789325', 'hashed_password_64', '4567890123', N'token', 1694272166, 1694272231),
						('user65', 'user65@example.com', '082456789335', 'hashed_password_65', '5678901234', N'token', 1694272166, 1694272231),
						('user66', 'user66@example.com', '082456789345', 'hashed_password_66', '6789012345', N'token', 1694272166, 1694272231),
						('user67', 'user67@example.com', '082456789355', 'hashed_password_67', '7890123456', N'token', 1694272166, 1694272231),
						('user68', 'user68@example.com', '082456789365', 'hashed_password_68', '8901234567', N'token', 1694272166, 1694272231),
						('user69', 'user69@example.com', '082456789385', 'hashed_password_69', '9012345678', N'token', 1694272166, 1694272231),
						('user70', 'user70@example.com', '082456789395', 'hashed_password_70', '0123456789', N'token', 1694272166, 1694272231),
						('admin1', 'admin1@example.com', '082256789375', 'hashed_admin_password_1', '1111111111', N'token', 1694272166, 1694272231),
						('admin2', 'admin2@example.com', '082356789375', 'hashed_admin_password_2', '2222222222', N'token', 1694272166, 1694272231),
						('admin3', 'admin3@example.com', '022456789375', 'hashed_admin_password_3', '3333333333', N'token', 1694272166, 1694272231),
						('admin4', 'admin4@example.com', '082556789375', 'hashed_admin_password_4', '4444444444', N'token', 1694272166, 1694272231),
						('admin5', 'admin5@example.com', '082656789375', 'hashed_admin_password_5', '5555555555', N'token', 1694272166, 1694272231),
						('admin6', 'admin6@example.com', '082756789375', 'hashed_admin_password_6', '6666666666', N'token', 1694272166, 1694272231),
						('admin7', 'admin7@example.com', '082856789375', 'hashed_admin_password_7', '7777777777', N'token', 1694272166, 1694272231),
						('admin8', 'admin8@example.com', '082956789375', 'hashed_admin_password_8', '8888888888', N'token', 1694272166, 1694272231),
						('admin9', 'admin9@example.com', '082416789375', 'hashed_admin_password_9', '9999999999', N'token', 1694272166, 1694272231),
						('admin10', 'admin10@example.com', '082426789375', 'hashed_admin_password_10', '0000000000', N'token', 1694272166, 1694272231);
INSERT INTO Authorities VALUES					(1, N'081456789370', N'CUST'),
												(2, N'012456789375', N'CUST'),
												(3, N'083456789375', N'CUST'),
												(4, N'084456789375', N'CUST'),
												(5, N'085456789375', N'CUST'),
												(6, N'086456789375', N'CUST'),
												(7, N'087456789375', N'CUST'),
												(8, N'088456789375', N'CUST'),
												(9, N'089456789375', N'CUST'),
												(10, N'082156789375', N'CUST'),
												(11, N'082436789375', N'CUST'),
												(12, N'082446789375', N'CUST'),
												(13, N'032456789375', N'CUST'),
												(14, N'082466789375', N'CUST'),
												(15, N'082476789375', N'CUST'),
												(16, N'082486789375', N'CUST'),
												(17, N'082496789375', N'CUST'),
												(18, N'082451789375', N'CUST'),
												(19, N'082452789375', N'CUST'),
												(20, N'082453789375', N'CUST'),
												(21, N'082454789375', N'CUST'),
												(22, N'082455789375', N'CUST'),
												(23, N'042456789375', N'CUST'),
												(24, N'082457789375', N'CUST'),
												(25, N'082458789375', N'CUST'),
												(26, N'082459789375', N'CUST'),
												(27, N'082456189375', N'CUST'),
												(28, N'082456289375', N'CUST'),
												(29, N'082456389375', N'CUST'),
												(30, N'082456489375', N'CUST'),
												(31, N'082456589375', N'CUST'),
												(32, N'082456689375', N'CUST'),
												(33, N'052456789375', N'CUST'),
												(34, N'082456889375', N'CUST'),
												(35, N'082456989375', N'CUST'),
												(36, N'082456719375', N'CUST'),
												(37, N'082456729375', N'CUST'),
												(38, N'082456739375', N'CUST'),
												(39, N'082456749375', N'CUST'),
												(40, N'082456759375', N'CUST'),
												(41, N'082456769375', N'CUST'),
												(42, N'082456779375', N'CUST'),
												(43, N'062456789375', N'CUST'),
												(44, N'082456799375', N'CUST'),
												(45, N'082456781375', N'CUST'),
												(46, N'082456782375', N'CUST'),
												(47, N'082456783375', N'CUST'),
												(48, N'082456784375', N'CUST'),
												(49, N'082456785375', N'CUST'),
												(50, N'082456786375', N'CUST'),
												(51, N'082456787375', N'CUST'),
												(52, N'082456788375', N'CUST'),
												(53, N'072456789375', N'CUST'),
												(54, N'082456789175', N'CUST'),
												(55, N'082456789275', N'CUST'),
												(56, N'092456789375', N'CUST'),
												(57, N'082456789475', N'CUST'),
												(58, N'082456789575', N'CUST'),
												(59, N'082456789675', N'CUST'),
												(60, N'082456789775', N'CUST'),
												(61, N'082456789875', N'CUST'),
												(62, N'082456789975', N'CUST'),
												(63, N'082456789315', N'CUST'),
												(64, N'082456789325', N'CUST'),
												(65, N'082456789335', N'CUST'),
												(66, N'082456789345', N'CUST'),
												(67, N'082456789355', N'CUST'),
												(68, N'082456789365', N'CUST'),
												(69, N'082456789385', N'CUST'),
												(70, N'082456789395', N'CUST'),
												(71, N'082256789375', N'STAF'),
												(72, N'082356789375', N'DIRE'),
												(73, N'022456789375', N'DIRE'),
												(74, N'082556789375', N'DIRE'),
												(75, N'082656789375', N'DIRE'),
												(76, N'082756789375', N'DIRE'),
												(77, N'082856789375', N'DIRE'),
												(78, N'082956789375', N'DIRE'),
												(79, N'082416789375', N'DIRE'),
												(80, N'082426789375', N'DIRE');
INSERT INTO Bookings (booking_id, CMT, payment_id, booking_date, checkin_date, checkout_date, payment_status, total_price, created_at, updated_at, hotel_id)
VALUES
					(1, '081456789375', 1, '2023-08-01', '2023-08-15', '2023-08-20', 1, 750.00, 1694272166, 1694272231,1),
					(2, '081456789375', 5, '2023-08-02', '2023-08-10', '2023-08-15', 1, 500.00, 1694272166, 1694272231, 4),
					(3, '081456789375', 10, '2023-08-05', '2023-08-12', '2023-08-18', 0, 850.00, 1694272166, 1694272231,7),
					(4, '081456789375', 20, '2023-08-07', '2023-08-25', '2023-08-30', 1, 1200.00, 1694272166, 1694272231,6),
					(5, '081456789375', 25, '2023-08-10', '2023-08-20', '2023-08-25', 1, 600.00, 1694272166, 1694272231,8),
					(6, '081456789375', 30, '2023-08-12', '2023-08-22', '2023-08-27', 0, 750.00, 1694272166, 1694272231,7),
					(7, '081456789375', 35, '2023-08-15', '2023-08-18', '2023-08-23', 1, 450.00, 1694272166, 1694272231,9),
					(8, '081456789375', 40, '2023-08-17', '2023-08-19', '2023-08-24', 1, 300.00, 1694272166, 1694272231,5),
					(9, '081456789375', 45, '2023-08-20', '2023-08-30', '2023-09-05', 0, 700.00, 1694272166, 1694272231, 1),
					(10, '081456789375', 50, '2023-08-22', '2023-08-28', '2023-09-02', 1, 550.00, 1694272166, 1694272231, 2),
					(11, '081456789375', 5, '2023-08-25', '2023-09-01', '2023-09-06', 1, 600.00, 1694272166, 1694272231, 4),
					(12, '081456789375', 6, '2023-08-27', '2023-09-05', '2023-09-10', 0, 850.00, 1694272166, 1694272231, 9),
					(13, '081456789375', 11, '2023-08-30', '2023-09-08', '2023-09-13', 1, 750.00, 1694272166, 1694272231, 6),
					(14, '081456789375', 7, '2023-09-02', '2023-09-10', '2023-09-15', 1, 500.00, 1694272166, 1694272231, 7),
					(15, '081456789375', 12, '2023-09-05', '2023-09-15', '2023-09-20', 0, 900.00, 1694272166, 1694272231, 4),
					(16, '081456789375', 8, '2023-09-07', '2023-09-12', '2023-09-17', 1, 650.00, 1694272166, 1694272231, 3),
					(17, '081456789375', 13, '2023-09-10', '2023-09-18', '2023-09-23', 1, 550.00, 1694272166, 1694272231, 1),
					(18, '081456789375', 9, '2023-09-12', '2023-09-20', '2023-09-25', 0, 750.00, 1694272166, 1694272231, 3),
					(19, '081456789375', 14, '2023-09-15', '2023-09-22', '2023-09-27', 1, 600.00, 1694272166, 1694272231, 6),
					(20, '081456789375', 10, '2023-09-18', '2023-09-25', '2023-09-30', 1, 700.00, 1694272166, 1694272231, 9),
					(21, '081456789375', 15, '2023-09-20', '2023-09-28', '2023-10-03', 0, 800.00, 1694272166, 1694272231, 8),
					(22, '081456789375', 2, '2023-09-23', '2023-09-30', '2023-10-05', 1, 750.00, 1694272166, 1694272231, 24),
					(23, '081456789375', 7, '2023-09-25', '2023-10-02', '2023-10-07', 1, 550.00, 1694272166, 1694272231, 16),
					(24, '081456789375', 3, '2023-09-28', '2023-10-05', '2023-10-10', 0, 600.00, 1694272166, 1694272231, 39),
					(25, '081456789375', 8, '2023-09-30', '2023-10-08', '2023-10-13', 1, 700.00, 1694272166, 1694272231, 40),
					(26, '081456789375', 4, '2023-10-03', '2023-10-10', '2023-10-15', 1, 800.00, 1694272166, 1694272231, 14),
					(27, '081456789375', 9, '2023-10-05', '2023-10-12', '2023-10-17', 0, 750.00, 1694272166, 1694272231, 13),
					(28, '081456789375', 40, '2023-10-08', '2023-10-15', '2023-10-20', 1, 600.00, 1694272166, 1694272231, 17),
					(29, '081456789375', 19, '2023-10-10', '2023-10-18', '2023-10-23', 1, 650.00, 1694272166, 1694272231, 37),
					(30, '081456789375', 47, '2023-10-13', '2023-10-20', '2023-10-25', 0, 700.00, 1694272166, 1694272231, 10),
					(31, '081456789375', 21, '2023-10-15', '2023-10-22', '2023-10-27', 1, 800.00, 1694272166, 1694272231, 35),
					(32, '081456789375', 16, '2023-10-18', '2023-10-25', '2023-10-30', 1, 750.00, 1694272166, 1694272231, 36),
					(33, '081456789375', 33, '2023-10-20', '2023-10-28', '2023-11-02', 0, 600.00, 1694272166, 1694272231, 28),
					(34, '081456789375', 28, '2023-10-23', '2023-10-30', '2023-11-04', 1, 650.00, 1694272166, 1694272231, 26),
					(35, '081456789375', 42, '2023-10-25', '2023-11-01', '2023-11-06', 1, 700.00, 1694272166, 1694272231, 11),
					(36, '081456789375', 48, '2023-10-28', '2023-11-05', '2023-11-10', 0, 800.00, 1694272166, 1694272231, 18),
					(37, '081456789375', 15, '2023-10-30', '2023-11-08', '2023-11-13', 1, 750.00, 1694272166, 1694272231, 21),
					(38, '081456789375', 23, '2023-11-02', '2023-11-10', '2023-11-15', 1, 600.00, 1694272166, 1694272231, 22),
					(39, '081456789375', 19, '2023-11-05', '2023-11-12', '2023-11-17', 0, 650.00, 1694272166, 1694272231, 23),
					(40, '081456789375', 44, '2023-11-07', '2023-11-15', '2023-11-20', 1, 700.00, 1694272166, 1694272231, 32),
					(41, '081456789375', 24, '2023-11-10', '2023-11-17', '2023-11-22', 1, 800.00, 1694272166, 1694272231, 31),
					(42, '081456789375', 37, '2023-11-12', '2023-11-20', '2023-11-25', 0, 750.00, 1694272166, 1694272231, 16),
					(43, '081456789375', 28, '2023-11-15', '2023-11-22', '2023-11-27', 1, 600.00, 1694272166, 1694272231, 14),
					(44, '081456789375', 19, '2023-11-17', '2023-11-25', '2023-11-30', 1, 650.00, 1694272166, 1694272231, 18),
					(45, '081456789375', 26, '2023-11-20', '2023-11-27', '2023-12-02', 0, 700.00, 1694272166, 1694272231, 35),
					(46, '081456789375', 39, '2023-11-22', '2023-11-30', '2023-12-05', 1, 800.00, 1694272166, 1694272231, 22),
					(47, '081456789375', 29, '2023-11-25', '2023-12-02', '2023-12-07', 1, 750.00, 1694272166, 1694272231, 30),
					(48, '081456789375', 23, '2023-11-27', '2023-12-05', '2023-12-10', 0, 600.00, 1694272166, 1694272231, 20),
					(49, '081456789375', 22, '2023-11-30', '2023-12-07', '2023-12-12', 1, 650.00, 1694272166, 1694272231, 40),
					(50, '081456789375', 3, '2023-12-02', '2023-12-10', '2023-12-15', 1, 700.00, 1694272166, 1694272231, 26);
insert into Booking_room values (1, 10, 1694272166, 1694272231),
								(5, 20, 1694272166, 1694272231),
								(10, 30, 1694272166, 1694272231),
								(15, 40, 1694272166, 1694272231),
								(20, 50, 1694272166, 1694272231),
								(25, 60, 1694272166, 1694272231),
								(30, 70, 1694272166, 1694272231),
								(35, 80, 1694272166, 1694272231),
								(40, 90, 1694272166, 1694272231),
								(45, 110, 1694272166, 1694272231),
								(50, 120, 1694272166, 1694272231),
								(11, 130, 1694272166, 1694272231),
								(21, 140, 1694272166, 1694272231),
								(31, 150, 1694272166, 1694272231),
								(41, 160, 1694272166, 1694272231),
								(46, 170, 1694272166, 1694272231),
								(16, 180, 1694272166, 1694272231),
								(26, 190, 1694272166, 1694272231),
								(36, 200, 1694272166, 1694272231)

-- Thêm dữ liệu vào bảng blogs
INSERT INTO blogs (id, title, content, short_description, author, created_at, updated_at, image_id)
VALUES
(1, 'Bài viết 1', 'Nội dung bài viết 1', 'Mô tả ngắn 1', 'Tác giả 1', 1633900000, 1634000000, 1),
(2, 'Bài viết 2', 'Nội dung bài viết 2', 'Mô tả ngắn 2', 'Tác giả 2', 1633901000, 1634001000, 2),
(3, 'Bài viết 3', 'Nội dung bài viết 3', 'Mô tả ngắn 3', 'Tác giả 3', 1633902000, 1634002000, 3),
(4, 'Bài viết 4', 'Nội dung bài viết 4', 'Mô tả ngắn 4', 'Tác giả 4', 1633903000, 1634003000, 4),
(5, 'Bài viết 5', 'Nội dung bài viết 5', 'Mô tả ngắn 5', 'Tác giả 5', 1633904000, 1634004000, 5),
(6, 'Bài viết 6', 'Nội dung bài viết 6', 'Mô tả ngắn 6', 'Tác giả 6', 1633905000, 1634005000, 1),
(7, 'Bài viết 7', 'Nội dung bài viết 7', 'Mô tả ngắn 7', 'Tác giả 7', 1633906000, 1634006000, 2),
(8, 'Bài viết 8', 'Nội dung bài viết 8', 'Mô tả ngắn 8', 'Tác giả 8', 1633907000, 1634007000, 3),
(9, 'Bài viết 9', 'Nội dung bài viết 9', 'Mô tả ngắn 9', 'Tác giả 9', 1633908000, 1634008000, 4),
(10, 'Bài viết 10', 'Nội dung bài viết 10', 'Mô tả ngắn 10', 'Tác giả 10', 1633909000, 1634009000, 5);
insert into Hotels values  (253, N'HANZ Premium MaiVy Hotel', N' 150 Street 30/4, Xã Hiệp Tân, Hòa Thành, Tỉnh Tây Ninh', '0908360915', 'HANZpremiummaivyhotel@gmail.com', N'<h2>Giới thiệu HANZ Premium MaiVy Hotel</h2><br><h3>Vị trí</h3><br>
HANZ Premium MaiVy Hotel Tay Ninh là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Xã Hiệp Tân.
Không chỉ sở hữu vị trí đắc địa, HANZ Premium MaiVy Hotel Tay Ninh còn là một trong những khách sạn nằm cách Dong Pan Crossroads chưa đầy 28,91 km và Trade Center and Leisure Na Ca chưa đầy 2,8 km.
<h3>Thông tin về HANZ Premium MaiVy Hotel Tay Ninh</h3><br>Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
HANZ Premium MaiVy Hotel Tay Ninh là lựa chọn sáng suốt dành cho những du khách ghé thăm Xã Hiệp Tân.', 22, 1),
							(254, N'Ngọc An Hotel', N' Số 18 Đường Trường Hoà, ấp Trường Cửu, Xã Trường Hòa, Thị Xã Hòa Thành, Tỉnh Tây Ninh, Xã Trường Hòa, Hòa Thành, Tỉnh Tây Ninh', '0899795710', 'ngocanhotel@gmail.com', N' <h2>Giới thiệu Ngọc An Hotel</h2><br><h3>Vị trí</h3><br>
Lưu trú tại Ngoc An Hotel là một lựa chọn đúng đắn khi quý khách đến thăm Xã Trường Hòa.
khách sạn này rất dễ tìm bởi vị trí đắc địa, nằm gần với nhiều tiện ích công cộng.
<h3>Thông tin về Ngoc An Hotel</h3><br>Không chỉ sở hữu vị trí giúp quý khách dễ dàng ghé thăm những địa điểm lý thú trong chuyến hành trình, Ngoc An Hotel cũng sẽ mang đến cho quý khách trải nghiệm lưu trú mỹ mãn.
Ngoc An Hotel là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một khách sạn vừa thoải mái lại hợp túi tiền.
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Ngoc An Hotel sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Từ sự kiện doanh nghiệp đến họp mặt công ty, Ngoc An Hotel cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Ngoc An Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Ngoc An Hotel
Nếu dự định có một kỳ nghỉ dài, thì Ngoc An Hotel chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Ngoc An Hotel sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Ngoc An Hotel là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Ngoc An Hotel là lựa chọn đúng đắn cho những ai đang tìm kiếm các khách sạn hợp với túi tiền tại Xã Trường Hòa.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Lưu trú tại Ngoc An Hotel chắc chắn sẽ làm quý khách hài lòng bởi sự đón tiếp nhiệt thành và mức giá dễ chịu của khách sạn.', 22, 1),
							(255, N'Hoàng Mai Hotel', N'130 Nguyễn Hữu Thọ, Hiep Ninh Ward, Thành phố Tây Ninh, tỉnh Tây Ninh', '0903820406', 'hoangmaihotel@gmail.com', N'<h2>Giới thiệu Hoang Mai Hotel</h2><br><h3>Vị trí</h3><br>
Hoang Mai Hotel toạ lạc tại khu vực / thành phố Hiep Ninh Ward.
Có rất nhiều điểm tham quan lân cận như Tòa thánh Tây Ninh ở khoảng cách 2,48 km, và Tháp cổ Chót Mạt ở khoảng cách 19,78 km.
<h3>Thông tin về Hoang Mai Hotel</h3><br>
Không chỉ sở hữu vị trí giúp quý khách dễ dàng ghé thăm những địa điểm lý thú trong chuyến hành trình, Hoang Mai Hotel cũng sẽ mang đến cho quý khách trải nghiệm lưu trú mỹ mãn.
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Hoang Mai Hotel sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Hoang Mai Hotel
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Hoang Mai Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Hoang Mai Hotel là lựa chọn đúng đắn cho những ai đang tìm kiếm các khách sạn hợp với túi tiền tại Hiep Ninh Ward.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Hoang Mai Hotel là lựa chọn lý tưởng cho những ai đang tìm kiếm một phòng nghỉ thoải mái với giá thành hợp lý. ', 22, 2),
							(256, N'Sunrise Hotel', N' 81 Hoàng Lê Kha, Phường 3, Phường 3, Thành phố Tây Ninh, tỉnh Tây Ninh', '0909494077', 'sunrisehotel@gmail.com', N'<h2>Giới thiệu Sunrise Hotel Tay Ninh</h2><br><h3>Vị trí</h3><br>
Sunrise Hotel Tay Ninh toạ lạc tại khu vực / thành phố Phường 3.
Có rất nhiều điểm tham quan lân cận như Tòa thánh Tây Ninh ở khoảng cách 3,34 km, và Cu Chi Tunnels ở khoảng cách 43,03 km.
<h3>Thông tin về Sunrise Hotel Tay Ninh</h3><br>
Sunrise Hotel Tay Ninh là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.
Từ sự kiện doanh nghiệp đến họp mặt công ty, Sunrise Hotel Tay Ninh cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Sunrise Hotel Tay Ninh, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Sunrise Hotel Tay Ninh chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Sunrise Hotel Tay Ninh sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Sunrise Hotel Tay Ninh là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Dịch vụ tuyệt vời, cơ sở vật chất hoàn chỉnh và các tiện nghi khách sạn cung cấp sẽ khiến quý khách không thể phàn nàn trong suốt kỳ lưu trú tại Sunrise Hotel Tay Ninh.
Trung tâm thể dục của khách sạn là một trong những tiện nghi không thể bỏ qua khi lưu trú tại đây.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Sunrise Hotel Tay Ninh chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Sunrise Hotel Tay Ninh là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Với những tiện nghi sẵn có Sunrise Hotel Tay Ninh thực sự là một nơi lưu trú hoàn hảo.', 22, 2),
							(257, N'Lê Phan Hotel', N'Khu phố Thương mại Mai Anh 2, Đường Trường Chinh, Khu phố 4, Phường 3, Thành phố Tây Ninh, tỉnh Tây Ninh', '0902608029', 'lephanhotel@gmail.com', N'<h2>Giới thiệu Khách sạn Lê Phan Tây Ninh </h2><br><h3>Vị trí</h3><br>
Lưu trú tại Le Phan Hotel Tay Ninh là một lựa chọn đúng đắn khi quý khách đến thăm Phường 3.
khách sạn này rất dễ tìm bởi vị trí đắc địa, nằm gần với nhiều tiện ích công cộng.
<h3>Thông tin về Le Phan Hotel Tay Ninh</h3><br>Không chỉ sở hữu vị trí giúp quý khách dễ dàng ghé thăm những địa điểm lý thú trong chuyến hành trình, Le Phan Hotel Tay Ninh cũng sẽ mang đến cho quý khách trải nghiệm lưu trú mỹ mãn.
Le Phan Hotel Tay Ninh là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một khách sạn vừa thoải mái lại hợp túi tiền.
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Le Phan Hotel Tay Ninh sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Le Phan Hotel Tay Ninh
Le Phan Hotel Tay Ninh là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.
Khi lưu trú tại khách sạn thì nội thất và kiến trúc hẳn là hai yếu tố quan trọng khiến quý khách mãn nhãn. Với thiết kế độc đáo, Le Phan Hotel Tay Ninh mang đến không gian lưu trú làm hài lòng quý khách.
Từ sự kiện doanh nghiệp đến họp mặt công ty, Le Phan Hotel Tay Ninh cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Le Phan Hotel Tay Ninh, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Le Phan Hotel Tay Ninh chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Le Phan Hotel Tay Ninh sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Le Phan Hotel Tay Ninh là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Le Phan Hotel Tay Ninh là lựa chọn thông thái nhất cho những ai đang tìm kiếm một khách sạn với dịch vụ xuất sắc nhưng hợp với túi tiền.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Le Phan Hotel Tay Ninh là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Lưu trú tại Le Phan Hotel Tay Ninh chắc chắn sẽ làm quý khách hài lòng bởi sự đón tiếp nhiệt thành và mức giá dễ chịu của khách sạn.', 22, 3),
							(258, N'Hotel986', N'khu đô thị Mai Anh, phường 3, Trường chinh, thành phố Tây Ninh, tỉnh Tây Ninh', '0936844911', 'hotel986@gmail.com', N'<h2>Giới thiệu Hotel986</h2><br><h3>Vị trí</h3><br>
Hotel986 là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Phường 3.
Không chỉ sở hữu vị trí đắc địa, Hotel986 còn là một trong những khách sạn nằm cách Trade Center and Leisure Na Ca chưa đầy 2,98 km và Dong Pan Crossroads chưa đầy 26,37 km.
<h3>Thông tin về Hotel986</h3><br>

Không chỉ sở hữu vị trí giúp quý khách dễ dàng ghé thăm những địa điểm lý thú trong chuyến hành trình, Hotel986 cũng sẽ mang đến cho quý khách trải nghiệm lưu trú mỹ mãn.
Hotel986 là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một khách sạn vừa thoải mái lại hợp túi tiền.
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Hotel986 sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Hotel986
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Hotel986, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Hotel986 chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Hotel986 sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Hotel986 là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Hotel986 là lựa chọn thông thái nhất cho những ai đang tìm kiếm một khách sạn với dịch vụ xuất sắc nhưng hợp với túi tiền.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Hotel986 là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Hotel986 là lựa chọn sáng suốt dành cho những du khách ghé thăm Phường 3.', 22, 3),
							(259, N'Mai Tuấn Hotel', N'so 6 chanh mon a, Phuong 4, Phường 4, Thành phố Tây Ninh, Tỉnh Tây Ninh', '0902898607', 'maituanhotel@gmail.com', N'<h2>Giới Thiệu Mai Tuấn Hotel</h2><br><h3>Vị trí</h3><br>
Mai Tuan Hotel toạ lạc tại khu vực / thành phố Phường 4.
Có rất nhiều điểm tham quan lân cận như Chùa Thái Sơn ở khoảng cách 26,14 km, và Trade Center and Leisure Na Ca ở khoảng cách 0,67 km.
<h3>Thông tin về Mai Tuan Hotel</h3><br>
Dịch vụ tuyệt vời, cơ sở vật chất hoàn chỉnh và các tiện nghi khách sạn cung cấp sẽ khiến quý khách không thể phàn nàn trong suốt kỳ lưu trú tại Mai Tuan Hotel.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Mai Tuan Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Với những tiện nghi sẵn có Mai Tuan Hotel thực sự là một nơi lưu trú hoàn hảo.
 ', 22, 4),
							(260, N'ARINA Hotel', N' Khu Vincom Shophouse, Phường 3, Thành phố Tây Ninh, Tỉnh Tây Ninh', '0909963153', 'arinahotel@gmail.com', N'<h2>Giới Thiệu Arina Hotel</h2><br><h3>Vị trí</h3><br>
Lưu trú tại ARINA HOTEL là một lựa chọn đúng đắn khi quý khách đến thăm Phường 3.
khách sạn này rất dễ tìm bởi vị trí đắc địa, nằm gần với nhiều tiện ích công cộng.
<h3>Thông tin về ARINA HOTEL</h3><br>
Dịch vụ tuyệt vời, cơ sở vật chất hoàn chỉnh và các tiện nghi khách sạn cung cấp sẽ khiến quý khách không thể phàn nàn trong suốt kỳ lưu trú tại ARINA HOTEL.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Với những tiện nghi sẵn có ARINA HOTEL thực sự là một nơi lưu trú hoàn hảo.', 22, 4),
							(261, N'Victory Hotel', N' 255, đường 30/4, Phường 3, Thành phố Tây Ninh, Tỉnh Tây Ninh', '0939504668', 'victoryhotel@gmail.com', N'<h2>Giới Thiệu Victory Hotel Tay Ninh</h2><br><h3>Vị trí</h3><br>
Lưu trú tại Victory Hotel Tay Ninh là một lựa chọn đúng đắn khi quý khách đến thăm Phường 3.
khách sạn này rất dễ tìm bởi vị trí đắc địa, nằm gần với nhiều tiện ích công cộng.
Thông tin về Victory Hotel Tay Ninh</h3><br>
Dù quý khách muốn tổ chức một sự kiện hay các dịp kỷ niệm đặc biệt khác, Victory Hotel Tay Ninh là lựa chọn tuyệt vời cho quý khách với phòng chức năng rộng lớn, được trang bị đầy đủ để sẵn sàng đáp ứng mọi yêu cầu.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Victory Hotel Tay Ninh
Từ sự kiện doanh nghiệp đến họp mặt công ty, Victory Hotel Tay Ninh cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Victory Hotel Tay Ninh, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Victory Hotel Tay Ninh chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Victory Hotel Tay Ninh sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Victory Hotel Tay Ninh là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Victory Hotel Tay Ninh là lựa chọn thông thái nhất cho những ai đang tìm kiếm một khách sạn với dịch vụ xuất sắc nhưng hợp với túi tiền.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Victory Hotel Tay Ninh là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Lưu trú tại Victory Hotel Tay Ninh chắc chắn sẽ làm quý khách hài lòng bởi sự đón tiếp nhiệt thành và mức giá dễ chịu của khách sạn.', 22, 5),
							(262, N'Dynasty Casino Hotel', N' Số 574 Đường 30/4, Khu Phố 5, Phường 3, Thành phố Tây Ninh, Tỉnh Tây Ninh', '0901302343', 'dynastycasinohotel@gmail.com', N'<h2>Giới Thiệu Dynasty Casino Hotel</h2><br><h3>Vị trí</h3><br>
Lưu trú tại Dynasty Casino Hotel là một lựa chọn đúng đắn khi quý khách đến thăm Tỉnh Tây Ninh.
khách sạn này rất dễ tìm bởi vị trí đắc địa, nằm gần với nhiều tiện ích công cộng.
<h3>Thông tin về Dynasty Casino Hotel</h3><br>
Dịch vụ tuyệt vời, cơ sở vật chất hoàn chỉnh và các tiện nghi khách sạn cung cấp sẽ khiến quý khách không thể phàn nàn trong suốt kỳ lưu trú tại Dynasty Casino Hotel.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Dynasty Casino Hotel chỉ dành riêng cho quý khách.
Dynasty Casino Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Với những tiện nghi sẵn có Dynasty Casino Hotel thực sự là một nơi lưu trú hoàn hảo. ', 22, 5),
							(263, N'Melia Hotel', N' 90 Le Duẩn, Phường 3, Thành phố Tây Ninh, Tỉnh Tây Ninh', '0909066627', 'meliahotel@gmail.com', N'<h2>Giới Thiệu Melia Hotel</h2><br><h3>Vị trí</h3><br>
Melia Hotel Tay Ninh là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Phường 3.
Không chỉ sở hữu vị trí đắc địa, Melia Hotel Tay Ninh còn là một trong những khách sạn nằm cách Ngã tư Đồng Pan chưa đầy 26,95 km và Tay Ninh Holy See chưa đầy 3,78 km.
<h3>Thông tin về Melia Hotel Tay Ninh</h3><br>
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Melia Hotel Tay Ninh
Melia Hotel Tay Ninh là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.
Một trong những đặc điểm chính của khách sạn này là các liệu pháp spa đa dạng. Hãy nâng niu bản thân bằng các liệu pháp thư giãn, phục hồi giúp quý khách tươi trẻ thân, tâm.
Từ sự kiện doanh nghiệp đến họp mặt công ty, Melia Hotel Tay Ninh cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Nếu dự định có một kỳ nghỉ dài, thì Melia Hotel Tay Ninh chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Melia Hotel Tay Ninh sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Dịch vụ thượng hạng song hành với hàng loạt tiện nghi phong phú sẽ đem đến cho quý khách trải nghiệm của một kỳ nghỉ viên mãn nhất.
Hưởng thụ một ngày thư thái đầy thú vị tại hồ bơi dù quý khách đang du lịch một mình hay cùng người thân.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Melia Hotel Tay Ninh chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Melia Hotel Tay Ninh là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Hãy sẵn sàng đón nhận những giây phút vô giá khó phai trong suốt kỳ nghỉ của quý khách tại Melia Hotel Tay Ninh.', 22, 6),
							(264, N'Tama Hotel', N'10 Quang Trung phường 1 thành phố Đà Lạt, tỉnh Tây Ninh', '0918424651', 'tamahotel@gmail.com', N' <h2>Giới Thiệu Tama Hotel</h2><br><h3>Vị trí</h3><br>
Tama Hotel Tay Ninh là một nơi nghỉ nằm trong khu vực an ninh, toạ lạc tại Phường 3.
Không chỉ sở hữu vị trí đắc địa, Tama Hotel Tay Ninh còn là một trong những nơi nghỉ nằm cách Trade Center and Leisure Na Ca chưa đầy 3,32 km và Tòa thánh Tây Ninh chưa đầy 3,74 km.
<h3>Thông tin về Tama Hotel Tay Ninh</h3><br>
Tama Hotel Tay Ninh là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một nơi nghỉ vừa thoải mái lại hợp túi tiền.
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Tama Hotel Tay Ninh sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Tama Hotel Tay Ninh, một nơi nghỉ tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Du lịch một mình cũng không hề kém phần thú vị và Tama Hotel Tay Ninh là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Sóng WiFi phủ khắp các khu vực chung của nơi nghỉ cho phép quý khách luôn kết nối với gia đình và bè bạn.
Tama Hotel Tay Ninh là nơi nghỉ sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Tama Hotel Tay Ninh là lựa chọn sáng suốt dành cho những du khách ghé thăm Phường 3.', 22, 6),
							(265, N'Green Lotus Hotel', N'217 Lê Lợi, Phường 2, Thành phố Cao Lãnh, Tỉnh Đồng Tháp', '0932012161', 'greenlotushotel@gmail.com', N'<h2>Giới Thiệu Green Lotus Hotel</h2><br><h3>Vị trí</h3><br>
Green Lotus Hotel toạ lạc tại khu vực / thành phố Phường 2.
Có rất nhiều điểm tham quan lân cận như Ancient House of Huynh Thuy Le ở khoảng cách 22,79 km, và Binh Thuy Temple ở khoảng cách 44,39 km.
<h3>Thông tin về Green Lotus Hotel</h3><br>
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Green Lotus Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Green Lotus Hotel chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Green Lotus Hotel sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Green Lotus Hotel là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Green Lotus Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Green Lotus Hotel là lựa chọn lý tưởng cho những ai đang tìm kiếm một phòng nghỉ thoải mái với giá thành hợp lý.', 23, 1),
							(266, N'Trí Lê Hotel', N'PG3.05 - PG3.06, Vincom Shophouse Cao Lãnh, Đường 30 Tháng 4, Phường 1, Thành phố Cao Lãnh, Tỉnh Đồng Tháp', '0908888005', 'trilehotel@gmail.com', N'<h2>Giới thiệu Tri Le Hotel</h2><br><h3>Vị trí</h3><br>
Tri Le Hotel toạ lạc tại khu vực / thành phố Phường 1.
Có rất nhiều điểm tham quan lân cận như Sa Dec Flower Village Park ở khoảng cách 22,12 km, và Nam Phuong Linh Tu ở khoảng cách 12,18 km.
<h3>Thông tin về Tri Le Hotel</h3><br>
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Tri Le Hotel
Tri Le Hotel là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.
Bạn có phải là tín đồ mua sắm? Lưu trú tại Tri Le Hotel chắc chắn sẽ thoả mãn bạn với hàng loạt các trung tâm mua sắm kề cận.
Từ sự kiện doanh nghiệp đến họp mặt công ty, Tri Le Hotel cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Tri Le Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Tri Le Hotel là lựa chọn đúng đắn cho những ai đang tìm kiếm các khách sạn hợp với túi tiền tại Phường 1.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Tri Le Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Tri Le Hotel là lựa chọn lý tưởng cho những ai đang tìm kiếm một phòng nghỉ thoải mái với giá thành hợp lý.', 23, 1),
							(267, N'Van Kim Long Hotel', N'26 ĐT848, ấp Phú Long, xã Tân Phú ĐÔng, Xã Tân Phú Đông, Sa Đéc, Tỉnh Đồng Tháp', '0707242678', 'vankimlonghotel@gmail.com', N'<h2>Giới thiệu Van Kim Long Hotel</h2><br><h3>Vị trí</h3><br>
Van Kim Long Hotel là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Xã Tân Phú Đông.
Không chỉ sở hữu vị trí đắc địa, Van Kim Long Hotel còn là một trong những khách sạn nằm cách Ancient House of Huynh Thuy Le chưa đầy 1,86 km và Binh Thuy Temple chưa đầy 24,15 km.
<h3>Thông tin về Van Kim Long Hotel</h3><br>
Van Kim Long Hotel là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một khách sạn vừa thoải mái lại hợp túi tiền.
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Van Kim Long Hotel sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Dù quý khách muốn tổ chức một sự kiện hay các dịp kỷ niệm đặc biệt khác, Van Kim Long Hotel là lựa chọn tuyệt vời cho quý khách với phòng chức năng rộng lớn, được trang bị đầy đủ để sẵn sàng đáp ứng mọi yêu cầu.
Van Kim Long Hotel là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Van Kim Long Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Van Kim Long Hotel chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Van Kim Long Hotel sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Van Kim Long Hotel là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Van Kim Long Hotel chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Van Kim Long Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Van Kim Long Hotel là lựa chọn sáng suốt dành cho những du khách ghé thăm Xã Tân Phú Đông.', 23, 2),
							(268, N'Lan Thái Ngọc Hotel', N' 08 Dương Văn Hòa, Phường 1, Thành phố Cao Lãnh, Tỉnh Đồng Tháp', '0903457179', 'lanthaingochotel@gmail.com', N'<h2>Giới thiệu Khách sạn Lan Thái Ngọc</h2><br><h3>Vị trí</h3><br>
Lan Thai Ngoc Hotel toạ lạc tại khu vực / thành phố Phường 1.
Có rất nhiều điểm tham quan lân cận như Ancient House of Huynh Thuy Le ở khoảng cách 23,88 km, và Binh Thuy Temple ở khoảng cách 45,23 km.
<h3>Thông tin về Lan Thai Ngoc Hotel</h3><br>
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Lan Thai Ngoc Hotel sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Lan Thai Ngoc Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Lan Thai Ngoc Hotel là lựa chọn đúng đắn cho những ai đang tìm kiếm các khách sạn hợp với túi tiền tại Phường 1.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Lan Thai Ngoc Hotel chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Lan Thai Ngoc Hotel là lựa chọn lý tưởng cho những ai đang tìm kiếm một phòng nghỉ thoải mái với giá thành hợp lý.', 23, 2),
							(269, N'Thu Le Hotel', N' PG2-01, PG2-02, Vincom Cao Lãnh, Đường 30/4, Phường 1, Thành phố Cao Lãnh, Tỉnh Đồng Tháp', '0905040551', 'thulehotel@gmail.com', N'<h2>Giới thiệu Thu Le Hotel</h2><br><h3>Vị trí</h3><br>
Lưu trú tại Thu Le Hotel là một lựa chọn đúng đắn khi quý khách đến thăm Phường 1.
khách sạn này rất dễ tìm bởi vị trí đắc địa, nằm gần với nhiều tiện ích công cộng.
<h3>Thông tin về Thu Le Hotel</h3><br>
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Thu Le Hotel sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Từ sự kiện doanh nghiệp đến họp mặt công ty, Thu Le Hotel cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Thu Le Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Thu Le Hotel chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Thu Le Hotel sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Thu Le Hotel là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.
Bạn có phải là tín đồ mua sắm? Lưu trú tại Thu Le Hotel chắc chắn sẽ thoả mãn bạn với hàng loạt các trung tâm mua sắm kề cận.
Du lịch một mình cũng không hề kém phần thú vị và Thu Le Hotel là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Một trong những đặc điểm chính của khách sạn này là các liệu pháp spa đa dạng. Hãy nâng niu bản thân bằng các liệu pháp thư giãn, phục hồi giúp quý khách tươi trẻ thân, tâm.
Thu Le Hotel là lựa chọn thông thái nhất cho những ai đang tìm kiếm một khách sạn với dịch vụ xuất sắc nhưng hợp với túi tiền.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Thu Le Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Lưu trú tại Thu Le Hotel chắc chắn sẽ làm quý khách hài lòng bởi sự đón tiếp nhiệt thành và mức giá dễ chịu của khách sạn.', 23, 3),
							(270, N'Huynh Duc 2 Hotel', N' 9 Ngô Thời Nhậm, Phường 1, Thành phố Cao Lãnh, Tỉnh Đồng Tháp', '0782792181', 'huynhduc2@gmail.com', N'<h2>Giới thiệu Huynh Duc 2 Hotel</h2><br><h3>Vị trí</h3><br>
Lưu trú tại Huynh Duc 2 Hotel là một lựa chọn đúng đắn khi quý khách đến thăm Phường 1.
khách sạn này rất dễ tìm bởi vị trí đắc địa, nằm gần với nhiều tiện ích công cộng.
<h3>Thông tin về Huynh Duc 2 Hotel</h3><br>
Huynh Duc 2 Hotel là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một khách sạn vừa thoải mái lại hợp túi tiền.
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Huynh Duc 2 Hotel sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Huynh Duc 2 Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Du lịch một mình cũng không hề kém phần thú vị và Huynh Duc 2 Hotel là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Huynh Duc 2 Hotel là lựa chọn thông thái nhất cho những ai đang tìm kiếm một khách sạn với dịch vụ xuất sắc nhưng hợp với túi tiền.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Huynh Duc 2 Hotel chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Huynh Duc 2 Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Lưu trú tại Huynh Duc 2 Hotel chắc chắn sẽ làm quý khách hài lòng bởi sự đón tiếp nhiệt thành và mức giá dễ chịu của khách sạn.', 23, 3),
							(271, N'Sao Mai Hotel', N' 178 Nguyễn Huệ, Phường 2, Thành phố Cao Lãnh, Tỉnh Đồng Tháp', '0933727499', 'saomaihotel@gmail.com', N'<h2>Giới thiêu sao mai Hotel</h2> <br><h3>Vị trí</h3><br>
Sao Mai Hotel Cao Lanh toạ lạc tại khu vực / thành phố Phường 2.
Có rất nhiều điểm tham quan lân cận như Ancient House of Huynh Thuy Le ở khoảng cách 23,08 km, và Binh Thuy Temple ở khoảng cách 44,61 km.
<h3>Thông tin về Sao Mai Hotel Cao Lanh</h3><br>
Dù quý khách muốn tổ chức một sự kiện hay các dịp kỷ niệm đặc biệt khác, Sao Mai Hotel Cao Lanh là lựa chọn tuyệt vời cho quý khách với phòng chức năng rộng lớn, được trang bị đầy đủ để sẵn sàng đáp ứng mọi yêu cầu.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Sao Mai Hotel Cao Lanh
Từ sự kiện doanh nghiệp đến họp mặt công ty, Sao Mai Hotel Cao Lanh cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Sao Mai Hotel Cao Lanh, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Sao Mai Hotel Cao Lanh chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Sao Mai Hotel Cao Lanh sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Sao Mai Hotel Cao Lanh là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Dịch vụ tuyệt vời, cơ sở vật chất hoàn chỉnh và các tiện nghi khách sạn cung cấp sẽ khiến quý khách không thể phàn nàn trong suốt kỳ lưu trú tại Sao Mai Hotel Cao Lanh.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Sao Mai Hotel Cao Lanh chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Với những tiện nghi sẵn có Sao Mai Hotel Cao Lanh thực sự là một nơi lưu trú hoàn hảo.', 23, 4),
							(272, N'Bông Hồng Hotel', N' 251A Nguyễn Sinh Sắc, Khóm 2, Phường 2, Sa Đéc, Tỉnh Đồng Tháp', '0934057239', 'bonghonghotel@gmail.com', N'<h2>Giới Thiệu Khách Sạn Bông Hồng</h2><br><h3>Vị trí</h3><br>
Hotel Bong Hong Sa Dec là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Phường 2.
Không chỉ sở hữu vị trí đắc địa, Hotel Bong Hong Sa Dec còn là một trong những khách sạn nằm cách Cảng Cần Thơ chưa đầy 26,59 km và Ho Chi Minh Museum chưa đầy 28,36 km.
<h3>Thông tin về Hotel Bong Hong Sa Dec</h3><br>
Dịch vụ tuyệt vời, cơ sở vật chất hoàn chỉnh và các tiện nghi khách sạn cung cấp sẽ khiến quý khách không thể phàn nàn trong suốt kỳ lưu trú tại Hotel Bong Hong Sa Dec.
Hotel Bong Hong Sa Dec là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Với những tiện nghi sẵn có Hotel Bong Hong Sa Dec thực sự là một nơi lưu trú hoàn hảo.', 23, 4),
							(273, N'De Vuong 2 Hotel', N' 274 Hùng Vương, Phường 2, Thành phố Cao Lãnh, Tỉnh Đồng Tháp', '0908215171', 'devuong2hotel@gmail.com', N'<h2>Giới thiệu De Vuong 2 Hotel</h2><br><h3>Vị trí</h3><br>

De Vuong 2 Hotel toạ lạc tại khu vực / thành phố Phường 2.
Có rất nhiều điểm tham quan lân cận như Ancient House of Huynh Thuy Le ở khoảng cách 22,96 km, và Binh Thuy Temple ở khoảng cách 44,67 km.
<h3>Thông tin về De Vuong 2 Hotel</h3><br>
De Vuong 2 Hotel là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một khách sạn vừa thoải mái lại hợp túi tiền.
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, De Vuong 2 Hotel sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại De Vuong 2 Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì De Vuong 2 Hotel chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, De Vuong 2 Hotel sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và De Vuong 2 Hotel là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
De Vuong 2 Hotel là lựa chọn đúng đắn cho những ai đang tìm kiếm các khách sạn hợp với túi tiền tại Phường 2.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
De Vuong 2 Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
De Vuong 2 Hotel là lựa chọn lý tưởng cho những ai đang tìm kiếm một phòng nghỉ thoải mái với giá thành hợp lý.', 23, 5),
							(274, N'De Vuong Hotel', N'20 Tắc Thầy Cai, Phường Mỹ Phú, Thành phố Cao Lãnh, Tỉnh Đồng Tháp', '0908215182', 'devuonghotel@gmail.com', N'<h2>Giới thiệu De Vuong Hotel</h2><br><h3>Vị trí</h3><br>

De Vuong Hotel toạ lạc tại khu vực / thành phố Phường Mỹ Phú.
Có rất nhiều điểm tham quan lân cận như Ancient House of Huynh Thuy Le ở khoảng cách 22,24 km, và Binh Thuy Temple ở khoảng cách 44,18 km.
<h3>Thông tin về De Vuong Hotel</h3><br>
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, De Vuong Hotel sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại De Vuong Hotel
De Vuong Hotel là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại De Vuong Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì De Vuong Hotel chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, De Vuong Hotel sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Không phải mọi khách sạn đều cho phép mang thú cưng theo cùng, nhưng với chính sách đặc biệt của mình De Vuong Hotel luôn chào đón những người bạn đồng hành thân thiết của du khách. Khách sạn thân thiện với thú cưng này cho phép quý khách tận hưởng kỳ nghỉ mà không phải bận tâm lo lắng như khi bỏ lại chúng ở nhà.
Du lịch một mình cũng không hề kém phần thú vị và De Vuong Hotel là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
De Vuong Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
De Vuong Hotel là lựa chọn lý tưởng cho những ai đang tìm kiếm một phòng nghỉ thoải mái với giá thành hợp lý.', 23, 5),
							(275, N'Huynh Duc Hotel', N'1 Ngô Thời Nhậm, Phường 1, Thành phố Cao Lãnh, Tỉnh Đồng Tháp', '0918433814', 'huynhduchotel@gmail.com', N'<h2>Giới thiệu Huỳnh Đức Hotel</h2><br> <h3>Vị trí</h3><br>
Huynh Duc Hotel toạ lạc tại khu vực / thành phố Phường 1.
Có rất nhiều điểm tham quan lân cận như Ancient House of Huynh Thuy Le ở khoảng cách 23,12 km, và Binh Thuy Temple ở khoảng cách 44,78 km.
<h3>Thông tin về Huynh Duc Hotel</h3><br>
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Huynh Duc Hotel sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Huynh Duc Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Du lịch một mình cũng không hề kém phần thú vị và Huynh Duc Hotel là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Huynh Duc Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Huynh Duc Hotel là lựa chọn lý tưởng cho những ai đang tìm kiếm một phòng nghỉ thoải mái với giá thành hợp lý.', 23, 6),
							(276, N'B.O.B Hotel', N'105 Tôn Đức Thắng, Phường 1, Thành phố Cao Lãnh, Tỉnh Đồng Tháp', '0908902016', 'bobhotel@gmail.com', N'<h2>Giới thiệu B.O.B Hotel Cao Lãnh</h2> <br><h3>Vị trí</h3><br>
B.O.B Hotel Cao Lanh là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Phường 1.
Không chỉ sở hữu vị trí đắc địa, B.O.B Hotel Cao Lanh còn là một trong những khách sạn nằm cách Ancient House of Huynh Thuy Le chưa đầy 24,04 km và Binh Thuy Temple chưa đầy 45,58 km.
<h3>Thông tin về B.O.B Hotel Cao Lanh</h3><br>
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại B.O.B Hotel Cao Lanh, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì B.O.B Hotel Cao Lanh chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, B.O.B Hotel Cao Lanh sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và B.O.B Hotel Cao Lanh là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
B.O.B Hotel Cao Lanh là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
B.O.B Hotel Cao Lanh là lựa chọn sáng suốt dành cho những du khách ghé thăm Phường 1.', 23, 6),
							(277, N'Dong Anh Hotel', N'25 Trần Hưng Đạo, P5, Phường 5, Thành phố Cà Mau, Tỉnh Cà Mau', '0938425358', 'donganhhotel@gmail.com', N'<h2>Giới thiệu Dong Anh Hotel</h2> <br><h3>Vị trí</h3><br>
Dong Anh Hotel là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Phường 5.
khách sạn sở hữu vị trí đắc địa cách sân bay Sân bay Cà Mau (CAH) 2,24 km.
Không chỉ sở hữu vị trí đắc địa, Dong Anh Hotel còn là một trong những khách sạn nằm cách Sân bay Cà Mau (CAH) chưa đầy 2,24 km và U Minh Ha National Park chưa đầy 26,68 km.
<h3>Thông tin về Dong Anh Hotel</h3><br>
Dong Anh Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Dong Anh Hotel là lựa chọn sáng suốt dành cho những du khách ghé thăm Phường 5.', 24, 1),
							(278, N'Anh Thư Hotel', N' 385 Lý Thường Kiệt, Thành phố Cà Mau, Tỉnh Cà Mau', '0909500388', 'anhthuhotel@gmail.com', N'<h2>Giới thiệu Anh Thư Hotel</h2> <br><h3>Vị trí</h3><br>

Anh Thu Hotel Ca Mau là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Thành phố Cà Mau.
khách sạn sở hữu vị trí đắc địa cách sân bay Sân bay Cà Mau (CAH) 0,42 km.
Không chỉ sở hữu vị trí đắc địa, Anh Thu Hotel Ca Mau còn là một trong những khách sạn nằm cách Sân bay Cà Mau (CAH) chưa đầy 0,42 km và U Minh Ha National Park chưa đầy 28,53 km.
<h3>Thông tin về Anh Thu Hotel Ca Mau</h3><br>

Anh Thu Hotel Ca Mau là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một khách sạn vừa thoải mái lại hợp túi tiền.
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Anh Thu Hotel Ca Mau sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Anh Thu Hotel Ca Mau, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Anh Thu Hotel Ca Mau chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Anh Thu Hotel Ca Mau sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Anh Thu Hotel Ca Mau là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Anh Thu Hotel Ca Mau là lựa chọn sáng suốt dành cho những du khách ghé thăm Thành phố Cà Mau.', 24, 1),
							(279, N'Ruby Hotel', N' 19-20A Hùng Vương, Thành phố Cà Mau, Tỉnh Cà Mau', '0702328733', 'rubyhotel@gmail.com', N'<h2>Giới thiệu Ruby Hotel</h2> <br><h3>Vị trí</h3><br>
Ruby Hotel Ca Mau toạ lạc tại khu vực / thành phố Thành phố Cà Mau.
khách sạn sở hữu vị trí đắc địa cách sân bay Sân bay Cà Mau (CAH) 2,81 km.
Có rất nhiều điểm tham quan lân cận như Quan Am Pagoda ở khoảng cách 1,03 km, và Hung Vuong Park ở khoảng cách 0,33 km.
<h3>Thông tin về Ruby Hotel Ca Mau</h3><br>
Không chỉ sở hữu vị trí giúp quý khách dễ dàng ghé thăm những địa điểm lý thú trong chuyến hành trình, Ruby Hotel Ca Mau cũng sẽ mang đến cho quý khách trải nghiệm lưu trú mỹ mãn.
Ruby Hotel Ca Mau là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một khách sạn vừa thoải mái lại hợp túi tiền.
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Ruby Hotel Ca Mau sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Ruby Hotel Ca Mau
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Ruby Hotel Ca Mau, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Ruby Hotel Ca Mau chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Ruby Hotel Ca Mau sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Ruby Hotel Ca Mau là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Ruby Hotel Ca Mau là lựa chọn thông thái nhất cho những ai đang tìm kiếm một khách sạn với dịch vụ xuất sắc nhưng hợp với túi tiền.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Ruby Hotel Ca Mau là lựa chọn lý tưởng cho những ai đang tìm kiếm một phòng nghỉ thoải mái với giá thành hợp lý.', 24, 2),
							(280, N'Sao Kim Hotel', N' 3 Lưu Tấn Tài, Phường 5, Thành phố Cà Mau, Tỉnh Cà Mau', '0772754929', 'saokimhotel@gmail.com', N'<h2>Giới thiệu Sao Kim Hotel</h2> <br><h3>Vị trí</h3><br>
Sao Kim Hotel Ca Mau toạ lạc tại khu vực / thành phố Phường 5.
khách sạn sở hữu vị trí đắc địa cách sân bay Sân bay Cà Mau (CAH) 2,76 km.
Có rất nhiều điểm tham quan lân cận như Tu Na Nam Can Bird Garden ở khoảng cách 46,87 km, và Sân bay Cà Mau (CAH) ở khoảng cách 2,76 km.
<h3>Thông tin về Sao Kim Hotel Ca Mau</h3><br>
Sao Kim Hotel Ca Mau là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một khách sạn vừa thoải mái lại hợp túi tiền.
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Sao Kim Hotel Ca Mau sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Sao Kim Hotel Ca Mau
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Sao Kim Hotel Ca Mau, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Sao Kim Hotel Ca Mau chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Sao Kim Hotel Ca Mau sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Sao Kim Hotel Ca Mau là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Sao Kim Hotel Ca Mau là lựa chọn thông thái nhất cho những ai đang tìm kiếm một khách sạn với dịch vụ xuất sắc nhưng hợp với túi tiền.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Sao Kim Hotel Ca Mau chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Sao Kim Hotel Ca Mau là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Sao Kim Hotel Ca Mau là lựa chọn lý tưởng cho những ai đang tìm kiếm một phòng nghỉ thoải mái với giá thành hợp lý.', 24, 2),
							(281, N'Ozon Hotel', N'Quốc Lộ 1A, ấp Chống Mỹ, Năm Căn, tỉnh Cà Mau', '0909419677', 'ozonhotel@gmail.com', N'<h2>Giới thiệu Ozon Hotel</h2> <br><h3>Vị trí</h3><br>
Ozon Hotel toạ lạc tại khu vực / thành phố Năm Căn.
Có rất nhiều điểm tham quan lân cận như Cape Ca Mau National Park ở khoảng cách 38,13 km, và Dat Mui Landmark ở khoảng cách 38,1 km.
<h3>Thông tin về Ozon Hotel</h3><br>
Từ sự kiện doanh nghiệp đến họp mặt công ty, Ozon Hotel cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Du lịch một mình cũng không hề kém phần thú vị và Ozon Hotel là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Dịch vụ tuyệt vời, cơ sở vật chất hoàn chỉnh và các tiện nghi khách sạn cung cấp sẽ khiến quý khách không thể phàn nàn trong suốt kỳ lưu trú tại Ozon Hotel.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Ozon Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Với những tiện nghi sẵn có Ozon Hotel thực sự là một nơi lưu trú hoàn hảo.', 24, 3),
							(282, N'Ánh Nguyệt Hotel', N'207 Phan Ngoc Hien, Ward 6, Thành phố Cà Mau, Tỉnh Cà Mau,', '0909700079', 'anhnguyethotel@gmail.com', N'<h2>Giới thiệu Ánh Nguyệt Hotel</h2> <br><h3>Vị trí</h3><br>
Anh Nguyet Hotel toạ lạc tại khu vực / thành phố Thành phố Cà Mau.
khách sạn sở hữu vị trí đắc địa cách sân bay Sân bay Cà Mau (CAH) 3,36 km.
Có rất nhiều điểm tham quan lân cận như Lâm Viên Cà Mau ở khoảng cách 1,22 km, và Trường THPT Minh Thuận ở khoảng cách 39,88 km.
<h3>Thông tin về Anh Nguyet Hotel</h3><br>
Hưởng thụ một ngày thư thái đầy thú vị tại hồ bơi dù quý khách đang du lịch một mình hay cùng người thân.
Nhận ưu đãi đặc biệt dành cho các liệu pháp spa tinh tuý nhất giúp thư giãn tinh thần và làm tươi trẻ cơ thể.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Anh Nguyet Hotel chỉ dành riêng cho quý khách.
Với những tiện nghi sẵn có Anh Nguyet Hotel thực sự là một nơi lưu trú hoàn hảo.', 24, 3),
							(283, N'Phú Cường Hotel', N'81 Phan Ngọc Hiển, phường 4, Thành phố Cà Mau, Tỉnh Cà Mau', '0909920108', 'phucuonghotel@gmail.com', N'<h2>Giới thiệu Phú Cường Hotel</h2> <br><h3>Vị trí</h3><br>
Phu Cuong Hotel Ca Mau toạ lạc tại khu vực / thành phố Thành phố Cà Mau.
khách sạn sở hữu vị trí đắc địa cách sân bay Sân bay Cà Mau (CAH) 3,18 km.
Có rất nhiều điểm tham quan lân cận như Quan Am Pagoda ở khoảng cách 0,44 km, và Hung Vuong Park ở khoảng cách 0,61 km.
<h3>Thông tin về Phu Cuong Hotel Ca Mau</h3><br>
Dù quý khách muốn tổ chức một sự kiện hay các dịp kỷ niệm đặc biệt khác, Phu Cuong Hotel Ca Mau là lựa chọn tuyệt vời cho quý khách với phòng chức năng rộng lớn, được trang bị đầy đủ để sẵn sàng đáp ứng mọi yêu cầu.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Phu Cuong Hotel Ca Mau
Phu Cuong Hotel Ca Mau là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.
Một trong những đặc điểm chính của khách sạn này là các liệu pháp spa đa dạng. Hãy nâng niu bản thân bằng các liệu pháp thư giãn, phục hồi giúp quý khách tươi trẻ thân, tâm.
Từ sự kiện doanh nghiệp đến họp mặt công ty, Phu Cuong Hotel Ca Mau cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Phu Cuong Hotel Ca Mau, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Phu Cuong Hotel Ca Mau chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Phu Cuong Hotel Ca Mau sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Phu Cuong Hotel Ca Mau là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Hãy sẵn sàng đón nhận trải nghiệm khó quên bằng dịch vụ độc đáo và hoàn hảo của khách sạn cùng các tiện nghi đầy đủ, đáp ứng mọi nhu cầu của quý khách.
Trung tâm thể dục của khách sạn là một trong những tiện nghi không thể bỏ qua khi lưu trú tại đây.
Hưởng thụ một ngày thư thái đầy thú vị tại hồ bơi dù quý khách đang du lịch một mình hay cùng người thân.
Nhận ưu đãi đặc biệt dành cho các liệu pháp spa tinh tuý nhất giúp thư giãn tinh thần và làm tươi trẻ cơ thể.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Phu Cuong Hotel Ca Mau chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Phu Cuong Hotel Ca Mau là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Tận hưởng trải nghiệm lưu trú xa hoa đầy thú vị không đâu sánh bằng tại Phu Cuong Hotel Ca Mau.', 24, 4),
							(284, N'Mường Thanh Luxury Hotel', N'Lô C.03a, Khu trung tâm HCCT Tỉnh Cà Mau, Phường 9, Thành phố Cà Mau, Tỉnh Cà Mau', '0898489681', 'muongthanhluxuryhotel@gmail.com', N'<h2>Giới thiệu Mường Thanh Luxury Hotel</h2> <br><h3>Vị trí</h3><br>
Muong Thanh Luxury Ca Mau Hotel toạ lạc tại khu vực / thành phố Thành phố Cà Mau.
khách sạn sở hữu vị trí đắc địa cách sân bay Sân bay Cà Mau (CAH) 4,05 km.
Có rất nhiều điểm tham quan lân cận như Quan Am Pagoda ở khoảng cách 1,14 km, và Hung Vuong Park ở khoảng cách 1,64 km.
<h3>Thông tin về Muong Thanh Luxury Ca Mau Hotel</h3><br>
Muong Thanh Luxury Ca Mau Hotel là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.
Hãy sẵn sàng đón nhận trải nghiệm khó quên bằng dịch vụ độc đáo và hoàn hảo của khách sạn cùng các tiện nghi đầy đủ, đáp ứng mọi nhu cầu của quý khách.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Muong Thanh Luxury Ca Mau Hotel chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Muong Thanh Luxury Ca Mau Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Tận hưởng trải nghiệm lưu trú xa hoa đầy thú vị không đâu sánh bằng tại Muong Thanh Luxury Ca Mau Hotel.', 24, 4),
							(285, N'The One Hotel', N'MG2 - 19-20-23-24 Khu Đô Thị Vincom Khu phố thương mại Vincom, Thành phố Cà Mau, Tỉnh Cà Mau', '0907598659', 'theonehotel@gmail.com', N'<h2>Giới thiệu The One Hotel</h2> <br><h3>Vị trí</h3><br>
The One Hotel Cà Mau là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Cà Mau.
Khách sạn sở hữu vị trí đắc địa cách sân bay Cà Mau (CAH) 4,4 km.
Không chỉ sở hữu vị trí đắc địa, The One Hotel Ca Mau còn là một trong những khách sạn nằm cách Sân vận động Cà Mau chưa đầy 0,77 km và Trung tâm thương mại Sense City Cà Mau chưa đầy 2,26 km.
<h3>Thông tin về Khách sạn The One Cà Mau</h3><br>
Khách sạn này là sự lựa chọn hoàn hảo cho các cặp đôi đang tìm kiếm một nơi nghỉ ngơi lãng mạn hoặc một kỳ nghỉ trăng mật. Hãy tận hưởng những đêm đáng nhớ nhất bên người thân yêu khi lưu trú tại The One Hotel Cà Mau.
bạn là tín đồ mua sắm phải không? Lưu trú tại The One Hotel Cà Mau chắc chắn bạn sẽ tha hồ khám phá vô số trung tâm mua sắm gần đó.
Từ sự kiện doanh nghiệp đến họp mặt công ty, The One Hotel Cà Mau cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng nhu cầu của bạn và đồng nghiệp.
Hãy tận hưởng niềm vui với nhiều tiện ích giải trí đa dạng dành cho bạn và cả gia đình tại The One Hotel Cà Mau, nơi lưu trú tuyệt vời cho kỳ nghỉ của gia đình bạn.
Nếu dự định có một kỳ nghỉ dài, thì The One Hotel Cà Mau chính là lựa chọn dành cho quý khách. Cung cấp nhiều tiện nghi và chất lượng dịch vụ tuyệt vời, chỗ ở này chắc chắn sẽ khiến bạn cảm thấy như đang ở nhà.
Mặc dù đi du lịch cùng bạn bè có thể rất thú vị nhưng du lịch một mình cũng có những đặc quyền riêng. Về chỗ ở, The One Hotel Cà Mau phù hợp với những ai đề cao sự riêng tư trong thời gian lưu trú.
Quầy lễ tân 24 giờ luôn sẵn sàng phục vụ bạn từ khâu nhận phòng đến trả phòng hoặc bất kỳ hỗ trợ nào bạn cần. Nếu bạn muốn nhiều hơn, đừng ngần ngại hỏi lễ tân, chúng tôi luôn sẵn sàng phục vụ bạn.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ The One Hotel Cà Mau chỉ dành riêng cho quý khách.
WiFi có sẵn trong các khu vực chung của chỗ nghỉ để giúp bạn kết nối với gia đình và bạn bè.
The One Hotel Cà Mau là lựa chọn sáng suốt dành cho những du khách ghé thăm Cà Mau.', 24, 5),
							(286, N'Nam Kiều Hotel', N'151A Ngô Quyền, phường 1, Thành phố Cà Mau, Tỉnh Cà Mau', '0936383948', 'namkieuhotel@gmail.com', N'<h2>Giới thiệu Nam Kiều Hotel</h2> <br><h3>Vị trí</h3><br>
Lưu trú tại Nam Kieu Ca Mau Hotel là một lựa chọn đúng đắn khi quý khách đến thăm Thành phố Cà Mau.
khách sạn sở hữu vị trí đắc địa cách sân bay Sân bay Cà Mau (CAH) 4,02 km.
khách sạn này rất dễ tìm bởi vị trí đắc địa, nằm gần với nhiều tiện ích công cộng.
<h3>Thông tin về Nam Kieu Ca Mau Hotel</h3><br>
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Nam Kieu Ca Mau Hotel sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Từ sự kiện doanh nghiệp đến họp mặt công ty, Nam Kieu Ca Mau Hotel cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Du lịch một mình cũng không hề kém phần thú vị và Nam Kieu Ca Mau Hotel là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Nam Kieu Ca Mau Hotel là lựa chọn thông thái nhất cho những ai đang tìm kiếm một khách sạn với dịch vụ xuất sắc nhưng hợp với túi tiền.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Nam Kieu Ca Mau Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Lưu trú tại Nam Kieu Ca Mau Hotel chắc chắn sẽ làm quý khách hài lòng bởi sự đón tiếp nhiệt thành và mức giá dễ chịu của khách sạn.
', 24, 5),
							(287, N'Thanh Trúc Hotel', N'113 Ngo Quyen Street, Ward 1, Thành phố Cà Mau, Tỉnh Cà Mau', '0934801910', 'thanhtruchotel@gmail.com', N'<h2>Giới thiệu Thanh Trúc Hotel</h2> <br><h3>Vị trí</h3><br>
Lưu trú tại Thanh Truc Hotel Camau là một lựa chọn đúng đắn khi quý khách đến thăm Thành phố Cà Mau.
khách sạn sở hữu vị trí đắc địa cách sân bay Sân bay Cà Mau (CAH) 3,9 km.
khách sạn này rất dễ tìm bởi vị trí đắc địa, nằm gần với nhiều tiện ích công cộng.
<h3>Thông tin về Thanh Truc Hotel Camau</h3><br>
Dịch vụ tuyệt vời, cơ sở vật chất hoàn chỉnh và các tiện nghi khách sạn cung cấp sẽ khiến quý khách không thể phàn nàn trong suốt kỳ lưu trú tại Thanh Truc Hotel Camau.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Thanh Truc Hotel Camau chỉ dành riêng cho quý khách.
Với những tiện nghi sẵn có Thanh Truc Hotel Camau thực sự là một nơi lưu trú hoàn hảo.', 24, 6),
							(288, N'Bungalow-Farmstay Hoa Rừng U Minh', N'Đường T23, Ấp 15, xã Khánh An, U Minh, Tỉnh Cà Mau', '0903851461', 'bungalowfarmstayhoarunguminh@gmail.com', N'<h2>Giới thiệu Bungalow-Farmstay Hoa Rừng U Minh</h2> <br><h3>Vị trí</h3><br>
Bungalow - Farmstay Hoa Rừng U Minh là một nơi nghỉ nằm trong khu vực an ninh, toạ lạc tại Huyện U Minh.
Không chỉ sở hữu vị trí đắc địa, Bungalow - Farmstay Hoa Rừng U Minh còn là một trong những nơi nghỉ dưỡng nằm cách Sân vận động Cà Mau chưa đầy 15,56 km và Trung tâm thương mại Sense City Cà Mau chưa đầy 17,11 km.
<h3>Thông tin Bungalow - Farmstay Hoa Rung U Minh</h3><br>
Không chỉ nằm ở vị trí dễ dàng di chuyển tới nhiều địa điểm tham quan thú vị cho chuyến phiêu lưu của bạn, lưu trú tại Bungalow - Farmstay Hoa Rừng U Minh còn sẽ mang đến cho bạn một kỳ nghỉ thú vị.
Bungalow - Farmstay Hoa Rừng U Minh được đánh giá cao dành cho du khách ba lô muốn có một kỳ nghỉ giá cả phải chăng nhưng vẫn thoải mái.
Từ sự kiện doanh nghiệp đến họp mặt công ty, Bungalow - Farmstay Hoa Rừng U Minh cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng nhu cầu của bạn và đồng nghiệp.
Cho dù bạn đang lên kế hoạch cho một sự kiện hay những dịp đặc biệt khác, Bungalow - Farmstay Hoa Rừng U Minh là sự lựa chọn tuyệt vời cho bạn với phòng chức năng rộng lớn, được trang bị đầy đủ để phù hợp với yêu cầu của bạn.
Hãy tận hưởng niềm vui với nhiều tiện ích giải trí đa dạng dành cho bạn và cả gia đình tại Bungalow - Farmstay Hoa Rừng U Minh, nơi lưu trú tuyệt vời cho kỳ nghỉ của gia đình bạn.
Khu nghỉ dưỡng này là sự lựa chọn hoàn hảo cho các cặp đôi đang tìm kiếm một nơi nghỉ ngơi lãng mạn hoặc một kỳ nghỉ trăng mật. Hãy tận hưởng những đêm đáng nhớ nhất bên người thân yêu khi lưu trú tại Bungalow - Farmstay Hoa Rừng U Minh.
Nếu dự định có một kỳ nghỉ dài, thì Bungalow - Farmstay Hoa Rừng U Minh chính là lựa chọn dành cho quý khách. Cung cấp nhiều tiện nghi và chất lượng dịch vụ tuyệt vời, chỗ ở này chắc chắn sẽ khiến bạn cảm thấy như đang ở nhà.
Không phải resort nào cũng cho phép khách mang theo thú cưng, nhưng Bungalow - Farmstay Hoa Rừng U Minh luôn chào đón người bạn lông xù của mình với chính sách đặc biệt. Khách sạn thân thiện với vật nuôi này cho phép bạn tận hưởng kỳ nghỉ mà không phải lo lắng về việc để thú cưng ở nhà.
Khu nghỉ dưỡng này là nơi tốt nhất cho những ai mong muốn một nơi nghỉ ngơi thanh bình và yên bình, tránh xa đám đông.
Chúc bạn có một ngày thú vị và thư giãn tại hồ bơi, cho dù bạn đi du lịch một mình hay đi cùng người thân.
Quầy lễ tân 24 giờ luôn sẵn sàng phục vụ bạn từ khâu nhận phòng đến trả phòng hoặc bất kỳ hỗ trợ nào bạn cần. Nếu bạn muốn nhiều hơn, đừng ngần ngại hỏi lễ tân, chúng tôi luôn sẵn sàng phục vụ bạn.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Bungalow - Farmstay Hoa Rừng U Minh chỉ dành riêng cho quý khách.
WiFi có sẵn trong các khu vực chung của chỗ nghỉ để giúp bạn kết nối với gia đình và bạn bè.
Bungalow - Farmstay Hoa Rừng U Minh là lựa chọn sáng suốt dành cho những du khách ghé thăm Huyện U Minh.', 24, 6),

							(289, N'S’Bungalow Bến Tre', N'Cồn Phụng, Tân Thạch, Huyện Châu Thành, Tỉnh Bến Tre', '0702331103', 'sbungalowbentre@gmail.com', N'<h2>Giới thiệu S’Bungalow Bến Tre</h2> <br><h3Vị trí</h3><br>
Lưu trú tại SBungalow Ben Tre là một lựa chọn đúng đắn khi quý khách đến thăm Huyện Châu Thành.
nơi nghỉ này rất dễ tìm bởi vị trí đắc địa, nằm gần với nhiều tiện ích công cộng.
<h3>Thông tin về SBungalow Ben Tre</h3><br>
Không chỉ sở hữu vị trí giúp quý khách dễ dàng ghé thăm những địa điểm lý thú trong chuyến hành trình, SBungalow Ben Tre cũng sẽ mang đến cho quý khách trải nghiệm lưu trú mỹ mãn.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại SBungalow Ben Tre
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại SBungalow Ben Tre, một nơi nghỉ tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì SBungalow Ben Tre chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, SBungalow Ben Tre sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và SBungalow Ben Tre là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Khách sạn này là nơi tốt nhất dành cho những ai mong muốn một nơi thanh bình, thư thái để ẩn mình khỏi đám đông ồn ã, xô bồ.
SBungalow Ben Tre là lựa chọn thông thái nhất cho những ai đang tìm kiếm một nơi nghỉ với dịch vụ xuất sắc nhưng hợp với túi tiền.
Nhận ưu đãi đặc biệt dành cho các liệu pháp spa tinh tuý nhất giúp thư giãn tinh thần và làm tươi trẻ cơ thể.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ SBungalow Ben Tre chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của nơi nghỉ cho phép quý khách luôn kết nối với gia đình và bè bạn.
SBungalow Ben Tre là nơi nghỉ sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Lưu trú tại SBungalow Ben Tre chắc chắn sẽ làm quý khách hài lòng bởi sự đón tiếp nhiệt thành và mức giá dễ chịu của nơi nghỉ.', 25, 1),
							(290, N'Peace Farm', N'Hamlet 5, My Thanh Village, Giồng Trôm, Tỉnh Bến Tre', '0903687586', 'peacefarm@gmail.com', N'<h2>Giới thiệu Peace Farm</h2> <br><h3Vị trí</h3><br>
Peace Farm toạ lạc tại khu vực / thành phố Giồng Trôm.
Có rất nhiều điểm tham quan lân cận như Victory Monument Rach Gam - Xoai Mut ở khoảng cách 22,26 km, và Tien Giang Province Stadium ở khoảng cách 17,02 km.
<h3>Thông tin về Peace Farm</h3><br>
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Peace Farm, một nơi nghỉ tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Peace Farm là lựa chọn đúng đắn cho những ai đang tìm kiếm các nơi nghỉ hợp với túi tiền tại Giồng Trôm.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Sóng WiFi phủ khắp các khu vực chung của nơi nghỉ cho phép quý khách luôn kết nối với gia đình và bè bạn.
Peace Farm là lựa chọn lý tưởng cho những ai đang tìm kiếm một phòng nghỉ thoải mái với giá thành hợp lý.', 25, 1),
							(291, N'Cona Hotel Con Phung Ben Tre', N'ấp 10, xã Tân Thạch, huyện Châu Thành, Huyện Châu Thành, Tỉnh Bến Tre', '0901876803', 'conahotelconphungbentre@gmail.com', N'<h2>Giới thiệu Cona Hotel Con Phung Ben Tre</h2> <br><h3Vị trí</h3><br> 
Cona Hotel Con Phung Ben Tre toạ lạc tại khu vực / thành phố Huyện Châu Thành.
Có rất nhiều điểm tham quan lân cận như Ba Duc Ancient House ở khoảng cách 37,87 km, và Historic District Le Bau Lang Co ở khoảng cách 46,57 km.
<h3>Thông tin về Cona Hotel Con Phung Ben Tre</h3><br>
Không chỉ sở hữu vị trí giúp quý khách dễ dàng ghé thăm những địa điểm lý thú trong chuyến hành trình, Cona Hotel Con Phung Ben Tre cũng sẽ mang đến cho quý khách trải nghiệm lưu trú mỹ mãn.
Cona Hotel Con Phung Ben Tre là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một khách sạn vừa thoải mái lại hợp túi tiền.
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Cona Hotel Con Phung Ben Tre sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Cona Hotel Con Phung Ben Tre
Nếu dự định có một kỳ nghỉ dài, thì Cona Hotel Con Phung Ben Tre chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Cona Hotel Con Phung Ben Tre sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Cona Hotel Con Phung Ben Tre là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Cona Hotel Con Phung Ben Tre là lựa chọn lý tưởng cho những ai đang tìm kiếm một phòng nghỉ thoải mái với giá thành hợp lý.', 25, 2),
							(292, N'Innerzen Riverside homestay', N'369C, 2B ward, Nhon Thanh, Xã Nhơn Thạnh, Bến Tre, Tỉnh Bến Tre', '0908871037', 'innerzenriversidehomestaybentre@gmail.com', N'<h2>Giới thiệu Cona Hotel Con Phung Ben Tre</h2> <br><h3Vị trí</h3><br>
Innerzen Riverside Homestay Ben Tre là một nơi nghỉ nằm trong khu vực an ninh, toạ lạc tại Xã Nhơn Thạnh.
Không chỉ sở hữu vị trí đắc địa, Innerzen Riverside Homestay Ben Tre còn là một trong những nơi nghỉ nằm cách Cai Be Church chưa đầy 42,48 km và Tan Thanh Beach chưa đầy 43,5 km.
<h3>Thông tin về Innerzen Riverside Homestay Ben Tre</h3><br>
Innerzen Riverside Homestay Ben Tre là lựa chọn thông thái nhất cho những ai đang tìm kiếm một nơi nghỉ với dịch vụ xuất sắc nhưng hợp với túi tiền.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Innerzen Riverside Homestay Ben Tre chỉ dành riêng cho quý khách.
Innerzen Riverside Homestay Ben Tre là lựa chọn sáng suốt dành cho những du khách ghé thăm Xã Nhơn Thạnh.
', 25, 2),
							(293, N'Trường Thịnh Hotel', N'500A2, Nguyễn Thị Định, Phường Phú Khương, Bến Tre, Tỉnh Bến Tre', '0931832456', 'truongthinhhotel@gmail.com', N'<h2>Giới thiệu Trường Thịnh Hotel</h2> <br><h3Vị trí</h3><br>
Truong Thinh Hotel Ben Tre toạ lạc tại khu vực / thành phố Phường Phú Khương.
Có rất nhiều điểm tham quan lân cận như Ba Duc Ancient House ở khoảng cách 39,84 km, và Chùa Vĩnh Tràng ở khoảng cách 11,81 km.
<h3>Thông tin về Truong Thinh Hotel Ben Tre</h3><br>
Truong Thinh Hotel Ben Tre là lựa chọn thông thái nhất cho những ai đang tìm kiếm một khách sạn với dịch vụ xuất sắc nhưng hợp với túi tiền.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Truong Thinh Hotel Ben Tre chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Truong Thinh Hotel Ben Tre là lựa chọn lý tưởng cho những ai đang tìm kiếm một phòng nghỉ thoải mái với giá thành hợp lý.', 25, 3),
							(294, N'Huỳnh Thảo Hotel', N'69C Đồng Văn Cống, Xã Bình Phú, Bến Tre, Tỉnh Bến Tre', '0785023563', 'huynhthaohotel@gmail.com', N'<h2>Giới thiệu Huỳnh Thảo Hotel</h2> <br><h3Vị trí</h3><br>

Lưu trú tại Huynh Thao Hotel là một lựa chọn đúng đắn khi quý khách đến thăm Xã Bình Phú.
khách sạn này rất dễ tìm bởi vị trí đắc địa, nằm gần với nhiều tiện ích công cộng.
<h3>Thông tin về Huynh Thao Hotel</h3><br>
Khi lưu trú tại khách sạn thì nội thất và kiến trúc hẳn là hai yếu tố quan trọng khiến quý khách mãn nhãn. Với thiết kế độc đáo, Huynh Thao Hotel mang đến không gian lưu trú làm hài lòng quý khách.
Từ sự kiện doanh nghiệp đến họp mặt công ty, Huynh Thao Hotel cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Huynh Thao Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Huynh Thao Hotel chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Huynh Thao Hotel sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Huynh Thao Hotel là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Nhận ưu đãi đặc biệt dành cho các liệu pháp spa tinh tuý nhất giúp thư giãn tinh thần và làm tươi trẻ cơ thể.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Huynh Thao Hotel chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Huynh Thao Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Lưu trú tại Huynh Thao Hotel chắc chắn sẽ làm quý khách hài lòng bởi sự đón tiếp nhiệt thành và mức giá dễ chịu của khách sạn.', 25, 3),
							(295, N'Việt Úc Hotel', N' 144 Hùng Vương, Phường 3, Bến Tre, Tỉnh Bến Tre', '0768502889', 'vietuchotel@gmail.com', N' <h2>Giới thiệu Việt Úc Hotel</h2> <br><h3Vị trí</h3><br>
Viet Uc Hotel Ben Tre toạ lạc tại khu vực / thành phố Phường 3.
Có rất nhiều điểm tham quan lân cận như Cai Be Church ở khoảng cách 38,91 km, và Vinh Trang Temple ở khoảng cách 14,18 km.
<h3>Thông tin về Viet Uc Hotel Ben Tre</h3><br>
Dịch vụ tuyệt vời, cơ sở vật chất hoàn chỉnh và các tiện nghi khách sạn cung cấp sẽ khiến quý khách không thể phàn nàn trong suốt kỳ lưu trú tại Viet Uc Hotel Ben Tre.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Viet Uc Hotel Ben Tre chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Với những tiện nghi sẵn có Viet Uc Hotel Ben Tre thực sự là một nơi lưu trú hoàn hảo.', 25, 4),
							(296, N'Hàm Luông Hotel', N'200C, đường Hùng Vương, Tỉnh Bến Tre, Phường 5, Bến Tre, Tỉnh Bến Tre', '0776666497', 'hamluonghotel@gmail.com', N'<h2>Giới thiệu Hàm Luông Hotel</h2> <br><h3> Vị trí</h3><br>

Ham Luong Hotel Ben Tre toạ lạc tại khu vực / thành phố Phường 5.
Có rất nhiều điểm tham quan lân cận như Chùa Phù Châu ở khoảng cách 38,44 km, và Tien Giang Province Stadium ở khoảng cách 13,83 km.
<h3>Thông tin về Ham Luong Hotel Ben Tre</h3><br>
Dù quý khách muốn tổ chức một sự kiện hay các dịp kỷ niệm đặc biệt khác, Ham Luong Hotel Ben Tre là lựa chọn tuyệt vời cho quý khách với phòng chức năng rộng lớn, được trang bị đầy đủ để sẵn sàng đáp ứng mọi yêu cầu.
Nếu dự định có một kỳ nghỉ dài, thì Ham Luong Hotel Ben Tre chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Ham Luong Hotel Ben Tre sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Ham Luong Hotel Ben Tre là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Dịch vụ tuyệt vời, cơ sở vật chất hoàn chỉnh và các tiện nghi khách sạn cung cấp sẽ khiến quý khách không thể phàn nàn trong suốt kỳ lưu trú tại Ham Luong Hotel Ben Tre.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Ham Luong Hotel Ben Tre là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Với những tiện nghi sẵn có Ham Luong Hotel Ben Tre thực sự là một nơi lưu trú hoàn hảo.
', 25, 4),
							(297, N'Bến Tre Riverside Resort', N' 708 Nguyễn Văn Tú, Phường 7, Phường 7, Bến Tre, Tỉnh Bến Tre', '0938494454', 'bentreriversideresort@gmail.com', N' <h2>Giới thiệu Bến Tre Riverside Resort</h2> <br><h3> Vị trí</h3><br>
Ben Tre Riverside Resort là một nơi nghỉ nằm trong khu vực an ninh, toạ lạc tại Phường 7.
Không chỉ sở hữu vị trí đắc địa, Ben Tre Riverside Resort còn là một trong những nơi nghỉ nằm cách Tien Giang Museum chưa đầy 15,94 km và Victory Monument Rach Gam - Xoai Mut chưa đầy 16,13 km.
<h3>Thông tin về Ben Tre Riverside Resort</h3><br>
Dù quý khách muốn tổ chức một sự kiện hay các dịp kỷ niệm đặc biệt khác, Ben Tre Riverside Resort là lựa chọn tuyệt vời cho quý khách với phòng chức năng rộng lớn, được trang bị đầy đủ để sẵn sàng đáp ứng mọi yêu cầu.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Ben Tre Riverside Resort
Ben Tre Riverside Resort là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.
Từ sự kiện doanh nghiệp đến họp mặt công ty, Ben Tre Riverside Resort cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Ben Tre Riverside Resort , một nơi nghỉ tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Khách sạn này là nơi tốt nhất dành cho những ai mong muốn một nơi thanh bình, thư thái để ẩn mình khỏi đám đông ồn ã, xô bồ.
Hãy sẵn sàng đón nhận trải nghiệm khó quên bằng dịch vụ độc đáo và hoàn hảo của nơi nghỉ cùng các tiện nghi đầy đủ, đáp ứng mọi nhu cầu của quý khách.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Ben Tre Riverside Resort chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của nơi nghỉ cho phép quý khách luôn kết nối với gia đình và bè bạn.
Ben Tre Riverside Resort là nơi nghỉ sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Hãy sẵn sàng đón nhận những giây phút vô giá khó phai trong suốt kỳ nghỉ của quý khách tại Ben Tre Riverside Resort .', 25, 5),
							(298, N'Forever Green Resort', N'Phú Khương, xã Phú Túc, Huyện Châu Thành, Bến Tre, Huyện Châu Thành, Tỉnh Bến Tre', '0909651194', 'forevergreenresort@gmail.com', N'<h2>Giới thiệu Forever Green Resort</h2> <br><h3> Vị trí</h3><br>
Forever Green Resort toạ lạc tại khu vực / thành phố Huyện Châu Thành.
Có rất nhiều điểm tham quan lân cận như Xeo Quyt Relic Area ở khoảng cách 49,7 km, và Ba Duc Ancient House ở khoảng cách 25,29 km.
Thông tin về Forever Green Resort
Khách sạn này là nơi tốt nhất dành cho những ai mong muốn một nơi thanh bình, thư thái để ẩn mình khỏi đám đông ồn ã, xô bồ.
Hãy sẵn sàng đón nhận trải nghiệm khó quên bằng dịch vụ độc đáo và hoàn hảo của nơi nghỉ cùng các tiện nghi đầy đủ, đáp ứng mọi nhu cầu của quý khách.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Sóng WiFi phủ khắp các khu vực chung của nơi nghỉ cho phép quý khách luôn kết nối với gia đình và bè bạn.
Forever Green Resort là nơi nghỉ sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Tận hưởng trải nghiệm lưu trú xa hoa đầy thú vị không đâu sánh bằng tại Forever Green Resort.', 25, 5),
							(299, N'Bến Tre Farm Stay', N' Tân Long 3, Tân Thanh Bình, Huyện Mỏ Cày Bắc, Xã Tân Thành Bình, Mỏ Cày Bắc, Tỉnh Bến Tre', '0788267267', 'bentrefarmstay@gmail.com', N' <h2>Giới thiệu Bến Tre Farm Stay</h2> <br><h3> Vị trí</h3><br>

Ben Tre Farmstay là một nơi nghỉ nằm trong khu vực an ninh, toạ lạc tại Xã Tân Thành Bình.
Không chỉ sở hữu vị trí đắc địa, Ben Tre Farmstay còn là một trong những nơi nghỉ nằm cách Cù lao Thới Sơn chưa đầy 14,1 km và Mekong River Cruise chưa đầy 14,9 km.
<h3>Thông tin về Ben Tre Farmstay</h3><br>
Ben Tre Farmstay là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một nơi nghỉ vừa thoải mái lại hợp túi tiền.
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Ben Tre Farmstay sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Ben Tre Farmstay, một nơi nghỉ tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Du lịch một mình cũng không hề kém phần thú vị và Ben Tre Farmstay là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Ben Tre Farmstay chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của nơi nghỉ cho phép quý khách luôn kết nối với gia đình và bè bạn.
Ben Tre Farmstay là lựa chọn sáng suốt dành cho những du khách ghé thăm Xã Tân Thành Bình.', 25, 6),
							(300, N'DiaMond Stars Bến Tre Hotel', N' 140 Hùng Vương, Phường 3, Bến Tre, Tỉnh Bến Tre', '0908807337', 'diamondstarsbentrehotel@gmail.com', N'<h2>Giới thiệu DiaMond Stars Bến Tre Hotel</h2> <br><h3> Vị trí</h3><br>
Lưu trú tại Diamond Stars Ben Tre Hotel là một lựa chọn đúng đắn khi quý khách đến thăm Phường 3.
khách sạn này rất dễ tìm bởi vị trí đắc địa, nằm gần với nhiều tiện ích công cộng.
<h3>Thông tin về Diamond Stars Ben Tre Hotel</h3><br>
Diamond Stars Ben Tre Hotel là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.
Dịch vụ thượng hạng song hành với hàng loạt tiện nghi phong phú sẽ đem đến cho quý khách trải nghiệm của một kỳ nghỉ viên mãn nhất.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Diamond Stars Ben Tre Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Hãy sẵn sàng đón nhận những giây phút vô giá khó phai trong suốt kỳ nghỉ của quý khách tại Diamond Stars Ben Tre Hotel.', 25, 6),

							(301, N'SOJO Hotel', N'7 Quang Trung, Quang Trung, Thành phố Thái Bình, Tỉnh Thái Bình', '0782592249', 'sojohotel@gmail.com', N'<h2>Giới thiệu SOJO Hotel</h2> <br><h3> Vị trí</h3><br>

SOJO Hotel Thai Binh là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Quang Trung.
Không chỉ sở hữu vị trí đắc địa, SOJO Hotel Thai Binh còn là một trong những khách sạn nằm cách Trang An Ecological Tourist Site chưa đầy 48,47 km và Chợ Đồng Văn chưa đầy 48,76 km.
<h3>Thông tin về SOJO Hotel Thai Binh</h3><br>
SOJO Hotel Thai Binh là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.
Từ sự kiện doanh nghiệp đến họp mặt công ty, SOJO Hotel Thai Binh cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại SOJO Hotel Thai Binh, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì SOJO Hotel Thai Binh chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, SOJO Hotel Thai Binh sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và SOJO Hotel Thai Binh là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Hãy sẵn sàng đón nhận trải nghiệm khó quên bằng dịch vụ độc đáo và hoàn hảo của khách sạn cùng các tiện nghi đầy đủ, đáp ứng mọi nhu cầu của quý khách.
Trung tâm thể dục của khách sạn là một trong những tiện nghi không thể bỏ qua khi lưu trú tại đây.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ SOJO Hotel Thai Binh chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
SOJO Hotel Thai Binh là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Hãy sẵn sàng đón nhận những giây phút vô giá khó phai trong suốt kỳ nghỉ của quý khách tại SOJO Hotel Thai Binh.', 26, 1),
							(302, N'Selegend Hotel', N'36 QUANG TRUNG Street, Tran Hung Dao Ward , Thai Binh rovinceP, Trần Hưng Đạo, Thành phố Thái Bình, Tỉnh Thái Bình', '0938154297', 'selegendhotel@gmail.com', N'<h2>Giới thiệu Selegend Hotel</h2> <br><h3> Vị trí</h3><br>
Lưu trú tại Selegend Hotel là một lựa chọn đúng đắn khi quý khách đến thăm Trần Hưng Đạo.
khách sạn này rất dễ tìm bởi vị trí đắc địa, nằm gần với nhiều tiện ích công cộng.
<h3>Thông tin về Selegend Hotel</h3><br>
Tọa lạc gần sân bay, Selegend Hotel là nơi nghỉ ngơi lý tưởng trong lúc quý khách đang chờ chuyến bay kế tiếp. Quý khách có thể tận hưởng không gian nghỉ dưỡng vừa ý nơi đây trong quá trình quá cảnh.
Từ sự kiện doanh nghiệp đến họp mặt công ty, Selegend Hotel cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Dù quý khách muốn tổ chức một sự kiện hay các dịp kỷ niệm đặc biệt khác, Selegend Hotel là lựa chọn tuyệt vời cho quý khách với phòng chức năng rộng lớn, được trang bị đầy đủ để sẵn sàng đáp ứng mọi yêu cầu.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Selegend Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Hãy tận hưởng trải nghiệm lưu trú có một không hai tại toà nhà mang đậm dấu ấn lịch sử của Selegend Hotel, điều quý khách khó có thể tìm thấy tại bất kỳ đâu.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Selegend Hotel
Nếu dự định có một kỳ nghỉ dài, thì Selegend Hotel chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Selegend Hotel sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Selegend Hotel là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Một trong những đặc điểm chính của khách sạn này là các liệu pháp spa đa dạng. Hãy nâng niu bản thân bằng các liệu pháp thư giãn, phục hồi giúp quý khách tươi trẻ thân, tâm.
Hãy sẵn sàng đón nhận trải nghiệm khó quên bằng dịch vụ độc đáo và hoàn hảo của khách sạn cùng các tiện nghi đầy đủ, đáp ứng mọi nhu cầu của quý khách.
Trung tâm thể dục của khách sạn là một trong những tiện nghi không thể bỏ qua khi lưu trú tại đây.
Nhận ưu đãi đặc biệt dành cho các liệu pháp spa tinh tuý nhất giúp thư giãn tinh thần và làm tươi trẻ cơ thể.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Selegend Hotel chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Selegend Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Hãy sẵn sàng đón nhận những giây phút vô giá khó phai trong suốt kỳ nghỉ của quý khách tại Selegend Hotel.', 26, 1),
							(303, N'White Palace Lam Son Hotel', N'1A Ky Dong street, Tran Hung Dao ward, Thai Binh city, Thai Binh province, Trần Hưng Đạo, Thành phố Thái Bình, Tỉnh Thái Bình', '0918424651', 'whitepalacelamsonhotel@gmail.com', N'<h2>Giới thiệu White Palace Lam Son Hotel</h2> <br><h3> Vị trí</h3><br>
White Palace Lam Son Hotel là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Trần Hưng Đạo.
Không chỉ sở hữu vị trí đắc địa, White Palace Lam Son Hotel còn là một trong những khách sạn nằm cách Hang Múa chưa đầy 46,96 km và Thung Nắng chưa đầy 49,66 km.
<h3>Thông tin về White Palace Lam Son Hotel</h3><br>
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, White Palace Lam Son Hotel sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại White Palace Lam Son Hotel
White Palace Lam Son Hotel là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.
Từ sự kiện doanh nghiệp đến họp mặt công ty, White Palace Lam Son Hotel cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại White Palace Lam Son Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Du lịch một mình cũng không hề kém phần thú vị và White Palace Lam Son Hotel là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Dịch vụ tuyệt vời, cơ sở vật chất hoàn chỉnh và các tiện nghi khách sạn cung cấp sẽ khiến quý khách không thể phàn nàn trong suốt kỳ lưu trú tại White Palace Lam Son Hotel.
Nhận ưu đãi đặc biệt dành cho các liệu pháp spa tinh tuý nhất giúp thư giãn tinh thần và làm tươi trẻ cơ thể.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ White Palace Lam Son Hotel chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
White Palace Lam Son Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Với những tiện nghi sẵn có White Palace Lam Son Hotel thực sự là một nơi lưu trú hoàn hảo.', 26, 2),
							(304, N'Golden Thai Bình Hotel', N' 716B Lê Thánh Tông, Trần Hưng Đạo, Thành phố Thái Bình, Tỉnh Thái Bình', '0779752226', 'goldenthaibinhhotel@gmail.com', N' <h2>Giới thiệu Golden Thai Bình Hotel</h2> <br><h3> Vị trí</h3><br>
Lưu trú tại Golden Thai Binh Hotel là một lựa chọn đúng đắn khi quý khách đến thăm Trần Hưng Đạo.
khách sạn này rất dễ tìm bởi vị trí đắc địa, nằm gần với nhiều tiện ích công cộng.
<h3>Thông tin về Golden Thai Binh Hotel</h3><br>
Dù quý khách muốn tổ chức một sự kiện hay các dịp kỷ niệm đặc biệt khác, Golden Thai Binh Hotel là lựa chọn tuyệt vời cho quý khách với phòng chức năng rộng lớn, được trang bị đầy đủ để sẵn sàng đáp ứng mọi yêu cầu.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Golden Thai Binh Hotel
Golden Thai Binh Hotel là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.
Một trong những đặc điểm chính của khách sạn này là các liệu pháp spa đa dạng. Hãy nâng niu bản thân bằng các liệu pháp thư giãn, phục hồi giúp quý khách tươi trẻ thân, tâm.
Tọa lạc gần sân bay, Golden Thai Binh Hotel là nơi nghỉ ngơi lý tưởng trong lúc quý khách đang chờ chuyến bay kế tiếp. Quý khách có thể tận hưởng không gian nghỉ dưỡng vừa ý nơi đây trong quá trình quá cảnh.
Từ sự kiện doanh nghiệp đến họp mặt công ty, Golden Thai Binh Hotel cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Golden Thai Binh Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Golden Thai Binh Hotel chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Golden Thai Binh Hotel sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Dịch vụ tuyệt vời, cơ sở vật chất hoàn chỉnh và các tiện nghi khách sạn cung cấp sẽ khiến quý khách không thể phàn nàn trong suốt kỳ lưu trú tại Golden Thai Binh Hotel.
Nhận ưu đãi đặc biệt dành cho các liệu pháp spa tinh tuý nhất giúp thư giãn tinh thần và làm tươi trẻ cơ thể.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Golden Thai Binh Hotel chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Golden Thai Binh Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Với những tiện nghi sẵn có Golden Thai Binh Hotel thực sự là một nơi lưu trú hoàn hảo.', 26, 2),
							(305, N'Hoàng Gia Hotel', N'243 Trần Thái Tông, Trần Hưng Đạo, Thành phố Thái Bình, Tỉnh Thái Bình', '0909494721', 'hoanggiahotel@gmail.com', N'<h2>Giới thiệu Hoàng Gia Hotel</h2> <br><h3> Vị trí</h3><br>
Hoang Gia Hotel là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Trần Hưng Đạo.
Không chỉ sở hữu vị trí đắc địa, Hoang Gia Hotel còn là một trong những khách sạn nằm cách Đền Quan Tuần Tranh chưa đầy 32,72 km và La Tien Temple chưa đầy 26,22 km.
<h3>Thông tin về Hoang Gia Hotel</h3><br>
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Hoang Gia Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Hoang Gia Hotel chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Hoang Gia Hotel sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Hoang Gia Hotel là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Hoang Gia Hotel là lựa chọn thông thái nhất cho những ai đang tìm kiếm một khách sạn với dịch vụ xuất sắc nhưng hợp với túi tiền.
Hoang Gia Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Hoang Gia Hotel là lựa chọn sáng suốt dành cho những du khách ghé thăm Trần Hưng Đạo.
', 26, 3),
							(306, N'Hoàng Gia 2 Hotel', N'Khách sạn Hoàng Gia II, Cầu Báng, tổ 1, phường Tiền Phong, Thành phố Thái Bình, Thành phố Thái Bình, Tỉnh Thái Bình', '0907202292', 'hoanggia2hotel@gmail.com', N'<h2>Giới thiệu Hoàng Gia 2 Hotel</h2> <br><h3> Vị trí</h3><br>
Lưu trú tại Hoang Gia II Hotel là một lựa chọn đúng đắn khi quý khách đến thăm Thành phố Thái Bình.
khách sạn này rất dễ tìm bởi vị trí đắc địa, nằm gần với nhiều tiện ích công cộng.
<h3>Thông tin về Hoang Gia II Hotel</h3><br>
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Hoang Gia II Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Hoang Gia II Hotel
Nếu dự định có một kỳ nghỉ dài, thì Hoang Gia II Hotel chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Hoang Gia II Hotel sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Hoang Gia II Hotel là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.
Du lịch một mình cũng không hề kém phần thú vị và Hoang Gia II Hotel là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Hoang Gia II Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Lưu trú tại Hoang Gia II Hotel chắc chắn sẽ làm quý khách hài lòng bởi sự đón tiếp nhiệt thành và mức giá dễ chịu của khách sạn.
', 26, 3),
							(307, N'NewDay Hotel', N'Thôn Phương Ngãi, xã Bình Minh, Kiến Xương, Tỉnh Thái Bình', '0763661837', 'newdayhotel@gmail.com', N'<h2>Giới thiệu NewDay Hotel</h2> <br><h3> Vị trí</h3><br>
NewDay Hotel là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Kiến Xương.
Không chỉ sở hữu vị trí đắc địa, NewDay Hotel còn là một trong những khách sạn nằm cách Giaỳ dép ngọc oanh chưa đầy 32 km và TocoToco Hải Hậu chưa đầy 26,26 km.
<h3>Thông tin về NewDay Hotel</h3><br>
Dù quý khách muốn tổ chức một sự kiện hay các dịp kỷ niệm đặc biệt khác, NewDay Hotel là lựa chọn tuyệt vời cho quý khách với phòng chức năng rộng lớn, được trang bị đầy đủ để sẵn sàng đáp ứng mọi yêu cầu.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại NewDay Hotel
Từ sự kiện doanh nghiệp đến họp mặt công ty, NewDay Hotel cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại NewDay Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì NewDay Hotel chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, NewDay Hotel sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và NewDay Hotel là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ NewDay Hotel chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Với những tiện nghi sẵn có NewDay Hotel thực sự là một nơi lưu trú hoàn hảo.', 26, 4),
							(308, N'White Palace Hotel', N'245B Tran Thai Tong, Trần Hưng Đạo, Thành phố Thái Bình, Tỉnh Thái Bình', '0908599154', 'whitepalacehotel@gmail.com', N'<h2>Giới thiệu White Palace Hotel Thái Bình</h2> <br><h3> Vị trí</h3><br>
White Palace Hotel Thai Binh là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Trần Hưng Đạo.
Không chỉ sở hữu vị trí đắc địa, White Palace Hotel Thai Binh còn là một trong những khách sạn nằm cách Trang An Ecological Tourist Site chưa đầy 47,88 km và Chợ Đồng Văn chưa đầy 47,9 km.
<h3>Thông tin về White Palace Hotel Thai Binh</h3><br>
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, White Palace Hotel Thai Binh sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Dù quý khách muốn tổ chức một sự kiện hay các dịp kỷ niệm đặc biệt khác, White Palace Hotel Thai Binh là lựa chọn tuyệt vời cho quý khách với phòng chức năng rộng lớn, được trang bị đầy đủ để sẵn sàng đáp ứng mọi yêu cầu.
White Palace Hotel Thai Binh là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.
Từ sự kiện doanh nghiệp đến họp mặt công ty, White Palace Hotel Thai Binh cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại White Palace Hotel Thai Binh, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ White Palace Hotel Thai Binh chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
White Palace Hotel Thai Binh là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Với những tiện nghi sẵn có White Palace Hotel Thai Binh thực sự là một nơi lưu trú hoàn hảo.', 26, 4),
							(309, N'Diamond Hotel', N'373 Tran Nhan Tong, Đề Thám, Thành phố Thái Bình, Tỉnh Thái Bình', '0901104114', 'diamondhotel@gmail.com', N'<h2>Giới thiệu Diamond Hotel Thái Bình</h2> <br><h3> Vị trí</h3><br>
Diamond Hotel Thai Binh là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Đề Thám.
Không chỉ sở hữu vị trí đắc địa, Diamond Hotel Thai Binh còn là một trong những khách sạn nằm cách Đền Quan Tuần Tranh chưa đầy 32,53 km và La Tien Temple chưa đầy 26,35 km.
<h3>Thông tin về Diamond Hotel Thai Binh</h3><br>
Không chỉ sở hữu vị trí giúp quý khách dễ dàng ghé thăm những địa điểm lý thú trong chuyến hành trình, Diamond Hotel Thai Binh cũng sẽ mang đến cho quý khách trải nghiệm lưu trú mỹ mãn.
Diamond Hotel Thai Binh là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một khách sạn vừa thoải mái lại hợp túi tiền.
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Diamond Hotel Thai Binh sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Dù quý khách muốn tổ chức một sự kiện hay các dịp kỷ niệm đặc biệt khác, Diamond Hotel Thai Binh là lựa chọn tuyệt vời cho quý khách với phòng chức năng rộng lớn, được trang bị đầy đủ để sẵn sàng đáp ứng mọi yêu cầu.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Diamond Hotel Thai Binh
Khi lưu trú tại khách sạn thì nội thất và kiến trúc hẳn là hai yếu tố quan trọng khiến quý khách mãn nhãn. Với thiết kế độc đáo, Diamond Hotel Thai Binh mang đến không gian lưu trú làm hài lòng quý khách.
Từ sự kiện doanh nghiệp đến họp mặt công ty, Diamond Hotel Thai Binh cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Diamond Hotel Thai Binh, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Diamond Hotel Thai Binh chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Diamond Hotel Thai Binh sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Diamond Hotel Thai Binh là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Diamond Hotel Thai Binh là lựa chọn thông thái nhất cho những ai đang tìm kiếm một khách sạn với dịch vụ xuất sắc nhưng hợp với túi tiền.
Trung tâm thể dục của khách sạn là một trong những tiện nghi không thể bỏ qua khi lưu trú tại đây.
Nhận ưu đãi đặc biệt dành cho các liệu pháp spa tinh tuý nhất giúp thư giãn tinh thần và làm tươi trẻ cơ thể.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Diamond Hotel Thai Binh chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Diamond Hotel Thai Binh là lựa chọn sáng suốt dành cho những du khách ghé thăm Đề Thám.', 26, 5),
							(310, N'Petro Thái Bình Hotel', N'458 Lý Bôn, Phường Đề Thám, Thành phố Thái Bình Việt Nam, Đề Thám, Thành phố Thái Bình, Tỉnh Thái Bình', '0909336379', 'petrothaibinhhotel@gmail.com', N'<h2>Giới thiệu Petro Thái Bình Hotel</h2> <br><h3> Vị trí</h3><br>
Petro Thai Binh Hotel là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Đề Thám.
Không chỉ sở hữu vị trí đắc địa, Petro Thai Binh Hotel còn là một trong những khách sạn nằm cách Đền Quan Tuần Tranh chưa đầy 32,64 km và La Tien Temple chưa đầy 26,54 km.
<h3>Thông tin về Petro Thai Binh Hotel</h3><br>
Từ sự kiện doanh nghiệp đến họp mặt công ty, Petro Thai Binh Hotel cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy sẵn sàng đón nhận trải nghiệm khó quên bằng dịch vụ độc đáo và hoàn hảo của khách sạn cùng các tiện nghi đầy đủ, đáp ứng mọi nhu cầu của quý khách.
Trung tâm thể dục của khách sạn là một trong những tiện nghi không thể bỏ qua khi lưu trú tại đây.
Hưởng thụ một ngày thư thái đầy thú vị tại hồ bơi dù quý khách đang du lịch một mình hay cùng người thân.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Petro Thai Binh Hotel chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Hãy sẵn sàng đón nhận những giây phút vô giá khó phai trong suốt kỳ nghỉ của quý khách tại Petro Thai Binh Hotel.', 26, 5),
							(311, N'Thái Bình Dream Hotel', N'355 Ly Bon, P. De Tham, Đề Thám, Thành phố Thái Bình, Tỉnh Thái Bình', '0918424651', 'thaibinhdreamhotel@gmail.com', N'<h2>Giới thiệu Thái Bình Dream Hotel</h2> <br><h3> Vị trí</h3><br>
Thai Binh Dream Hotel toạ lạc tại khu vực / thành phố Đề Thám.
Có rất nhiều điểm tham quan lân cận như Trang An Ecological Tourist Site ở khoảng cách 48,69 km, và Chợ Đồng Văn ở khoảng cách 48,89 km.
<h3>Thông tin về Thai Binh Dream Hotel</h3><br>
Thai Binh Dream Hotel là lựa chọn đúng đắn cho những ai đang tìm kiếm các nơi nghỉ hợp với túi tiền tại Đề Thám.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Thai Binh Dream Hotel là lựa chọn lý tưởng cho những ai đang tìm kiếm một phòng nghỉ thoải mái với giá thành hợp lý.', 26, 6),
							(312, N'Kim Long Hotel', N'368 Lý Bôn, Tiền Phong, Thành phố Thái Bình, Tỉnh Thái Bình', '0908441247', 'kimlonghotel@gmail.com', N'<h2>Giới thiệu Kim Long Hotel Thái Bình</h2> <br><h3> Vị trí</h3><br>
Kim Long Hotel Thai Binh là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Tiền Phong.
Không chỉ sở hữu vị trí đắc địa, Kim Long Hotel Thai Binh còn là một trong những khách sạn nằm cách Bệnh Viện Na Uy chưa đầy 47,52 km và Benh vien da khoa An Lao chưa đầy 47,6 km.
<h3>Thông tin về Kim Long Hotel Thai Binh</h3><br>
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Kim Long Hotel Thai Binh sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Kim Long Hotel Thai Binh, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Kim Long Hotel Thai Binh chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Kim Long Hotel Thai Binh sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Kim Long Hotel Thai Binh là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Dịch vụ tuyệt vời, cơ sở vật chất hoàn chỉnh và các tiện nghi khách sạn cung cấp sẽ khiến quý khách không thể phàn nàn trong suốt kỳ lưu trú tại Kim Long Hotel Thai Binh.
Với những tiện nghi sẵn có Kim Long Hotel Thai Binh thực sự là một nơi lưu trú hoàn hảo.', 26, 6),

							(313, N'Mường Thanh Luxury Cao Bằng Hotel', N'42 Kim Đồng, Phường Hợp Giang, Cao Bằng', '0905211089', 'muongthanhluxurycaobanghotel@gmail.com', N'<h2>Giới thiệu Mường Thanh Luxury Cao Bằng Hotel</h2> <br><h3> Vị trí</h3><br>
Lưu trú tại Muong Thanh Luxury Cao Bang là một lựa chọn đúng đắn khi quý khách đến thăm Phường Hợp Giang.
khách sạn này rất dễ tìm bởi vị trí đắc địa, nằm gần với nhiều tiện ích công cộng.
<h3>Thông tin về Muong Thanh Luxury Cao Bang</h3><br>
Dù quý khách muốn tổ chức một sự kiện hay các dịp kỷ niệm đặc biệt khác, Muong Thanh Luxury Cao Bang là lựa chọn tuyệt vời cho quý khách với phòng chức năng rộng lớn, được trang bị đầy đủ để sẵn sàng đáp ứng mọi yêu cầu.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Muong Thanh Luxury Cao Bang
Muong Thanh Luxury Cao Bang là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.
Một trong những đặc điểm chính của khách sạn này là các liệu pháp spa đa dạng. Hãy nâng niu bản thân bằng các liệu pháp thư giãn, phục hồi giúp quý khách tươi trẻ thân, tâm.
Từ sự kiện doanh nghiệp đến họp mặt công ty, Muong Thanh Luxury Cao Bang cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Muong Thanh Luxury Cao Bang, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Hãy tận hưởng trải nghiệm lưu trú có một không hai tại toà nhà mang đậm dấu ấn lịch sử của Muong Thanh Luxury Cao Bang, điều quý khách khó có thể tìm thấy tại bất kỳ đâu.
Nếu dự định có một kỳ nghỉ dài, thì Muong Thanh Luxury Cao Bang chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Muong Thanh Luxury Cao Bang sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Muong Thanh Luxury Cao Bang là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Hãy sẵn sàng đón nhận trải nghiệm khó quên bằng dịch vụ độc đáo và hoàn hảo của khách sạn cùng các tiện nghi đầy đủ, đáp ứng mọi nhu cầu của quý khách.
Trung tâm thể dục của khách sạn là một trong những tiện nghi không thể bỏ qua khi lưu trú tại đây.
Hưởng thụ một ngày thư thái đầy thú vị tại hồ bơi dù quý khách đang du lịch một mình hay cùng người thân.
Nhận ưu đãi đặc biệt dành cho các liệu pháp spa tinh tuý nhất giúp thư giãn tinh thần và làm tươi trẻ cơ thể.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Muong Thanh Luxury Cao Bang chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Hãy sẵn sàng đón nhận những giây phút vô giá khó phai trong suốt kỳ nghỉ của quý khách tại Muong Thanh Luxury Cao Bang.', 27, 1),
							(314, N'Bao Ngoc Diamond Hotel', N'124 phố Bế Văn Đàn, phường Hợp Giang, Phường Hợp Giang, Cao Bằng', '0904836215', 'baongocdiamondhotel@gmail.com', N'<h2>Giới thiệu Bao Ngoc Diamond Hotel</h2> <br><h3> Vị trí</h3><br>
Bao Ngoc Diamond Hotel toạ lạc tại khu vực / thành phố Phường Hợp Giang.
Có rất nhiều điểm tham quan lân cận như Tháp chàm Po Nagar ở khoảng cách 41,68 km, và Coc Bo Cave ở khoảng cách 41,68 km.
<h3>Thông tin về Bao Ngoc Diamond Hotel</h3><br>
Bao Ngoc Diamond Hotel là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Bao Ngoc Diamond Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Bao Ngoc Diamond Hotel chỉ dành riêng cho quý khách.
Bao Ngoc Diamond Hotel là lựa chọn lý tưởng cho những ai đang tìm kiếm một phòng nghỉ thoải mái với giá thành hợp lý.', 27, 1),
							(315, N'Kha Bản Homestay', N'No. 048, Lane 03, Pac Bo, Song Bang Ward, 1, Phường Sông Bằng, Cao Bằng', '0937160635', 'khabanhomestay@gmail.com', N'<h2>Giới thiệu Kha Bản Homestay</h2> <br><h3> Vị trí</h3><br>
Homestay Kha Ban toạ lạc tại khu vực / thành phố Phường Sông Bằng.
Có rất nhiều điểm tham quan lân cận như Phia Đén ở khoảng cách 40,7 km, và Pac Bo Historical Site ở khoảng cách 40,62 km.
<h3>Thông tin về Homestay Kha Ban</h3><br>
Dịch vụ tuyệt vời, cơ sở vật chất hoàn chỉnh và các tiện nghi nơi nghỉ cung cấp sẽ khiến quý khách không thể phàn nàn trong suốt kỳ lưu trú tại Homestay Kha Ban.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Với những tiện nghi sẵn có Homestay Kha Ban thực sự là một nơi lưu trú hoàn hảo.
', 27, 2),
							(316, N'Max Boutique Hotel', N'117 Vườn Cam, Phường Hợp Giang, Cao Bằng', '0933534935', 'maxboutiquehotel@gmail.com', N'<h2>Giới thiệu Max Boutique Hotel</h2> <br><h3> Vị trí</h3><br>
Max Boutique Hotel là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Phường Hợp Giang.
Không chỉ sở hữu vị trí đắc địa, Max Boutique Hotel còn là một trong những khách sạn nằm cách Phia Đén chưa đầy 40,42 km và Pac Bo Historical Site chưa đầy 40,59 km.
<h3>Thông tin về Max Boutique Hotel</h3><br>
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Max Boutique Hotel
Max Boutique Hotel là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.
Tọa lạc gần sân bay, Max Boutique Hotel là nơi nghỉ ngơi lý tưởng trong lúc quý khách đang chờ chuyến bay kế tiếp. Quý khách có thể tận hưởng không gian nghỉ dưỡng vừa ý nơi đây trong quá trình quá cảnh.
Khi lưu trú tại khách sạn thì nội thất và kiến trúc hẳn là hai yếu tố quan trọng khiến quý khách mãn nhãn. Với thiết kế độc đáo, Max Boutique Hotel mang đến không gian lưu trú làm hài lòng quý khách.
Từ sự kiện doanh nghiệp đến họp mặt công ty, Max Boutique Hotel cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Max Boutique Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Max Boutique Hotel chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Max Boutique Hotel sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Max Boutique Hotel là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Dịch vụ tuyệt vời, cơ sở vật chất hoàn chỉnh và các tiện nghi khách sạn cung cấp sẽ khiến quý khách không thể phàn nàn trong suốt kỳ lưu trú tại Max Boutique Hotel.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Max Boutique Hotel chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Max Boutique Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Với những tiện nghi sẵn có Max Boutique Hotel thực sự là một nơi lưu trú hoàn hảo.', 27, 2),
							(317, N'Sunny Hotel', N'40 pho Kim Dong, Phuong Hop Giang, Phường Hợp Giang, Cao Bằng', '0932649002', 'sunnyhotel@gmail.com', N'<h2>Giới thiệu Sunny Hotel</h2> <br><h3> Vị trí</h3><br>
SUNNY HOTEL toạ lạc tại khu vực / thành phố Phường Hợp Giang.
Có rất nhiều điểm tham quan lân cận như Tháp chàm Po Nagar ở khoảng cách 41,84 km, và Pac Bo Historical Site ở khoảng cách 40,9 km.
<h3>Thông tin về SUNNY HOTEL</h3><br>
Dịch vụ tuyệt vời, cơ sở vật chất hoàn chỉnh và các tiện nghi khách sạn cung cấp sẽ khiến quý khách không thể phàn nàn trong suốt kỳ lưu trú tại SUNNY HOTEL.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ SUNNY HOTEL chỉ dành riêng cho quý khách.
Với những tiện nghi sẵn có SUNNY HOTEL thực sự là một nơi lưu trú hoàn hảo.', 27, 3),
							(318, N'Luxury Hotel', N' Pho Kim Dong, Phuong Hop Giang, Phường Hợp Giang, Cao Bằng', '0907002649', 'luxuryhotel@gmail.com', N' <h2>Giới thiệu Luxury Hotel</h2> <br><h3> Vị trí</h3><br>
Luxury Hotel là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Phường Hợp Giang.
Không chỉ sở hữu vị trí đắc địa, Luxury Hotel còn là một trong những khách sạn nằm cách Phia Đén chưa đầy 40,28 km và Pac Bo Historical Site chưa đầy 41,01 km.
<h3>Thông tin về Luxury Hotel</h3><br>
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Luxury Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Với những tiện nghi sẵn có Luxury Hotel thực sự là một nơi lưu trú hoàn hảo.', 27, 3),
							(319, N'Jeanne Hotel', N'99 Kim Đồng, Hợp Giang, Phường Hợp Giang, Cao Bằng', '0763661837', 'jeannehotel@gmail.com', N'<h2>Giới thiệu Jeanne Hotel</h2> <br><h3> Vị trí</h3><br>
Lưu trú tại Jeanne Hotel là một lựa chọn đúng đắn khi quý khách đến thăm Phường Hợp Giang.
khách sạn này rất dễ tìm bởi vị trí đắc địa, nằm gần với nhiều tiện ích công cộng.
<h3>Thông tin về Jeanne Hotel</h3><br>
Jeanne Hotel là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một khách sạn vừa thoải mái lại hợp túi tiền.
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Jeanne Hotel sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Khi lưu trú tại khách sạn thì nội thất và kiến trúc hẳn là hai yếu tố quan trọng khiến quý khách mãn nhãn. Với thiết kế độc đáo, Jeanne Hotel mang đến không gian lưu trú làm hài lòng quý khách.
Từ sự kiện doanh nghiệp đến họp mặt công ty, Jeanne Hotel cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Jeanne Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Jeanne Hotel chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Jeanne Hotel sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Jeanne Hotel là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Jeanne Hotel là lựa chọn thông thái nhất cho những ai đang tìm kiếm một khách sạn với dịch vụ xuất sắc nhưng hợp với túi tiền.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Jeanne Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Lưu trú tại Jeanne Hotel chắc chắn sẽ làm quý khách hài lòng bởi sự đón tiếp nhiệt thành và mức giá dễ chịu của khách sạn.', 27, 4),
							(320, N'AK Homestay', N'Group 6, Hoa Chung Ward, Phường Hòa Chung, Cao Bằng', '0785946694', 'akhomestay@gmail.com', N'<h2>Giới thiệu AK Homestay</h2> <br><h3> Vị trí</h3><br>
AK Homestay - Cao Bang City là một nơi nghỉ nằm trong khu vực an ninh, toạ lạc tại Phường Hòa Chung.
Không chỉ sở hữu vị trí đắc địa, AK Homestay - Cao Bang City còn là một trong những nơi nghỉ nằm cách Phia Đén chưa đầy 39,23 km và Pac Bo Historical Site chưa đầy 41,31 km.
<h3>Thông tin về AK Homestay - Cao Bang City</h3><br>
Dịch vụ tuyệt vời, cơ sở vật chất hoàn chỉnh và các tiện nghi nơi nghỉ cung cấp sẽ khiến quý khách không thể phàn nàn trong suốt kỳ lưu trú tại AK Homestay - Cao Bang City.
Với những tiện nghi sẵn có AK Homestay - Cao Bang City thực sự là một nơi lưu trú hoàn hảo.', 27, 4),
							(321, N'Gia Quý Hotel', N'007 Hoang Dinh Giong Street, Hop Giang District, Phường Hợp Giang, Cao Bằng', '0939398001', 'giaquyhotel@gmail.com', N'<h2>Giới thiệu Gia Quý Hotel</h2> <br><h3> Vị trí</h3><br>
Gia Quy Hotel toạ lạc tại khu vực / thành phố Phường Hợp Giang.
Có rất nhiều điểm tham quan lân cận như Phia Đén ở khoảng cách 40,06 km, và Pac Bo Historical Site ở khoảng cách 40,61 km.
<h3>Thông tin về Gia Quy Hotel</h3><br>
Dịch vụ tuyệt vời, cơ sở vật chất hoàn chỉnh và các tiện nghi khách sạn cung cấp sẽ khiến quý khách không thể phàn nàn trong suốt kỳ lưu trú tại Gia Quy Hotel.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Gia Quy Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Với những tiện nghi sẵn có Gia Quy Hotel thực sự là một nơi lưu trú hoàn hảo.', 27, 5),
							(322, N'Highlands Hotel Cao Bằng', N'019 Ly Tu Trong, Hop Giang, Phường Hợp Giang, Cao Bằng', '0903837257', 'highlandshotelcaobang@gmail.com', N'<h2>Giới thiệu Highlands Hotel Cao Bằng</h2> <br><h3> Vị trí</h3><br>
Highlands Hotel Cao Bang - Hostel là một nơi nghỉ nằm trong khu vực an ninh, toạ lạc tại Phường Hợp Giang.
Không chỉ sở hữu vị trí đắc địa, Highlands Hotel Cao Bang - Hostel còn là một trong những nơi nghỉ nằm cách Phia Đén chưa đầy 40,06 km và Pac Bo Historical Site chưa đầy 40,29 km.
<h3>Thông tin về Highlands Hotel Cao Bang - Hostel</h3><br>
Highlands Hotel Cao Bang - Hostel là lựa chọn thông thái nhất cho những ai đang tìm kiếm một nơi nghỉ với dịch vụ xuất sắc nhưng hợp với túi tiền.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Highlands Hotel Cao Bang - Hostel là lựa chọn sáng suốt dành cho những du khách ghé thăm Phường Hợp Giang.', 27, 5),
							(323, N'Hưng Thịnh Hotel', N'29 Lê Lợi, Phường Sông Bằng, Cao Bằng', '0899544447', 'hungthinhhotel@gmail.com', N'<h2>Giới thiệu Hưng Thịnh Hotel</h2> <br><h3> Vị trí</h3><br>
Hung Thinh Hotel Cao Bang toạ lạc tại khu vực / thành phố Phường Sông Bằng.
Có rất nhiều điểm tham quan lân cận như Phia Đén ở khoảng cách 41,02 km, và Pac Bo Historical Site ở khoảng cách 40,66 km.
<h3>Thông tin về Hung Thinh Hotel Cao Bang</h3><br>
Không chỉ sở hữu vị trí giúp quý khách dễ dàng ghé thăm những địa điểm lý thú trong chuyến hành trình, Hung Thinh Hotel Cao Bang cũng sẽ mang đến cho quý khách trải nghiệm lưu trú mỹ mãn.
Hung Thinh Hotel Cao Bang là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một khách sạn vừa thoải mái lại hợp túi tiền.
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Hung Thinh Hotel Cao Bang sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Hung Thinh Hotel Cao Bang, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Hung Thinh Hotel Cao Bang chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Hung Thinh Hotel Cao Bang sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Hung Thinh Hotel Cao Bang là lựa chọn đúng đắn cho những ai đang tìm kiếm các khách sạn hợp với túi tiền tại Phường Sông Bằng.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Hung Thinh Hotel Cao Bang là lựa chọn lý tưởng cho những ai đang tìm kiếm một phòng nghỉ thoải mái với giá thành hợp lý.', 27, 6),
							(324, N'Thành Loan Hotel', N'131 Vuon Cam Street, Group 23, Hop Giang Ward, Phường Hợp Giang, Cao Bằng', '0899968779', 'thanhloanhotel@gmail.com', N'<h2>Giới thiệu Thành Loan Hotel</h2> <br><h3> Vị trí</h3><br>
Thanh Loan Hotel là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Phường Hợp Giang.
Không chỉ sở hữu vị trí đắc địa, Thanh Loan Hotel còn là một trong những khách sạn nằm cách Phia Đén chưa đầy 40,45 km và Pac Bo Historical Site chưa đầy 40,62 km.
<h3>Thông tin về Thanh Loan Hotel</h3><br>
Thanh Loan Hotel là lựa chọn thông thái nhất cho những ai đang tìm kiếm một khách sạn với dịch vụ xuất sắc nhưng hợp với túi tiền.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Thanh Loan Hotel là lựa chọn sáng suốt dành cho những du khách ghé thăm Phường Hợp Giang.', 27, 6),

							(325, N'Phú Thắng Grand Hotel', N'Khu Công Nghiệp Tân Đô, Xã Bình Tiên 2, Đức Hòa, Đức Hòa, Tỉnh Long An', '0785600600', 'phuthanggrandhotel@gmail.com', N'<h2>Giới thiệu Phú Thắng Grand Hotel</h2> <br><h3> Vị trí</h3><br>
Phu Thang Grand Hotel là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Đức Hòa.
Không chỉ sở hữu vị trí đắc địa, Phu Thang Grand Hotel còn là một trong những khách sạn nằm cách Vườn thú Đại Nam chưa đầy 31,19 km và Cu Chi Tunnels chưa đầy 38,09 km.
<h3>Thông tin về Phu Thang Grand Hotel</h3><br>
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Phu Thang Grand Hotel sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Dù quý khách muốn tổ chức một sự kiện hay các dịp kỷ niệm đặc biệt khác, Phu Thang Grand Hotel là lựa chọn tuyệt vời cho quý khách với phòng chức năng rộng lớn, được trang bị đầy đủ để sẵn sàng đáp ứng mọi yêu cầu.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Phu Thang Grand Hotel
Phu Thang Grand Hotel là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.
Một trong những đặc điểm chính của khách sạn này là các liệu pháp spa đa dạng. Hãy nâng niu bản thân bằng các liệu pháp thư giãn, phục hồi giúp quý khách tươi trẻ thân, tâm.
Từ sự kiện doanh nghiệp đến họp mặt công ty, Phu Thang Grand Hotel cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Phu Thang Grand Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Phu Thang Grand Hotel chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Phu Thang Grand Hotel sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Phu Thang Grand Hotel là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Dịch vụ tuyệt vời, cơ sở vật chất hoàn chỉnh và các tiện nghi khách sạn cung cấp sẽ khiến quý khách không thể phàn nàn trong suốt kỳ lưu trú tại Phu Thang Grand Hotel.
Nhận ưu đãi đặc biệt dành cho các liệu pháp spa tinh tuý nhất giúp thư giãn tinh thần và làm tươi trẻ cơ thể.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Phu Thang Grand Hotel chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Phu Thang Grand Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Với những tiện nghi sẵn có Phu Thang Grand Hotel thực sự là một nơi lưu trú hoàn hảo.', 28, 1),
							(326, N'Lang Hoi Tan Lap Hotel', N'Quốc lộ 62, Ấp 3, Xã Tân Lập, Mộc Hóa, Tỉnh Long An', '093728777', 'langhoitanlap@gmail.com', N'<h2>Giới thiệu Lang Hoi Tan Lap Hotel</h2> <br><h3> Vị trí</h3><br>
Lang Noi Tan Lap Hotel là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Mộc Hóa.
Không chỉ sở hữu vị trí đắc địa, Lang Noi Tan Lap Hotel còn là một trong những khách sạn nằm cách Rừng Tràm chưa đầy 21,18 km và Cao Lãnh Bridge chưa đầy 49,71 km.
<h3>Thông tin về Lang Noi Tan Lap Hotel</h3><br>
Không chỉ sở hữu vị trí giúp quý khách dễ dàng ghé thăm những địa điểm lý thú trong chuyến hành trình, Lang Noi Tan Lap Hotel cũng sẽ mang đến cho quý khách trải nghiệm lưu trú mỹ mãn.
Lang Noi Tan Lap Hotel là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một khách sạn vừa thoải mái lại hợp túi tiền.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Lang Noi Tan Lap Hotel
Từ sự kiện doanh nghiệp đến họp mặt công ty, Lang Noi Tan Lap Hotel cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Lang Noi Tan Lap Hotel, một khách sạn tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Lang Noi Tan Lap Hotel chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Lang Noi Tan Lap Hotel sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Lang Noi Tan Lap Hotel là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.
Khách sạn này là nơi tốt nhất dành cho những ai mong muốn một nơi thanh bình, thư thái để ẩn mình khỏi đám đông ồn ã, xô bồ.
Nhận ưu đãi đặc biệt dành cho các liệu pháp spa tinh tuý nhất giúp thư giãn tinh thần và làm tươi trẻ cơ thể.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Lang Noi Tan Lap Hotel chỉ dành riêng cho quý khách.
Sóng WiFi phủ khắp các khu vực chung của khách sạn cho phép quý khách luôn kết nối với gia đình và bè bạn.
Lang Noi Tan Lap Hotel là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Lang Noi Tan Lap Hotel là lựa chọn sáng suốt dành cho những du khách ghé thăm Mộc Hóa.', 28, 1),
							(327, N'Lucky Ruby Border Casino', N'Quoc lo 62, Au village, Thmei Commune, Kien Tuong Town, Tỉnh Long An', '0785120787', 'luckyrubybordercasino@gmail.com', N'<h2>Giới thiệu Lucky Ruby Border Casino</h2> <br><h3> Vị trí</h3><br>
Lưu trú tại Lucky Ruby Border Casino là một lựa chọn đúng đắn khi quý khách đến thăm Kien Tuong Town.
khách sạn này rất dễ tìm bởi vị trí đắc địa, nằm gần với nhiều tiện ích công cộng.
<h3>Thông tin về Lucky Ruby Border Casino</h3><br>
Dịch vụ tuyệt vời, cơ sở vật chất hoàn chỉnh và các tiện nghi khách sạn cung cấp sẽ khiến quý khách không thể phàn nàn trong suốt kỳ lưu trú tại Lucky Ruby Border Casino.
Nhận ưu đãi đặc biệt dành cho các liệu pháp spa tinh tuý nhất giúp thư giãn tinh thần và làm tươi trẻ cơ thể.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Lucky Ruby Border Casino chỉ dành riêng cho quý khách.
Với những tiện nghi sẵn có Lucky Ruby Border Casino thực sự là một nơi lưu trú hoàn hảo.', 28, 2),
							(328, N'Cherry Hotel Bến Lức', N' Nguyễn Văn Tiếp 11 01 đường số 11, kdc đường 10 Bến Lức, Bến Lức', '0933978867', 'cherryhotelbenluc@gmail.com', N'Nằm ở thị trấn Bến Lức, Cherry Hotel Bến Lức cung cấp chỗ nghỉ với ban công riêng. Chỗ nghỉ này có lễ tân 24 giờ và dịch vụ phòng cho khách. Chỗ ở tại Cherry Hotel Bến Lức được trang bị TV màn hình phẳng, máy điều hòa và tủ để quần áo. Chỗ nghỉ có sân hiên. Thành phố Hồ Chí Minh nằm trong bán kính 32 km từ Cherry Hotel Bến Lức. Sân bay gần nhất là sân bay quốc tế Tân Sơn Nhất, cách khách sạn 26 km. Các cặp đôi đặc biệt thích địa điểm này — họ cho điểm 9,0 cho kỳ nghỉ dành cho 2 người.', 28, 2),
							(329, N'Trí Tâm Hotel', N'Số 10, Đường số 9, KDC Đường 10, thị trấn Bến Lức, huyện Bến Lức, tỉnh Long An', '0936939416', 'tritamhotel@gmail.com', N'Tọa lạc tại Bến Lức, cách Công viên Văn hóa Đầm Sen 24 km, TRÍ TÂM KHÁCH SẠN - Khách sạn TRÍ TÂM Bến Lức cung cấp chỗ nghỉ với sân hiên, chỗ đỗ xe riêng miễn phí và quầy bar. Nằm cách Chùa Giác Lâm khoảng 26 km, khách sạn có Wi-Fi miễn phí cũng cách Trung tâm Hội chợ và Triển lãm Sài Gòn 30 km. Có phòng tắm hơi, karaoke và dịch vụ phòng. Tại khách sạn, các phòng đều có tủ quần áo. Tất cả các phòng nghỉ tại TRÍ TÂM KHÁCH SẠN - Khách sạn TRÍ TÂM Bến Lức hoàn chỉnh với phòng tắm riêng với vòi sen và đồ vệ sinh cá nhân miễn phí, TV màn hình phẳng và máy điều hòa. Một số phòng chọn lọc còn có ban công. Tại chỗ ở, các phòng đều có khăn trải giường và khăn tắm. Khách sạn TRÍ TÂM Bến Lức phục vụ bữa sáng à la carte hàng ngày. Tại khách sạn, du khách có thể thoải mái sử dụng bồn tắm nước nóng. Lễ tân tại KHÁCH SẠN TRÍ TÂM - Khách sạn TRÍ TÂM Bến Lức có thể tư vấn về khu vực xung quanh. Bảo tàng Mỹ thuật cách chỗ nghỉ 32 km trong khi Chợ Ẩm thực Đường phố Bến Thành cách đó 32 km. Sân bay gần nhất là Sân bay Quốc tế Tân Sơn Nhất, cách KHÁCH SẠN TRÍ TÂM - Khách sạn TRÍ TÂM Bến Lức 28 km.', 28, 3),
							(330, N'Thanh Bình Hotel', N'8/4 Quốc lộ 1A, Khu phố 6, Thị trấn Bến Lức, Huyện Bến Lức, Long An', '0909415831', 'thanhbinhhotel@gmail.com', N'Nằm ở Bến Lức, cách Công viên Văn hóa Đầm Sen 26 km, Khách sạn Thanh Bình Bến Lức cung cấp chỗ nghỉ với vườn, chỗ đỗ xe riêng miễn phí và sân hiên. Nằm cách Chùa Giác Lâm khoảng 28 km, khách sạn có Wi-Fi miễn phí cũng cách Trung tâm Hội nghị và Triển lãm Sài Gòn 30 km. Chỗ ở này cung cấp dịch vụ phòng và quầy lễ tân 24 giờ cho khách. Phòng nghỉ tại khách sạn có máy điều hòa, bàn làm việc, phòng tắm riêng, TV màn hình phẳng, khăn trải giường và sân hiên nhìn ra sông. Tại Khách sạn Thanh Bình Bến Lức, mỗi phòng đều có khu vực ghế ngồi. Bảo tàng Mỹ thuật cách chỗ nghỉ 32 km trong khi Chợ Ẩm thực Đường phố Bến Thành cách đó 32 km. Sân bay gần nhất là Sân bay Quốc tế Tân Sơn Nhất, cách Khách sạn Thanh Bình Bến Lức 30 km.', 28, 3),
							(331, N'Hải Đăng Hotel', N'1073 QL1A, Khánh Hậu, Tân An, Long An', '0777070110', 'haidanghotel@gmail.com', N'Với khu vườn, Hải Đăng Hotel nằm ở xã Tân Hương. Chỗ đậu xe riêng có thể được sắp xếp với một khoản phụ phí. Tại khách sạn, mỗi phòng đều có máy điều hòa, bàn làm việc, TV màn hình phẳng, phòng tắm riêng, khăn trải giường, khăn tắm và sân hiên nhìn ra vườn. Các căn hộ sẽ cung cấp cho khách tủ quần áo và ấm đun nước. Nhân viên nói tiếng Anh và tiếng Việt luôn sẵn sàng trợ giúp tại quầy lễ tân. Sân bay gần nhất là Sân bay Quốc tế Tân Sơn Nhất, cách Hải Đăng Hotel 57 km.', 28, 4),
							(332, N'An Khang Hotel', N'
736 TL 825, Village 5, Duc Hoa Dong, Đức Hòa, tỉnh Long An', '0938446678', 'ankhanghotel@gmail.com', N'Các điểm du lịch lân cận bao gồm Tha La Xom Dao (5,9 dặm), Một số tiện nghi phổ biến của khách sạn bao gồm wifi miễn phí, xe đưa đón đến sân bay và dịch vụ phòng.
, Nằm gần các nhà hàng như Cơm Niêu Thiên Lý, Nhà Hàng Hoàng Yến và Trịnh Café, Có, khách có thể sử dụng phòng tắm hơi trong khi lưu trú, Có, An Khang Hotel có cung cấp dịch vụ đưa đón tại sân bay cho khách. Chúng tôi khuyến khích bạn gọi điện trước để xác nhận chi tiết, Có, An Khang Hotel cung cấp dịch vụ đưa đón tại sân bay cho khách. Chúng tôi khuyến khích bạn gọi điện trước để xác nhận chi tiết.', 28, 4),
							(333, N'Hoang De Hotel', N'67 Huynh Van Tao, Tân An, tỉnh Long An', '0903031231', 'hoangdehotel@gmail.com', N'Các điểm du lịch lân cận bao gồm Jump Arena (0,6 dặm) và Làng Nổi Tân Lập (0,6 dặm), Các tiện nghi hàng đầu trong phòng bao gồm quầy bar mini, điều hòa nhiệt độ và khu ghế ngồi, Nằm gần các nhà hàng như Nhà Hàng & Khách Sạn Thanh Vân 2, Bánh Mì Tuấn Mập Sài Gòn và Jisan - Bep Tra Truyen Thong', 28, 5),
							(334, N'Phượng Hoàng Hotel', N'Khu phố 3 trung tâm Thạnh Hóa, Huyện Thạnh Hóa, Tân An, Long anh', '0906908086', 'phuonghoanghotel@gmail.com', N'Đỗ xe và Wi-Fi luôn miễn phí, vì vậy quý khách có thể giữ liên lạc, đến và đi tùy ý. Nằm ở vị trí trung tâm, chỗ nghỉ này đặt quý khách ở gần các điểm thu hút và tùy chọn ăn uống thú vị. Xông khô, phòng xông ướt và nhà hàng là một trong những tiện nghi đặc biệt sẽ nâng cao kỳ nghỉ của quý khách với sự tiện lợi ngay trong khuôn viên.', 28, 5),
							(335, N'Lucky89 Border Casino', N'09 đường số 3, Khu dân cư Nam Long, Ấp 4, Xã An Thạnh, Huyện Bến Lức, Tỉnh Long An', '0902853677', 'lucky89bordercasino@gmail.com', N'<h2>Giới thiệu Lucky89 Border Casino</h2> <br><h3> Vị trí</h3><br>
Lucky89 Border Casino là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Kien Tuong Town.
Không chỉ sở hữu vị trí đắc địa, Lucky89 Border Casino còn là một trong những khách sạn nằm cách Tram Chim National Park chưa đầy 48,02 km và Rừng Tràm chưa đầy 19,05 km.
<h3>Thông tin về Lucky89 Border Casino</h3><br>
Trung tâm thể dục của khách sạn là một trong những tiện nghi không thể bỏ qua khi lưu trú tại đây.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Lucky89 Border Casino chỉ dành riêng cho quý khách.
Với những tiện nghi sẵn có Lucky89 Border Casino thực sự là một nơi lưu trú hoàn hảo.
', 28, 6),
							(336, N'Thùy Vân Hotel', N'Số 12 Đường Nguyễn Sĩ Cần Giuộc, Thị trấn Cần Giuộc, Huyện Cần Giuộc, Tỉnh Long An', '0937350389', 'thuyvanhotel@gmail.com', N'Đỗ xe và Wi-Fi luôn miễn phí, vì vậy quý khách có thể giữ liên lạc, đến và đi tùy ý. Nằm ở vị trí gần trung tâm, chỗ nghỉ này đặt quý khách ở gần các điểm thu hút và tùy chọn ăn uống thú vị. Xông khô, phòng xông ướt và nhà hàng là một trong những tiện nghi đặc biệt sẽ nâng cao kỳ nghỉ của quý khách với sự tiện lợi ngay trong khuôn viên', 28, 6),
							(337, N'Phương Lâm Hotel', N'11 Đường Nơ Trang Long, Dồng Xoài,, tỉnh Bình Phước', '0798575979', 'phuonglamhotel@gmail.com', N'Khách sạn Phương Lâm tọa lạc tại Đồng Xoài. Khách sạn 1 sao này cung cấp dịch vụ phòng, lễ tân 24 giờ và WiFi miễn phí. Chỗ ở này cung cấp máy ATM và chỗ để hành lý cho khách. Tại khách sạn, mỗi phòng đều có bàn làm việc, TV màn hình phẳng, phòng tắm riêng, bộ khăn trải giường và khăn tắm. Tất cả chỗ ở tại Khách sạn Phương Lâm đều có máy điều hòa và tủ quần áo. Sân bay gần nhất là Sân bay Quốc tế Tân Sơn Nhất, cách chỗ nghỉ 96 km.', 29, 1),
							(338, N'An Lộc Hotel', N'National Highway 13, Hung Chien Ward, Binh Long, tỉnh Bình Phước', '0909690101', 'anlochotel@gmail.com', N'Tọa lạc tại thị xã Bình Long, cách trung tâm thị xã 1,8 km, An Loc Hotel & Spa cung cấp chỗ nghỉ với nhà hàng, chỗ đỗ xe riêng miễn phí, trung tâm thể dục và quầy bar. Khách sạn 3 sao này có WiFi miễn phí, sảnh khách chung và vườn. Nơi đây cung cấp dịch vụ lễ tân 24 giờ, dịch vụ phòng và dịch vụ thu đổi ngoại tệ cho khách. Tất cả phòng nghỉ tại đây được trang bị máy điều hòa, TV truyền hình cáp màn hình phẳng, tủ lạnh, ấm đun nước, vòi sen, dép và bàn làm việc. Mỗi phòng đều có phòng tắm riêng, máy sấy tóc và ga trải giường. Khách nghỉ tại khách sạn có thể thưởng thức bữa sáng kiểu Á. Ấp Ðông Phất (2) nằm trong bán kính 2,2 km từ An Loc Hotel & Spa trong khi huyện Hớn Quản cách đó 3,3 km. Sân bay gần nhất là sân bay quốc tế Tân Sơn Nhất, cách chỗ nghỉ 102 km.', 29, 1),
							(339, N'Thanh Bình Hotel', N'Lô G06 Nguyễn Bỉnh Khiêm, Phường Hưng Chiến, thị xã Bình Long, tỉnh Bình Phước', '0933292954', 'thanhbinhhotel@gmail.com', N'KHÁCH SẠN THANH BÌNH, Bình Long nằm ở Bình Long và có quán bar. Chỗ ở này có dịch vụ phòng và quầy lễ tân 24 giờ cho khách. Hoàn chỉnh với phòng tắm riêng được trang bị chậu vệ sinh và đồ vệ sinh cá nhân miễn phí, tất cả các phòng tại khách sạn đều có TV màn hình phẳng và máy điều hòa. Một số phòng còn có sân hiên. Các căn hộ sẽ cung cấp cho khách tủ quần áo và ấm đun nước. Sân bay gần nhất là Sân bay Quốc tế Tân Sơn Nhất, cách KHÁCH SẠN THANH BÌNH, Bình Long 101 km.', 29, 2),
							(340, N'Hoàng Hùng Hotel', N'Ap 3B Quoc lo 13, Chơn Thành, Tỉnh Bình Phước', '0908091190', 'hoanghunghotel@gmail.com', N'<h2>Giới thiệu Hoàng Hùng Hotel</h2> <br><h3> Vị trí</h3><br>
Hoang Hung Hotel là một khách sạn nằm trong khu vực an ninh, toạ lạc tại Chơn Thành.
Không chỉ sở hữu vị trí đắc địa, Hoang Hung Hotel còn là một trong những khách sạn nằm cách Vườn thú Đại Nam chưa đầy 48,37 km và Ben Duoc Memorial Temple chưa đầy 41,06 km.
<h3>Thông tin về Hoang Hung Hotel</h3><br>
Hưởng thụ một ngày thư thái đầy thú vị tại hồ bơi dù quý khách đang du lịch một mình hay cùng người thân.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Tận hưởng những món ăn yêu thích với phong cách ẩm thực đặc biệt từ Hoang Hung Hotel chỉ dành riêng cho quý khách.
Hoang Hung Hotel là lựa chọn sáng suốt dành cho những du khách ghé thăm Chơn Thành.
', 29, 2),
							(341, N'Mỹ Lệ Hotel', N'04 Nguyễn Huệ, Phường Long Thủy, Phước Long, Phước Long, tỉnh Bình Phước', '0789888368', 'mylehotel@gmail.com', N'Hãy để chuyến đi của quý khách có một khởi đầu tuyệt vời khi ở lại khách sạn này, nơi có Wi-Fi miễn phí trong tất cả các phòng. Nằm ở vị trí trung tâm tại Phước Long của Phước Long (Bình Phước), chỗ nghỉ này đặt quý khách ở gần các điểm thu hút và tùy chọn ăn uống thú vị. Được xếp hạng 2 sao, chỗ nghỉ chất lượng cao này cho phép khách nghỉ sử dụng bể bơi trong nhà và nhà hàng ngay trong khuôn viên.', 29, 3),
							(342, N'Bombo Hotel', N'QL 14, thị xã Đông Xoài, tỉnh Bình Phước', '0784741390', 'bombohotel@gmail.com', N'Khách sạn BomBo đạt tiêu chuẩn 2 sao ở Bình Phước tọa lạc ngay trung tâm thị xã Đồng Xoài. Khách sạn là một công trình kiến trúc quy mô hiện đại, phòng nghỉ đạt tiêu chuẩn quốc tế, cùng với hệ thông nhà hàng phục vụ Buffet sáng, tiệc cưới, hội trường, cà phê sân thượng, khu spa, internet wifi miễn phí… tất cả nội thất bên trong rất sạng trọng, tiện nghi và không gian ấm cúng. Ngoài ra, khách sạn có nhà hàng phục vụ các món Á – Âu đặc sắc do các đầu bếp có kinh nghiệm chế biến và phục vụ. Cùng với đó là đội ngũ nhân viên chuyên nghiệp, thân thiện chắc chắn sẽ mang đến cho du khách dịch vụ nghỉ dưỡng trọn vẹn nhất, Tất cả các phòng được trang bị một chiếc giường đôi thích hợp cho những kế hoạch ở lại hơn. Phòng tại khách sạn Bombo được trang bị bàn làm việc, phòng tiếp khách, tủ quần áo, phòng tắm được trang bị thêm dầu gọi đầu, dầu xả, sửa tắm, bàn chải đánh răng, khăn tắm … Đặc biệt hệ thống cửa sổ được lắp theo tiêu chuẩn châu Âu. Chăn ga, gối, nệm nhập khẩu từ Singapore… tất cả đều tiện nghi hy vọng mang đến trải nghiệm tốt nhất cho du khách.', 29, 3),
							(343, N'Vân Anh Hotel', N' 121, đường Hùng Vương, Tân Bình, Đồng Xoài, Bình Phước', '0786038074', 'vananhhotel@gmail.com', N'Là một trong những khách sạn đi vào hoạt động sớm nhất tại thị xã Đồng Xoài, khách sạn Vân Anh uy tín được nhiều du khách lựa chọn khi đến Bình Phước. Tọa lạc giữa lòng một thị xã đang phát triển và có sức vươn mạnh mẽ, khách sạn Vân Anh lại mang kiến trúc cổ kính, thâm trầm như tách biệt khởi không khí ồn ào, náo nhiệt. Khách sạn gồm 40 phòng nghỉ được bày trí đơn giản nhưng cũng không kém phần sang trọng với đầy đủ trang thiết bị, tiện nghi. Nhờ có vị trí trung tâm thị xã Đồng Xoài, từ khách sạn Vân Anh, du khách có thể di chuyển đến nhiều địa điểm du lịch, vui chơi như: vườn quốc gia Tây Cát Tiên, nhà máy thủy điện Thác Mơ, các khu du lịch sinh thái như Bàu Ké, Bà Rá- Thác Mơ, Tất cả các phòng tại khách sạn Vân Anh đều rất rộng rãi, thoáng mát, đầy đủ tiện nghi hiện và có hướng nhìn đẹp. Khách sạn có chỗ đậu xe rộng rãi miễn hí, có dịch vụ trông trẻ, wifi miễn phí, có chuỗi nhà hàng, quán cafe sang trọng, cửa hàng mua sắm phục vụ nhu cầu của du khách. Ngoài ra, khách sạn có dịch vụ đưa đón tại sân bay, tour du lịch cũng được đánh giá cao. Khách sạn có phòng gia đình 4 người rộng rãi, hiện đại với trang bị đầy đu tiện nghi cơ bản nhất.', 29, 4),
							(344, N'Hương Sen Hotel', N'Đường Nguyễn Huệ, phường Tân Xuân, thị xã Đồng Xoài, tỉnh Bình Phước', '0934055702', 'huongsenhotel@gmail.com', N'Tọa lạc trên đường Nguyễn Huệ, P. Tân Xuân, TX. Đồng Xoài, Bình Phước từ lâu Khách sạn Hương Sen đã là điểm đến quen thuộc với nhiều du khách. Khách sạn có không gian ấm cúng, bình yên và thoải mái, thích hợp với gia đình nghỉ dưỡng. Khách sạn gồm 22 phòng với sức chứa từ 80 – 90 khách. Hương Sen là khách sạn Bình Phước giá rẻ trong thành phố. Tuy nhiên, chất lượng của khách sạn là không thể bàn cải. Cùng khuôn viên rộng rãi thoáng đãng có bãi đậu xe an toàn và tiện, Khách sạn Hương Sen chỉ đạt tiêu chuẩn 1 sao, nhưng các phòng nghỉ đều được trang bị những tiện nghi cao cấp, đẹp và phong cách riêng mang đến cảm giác ấm cúng. Khách sạn Hương Sen hướng tới sự hoàn mũ cùng với các loại hình dịch vụ chất lượng. Nhằm tiến tới xây dựng một khách sạn mang tính văn hóa, thân thiện, hiếu khách qua đó mang thương hiệu Hương Sen mãi vươn xa, xứng tầm với một thành phố đang phát triển và sẽ là nơi lưu giữ những kỷ niệm đẹp của du khách dành cho Hương Sen.', 29, 4),
							(345, N'Ngọc Trâm Hotel', N'353 Quốc lộ 14- Phường Tân Thiện, thị xã Đồng Xoài, tỉnh Bình Phước', '0777393839', 'ngoctramhotel@gmail.com', N'Khách sạn Ngọc Trâm tọa lạc ở trung tâm thị xã Đồng Xoài, tỉnh Bình Phước. Được thiết kế theo phong cách hiện đại với giá thành hợp lý, vậy nên khách sạn Ngọc Trâm là một trong những khách sạn ở Bình Phước thường xuyên hết phòng. Khách sạn Ngọc Trâm mang đến một khung cảnh thoáng mát, yên tĩnh, phòng đầy đủ tiện nghi, cùng đội ngũ nhân viên lịch sự, phục vụ tận tình chu đáo mang đến trải nghiệm trọn vẹn cho du khách, Khách sạn Ngọc Trâm có hệ thống phòng ốc đầy đủ tiện nghi như truyền hình kỹ thuật số, tivi thông minh điều khiển bằng giọng nói, tủ lạnh với nhiều đồ uống, điều hòa. Phòng tắm cugnx rất sang trọng, sạch sẽ. Giữa các phòng sử dụng vách cách tâm tuyệt đối, tạo sự yên tĩnh, riêng tư cho mỗi phòng. Bên cạnh đó, khách sạn còn cung cấp dịch vụ tư vấn du lịch, cho thuê xe tự lái và đưa đón tận nơi. Đội ngũ nhân viên tận tâm, và giàu lòng hiếu khách đón tiếp Quý khách như người thân trong gia đình.', 29, 5),
							(346, N'Ban Mai Hotel', N'9 Lê Duẩn Tân Phú Đồng Xoài Bình Phước', '0899126455', 'banmaihotel@gmail.com', N'Cũng như nhiều khách sạn khác tại thành phố Đồng Xoài, Bình Phước, khách sạn Ban Mai mang đến sự hài lòng cho du khách về hai tiêu chí là giá cả và phục vụ. Ban Mai Hotel có hệ thống phòng nghỉ đầy đủ tiện nghi, wifi miễn phí trong toàn bộ khuôn viên, cùng lối thiết kế mang đến sự ấm áp, gần gũi với thiên nhiên rất được nhiều du khách ưa chuộng và đánh giá cao. Mọi phòng ở Ban Mai đều có không gian thoải mái, phù hợp với túi tiền của nhiều đối tượng khác nhau, Với mức giá hợp lý, nhưng mỗi phòng tại khách sạn Ban Mai đều được trang bị đầy đủ các thiết bị tiện nghi hiện đại và có thêm hệ thống nước nóng năng lượng mặt trời, tivi màn hình phẳng, giường gỗ tự nhiên,… bãi xe máy rộng, có cả bãi đỗ xe tải, ôtô…, miễn phí mạng wifi cho các phòng. Vì vậy, khách sạn sẽ là lựa chọn tuyệt vời cho chuyến đi thêm thú vị. Cùng với đó là đội ngũ nhân viên chuyên nghiệp, nhiệt thành sẽ mang lại những trải nghiệm trọn vẹn cho du khách.', 29, 5),
							(347, N'Phương Trang Hotel', N'Căn Số 18 Quốc Lộ 14 Phú Thanh -Phường Tân Phú – Đồng Xoài – Bình Phước.', '0909899353', 'phuongtranghotel@gmail.com', N'Khách sạn Phương Trang tọa lạc tại số 18 Quốc Lộ 14 Phú Thanh, Phường Tần Phú, Đồng Xoài, Bình Phước. Khách sạn được thiết kế theo phong cách hiện đại, đầy đủ tiện nghi phục vụ chuyên nghiệp cho khách du lịch và khách thương mại. Mọi phòng nghỉ ở khách sạn đều thoáng mát, thoải mái, thoáng đãng tao cho du khách cảm giác dễ chịu và hài lòng. Khách sạn Phương Trang gồm nhiều hạng phòng, trong đó phòng 1 giường, vip 1 giường, 2 giường, vip 2 giường, 03 giường chúng tôi sử dụng nệm Kymdan chất lượng cao. Ngoài ra, phòng sang trọng với thiết kế cửa sổ mở rộng, mang tới không gian thoáng mát yên bình và vẻ đẹp tràn đầy sinh khí cho căn phòng, Các phòng tại khách sạn Phương Trang có đầy đủ trang bị hiện đại như: truyền hình cap, điều hòa, điện thoại, vòi tắm hoa sen, bàn tiếp khách, bàn trang điểm, wifi miễn phí trong toàn bộ khuôn viên… Khách sạn có bãi đậu xe rộng rãi, đội ngũ nhân biên được đào tạo chuyên nghiệp, phục vụ tận tình sẽ làm du khách hài lòng. Các cặp đôi đặc biệt thích địa điểm Khách sạn Phương Trang. Chỗ nghỉ này cũng được đánh giá là đáng giá tiền nhất ở quanh khu vực, du khách sẽ tiết kiệm được nhiều hơn so với các chỗ nghỉ khác.', 29, 6),
							(348, N'Hùng Vương Hotel', N'số 72 – 74 Hùng Vương, phường Tân Bình, thị xã Đồng Xoài, Bình Phước', '0909793960', 'hungvuonghotel@gmail.com', N'Khách sạn Ngọc Lan đạt tiêu chuẩn 5 sao ở Bình Phước. Khách sạn được nhiều du khách gần xa lựa chọn làm nơi lưu trú, nghỉ ngơi khi du lịch đến mảnh đất này. Lưu trú tại khách sạn, du khách chỉ mất vài phut để di chuyển đến các địa điểm du lịch ở Bình Phước. Ngoài ra, khách sạn còn trang bị nhiều xe đua đón tận nơi nhằm mang đến sự thuận lợi và thoải mái nhất cho du khách, Tuy không gian sở hữu không gian sang trọng lộng lẫy nhưng khách sạn được đánh giá khá cao bởi diện tích rộng rãi, thoáng đãng cùng đầy đủ tiện nghỉ. Mỗi phòng tại khách sạn Ngọc Lan đều trang bị tivi màn hình phẳng, điều hòa, tủ lạnh, quạt máy… Cùng với đó là nhà tắm sạch sẽ, thơm tho khiến du khách đến ở khá hài lòng. Hơn nữa, khách sạn còn cung cấp nhiều dịch vụ tiện lợi như: Massage, giặt ủi, phòng gym, tư vấn du lịch… Ngoài ra, wifi tốc độ cùng bãi đậu xe rộng rãi cũng là điểm cộng khiến du khách rất thích lưu trú tại đây.', 29, 6),
							(349, N'Trường Huy Hotel', N'Tổ 1, ấp Tân Hưng, xã Trường An, Tp. Vĩnh Long, Vĩnh Long', '0904909194', 'truonghuyhotel@gmail.com', N'Tọa lạc tại trung tậm Tp. Vĩnh Long, khách sạn Trường Huy được thiết kế mang dấu ấn phong cách miệt vườn với nhà được kết cấu từ tre, các nội thất tối giản, không gian của khách sạn được bài trí giản dị, gần gũi với lối đi lát gạch, hồ cá, cây dừa, khóm trúc, tất cả như mang trọn miền Tây đến tại nơi nghỉ của bạn, Các phòng trang bị ti vi, máy lạnh, ấm nấu nước và các dụng cụ nhà tắm cần thiết. Ngoài ra, khách sạn còn cung cấp dịch vụ với nhà hàng chuyên phục vụ những món ăn dân dã, dịch vụ cho thuê xe máy cùng khu vui chơi biệt lập dành cho trẻ em, Với mức giá vừa phải lại được sống trong không gian tươi mát miệt vườn, Trường Huy hotel sẽ là lựa chọn thích hợp cho những bạn vừa muốn trải nghiệm cuộc sống sôi động mà vẫn muốn gần gũi với văn hóa sông nước độc đáo.', 30, 1),
							(350, N'Út Trinh Homestay', N'Ấp Hòa Quí, xã Hòa Ninh, huyện Long Hồ, Vĩnh Long', '0896613539', 'uttrinhhomestay@gmail.com', N'Lưu trú tại Út Trinh homestay với 11 phòng ở là cơ hội để du khách khám phá, hòa mình vào đời sống sinh hoạt của người dân vùng đồng bằng sông Cửu Long. Nơi đây gây ấn tượng với du khách bởi lối kiến trúc xưa Nam Bộ nhà ba gian truyền thống nép bên bóng dừa cùng mái ngói đặc trưng mà ít nơi nào còn lưu giữ, Vì là homestay nên các vật dụng trong phòng không được bài trí nhiều, thay vào đó là tận dụng không gian tươi mát, yên tĩnh mang lại cảm giác dễ chịu cho du khách. Đến với homestay du khách có thể thưởng thức các loại hoa quả tươi ngon ngay tại vườn nhà như chôm chôm, ổi, cóc, nhãn, măng cụt,…Tham gia hái rau xanh tại vườn cùng cô chủ, chuẩn bị những món ăn tiếp đãi du khách.', 30, 1),
							(351, N'Vĩnh Long Cửu Long Hotel', N'11 Tháng 5, Phường 1, Thành Phố Vĩnh Long, Tỉnh Vĩnh Long', '0903525130', 'vinhlongcuulonghotel@gmail.com', N'Khách sạn 1 sao Vĩnh Long này cũng cấp tiện nghi đầy đủ. Quầy lễ tân luôn phục vụ khách hàng 24/24. Đến với khách sạn Vĩnh Long này dịch vụ dọn dẹp phòng luôn được phục vụ miễn phí. Đặc biệt khách sạn có phòng dành cho gia đình, rất thuận tiện nếu cả nhà cùng nhau đi du lịch hoặc có những công việc riêng. Thức ăn tự chọn được phục vụ mỗi sáng rất tiện lợi cho các bạn ở xa đến đây lưu trú. Ngoài ra khách sạn có cả dịch vụ đưa đón khách đến sân bay giá hợp lý.
', 30, 2),
							(352, N'Vĩnh Long Ngũ Long Hotel', N'34 Trưng Nữ Vương, Thành Phố Vĩnh Long, Tỉnh Vĩnh Long', '0354337654', 'vinhlongngulonghotel@gmail.com', N'Được khách hàng đánh giá là khách sạn thân thiện, có hầm để xe riêng. Khách sạn nằm tại vị trí thuận lợi để du khách có thể du lịch các địa điểm nổi tiếng tại Vĩnh Long. Ở đây các bạn được thưởng thức điểm tâm trên các quán nổi trên sông, cũng là một điều thú vị khi đến đây. Phục vụ tại khách sạn rất chu đáo, giá cả hợp lý với mức tiền bạn chi trả. Có phòng dành cho gia đình, không gian thoáng mát. Ngũ Long là khách sạn hai sao nhưng rất được lòng du khách tại Vĩnh Long mà du khách không nên bỏ lỡ.', 30, 2),
							(353, N'Vĩnh Long Xuân Hương Hotel', N'245A Phạm Hùng, Phường 9, Thành Phố Vĩnh Long, Tỉnh Vĩnh Long', '0908229519', 'vinhlongxuanhuonghotel@gmail.com', N'Nội thất bên trong mang đến cho bạn cảm giác thân thuộc như đang ở nhà mình vậy. Có bàn làm việc nhỏ ngay bên trong phòng, điều này thuận tiện cho các bạn đang đi làm việc công tác tại Vĩnh Long, Khách sạn Xuân Hương có phòng karaoke tiêu chuẩn rất tốt. Nghỉ ngơi cùng bạn bè hoặc người thân làm vài bản tình ca cũng rất thú, thêm kỉ niệm đáng nhớ. Điểm cộng cho Xuân Hương Hotel là du khách không phải di chuyển xa, dễ dàng tiếp cận các điểm tham quan nổi tiếng.', 30, 3),
							(354, N'Tài Nguyên Hotel', N'60 Mậu Thân, Phường 3, Thành Phố Vĩnh Long, Tỉnh Vĩnh Long', '0938939956', 'tainguyenhotel@gmail.com', N'Ưu điểm vượt trội của khách sạn Tài Nguyên tại Vĩnh Long này chính là phục vụ các món ăn dân dã đậm đà hương sắc miền Tây. Sân thượng là quán cà phê thoáng mát, vừa thưởng thức đồ uống vừa trò chuyện hóng mát thì còn gì bằng. Từ khách sạn rất thuận tiện đến các địa danh du lịch nổi tiếng: khu du lịch sinh thái miệt vườn Vĩnh Long, cầu Mỹ Thuận, chùa Tiên Châu,… Tài Nguyên Hotel tuy là khách sạn hai sao nhưng phục vụ rất tốt ngoài sự mong đợi của Quý khách.', 30, 3),
							(355, N'Khánh Quỳnh Hotel', N'2A Hưng Đạo Vương, Phường 1, Thành Phố Vĩnh Long, Tỉnh Vĩnh Long', '0938577048', 'khanhquynhhotel@gmail.com', N'Khách sạn Khánh Quỳnh luôn được nhiều du khách lựa chọn khi bước chân đến Vĩnh Long. Nằm ngay trung tâm thành phố, là địa điểm thích hợp để di chuyển đến các nơi du lịch nổi tiếng. Khánh Quỳnh Hotel có dịch vụ thuê xe đạp nhằm phục vụ di khách muốn dạo quanh thăm thú ngắm nhìn thành phố. Tiện nghi luôn được phục vụ đầy đủ và chu đáo nhất, nhân viên lễ tân nhiệt tình hướng dẫn du khách nếu muốn du lịch tại các điểm nổi tiếng.', 30, 4),
							(356, N'Hoàng Hảo Hotel', N'234A Tân Vĩnh Thuận, Tân Ngãi, Thành Phố Vĩnh Long, Tỉnh Vĩnh Long', '0937093608', 'hoanghaohotel@gmail.com', N'Khách sạn Hoàng Hảo sẽ là sự lựa chọn tâm đắc dành cho bạn. Hotel là sự kết hợp hoàn hảo giữa mảng nhà hàng và khách sạn. Các bạn sẽ được phục vụ chu đáo và tận tâm từ nghỉ dưỡng đến khám phá ẩm thực. Phòng nghỉ được trang bị đầy đủ thiết bị, tiện nghi… đem đến những giây phút thoải mái cho du khách khi lưu trú tại đây. Khách sạn Vĩnh Long này cũng là một trong những hotel được nhiều người biết đến và được lưu tâm rất nhiều.', 30, 4),
							(357, N'Sài Gòn - Vĩnh Long Hotel', N'Số 2 Trưng Nữ Vương, phường 1, Tp. Vĩnh Long, Vĩnh Long', '0909824132', 'saigonvinhlonghotel@gmail.com', N'Khách sạn Sài Gòn – Vĩnh Long là khách sạn Vĩnh Long đầu tiên đạt tiêu chuẩn 4 sao quốc tế tại Vĩnh Long tính đến thời điểm hiện tại. Được đưa vào hoạt động từ tháng 6/2018, khách sạn thiết kế hiện đại theo phong cách châu Âu kết hợp hài hòa những nét đặc trưng trong văn hóa miền Tây, tất cả mang đến một không gian thân thuộc nhưng không kém phần sang trọng. 84 phòng nghỉ được trang bị tivi, tủ lạnh, máy lạnh cùng các đồ dùng tiện nghi với view hướng sông nước hữu tình. Ngoài ra khách sạn còn cung cấp sảnh tiệc phục vụ đám cưới, hội nghị, hội thảo với Citi Hall và Central Hall có sức chứa lên đến 1.600 khách. Hệ thống nhà hàng rộng rãi chuyên phục vụ các món ăn Âu – Á và các món đặc sản miền Tây. Hệ thống Queen Bar tại tầng 5 phục vụ du khách các loại thức uống tươi ngon được chế biến từ các bartender chuyên nghiệp, Sài Gòn – Vĩnh Long tọa lạc tại trung tâm Tp. Vĩnh Long, nằm bên bờ sông Cổ Chiên thơ mộng, cách chợ trung tâm Vĩnh Long chỉ vỏn vẹn vài phút đi bộ, nơi đây được đánh giá cao cả về địa lí, chất lượng phòng và thái độ phục vụ chuyên nghiệp từ đội ngũ nhân viên. Nếu đến Vĩnh Long, hãy thử một lần lưu trú tại đây để cảm nhận hết hương vị sông nước.', 30, 5),
							(358, N'Coco Riverside Loge', N'Ấp Phú An, xã Trung Nghĩa, huyện Vĩnh Liêm, Vĩnh Long', '0908196245', 'cocoriversideloge@gmail.com', N'Nếu đang mải miết kiếm tìm một chỗ lưu trú có thể thỏa sức “vùng vẫy” cùng sông nước miền Tây thì chắc chắn bạn không thể bỏ qua Coco Riverside Loge, khu nghỉ dưỡng 3 sao với các hoạt động miệt vườn đa dạng. Khu nghỉ có 4 bungalow rộng được thiết kế riêng biệt với phòng ngủ, nhà tắm và phòng bếp. Các phòng được thiết kế chủ yếu từ gỗ, tre, trúc mang du khách hòa mình cùng thiên nhiên. Ti vi, tủ lạnh, bàn trang điểm và các dụng cụ nhà tắm là thứ bạn có thể tìm thấy ở bất kì bungalow nào tại đây. Ngoài ra, nhà hàng hướng sông với hệ thống ghế ngồi thư giãn hứa hẹn sẽ mang du khách đến gần hơn với nếp sống sinh hoạt của người dân miền Tây. Lưu trú tại khách sạn Vĩnh Long này, ngoài việc được thưởng thức các món ăn dân dã, du khách còn được tham gia các hoạt động ngoài trời như đi ghe, đạp xe đạp quanh những đồng lúa xanh ngát, tham quan và thưởng thức trái cây ở những nhà vườn cùng các hoạt động làm bánh, kẹo tại các làng nghề truyền thống.', 30, 5),
							(359, N'Resort Vinh Sang', N'Tổ 14, Ấp An Thuận, Xã An Bình, Huyện Long Hồ, Vĩnh Long', '0787952911', 'resortvinhsang@gmail.com', N'oàn cảnh hotel – resort Vinh Sang là một nơi check in cực chất miền Tây sông nước. Gồm 12 phòng ngủ nằm đối diện cầu Mỹ Thuận, bồng bềnh mặt nước là những mảng lục bình xanh mướt bình yên trôi theo dòng nước. Du khách vừa được nghỉ dưỡng vừa được tận hưởng nhịp sống bình dị của ngư dân miền Tây. Nằm tại vị trí đẹp cùng không gian lãng mạn rất thích hợp với các cặp đôi. Lại nằm trong khu du lịch Vinh Sang rất tiện để du lịch sinh thái, tham gia các hoạt động giải trí.', 30, 6),
							(360, N'Riverside Park Eco Resort', N'283/20 Bình Hòa 2, Bình Hòa Phước,Long Hồ, TP. Vĩnh Long, Tỉnh Vĩnh Long', '0918755578', 'riversideparkecoresort@gmail.com', N'Thật tuyệt vời khi đến miền sông nước du ngoạn, trở về thành phố nghỉ dưỡng tại hotel -resort Riverside Park Eco. Các bạn sẽ được hòa mình vào thiên nhiên trọn vẹn ngay giữa lòng thành phố. Tiện nghi đầy đủ thì khỏi phải nói rồi, thêm cả các tiện ích tuyệt vời như: quán bar, sân vườn, bể bơi,… các bạn còn được sử dụng xe đạp miễn phí để tiện cho việc đi lại thăm thú những nơi gần nữa. Đã vậy du khách còn được tham gia thú vui câu cá tao nhã. Đảm bảo đến là mê không muốn về.', 30, 6);
