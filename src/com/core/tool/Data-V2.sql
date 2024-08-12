--USE DuAn1;

-- Bảng HeDieuHanh
INSERT INTO HeDieuHanh (ten, hoat_dong) VALUES (N'Chưa nhập', 1);
INSERT INTO HeDieuHanh (ten, hoat_dong) VALUES ('Android', 1);
INSERT INTO HeDieuHanh (ten, hoat_dong) VALUES ('iOS', 1);
INSERT INTO HeDieuHanh (ten, hoat_dong) VALUES ('Windows Phone', 1);
INSERT INTO HeDieuHanh (ten, hoat_dong) VALUES ('HarmonyOS', 1);
INSERT INTO HeDieuHanh (ten, hoat_dong) VALUES ('BlackBerry OS', 1);

-- Bảng ManHinh
INSERT INTO ManHinh (ten, hoat_dong) VALUES (N'Chưa nhập', 1);
INSERT INTO ManHinh (ten, hoat_dong) VALUES ('LCD', 1);
INSERT INTO ManHinh (ten, hoat_dong) VALUES ('OLED', 1);
INSERT INTO ManHinh (ten, hoat_dong) VALUES ('AMOLED', 1);
INSERT INTO ManHinh (ten, hoat_dong) VALUES ('Super AMOLED', 1);
INSERT INTO ManHinh (ten, hoat_dong) VALUES ('Retina', 1);

-- Bảng Hang
INSERT INTO Hang (ten, hoat_dong) VALUES (N'Chưa nhập', 1);
INSERT INTO Hang (ten, hoat_dong) VALUES ('Samsung', 1);
INSERT INTO Hang (ten, hoat_dong) VALUES ('Apple', 1);
INSERT INTO Hang (ten, hoat_dong) VALUES ('Huawei', 1);
INSERT INTO Hang (ten, hoat_dong) VALUES ('Xiaomi', 1);
INSERT INTO Hang (ten, hoat_dong) VALUES ('Nokia', 1);

-- Bảng CameraTruoc
INSERT INTO CameraTruoc (ten, hoat_dong) VALUES (N'Chưa nhập', 1);
INSERT INTO CameraTruoc (ten, hoat_dong) VALUES ('8MP', 1);
INSERT INTO CameraTruoc (ten, hoat_dong) VALUES ('12MP', 1);
INSERT INTO CameraTruoc (ten, hoat_dong) VALUES ('16MP', 1);
INSERT INTO CameraTruoc (ten, hoat_dong) VALUES ('20MP', 1);
INSERT INTO CameraTruoc (ten, hoat_dong) VALUES ('32MP', 1);

-- Bảng CameraSau
INSERT INTO CameraSau (ten, hoat_dong) VALUES (N'Chưa nhập', 1);
INSERT INTO CameraSau (ten, hoat_dong) VALUES ('12MP', 1);
INSERT INTO CameraSau (ten, hoat_dong) VALUES ('24MP', 1);
INSERT INTO CameraSau (ten, hoat_dong) VALUES ('48MP', 1);
INSERT INTO CameraSau (ten, hoat_dong) VALUES ('64MP', 1);
INSERT INTO CameraSau (ten, hoat_dong) VALUES ('108MP', 1);

-- Bảng Pin
INSERT INTO Pin (ten, hoat_dong) VALUES (N'Chưa nhập', 1);
INSERT INTO Pin (ten, hoat_dong) VALUES ('3000mAh', 1);
INSERT INTO Pin (ten, hoat_dong) VALUES ('4000mAh', 1);
INSERT INTO Pin (ten, hoat_dong) VALUES ('5000mAh', 1);
INSERT INTO Pin (ten, hoat_dong) VALUES ('6000mAh', 1);
INSERT INTO Pin (ten, hoat_dong) VALUES ('7000mAh', 1);

-- Bảng Chip
INSERT INTO Chip (ten, hoat_dong) VALUES (N'Chưa nhập', 1);
INSERT INTO Chip (ten, hoat_dong) VALUES ('Snapdragon 865', 1);
INSERT INTO Chip (ten, hoat_dong) VALUES ('A14 Bionic', 1);
INSERT INTO Chip (ten, hoat_dong) VALUES ('Kirin 9000', 1);
INSERT INTO Chip (ten, hoat_dong) VALUES ('Exynos 2100', 1);
INSERT INTO Chip (ten, hoat_dong) VALUES ('MediaTek Dimensity 1200', 1);

