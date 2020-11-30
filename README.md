
## 项目说明
由于需要对数据进行批处理，使用`Spring Batch `进行学习与开发，本项目（`spring-batch-example `）旨在提供基于`Spring Batch`进行批处理的示例，每个示例都以解决某一问题为目标，以帮助`Spring Batch`使用者更方便学习，以实践带动学习，欢迎大家可以一起交流学习，欢迎`fork`和添加更多的示例。

## 示例列表

当前示例列表如下：

- [spring-batch-helloworld](https://github.com/mianshenglee/spring-batch-example/tree/master/spring-batch-helloworld)
- [spring-batch-file2db](https://github.com/mianshenglee/spring-batch-example/tree/master/spring-batch-file2db)
- [spring-batch-db2db](https://github.com/mianshenglee/spring-batch-example/tree/master/spring-batch-db2db)
- [spring-batch-beetlsql](https://github.com/mianshenglee/spring-batch-example/tree/master/spring-batch-beetlsql)
- [spring-batch-increment](https://github.com/mianshenglee/spring-batch-example/tree/master/spring-batch-increment)
- [spring-batch-xxl-executor](https://github.com/mianshenglee/spring-batch-example/tree/master/spring-batch-xxl-executor) 与 [xxl-job](https://github.com/mianshenglee/spring-batch-example/tree/master/xxl-job)
- [spring-batch-mysql2mongo](https://github.com/mianshenglee/spring-batch-example/tree/master/spring-batch-mysql2mongo)
- [spring-batch-param](https://github.com/mianshenglee/spring-batch-example/tree/master/spring-batch-param)

示例说明如下：

### `spring-batch-helloworld`

示例功能：很简单的示例，读字符串数组，转为大写，输出到控制，示例虽小，五脏俱全，通过此示例，可以对`Spring Batch`作一个基础的了解。

- 配套文章：[快速了解组件-spring batch(2)之helloworld][2]

### `spring-batch-file2db`

示例功能：从文本读数据，转为`User`实体，输出到数据库中进行存储，通过此示例，可以对`Spring Batch`的默认组件（读文件、写数据库）有一定的了解。

- 配套文章：[快速使用组件-spring batch(3)读文件数据到数据库][3]



### `spring-batch-db2db`

示例功能：从数据库读数据，转为`User`实体，输出到数据库中进行存储，通过此示例，可以对多数据源配置、`Spring Batch`的默认组件（读数据库、写数据库）有一定的了解。


- 配套文章：[决战数据库-spring batch(4)数据库到数据库][4]

### `spring-batch-beetlsql`

示例功能：与`spring-batch-db2db`一致，只是更改了读数据库和写数据库的组件，改为使用`BeetlSql`，更简单，更灵活。


- 配套文章：[便捷的数据读写-spring batch(5)结合beetlSql进行数据读写][5]

### `spring-batch-increment`

示例功能：对数据同步实现增量同步，结合`Spring Batch`和`BeetlSql`，实现基于时间戳实现数据的增量同步。

- 配套文章：[增量同步-spring batch(6)动态参数绑定与增量同步][6]

### `spring-batch-xxl-executor` 和 `xxl-job`

示例功能：在增量同步的基础上，实现企业级的数据同步和调度框架结合，结合`xxl-job`，实现任务调度，并查看数据同步结果。

- 配套文章：[调度与监控-spring batch(7)结合xxl-job进行批处理][7]

### `spring-batch-mysql2mongo`

示例功能: 使用Mongo相组件，实现mysql --> mongodb 的数据同步。

- 配套文章：[mongo同步-spring batch(8)的mongo读写组件使用][8]

### `spring-batch-param`

示例功能: 在 Spring Batch 中进行数据及参数传递的方法。

- 配套文章：[数据共享-spring batch(9)上下文处理][9]

## 示例使用

示例都是基于spring boot建立的java工程，使用maven进行包管理。因此直接使用开发工具如`eclipse`或`idea`导入maven工程即可使用，有几点需要注意：

1. 使用开发工具导入工程后，需要使用maven进行依赖管理，下载相应的jar包，特别是spring batch相关的包。
2. 示例结合文章的说明一起使用，可以先文章，再运行示例。
3. 有一些示例是需要结合数据脚本来运行的，因此运行前请先按提供的sql脚本进行建库，建表，添加测试数据。

## 文章列表

本项目中的示例代码，与我写的[`Spring Batch`系列文章](https://mianshenglee.github.io/)有对应关系，每个示例均可独立运行，学习者可直接阅读文章，结合代码示例进行学习。

- [数据批处理神器-Spring Batch(1)简介及使用场景][1]
- [快速了解组件-spring batch(2)之helloworld][2]
- [快速使用组件-spring batch(3)读文件数据到数据库][3]
- [决战数据库-spring batch(4)数据库到数据库][4]
- [便捷的数据读写-spring batch(5)结合beetlSql进行数据读写][5]
- [增量同步-spring batch(6)动态参数绑定与增量同步][6]
- [调度与监控-spring batch(7)结合xxl-job进行批处理][7]
- [mongo同步-spring batch(8)的mongo读写组件使用][8]
- [数据共享-spring batch(9)上下文处理][9]

## 与我交流

可以使用以下几种方式一起交流：

- [在项目中提issue](https://github.com/mianshenglee/spring-batch-example/issues)：`https://github.com/mianshenglee/spring-batch-example/issues`

- 微信公众号：![mason技术记录](https://mianshenglee.github.io/public/img/wx-qrcode.jpg)
- [我的博客](https://mianshenglee.github.io/)：`https://mianshenglee.github.io/`

[1]: https://mianshenglee.github.io/2019/06/04/springbatch(1).html
[2]: https://mianshenglee.github.io/2019/06/07/spring-batch(2).html
[3]: https://mianshenglee.github.io/2019/06/08/spring-batch(3).html
[4]: https://mianshenglee.github.io/2019/06/09/spring-batch(4).html
[5]: https://mianshenglee.github.io/2019/06/10/spring-batch(5).html
[6]: https://mianshenglee.github.io/2019/06/11/spring-batch(6).htm
[7]: https://mianshenglee.github.io/2019/06/12/spring-batch(7).html
[8]: https://mianshenglee.github.io/2019/08/09/spring-batch(8).html
[9]: https://mianshenglee.github.io/2020/11/30/spring-batch(9).html