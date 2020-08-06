-- --------------------------------------------------------
-- 호스트:                          localhost
-- 서버 버전:                        10.5.4-MariaDB-1:10.5.4+maria~focal - mariadb.org binary distribution
-- 서버 OS:                        debian-linux-gnu
-- HeidiSQL 버전:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- travel 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `travel` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `travel`;

-- 테이블 travel.article 구조 내보내기
CREATE TABLE IF NOT EXISTS `article` (
  `articleno` int(11) NOT NULL AUTO_INCREMENT,
  `bookno` int(11) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `writedate` datetime DEFAULT current_timestamp(),
  `score` decimal(3,2) DEFAULT NULL,
  `score_count` int(11) DEFAULT NULL,
  `text` mediumtext DEFAULT NULL,
  `traveldate` date DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `img` longblob DEFAULT NULL,
  PRIMARY KEY (`articleno`),
  KEY `bookno` (`bookno`),
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`bookno`) REFERENCES `book` (`bookno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 travel.article:~6 rows (대략적) 내보내기
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` (`articleno`, `bookno`, `title`, `writedate`, `score`, `score_count`, `text`, `traveldate`, `day`, `img`) VALUES
	(1, 1, '부산 당일치기, 거제 이동', '2020-08-03 02:56:48', 4.67, NULL, '1일차', '2020-07-04', 1, NULL),
	(2, 1, '거제 여행', '2020-08-03 02:59:58', NULL, NULL, '2일차', '2020-07-05', 2, NULL),
	(3, 1, '귀가', '2020-08-03 12:00:45', NULL, NULL, '3일차', '2020-07-06', 3, NULL),
	(4, 2, '광화문 탐방', '2020-08-04 01:50:57', 4.12, NULL, '1일차', '2020-08-04', 1, NULL),
	(5, 2, '서촌 맛집 탐방', '2020-08-04 01:51:54', 4.53, NULL, '2일차', '2020-08-04', 2, NULL),
	(6, 2, '동대문 패션 타운', '2020-08-04 01:52:31', 4.91, NULL, '3일차', '2020-08-04', 3, NULL),
	(7, 5, '다양하게 즐긴 1일차', '2020-08-06 05:24:57', 4.50, NULL, '1일차', '2013-08-02', 1, NULL),
	(8, 5, '정말 좋았던 2일차', '2020-08-06 14:28:47', 5.00, NULL, '2일차', '2013-08-03', 2, NULL),
	(9, 5, '기차 타느라 다 보지 못한 3일차', '2020-08-06 14:29:24', 4.00, NULL, '3일차', '2013-08-04', 3, NULL),
	(10, 6, '부산 내려가는 길', '2020-08-06 14:30:24', 3.78, NULL, '1일차', '2019-11-16', 1, NULL),
	(11, 6, '첫 지스타 방문', '2020-08-06 14:30:54', 4.86, NULL, '2일차', '2019-11-17', 2, NULL),
	(12, 6, '돌아가기 전 부산 여행', '2020-08-06 14:31:33', 4.39, NULL, '3일차', '2019-11-18', 3, NULL);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;

-- 테이블 travel.article_comment 구조 내보내기
CREATE TABLE IF NOT EXISTS `article_comment` (
  `commentno` int(11) NOT NULL AUTO_INCREMENT,
  `articleno` int(11) DEFAULT NULL,
  `writer` varchar(50) DEFAULT NULL,
  `writedate` datetime DEFAULT current_timestamp(),
  `text` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`commentno`),
  KEY `writer` (`writer`),
  KEY `articleno` (`articleno`),
  CONSTRAINT `article_comment_ibfk_1` FOREIGN KEY (`writer`) REFERENCES `user` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `article_comment_ibfk_2` FOREIGN KEY (`articleno`) REFERENCES `article` (`articleno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 travel.article_comment:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `article_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_comment` ENABLE KEYS */;

-- 테이블 travel.article_score 구조 내보내기
CREATE TABLE IF NOT EXISTS `article_score` (
  `scoreno` int(11) NOT NULL AUTO_INCREMENT,
  `score` int(11) NOT NULL DEFAULT 5,
  `author` varchar(50) DEFAULT NULL,
  `articleno` int(11) DEFAULT NULL,
  PRIMARY KEY (`scoreno`),
  KEY `author` (`author`),
  KEY `articleno` (`articleno`),
  CONSTRAINT `article_score_ibfk_1` FOREIGN KEY (`author`) REFERENCES `user` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `article_score_ibfk_2` FOREIGN KEY (`articleno`) REFERENCES `article` (`articleno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 travel.article_score:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `article_score` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_score` ENABLE KEYS */;

-- 테이블 travel.book 구조 내보내기
CREATE TABLE IF NOT EXISTS `book` (
  `bookno` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `writer` varchar(50) DEFAULT NULL,
  `writedate` datetime DEFAULT current_timestamp(),
  `province` varchar(10) DEFAULT NULL,
  `city` varchar(10) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `score` decimal(3,2) DEFAULT NULL,
  `img` longblob DEFAULT NULL,
  PRIMARY KEY (`bookno`),
  KEY `writer` (`writer`),
  KEY `book_ibfk_2` (`province`,`city`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`writer`) REFERENCES `user` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `book_ibfk_2` FOREIGN KEY (`province`, `city`) REFERENCES `city_list` (`province`, `city`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 travel.book:~6 rows (대략적) 내보내기
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`bookno`, `title`, `writer`, `writedate`, `province`, `city`, `startdate`, `score`, `img`) VALUES
	(1, '2박 3일 거제도 여행', 'dlsgk147@naver.com', '2020-08-03 09:53:24', '경상', '거제', '2020-07-04', NULL, NULL),
	(2, '서울 여행', 'dlsgk147@naver.com', '2020-08-03 16:52:17', '서울', '서울', '2020-08-03', NULL, NULL),
	(3, '서울 나들이', 'dlsgk147@naver.com', '2020-08-03 18:04:05', '서울', '서울', '2020-06-26', NULL, NULL),
	(4, '서울이 좋다', 'dlsgk147@naver.com', '2020-08-03 18:04:45', '서울', '서울', '2020-08-01', NULL, NULL),
	(5, '부산국제록페스티벌에 가다', 'dlsgk147@gmail.com', '2020-08-06 05:19:53', '부산', '부산', '2013-08-02', NULL, NULL),
	(6, '2019 지스타 구경', 'dlsgk147@gmail.com', '2020-08-06 14:22:22', '부산', '부산', '2019-11-16', NULL, NULL);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;

-- 테이블 travel.city_list 구조 내보내기
CREATE TABLE IF NOT EXISTS `city_list` (
  `province` varchar(10) NOT NULL,
  `city` varchar(10) NOT NULL,
  PRIMARY KEY (`province`,`city`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 travel.city_list:~155 rows (대략적) 내보내기
/*!40000 ALTER TABLE `city_list` DISABLE KEYS */;
INSERT INTO `city_list` (`province`, `city`) VALUES
	('강원', '강릉'),
	('강원', '고성'),
	('강원', '동해'),
	('강원', '삼척'),
	('강원', '속초'),
	('강원', '양구'),
	('강원', '양양'),
	('강원', '영월'),
	('강원', '원주'),
	('강원', '인제'),
	('강원', '정선'),
	('강원', '철원'),
	('강원', '춘천'),
	('강원', '태백'),
	('강원', '평창'),
	('강원', '홍천'),
	('강원', '화천'),
	('강원', '횡성'),
	('경기', '가평'),
	('경기', '고양'),
	('경기', '과천'),
	('경기', '광명'),
	('경기', '광주'),
	('경기', '구리'),
	('경기', '군포'),
	('경기', '김포'),
	('경기', '남양주'),
	('경기', '동두천'),
	('경기', '부천'),
	('경기', '성남'),
	('경기', '수원'),
	('경기', '시흥'),
	('경기', '안산'),
	('경기', '안성'),
	('경기', '안양'),
	('경기', '양주'),
	('경기', '양평'),
	('경기', '여주'),
	('경기', '연천'),
	('경기', '오산'),
	('경기', '용인'),
	('경기', '의왕'),
	('경기', '의정부'),
	('경기', '이천'),
	('경기', '인천'),
	('경기', '파주'),
	('경기', '평택'),
	('경기', '포천'),
	('경기', '하남'),
	('경기', '화성'),
	('경상', '거제'),
	('경상', '거창'),
	('경상', '경산'),
	('경상', '경주'),
	('경상', '고령'),
	('경상', '고성'),
	('경상', '구미'),
	('경상', '군위'),
	('경상', '김천'),
	('경상', '김해'),
	('경상', '남해'),
	('경상', '대구'),
	('경상', '문경'),
	('경상', '밀양'),
	('경상', '봉화'),
	('경상', '사천'),
	('경상', '산청'),
	('경상', '상주'),
	('경상', '성주'),
	('경상', '안동'),
	('경상', '양산'),
	('경상', '영덕'),
	('경상', '영양'),
	('경상', '영주'),
	('경상', '영천'),
	('경상', '예천'),
	('경상', '울산'),
	('경상', '울진'),
	('경상', '의령'),
	('경상', '의성'),
	('경상', '진주'),
	('경상', '창녕'),
	('경상', '창원'),
	('경상', '청도'),
	('경상', '청송'),
	('경상', '칠곡'),
	('경상', '통영'),
	('경상', '포항'),
	('경상', '하동'),
	('경상', '함안'),
	('경상', '함양'),
	('경상', '합천'),
	('경샹', '울릉'),
	('부산', '부산'),
	('서울', '서울'),
	('전라', '강진'),
	('전라', '고창'),
	('전라', '고흥'),
	('전라', '곡성'),
	('전라', '광양'),
	('전라', '광주'),
	('전라', '구례'),
	('전라', '군산'),
	('전라', '김제'),
	('전라', '나주'),
	('전라', '남원'),
	('전라', '담양'),
	('전라', '목포'),
	('전라', '무안'),
	('전라', '무주'),
	('전라', '보성'),
	('전라', '부안'),
	('전라', '순창'),
	('전라', '순천'),
	('전라', '신안'),
	('전라', '여수'),
	('전라', '영광'),
	('전라', '영암'),
	('전라', '완도'),
	('전라', '완주'),
	('전라', '익산'),
	('전라', '임실'),
	('전라', '장성'),
	('전라', '장수'),
	('전라', '장흥'),
	('전라', '전주'),
	('전라', '정읍'),
	('전라', '진도'),
	('전라', '진안'),
	('전라', '함평'),
	('전라', '해남'),
	('전라', '화순'),
	('제주', '서귀포'),
	('제주', '제주'),
	('충청', '계룡'),
	('충청', '공주'),
	('충청', '괴산'),
	('충청', '금산'),
	('충청', '논산'),
	('충청', '단양'),
	('충청', '당진'),
	('충청', '대전'),
	('충청', '보령'),
	('충청', '보은'),
	('충청', '부여'),
	('충청', '서산'),
	('충청', '서천'),
	('충청', '세종'),
	('충청', '아산'),
	('충청', '영동'),
	('충청', '예산'),
	('충청', '옥천'),
	('충청', '음성'),
	('충청', '제천'),
	('충청', '증평'),
	('충청', '진천'),
	('충청', '천안'),
	('충청', '청양'),
	('충청', '청주'),
	('충청', '충주'),
	('충청', '태안'),
	('충청', '홍성');
/*!40000 ALTER TABLE `city_list` ENABLE KEYS */;

-- 테이블 travel.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `email` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nickname` varchar(10) NOT NULL,
  `introduce` varchar(100) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 travel.user:~2 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`email`, `password`, `nickname`, `introduce`) VALUES
	('dlsgk147@gmail.com', 'Okey102938', 'mocaran', '안녕하세요'),
	('dlsgk147@naver.com', '1234', 'hinew', 'hello world');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
