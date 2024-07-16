/**
 *
 * @author hatun
 */

USE DuAn1;

-- Bảng HeDieuHanh
INSERT INTO HeDieuHanh (ten, hoat_dong) VALUES ('Chưa nhập', 1);
INSERT INTO HeDieuHanh (ten, hoat_dong) VALUES ('Android', 1);
INSERT INTO HeDieuHanh (ten, hoat_dong) VALUES ('iOS', 1);
INSERT INTO HeDieuHanh (ten, hoat_dong) VALUES ('Windows Phone', 1);
INSERT INTO HeDieuHanh (ten, hoat_dong) VALUES ('HarmonyOS', 1);
INSERT INTO HeDieuHanh (ten, hoat_dong) VALUES ('BlackBerry OS', 1);

-- Bảng ManHinh
INSERT INTO ManHinh (ten, hoat_dong) VALUES ('Chưa nhập', 1);
INSERT INTO ManHinh (ten, hoat_dong) VALUES ('LCD', 1);
INSERT INTO ManHinh (ten, hoat_dong) VALUES ('OLED', 1);
INSERT INTO ManHinh (ten, hoat_dong) VALUES ('AMOLED', 1);
INSERT INTO ManHinh (ten, hoat_dong) VALUES ('Super AMOLED', 1);
INSERT INTO ManHinh (ten, hoat_dong) VALUES ('Retina', 1);

-- Bảng Hang
INSERT INTO Hang (ten, hoat_dong) VALUES ('Chưa nhập', 1);
INSERT INTO Hang (ten, hoat_dong) VALUES ('Samsung', 1);
INSERT INTO Hang (ten, hoat_dong) VALUES ('Apple', 1);
INSERT INTO Hang (ten, hoat_dong) VALUES ('Huawei', 1);
INSERT INTO Hang (ten, hoat_dong) VALUES ('Xiaomi', 1);
INSERT INTO Hang (ten, hoat_dong) VALUES ('Nokia', 1);

-- Bảng CameraTruoc
INSERT INTO CameraTruoc (ten, hoat_dong) VALUES ('Chưa nhập', 1);
INSERT INTO CameraTruoc (ten, hoat_dong) VALUES ('8MP', 1);
INSERT INTO CameraTruoc (ten, hoat_dong) VALUES ('12MP', 1);
INSERT INTO CameraTruoc (ten, hoat_dong) VALUES ('16MP', 1);
INSERT INTO CameraTruoc (ten, hoat_dong) VALUES ('20MP', 1);
INSERT INTO CameraTruoc (ten, hoat_dong) VALUES ('32MP', 1);

-- Bảng CameraSau
INSERT INTO CameraSau (ten, hoat_dong) VALUES ('Chưa nhập', 1);
INSERT INTO CameraSau (ten, hoat_dong) VALUES ('12MP', 1);
INSERT INTO CameraSau (ten, hoat_dong) VALUES ('24MP', 1);
INSERT INTO CameraSau (ten, hoat_dong) VALUES ('48MP', 1);
INSERT INTO CameraSau (ten, hoat_dong) VALUES ('64MP', 1);
INSERT INTO CameraSau (ten, hoat_dong) VALUES ('108MP', 1);

-- Bảng Pin
INSERT INTO Pin (ten, hoat_dong) VALUES ('Chưa nhập', 1);
INSERT INTO Pin (ten, hoat_dong) VALUES ('3000mAh', 1);
INSERT INTO Pin (ten, hoat_dong) VALUES ('4000mAh', 1);
INSERT INTO Pin (ten, hoat_dong) VALUES ('5000mAh', 1);
INSERT INTO Pin (ten, hoat_dong) VALUES ('6000mAh', 1);
INSERT INTO Pin (ten, hoat_dong) VALUES ('7000mAh', 1);

-- Bảng Chip
INSERT INTO Chip (ten, hoat_dong) VALUES ('Chưa nhập', 1);
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
INSERT INTO Ram (ten, hoat_dong) VALUES ('Chưa nhập', 1);
INSERT INTO Ram (ten, hoat_dong) VALUES ('4GB', 1);
INSERT INTO Ram (ten, hoat_dong) VALUES ('6GB', 1);
INSERT INTO Ram (ten, hoat_dong) VALUES ('8GB', 1);
INSERT INTO Ram (ten, hoat_dong) VALUES ('12GB', 1);
INSERT INTO Ram (ten, hoat_dong) VALUES ('16GB', 1);

-- Bảng BoNho
INSERT INTO BoNho (ten, hoat_dong) VALUES ('Chưa nhập', 1);
INSERT INTO BoNho (ten, hoat_dong) VALUES ('64GB', 1);
INSERT INTO BoNho (ten, hoat_dong) VALUES ('128GB', 1);
INSERT INTO BoNho (ten, hoat_dong) VALUES ('256GB', 1);
INSERT INTO BoNho (ten, hoat_dong) VALUES ('512GB', 1);
INSERT INTO BoNho (ten, hoat_dong) VALUES ('1TB', 1);

