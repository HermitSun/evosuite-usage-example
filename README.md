## evosuite的安装与使用

目前因为evosuite的官网不可用，只能用jar包凑合一下了。反正jar包也是完整可运行的不是吗（笑）。

先到evosuite的github仓库里下载jar包：https://github.com/EvoSuite/evosuite/releases

先编译或者运行一下项目（如果不编译，是不会有`/target/classes`的），然后进入`项目根目录/target/classes/`。

这一步请在**操作系统的文件资源管理器**里进行，比如我用的是Windows系统，路径是`C:\mooctest\projects\3419\47795\Triangle`；如果直接在IDE里打开，可能会看不到上述的目录。此外，可以顺便把evosuite的jar包复制过来，就不用绝对路径了。

然后，执行以下命令：

```shell
java -jar evosuite-1.0.6.jar -projectCP ./ -class net.mooctest.Triangle 
```

`-projectCP`是为了指定生成的路径。这一步用可以用`-help`来显示可用的选项，中间还可以加上`-criterion`指定生成的标准，比如line、branch、cbranch、mutation 、exception，等等。

如果需要生成某个包里所有文件的测试用例，可以使用`-prefix`命令。比如：

```
java -jar evosuite-1.0.6.jar -projectCP ./ -prefix net.mooctest
```

这一步在运行evosuite的时候，可能会出现类似于这样的错误：

```shell
ERROR EvoSuite - Fatal crash on main EvoSuite process. Class using seed 1428172877144. Configuration id : null
```

这个有可能是JDK版本导致的报错，因为evosuite的稳定版本**只支持JDK1.8**。据说开发版本已经支持Java 9了，但是毕竟不太稳定，使用的时候还是要慎重，用稳定版的会比较保险。

如果一切顺利，这里会生成两个文件夹：`evosuite-report/`和`evosuite-tests/`。因为maven插件不可用，所以就手动把evosuite-tests里的测试文件（可能会有嵌套的目录，我这里是`Triangle_ESTest.java`和`Triangle_ESTest_scaffolding.java`）复制到对应maven项目的`test/`目录下运行JUnit测试。

也许你会发现生成的测试用例覆盖率很低，而且低得有点奇怪。我们可以打开生成的测试文件看一下：

```java
@Test(timeout = 4000)
public void test0()  throws Throwable  {
    Triangle triangle0 = null;
    try {
        triangle0 = new Triangle((-1794L), (-1794L), (-1794L));
        fail("Expecting exception: NoClassDefFoundError");
    } catch(NoClassDefFoundError e) {
        //
        // com/sun/tdk/jcov/runtime/Collect
        //
        verifyException("net.mooctest.Triangle", e);
    }
}
```

可以发现只有这一个用例，而且报的是这个`NoClassDefFoundError:com/sun/tdk/jcov/runtime/Collect `。相信用过mooctest插件的人都见过这个报错，解决方法也很简单，就是maven-update。如果用的是eclipse，右击当前项目，选择
Maven – Update Project…（我相信看到这篇文章的人用的都是eclipse，笑），然后再次运行上面的命令，就可以看到完整的测试用例了。

但是生成的测试文件其实是直接不能运行的，因为缺少evosuite的运行环境，根本过不了编译。

如果只是为了补充测试用例，可以直接把生成的测试用例（主要是`Triangle_ESTest.java`）复制到原有的测试文件里，因为mooctest似乎只能识别原有的测试文件。

> 这一点不确定，可能是我的打开方式不对。

需要强调的是，在复制生成的用例的时候，需要将evosuite的辅助方法换成JUnit的异常捕获机制。以前文为例，以下的代码是无法运行的，因为缺少verifyException方法：

```java
@Test(timeout = 4000)
public void test0()  throws Throwable  {
    Triangle triangle0 = null;
    try {
        triangle0 = new Triangle((-1794L), (-1794L), (-1794L));
        fail("Expecting exception: NoClassDefFoundError");
    } catch(NoClassDefFoundError e) {
        //
        // com/sun/tdk/jcov/runtime/Collect
        //
        verifyException("net.mooctest.Triangle", e);
    }
}
```

所以，我们应该把代码改成这样：

```java
@Test(timeout = 4000, expect = NoClassDefFoundError.class)
public void test0()  throws Throwable  {
    Triangle triangle0 = null;
    triangle0 = new Triangle((-1794L), (-1794L), (-1794L));
}
```

这样就能运行了。当然，这只是一个例子，实际生成的代码可能不是这样，JUnit捕获异常的方式也不止这一种，可以按照自己习惯的方式去写。

如果需要本地运行，就需要进一步在`pom.xml`里增加依赖配置：

```xml
<dependency>
    <groupId>org.evosuite.plugins</groupId>
    <artifactId>evosuite-maven-plugin</artifactId>
    <version>1.0.6</version>
</dependency>
```

这一步可能时间会比较长（具体原因就不说了，大家都知道）。配置完成之后再次运行就可以了。

这一步可能会报错`Missing artifact com.sun:tools:jar:1.0.0`。解决方法也很简单，引入JDK里的tools.jar就好了（记得把这里的版本改成**你自己的**JDK版本，我的版本是1.8.0_191)：

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