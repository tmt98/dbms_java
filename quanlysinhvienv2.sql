-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 20, 2018 lúc 05:54 PM
-- Phiên bản máy phục vụ: 10.1.35-MariaDB
-- Phiên bản PHP: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlysinhvienv2`
--

DELIMITER $$
--
-- Thủ tục
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `AddHP` (`maHPx` VARCHAR(5), `mssvx` VARCHAR(8), `HocKix` INT, `NamHocx` INT)  BEGIN
	INSERT INTO ketqua (maHP, mssv, HocKi, NamHoc,diem) VALUES (maHPx,mssvx,HocKix,NamHocx,-1);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `AddSinhVien` (`mssv` CHAR(8), `hoTen` VARCHAR(45), `gioiTinh` CHAR(3), `ngaySinh` DATE, `diaChi` VARCHAR(100), `maKhoa` CHAR(2), `maNganh` CHAR(2), `KhoaHoc` INT)  BEGIN
	INSERT INTO sinhvien(mssv, hoTen, gioiTinh, ngaySinh, diaChi, maKhoa, maNganh, KhoaHoc) VALUES(mssv,hoTen,gioiTinh,ngaySinh,diaChi,maKhoa,maNganh,KhoaHoc);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteHP` (`mssvx` VARCHAR(8), `maHPx` VARCHAR(5))  BEGIN
	DELETE FROM ketqua WHERE mssv=mssvx AND maHP=maHPx;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteSinhVien` (`mssvx` VARCHAR(8))  BEGIN
	DELETE FROM sinhvien WHERE mssv=mssvx;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteUser` (`mssvx` VARCHAR(8))  BEGIN
	DELETE FROM taikhoan WHERE mssv=mssvx;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetHP` (`mssvx` VARCHAR(8), `makhoax` CHAR(2))  BEGIN
	select maHP, tenHP, soTinChi from hocphan a where (a.maHP not in (select a.maHP from hocphan a, ketqua b where a.mahp = b.mahp and b.mssv = mssvx) and makhoa=makhoax);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetHPNoRE` (`mssvx` VARCHAR(8), `makhoax` VARCHAR(2))  BEGIN
	select maHP, tenHP, soTinChi from hocphan a where (a.maHP not in (select a.maHP from hocphan a, ketqua b where a.mahp = b.mahp and b.mssv = mssvx) and makhoa=makhoax);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetKetQua` (`mssvx` VARCHAR(8))  BEGIN
	SELECT mssv,a.maHP, soTinChi, tenHP, HocKi, NamHoc,diem FROM hocphan a,ketqua b WHERE a.maHP=b.maHP && mssv=mssvx;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetNganh` ()  BEGIN
	SELECT khoa.maKhoa, khoa.tenkhoa, nganh.maNganh, tenNganh FROM khoa, nganh WHERE khoa.maKhoa = nganh.maKhoa ORDER BY khoa.maKhoa;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetSoSV` ()  BEGIN
	SELECT COUNT(mssv) as TongSV, tenKhoa FROM sinhvien a , khoa b WHERE a.maKhoa = b.maKhoa GROUP BY a.maKhoa;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `LoginDB` (`mssvx` VARCHAR(8), `matKhaux` VARCHAR(18))  BEGIN
	SELECT taikhoan.rank FROM taikhoan WHERE mssv = mssvx AND matKhau = matKhaux;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SearchSinhVien` (`hoTenx` VARCHAR(32))  BEGIN
	SELECT * FROM SINHVIEN WHERE hoTen LIKE hotenX;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SelectNganh` (`maKhoax` CHAR(2), `maNganhx` CHAR(2))  BEGIN
	SELECT * FROM SINHVIEN WHERE makhoa=maKhoax && manganh=maNganhx;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ShowSinhVien` ()  BEGIN
	SELECT * FROM sinhvien;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateDiem` (`diemx` INT, `mssvx` VARCHAR(8), `maHPx` VARCHAR(5))  BEGIN
	UPDATE ketqua SET diem = diemx WHERE mssv = mssvx AND maHP = maHPx;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateSinhVien` (`mssvx` CHAR(8), `hoTenx` VARCHAR(45), `gioiTinhx` CHAR(3), `ngaySinhx` DATE, `diaChix` VARCHAR(100), `maKhoax` CHAR(2), `maNganhx` CHAR(2), `KhoaHocx` INT)  BEGIN
	UPDATE sinhvien SET mssv=mssvx, hoTen=hoTenx, gioiTinh=gioiTinhx, ngaySinh=ngaySinhx,  diaChi=diaChix, maKhoa=maKhoax, maNganh=maNganhx, KhoaHoc=KhoaHocx WHERE mssv=mssvx;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hocphan`
--

CREATE TABLE `hocphan` (
  `maHP` char(5) NOT NULL,
  `tenHP` varchar(50) NOT NULL,
  `maKhoa` varchar(2) NOT NULL,
  `soTinChi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `hocphan`
