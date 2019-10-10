
## spring-batch-example 项目介绍
本项目旨在提供基于`Spring Batch`进行批处理的示例，以帮助`Spring Batch`使用者更方便学习，和大家一起交流学习。欢迎`fork`和添加更多的示例。

## 示例列表
当前示例与我写的[`Spring Batch`系列][1]有对应关系，每个示例均可独立运行，学习者可直接参考。当前示例列表如下：

- `spring-batch-helloworld`

很简单的示例，读字符串数组，转为大写，输出到控制，示例虽小，五脏俱全，通过此示例，可以对`Spring Batch`作一个基础的了解。

- `spring-batch-file2db`

示例功能是从文本读数据，转为`User`实体，输出到数据库中进行存储，通过此示例，可以对`Spring Batch`的默认组件（读文件、写数据库）有一定的了解。


- `spring-batch-db2db`

示例功能是从数据库读数据，转为`User`实体，输出到数据库中进行存储，通过此示例，可以对多数据源配置、`Spring Batch`的默认组件（读数据库、写数据库）有一定的了解。


- `spring-batch-beetlsql`

示例功能是与`spring-batch-db2db`一致，只是更改了读数据库和写数据库的组件，改为使用`BeetlSql`，更简单，更灵活。


- `spring-batch-increment`

示例功能是对数据同步实现增量同步，结合`Spring Batch`和`BeetlSql`，实现基于时间戳实现数据的增量同步。


- `spring-batch-xxl-executor` 和 `xxl-job`

示例功能是在增量同步的基础上，实现企业级的数据同步和调度框架结合，结合`xxl-job`，实现任务调度，并查看数据同步结果。

- `spring-batch-mysql2mongo`

示例功能: 使用Mongo相组件，实现mysql --> mongodb 的数据同步。
