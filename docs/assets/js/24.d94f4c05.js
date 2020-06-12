(window.webpackJsonp=window.webpackJsonp||[]).push([[24],{371:function(s,a,t){"use strict";t.r(a);var e=t(42),n=Object(e.a)({},(function(){var s=this,a=s.$createElement,t=s._self._c||a;return t("ContentSlotsDistributor",{attrs:{"slot-key":s.$parent.slotKey}},[t("h1",{attrs:{id:"通用命令"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#通用命令"}},[s._v("#")]),s._v(" 通用命令")]),s._v(" "),t("h2",{attrs:{id:"安装-wget-工具"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#安装-wget-工具"}},[s._v("#")]),s._v(" 安装 wget 工具")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[s._v("yum "),t("span",{pre:!0,attrs:{class:"token function"}},[s._v("install")]),s._v(" "),t("span",{pre:!0,attrs:{class:"token function"}},[s._v("wget")]),s._v(" -y\n")])])]),t("h2",{attrs:{id:"安装-net-tools-工具"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#安装-net-tools-工具"}},[s._v("#")]),s._v(" 安装 net-tools 工具")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[s._v("yum "),t("span",{pre:!0,attrs:{class:"token function"}},[s._v("install")]),s._v(" net-tools -y\n")])])]),t("h2",{attrs:{id:"安装-vim-工具"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#安装-vim-工具"}},[s._v("#")]),s._v(" 安装 vim 工具")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[s._v("yum "),t("span",{pre:!0,attrs:{class:"token function"}},[s._v("install")]),s._v(" "),t("span",{pre:!0,attrs:{class:"token function"}},[s._v("vim")]),s._v(" -y\n")])])]),t("h2",{attrs:{id:"查看进程"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#查看进程"}},[s._v("#")]),s._v(" 查看进程")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[s._v("ps")]),s._v(" -ef "),t("span",{pre:!0,attrs:{class:"token operator"}},[s._v("|")]),s._v(" "),t("span",{pre:!0,attrs:{class:"token function"}},[s._v("grep")]),s._v(" 进程名/端口\n")])])]),t("h2",{attrs:{id:"关闭selinux"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#关闭selinux"}},[s._v("#")]),s._v(" 关闭selinux")]),s._v(" "),t("p",[s._v("vi /etc/selinux/config")]),s._v(" "),t("div",{staticClass:"language-sh extra-class"},[t("pre",{pre:!0,attrs:{class:"language-sh"}},[t("code",[t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[s._v("SELINUX")]),t("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),s._v("disabled\n")])])]),t("h2",{attrs:{id:"设置本地域名解析"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#设置本地域名解析"}},[s._v("#")]),s._v(" 设置本地域名解析")]),s._v(" "),t("p",[s._v("vi /etc/hosts")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# ip 别名")]),s._v("\n"),t("span",{pre:!0,attrs:{class:"token number"}},[s._v("192.168")]),s._v(".0.101 software\n"),t("span",{pre:!0,attrs:{class:"token number"}},[s._v("192.168")]),s._v(".0.102 appserver\n"),t("span",{pre:!0,attrs:{class:"token number"}},[s._v("192.168")]),s._v(".0.121 bigdata01\n"),t("span",{pre:!0,attrs:{class:"token number"}},[s._v("192.168")]),s._v(".0.122 bigdata02\n"),t("span",{pre:!0,attrs:{class:"token number"}},[s._v("192.168")]),s._v(".0.123 bigdata03\n"),t("span",{pre:!0,attrs:{class:"token number"}},[s._v("192.168")]),s._v(".0.124 bigdata04\n")])])]),t("h2",{attrs:{id:"设置免密登录"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#设置免密登录"}},[s._v("#")]),s._v(" 设置免密登录")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[s._v("ssh")]),s._v(" localhost\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 在本地机器用ssh-keygen 生成一个公私钥对")]),s._v("\nssh-keygen -t dsa -P "),t("span",{pre:!0,attrs:{class:"token string"}},[s._v("''")]),s._v(" -f ~/.ssh/id_dsa\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 把公钥加入authorized_keys中")]),s._v("\nssh-copy-id root@bigdata01\n")])])]),t("h2",{attrs:{id:"关机命令"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#关机命令"}},[s._v("#")]),s._v(" 关机命令")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[s._v("shutdown")]),s._v(" -h now\n")])])]),t("h2",{attrs:{id:"重启命令"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#重启命令"}},[s._v("#")]),s._v(" 重启命令")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[s._v("reboot")]),s._v("\n")])])]),t("h2",{attrs:{id:"解压命令"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#解压命令"}},[s._v("#")]),s._v(" 解压命令")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 解压tar文件到指定路径")]),s._v("\n"),t("span",{pre:!0,attrs:{class:"token function"}},[s._v("tar")]),s._v(" -zxvf 压缩包路径 -C 解压路径\n")])])]),t("h2",{attrs:{id:"更改linux连接最大数"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#更改linux连接最大数"}},[s._v("#")]),s._v(" 更改linux连接最大数")]),s._v(" "),t("p",[s._v("vi /etc/security/limits.conf")]),s._v(" "),t("div",{staticClass:"language-sh extra-class"},[t("pre",{pre:!0,attrs:{class:"language-sh"}},[t("code",[s._v("*       soft    nofile  "),t("span",{pre:!0,attrs:{class:"token number"}},[s._v("102400")]),s._v("\n*       hard    nofile  "),t("span",{pre:!0,attrs:{class:"token number"}},[s._v("102400")]),s._v("\n*       soft    nproc   "),t("span",{pre:!0,attrs:{class:"token number"}},[s._v("102400")]),s._v("\n*       hard    nproc   "),t("span",{pre:!0,attrs:{class:"token number"}},[s._v("102400")]),s._v("\n")])])]),t("p",[s._v("vi /etc/pam.d/login")]),s._v(" "),t("div",{staticClass:"language-sh extra-class"},[t("pre",{pre:!0,attrs:{class:"language-sh"}},[t("code",[s._v("session    required     pam_limits.so\n")])])]),t("h2",{attrs:{id:"关闭防火墙，开机不启动防火墙"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#关闭防火墙，开机不启动防火墙"}},[s._v("#")]),s._v(" 关闭防火墙，开机不启动防火墙")]),s._v(" "),t("h3",{attrs:{id:"centos6"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#centos6"}},[s._v("#")]),s._v(" CentOS6")]),s._v(" "),t("p",[s._v("查看状态")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[s._v("service")]),s._v(" iptables status\n")])])]),t("p",[s._v("停止服务")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[s._v("service")]),s._v(" iptables stop\n")])])]),t("p",[s._v("设置开机禁止启动")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[s._v("chkconfig")]),s._v(" iptables off\n")])])]),t("h3",{attrs:{id:"centos7"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#centos7"}},[s._v("#")]),s._v(" CentOS7")]),s._v(" "),t("p",[s._v("查看状态")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[s._v("systemctl status firewalld.service\n")])])]),t("p",[s._v("停止服务")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[s._v("systemctl stop firewalld.service\n")])])]),t("p",[s._v("设置开机禁止启动")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[s._v("systemctl disable firewalld.service\n")])])]),t("h2",{attrs:{id:"时间同步阿里云"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#时间同步阿里云"}},[s._v("#")]),s._v(" 时间同步阿里云")]),s._v(" "),t("h3",{attrs:{id:"ntp的安装及配置"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#ntp的安装及配置"}},[s._v("#")]),s._v(" ntp的安装及配置")]),s._v(" "),t("p",[s._v("安装时间同步工具ntp")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[s._v("yum "),t("span",{pre:!0,attrs:{class:"token function"}},[s._v("install")]),s._v(" ntp  -y\n")])])]),t("p",[s._v("设置阿里云时间同步地址")]),s._v(" "),t("p",[s._v("vi /etc/ntp.conf")]),s._v(" "),t("div",{staticClass:"language-sh extra-class"},[t("pre",{pre:!0,attrs:{class:"language-sh"}},[t("code",[s._v("server ntp1.aliyun.com\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("#注释掉其他的server")]),s._v("\n")])])]),t("h3",{attrs:{id:"centos6-2"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#centos6-2"}},[s._v("#")]),s._v(" CentOS6")]),s._v(" "),t("p",[s._v("查看状态")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[s._v("service")]),s._v(" ntpd status\n")])])]),t("p",[s._v("启动服务")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[s._v("service")]),s._v(" ntpd start\n")])])]),t("p",[s._v("停止服务")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[s._v("service")]),s._v(" ntpd stop\n")])])]),t("p",[s._v("设置开机启动")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[s._v("chkconfig")]),s._v(" ntpd on\n")])])]),t("h3",{attrs:{id:"centos7-2"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#centos7-2"}},[s._v("#")]),s._v(" CentOS7")]),s._v(" "),t("p",[s._v("查看状态")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[s._v("systemctl status ntpd.service\n")])])]),t("p",[s._v("启动服务")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[s._v("systemctl start ntpd.service\n")])])]),t("p",[s._v("停止服务")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[s._v("systemctl stop ntpd.service\n")])])]),t("p",[s._v("设置开机启动")]),s._v(" "),t("div",{staticClass:"language-sh extra-class"},[t("pre",{pre:!0,attrs:{class:"language-sh"}},[t("code",[s._v("systemctl "),t("span",{pre:!0,attrs:{class:"token builtin class-name"}},[s._v("enable")]),s._v(" ntpd.service\n\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# ntpd可能与chronyd冲突，所以需要先把chronyd禁止开机启动。如果发现ntpd服务没有开机启动，可以执行下面命令")]),s._v("\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# systemctl disable chronyd")]),s._v("\n")])])]),t("h2",{attrs:{id:"切换-centos-阿里云yum源"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#切换-centos-阿里云yum源"}},[s._v("#")]),s._v(" 切换 centos 阿里云yum源")]),s._v(" "),t("h3",{attrs:{id:"centos6-3"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#centos6-3"}},[s._v("#")]),s._v(" CentOS6")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 进入系统yum源的目录")]),s._v("\n"),t("span",{pre:!0,attrs:{class:"token builtin class-name"}},[s._v("cd")]),s._v(" /etc/yum.repos.d/\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 创建备份目录，方便出错后回退")]),s._v("\n"),t("span",{pre:!0,attrs:{class:"token function"}},[s._v("mkdir")]),s._v(" backup\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 拷贝备份")]),s._v("\n"),t("span",{pre:!0,attrs:{class:"token function"}},[s._v("cp")]),s._v(" CentOS-* ./backup/\n\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 下载阿里云centos6的yum源覆盖本地文件")]),s._v("\n"),t("span",{pre:!0,attrs:{class:"token function"}},[s._v("wget")]),s._v(" -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-6.repo\n\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 执行清理缓存")]),s._v("\nyum clean all\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 重新构造yum源的缓存")]),s._v("\nyum makecache\n\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 使用新yum源更新已安装的软件")]),s._v("\nyum update -y\n")])])]),t("h3",{attrs:{id:"centos7-3"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#centos7-3"}},[s._v("#")]),s._v(" CentOS7")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 进入系统yum源的目录")]),s._v("\n"),t("span",{pre:!0,attrs:{class:"token builtin class-name"}},[s._v("cd")]),s._v(" /etc/yum.repos.d/\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 创建备份目录，方便出错后回退")]),s._v("\n"),t("span",{pre:!0,attrs:{class:"token function"}},[s._v("mkdir")]),s._v(" backup\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 拷贝备份")]),s._v("\n"),t("span",{pre:!0,attrs:{class:"token function"}},[s._v("cp")]),s._v(" CentOS-* ./backup/\n\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 下载阿里云centos6的yum源覆盖本地文件")]),s._v("\n"),t("span",{pre:!0,attrs:{class:"token function"}},[s._v("wget")]),s._v(" -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo\n\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 添加EPEL源")]),s._v("\n"),t("span",{pre:!0,attrs:{class:"token function"}},[s._v("wget")]),s._v(" -P /etc/yum.repos.d/ http://mirrors.aliyun.com/repo/epel-7.repo\n\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 执行清理缓存")]),s._v("\nyum clean all\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 重新构造yum源的缓存")]),s._v("\nyum makecache\n\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 使用新yum源更新已安装的软件")]),s._v("\nyum update -y\n")])])]),t("h3",{attrs:{id:"问题恢复"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#问题恢复"}},[s._v("#")]),s._v(" 问题恢复")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token builtin class-name"}},[s._v("cd")]),s._v(" /etc/yum.repos.d/backup\n\n"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("\\")]),s._v("cp -rf CentOS-* /etc/yum.repos.d/\n\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 执行清理缓存")]),s._v("\nyum clean all\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 重新构造yum源的缓存")]),s._v("\nyum makecache\n\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 查看源列表")]),s._v("\nyum repolist\n\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 使用新yum源更新已安装的软件")]),s._v("\nyum update -y\n")])])]),t("h2",{attrs:{id:"设置主机名"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#设置主机名"}},[s._v("#")]),s._v(" 设置主机名")]),s._v(" "),t("h3",{attrs:{id:"centos6-4"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#centos6-4"}},[s._v("#")]),s._v(" CentOS6")]),s._v(" "),t("p",[s._v("vi /etc/sysconfig/network")]),s._v(" "),t("div",{staticClass:"language-sh extra-class"},[t("pre",{pre:!0,attrs:{class:"language-sh"}},[t("code",[t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[s._v("NETWORKING")]),t("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),s._v("yes\n"),t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[t("span",{pre:!0,attrs:{class:"token environment constant"}},[s._v("HOSTNAME")])]),t("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),s._v("主机名\n")])])]),t("h3",{attrs:{id:"centos7-4"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#centos7-4"}},[s._v("#")]),s._v(" CentOS7")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 设置主机名")]),s._v("\nhostnamectl set-hostname 主机名\n\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 查看主机名")]),s._v("\nhostnamectl\n")])])]),t("h2",{attrs:{id:"网络配置"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#网络配置"}},[s._v("#")]),s._v(" 网络配置")]),s._v(" "),t("h3",{attrs:{id:"centos7-5"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#centos7-5"}},[s._v("#")]),s._v(" CentOS7")]),s._v(" "),t("div",{staticClass:"language-sh extra-class"},[t("pre",{pre:!0,attrs:{class:"language-sh"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[s._v("vi")]),s._v(" /etc/sysconfig/network-scripts/ifcfg-ens33\n")])])]),t("div",{staticClass:"language-sh extra-class"},[t("pre",{pre:!0,attrs:{class:"language-sh"}},[t("code",[t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[s._v("BOOTPROTO")]),t("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),t("span",{pre:!0,attrs:{class:"token string"}},[s._v('"none"')]),s._v("\n"),t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[s._v("ONBOOT")]),t("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),t("span",{pre:!0,attrs:{class:"token string"}},[s._v('"yes"')]),s._v("\n"),t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[s._v("IPADDR")]),t("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),t("span",{pre:!0,attrs:{class:"token string"}},[s._v('"192.168.0.108"')]),s._v("\n"),t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[s._v("PREFIX")]),t("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),t("span",{pre:!0,attrs:{class:"token string"}},[s._v('"24"')]),s._v("\n"),t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[s._v("GATEWAY")]),t("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),t("span",{pre:!0,attrs:{class:"token string"}},[s._v('"192.168.0.1"')]),s._v("\n"),t("span",{pre:!0,attrs:{class:"token assign-left variable"}},[s._v("DNS1")]),t("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),t("span",{pre:!0,attrs:{class:"token string"}},[s._v('"192.168.0.1"')]),s._v("\n")])])]),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[s._v("service")]),s._v(" network restart\n")])])]),t("div",{staticClass:"language-sh extra-class"},[t("pre",{pre:!0,attrs:{class:"language-sh"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[s._v("vi")]),s._v(" /etc/resolv.conf\n")])])]),t("div",{staticClass:"language-sh extra-class"},[t("pre",{pre:!0,attrs:{class:"language-sh"}},[t("code",[t("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 阿里云提供的")]),s._v("\nnameserver "),t("span",{pre:!0,attrs:{class:"token number"}},[s._v("223.5")]),s._v(".5.5\nnameserver "),t("span",{pre:!0,attrs:{class:"token number"}},[s._v("223.6")]),s._v(".6.6\n")])])]),t("h2",{attrs:{id:"卸载指定安装包"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#卸载指定安装包"}},[s._v("#")]),s._v(" 卸载指定安装包")]),s._v(" "),t("h3",{attrs:{id:"查看已安装的包"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#查看已安装的包"}},[s._v("#")]),s._v(" 查看已安装的包")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[s._v("rpm")]),s._v(" -qa "),t("span",{pre:!0,attrs:{class:"token operator"}},[s._v("|")]),s._v(" "),t("span",{pre:!0,attrs:{class:"token function"}},[s._v("grep")]),s._v(" xxx\n")])])]),t("h3",{attrs:{id:"执行卸载"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#执行卸载"}},[s._v("#")]),s._v(" 执行卸载")]),s._v(" "),t("div",{staticClass:"language-shell extra-class"},[t("pre",{pre:!0,attrs:{class:"language-shell"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[s._v("rpm")]),s._v(" -e "),t("span",{pre:!0,attrs:{class:"token variable"}},[t("span",{pre:!0,attrs:{class:"token variable"}},[s._v("`")]),t("span",{pre:!0,attrs:{class:"token function"}},[s._v("rpm")]),s._v(" -qa "),t("span",{pre:!0,attrs:{class:"token operator"}},[s._v("|")]),s._v(" "),t("span",{pre:!0,attrs:{class:"token function"}},[s._v("grep")]),s._v(" xxx"),t("span",{pre:!0,attrs:{class:"token variable"}},[s._v("`")])]),s._v("\n"),t("span",{pre:!0,attrs:{class:"token function"}},[s._v("rpm")]),s._v(" -e xxx\n")])])])])}),[],!1,null,null,null);a.default=n.exports}}]);