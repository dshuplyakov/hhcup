CREATE TABLE `account` (
  `id` UInt32 ,
  `fname` String,
  `sname` String,
  `email` String,
  `phone` String,
  `sex` String,
  `birth` Date,
  `country` String,
  `city` String,
  `joined` Date,
  `status` String
) ENGINE = Log;