-- Bảng SanPham
INSERT INTO SanPham (id_he_dieu_hanh, id_man_hinh, id_hang, id_camera_truoc, id_camera_sau, id_chip, id_pin, ten_san_pham, ngay_tao, nguoi_tao, hoat_dong)
VALUES (1, 1, 1, 1, 1, 1, 1, 'Samsung Galaxy S21', GETDATE(), 'admin', 1);
INSERT INTO SanPham (id_he_dieu_hanh, id_man_hinh, id_hang, id_camera_truoc, id_camera_sau, id_chip, id_pin, ten_san_pham, ngay_tao, nguoi_tao, hoat_dong)
VALUES (2, 5, 2, 5, 5, 2, 2, 'iPhone 12', GETDATE(), 'admin', 1);
INSERT INTO SanPham (id_he_dieu_hanh, id_man_hinh, id_hang, id_camera_truoc, id_camera_sau, id_chip, id_pin, ten_san_pham, ngay_tao, nguoi_tao, hoat_dong)
VALUES (3, 2, 3, 2, 2, 3, 3, 'Huawei Mate 40', GETDATE(), 'admin', 1);
INSERT INTO SanPham (id_he_dieu_hanh, id_man_hinh, id_hang, id_camera_truoc, id_camera_sau, id_chip, id_pin, ten_san_pham, ngay_tao, nguoi_tao, hoat_dong)
VALUES (4, 4, 4, 4, 4, 4, 4, 'Xiaomi Mi 11', GETDATE(), 'admin', 1);
INSERT INTO SanPham (id_he_dieu_hanh, id_man_hinh, id_hang, id_camera_truoc, id_camera_sau, id_chip, id_pin, ten_san_pham, ngay_tao, nguoi_tao, hoat_dong)
VALUES (5, 3, 5, 3, 3, 5, 5, 'Nokia 9 PureView', GETDATE(), 'admin', 1);

-- Bảng Ram
INSERT INTO Ram (ten, hoat_dong) VALUES (N'Chưa nhập', 1);
INSERT INTO Ram (ten, hoat_dong) VALUES ('4GB', 1);
INSERT INTO Ram (ten, hoat_dong) VALUES ('6GB', 1);
INSERT INTO Ram (ten, hoat_dong) VALUES ('8GB', 1);
INSERT INTO Ram (ten, hoat_dong) VALUES ('12GB', 1);
INSERT INTO Ram (ten, hoat_dong) VALUES ('16GB', 1);

-- Bảng BoNho
INSERT INTO BoNho (ten, hoat_dong) VALUES (N'Chưa nhập', 1);
INSERT INTO BoNho (ten, hoat_dong) VALUES ('64GB', 1);
INSERT INTO BoNho (ten, hoat_dong) VALUES ('128GB', 1);
INSERT INTO BoNho (ten, hoat_dong) VALUES ('256GB', 1);
INSERT INTO BoNho (ten, hoat_dong) VALUES ('512GB', 1);
INSERT INTO BoNho (ten, hoat_dong) VALUES ('1TB', 1);

-- Bảng MauSac
INSERT INTO MauSac (ten, hoat_dong) VALUES (N'Chưa nhập', 1);
INSERT INTO MauSac (ten, hoat_dong) VALUES (N'Đen', 1);
INSERT INTO MauSac (ten, hoat_dong) VALUES (N'Trắng', 1);
INSERT INTO MauSac (ten, hoat_dong) VALUES (N'Xanh', 1);
INSERT INTO MauSac (ten, hoat_dong) VALUES (N'Đỏ', 1);
INSERT INTO MauSac (ten, hoat_dong) VALUES (N'Vàng', 1);

