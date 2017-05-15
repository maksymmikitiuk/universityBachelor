-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 07, 2017 at 04:25 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `university`
--

-- --------------------------------------------------------

--
-- Table structure for table `chairs`
--

CREATE TABLE `chairs` (
  `idchairs` int(11) NOT NULL,
  `abbreviation` varchar(45) DEFAULT NULL,
  `name` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chairs`
--

INSERT INTO `chairs` (`idchairs`, `abbreviation`, `name`) VALUES
(1, 'ОТ', 'Кафедра обчислювальної техніки'),
(2, 'ЗІ', 'Кафедра захисту iнформацiї'),
(3, 'ПІ', 'Кафедра програмного забезпечення '),
(4, 'КН', 'Кафедра комп\'ютерних наук'),
(5, 'інше', 'інше');

-- --------------------------------------------------------

--
-- Table structure for table `diplomaform`
--

CREATE TABLE `diplomaform` (
  `iddiplomaForm` int(11) NOT NULL,
  `name` varchar(150) DEFAULT NULL,
  `idqualificationLevel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `diplomaform`
--

INSERT INTO `diplomaform` (`iddiplomaForm`, `name`, `idqualificationLevel`) VALUES
(1, 'Дипломна робота', 1),
(2, 'Дипломний проект', 1),
(3, 'Дипломна робота', 2),
(4, 'Дипломна робота', 2),
(5, 'Дипломна робота', 3),
(6, 'Дипломна робота', 3);

-- --------------------------------------------------------

--
-- Table structure for table `diplomamarks`
--

CREATE TABLE `diplomamarks` (
  `iddiplomaMarks` int(11) NOT NULL,
  `owner` int(11) DEFAULT NULL,
  `typeOwner` int(11) DEFAULT NULL,
  `mark` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `diplomamarks`
--

INSERT INTO `diplomamarks` (`iddiplomaMarks`, `owner`, `typeOwner`, `mark`) VALUES
(1, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `diplomasubjects`
--

CREATE TABLE `diplomasubjects` (
  `iddiplomaSubjects` int(11) NOT NULL,
  `subject` varchar(5000) DEFAULT NULL,
  `defencediploma` datetime DEFAULT NULL,
  `type_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `curator_id` int(11) NOT NULL,
  `reviewer_id` int(11) NOT NULL,
  `mark_id` int(11) NOT NULL,
  `tag` varchar(5000) DEFAULT '',
  `plag` decimal(15,2) DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `diplomasubjects`
--

INSERT INTO `diplomasubjects` (`iddiplomaSubjects`, `subject`, `defencediploma`, `type_id`, `student_id`, `curator_id`, `reviewer_id`, `mark_id`, `tag`, `plag`) VALUES
(1, 'Інформаційна система для обліку та аналізу випускних робіт на кафедрі обчислювальної техніки', '2017-05-04 00:35:22', 1, 1, 1, 2, 1, '', '0.00');

-- --------------------------------------------------------

--
-- Table structure for table `diplomatype`
--

CREATE TABLE `diplomatype` (
  `iddiplomaType` int(11) NOT NULL,
  `name` varchar(150) DEFAULT NULL,
  `iddiplomaForm` int(11) NOT NULL,
  `comment` varchar(45) DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `diplomatype`
--

INSERT INTO `diplomatype` (`iddiplomaType`, `name`, `iddiplomaForm`, `comment`) VALUES
(1, 'Комплексна', 1, NULL),
(2, 'Міжкафедральна', 1, NULL),
(3, 'Комплексна', 2, NULL),
(4, 'Міжкафедральна', 2, NULL),
(5, 'Комплексна', 3, NULL),
(6, 'Міжкафедральна', 3, NULL),
(7, 'Комплексна', 4, NULL),
(8, 'Міжкафедральна', 4, NULL),
(9, 'Комплексна', 5, NULL),
(10, 'Міжкафедральна', 5, NULL),
(11, 'Комплексна', 6, NULL),
(12, 'Міжкафедральна', 6, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `documentregistration`
--

CREATE TABLE `documentregistration` (
  `iddocumentRegistration` int(11) NOT NULL,
  `documentregistration` datetime DEFAULT NULL,
  `idusers` int(11) NOT NULL,
  `iddiplomaSubjects` int(11) NOT NULL,
  `path` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `documentregistration`
--

INSERT INTO `documentregistration` (`iddocumentRegistration`, `documentregistration`, `idusers`, `iddiplomaSubjects`, `path`) VALUES
(1, NULL, 1, 1, 'где-то');

-- --------------------------------------------------------

--
-- Table structure for table `documenttype`
--

CREATE TABLE `documenttype` (
  `iddocumentType` int(11) NOT NULL,
  `typename` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `documenttype`
--

INSERT INTO `documenttype` (`iddocumentType`, `typename`) VALUES
(1, 'Записка'),
(2, 'Додатки'),
(3, 'Презентація');

-- --------------------------------------------------------

--
-- Table structure for table `groupform`
--

CREATE TABLE `groupform` (
  `idgroupForm` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `groupform`
--

INSERT INTO `groupform` (`idgroupForm`, `name`) VALUES
(1, 'мс'),
(2, 'спеціаліст'),
(3, 'бакалавр'),
(4, 'магістр'),
(5, '-');

-- --------------------------------------------------------

--
-- Table structure for table `groups`
--

CREATE TABLE `groups` (
  `idgroups` int(11) NOT NULL,
  `abbreviation` varchar(45) DEFAULT NULL,
  `name` varchar(300) DEFAULT NULL,
  `yearofentering` datetime DEFAULT NULL,
  `idchairs` int(11) NOT NULL,
  `idqualificationLevel` int(11) NOT NULL,
  `idgroupForm` int(11) NOT NULL,
  `idgroupType` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `groups`
--

INSERT INTO `groups` (`idgroups`, `abbreviation`, `name`, `yearofentering`, `idchairs`, `idqualificationLevel`, `idgroupForm`, `idgroupType`) VALUES
(1, '1КІ-13б', '1 група, комп\'ютерна інженерія, бакалавр', NULL, 1, 1, 3, 1),
(2, '2КІ-13б', '2 група, комп\'ютерна інженерія, бакалавр', NULL, 1, 1, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `grouptype`
--

CREATE TABLE `grouptype` (
  `idgroupType` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `grouptype`
--

INSERT INTO `grouptype` (`idgroupType`, `name`) VALUES
(1, 'Стаціонар'),
(2, 'Заочне'),
(3, '2 вища');

-- --------------------------------------------------------

--
-- Table structure for table `marks`
--

CREATE TABLE `marks` (
  `idmarks` int(11) NOT NULL,
  `nationalscale` varchar(45) DEFAULT NULL,
  `nationalscalenumber` int(1) DEFAULT NULL,
  `pointsfrom` int(11) DEFAULT NULL,
  `pointsto` int(11) DEFAULT NULL,
  `ects` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `marks`
--

INSERT INTO `marks` (`idmarks`, `nationalscale`, `nationalscalenumber`, `pointsfrom`, `pointsto`, `ects`) VALUES
(1, '1', 1, 1, 1, '1');

-- --------------------------------------------------------

--
-- Table structure for table `qualificationlevel`
--

CREATE TABLE `qualificationlevel` (
  `idqualificationLevel` int(11) NOT NULL,
  `name` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `qualificationlevel`
--

INSERT INTO `qualificationlevel` (`idqualificationLevel`, `name`) VALUES
(1, 'Бакалавр'),
(2, 'Спеціаліст'),
(3, 'Магістр');

-- --------------------------------------------------------

--
-- Table structure for table `structureofthediploma`
--

CREATE TABLE `structureofthediploma` (
  `idstructureOfTheDiploma` int(11) NOT NULL,
  `iddocumentType` int(11) NOT NULL,
  `iddiplomaType` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `structureofthediploma`
--

INSERT INTO `structureofthediploma` (`idstructureOfTheDiploma`, `iddocumentType`, `iddiplomaType`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 1, 2),
(5, 2, 2),
(6, 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `idstudents` int(11) NOT NULL,
  `studentidcard` varchar(45) DEFAULT NULL,
  `firstName` varchar(50) DEFAULT NULL,
  `middleName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `idgroups` int(11) NOT NULL,
  `lfmName` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`idstudents`, `studentidcard`, `firstName`, `middleName`, `lastName`, `email`, `phone`, `idgroups`, `lfmName`) VALUES
(1, 'ВН09986115', 'Максим', 'Васильович', 'Микитюк', 'maksymmikitiuk@gmail.com', '0635044213', 1, 'Микитюк Максим Васильович'),
(3, 'ВН09986104', 'Павло', 'Олегович', 'Бевз', 'email@email.com', '0635044200', 1, 'Бевз Павло Олегович'),
(4, 'ВН09986105', 'Станіслав', 'Юрійович', 'Бондарчук', 'email@email.com', '0635044201', 1, 'Бондарчук Станіслав Юрійович'),
(5, 'ВН09986106', 'Владислав', 'Русланович', 'Гронюк', 'email@email.com', '0635044202', 1, 'Гронюк Владислав Русланович'),
(6, 'ВН09986107', 'Денис', 'Русланович', 'Глушко', 'email@email.com', '0635044203', 1, 'Глушко Денис Русланович'),
(7, 'ВН09986108', 'Оксана', 'Станіславівна', 'Добровольська', 'email@email.com', '0635044204', 1, 'Добровольська Оксана Станіславівна'),
(8, 'ВН09986109', 'Микола', 'Олександрович', 'Зайцев', 'email@email.com', '0635044205', 1, 'Зайцев Микола Олександрович'),
(9, 'ВН09986110', 'Олександр', 'Юрійович', 'Кондратюк', 'email@email.com', '0635044206', 1, 'Кондратюк Олександр Юрійович'),
(10, 'ВН09986111', 'Богдан', 'Миколайович', 'Купчишин', 'email@email.com', '0635044207', 1, 'Купчишин Богдан Миколайович'),
(11, 'ВН09986112', 'Дмитро', 'Володимирович', 'Куций', 'email@email.com', '0635044208', 1, 'Куций Дмитро Володимирович'),
(12, 'ВН09986113', 'Олександр', 'Іванович', 'Мельничук', 'email@email.com', '0635044209', 1, 'Мельничук Олександр Іванович'),
(13, 'ВН09986114', 'Андрій', 'Ігорович', 'Настасьєв', 'email@email.com', '0635044210', 1, 'Настасьєв Андрій Ігорович'),
(14, 'ВН09986125', 'Вадим', 'Валерійович', 'Озаринський', 'email@email.com', '0635044211', 1, 'Озаринський Вадим Валерійович'),
(15, 'ВН09986116', 'Володимир', 'Віталійович', 'Петрунько', 'email@email.com', '0635044212', 1, 'Петрунько Володимир Віталійович'),
(16, 'ВН09986117', 'Дмитро', 'Анатолійович', 'Провозьон', 'email@email.com', '0635044221', 1, 'Провозьон Дмитро Анатолійович'),
(17, 'ВН09986118', 'Владислав', 'Віталійович', 'Рацюк', 'email@email.com', '0635044214', 1, 'Рацюк Владислав Віталійович'),
(18, 'ВН09986119', 'Андрій', 'Анатолійович', 'Рудик', 'email@email.com', '0635044215', 1, 'Рудик Андрій Анатолійович'),
(19, 'ВН09986120', 'Олександр', 'Ігорович', 'Савчук', 'email@email.com', '0635044216', 1, 'Савчук Олександр Ігорович'),
(20, 'ВН09986121', 'Владислав', 'Юрійович', 'Тарануха', 'email@email.com', '0635044217', 1, 'Тарануха Владислав Юрійович'),
(21, 'ВН09986122', 'Ігор', 'Анатолійович', 'Чернуха', 'email@email.com', '0635044218', 1, 'Чернуха Ігор Анатолійович'),
(24, 'ВН09986126', 'Анна', 'Олегівна', 'Ніколюк', 'email@email.com', '0635044221', 2, 'Ніколюк Анна Олегівна');

--
-- Triggers `students`
--
DELIMITER $$
CREATE TRIGGER `set_full_name_insert` BEFORE INSERT ON `students` FOR EACH ROW SET NEW.lfmName = CONCAT(NEW.lastName, ' ', NEW.firstName, ' ', NEW.middleName)
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `set_full_name_update` BEFORE UPDATE ON `students` FOR EACH ROW SET NEW.lfmName = CONCAT(NEW.lastName, ' ', NEW.firstName, ' ', NEW.middleName)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `teachers`
--

CREATE TABLE `teachers` (
  `idteachers` int(11) NOT NULL,
  `firstName` varchar(50) DEFAULT NULL,
  `middleName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `idchairs` int(11) NOT NULL,
  `lfmName` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`idteachers`, `firstName`, `middleName`, `lastName`, `idchairs`, `lfmName`) VALUES
(1, 'Дмитро', 'Васильович', 'Кисюк', 1, 'Кисюк Дмитро Васильович'),
(2, 'Сергій', 'Михайлович', 'Цирульник', 1, 'Цирульник Сергій Михайлович'),
(3, 'Тетяна', 'Іванівна', 'Трояновська', 1, 'Трояновська Тетяна Іванівна'),
(4, 'Володимир', 'Андрійович', 'Лужецький', 2, 'Лужецький Володимир Андрійович'),
(5, 'Наталія', 'Романівна', 'Кондратенко', 2, 'Кондратенко Наталія Романівна'),
(6, 'Андрій', 'Веніамінович', 'Дудатьєв', 2, 'Дудатьєв Андрій Веніамінович');

--
-- Triggers `teachers`
--
DELIMITER $$
CREATE TRIGGER `set_full_name` BEFORE INSERT ON `teachers` FOR EACH ROW SET NEW.lfmName = CONCAT(NEW.lastName, ' ', NEW.firstName, ' ', NEW.middleName)
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `set_full_names` BEFORE UPDATE ON `teachers` FOR EACH ROW SET NEW.lfmName = CONCAT(NEW.lastName, ' ', NEW.firstName, ' ', NEW.middleName)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `typeOwnerMark`
--

CREATE TABLE `typeOwnerMark` (
  `id` int(11) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `idusers` int(11) NOT NULL,
  `password` varchar(300) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `firstName` varchar(50) DEFAULT NULL,
  `middleName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`idusers`, `password`, `username`, `firstName`, `middleName`, `lastName`) VALUES
(5, '655fa5d6d229c94f0a320c6332a69144', 'milena', 'Милена', 'Михайловна', 'Чижова');

-- --------------------------------------------------------

--
-- Table structure for table `еxceptions`
--

CREATE TABLE `еxceptions` (
  `id` int(11) DEFAULT NULL,
  `value` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chairs`
--
ALTER TABLE `chairs`
  ADD PRIMARY KEY (`idchairs`);

--
-- Indexes for table `diplomaform`
--
ALTER TABLE `diplomaform`
  ADD PRIMARY KEY (`iddiplomaForm`,`idqualificationLevel`);

--
-- Indexes for table `diplomamarks`
--
ALTER TABLE `diplomamarks`
  ADD PRIMARY KEY (`iddiplomaMarks`);

--
-- Indexes for table `diplomasubjects`
--
ALTER TABLE `diplomasubjects`
  ADD PRIMARY KEY (`iddiplomaSubjects`);

--
-- Indexes for table `diplomatype`
--
ALTER TABLE `diplomatype`
  ADD PRIMARY KEY (`iddiplomaType`);

--
-- Indexes for table `documentregistration`
--
ALTER TABLE `documentregistration`
  ADD PRIMARY KEY (`iddocumentRegistration`,`idusers`,`iddiplomaSubjects`);

--
-- Indexes for table `documenttype`
--
ALTER TABLE `documenttype`
  ADD PRIMARY KEY (`iddocumentType`);

--
-- Indexes for table `groupform`
--
ALTER TABLE `groupform`
  ADD PRIMARY KEY (`idgroupForm`);

--
-- Indexes for table `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`idgroups`,`idchairs`,`idqualificationLevel`,`idgroupForm`);

--
-- Indexes for table `grouptype`
--
ALTER TABLE `grouptype`
  ADD PRIMARY KEY (`idgroupType`);

--
-- Indexes for table `marks`
--
ALTER TABLE `marks`
  ADD PRIMARY KEY (`idmarks`);

--
-- Indexes for table `qualificationlevel`
--
ALTER TABLE `qualificationlevel`
  ADD PRIMARY KEY (`idqualificationLevel`);

--
-- Indexes for table `structureofthediploma`
--
ALTER TABLE `structureofthediploma`
  ADD PRIMARY KEY (`idstructureOfTheDiploma`,`iddocumentType`,`iddiplomaType`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`idstudents`,`idgroups`);

--
-- Indexes for table `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`idteachers`,`idchairs`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`idusers`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chairs`
--
ALTER TABLE `chairs`
  MODIFY `idchairs` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `diplomaform`
--
ALTER TABLE `diplomaform`
  MODIFY `iddiplomaForm` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `diplomamarks`
--
ALTER TABLE `diplomamarks`
  MODIFY `iddiplomaMarks` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `diplomasubjects`
--
ALTER TABLE `diplomasubjects`
  MODIFY `iddiplomaSubjects` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `diplomatype`
--
ALTER TABLE `diplomatype`
  MODIFY `iddiplomaType` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `documentregistration`
--
ALTER TABLE `documentregistration`
  MODIFY `iddocumentRegistration` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `documenttype`
--
ALTER TABLE `documenttype`
  MODIFY `iddocumentType` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `groupform`
--
ALTER TABLE `groupform`
  MODIFY `idgroupForm` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `groups`
--
ALTER TABLE `groups`
  MODIFY `idgroups` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `grouptype`
--
ALTER TABLE `grouptype`
  MODIFY `idgroupType` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `marks`
--
ALTER TABLE `marks`
  MODIFY `idmarks` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `qualificationlevel`
--
ALTER TABLE `qualificationlevel`
  MODIFY `idqualificationLevel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `structureofthediploma`
--
ALTER TABLE `structureofthediploma`
  MODIFY `idstructureOfTheDiploma` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `idstudents` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT for table `teachers`
--
ALTER TABLE `teachers`
  MODIFY `idteachers` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `idusers` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
