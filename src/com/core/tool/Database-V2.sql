
--DROP DATABASE DuAn1;
--
--CREATE DATABASE DuAn1;
--
--USE DuAn1;
--
CREATE TABLE HeDieuHanh
(
    id INT IDENTITY(0,1) PRIMARY KEY,
    ten NVARCHAR(30) NOT NULL UNIQUE,
    hoat_dong BIT NOT NULL
);

CREATE TABLE ManHinh
(
    id INT IDENTITY(0,1) PRIMARY KEY,
    ten NVARCHAR(30) NOT NULL UNIQUE,
    hoat_dong BIT NOT NULL
);

CREATE TABLE Hang
(
    id INT IDENTITY(0,1) PRIMARY KEY,
    ten NVARCHAR(30) NOT NULL UNIQUE,
    hoat_dong BIT NOT NULL
);

CREATE TABLE CameraTruoc
(
    id INT IDENTITY(0,1) PRIMARY KEY,
    ten NVARCHAR(30) NOT NULL UNIQUE,
    hoat_dong BIT NOT NULL
);

CREATE TABLE CameraSau
(
    id INT IDENTITY(0,1) PRIMARY KEY,
    ten NVARCHAR(30) NOT NULL UNIQUE,
    hoat_dong BIT NOT NULL
);

CREATE TABLE Pin
(
    id INT IDENTITY(0,1) PRIMARY KEY,
    ten NVARCHAR(30) NOT NULL UNIQUE,
    hoat_dong BIT NOT NULL
);

CREATE TABLE Chip
(
    id INT IDENTITY(0,1) PRIMARY KEY,
    ten NVARCHAR(30) NOT NULL UNIQUE,
    hoat_dong BIT NOT NULL
);

CREATE TABLE SanPham
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    id_he_dieu_hanh INT,
    id_man_hinh INT,
    id_hang INT,
    id_camera_truoc INT,
    id_camera_sau INT,
    id_chip INT,
    id_pin INT,
    ten_san_pham NVARCHAR(250),
    ngay_tao DATE NOT NULL,
    ngay_sua DATE,
    nguoi_tao VARCHAR(25) NOT NULL,
    nguoi_sua VARCHAR(25),
    hoat_dong BIT NOT NULL,
    FOREIGN KEY (id_he_dieu_hanh) REFERENCES HeDieuHanh(id),
    FOREIGN KEY (id_man_hinh) REFERENCES ManHinh(id),
    FOREIGN KEY (id_hang) REFERENCES Hang(id),
    FOREIGN KEY (id_camera_truoc) REFERENCES CameraTruoc(id),
    FOREIGN KEY (id_camera_sau) REFERENCES CameraSau(id),
    FOREIGN KEY (id_chip) REFERENCES Chip(id),
    FOREIGN KEY (id_pin) REFERENCES Pin(id)
);

CREATE TABLE Ram
(
    id INT IDENTITY(0,1) PRIMARY KEY,
    ten NVARCHAR(30) NOT NULL UNIQUE,
    hoat_dong BIT NOT NULL
);

CREATE TABLE BoNho
(
    id INT IDENTITY(0,1) PRIMARY KEY,
    ten NVARCHAR(30) NOT NULL UNIQUE,
    hoat_dong BIT NOT NULL
);

CREATE TABLE MauSac
(
    id INT IDENTITY(0,1) PRIMARY KEY,
    ten NVARCHAR(30) NOT NULL UNIQUE,
    hoat_dong BIT NOT NULL
);

CREATE TABLE ChiTietSanPham
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    id_san_pham INT,
    id_ram INT,
    id_bo_nho INT,
    id_mau_sac INT,
    ten_san_pham_chi_tiet NVARCHAR(250),
    gia_ban FLOAT,
    yeu_thich BIT,
    ngay_tao DATE NOT NULL,
    ngay_sua DATE,
    nguoi_tao VARCHAR(25) NOT NULL,
    nguoi_sua VARCHAR(25),
    hoat_dong BIT NOT NULL,
    FOREIGN KEY (id_san_pham) REFERENCES SanPham(id),
    FOREIGN KEY (id_ram) REFERENCES Ram(id),
    FOREIGN KEY (id_bo_nho) REFERENCES BoNho(id),
    FOREIGN KEY (id_mau_sac) REFERENCES MauSac(id)
);

