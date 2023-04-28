-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 21, 2022 at 11:30 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perpustakaan`
--

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `id_buku` int(100) NOT NULL,
  `id_kategori` int(100) NOT NULL,
  `nama_buku` varchar(255) NOT NULL,
  `penulis` varchar(255) DEFAULT NULL,
  `penerbit` varchar(255) DEFAULT NULL,
  `stok` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`id_buku`, `id_kategori`, `nama_buku`, `penulis`, `penerbit`, `stok`) VALUES
(1, 1, 'PJOK KELAS 11', 'TATANG', 'ERLANGGA', 102),
(2, 1, 'PKN', 'ERLANGGA', 'LILIS', 17),
(4, 2, 'Konan', 'Andi', 'Acong', 4),
(7, 1, 'PLBJ', 'Adam', 'ERLANGGA', 0),
(11, 2, 'Pemograman', 'KOMPAS', 'ADAM', 50),
(12, 1, 'Matematika Kelas 12', '', 'Erlangga', 11);

-- --------------------------------------------------------

--
-- Table structure for table `guru`
--

CREATE TABLE `guru` (
  `username` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `ttl` date NOT NULL,
  `no_telp` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `last_login` datetime NOT NULL,
  `status_login` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `guru`
--

INSERT INTO `guru` (`username`, `nama`, `ttl`, `no_telp`, `password`, `last_login`, `status_login`) VALUES
('Anissa', 'Anissa', '1992-08-06', '089712312315', 'anissa', '2022-08-16 18:31:06', 1),
('Ayu', 'ayu', '1992-08-07', '082131213123', 'ayu', '2022-08-16 18:57:00', 2),
('BambangS', 'Bambang Sustrisno', '1974-12-01', '0877123123123', 'bambang', '2022-08-16 18:10:39', 1),
('Dwita', 'Dwita', '1986-12-20', '0812351513414', 'Dwita', '2022-08-16 18:28:12', 1),
('Elen', 'elen ratna sari', '1986-12-12', '0899116785466', 'elen', '2022-08-16 18:28:41', 1),
('Erna', 'Erna', '1988-12-22', '089124652313', 'erna', '2022-08-16 18:29:03', 1),
('Fitri', 'Fitri Nur', '1996-08-17', '0899765678[', 'fitri', '2022-08-16 18:29:34', 1),
('guru', 'Guru', '1977-08-16', '08965445788', 'guru', '2022-08-16 21:46:19', 2);

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `id_kategori` int(100) NOT NULL,
  `deskripsi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id_kategori`, `deskripsi`) VALUES
(1, 'Sekolah'),
(2, 'umum'),
(3, 'Komik'),
(4, 'Majalah');

-- --------------------------------------------------------

--
-- Table structure for table `operator`
--

CREATE TABLE `operator` (
  `id_operator` int(10) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `no_telp` varchar(100) NOT NULL,
  `last_login` datetime NOT NULL,
  `status_login` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `operator`
--

INSERT INTO `operator` (`id_operator`, `nama`, `username`, `password`, `no_telp`, `last_login`, `status_login`) VALUES
(1, 'adam', 'adam', 'adam', '082121312312', '2021-07-05 13:59:55', 2),
(2, 'didit', 'didit', '123', '08812371859', '2021-06-27 11:59:32', 1),
(3, 'sahrul', 'rul', '123', '085151213139', '2021-06-27 12:06:18', 1),
(4, 'Budi', 'budi', '123', '089612315960', '2021-07-04 00:06:13', 1),
(5, 'admin', 'admin', 'admin', '089528506933', '2022-08-18 20:28:42', 2);

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `nis` varchar(50) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `kelas` varchar(10) NOT NULL,
  `no_telp` varchar(255) NOT NULL,
  `ttl` date NOT NULL,
  `password` varchar(255) NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `status_login` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`nis`, `nama`, `kelas`, `no_telp`, `ttl`, `password`, `last_login`, `status_login`) VALUES
('00112444', 'Syaiful', '10', '08515912305', '2000-08-27', '0512313', '2022-08-17 18:20:20', 1),
('00112450', 'Sultan Ahmad', '10', '0852131313123', '2002-08-17', 'sultan', '2022-08-17 18:05:52', 1),
('00112451', 'Hilal ahmad', '10', '089558581939', '2002-08-08', 'hilal', '2022-08-17 18:10:10', 1),
('00112543', 'dum', '11', '085959595', '2021-06-01', 'dum', '2022-08-16 18:59:36', 2),
('00112544', 'Budiyanto', '11', '081123338859', '2021-06-01', 'Budiyanto', '2022-08-16 18:51:18', 2),
('00112545', 'Adam', '11', '081235616115', '2022-08-06', 'Adam', '2022-08-16 18:31:54', 1),
('00112601', 'Subardjo', '12', '085104923025', '2001-08-18', 'subardjo', '2022-08-17 18:13:03', 1),
('00112602', 'Kimaya', '12', '089604020605', '2002-12-18', 'kimaya123', '2022-08-17 18:13:48', 1),
('00112603', 'Subakti', '12', '085154356413', '2000-05-28', 'SUBAKTI123', '2022-08-17 18:14:39', 1);

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `id_status` int(1) NOT NULL,
  `keterangan` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`id_status`, `keterangan`) VALUES
(1, 'Pending'),
(2, 'Acc'),
(3, 'Selesai'),
(4, 'Batal');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_guru`
--

