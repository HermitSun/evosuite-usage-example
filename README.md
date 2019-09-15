### evosuite的安装与使用

目前阶段因为evosuite的官网不可用，只能用jar包凑合一下了。反正jar包也是完整可运行的不是吗（笑）。

先到evosuite的github仓库里下载jar包：https://github.com/EvoSuite/evosuite/releases

然后进入`项目根目录/target/classes/`（可以顺便把evosuite的jar包复制过来），执行以下命令：

```shell
java -jar evosuite-1.0.6.jar -projectCP ./ -class net.mooctest.Triangle 
```

`-projectCP`是为了指定生成的路径。这一步用可以用`-help`来显示可用的选项，中间还可以加上`-criterion`指定生成的标准，比如line、branch、cbranch、mutation 、exception，等等。

然后会生成两个文件夹：`evosuite-report/`和`evosuite-tests/`。因为maven插件不可用，所以就手动把evosuite-tests里的测试文件（可能会有嵌套的目录，我这里是`Triangle_ESTest.java`和`Triangle_ESTest_scaffolding.java`）复制到对应maven项目的`test/`目录下运行JUnit测试。

但是生成的测试文件其实是不能运行的，因为缺少evosuite的运行环境，需要在pom.xml里增加依赖配置：

```xml
<dependency>
    <groupId>org.evosuite.plugins</groupId>
    <artifactId>evosuite-maven-plugin</artifactId>
    <version>1.0.6</version>
</dependency>
```

这一步可能时间会比较长（具体原因就不说了，大家都知道）。配置完成之后再次运行就可以了。

这一步可能会报错`Missing artifact com.sun:tools:jar:1.0.0`。解决方法也很简单，引入JDK里的tools.jar就好了：

```xml
<dependency>
    <groupId>com.sun</groupId>
    <artifactId>tools</artifactId>
    <version>1.8.0_191</version>
    <scope>system</scope>
    <systemPath>D:\Java\jdk1.8.0_191\lib\tools.jar</systemPath>
</dependency>
```

另外，据说加上这一段以后可以用`mvn evosuite:help`来下载和运行插件：

```xml
<pluginRepositories>
    <pluginRepository>
        <id>EvoSuite</id>
        <name>EvoSuite Repository</name>
        <url>http://www.evosuite.org/m2</url>
    </pluginRepository>
</pluginRepositories>
```

但是因为evosuite的官网炸了（502，不知道他们干了什么），这个方法的真实性无法验证。而且，就算这个方法可用，只要evosuite的官网是正常的，我为什么不用eclipse自带的插件呢？

#### 参考资料

1. [EVOSUITE使用方法入门](https://www.cnblogs.com/sqchao/p/9954091.html)
2. [evosuite 对单个 .class 生成测试用例](https://www.jianshu.com/p/0789b3a9eb7a)
3. [maven项目内配置evosuite插件来自动生成test suite](https://blog.csdn.net/weixin_36864894/article/details/80713000)
4. [Missing artifact com.sun:tools:jar:1.x.x解决办法](https://blog.csdn.net/yang5726685/article/details/58586977)