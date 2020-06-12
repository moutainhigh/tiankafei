(window.webpackJsonp=window.webpackJsonp||[]).push([[16],{360:function(s,a,t){"use strict";t.r(a);var e=t(42),n=Object(e.a)({},(function(){var s=this,a=s.$createElement,t=s._self._c||a;return t("ContentSlotsDistributor",{attrs:{"slot-key":s.$parent.slotKey}},[t("h1",{attrs:{id:"nacos"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#nacos"}},[s._v("#")]),s._v(" nacos")]),s._v(" "),t("h2",{attrs:{id:"安装"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#安装"}},[s._v("#")]),s._v(" 安装")]),s._v(" "),t("h3",{attrs:{id:"下载"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#下载"}},[s._v("#")]),s._v(" 下载")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[s._v("wget")]),s._v(" https://github.com/alibaba/nacos/releases/download/1.2.0/nacos-server-1.2.0.tar.gz\n")])])]),t("h3",{attrs:{id:"解压"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#解压"}},[s._v("#")]),s._v(" 解压")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[s._v("tar")]),s._v(" -zvxf nacos-server-1.2.0.tar.gz\n")])])]),t("h3",{attrs:{id:"创建软件目录"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#创建软件目录"}},[s._v("#")]),s._v(" 创建软件目录")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[s._v("mkdir")]),s._v(" -p /opt/software\n")])])]),t("h3",{attrs:{id:"移动软件到制定目录"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#移动软件到制定目录"}},[s._v("#")]),s._v(" 移动软件到制定目录")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[s._v("mv")]),s._v(" nacos /opt/software/\n")])])]),t("h3",{attrs:{id:"进入目录"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#进入目录"}},[s._v("#")]),s._v(" 进入目录")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token builtin class-name"}},[s._v("cd")]),s._v(" /opt/software/nacos/bin\n\n"),t("span",{pre:!0,attrs:{class:"token builtin class-name"}},[s._v("pwd")]),s._v("\n/opt/software/nacos/bin\n")])])]),t("h3",{attrs:{id:"注册服务脚本"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#注册服务脚本"}},[s._v("#")]),s._v(" 注册服务脚本")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[s._v("vi")]),s._v(" /lib/systemd/system/nacos.service\n")])])]),t("h3",{attrs:{id:"脚本内容"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#脚本内容"}},[s._v("#")]),s._v(" 脚本内容")]),s._v(" "),t("div",{staticClass:"language-sh extra-class"},[t("pre",{pre:!0,attrs:{class:"language-sh"}},[t("code",[t("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("[")]),s._v("Unit"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("]")]),s._v("\n"),t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[s._v("Description")]),t("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),s._v("nacos\n"),t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[s._v("After")]),t("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),s._v("network.target\n\n"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("[")]),s._v("Service"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("]")]),s._v("\n"),t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[s._v("Type")]),t("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),s._v("forking\n"),t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[s._v("ExecStart")]),t("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),s._v("/opt/software/nacos/bin/startup.sh -m standalone\n"),t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[s._v("ExecReload")]),t("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),s._v("/opt/software/nacos/bin/shutdown.sh\n"),t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[s._v("ExecStop")]),t("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),s._v("/opt/software/nacos/bin/shutdown.sh\n"),t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[s._v("PrivateTmp")]),t("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),s._v("true\n\n"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("[")]),s._v("Install"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("]")]),s._v("\n"),t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[s._v("WantedBy")]),t("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),s._v("multi-user.target\n")])])]),t("h3",{attrs:{id:"设置开机启动并启动nacos"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#设置开机启动并启动nacos"}},[s._v("#")]),s._v(" 设置开机启动并启动nacos")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 重新加载service的配置")]),s._v("\nsystemctl daemon-reload\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 设置开机启动")]),s._v("\nsystemctl "),t("span",{pre:!0,attrs:{class:"token builtin class-name"}},[s._v("enable")]),s._v(" nacos.service\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 启动nacos")]),s._v("\nsystemctl start nacos.service\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 重启nacos")]),s._v("\nsystemctl restart nacos.service\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 停止nacos")]),s._v("\nsystemctl stop nacos.service\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 查看nacos状态")]),s._v("\nsystemctl status nacos.service\n\n")])])])])}),[],!1,null,null,null);a.default=n.exports}}]);