-- Bảng ChiTietSanPham
INSERT INTO ChiTietSanPham (id_san_pham, id_ram, id_bo_nho, id_mau_sac, ten_san_pham_chi_tiet, gia_ban, yeu_thich, ngay_tao, nguoi_tao, hoat_dong)
VALUES (1, 1, 1, 1, 'Samsung Galaxy S21 4GB 64GB Đen', 7000000, 1, GETDATE(), 'admin', 1);
INSERT INTO ChiTietSanPham (id_san_pham, id_ram, id_bo_nho, id_mau_sac, ten_san_pham_chi_tiet, gia_ban, yeu_thich, ngay_tao, nguoi_tao, hoat_dong)
VALUES (2, 2, 2, 2, 'iPhone 12 6GB 128GB Trắng', 8000000, 1, GETDATE(), 'admin', 1);
INSERT INTO ChiTietSanPham (id_san_pham, id_ram, id_bo_nho, id_mau_sac, ten_san_pham_chi_tiet, gia_ban, yeu_thich, ngay_tao, nguoi_tao, hoat_dong)
VALUES (3, 3, 3, 3, 'Huawei Mate 40 8GB 256GB Xanh', 10000000, 1, GETDATE(), 'admin', 1);
INSERT INTO ChiTietSanPham (id_san_pham, id_ram, id_bo_nho, id_mau_sac, ten_san_pham_chi_tiet, gia_ban, yeu_thich, ngay_tao, nguoi_tao, hoat_dong)
VALUES (4, 4, 4, 4, 'Xiaomi Mi 11 12GB 512GB Đỏ', 10000000, 1, GETDATE(), 'admin', 1);
INSERT INTO ChiTietSanPham (id_san_pham, id_ram, id_bo_nho, id_mau_sac, ten_san_pham_chi_tiet, gia_ban, yeu_thich, ngay_tao, nguoi_tao, hoat_dong)
VALUES (5, 5, 5, 5, 'Nokia 9 PureView 16GB 1TB Vàng', 19000000, 1, GETDATE(), 'admin', 1);


-- Bảng KhachHang
INSERT INTO KhachHang (ten_khach_hang, so_dien_thoai, ghi_chu, hoat_dong) VALUES ('Nguyen Van A', '0123456789', 'Test', 1);
INSERT INTO KhachHang (ten_khach_hang, so_dien_thoai,ghi_chu , hoat_dong) VALUES ('Tran Thi B', '0987654321', 'Test 2', 1);
INSERT INTO KhachHang (ten_khach_hang, so_dien_thoai,ghi_chu, hoat_dong) VALUES ('Le Van C', '0112233445', 'test 3', 1);
INSERT INTO KhachHang (ten_khach_hang, so_dien_thoai,ghi_chu, hoat_dong) VALUES ('Pham Thi D', '0223344556', 'test 4', 1);
INSERT INTO KhachHang (ten_khach_hang, so_dien_thoai,ghi_chu, hoat_dong) VALUES ('Hoang Van E', '0334455667', 'test 5', 1);

-- Bảng NhanVien
INSERT INTO NhanVien (ma_nhan_vien, ten_nhan_vien, so_dien_thoai, dia_chi, cccd, mat_khau, chuc_vu, ngay_tao, nguoi_tao, hoat_dong) 
VALUES ('1', 'Nguyen Van F', '0123', '123 Le Loi', '0123', '1', 1, GETDATE(), 'admin', 1);
INSERT INTO NhanVien (ma_nhan_vien, ten_nhan_vien, so_dien_thoai, dia_chi, cccd, mat_khau, chuc_vu, ngay_tao, nguoi_tao, hoat_dong) 
VALUES ('2', 'Nguyen Van F', '01234', '123 Le Loi', '012345', '2', 0, GETDATE(), 'admin', 1);


-- Bảng PhieuGiamGia
INSERT INTO PhieuGiamGia (ma_voucher, ten_voucher, so_luong, gia_tri_toi_thieu_ap_dung, phan_tram_giam, so_tien_duoc_giam_toi_da, ngay_bat_dau, ngay_ket_thuc, mo_ta, ngay_tao, nguoi_tao, hoat_dong)
VALUES ('VOUCHER0', 'Giam 10%', 100, 1000000, 10, 500000, '2024-01-01', '2025-12-31', 'Giam 10% cho don hang tren 100000', GETDATE(), 'admin', 1);


-- Bảng HinhThucThanhToan
INSERT INTO HinhThucThanhToan (ten, hoat_dong) VALUES ('Tien mat', 1);
INSERT INTO HinhThucThanhToan (ten, hoat_dong) VALUES ('Chuyen khoan', 1);


-- NEW 


