USE [master]
GO
/****** Object:  Database [ProjectWebHotelBooking]    Script Date: 4/17/2020 2:24:30 PM ******/
CREATE DATABASE [ProjectWebHotelBooking]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ProjectWebHotelBooking', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\ProjectWebHotelBooking.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'ProjectWebHotelBooking_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\ProjectWebHotelBooking_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [ProjectWebHotelBooking] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ProjectWebHotelBooking].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ProjectWebHotelBooking] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET ARITHABORT OFF 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET  DISABLE_BROKER 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET  MULTI_USER 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ProjectWebHotelBooking] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [ProjectWebHotelBooking] SET DELAYED_DURABILITY = DISABLED 
GO
USE [ProjectWebHotelBooking]
GO
/****** Object:  Table [dbo].[tblBookingDetails]    Script Date: 4/17/2020 2:24:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblBookingDetails](
	[bookingID] [bigint] NOT NULL,
	[roomID] [bigint] NOT NULL,
	[amount] [int] NULL,
	[unitPrice] [money] NULL,
	[checkInDate] [date] NULL,
	[checkOutDate] [date] NULL,
 CONSTRAINT [PK_tblBookingDetails] PRIMARY KEY CLUSTERED 
(
	[bookingID] ASC,
	[roomID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblBookings]    Script Date: 4/17/2020 2:24:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblBookings](
	[bookingID] [bigint] IDENTITY(1,1) NOT NULL,
	[userID] [varchar](50) NULL,
	[totalPrice] [money] NULL,
	[bookingDate] [date] NULL,
 CONSTRAINT [PK_tblBookings] PRIMARY KEY CLUSTERED 
(
	[bookingID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblHotels]    Script Date: 4/17/2020 2:24:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblHotels](
	[hotelID] [bigint] IDENTITY(1,1) NOT NULL,
	[hotelName] [varchar](50) NULL,
	[address] [varchar](1000) NULL,
 CONSTRAINT [PK_tblHotels] PRIMARY KEY CLUSTERED 
(
	[hotelID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblRoles]    Script Date: 4/17/2020 2:24:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblRoles](
	[roleID] [varchar](50) NOT NULL,
	[roleName] [varchar](50) NULL,
 CONSTRAINT [PK_tblRoles] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblRooms]    Script Date: 4/17/2020 2:24:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblRooms](
	[roomID] [bigint] IDENTITY(1,1) NOT NULL,
	[hotelID] [bigint] NULL,
	[typeID] [varchar](50) NULL,
	[quantity] [int] NULL,
	[unitPrice] [money] NULL,
	[statusNow] [bit] NULL,
 CONSTRAINT [PK_tblRooms] PRIMARY KEY CLUSTERED 
(
	[roomID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblTypeRooms]    Script Date: 4/17/2020 2:24:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblTypeRooms](
	[typeID] [varchar](50) NOT NULL,
	[typeName] [varchar](50) NULL,
 CONSTRAINT [PK_tblTypeRooms] PRIMARY KEY CLUSTERED 
(
	[typeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblUsers]    Script Date: 4/17/2020 2:24:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblUsers](
	[userID] [varchar](50) NOT NULL,
	[userName] [varchar](50) NULL,
	[password] [varchar](50) NULL,
	[roleID] [varchar](50) NULL,
	[statusNow] [bit] NULL,
 CONSTRAINT [PK_tblUsers] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[tblBookingDetails] ([bookingID], [roomID], [amount], [unitPrice], [checkInDate], [checkOutDate]) VALUES (1, 1, 9, 50.0000, CAST(N'2020-04-14' AS Date), CAST(N'2020-04-15' AS Date))
INSERT [dbo].[tblBookingDetails] ([bookingID], [roomID], [amount], [unitPrice], [checkInDate], [checkOutDate]) VALUES (2, 1, 3, 50.0000, CAST(N'2020-04-16' AS Date), CAST(N'2020-04-17' AS Date))
INSERT [dbo].[tblBookingDetails] ([bookingID], [roomID], [amount], [unitPrice], [checkInDate], [checkOutDate]) VALUES (3, 3, 1, 55.0000, CAST(N'2020-04-14' AS Date), CAST(N'2020-04-20' AS Date))
SET IDENTITY_INSERT [dbo].[tblBookings] ON 

INSERT [dbo].[tblBookings] ([bookingID], [userID], [totalPrice], [bookingDate]) VALUES (1, N'users', 450.0000, CAST(N'2020-04-14' AS Date))
INSERT [dbo].[tblBookings] ([bookingID], [userID], [totalPrice], [bookingDate]) VALUES (2, N'users', 150.0000, CAST(N'2020-04-14' AS Date))
INSERT [dbo].[tblBookings] ([bookingID], [userID], [totalPrice], [bookingDate]) VALUES (3, N'users', 330.0000, CAST(N'2020-04-14' AS Date))
SET IDENTITY_INSERT [dbo].[tblBookings] OFF
SET IDENTITY_INSERT [dbo].[tblHotels] ON 

INSERT [dbo].[tblHotels] ([hotelID], [hotelName], [address]) VALUES (1, N'Red', N'Q1, HCM')
INSERT [dbo].[tblHotels] ([hotelID], [hotelName], [address]) VALUES (2, N'Blue', N'Q2, HCM')
INSERT [dbo].[tblHotels] ([hotelID], [hotelName], [address]) VALUES (3, N'Yellow', N'Q1,HCM')
INSERT [dbo].[tblHotels] ([hotelID], [hotelName], [address]) VALUES (4, N'Black', N'Q3,HCM')
SET IDENTITY_INSERT [dbo].[tblHotels] OFF
INSERT [dbo].[tblRoles] ([roleID], [roleName]) VALUES (N'AD', N'Admin')
INSERT [dbo].[tblRoles] ([roleID], [roleName]) VALUES (N'UR', N'User')
SET IDENTITY_INSERT [dbo].[tblRooms] ON 

INSERT [dbo].[tblRooms] ([roomID], [hotelID], [typeID], [quantity], [unitPrice], [statusNow]) VALUES (1, 1, N'1', 10, 50.0000, 1)
INSERT [dbo].[tblRooms] ([roomID], [hotelID], [typeID], [quantity], [unitPrice], [statusNow]) VALUES (2, 1, N'2', 10, 100.0000, 1)
INSERT [dbo].[tblRooms] ([roomID], [hotelID], [typeID], [quantity], [unitPrice], [statusNow]) VALUES (3, 2, N'1', 10, 55.0000, 1)
SET IDENTITY_INSERT [dbo].[tblRooms] OFF
INSERT [dbo].[tblTypeRooms] ([typeID], [typeName]) VALUES (N'1', N'Single')
INSERT [dbo].[tblTypeRooms] ([typeID], [typeName]) VALUES (N'2', N'Double')
INSERT [dbo].[tblTypeRooms] ([typeID], [typeName]) VALUES (N'3', N'Family')
INSERT [dbo].[tblUsers] ([userID], [userName], [password], [roleID], [statusNow]) VALUES (N'admin', N'ADMIN', N'123', N'AD', 1)
INSERT [dbo].[tblUsers] ([userID], [userName], [password], [roleID], [statusNow]) VALUES (N'users', N'NORMAL USER', N'123', N'UR', 1)
ALTER TABLE [dbo].[tblBookingDetails]  WITH CHECK ADD  CONSTRAINT [FK_tblBookingDetails_tblBookings] FOREIGN KEY([bookingID])
REFERENCES [dbo].[tblBookings] ([bookingID])
GO
ALTER TABLE [dbo].[tblBookingDetails] CHECK CONSTRAINT [FK_tblBookingDetails_tblBookings]
GO
ALTER TABLE [dbo].[tblBookingDetails]  WITH CHECK ADD  CONSTRAINT [FK_tblBookingDetails_tblRooms] FOREIGN KEY([roomID])
REFERENCES [dbo].[tblRooms] ([roomID])
GO
ALTER TABLE [dbo].[tblBookingDetails] CHECK CONSTRAINT [FK_tblBookingDetails_tblRooms]
GO
ALTER TABLE [dbo].[tblBookings]  WITH CHECK ADD  CONSTRAINT [FK_tblBookings_tblUsers] FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([userID])
GO
ALTER TABLE [dbo].[tblBookings] CHECK CONSTRAINT [FK_tblBookings_tblUsers]
GO
ALTER TABLE [dbo].[tblRooms]  WITH CHECK ADD  CONSTRAINT [FK_tblRooms_tblHotels] FOREIGN KEY([hotelID])
REFERENCES [dbo].[tblHotels] ([hotelID])
GO
ALTER TABLE [dbo].[tblRooms] CHECK CONSTRAINT [FK_tblRooms_tblHotels]
GO
ALTER TABLE [dbo].[tblRooms]  WITH CHECK ADD  CONSTRAINT [FK_tblRooms_tblTypeRooms] FOREIGN KEY([typeID])
REFERENCES [dbo].[tblTypeRooms] ([typeID])
GO
ALTER TABLE [dbo].[tblRooms] CHECK CONSTRAINT [FK_tblRooms_tblTypeRooms]
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD  CONSTRAINT [FK_tblUsers_tblRoles] FOREIGN KEY([roleID])
REFERENCES [dbo].[tblRoles] ([roleID])
GO
ALTER TABLE [dbo].[tblUsers] CHECK CONSTRAINT [FK_tblUsers_tblRoles]
GO
USE [master]
GO
ALTER DATABASE [ProjectWebHotelBooking] SET  READ_WRITE 
GO
