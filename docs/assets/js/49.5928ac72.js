(window.webpackJsonp=window.webpackJsonp||[]).push([[49],{397:function(a,s,t){"use strict";t.r(s);var n=t(42),e=Object(n.a)({},(function(){var a=this,s=a.$createElement,t=a._self._c||s;return t("ContentSlotsDistributor",{attrs:{"slot-key":a.$parent.slotKey}},[t("h1",{attrs:{id:"centos7安装配置hbase"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#centos7安装配置hbase"}},[a._v("#")]),a._v(" centos7安装配置HBase")]),a._v(" "),t("blockquote",[t("p",[a._v("面向列的数据存储（十亿级别的行，百万级别的列）")])]),a._v(" "),t("h2",{attrs:{id:"下载hbase"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#下载hbase"}},[a._v("#")]),a._v(" 下载hbase")]),a._v(" "),t("p",[a._v("选择对应的版本进行下载，这里选择的是2.2.4")]),a._v(" "),t("div",{staticClass:"language-http extra-class"},[t("pre",{pre:!0,attrs:{class:"language-http"}},[t("code",[a._v("# hbase所有版本列表\n"),t("span",{pre:!0,attrs:{class:"token header-name keyword"}},[a._v("https:")]),a._v("//hbase.apache.org/downloads.html\n# tgz 解压包下载路径\n"),t("span",{pre:!0,attrs:{class:"token header-name keyword"}},[a._v("https:")]),a._v("//mirrors.tuna.tsinghua.edu.cn/apache/hbase/2.2.4/hbase-2.2.4-bin.tar.gz\n")])])]),t("h2",{attrs:{id:"单机部署"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#单机部署"}},[a._v("#")]),a._v(" 单机部署")]),a._v(" "),t("h3",{attrs:{id:"解压到指定路径"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#解压到指定路径"}},[a._v("#")]),a._v(" 解压到指定路径")]),a._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[a._v("mkdir")]),a._v(" -p /opt/bigdata/hbase-2.2.4-local\n"),t("span",{pre:!0,attrs:{class:"token function"}},[a._v("tar")]),a._v(" -xzvf /root/hbase-2.2.4-bin.tar.gz -C /opt/bigdata/hbase-2.2.4-local\n"),t("span",{pre:!0,attrs:{class:"token builtin class-name"}},[a._v("cd")]),a._v(" /opt/bigdata/hbase-2.2.4-local/hbase-2.2.4\n")])])]),t("h3",{attrs:{id:"设置环境变量"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#设置环境变量"}},[a._v("#")]),a._v(" 设置环境变量")]),a._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[a._v("vi")]),a._v(" /etc/profile\n")])])]),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token builtin class-name"}},[a._v("export")]),a._v(" "),t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[a._v("HBASE_HOME")]),t("span",{pre:!0,attrs:{class:"token operator"}},[a._v("=")]),a._v("/opt/bigdata/hbase-2.2.4-local/hbase-2.2.4\n"),t("span",{pre:!0,attrs:{class:"token builtin class-name"}},[a._v("export")]),a._v(" "),t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[t("span",{pre:!0,attrs:{class:"token environment constant"}},[a._v("PATH")])]),t("span",{pre:!0,attrs:{class:"token operator"}},[a._v("=")]),t("span",{pre:!0,attrs:{class:"token environment constant"}},[a._v("$PATH")]),t("span",{pre:!0,attrs:{class:"token builtin class-name"}},[a._v(":")]),t("span",{pre:!0,attrs:{class:"token variable"}},[a._v("$HBASE_HOME")]),a._v("/bin\n")])])]),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token builtin class-name"}},[a._v("source")]),a._v(" /etc/profile\n")])])]),t("h3",{attrs:{id:"执行配置"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#执行配置"}},[a._v("#")]),a._v(" 执行配置")]),a._v(" "),t("p",[a._v("cd $HBASE_HOME/conf")]),a._v(" "),t("blockquote",[t("p",[a._v("vi hbase-env.sh")]),a._v(" "),t("div",{staticClass:"language-sh extra-class"},[t("pre",{pre:!0,attrs:{class:"language-sh"}},[t("code",[t("span",{pre:!0,attrs:{class:"token comment"}},[a._v("# 设置JAVA的环境变量")]),a._v("\n"),t("span",{pre:!0,attrs:{class:"token builtin class-name"}},[a._v("export")]),a._v(" "),t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[a._v("JAVA_HOME")]),t("span",{pre:!0,attrs:{class:"token operator"}},[a._v("=")]),a._v("/usr/java/default\n")])])]),t("p",[a._v("vi hbase-site.xml")]),a._v(" "),t("div",{staticClass:"language-xml extra-class"},[t("pre",{pre:!0,attrs:{class:"language-xml"}},[t("code",[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("property")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n "),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("name")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("hbase.rootdir"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("name")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n "),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("value")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("file:///var/bigdata/hbase/local/data"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("value")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("property")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("property")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n "),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("name")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("hbase.zookeeper.property.dataDir"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("name")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n "),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("value")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("/var/bigdata/hbase/local/zookeeper"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("value")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("property")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("property")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n "),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("name")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("hbase.unsafe.stream.capability.enforce"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("name")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n "),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("value")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("false"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("value")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n "),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("description")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n   Controls whether HBase will check for stream capabilities (hflush/hsync).\n\n   Disable this if you intend to run on LocalFileSystem, denoted by a rootdir\n   with the 'file://' scheme, but be mindful of the NOTE below.\n\n   WARNING: Setting this to false blinds you to potential data loss and\n   inconsistent system state in the event of process and/or node failures. If\n   HBase is complaining of an inability to use hsync or hflush it's most\n   likely not a false positive.\n "),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("description")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("property")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n")])])])]),a._v(" "),t("h3",{attrs:{id:"运行"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#运行"}},[a._v("#")]),a._v(" 运行")]),a._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token comment"}},[a._v("# 启动hbase服务")]),a._v("\n./start-hbase.sh\n\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[a._v("# 进入hbase命令行")]),a._v("\nhbase shell\n")])])]),t("h2",{attrs:{id:"完全分布式"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#完全分布式"}},[a._v("#")]),a._v(" 完全分布式")]),a._v(" "),t("h3",{attrs:{id:"解压到指定目录"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#解压到指定目录"}},[a._v("#")]),a._v(" 解压到指定目录")]),a._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[a._v("mkdir")]),a._v(" -p /opt/bigdata/hbase-2.2.4-full\n"),t("span",{pre:!0,attrs:{class:"token function"}},[a._v("tar")]),a._v(" -xzvf /root/hbase-2.2.4-bin.tar.gz -C /opt/bigdata/hbase-2.2.4-full\n"),t("span",{pre:!0,attrs:{class:"token builtin class-name"}},[a._v("cd")]),a._v(" /opt/bigdata/hbase-2.2.4-full/hbase-2.2.4\n")])])]),t("h3",{attrs:{id:"设置环境变量-2"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#设置环境变量-2"}},[a._v("#")]),a._v(" 设置环境变量")]),a._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[a._v("vi")]),a._v(" /etc/profile\n")])])]),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token builtin class-name"}},[a._v("export")]),a._v(" "),t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[a._v("HBASE_HOME")]),t("span",{pre:!0,attrs:{class:"token operator"}},[a._v("=")]),a._v("/opt/bigdata/hbase-2.2.4-full/hbase-2.2.4\n"),t("span",{pre:!0,attrs:{class:"token builtin class-name"}},[a._v("export")]),a._v(" "),t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[t("span",{pre:!0,attrs:{class:"token environment constant"}},[a._v("PATH")])]),t("span",{pre:!0,attrs:{class:"token operator"}},[a._v("=")]),t("span",{pre:!0,attrs:{class:"token environment constant"}},[a._v("$PATH")]),t("span",{pre:!0,attrs:{class:"token builtin class-name"}},[a._v(":")]),t("span",{pre:!0,attrs:{class:"token variable"}},[a._v("$HBASE_HOME")]),a._v("/bin\n")])])]),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token builtin class-name"}},[a._v("source")]),a._v(" /etc/profile\n")])])]),t("h3",{attrs:{id:"执行配置-2"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#执行配置-2"}},[a._v("#")]),a._v(" 执行配置")]),a._v(" "),t("p",[a._v("cd $HBASE_HOME/conf")]),a._v(" "),t("blockquote",[t("p",[a._v("vim hbase-env.sh")]),a._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token comment"}},[a._v("# 设置JAVA的环境变量")]),a._v("\n"),t("span",{pre:!0,attrs:{class:"token builtin class-name"}},[a._v("export")]),a._v(" "),t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[a._v("JAVA_HOME")]),t("span",{pre:!0,attrs:{class:"token operator"}},[a._v("=")]),a._v("/usr/java/default\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[a._v("# 设置是否使用自己的zookeeper实例")]),a._v("\n"),t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[a._v("HBASE_MANAGES_ZK")]),t("span",{pre:!0,attrs:{class:"token operator"}},[a._v("=")]),a._v("false\n")])])]),t("p",[a._v("vim hbase-site.xml")]),a._v(" "),t("div",{staticClass:"language-xml extra-class"},[t("pre",{pre:!0,attrs:{class:"language-xml"}},[t("code",[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("property")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n "),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("name")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("hbase.rootdir"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("name")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n "),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("value")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("hdfs://mycluster/hbase"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("value")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("property")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("property")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n "),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("name")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("hbase.cluster.distributed"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("name")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n "),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("value")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("true"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("value")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("property")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("property")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n "),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("name")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("hbase.zookeeper.quorum"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("name")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n "),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("value")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("bigdata01,bigdata02,bigdata03,bigdata04"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("value")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("property")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[a._v("\x3c!-- 以下配置可以在测试时使用，让hbase把内存中的数据flush到磁盘 --\x3e")]),a._v("\n"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("property")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n "),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("name")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("hbase.server.thread.wakefrequency"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("name")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n "),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("value")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("1"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("value")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("property")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("property")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n "),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("name")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("hbase.server.compactchecker.interval.multiplier"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("name")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n "),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("<")]),a._v("value")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("1"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("value")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n"),t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token tag"}},[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("</")]),a._v("property")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(">")])]),a._v("\n")])])]),t("p",[a._v("vim regionservers")]),a._v(" "),t("div",{staticClass:"language- extra-class"},[t("pre",{pre:!0,attrs:{class:"language-text"}},[t("code",[a._v("bigdata02\nbigdata03\nbigdata04\n")])])]),t("p",[a._v("如果需要配置Master的高可用，需要在conf目录下创建backup-masters文件，并添加如下内容：")]),a._v(" "),t("div",{staticClass:"language- extra-class"},[t("pre",{pre:!0,attrs:{class:"language-text"}},[t("code",[a._v("vim backup-masters\nbigdata02\n")])])]),t("p",[a._v("拷贝hadoop配置文件到当前hbase的配置目录")]),a._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[a._v("scp")]),a._v(" "),t("span",{pre:!0,attrs:{class:"token variable"}},[a._v("$HADOOP_HOME")]),a._v("/etc/hadoop/hdfs-site.xml "),t("span",{pre:!0,attrs:{class:"token variable"}},[a._v("$HBASE_HOME")]),a._v("/conf/\n")])])])]),a._v(" "),t("h3",{attrs:{id:"执行分发"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#执行分发"}},[a._v("#")]),a._v(" 执行分发")]),a._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[a._v("scp")]),a._v(" -r hbase-env.sh hbase-site.xml regionservers backup-masters hdfs-site.xml bigdata02:"),t("span",{pre:!0,attrs:{class:"token variable"}},[t("span",{pre:!0,attrs:{class:"token variable"}},[a._v("`")]),t("span",{pre:!0,attrs:{class:"token builtin class-name"}},[a._v("pwd")]),t("span",{pre:!0,attrs:{class:"token variable"}},[a._v("`")])]),a._v("\n"),t("span",{pre:!0,attrs:{class:"token function"}},[a._v("scp")]),a._v(" -r hbase-env.sh hbase-site.xml regionservers backup-masters hdfs-site.xml bigdata03:"),t("span",{pre:!0,attrs:{class:"token variable"}},[t("span",{pre:!0,attrs:{class:"token variable"}},[a._v("`")]),t("span",{pre:!0,attrs:{class:"token builtin class-name"}},[a._v("pwd")]),t("span",{pre:!0,attrs:{class:"token variable"}},[a._v("`")])]),a._v("\n"),t("span",{pre:!0,attrs:{class:"token function"}},[a._v("scp")]),a._v(" -r hbase-env.sh hbase-site.xml regionservers backup-masters hdfs-site.xml bigdata04:"),t("span",{pre:!0,attrs:{class:"token variable"}},[t("span",{pre:!0,attrs:{class:"token variable"}},[a._v("`")]),t("span",{pre:!0,attrs:{class:"token builtin class-name"}},[a._v("pwd")]),t("span",{pre:!0,attrs:{class:"token variable"}},[a._v("`")])]),a._v("\n\n")])])]),t("h3",{attrs:{id:"运行-2"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#运行-2"}},[a._v("#")]),a._v(" 运行")]),a._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token comment"}},[a._v("# 启动hbase服务")]),a._v("\n./start-hbase.sh\n\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[a._v("# 进入hbase命令行")]),a._v("\nhbase shell\n")])])])])}),[],!1,null,null,null);s.default=e.exports}}]);