-- Bảng MauSac
INSERT INTO MauSac (ten, hoat_dong) VALUES ('Chưa nhập', 1);
INSERT INTO MauSac (ten, hoat_dong) VALUES ('Đen', 1);
INSERT INTO MauSac (ten, hoat_dong) VALUES ('Trắng', 1);
INSERT INTO MauSac (ten, hoat_dong) VALUES ('Xanh', 1);
INSERT INTO MauSac (ten, hoat_dong) VALUES ('Đỏ', 1);
INSERT INTO MauSac (ten, hoat_dong) VALUES ('Vàng', 1);

-- Bảng ChiTietSanPham
INSERT INTO ChiTietSanPham (id_san_pham, id_ram, id_bo_nho, id_mau_sac, ten_san_pham_chi_tiet, gia_ban, yeu_thich, ngay_tao, nguoi_tao, hoat_dong)
VALUES (1, 1, 1, 1, 'Samsung Galaxy S21 4GB 64GB Đen', 699.99, 1, GETDATE(), 'admin', 1);
INSERT INTO ChiTietSanPham (id_san_pham, id_ram, id_bo_nho, id_mau_sac, ten_san_pham_chi_tiet, gia_ban, yeu_thich, ngay_tao, nguoi_tao, hoat_dong)
VALUES (2, 2, 2, 2, 'iPhone 12 6GB 128GB Trắng', 799.99, 1, GETDATE(), 'admin', 1);
INSERT INTO ChiTietSanPham (id_san_pham, id_ram, id_bo_nho, id_mau_sac, ten_san_pham_chi_tiet, gia_ban, yeu_thich, ngay_tao, nguoi_tao, hoat_dong)
VALUES (3, 3, 3, 3, 'Huawei Mate 40 8GB 256GB Xanh', 899.99, 1, GETDATE(), 'admin', 1);
INSERT INTO ChiTietSanPham (id_san_pham, id_ram, id_bo_nho, id_mau_sac, ten_san_pham_chi_tiet, gia_ban, yeu_thich, ngay_tao, nguoi_tao, hoat_dong)
VALUES (4, 4, 4, 4, 'Xiaomi Mi 11 12GB 512GB Đỏ', 999.99, 1, GETDATE(), 'admin', 1);
INSERT INTO ChiTietSanPham (id_san_pham, id_ram, id_bo_nho, id_mau_sac, ten_san_pham_chi_tiet, gia_ban, yeu_thich, ngay_tao, nguoi_tao, hoat_dong)
VALUES (5, 5, 5, 5, 'Nokia 9 PureView 16GB 1TB Vàng', 1099.99, 1, GETDATE(), 'admin', 1);

-- Bảng Imei
INSERT INTO Imei (id_chi_tiet_san_pham, ma_imei, hoat_dong) VALUES (1, 'IMEI0001', 1);
INSERT INTO Imei (id_chi_tiet_san_pham, ma_imei, hoat_dong) VALUES (2, 'IMEI0002', 1);
INSERT INTO Imei (id_chi_tiet_san_pham, ma_imei, hoat_dong) VALUES (3, 'IMEI0003', 1);
INSERT INTO Imei (id_chi_tiet_san_pham, ma_imei, hoat_dong) VALUES (4, 'IMEI0004', 1);
INSERT INTO Imei (id_chi_tiet_san_pham, ma_imei, hoat_dong) VALUES (5, 'IMEI0005', 1);

-- Bảng KhachHang
INSERT INTO KhachHang (ten_khach_hang, so_dien_thoai, hoat_dong) VALUES ('Nguyen Van A', '0123456789', 1);
INSERT INTO KhachHang (ten_khach_hang, so_dien_thoai, hoat_dong) VALUES ('Tran Thi B', '0987654321', 1);
INSERT INTO KhachHang (ten_khach_hang, so_dien_thoai, hoat_dong) VALUES ('Le Van C', '0112233445', 1);
INSERT INTO KhachHang (ten_khach_hang, so_dien_thoai, hoat_dong) VALUES ('Pham Thi D', '0223344556', 1);
INSERT INTO KhachHang (ten_khach_hang, so_dien_thoai, hoat_dong) VALUES ('Hoang Van E', '0334455667', 1);

