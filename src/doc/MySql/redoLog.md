

- redolog格式

  表空间号+数据页号+数据页内偏移量+修改了几个字节的数据+实际修改数据

  redo log就是按照上述格式，一条一条的直接就写入到磁盘上的日志文件里去了吗？
  显然不是的！
  其实MySQL内有另外一个数据结构，叫做redo log block，大概你可以理解为，平时我们的数据不是存放在数据页了
  的么，用一页一页的数据页来存放数据。
  那么对于redo log也不是单行单行的写入日志文件的，他是用一个redo log block来存放多个单行日志的。
  一个redo log block是512字节，这个redo log block的512字节分为3个部分，一个是12字节的header块头，一个是
  496字节的body块体，一个是4字节的trailer块尾

- redolog buffer