CREATE TABLE `transaksi_guru` (
  `id_transaksi` int(100) NOT NULL,
  `username` varchar(255) NOT NULL,
  `id_buku` int(255) NOT NULL,
  `tgl_pinjam` date NOT NULL,
  `tgl_kembali` date NOT NULL,
  `id_status` int(1) NOT NULL,
  `tgl_transaksi` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi_guru`
--

INSERT INTO `transaksi_guru` (`id_transaksi`, `username`, `id_buku`, `tgl_pinjam`, `tgl_kembali`, `id_status`, `tgl_transaksi`) VALUES
(4, 'guru', 2, '2021-06-01', '2021-06-01', 4, '2021-06-29'),
(5, 'guru', 1, '2021-06-01', '2021-06-01', 3, '2021-06-29'),
(6, 'guru', 12, '2021-07-01', '2021-07-31', 2, '2021-07-04'),
(7, 'guru', 1, '2022-08-07', '2022-08-14', 3, '2022-08-16'),
(8, 'Ayu', 11, '2022-08-01', '2022-08-15', 2, '2022-08-16'),
(9, 'Ayu', 1, '2022-08-05', '2022-08-20', 3, '2022-08-16'),
(10, 'Ayu', 2, '2022-08-05', '2022-08-13', 3, '2022-08-16'),
(11, 'Anissa', 1, '2022-08-17', '2022-08-31', 2, '2022-08-16');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_siswa`
--

CREATE TABLE `transaksi_siswa` (
  `id_transaksi` int(100) NOT NULL,
  `nis` varchar(50) NOT NULL,
  `id_buku` int(50) NOT NULL,
  `tgl_pinjam` date NOT NULL,
  `tgl_kembali` date NOT NULL,
  `id_status` int(1) NOT NULL,
  `tgl_transaksi` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi_siswa`
--

INSERT INTO `transaksi_siswa` (`id_transaksi`, `nis`, `id_buku`, `tgl_pinjam`, `tgl_kembali`, `id_status`, `tgl_transaksi`) VALUES
(2, '00112546', 11, '2022-08-01', '2022-08-31', 4, '2021-07-04'),
(3, '00112546', 2, '2022-08-01', '2022-08-31', 4, '2021-07-04'),
(4, '00112546', 2, '2022-08-01', '2022-08-31', 3, '2021-07-04'),
(5, '00112546', 1, '2022-08-01', '2022-08-19', 2, '2022-08-16'),
(6, '00112546', 1, '2022-08-16', '2022-08-23', 1, '2022-08-16'),
(7, '00112546', 1, '2022-08-16', '2022-08-30', 3, '2022-08-16');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`id_buku`);

--
-- Indexes for table `guru`
--
ALTER TABLE `guru`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indexes for table `operator`
--
ALTER TABLE `operator`
  ADD PRIMARY KEY (`id_operator`);

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`nis`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`id_status`);

--
-- Indexes for table `transaksi_guru`
--
ALTER TABLE `transaksi_guru`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- Indexes for table `transaksi_siswa`
--
ALTER TABLE `transaksi_siswa`
  ADD PRIMARY KEY (`id_transaksi`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
