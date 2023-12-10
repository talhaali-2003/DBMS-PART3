

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

-- Table structure for table `bills`


CREATE TABLE `bills` (
  `id` int(11) NOT NULL,
  `orderid` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table `bills`

INSERT INTO `bills` (`id`, `orderid`, `price`, `discount`, `balance`, `status`) VALUES
(2, 1, 800, 50, 750, 'paid'),
(4, 1, 1000, 0, 1000, 'overdue'),
(5, 1, 800, 50, 750, 'paid'),
(6, 1, 800, 50, 750, 'paid'),
(8, 1, 800, 50, 750, 'paid'),
(9, 1, 800, 50, 750, 'paid'),
(10, 1, 800, 50, 750, 'paid'),
(11, 2, 1000, 50, 950, 'paid');


-- Table structure for table `clientstatistics`

CREATE TABLE `clientstatistics` (
  `id` int(11) NOT NULL,
  `clientid` int(11) NOT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `treecount` int(11) DEFAULT NULL,
  `totaldue` double DEFAULT NULL,
  `totalpaid` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table `clientstatistics`

INSERT INTO `clientstatistics` (`id`, `clientid`, `firstname`, `lastname`, `treecount`, `totaldue`, `totalpaid`) VALUES
(1, 1, 'Big', 'Client1', 2, 1500, 750),
(2, 2, 'Big', 'Client2', 1, 700, 0);

-- Table structure for table `orders`

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `quoteid` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `schedulestart` datetime DEFAULT NULL,
  `scheduleend` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table `orders`

INSERT INTO `orders` (`id`, `quoteid`, `price`, `schedulestart`, `scheduleend`) VALUES
(1, 1, 1000, '2023-11-23 08:00:00', '2023-11-23 12:00:00'),
(2, 1, 1000, '2023-12-01 08:00:00', '2023-12-01 12:00:00');

-- Table structure for table `quotes`

CREATE TABLE `quotes` (
  `id` int(11) NOT NULL,
  `contractorid` int(11) DEFAULT NULL,
  `clientid` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `schedulestart` datetime DEFAULT NULL,
  `scheduleend` datetime DEFAULT NULL,
  `note` varchar(44) DEFAULT 'note ',
  `status` varchar(22) NOT NULL DEFAULT 'pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table `quotes`

INSERT INTO `quotes` (`id`, `contractorid`, `clientid`, `price`, `schedulestart`, `scheduleend`, `note`, `status`) VALUES
(1, 1, 1, 33, '2023-11-25 00:00:00', '2023-11-25 00:00:00', 'note ', 'accepted'),
(2, 2, 2, 213, '2023-11-25 00:00:00', '2023-11-25 00:00:00', 'note ', 'pending'),
(3, 2, 2, 213, '2023-11-25 00:00:00', '2023-11-25 00:00:00', 'note ', 'accept'),
(4, 2, 2, 213, '2023-11-25 00:00:00', '2023-11-25 00:00:00', 'note ', 'reject'),
(5, 1, 1, 100, '2023-11-22 00:00:00', '2023-11-30 00:00:00', 'One tree quote for client 1', 'accept'),
(6, 1, 2, 150, '2023-11-23 00:00:00', '2023-12-01 00:00:00', 'One tree quote for client 2', 'pending'),
(7, 1, 1, 100, '2023-11-22 00:00:00', '2023-11-30 00:00:00', 'One tree quote for client 1', 'pending'),
(8, 1, 2, 150, '2023-11-23 00:00:00', '2023-12-01 00:00:00', 'One tree quote for client 2', 'accept'),
(9, 9, 9, 23, '2023-11-30 00:00:00', '2023-11-30 00:00:00', 'note ', 'reject'),
(10, 9, 9, 33, '2023-11-30 00:00:00', '2023-11-30 00:00:00', 'note ', 'accept');

-- Table structure for table `trees`

CREATE TABLE `trees` (
  `id` int(11) NOT NULL,
  `quoteid` int(11) NOT NULL,
  `size` double DEFAULT NULL,
  `height` double DEFAULT NULL,
  `distanceFromHouse` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table `trees`

INSERT INTO `trees` (`id`, `quoteid`, `size`, `height`, `distanceFromHouse`) VALUES
(1, 1, 10, 25, 8),
(2, 2, 12, 20, 6);

-- Table structure for table `users`

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `creditcard` char(16) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(33) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table `users`

INSERT INTO `users` (`id`, `firstname`, `lastname`, `creditcard`, `email`, `password`) VALUES
(1, 'as', 'ad', '2352351235', 'as@gmail.com', '123'),
(2, 'aa', 'bb', '345236342', 'abc@gmail.com', '123'),
(3, 'XC', 'Client1', '1111222233334444', 'bigclient1@example.com', 'password1'),
(4, 'ASD', 'Client2', '5555666677778888', 'bigclient2@example.com', 'password2'),
(5, 'Easy', 'Client1', '1111222233334444', 'easyclient1@example.com', 'password1'),
(6, 'Easy', 'Client2', '5555666677778888', 'easyclient2@example.com', 'password2'),
(7, 'as2', 'as', '23523364', 'as2@Gmail.com', '123'),
(8, 'qw', 'qw', '23523', 'qw@gmail.com', '123'),
(9, 'qs', 'ss', '2352351235', 'qs@gmail.com', '123'),
(12, 'CLINTSSS', 'Client1', '1111222233334444', 'bigclient11@example.com', ''),
(13, 'ABCDX', 'Client2', '5555666677778888', 'bigclient222@example.com', ''),
(14, 'Good', 'Client', '1111222233334444', 'goodclient@example.com', 'password1');

-- Table structure for table `users2`

CREATE TABLE `users2` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `userrole` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table `users2`

INSERT INTO `users2` (`id`, `username`, `userrole`, `email`, `password`) VALUES
(1, 'dsf', 'sdf', 'asdf@gmail.com', 'sss'),
(2, 'dv', 'client', 'user@gmail.com', '123'),
(3, 'dd', 'dd', 'dd@gmail.com', '123'),
(4, 'ddd', 'dv', 'dv@gmail.com', '123'),
(5, 'sd', 'divid', 'sd2f@gmail.com', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`id`),
  ADD KEY `orderid` (`orderid`);

--
-- Indexes for table `clientstatistics`
--
ALTER TABLE `clientstatistics`
  ADD PRIMARY KEY (`id`),
  ADD KEY `clientid` (`clientid`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `quoteid` (`quoteid`);

--
-- Indexes for table `quotes`
--
ALTER TABLE `quotes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `contractorid` (`contractorid`),
  ADD KEY `clientid` (`clientid`);

--
-- Indexes for table `trees`
--
ALTER TABLE `trees`
  ADD PRIMARY KEY (`id`),
  ADD KEY `quoteid` (`quoteid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `users2`
--
ALTER TABLE `users2`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bills`
--
ALTER TABLE `bills`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `clientstatistics`
--
ALTER TABLE `clientstatistics`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `quotes`
--
ALTER TABLE `quotes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `trees`
--
ALTER TABLE `trees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `users2`
--
ALTER TABLE `users2`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bills`
--
ALTER TABLE `bills`
  ADD CONSTRAINT `bills_ibfk_1` FOREIGN KEY (`orderid`) REFERENCES `orders` (`id`);

--
-- Constraints for table `clientstatistics`
--
ALTER TABLE `clientstatistics`
  ADD CONSTRAINT `clientstatistics_ibfk_1` FOREIGN KEY (`clientid`) REFERENCES `users` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`quoteid`) REFERENCES `quotes` (`id`);

--
-- Constraints for table `quotes`
--
ALTER TABLE `quotes`
  ADD CONSTRAINT `quotes_ibfk_1` FOREIGN KEY (`contractorid`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `quotes_ibfk_2` FOREIGN KEY (`clientid`) REFERENCES `users` (`id`);

--
-- Constraints for table `trees`
--
ALTER TABLE `trees`
  ADD CONSTRAINT `trees_ibfk_1` FOREIGN KEY (`quoteid`) REFERENCES `quotes` (`id`);
COMMIT;
