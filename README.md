projectcodeinfo
===============

eclipse的统计工程代码量插件
今天突然想统计我的工程里面有多少代码量，于是写了这个小插件。可以参考这篇博客。http://zhwj184.iteye.com/admin/blogs/687296
 
点击工程右键出现projectCodeInfo菜单，这里只统计工程级别，即只有在点击工程的目录才会出现这个菜单，点击工程里面某个包没出现这个菜单，可以自己再做扩展。
 
出现结果，统计该工程下所有java类文件的代码信息，包括类数据，代码行数，空格行数，注释行数等。
 
下面压缩文件里面的projectInfo_1.0.0.jar放到eclipse的plugin目录下重新eclipse即可使用。eclipse版本是3.4.4。高版本没有测试过，有兴趣的可以自己改改。
