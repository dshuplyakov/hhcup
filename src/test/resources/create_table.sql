CREATE TABLE `account` (
  `id` UInt16,
  `fname` FixedString(50),
  `sname` FixedString(50),
  `email` FixedString(100),
  `phone ` FixedString(100),
  `sex` FixedString(1),
  `birth` Date,
  `country` FixedString(50),
  `city` FixedString(50),
  `joined` Date,
  `status` FixedString(15)
) ENGINE = Log;
