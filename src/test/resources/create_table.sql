CREATE TABLE `account` (
  `id` UInt32 ,
  `fname` String,
  `sname` String,
  `email` String,
  `phone` String,
  `sex` String,
  `birth` UInt32,
  `country` String,
  `city` String,
  `joined` UInt32,
  `status` String
) ENGINE = Log;
