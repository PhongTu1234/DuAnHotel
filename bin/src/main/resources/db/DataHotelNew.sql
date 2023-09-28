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
CREATE TABLE HotelTypes (
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
    hotel_id INT,                           -- Khóa ngoại liên kết với bảng Khách sạn
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


-- Bảng Người dùng
CREATE TABLE Users (
    username VARCHAR(50) NOT NULL,          -- Tên người dùng
    email VARCHAR(100),                     -- Địa chỉ email của người dùng
	CMT VARCHAR(20) PRIMARY KEY,						-- Chứng minh thư của người dùng
    [password] VARCHAR(100),                -- Mật khẩu (được lưu dưới dạng mã hóa)
    phone_number VARCHAR(20),               -- Số điện thoại của người dùng
    access_level bit ,                      -- Quyền truy cập (người dùng thường, quản trị viên)
	created_at BIGINT,						--Thời gian tạo
	updated_at BIGINT,						--Thời gian chỉnh sửa
);
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

insert into HotelTypes values   (1, 1.2, 1694272166, 1694272231),
								(2, 1.7, 1694272166, 1694272231),
								(3, 2.2, 1694272166, 1694272231),
								(4, 3.0, 1694272166, 1694272231),
								(5, 4.9, 1694272166, 1694272231),
								(6, 4.0, 1694272166, 1694272231),
								(7, 3.6, 1694272166, 1694272231),
								(8, 4.4, 1694272166, 1694272231),
								(9, 5.0, 1694272166, 1694272231),
								(10, 3.8, 1694272166, 1694272231)

insert into Hotels values   (1, 'Thuy Van Hotel', N'10 Quang Trung phường 1 thành phố Đà Lạt', '012-345-6789', 'tuannhps21725@gmail.com', N'Sang Trong, đẹp đẽ', N'Free wifi,								security', 4.5, 1, 2, 1, 1694272166, 1694272231),
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
insert into Users values('user1', 'user1@example.com', '081456789375', 'hashed_password_1', '1234567890', 0, 1694272166, 1694272231),
						('user2', 'user2@example.com', '012456789375', 'hashed_password_2', '2345678901', 0, 1694272166, 1694272231),
						('user3', 'user3@example.com', '083456789375', 'hashed_password_3', '3456789012', 0, 1694272166, 1694272231),
						('user4', 'user4@example.com', '084456789375', 'hashed_password_4', '4567890123', 0, 1694272166, 1694272231),
						('user5', 'user5@example.com', '085456789375', 'hashed_password_5', '5678901234', 0, 1694272166, 1694272231),
						('user6', 'user6@example.com', '086456789375', 'hashed_password_6', '6789012345', 0, 1694272166, 1694272231),
						('user7', 'user7@example.com', '087456789375', 'hashed_password_7', '7890123456', 0, 1694272166, 1694272231),
						('user8', 'user8@example.com', '088456789375', 'hashed_password_8', '8901234567', 0, 1694272166, 1694272231),
						('user9', 'user9@example.com', '089456789375', 'hashed_password_9', '9012345678', 0, 1694272166, 1694272231),
						('user10', 'user10@example.com', '082156789375', 'hashed_password_10', '0123456789', 0, 1694272166, 1694272231),
						('admin1', 'admin1@example.com', '082256789375', 'hashed_admin_password_1', '1111111111', 1, 1694272166, 1694272231),
						('admin2', 'admin2@example.com', '082356789375', 'hashed_admin_password_2', '2222222222', 1, 1694272166, 1694272231),
						('admin3', 'admin3@example.com', '022456789375', 'hashed_admin_password_3', '3333333333', 1, 1694272166, 1694272231),
						('admin4', 'admin4@example.com', '082556789375', 'hashed_admin_password_4', '4444444444', 1, 1694272166, 1694272231),
						('admin5', 'admin5@example.com', '082656789375', 'hashed_admin_password_5', '5555555555', 1, 1694272166, 1694272231),
						('admin6', 'admin6@example.com', '082756789375', 'hashed_admin_password_6', '6666666666', 1, 1694272166, 1694272231),
						('admin7', 'admin7@example.com', '082856789375', 'hashed_admin_password_7', '7777777777', 1, 1694272166, 1694272231),
						('admin8', 'admin8@example.com', '082956789375', 'hashed_admin_password_8', '8888888888', 1, 1694272166, 1694272231),
						('admin9', 'admin9@example.com', '082416789375', 'hashed_admin_password_9', '9999999999', 1, 1694272166, 1694272231),
						('admin10', 'admin10@example.com', '082426789375', 'hashed_admin_password_10', '0000000000', 1, 1694272166, 1694272231),
						('user11', 'user11@example.com', '082436789375', 'hashed_password_11', '1234567890', 0, 1694272166, 1694272231),
						('user12', 'user12@example.com', '082446789375', 'hashed_password_12', '2345678901', 0, 1694272166, 1694272231),
						('user13', 'user13@example.com', '032456789375', 'hashed_password_13', '3456789012', 0, 1694272166, 1694272231),
						('user14', 'user14@example.com', '082466789375', 'hashed_password_14', '4567890123', 0, 1694272166, 1694272231),
						('user15', 'user15@example.com', '082476789375', 'hashed_password_15', '5678901234', 0, 1694272166, 1694272231),
						('user16', 'user16@example.com', '082486789375', 'hashed_password_16', '6789012345', 0, 1694272166, 1694272231),
						('user17', 'user17@example.com', '082496789375', 'hashed_password_17', '7890123456', 0, 1694272166, 1694272231),
						('user18', 'user18@example.com', '082451789375', 'hashed_password_18', '8901234567', 0, 1694272166, 1694272231),
						('user19', 'user19@example.com', '082452789375', 'hashed_password_19', '9012345678', 0, 1694272166, 1694272231),
						('user20', 'user20@example.com', '082453789375', 'hashed_password_20', '0123456789', 0, 1694272166, 1694272231),
						('user21', 'user21@example.com', '082454789375', 'hashed_password_21', '1234567890', 0, 1694272166, 1694272231),
						('user22', 'user22@example.com', '082455789375', 'hashed_password_22', '2345678901', 0, 1694272166, 1694272231),
						('user23', 'user23@example.com', '042456789375', 'hashed_password_23', '3456789012', 0, 1694272166, 1694272231),
						('user24', 'user24@example.com', '082457789375', 'hashed_password_24', '4567890123', 0, 1694272166, 1694272231),
						('user25', 'user25@example.com', '082458789375', 'hashed_password_25', '5678901234', 0, 1694272166, 1694272231),
						('user26', 'user26@example.com', '082459789375', 'hashed_password_26', '6789012345', 0, 1694272166, 1694272231),
						('user27', 'user27@example.com', '082456189375', 'hashed_password_27', '7890123456', 0, 1694272166, 1694272231),
						('user28', 'user28@example.com', '082456289375', 'hashed_password_28', '8901234567', 0, 1694272166, 1694272231),
						('user29', 'user29@example.com', '082456389375', 'hashed_password_29', '9012345678', 0, 1694272166, 1694272231),
						('user30', 'user30@example.com', '082456489375', 'hashed_password_30', '0123456789', 0, 1694272166, 1694272231),
						('user31', 'user31@example.com', '082456589375', 'hashed_password_31', '1234567890', 0, 1694272166, 1694272231),
						('user32', 'user32@example.com', '082456689375', 'hashed_password_32', '2345678901', 0, 1694272166, 1694272231),
						('user33', 'user33@example.com', '052456789375', 'hashed_password_33', '3456789012', 0, 1694272166, 1694272231),
						('user34', 'user34@example.com', '082456889375', 'hashed_password_34', '4567890123', 0, 1694272166, 1694272231),
						('user35', 'user35@example.com', '082456989375', 'hashed_password_35', '5678901234', 0, 1694272166, 1694272231),
						('user36', 'user36@example.com', '082456719375', 'hashed_password_36', '6789012345', 0, 1694272166, 1694272231),
						('user37', 'user37@example.com', '082456729375', 'hashed_password_37', '7890123456', 0, 1694272166, 1694272231),
						('user38', 'user38@example.com', '082456739375', 'hashed_password_38', '8901234567', 0, 1694272166, 1694272231),
						('user39', 'user39@example.com', '082456749375', 'hashed_password_39', '9012345678', 0, 1694272166, 1694272231),
						('user40', 'user40@example.com', '082456759375', 'hashed_password_40', '0123456789', 0, 1694272166, 1694272231),
						('user41', 'user41@example.com', '082456769375', 'hashed_password_41', '1234567890', 0, 1694272166, 1694272231),
						('user42', 'user42@example.com', '082456779375', 'hashed_password_42', '2345678901', 0, 1694272166, 1694272231),
						('user43', 'user43@example.com', '062456789375', 'hashed_password_43', '3456789012', 0, 1694272166, 1694272231),
						('user44', 'user44@example.com', '082456799375', 'hashed_password_44', '4567890123', 0, 1694272166, 1694272231),
						('user45', 'user45@example.com', '082456781375', 'hashed_password_45', '5678901234', 0, 1694272166, 1694272231),
						('user46', 'user46@example.com', '082456782375', 'hashed_password_46', '6789012345', 0, 1694272166, 1694272231),
						('user47', 'user47@example.com', '082456783375', 'hashed_password_47', '7890123456', 0, 1694272166, 1694272231),
						('user48', 'user48@example.com', '082456784375', 'hashed_password_48', '8901234567', 0, 1694272166, 1694272231),
						('user49', 'user49@example.com', '082456785375', 'hashed_password_49', '9012345678', 0, 1694272166, 1694272231),
						('user50', 'user50@example.com', '082456786375', 'hashed_password_50', '0123456789', 0, 1694272166, 1694272231),
						('user51', 'user51@example.com', '082456787375', 'hashed_password_51', '1234567890', 0, 1694272166, 1694272231),
						('user52', 'user52@example.com', '082456788375', 'hashed_password_52', '2345678901', 0, 1694272166, 1694272231),
						('user53', 'user53@example.com', '072456789375', 'hashed_password_53', '3456789012', 0, 1694272166, 1694272231),
						('user54', 'user54@example.com', '082456789175', 'hashed_password_54', '4567890123', 0, 1694272166, 1694272231),
						('user55', 'user55@example.com', '082456789275', 'hashed_password_55', '5678901234', 0, 1694272166, 1694272231),
						('user56', 'user56@example.com', '092456789375', 'hashed_password_56', '6789012345', 0, 1694272166, 1694272231),
						('user57', 'user57@example.com', '082456789475', 'hashed_password_57', '7890123456', 0, 1694272166, 1694272231),
						('user58', 'user58@example.com', '082456789575', 'hashed_password_58', '8901234567', 0, 1694272166, 1694272231),
						('user59', 'user59@example.com', '082456789675', 'hashed_password_59', '9012345678', 0, 1694272166, 1694272231),
						('user60', 'user60@example.com', '082456789775', 'hashed_password_60', '0123456789', 0, 1694272166, 1694272231),
						('user61', 'user61@example.com', '082456789875', 'hashed_password_61', '1234567890', 0, 1694272166, 1694272231),
						('user62', 'user62@example.com', '082456789975', 'hashed_password_62', '2345678901', 0, 1694272166, 1694272231),
						('user63', 'user63@example.com', '082456789315', 'hashed_password_63', '3456789012', 0, 1694272166, 1694272231),
						('user64', 'user64@example.com', '082456789325', 'hashed_password_64', '4567890123', 0, 1694272166, 1694272231),
						('user65', 'user65@example.com', '082456789335', 'hashed_password_65', '5678901234', 0, 1694272166, 1694272231),
						('user66', 'user66@example.com', '082456789345', 'hashed_password_66', '6789012345', 0, 1694272166, 1694272231),
						('user67', 'user67@example.com', '082456789355', 'hashed_password_67', '7890123456', 0, 1694272166, 1694272231),
						('user68', 'user68@example.com', '082456789365', 'hashed_password_68', '8901234567', 0, 1694272166, 1694272231),
						('user69', 'user69@example.com', '082456789385', 'hashed_password_69', '9012345678', 0, 1694272166, 1694272231),
						('user70', 'user70@example.com', '082456789395', 'hashed_password_70', '0123456789', 0, 1694272166, 1694272231);
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






