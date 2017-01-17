# DynamicLoadJar
Android dynamic loading jar(安卓动态加载jar)
#screenshot
![](https://github.com/momo145/DynamicLoadJar/blob/master/screenshot/S70117-205425.jpg)
# Description(描述)
每当我们为数据的加密而烦恼的时候,或者我们可以使用动态加载jar的方式,把比较重要的数据或者逻辑独立成一个jar,然后在程序启动的时候,
去网络下载,在程序加载完成之后就可以把jar删除,这样可以很大程度地防止程序被反向.这个例子说的就是这个.

这个项目安卓程序只有接口,实现都是在动态加载的jar里面
注意:安卓程序的接口包名必须和动态加载的实现类是一致的.

这个项目我特意在jar上加上了Gson的支持,但我打包的时候并没有把Gson的依赖给一起打进去,但是我安卓项目里面是有Gson支持库的,
但是动态加载之后可以正常运行.nice

# Importance(重要)
普通生成的jar并不能直接被Android程序动态加载,因为Android的虚拟机和JDK的虚拟机有区别,并不能直接识别.
所以我们需要用到 sdk里面的dx 命令来把jar转换成dex文件.
![](https://github.com/momo145/DynamicLoadJar/blob/master/screenshot/2883EDBE-AED3-4BAD-9569-8ED565BE655F.png)
使用 dx --dex --output=target_dex.jar source.jar 命令进行转换
target.jar:你需要生成的dex文件
source.jar:你的原始jar文件

#Very Important(非常重要)
如果你根据以上步骤做好,这样在安卓5.0(Android5.0)可以运行,但是在安卓5.0以下的手机运行会报
java.lang.IllegalAccessError: Class ref in pre-verified class resolved to unexpected implementation

原因是你的安卓程序有了接口,如果再加载我们刚才转换的jar会报类重复的异常.安卓5.0以上是没有这个问题的.

所以导出jar时不能带接口文件,不能带接口文件,不能带接口文件.

