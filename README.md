# 编程新技术实务 实验2

Java webapp
我具体做了什么大概都在git的message中了

### 环境

```shell
Ubuntu 18.04.4 LTS

mysql  Ver 14.14 Distrib 5.7.31, for Linux (x86_64) using  EditLine wrapper

openjdk 11.0.8 2020-07-14
OpenJDK Runtime Environment (build 11.0.8+10-post-Ubuntu-0ubuntu118.04.1)
OpenJDK 64-Bit Server VM (build 11.0.8+10-post-Ubuntu-0ubuntu118.04.1, mixed mode, sharing)

Apache Tomcat/9.0.39
```

### 项目结构

```
├─out
│  └─artifacts						// 这部分为编译生成的文件，可以直接部署
│      ├─Exp2						// 打好的war包，建议不用
│      └─Exp2_war_exploded			// 最后部署的项目
│          │  index.jsp
│          │  result.jsp
│          │  show_data.jsp
│          ├─js						// js脚本
│          │      insert.js
│          │      submit.js
│          └─WEB-INF				// 项目
│              │  web.xml
│              ├─classes			// 后端的class文件
│              │  ├─bean
│              │  ├─dao
│              │  ├─service
│              │  └─servlet
│              └─lib				// 依赖
├─src								// 源文件 .java对应的文件 带*为需要写的部分 不带*为exp1的代码
│  ├─bean							// 基本单元
│  │      Database.java
│  │      Person.java
│  │      User.java
│  ├─dao							// dao层
│  │      PersonOpt.java
│  │      UserOpt.java
│  ├─service						// 封装的接口
│  │      PersonService.java
│  │      UserService.java
│  └─servlet						// servlet
│          AddPersonServlet.java	// 处理person表单的提交
│          AddUserServlet.java		// 处理user表单的提交
│          DeleteUserServlet.java	// 处理删除操作
│          QueryServlet.java		// 处理查看数据库数据的操作
└─web								// JSP前端页面
    │  index.jsp					// 主页面
    │  result.jsp					// 查看进行的操作
    │  show_data.jsp				// 查看数据库数据
    ├─js							// js脚本 前端 需要写
    │      insert.js				// 表单校验
    │      submit.js				// 点击submit提交交互部分
    └─WEB-INF
        │  web.xml					// 在用servlet的时候你可能会去配置他，不过我没有这样写（见后文）
        ├─classes					// .java文件编译生成的.class
        │  ├─bean
        │  │      Database.class
        │  │      Person.class
        │  │      User.class
        │  ├─dao
        │  │      PersonOpt.class
        │  │      UserOpt.class
        │  ├─service
        │  │      PersonService.class
        │  │      UserService.class
        │  └─servlet
        │          AddPersonServlet.class
        │          AddUserServlet.class
        │          DeleteUserServlet.class
        │          QueryServlet.class
        └─lib						// jar包依赖（有能力建议使用maven）
```

### 关于我的思路

等我有空了再写(..)