CREATE TABLE Imei
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    id_chi_tiet_san_pham INT,
    ma_imei VARCHAR(30) NOT NULL UNIQUE,
    hoat_dong BIT NOT NULL,
    FOREIGN KEY (id_chi_tiet_san_pham) REFERENCES ChiTietSanPham(id)
);

CREATE TABLE KhachHang
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    ten_khach_hang NVARCHAR(50),
    so_dien_thoai VARCHAR(20),
    hoat_dong BIT NOT NULL
);

CREATE TABLE NhanVien
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    ma_nhan_vien VARCHAR(15) NOT NULL UNIQUE,
    ten_nhan_vien NVARCHAR(50),
    so_dien_thoai VARCHAR(20),
    dia_chi NVARCHAR(250),
    cccd VARCHAR(30),
    mat_khau VARCHAR(30) NOT NULL,
    chuc_vu BIT NOT NULL,
    ngay_tao DATE NOT NULL,
    ngay_sua DATE,
    nguoi_tao VARCHAR(25) NOT NULL,
    nguoi_sua VARCHAR(25),
    hoat_dong BIT NOT NULL
);

CREATE TABLE PhieuGiamGia
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    ma_voucher VARCHAR(15) NOT NULL UNIQUE,
    ten_voucher NVARCHAR(50),
    so_luong INT NOT NULL,
    gia_tri_toi_thieu_ap_dung FLOAT NOT NULL,
    phan_tram_giam INT,
    so_tien_duoc_giam_toi_da FLOAT NOT NULL,
    ngay_bat_dau DATE NOT NULL,
    ngay_ket_thuc DATE NOT NULL,
    mo_ta NVARCHAR(250),
    ngay_tao DATE NOT NULL,
    ngay_sua DATE,
    nguoi_tao VARCHAR(25) NOT NULL,
    nguoi_sua VARCHAR(25),
    hoat_dong BIT NOT NULL
);

CREATE TABLE HinhThucThanhToan
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    ten NVARCHAR(30) NOT NULL UNIQUE,
    hoat_dong BIT NOT NULL
);

CREATE TABLE HoaDon
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    id_khach_hang INT,
    id_nhan_vien INT,
    id_phieu_giam_gia INT,
    ngay_tao DATE NOT NULL,
    tong_tien FLOAT,
    tong_tien_sau_giam FLOAT,
    ten_khach_hang NVARCHAR(30),
    so_dien_thoai VARCHAR(20),
    hoat_dong BIT NOT NULL,
    nguoi_tao VARCHAR(25) NOT NULL,
    ngay_sua DATE,
    nguoi_sua VARCHAR(25),
    FOREIGN KEY (id_khach_hang) REFERENCES KhachHang(id),
    FOREIGN KEY (id_nhan_vien) REFERENCES NhanVien(id),
    FOREIGN KEY (id_phieu_giam_gia) REFERENCES PhieuGiamGia(id)
);

CREATE TABLE ChiTietHinhThucThanhToan
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    id_hoa_don INT NOT NULL,
    id_hinh_thuc_thanh_toan INT NOT NULL,
    tong_tien FLOAT,
    hoat_dong BIT NOT NULL,
    FOREIGN KEY (id_hoa_don) REFERENCES HoaDon(id),
    FOREIGN KEY (id_hinh_thuc_thanh_toan) REFERENCES HinhThucThanhToan(id)
);

CREATE TABLE HoaDonChiTiet
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    id_chi_tiet_san_pham INT NOT NULL,
    id_hoa_don INT NOT NULL,
    gia_ban FLOAT,
    mo_ta NVARCHAR(250),
    hoat_dong BIT NOT NULL,
    FOREIGN KEY (id_chi_tiet_san_pham) REFERENCES ChiTietSanPham(id),
    FOREIGN KEY (id_hoa_don) REFERENCES HoaDon(id)
);

CREATE TABLE ImeiDaBan
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    ma_imei VARCHAR(30) NOT NULL UNIQUE,
    hoat_dong BIT NOT NULL
);