-- Bảng NhanVien
INSERT INTO NhanVien (ma_nhan_vien, ten_nhan_vien, so_dien_thoai, dia_chi, cccd, mat_khau, chuc_vu, ngay_tao, nguoi_tao, hoat_dong) 
VALUES ('NV001', 'Nguyen Van F', '0123456780', '123 Le Loi', '012345678901', 'password1', 1, GETDATE(), 'admin', 1);
INSERT INTO NhanVien (ma_nhan_vien, ten_nhan_vien, so_dien_thoai, dia_chi, cccd, mat_khau, chuc_vu, ngay_tao, nguoi_tao, hoat_dong) 
VALUES ('NV002', 'Tran Thi G', '0987654320', '456 Tran Hung Dao', '012345678902', 'password2', 0, GETDATE(), 'admin', 1);
INSERT INTO NhanVien (ma_nhan_vien, ten_nhan_vien, so_dien_thoai, dia_chi, cccd, mat_khau, chuc_vu, ngay_tao, nguoi_tao, hoat_dong) 
VALUES ('NV003', 'Le Van H', '0112233440', '789 Nguyen Trai', '012345678903', 'password3', 1, GETDATE(), 'admin', 1);
INSERT INTO NhanVien (ma_nhan_vien, ten_nhan_vien, so_dien_thoai, dia_chi, cccd, mat_khau, chuc_vu, ngay_tao, nguoi_tao, hoat_dong) 
VALUES ('NV004', 'Pham Thi I', '0223344550', '321 Le Lai', '012345678904', 'password4', 0, GETDATE(), 'admin', 1);
INSERT INTO NhanVien (ma_nhan_vien, ten_nhan_vien, so_dien_thoai, dia_chi, cccd, mat_khau, chuc_vu, ngay_tao, nguoi_tao, hoat_dong) 
VALUES ('NV005', 'Hoang Van J', '0334455660', '654 Nguyen Thi Minh Khai', '012345678905', 'password5', 1, GETDATE(), 'admin', 1);
INSERT INTO NhanVien (ma_nhan_vien, ten_nhan_vien, so_dien_thoai, dia_chi, cccd, mat_khau, chuc_vu, ngay_tao, nguoi_tao, hoat_dong) 
VALUES ('NV006', N'Nguyễn Thanh Tùng', '0334455660', '654 Nguyen Thi Minh Khai', '012345678906', '123', 1, GETDATE(), 'admin', 1);

-- Bảng PhieuGiamGia
INSERT INTO PhieuGiamGia (ma_voucher, ten_voucher, so_luong, gia_tri_toi_thieu_ap_dung, phan_tram_giam, so_tien_duoc_giam_toi_da, ngay_bat_dau, ngay_ket_thuc, mo_ta, ngay_tao, nguoi_tao, hoat_dong)
VALUES ('VOUCHER1', 'Giam 10%', 100, 100000, 10, 50000, '2023-01-01', '2023-12-31', 'Giam 10% cho don hang tren 100000', GETDATE(), 'admin', 1);
INSERT INTO PhieuGiamGia (ma_voucher, ten_voucher, so_luong, gia_tri_toi_thieu_ap_dung, phan_tram_giam, so_tien_duoc_giam_toi_da, ngay_bat_dau, ngay_ket_thuc, mo_ta, ngay_tao, nguoi_tao, hoat_dong)
VALUES ('VOUCHER2', 'Giam 20%', 200, 200000, 20, 100000, '2023-01-01', '2023-12-31', 'Giam 20% cho don hang tren 200000', GETDATE(), 'admin', 1);
INSERT INTO PhieuGiamGia (ma_voucher, ten_voucher, so_luong, gia_tri_toi_thieu_ap_dung, phan_tram_giam, so_tien_duoc_giam_toi_da, ngay_bat_dau, ngay_ket_thuc, mo_ta, ngay_tao, nguoi_tao, hoat_dong)
VALUES ('VOUCHER3', 'Giam 30%', 300, 300000, 30, 150000, '2023-01-01', '2023-12-31', 'Giam 30% cho don hang tren 300000', GETDATE(), 'admin', 1);
INSERT INTO PhieuGiamGia (ma_voucher, ten_voucher, so_luong, gia_tri_toi_thieu_ap_dung, phan_tram_giam, so_tien_duoc_giam_toi_da, ngay_bat_dau, ngay_ket_thuc, mo_ta, ngay_tao, nguoi_tao, hoat_dong)
VALUES ('VOUCHER4', 'Giam 40%', 400, 400000, 40, 200000, '2023-01-01', '2023-12-31', 'Giam 40% cho don hang tren 400000', GETDATE(), 'admin', 1);
INSERT INTO PhieuGiamGia (ma_voucher, ten_voucher, so_luong, gia_tri_toi_thieu_ap_dung, phan_tram_giam, so_tien_duoc_giam_toi_da, ngay_bat_dau, ngay_ket_thuc, mo_ta, ngay_tao, nguoi_tao, hoat_dong)
VALUES ('VOUCHER5', 'Giam 50%', 500, 500000, 50, 250000, '2023-01-01', '2023-12-31', 'Giam 50% cho don hang tren 500000', GETDATE(), 'admin', 1);

-- Bảng HinhThucThanhToan
INSERT INTO HinhThucThanhToan (ten, hoat_dong) VALUES ('Tien mat', 1);
INSERT INTO HinhThucThanhToan (ten, hoat_dong) VALUES ('Chuyen khoan', 1);
