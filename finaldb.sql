USE [SWP123]
GO
/****** Object:  Table [dbo].[ACCOUNT]    Script Date: 7/20/2024 7:07:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ACCOUNT](
	[Account_id] [int] IDENTITY(1,1) NOT NULL,
	[first_name] [nvarchar](255) NULL,
	[last_name] [nvarchar](255) NULL,
	[Gmail] [nvarchar](max) NULL,
	[phone_number] [varchar](15) NULL,
	[isAdmin] [bit] NULL,
	[isUser] [bit] NULL,
	[passwordHash] [varchar](256) NULL,
	[isStaff] [bit] NULL,
 CONSTRAINT [PK__ACCOUNT__B19D4181D79DDBEB] PRIMARY KEY CLUSTERED 
(
	[Account_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CAMPSITE]    Script Date: 7/20/2024 7:07:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CAMPSITE](
	[Campsite_id] [int] IDENTITY(1,1) NOT NULL,
	[Price_id] [int] NULL,
	[Address] [nvarchar](255) NULL,
	[Name] [nvarchar](255) NULL,
	[Description] [nvarchar](max) NULL,
	[Status] [bit] NULL,
	[Image] [nvarchar](255) NULL,
	[Limite] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Campsite_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[COMMENT]    Script Date: 7/20/2024 7:07:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[COMMENT](
	[Comment_id] [int] IDENTITY(1,1) NOT NULL,
	[TimeStamp] [datetime] NULL,
	[Content] [nvarchar](max) NULL,
	[Account_id] [int] NULL,
	[CampsiteAddress] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[Comment_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FEEDBACK]    Script Date: 7/20/2024 7:07:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FEEDBACK](
	[Feedback_id] [int] IDENTITY(1,1) NOT NULL,
	[StarNumber] [int] NULL,
	[TimeStamp] [datetime] NULL,
	[Account_id] [int] NULL,
	[Content] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[Feedback_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GEAR]    Script Date: 7/20/2024 7:07:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GEAR](
	[Gear_id] [int] IDENTITY(1,1) NOT NULL,
	[Price_id] [int] NULL,
	[Name] [nvarchar](255) NULL,
	[Description] [nvarchar](max) NULL,
	[Image] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[Gear_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NOTIFICATION]    Script Date: 7/20/2024 7:07:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NOTIFICATION](
	[Notification_id] [int] IDENTITY(1,1) NOT NULL,
	[Sender] [int] NULL,
	[Receiver] [int] NULL,
	[Content] [nvarchar](max) NULL,
	[URL] [nvarchar](max) NULL,
	[TimeStamp] [datetime] NULL,
	[title] [nvarchar](255) NULL,
	[Status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[Notification_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ORDER_DETAIL]    Script Date: 7/20/2024 7:07:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ORDER_DETAIL](
	[OrderDetail_id] [int] IDENTITY(1,1) NOT NULL,
	[Orders_id] [int] NULL,
	[Quantity] [int] NULL,
	[Gear_id] [int] NULL,
	[SubAmount] [int] NULL,
 CONSTRAINT [PK__ORDER_DE__53D7B488DFAB1610] PRIMARY KEY CLUSTERED 
(
	[OrderDetail_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ORDERS]    Script Date: 7/20/2024 7:07:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ORDERS](
	[Orders_id] [int] IDENTITY(1,1) NOT NULL,
	[TimeStamp] [datetime] NULL,
	[Booker] [int] NULL,
	[Campsite_id] [int] NULL,
	[Quantity] [int] NULL,
	[StartDate] [datetime] NULL,
	[EndDate] [datetime] NULL,
	[ApproveStatus] [bit] NULL,
	[PaymentStatus] [bit] NULL,
	[TotalAmount] [int] NULL,
	[BookingPrice] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Orders_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PRICE]    Script Date: 7/20/2024 7:07:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PRICE](
	[Price_id] [int] IDENTITY(1,1) NOT NULL,
	[StartDate] [datetime] NULL,
	[EndDate] [datetime] NULL,
	[Price] [decimal](10, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[Price_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[VOUCHER]    Script Date: 7/20/2024 7:07:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[VOUCHER](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[code] [varchar](8) NULL,
	[vPercent] [int] NULL,
	[startDate] [datetime] NULL,
	[endDate] [datetime] NULL,
	[isUsed] [bit] NULL,
 CONSTRAINT [PK_Table_1] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[ACCOUNT] ON 

INSERT [dbo].[ACCOUNT] ([Account_id], [first_name], [last_name], [Gmail], [phone_number], [isAdmin], [isUser], [passwordHash], [isStaff]) VALUES (1, N'Admin', N'ad', N'admin@gmail.com', N'0762584567', 1, 0, N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 0)
INSERT [dbo].[ACCOUNT] ([Account_id], [first_name], [last_name], [Gmail], [phone_number], [isAdmin], [isUser], [passwordHash], [isStaff]) VALUES (2, N'Kim', N'Anh', N'user@gmail.com', N'0869381123', 0, 1, N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 0)
INSERT [dbo].[ACCOUNT] ([Account_id], [first_name], [last_name], [Gmail], [phone_number], [isAdmin], [isUser], [passwordHash], [isStaff]) VALUES (3, N'staff', N'1', N'staff@gmail.com', N'0123456789', 0, 0, N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 1)
SET IDENTITY_INSERT [dbo].[ACCOUNT] OFF
GO
SET IDENTITY_INSERT [dbo].[CAMPSITE] ON 

INSERT [dbo].[CAMPSITE] ([Campsite_id], [Price_id], [Address], [Name], [Description], [Status], [Image], [Limite]) VALUES (1, 31, N'Số 123 Đường Bên Sông 1', N'Khu Cắm Trại Bên Sông 1', N'Được đặt bên bờ sông, lý tưởng cho câu cá và thư giãn bên dòng sông số 1.', 1, N'campsite1.jpg', 0)
INSERT [dbo].[CAMPSITE] ([Campsite_id], [Price_id], [Address], [Name], [Description], [Status], [Image], [Limite]) VALUES (2, 32, N'Số 456 Đường Bên Sông 2', N'Khu Cắm Trại Bên Sông 2', N'Nghỉ ngơi bên bờ sông số 2 với cảnh quan hữu tình và yên bình.', 1, N'campsite2.jpg', 28)
INSERT [dbo].[CAMPSITE] ([Campsite_id], [Price_id], [Address], [Name], [Description], [Status], [Image], [Limite]) VALUES (3, 33, N'Số 789 Đường Bên Sông 3', N'Khu Cắm Trại Bên Sông 3', N'Nằm dưới bóng cây râm mát bên bờ sông số 3, phù hợp cho những ai thích yên tĩnh và thiên nhiên.', 1, N'campsite3.jpg', 39)
INSERT [dbo].[CAMPSITE] ([Campsite_id], [Price_id], [Address], [Name], [Description], [Status], [Image], [Limite]) VALUES (4, 34, N'Số 101 Đường Bên Sông 4', N'Khu Cắm Trại Bên Sông 4', N'Khám phá thiên nhiên hoang sơ và đắm mình trong không gian yên bình tại khu cắm trại số 4 bên bờ sông.', 1, N'campsite4.jpg', 18)
INSERT [dbo].[CAMPSITE] ([Campsite_id], [Price_id], [Address], [Name], [Description], [Status], [Image], [Limite]) VALUES (5, 35, N'Số 222 Đường Bên Sông 5', N'Khu Cắm Trại Bên Sông 5', N'Thư giãn và tận hưởng cuộc sống bên bờ sông số 5, với tiếng chim hót râm ran.', 1, N'campsite5.jpg', 60)
INSERT [dbo].[CAMPSITE] ([Campsite_id], [Price_id], [Address], [Name], [Description], [Status], [Image], [Limite]) VALUES (6, 36, N'Số 333 Đường Đỉnh Núi 1', N'Khu Nghỉ Dưỡng Đỉnh Núi 1', N'Đắm mình trong khung cảnh núi non hùng vĩ tại khu nghỉ dưỡng số 1 trên đỉnh núi.', 1, N'campsite6.jpg', 25)
INSERT [dbo].[CAMPSITE] ([Campsite_id], [Price_id], [Address], [Name], [Description], [Status], [Image], [Limite]) VALUES (7, 37, N'Số 444 Đường Đỉnh Núi 2', N'Khu Nghỉ Dưỡng Đỉnh Núi 2', N'Khám phá những con đường quanh co và cảnh đẹp bao quanh tại khu nghỉ dưỡng số 2 trên đỉnh núi.', 1, N'campsite7.jpg', 35)
INSERT [dbo].[CAMPSITE] ([Campsite_id], [Price_id], [Address], [Name], [Description], [Status], [Image], [Limite]) VALUES (8, 38, N'Số 555 Đường Đỉnh Núi 3', N'Khu Nghỉ Dưỡng Đỉnh Núi 3', N'Nhìn ra toàn cảnh của thung lũng và dòng sông từ khu nghỉ dưỡng số 3 trên đỉnh núi.', 1, N'campsite8.jpg', 15)
INSERT [dbo].[CAMPSITE] ([Campsite_id], [Price_id], [Address], [Name], [Description], [Status], [Image], [Limite]) VALUES (9, 39, N'Số 666 Đường Đỉnh Núi 4', N'Khu Nghỉ Dưỡng Đỉnh Núi 4', N'Thư giãn và tận hưởng không khí trong lành tại khu nghỉ dưỡng số 4 trên đỉnh núi.', 1, N'campsite9.jpg', 10)
INSERT [dbo].[CAMPSITE] ([Campsite_id], [Price_id], [Address], [Name], [Description], [Status], [Image], [Limite]) VALUES (10, 40, N'Số 777 Đường Đỉnh Núi 5', N'Khu Nghỉ Dưỡng Đỉnh Núi 5', N'Khám phá cảnh quan hoang sơ và trải nghiệm cuộc sống với thiên nhiên tại khu nghỉ dưỡng số 5 trên đỉnh núi.', 1, N'campsite10.jpg', 45)
INSERT [dbo].[CAMPSITE] ([Campsite_id], [Price_id], [Address], [Name], [Description], [Status], [Image], [Limite]) VALUES (11, 41, N'Số 888 Đường Biển 1', N'Khu Nghỉ Dưỡng Bên Biển 1', N'Tận hưởng không gian tĩnh lặng và sóng biển êm đều tại khu nghỉ dưỡng số 1 bên biển.', 1, N'campsite11.jpg', 22)
INSERT [dbo].[CAMPSITE] ([Campsite_id], [Price_id], [Address], [Name], [Description], [Status], [Image], [Limite]) VALUES (12, 42, N'Số 999 Đường Biển 2', N'Khu Nghỉ Dưỡng Bên Biển 2', N'Đắm mình trong không gian biển xanh ngát và cát trắng tại khu nghỉ dưỡng số 2 bên biển.', 1, N'campsite12.jpg', 18)
INSERT [dbo].[CAMPSITE] ([Campsite_id], [Price_id], [Address], [Name], [Description], [Status], [Image], [Limite]) VALUES (13, 43, N'Số 111 Đường Biển 3', N'Khu Nghỉ Dưỡng Bên Biển 3', N'Thư giãn và tận hưởng những giây phút bình yên bên bờ biển tại khu nghỉ dưỡng số 3.', 1, N'campsite13.jpg', 27)
INSERT [dbo].[CAMPSITE] ([Campsite_id], [Price_id], [Address], [Name], [Description], [Status], [Image], [Limite]) VALUES (14, 44, N'Số 222 Đường Biển 4', N'Khu Nghỉ Dưỡng Bên Biển 4', N'Nhìn ra biển cả và cảnh đẹp mê hồn tại khu nghỉ dưỡng số 4 bên biển.', 1, N'campsite14.jpg', 33)
INSERT [dbo].[CAMPSITE] ([Campsite_id], [Price_id], [Address], [Name], [Description], [Status], [Image], [Limite]) VALUES (15, 45, N'Số 333 Đường Biển 5', N'Khu Nghỉ Dưỡng Bên Biển 5', N'Khám phá vẻ đẹp tự nhiên và thư giãn tại khu nghỉ dưỡng số 5 bên biển.', 0, N'campsite15.jpg', 39)
INSERT [dbo].[CAMPSITE] ([Campsite_id], [Price_id], [Address], [Name], [Description], [Status], [Image], [Limite]) VALUES (16, 3, N'Đan Phượng', N'Test tạo kệ trêtrtret32544', N'fdg', 1, N'http://sieuthithucpham.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/1/8/18_cai_na_2_.jpg', 35)
SET IDENTITY_INSERT [dbo].[CAMPSITE] OFF
GO
SET IDENTITY_INSERT [dbo].[FEEDBACK] ON 

INSERT [dbo].[FEEDBACK] ([Feedback_id], [StarNumber], [TimeStamp], [Account_id], [Content]) VALUES (6, 5, CAST(N'2024-07-14T00:00:00.000' AS DateTime), 2, N'vxvxbcxvvxcvxcv')
SET IDENTITY_INSERT [dbo].[FEEDBACK] OFF
GO
SET IDENTITY_INSERT [dbo].[GEAR] ON 

INSERT [dbo].[GEAR] ([Gear_id], [Price_id], [Name], [Description], [Image]) VALUES (1, 1, N'Túi ngủ', N'Túi ngủ mùa đông giữ ấm tốt, chất liệu mềm mại, thích hợp cho những chuyến đi trong mùa lạnh.', N'tui_ngu.jpg')
INSERT [dbo].[GEAR] ([Gear_id], [Price_id], [Name], [Description], [Image]) VALUES (2, 2, N'Bếp ga du lịch', N'Bếp ga nhỏ gọn, tiện lợi khi mang theo, tiết kiệm nhiên liệu và dễ sử dụng.', N'bep_ga.jpg')
INSERT [dbo].[GEAR] ([Gear_id], [Price_id], [Name], [Description], [Image]) VALUES (3, 3, N'Bình nước', N'Bình nước giữ nhiệt 1 lít, giữ lạnh và giữ nóng tốt, thiết kế chắc chắn, dễ mang theo.', N'binh_nuoc.jpg')
INSERT [dbo].[GEAR] ([Gear_id], [Price_id], [Name], [Description], [Image]) VALUES (4, 4, N'Ba lô du lịch', N'Ba lô du lịch chống nước, dung tích 50L, nhiều ngăn tiện lợi, dây đeo êm ái, phù hợp cho những chuyến đi dài.', N'ba_lo.jpg')
INSERT [dbo].[GEAR] ([Gear_id], [Price_id], [Name], [Description], [Image]) VALUES (5, 5, N'Đèn pin', N'Đèn pin LED siêu sáng, sạc được, thời gian chiếu sáng lâu, thích hợp cho các hoạt động ngoài trời.', N'den_pin.jpg')
INSERT [dbo].[GEAR] ([Gear_id], [Price_id], [Name], [Description], [Image]) VALUES (6, 6, N'Dụng cụ nấu ăn', N'Bộ dụng cụ nấu ăn gọn nhẹ cho cắm trại, bao gồm nồi, chảo, và dụng cụ ăn uống, chất liệu an toàn.', N'dung_cu_nau_an.jpg')
INSERT [dbo].[GEAR] ([Gear_id], [Price_id], [Name], [Description], [Image]) VALUES (7, 7, N'Tấm trải picnic', N'Tấm trải picnic chống thấm nước, dễ dàng lau sạch, kích thước lớn, thích hợp cho gia đình.', N'tam_trai.jpg')
INSERT [dbo].[GEAR] ([Gear_id], [Price_id], [Name], [Description], [Image]) VALUES (8, 8, N'Dây thừng', N'Dây thừng cắm trại chịu lực cao, độ bền tốt, đa dạng, phù hợp cho nhiều mục đích khác nhau trong cắm trại.', N'day_thung.jpg')
INSERT [dbo].[GEAR] ([Gear_id], [Price_id], [Name], [Description], [Image]) VALUES (9, 9, N'Gậy đi bộ', N'Gậy đi bộ điều chỉnh được độ dài, chất liệu nhẹ, tay cầm êm, hỗ trợ tốt trong việc di chuyển trên địa hình khó khăn.', N'gay_di_bo.jpg')
INSERT [dbo].[GEAR] ([Gear_id], [Price_id], [Name], [Description], [Image]) VALUES (10, 10, N'Đèn pin siêu sáng', N'Đèn pin LED siêu sáng, thích hợp cho các hoạt động ngoài trời vào buổi tối.', N'den_pin.jpg')
INSERT [dbo].[GEAR] ([Gear_id], [Price_id], [Name], [Description], [Image]) VALUES (11, 11, N'Lều cắm trại', N'Lều cắm trại chống thấm nước, dễ lắp đặt, đủ chỗ cho 4 người, phù hợp cho gia đình.', N'leu.jpg')
SET IDENTITY_INSERT [dbo].[GEAR] OFF
GO
SET IDENTITY_INSERT [dbo].[ORDER_DETAIL] ON 

INSERT [dbo].[ORDER_DETAIL] ([OrderDetail_id], [Orders_id], [Quantity], [Gear_id], [SubAmount]) VALUES (4, 8, 1, 3, 120000)
INSERT [dbo].[ORDER_DETAIL] ([OrderDetail_id], [Orders_id], [Quantity], [Gear_id], [SubAmount]) VALUES (5, 8, 1, 5, 140000)
INSERT [dbo].[ORDER_DETAIL] ([OrderDetail_id], [Orders_id], [Quantity], [Gear_id], [SubAmount]) VALUES (7, 17, 1, 4, 130000)
INSERT [dbo].[ORDER_DETAIL] ([OrderDetail_id], [Orders_id], [Quantity], [Gear_id], [SubAmount]) VALUES (8, 17, 1, 2, 110000)
INSERT [dbo].[ORDER_DETAIL] ([OrderDetail_id], [Orders_id], [Quantity], [Gear_id], [SubAmount]) VALUES (9, 19, 2, 7, 160000)
INSERT [dbo].[ORDER_DETAIL] ([OrderDetail_id], [Orders_id], [Quantity], [Gear_id], [SubAmount]) VALUES (10, 20, 2, 2, 110000)
INSERT [dbo].[ORDER_DETAIL] ([OrderDetail_id], [Orders_id], [Quantity], [Gear_id], [SubAmount]) VALUES (11, 21, 2, 4, 130000)
INSERT [dbo].[ORDER_DETAIL] ([OrderDetail_id], [Orders_id], [Quantity], [Gear_id], [SubAmount]) VALUES (12, 22, 1, 3, 120000)
INSERT [dbo].[ORDER_DETAIL] ([OrderDetail_id], [Orders_id], [Quantity], [Gear_id], [SubAmount]) VALUES (13, 24, 1, 3, 120000)
SET IDENTITY_INSERT [dbo].[ORDER_DETAIL] OFF
GO
SET IDENTITY_INSERT [dbo].[ORDERS] ON 

INSERT [dbo].[ORDERS] ([Orders_id], [TimeStamp], [Booker], [Campsite_id], [Quantity], [StartDate], [EndDate], [ApproveStatus], [PaymentStatus], [TotalAmount], [BookingPrice]) VALUES (8, CAST(N'2024-07-14T13:14:06.153' AS DateTime), 2, 2, 2, CAST(N'2024-07-15T00:00:00.000' AS DateTime), CAST(N'2024-07-17T00:00:00.000' AS DateTime), 0, 1, 700000, 440000)
INSERT [dbo].[ORDERS] ([Orders_id], [TimeStamp], [Booker], [Campsite_id], [Quantity], [StartDate], [EndDate], [ApproveStatus], [PaymentStatus], [TotalAmount], [BookingPrice]) VALUES (17, CAST(N'2024-07-14T16:39:38.383' AS DateTime), 2, 1, 3, CAST(N'2024-07-22T00:00:00.000' AS DateTime), CAST(N'2024-07-24T00:00:00.000' AS DateTime), 1, 1, 840000, 600000)
INSERT [dbo].[ORDERS] ([Orders_id], [TimeStamp], [Booker], [Campsite_id], [Quantity], [StartDate], [EndDate], [ApproveStatus], [PaymentStatus], [TotalAmount], [BookingPrice]) VALUES (18, CAST(N'2024-07-14T17:15:24.040' AS DateTime), 2, 1, 1, CAST(N'2024-07-25T00:00:00.000' AS DateTime), CAST(N'2024-07-27T00:00:00.000' AS DateTime), 0, 0, 200000, 200000)
INSERT [dbo].[ORDERS] ([Orders_id], [TimeStamp], [Booker], [Campsite_id], [Quantity], [StartDate], [EndDate], [ApproveStatus], [PaymentStatus], [TotalAmount], [BookingPrice]) VALUES (19, CAST(N'2024-07-20T15:49:19.073' AS DateTime), 2, 2, 2, CAST(N'2024-07-20T00:00:00.000' AS DateTime), CAST(N'2024-07-22T00:00:00.000' AS DateTime), 0, 1, 760000, 440000)
INSERT [dbo].[ORDERS] ([Orders_id], [TimeStamp], [Booker], [Campsite_id], [Quantity], [StartDate], [EndDate], [ApproveStatus], [PaymentStatus], [TotalAmount], [BookingPrice]) VALUES (20, CAST(N'2024-07-20T15:56:33.603' AS DateTime), 2, 2, 2, CAST(N'2024-07-20T00:00:00.000' AS DateTime), CAST(N'2024-07-21T00:00:00.000' AS DateTime), 0, 1, 440000, 220000)
INSERT [dbo].[ORDERS] ([Orders_id], [TimeStamp], [Booker], [Campsite_id], [Quantity], [StartDate], [EndDate], [ApproveStatus], [PaymentStatus], [TotalAmount], [BookingPrice]) VALUES (21, CAST(N'2024-07-20T15:58:36.893' AS DateTime), 2, 2, 2, CAST(N'2024-07-20T00:00:00.000' AS DateTime), CAST(N'2024-07-21T00:00:00.000' AS DateTime), 0, 1, 480000, 220000)
INSERT [dbo].[ORDERS] ([Orders_id], [TimeStamp], [Booker], [Campsite_id], [Quantity], [StartDate], [EndDate], [ApproveStatus], [PaymentStatus], [TotalAmount], [BookingPrice]) VALUES (22, CAST(N'2024-07-20T16:02:01.313' AS DateTime), 2, 4, 2, CAST(N'2024-07-20T00:00:00.000' AS DateTime), CAST(N'2024-07-21T00:00:00.000' AS DateTime), 0, 1, 380000, 260000)
INSERT [dbo].[ORDERS] ([Orders_id], [TimeStamp], [Booker], [Campsite_id], [Quantity], [StartDate], [EndDate], [ApproveStatus], [PaymentStatus], [TotalAmount], [BookingPrice]) VALUES (23, CAST(N'2024-07-20T16:02:38.497' AS DateTime), 2, 3, 1, CAST(N'2024-07-20T00:00:00.000' AS DateTime), CAST(N'2024-07-23T00:00:00.000' AS DateTime), 0, 0, 360000, 360000)
INSERT [dbo].[ORDERS] ([Orders_id], [TimeStamp], [Booker], [Campsite_id], [Quantity], [StartDate], [EndDate], [ApproveStatus], [PaymentStatus], [TotalAmount], [BookingPrice]) VALUES (24, CAST(N'2024-07-20T18:28:11.800' AS DateTime), 2, 2, 2, CAST(N'2024-07-26T00:00:00.000' AS DateTime), CAST(N'2024-07-28T00:00:00.000' AS DateTime), 0, 1, 476000, 440000)
INSERT [dbo].[ORDERS] ([Orders_id], [TimeStamp], [Booker], [Campsite_id], [Quantity], [StartDate], [EndDate], [ApproveStatus], [PaymentStatus], [TotalAmount], [BookingPrice]) VALUES (25, CAST(N'2024-06-20T18:28:11.800' AS DateTime), 2, 1, 3, CAST(N'2024-06-22T00:00:00.000' AS DateTime), CAST(N'2024-06-24T00:00:00.000' AS DateTime), 1, 1, 600000, 600000)
INSERT [dbo].[ORDERS] ([Orders_id], [TimeStamp], [Booker], [Campsite_id], [Quantity], [StartDate], [EndDate], [ApproveStatus], [PaymentStatus], [TotalAmount], [BookingPrice]) VALUES (26, CAST(N'2024-06-21T18:28:11.800' AS DateTime), 2, 2, 2, CAST(N'2024-06-22T00:00:00.000' AS DateTime), CAST(N'2024-06-24T00:00:00.000' AS DateTime), 1, 1, 440000, 440000)
INSERT [dbo].[ORDERS] ([Orders_id], [TimeStamp], [Booker], [Campsite_id], [Quantity], [StartDate], [EndDate], [ApproveStatus], [PaymentStatus], [TotalAmount], [BookingPrice]) VALUES (27, CAST(N'2024-03-20T15:56:33.603' AS DateTime), 2, 2, 2, CAST(N'2024-03-22T00:00:00.000' AS DateTime), CAST(N'2024-03-24T00:00:00.000' AS DateTime), 1, 1, 440000, 440000)
SET IDENTITY_INSERT [dbo].[ORDERS] OFF
GO
SET IDENTITY_INSERT [dbo].[PRICE] ON 

INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (1, CAST(N'2023-01-01T00:00:00.000' AS DateTime), CAST(N'2023-01-31T00:00:00.000' AS DateTime), CAST(100000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (2, CAST(N'2023-02-01T00:00:00.000' AS DateTime), CAST(N'2023-02-28T00:00:00.000' AS DateTime), CAST(110000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (3, CAST(N'2023-03-01T00:00:00.000' AS DateTime), CAST(N'2023-03-31T00:00:00.000' AS DateTime), CAST(120000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (4, CAST(N'2023-04-01T00:00:00.000' AS DateTime), CAST(N'2023-04-30T00:00:00.000' AS DateTime), CAST(130000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (5, CAST(N'2023-05-01T00:00:00.000' AS DateTime), CAST(N'2023-05-31T00:00:00.000' AS DateTime), CAST(140000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (6, CAST(N'2023-06-01T00:00:00.000' AS DateTime), CAST(N'2023-06-30T00:00:00.000' AS DateTime), CAST(150000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (7, CAST(N'2023-07-01T00:00:00.000' AS DateTime), CAST(N'2023-07-31T00:00:00.000' AS DateTime), CAST(160000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (8, CAST(N'2023-08-01T00:00:00.000' AS DateTime), CAST(N'2023-08-31T00:00:00.000' AS DateTime), CAST(170000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (9, CAST(N'2023-09-01T00:00:00.000' AS DateTime), CAST(N'2023-09-30T00:00:00.000' AS DateTime), CAST(180000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (10, CAST(N'2023-10-01T00:00:00.000' AS DateTime), CAST(N'2023-10-31T00:00:00.000' AS DateTime), CAST(190000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (11, CAST(N'2023-11-01T00:00:00.000' AS DateTime), CAST(N'2023-11-30T00:00:00.000' AS DateTime), CAST(200000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (12, CAST(N'2023-12-01T00:00:00.000' AS DateTime), CAST(N'2023-12-31T00:00:00.000' AS DateTime), CAST(210000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (13, CAST(N'2024-01-01T00:00:00.000' AS DateTime), CAST(N'2024-01-31T00:00:00.000' AS DateTime), CAST(220000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (14, CAST(N'2024-02-01T00:00:00.000' AS DateTime), CAST(N'2024-02-29T00:00:00.000' AS DateTime), CAST(230000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (15, CAST(N'2024-03-01T00:00:00.000' AS DateTime), CAST(N'2024-03-31T00:00:00.000' AS DateTime), CAST(240000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (16, CAST(N'2024-04-01T00:00:00.000' AS DateTime), CAST(N'2024-04-30T00:00:00.000' AS DateTime), CAST(250000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (17, CAST(N'2024-05-01T00:00:00.000' AS DateTime), CAST(N'2024-05-31T00:00:00.000' AS DateTime), CAST(260000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (18, CAST(N'2024-06-01T00:00:00.000' AS DateTime), CAST(N'2024-06-30T00:00:00.000' AS DateTime), CAST(270000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (19, CAST(N'2024-07-01T00:00:00.000' AS DateTime), CAST(N'2024-07-31T00:00:00.000' AS DateTime), CAST(280000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (20, CAST(N'2024-08-01T00:00:00.000' AS DateTime), CAST(N'2024-08-31T00:00:00.000' AS DateTime), CAST(290000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (21, CAST(N'2024-09-01T00:00:00.000' AS DateTime), CAST(N'2024-09-30T00:00:00.000' AS DateTime), CAST(300000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (22, CAST(N'2024-10-01T00:00:00.000' AS DateTime), CAST(N'2024-10-31T00:00:00.000' AS DateTime), CAST(310000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (23, CAST(N'2024-11-01T00:00:00.000' AS DateTime), CAST(N'2024-11-30T00:00:00.000' AS DateTime), CAST(320000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (24, CAST(N'2024-12-01T00:00:00.000' AS DateTime), CAST(N'2024-12-31T00:00:00.000' AS DateTime), CAST(330000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (25, CAST(N'2025-01-01T00:00:00.000' AS DateTime), CAST(N'2025-01-31T00:00:00.000' AS DateTime), CAST(340000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (26, CAST(N'2025-02-01T00:00:00.000' AS DateTime), CAST(N'2025-02-28T00:00:00.000' AS DateTime), CAST(350000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (27, CAST(N'2025-03-01T00:00:00.000' AS DateTime), CAST(N'2025-03-31T00:00:00.000' AS DateTime), CAST(360000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (28, CAST(N'2025-04-01T00:00:00.000' AS DateTime), CAST(N'2025-04-30T00:00:00.000' AS DateTime), CAST(370000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (29, CAST(N'2025-05-01T00:00:00.000' AS DateTime), CAST(N'2025-05-31T00:00:00.000' AS DateTime), CAST(380000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (30, CAST(N'2025-06-01T00:00:00.000' AS DateTime), CAST(N'2025-06-30T00:00:00.000' AS DateTime), CAST(390000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (31, CAST(N'2023-01-01T00:00:00.000' AS DateTime), CAST(N'2023-01-31T00:00:00.000' AS DateTime), CAST(100000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (32, CAST(N'2023-02-01T00:00:00.000' AS DateTime), CAST(N'2023-02-28T00:00:00.000' AS DateTime), CAST(110000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (33, CAST(N'2023-03-01T00:00:00.000' AS DateTime), CAST(N'2023-03-31T00:00:00.000' AS DateTime), CAST(120000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (34, CAST(N'2023-04-01T00:00:00.000' AS DateTime), CAST(N'2023-04-30T00:00:00.000' AS DateTime), CAST(130000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (35, CAST(N'2023-05-01T00:00:00.000' AS DateTime), CAST(N'2023-05-31T00:00:00.000' AS DateTime), CAST(140000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (36, CAST(N'2023-06-01T00:00:00.000' AS DateTime), CAST(N'2023-06-30T00:00:00.000' AS DateTime), CAST(150000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (37, CAST(N'2023-07-01T00:00:00.000' AS DateTime), CAST(N'2023-07-31T00:00:00.000' AS DateTime), CAST(160000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (38, CAST(N'2023-08-01T00:00:00.000' AS DateTime), CAST(N'2023-08-31T00:00:00.000' AS DateTime), CAST(170000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (39, CAST(N'2023-09-01T00:00:00.000' AS DateTime), CAST(N'2023-09-30T00:00:00.000' AS DateTime), CAST(180000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (40, CAST(N'2023-10-01T00:00:00.000' AS DateTime), CAST(N'2023-10-31T00:00:00.000' AS DateTime), CAST(190000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (41, CAST(N'2023-11-01T00:00:00.000' AS DateTime), CAST(N'2023-11-30T00:00:00.000' AS DateTime), CAST(200000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (42, CAST(N'2023-12-01T00:00:00.000' AS DateTime), CAST(N'2023-12-31T00:00:00.000' AS DateTime), CAST(210000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (43, CAST(N'2024-01-01T00:00:00.000' AS DateTime), CAST(N'2024-01-31T00:00:00.000' AS DateTime), CAST(220000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (44, CAST(N'2024-02-01T00:00:00.000' AS DateTime), CAST(N'2024-02-29T00:00:00.000' AS DateTime), CAST(230000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (45, CAST(N'2024-03-01T00:00:00.000' AS DateTime), CAST(N'2024-03-31T00:00:00.000' AS DateTime), CAST(240000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (46, CAST(N'2024-04-01T00:00:00.000' AS DateTime), CAST(N'2024-04-30T00:00:00.000' AS DateTime), CAST(250000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (47, CAST(N'2024-05-01T00:00:00.000' AS DateTime), CAST(N'2024-05-31T00:00:00.000' AS DateTime), CAST(260000.00 AS Decimal(10, 2)))
INSERT [dbo].[PRICE] ([Price_id], [StartDate], [EndDate], [Price]) VALUES (48, CAST(N'2024-06-01T00:00:00.000' AS DateTime), CAST(N'2024-06-30T00:00:00.000' AS DateTime), CAST(270000.00 AS Decimal(10, 2)))
SET IDENTITY_INSERT [dbo].[PRICE] OFF
GO
SET IDENTITY_INSERT [dbo].[VOUCHER] ON 

INSERT [dbo].[VOUCHER] ([id], [code], [vPercent], [startDate], [endDate], [isUsed]) VALUES (2, N'W1K15UDK', 15, CAST(N'2024-07-17T00:00:00.000' AS DateTime), CAST(N'2024-07-25T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[VOUCHER] ([id], [code], [vPercent], [startDate], [endDate], [isUsed]) VALUES (3, N'TEW6ZCG6', 12, CAST(N'2024-07-20T00:00:00.000' AS DateTime), CAST(N'2024-07-24T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[VOUCHER] ([id], [code], [vPercent], [startDate], [endDate], [isUsed]) VALUES (7, N'OHV3GEPJ', 30, CAST(N'2024-07-18T00:00:00.000' AS DateTime), CAST(N'2024-07-19T00:00:00.000' AS DateTime), 0)
INSERT [dbo].[VOUCHER] ([id], [code], [vPercent], [startDate], [endDate], [isUsed]) VALUES (8, N'7L9ITACH', 40, CAST(N'2024-07-24T00:00:00.000' AS DateTime), CAST(N'2024-07-25T00:00:00.000' AS DateTime), 0)
SET IDENTITY_INSERT [dbo].[VOUCHER] OFF
GO
ALTER TABLE [dbo].[CAMPSITE]  WITH CHECK ADD FOREIGN KEY([Price_id])
REFERENCES [dbo].[PRICE] ([Price_id])
GO
ALTER TABLE [dbo].[COMMENT]  WITH CHECK ADD  CONSTRAINT [FK__COMMENT__Account__440B1D61] FOREIGN KEY([Account_id])
REFERENCES [dbo].[ACCOUNT] ([Account_id])
GO
ALTER TABLE [dbo].[COMMENT] CHECK CONSTRAINT [FK__COMMENT__Account__440B1D61]
GO
ALTER TABLE [dbo].[FEEDBACK]  WITH CHECK ADD  CONSTRAINT [FK__FEEDBACK__Review__35BCFE0A] FOREIGN KEY([Account_id])
REFERENCES [dbo].[ACCOUNT] ([Account_id])
GO
ALTER TABLE [dbo].[FEEDBACK] CHECK CONSTRAINT [FK__FEEDBACK__Review__35BCFE0A]
GO
ALTER TABLE [dbo].[GEAR]  WITH CHECK ADD FOREIGN KEY([Price_id])
REFERENCES [dbo].[PRICE] ([Price_id])
GO
ALTER TABLE [dbo].[NOTIFICATION]  WITH CHECK ADD  CONSTRAINT [FK__NOTIFICAT__Recei__31EC6D26] FOREIGN KEY([Receiver])
REFERENCES [dbo].[ACCOUNT] ([Account_id])
GO
ALTER TABLE [dbo].[NOTIFICATION] CHECK CONSTRAINT [FK__NOTIFICAT__Recei__31EC6D26]
GO
ALTER TABLE [dbo].[NOTIFICATION]  WITH CHECK ADD  CONSTRAINT [FK__NOTIFICAT__Sende__30F848ED] FOREIGN KEY([Sender])
REFERENCES [dbo].[ACCOUNT] ([Account_id])
GO
ALTER TABLE [dbo].[NOTIFICATION] CHECK CONSTRAINT [FK__NOTIFICAT__Sende__30F848ED]
GO
ALTER TABLE [dbo].[ORDER_DETAIL]  WITH CHECK ADD  CONSTRAINT [FK__ORDER_DET__Order__3B75D760] FOREIGN KEY([Orders_id])
REFERENCES [dbo].[ORDERS] ([Orders_id])
GO
ALTER TABLE [dbo].[ORDER_DETAIL] CHECK CONSTRAINT [FK__ORDER_DET__Order__3B75D760]
GO
ALTER TABLE [dbo].[ORDER_DETAIL]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Gear] FOREIGN KEY([Gear_id])
REFERENCES [dbo].[GEAR] ([Gear_id])
GO
ALTER TABLE [dbo].[ORDER_DETAIL] CHECK CONSTRAINT [FK_OrderDetail_Gear]
GO
ALTER TABLE [dbo].[ORDERS]  WITH CHECK ADD  CONSTRAINT [FK__ORDERS__Booker__38996AB5] FOREIGN KEY([Booker])
REFERENCES [dbo].[ACCOUNT] ([Account_id])
GO
ALTER TABLE [dbo].[ORDERS] CHECK CONSTRAINT [FK__ORDERS__Booker__38996AB5]
GO
ALTER TABLE [dbo].[ORDERS]  WITH CHECK ADD  CONSTRAINT [FK_ORDERS_CAMPSITE] FOREIGN KEY([Campsite_id])
REFERENCES [dbo].[CAMPSITE] ([Campsite_id])
GO
ALTER TABLE [dbo].[ORDERS] CHECK CONSTRAINT [FK_ORDERS_CAMPSITE]
GO