-- Thêm dữ liệu vào bảng HeDieuHanh
INSERT INTO HeDieuHanh (ten, hoat_dong) VALUES 
('iOS', 1),
('Android', 1),
('Windows Phone', 1),
('Symbian', 1),
('BlackBerry', 1);

-- Thêm dữ liệu vào bảng ManHinh
INSERT INTO ManHinh (ten, hoat_dong) VALUES 
('Retina', 1),
('Super AMOLED', 1),
('OLED', 1),
('LCD', 1),
('IPS', 1);

-- Thêm dữ liệu vào bảng Hang
INSERT INTO Hang (ten, hoat_dong) VALUES 
('Apple', 1),
('Samsung', 1),
('Nokia', 1),
('Sony', 1),
('Xiaomi', 1);

-- Thêm dữ liệu vào bảng CameraTruoc
INSERT INTO CameraTruoc (ten, hoat_dong) VALUES 
('12MP', 1),
('8MP', 1),
('5MP', 1),
('2MP', 1),
('No Front Camera', 1);

-- Thêm dữ liệu vào bảng CameraSau
INSERT INTO CameraSau (ten, hoat_dong) VALUES 
('12MP', 1),
('48MP', 1),
('64MP', 1),
('108MP', 1),
('2MP', 1);

-- Thêm dữ liệu vào bảng Pin
INSERT INTO Pin (ten, hoat_dong) VALUES 
('2000mAh', 1),
('3000mAh', 1),
('4000mAh', 1),
('5000mAh', 1),
('6000mAh', 1);

-- Thêm dữ liệu vào bảng Chip
INSERT INTO Chip (ten, hoat_dong) VALUES 
('A14 Bionic', 1),
('Snapdragon 888', 1),
('Exynos 2100', 1),
('Kirin 9000', 1),
('MediaTek Dimensity', 1);

-- Thêm dữ liệu vào bảng SanPham (chỉ thêm iPhone)
INSERT INTO SanPham (id_he_dieu_hanh, id_man_hinh, id_hang, id_camera_truoc, id_camera_sau, id_chip, id_pin, ten_san_pham, ngay_tao, nguoi_tao, hoat_dong) VALUES 
(0, 1, 0, 1, 1, 0, 3, 'iPhone 12',GETDATE(), 'Admin',  1),
(0, 1, 0, 1, 1, 0, 3, 'iPhone 12 Pro', GETDATE(), 'Admin',  1),
(0, 1, 0, 1, 1, 0, 3, 'iPhone 12 Pro Max',GETDATE(), 'Admin',  1),
(0, 1, 0, 1, 1, 0, 3, 'iPhone 13',GETDATE(), 'Admin',  1),
(0, 1, 0, 1, 1, 0, 3, 'iPhone 13 Pro Max',GETDATE(), 'Admin',  1);

-- Thêm dữ liệu vào bảng Ram
INSERT INTO Ram (ten, hoat_dong) VALUES 
('2GB', 1),
('3GB', 1),
('4GB', 1),
('6GB', 1),
('8GB', 1);

-- Thêm dữ liệu vào bảng BoNho
INSERT INTO BoNho (ten, hoat_dong) VALUES 
('32GB', 1),
('64GB', 1),
('128GB', 1),
('256GB', 1),
('512GB', 1);
select * from BoNho
-- Thêm dữ liệu vào bảng MauSac
INSERT INTO MauSac (ten, hoat_dong) VALUES 
('Black', 1),
('White', 1),
('Gold', 1),
('Blue', 1),
('Red', 1);

-- Thêm dữ liệu vào bảng ChiTietSanPham
INSERT INTO ChiTietSanPham (id_san_pham, id_ram, id_bo_nho, id_mau_sac, ten_san_pham_chi_tiet, gia_ban, yeu_thich, ngay_tao, nguoi_tao, hoat_dong) VALUES 
(1, 1, 2, 0, 'iPhone 12 3GB 128GB Black', 15000000,  1, GETDATE(), 'admin', 1),
(2, 2, 3, 1, 'iPhone 12 Pro 4GB 256GB White', 17000000,  1, GETDATE(), 'admin', 1),
(3, 3, 3, 2, 'iPhone 12 Pro Max 6GB 256GB Gold', 17000000,  1, GETDATE(), 'admin', 1),
(4, 4, 3, 3, 'iPhone 13 128GB 8GB 256GB Blue', 18000000,  1, GETDATE(), 'admin', 1),
(5, 4, 4, 2, 'iPhone 13 Pro Max 8GB 512GB Gold', 19000000,  1, GETDATE(), 'admin', 1);