--

INSERT INTO `hocphan` (`maHP`, `tenHP`, `maKhoa`, `soTinChi`) VALUES
('CT103', 'Cau Truc Du Lieu', 'DI', 4),
('CT172', 'Toan Roi Rac', 'DI', 4),
('CT173', 'Kien Truc May Tinh', 'DI', 3),
('CT176', 'Lap Trinh Huong Doi Tuong', 'DI', 3),
('FG123', 'Hoa Dai Cuong', 'NN', 4),
('ML009', 'Mac Le Nin 1', '', 2),
('ML010', 'Mac Le Nin 2', '', 2),
('TN001', 'Vi - Tich Phan A1', '', 3),
('TN010', 'Xac Suat Thong Ke', '', 3),
('TN012', 'Dai So Tuyen Tinh', '', 4),
('TN033', 'Tin Hoc Can Ban', '', 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ketqua`
--

CREATE TABLE `ketqua` (
  `maHP` char(5) NOT NULL,
  `mssv` char(8) NOT NULL,
  `HocKi` int(11) NOT NULL,
  `NamHoc` int(11) NOT NULL,
  `diem` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `ketqua`
--

INSERT INTO `ketqua` (`maHP`, `mssv`, `HocKi`, `NamHoc`, `diem`) VALUES
('CT172', 'B1606909', 1, 2017, -1),
('CT172', 'B1606930', 1, 2017, -1),
('CT172', 'B1606931', 1, 2017, 6),
('CT176', 'B1606931', 3, 2017, -1),
('TN001', 'B1606909', 1, 2017, -1),
('TN001', 'B1606930', 1, 2017, 4),
('TN001', 'B1606931', 1, 2017, 6),
('TN012', 'B1606931', 1, 2018, -1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khoa`
--

CREATE TABLE `khoa` (
  `maKhoa` char(2) NOT NULL,
  `tenKhoa` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `khoa`
--

INSERT INTO `khoa` (`maKhoa`, `tenKhoa`) VALUES
('CN', 'Cong Nghe'),
('DI', 'CNTT & TT'),
('KH', 'Khoa Hoc Tu Nhien'),
('KT', 'Kinh Te'),
('ML', 'Chinh Tri'),
('NN', 'Nong Nghiep'),
('TC', 'Giao Duc The Chat'),
('TS', 'Thuy San'),
('XH', 'Xa Hoi Nhan Van');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nganh`
--

CREATE TABLE `nganh` (
  `maNganh` char(2) NOT NULL,
  `maKhoa` char(2) NOT NULL,
  `tenNganh` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `nganh`
--

INSERT INTO `nganh` (`maNganh`, `maKhoa`, `tenNganh`) VALUES
('A8', 'KT', 'Kinh Doanh Quoc Te'),
('AX', 'NN', 'Bao Ve Thuc Vat'),
('AZ', 'NN', 'Khoa Hoc Cay Trong'),
('G7', 'TC', 'Giao Duc The Chat'),
('R8', 'KT', 'Quan Tri Kinh Doanh'),
('V7', 'DI', 'Cong Nghe Thong Tin'),
('X4', 'ML', 'Giao Duc Cong Dan'),
('Y7', 'DI', 'Tin Hoc Ung Dung');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sinhvien`
--

CREATE TABLE `sinhvien` (
  `mssv` char(8) NOT NULL,
  `hoTen` varchar(45) NOT NULL,
  `gioiTinh` char(3) NOT NULL,
  `ngaySinh` date NOT NULL,
  `diaChi` varchar(100) NOT NULL,
  `maKhoa` char(2) NOT NULL,
  `maNganh` char(2) NOT NULL,
  `KhoaHoc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `sinhvien`
--

INSERT INTO `sinhvien` (`mssv`, `hoTen`, `gioiTinh`, `ngaySinh`, `diaChi`, `maKhoa`, `maNganh`, `KhoaHoc`) VALUES
('B1234653', 'Nguyen Hoang Phu', 'Nam', '1998-09-01', 'Can Tho', 'NN', 'AZ', 44),
('B1400101', 'Luong Xuan Truong', 'Nam', '1995-01-01', 'Bac Lieu', 'TC', 'G7', 39),
('B1400999', 'Phan Van Duc', 'Nam', '1995-01-01', 'Ha Noi', 'TC', 'G7', 39),
('B1406995', 'Nguyen Cong Phuong', 'Nam', '1995-01-01', 'Ben Tre', 'TC', 'G7', 39),
('B1410122', 'Nguyen Phong Hong Duy', 'Nam', '1996-01-01', 'Long An', 'TC', 'G7', 39),
('B1508626', 'Nguyen Hoang Anh', 'Nam', '1997-01-01', 'Can Tho', 'NN', 'AX', 41),
('B1508627', 'Nguyen Thi Minh Anh', 'Nu', '1997-01-01', 'Can Tho', 'ML', 'X4', 41),
('B1508628', 'Trieu Thuy Anh', 'Nu', '1997-01-01', 'Tien Giang', 'ML', 'X4', 41),
('B1508629', 'Tran Thoai Ba', 'Nu', '1997-01-01', 'Hau Giang', 'KT', 'R8', 41),
('B1508630', 'Nguyen Thi To Cam', 'Nu', '1997-01-01', 'Vinh Long', 'KT', 'R8', 41),
('B1522131', 'Nguyen Tan An', 'Nam', '1995-12-12', 'Kien Giang', 'KT', 'A8', 40),
('B1599111', 'Pham Nhat Vinh', 'Nam', '1995-01-01', 'Ca Mau', 'KT', 'A8', 40),
('B1599213', 'Doan Nguyen Duc', 'Nam', '1996-01-01', 'Gia Lai', 'KT', 'A8', 40),
('B1606899', 'Huynh Thanh An', 'Nam', '1997-01-01', 'Bac Lieu', 'DI', 'Y7', 42),
('B1606909', 'Tao Van Luan', 'Nam', '1998-04-18', 'Soc Trang', 'DI', 'Y7', 42),
('B1606930', 'Le Quang Sang', 'Nam', '1997-01-01', 'Tra Vinh', 'DI', 'V7', 42),
('B1606931', 'Tran Minh Tai', 'Nam', '1998-07-26', 'An Giang', 'DI', 'V7', 42),
('B1606955', 'Vu Hoai Nam', 'Nam', '1990-01-01', 'Can Tho', 'NN', 'AZ', 42),
('B1707931', 'Tan Thanh Hoang', 'Nam', '1990-01-01', 'Soc Trang', 'DI', 'V7', 41);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `mssv` varchar(8) NOT NULL,
  `matkhau` varchar(16) DEFAULT NULL,
  `rank` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`mssv`, `matkhau`, `rank`) VALUES
('admin', 'admin', 'GV'),
('B1234653', '1', 'SV'),
('B1400101', '1', 'SV'),
('B1400999', '1', 'SV'),
('B1406995', '1', 'SV'),
('B1410122', '1', 'SV'),
('B1508626', '1', 'SV'),
('B1508627', '1', 'SV'),
('B1508628', '1', 'SV'),
('B1508629', '1', 'SV'),
('B1508630', '1', 'SV'),
('B1522131', '1', 'SV'),
('B1599111', '1', 'SV'),
('B1599213', '1', 'SV'),
('B1606899', '1', 'SV'),
('B1606909', '1', 'SV'),
('B1606930', '1', 'SV'),
('B1606931', '1', 'SV'),
('B1606955', '1', 'SV'),
('B1707931', '1', 'SV'),
('B2212345', '1', 'SV'),
('B2312345', '1', 'SV');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `hocphan`
--
ALTER TABLE `hocphan`
  ADD PRIMARY KEY (`maHP`),
  ADD KEY `fk_HocPhan_maKhoa` (`maKhoa`);

--
-- Chỉ mục cho bảng `ketqua`
--
ALTER TABLE `ketqua`
  ADD PRIMARY KEY (`maHP`,`mssv`),
  ADD KEY `fk_KetQua_mssv` (`mssv`);

--
-- Chỉ mục cho bảng `khoa`
--
ALTER TABLE `khoa`
  ADD PRIMARY KEY (`maKhoa`);

--
-- Chỉ mục cho bảng `nganh`
--
ALTER TABLE `nganh`
  ADD PRIMARY KEY (`maNganh`,`maKhoa`),
  ADD KEY `fk_Nganh_maKhoa` (`maKhoa`);

--
-- Chỉ mục cho bảng `sinhvien`
--
ALTER TABLE `sinhvien`
  ADD PRIMARY KEY (`mssv`),
  ADD KEY `fk_SinhVien_maKhoa` (`maKhoa`),
  ADD KEY `fk_SinhVien_maNganh` (`maNganh`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`mssv`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
