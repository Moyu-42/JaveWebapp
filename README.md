# 编程新技术实务 实验2

Java webapp
我具体做了什么大概都在git的message中了

upd 11/25：
由于验收完了拿了A+，所以有空就更一下我是怎么做实验的

(主要给明年的我看看现在的我是有多废物

最终我验收的demo可在 49.234.84.130:8080/Exp2 查看并使用。

已知但未修复的bug：在删除数据时，如果删除成功了，不进行别的操作，再次点击提交按钮，依旧会提示删除成功。（可能的解决方案：BootstrapValidator在点击提交按钮后，使其清除表单）

### 功利一点

加分项：提交数据异步提交不刷新页面（ajax技术），使用框架，使用连接池，项目部署到远程服务器（Ubuntu），进行操作时有确认提示

扣分项：当插入person表年龄为空时，要注意查看时，年龄一栏不能出现任何数据（如0，null），留空；person表Name要可以为中文；person表要以Name为主键，详见下“数据提交——可能遇到的问题2”

验收流程：

1. 先向person表插入数据，username随便输入，Name为中文
2. 查看数据库
3. 将上述插入的数据只填入年龄，再次插入并查看数据库
4. 删除插入的数据
5. 再次删除
6. 看老师心情看不看代码
7. 看上述使用的加分项有没有使用。我当时只看了云服务器，让我ssh上去看一下tomcat的目录放在了什么位置（确保确实是我的服务器）

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
│  └─artifacts                      // 这部分为编译生成的文件，可以直接部署
│      ├─Exp2                       // 打好的war包，建议不用
│      └─Exp2_war_exploded          // 最后部署的项目
│          │  index.jsp
│          │  result.jsp
│          │  show_data.jsp
│          ├─js                     // js脚本
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

正如这个标题所说，这部分只会说我是怎么做实验的以及我用到了什么技术，并不会详细的对代码进行解释，因为我也是抄的别人写好的代码，拿过来修改一下让他符合我的要求而已。所以，具体如何使用还需要去查相关的资料。（不过都挺好找的，而且对于我遇到的一些坑我也会尽量说明）

如果实在是有不懂且不清楚的，可以email联系。

#### 从哪里开始

显然，是要从主页面`index.jsp`开始。

这一部分的话没有什么难度，我是直接使用框架（BootStrap）来进行页面的构建，当然，也有许多其他的框架可以使用。值得注意的也很显然的就是，输入的数据和提交按钮要放在同一个表单（Form）中，如对于person表的操作，将4个输入部分和一个提交按钮放到一个表单中，为了在后面数据校验和提交时使用。

> 页面创建部分当然是可以插入图片的，但是在我做时，考虑到插入图片后的效果还不如不插入图片，且对于验收成绩毫无影响（太难看了甚至会被扣分），所以我就没有做。

#### 页面完成后的数据校验`insert.js`

当我完成了`index.jsp`后，便开始写js脚本对数据进行校验，判断是否符合要求。最开始（`详见git版本：11.9-Version 3 857d56bc48cd852dc0657d275d70551d8a1e7cfd`)，我使用的是`BootstrapValidator`，效果看起来还不错，可以在输入时校验，而且可以提供相应的校验成功或失败的图标。但是这存在一个问题，当某些选项可以留空时，还是需要进行输入才能出发submit按钮，不能直接跳过不进行操作。这点我直到完成了实验也没有解决，感兴趣的话可以在这方面浪费点时间，看看有没有比较好的解决方法。

直到后来，改用了jQuery，解决了上面的问题，也就是现在最新的版本。

上述两种方法在我看来都是比较简单的，网上也有许多相关的资料，不过关于BootstrapValidator已经是上个世纪的东西了（指5年前），而且现在也不再进行维护，所以更推荐使用jQuery，当然，也可以使用其他的验证方法。

做完基础的数据校验之后又有了新的问题，当主键对应的值已经存在之后，如何进行校验。这里的解决方法就是使用remote + ajax向后台发送数据，根据返回值判断是否存在，从而触发相应的校验规则。对于BootstrapValidator和jQuery都提供了这一个方法，在`insert.js`中`del check()`的方法里，校验username时使用的是BootstrapValidator的方法；而jQuery则没有完成，因为配置方面出现了些问题，而且我也懒得去找解决办法了，所以就没有去使用。感兴趣的话可以尝试去完成。

#### 数据提交`submit.js`

当数据校验完成之后，那么就可以进行提交了。这里我是将对应的按钮与.on方法进行绑定，在点击时触发一个函数，从而完成提交。

> 对于表单中的标签，如input, submit之类，都存在一个id属性，这个属性就是在这里用来识别的。更详细的可以搜索：如何根据id获得值。我这里是用：$("#你用的id")进行

这里就遇到了第一个加分项：不刷新页面进行提交——使用ajax进行异步提交。

在这个js脚本中可以看到，我是使用了两个ajax方法，第一个方法用来查看主键是否存在，存在则发出提示信息：是否要进行修改，否则就直接询问是否要插入，第二个则是进行表单的提交。由于ajax是异步提交，如果单独的写两个ajax是无法确定哪一个先执行的，而这里，我想要先进行判断是否存在再提交，所以使用了jQuery.when().done()。当然，在查找资料时，看到有人说可以将ajax设置为同步，但是我没有成功，就算了。关于确认部分，可以使用confirm，会弹出提示框，根据用户的选择返回true和false，这里我直接使用了Boolean类型接受返回值，再简单的使用if-else逻辑来判断是否要提交等条件，详见`submit.js`。

##### 可能遇到的问题1：

到了提交的阶段，就是把数据从前端传到了后端的servlet，在这里我遇到的主要的问题，就是如何配置servlet。我所找到的有两种方法：1.在`/web/WEB-INF/web.xml`里进行配置；2.直接在`/src/*Servlet.java`里class声明之前使用@WebServlet(name = "`ServletName`", urlPatterns = {"`/ServletPath`"})，比如`name = "addPersonServlet", urlPatterns = {"/addPersonServlet"}`。我使用的是方法2，以上配置之后，就可以在ajax的url中写入相应的`ServletPath`，比如某个ajax想发送到addPersonServlet，使用`url: addPersonServlet`即可完成。

这部分要是存在问题的话，可以在后端的servlet中打上断点，在前端页面进行调试，看是不是成功跳转了，跳转成功进入后端的断点，则表示url配置没问题，否则会在前端调试窗口反馈出相关的错误信息，这时根据相关的信息再查找资料进行修改即可。

##### 可能遇到的问题2：

这一个或许你在做实验1时已经发现了，在person表中的主键是Name，但是实验文档中的描述则根据Username进行修改。这就存在一个问题：当Name存在Username不存在时，修改是不可发生的，但是如果忽略这一点，成功的插入，person表不会改变，而如果username在user表中也不存在，则会插入到user表中——这个逻辑显然是不对的。

所以，当Name存在Username不存在时，校验不通过，不可插入；当Name存在且Username存在时，可以插入，此时对相应的数据进行修改；Name不存在且Username存在时，好吧，不会发生这种情况；两者都不存在就可以插入了。

我的解决方案就是，在插入person表时，通过ajax发送数据查看是否符合要求，不符合则发出alert，弹出提示框：不可插入，否则就如上述所说，提出修改或者是否插入。这一个校验部分建议在`insert.js`中通过在校验规则中使用remote + ajax完成，当然，这样实现问题也不大，不过最好是使用remote + ajax。

数据插入部分就这样可以完成了，值得注意的是，每次插入，修改，删除都要有相应的提示框。

upd 12.1：先到这里，累了，下次有空再更。