-- Thêm dữ liệu vào bảng Imei
INSERT INTO Imei (id_chi_tiet_san_pham, ma_imei, hoat_dong) VALUES 
(1, 'IMEI358776102345674', 1),
(1, 'IMEI864359031234567', 1),
(1, 'IMEI356789104567891', 1),
(1, 'IMEI490154203237518', 1),
(1, 'IMEI867354032145678', 1),

(2, 'IMEI357842107654321', 1),
(2, 'IMEI860123041234567', 1),
(2, 'IMEI351896107654329', 1),
(2, 'IMEI863257031245678', 1),
(2, 'IMEI358765103234567', 1),

(3, 'IMEI490123052376891', 1),
(3, 'IMEI867254032145687', 1),
(3, 'IMEI359876102345678', 1),
(3, 'IMEI860245041234589', 1),
(3, 'IMEI354987107654321', 1),

(4, 'IMEI862135031245678', 1),
(4, 'IMEI352486104567891', 1),
(4, 'IMEI864527032145678', 1),
(4, 'IMEI355671108765432', 1),
(4, 'IMEI863479031245678', 1),

(5, 'IMEI357842109876543', 1),
(5, 'IMEI490123062375891', 1),
(5, 'IMEI867354012345678', 1),
(5, 'IMEI351896107654328', 1),
(5, 'IMEI860245041234567', 1);

-- Thêm dữ liệu vào bảng KhachHang
INSERT INTO KhachHang (ten_khach_hang, so_dien_thoai, ghi_chu, hoat_dong) VALUES 
('Nguyen Van A', '0901234567', N'Khách quen', 1),
('Tran Thi B', '0907654321', N'Khách mới', 1),
('Le Van C', '0912345678', N'', 1),
('Pham Thi D', '0934567890', N'Thường xuyên mua hàng', 1),
('Vo Van E', '0945678901', N'', 1);

-- Thêm dữ liệu vào bảng NhanVien
INSERT INTO NhanVien (ma_nhan_vien, ten_nhan_vien, so_dien_thoai, dia_chi, cccd, mat_khau, chuc_vu, ngay_tao, nguoi_tao, hoat_dong) 
VALUES ('1', 'Nguyen Van F', '0123', '123 Le Loi', '0123', '1', 1, GETDATE(), 'admin', 1);
INSERT INTO NhanVien (ma_nhan_vien, ten_nhan_vien, so_dien_thoai, dia_chi, cccd, mat_khau, chuc_vu, ngay_tao, nguoi_tao, hoat_dong) 
VALUES ('2', 'Nguyen Van D', '01234', '123 Le Loi', '012345', '2', 0, GETDATE(), 'admin', 1);

-- Thêm dữ liệu vào bảng PhieuGiamGia
INSERT INTO PhieuGiamGia (ma_voucher, ten_voucher, so_luong, gia_tri_toi_thieu_ap_dung, phan_tram_giam, so_tien_duoc_giam_toi_da, ngay_bat_dau, ngay_ket_thuc, mo_ta, ngay_tao, nguoi_tao, hoat_dong)
VALUES ('VOUCHER0', 'Giam 0%', 333, 0, 0, 0, '2024-08-08', '2026-01-01', 'Khong giam % nao', GETDATE(), 'admin', 1);
INSERT INTO PhieuGiamGia (ma_voucher, ten_voucher, so_luong, gia_tri_toi_thieu_ap_dung, phan_tram_giam, so_tien_duoc_giam_toi_da, ngay_bat_dau, ngay_ket_thuc, mo_ta, ngay_tao, nguoi_tao, hoat_dong)
VALUES ('VOUCHER1', 'Giam 10%', 300, 1000000, 10, 500000, '2024-08-08', '2026-01-01', 'giam 10% nao', GETDATE(), 'admin', 1);

-- Thêm dữ liệu vào bảng HinhThucThanhToan
INSERT INTO HinhThucThanhToan (ten, hoat_dong) VALUES ('Tien mat', 1);
INSERT INTO HinhThucThanhToan (ten, hoat_dong) VALUES ('Chuyen khoan', 1);

