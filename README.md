Welcome to the opscloud wiki!

# OpsCloud4.0 IaC基础架构即代码
<img src="https://img.shields.io/badge/version-4.0.8-brightgreen.svg"></img>
<img src="https://img.shields.io/badge/java-8-brightgreen.svg"></img> 
<img src="https://img.shields.io/badge/springboot-2.4.13-brightgreen.svg"></img> 
<img src="https://img.shields.io/badge/mysql-8-brightgreen.svg"></img> 

<br>

### 项目语言&仓库
+ 服务端Java, github.com/ixrjog/opscloud4
+ 前端Vue, github.com/ixrjog/opscloud4-web

### 开发环境
+ MacBook M1 PRO / macOS Monterey
+ IntelliJ IDEA 2021.3.2 (Ultimate Edition)
+ WebStorm 2021.3.2

### 系统
#### 环境管理
<img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/github/sys/env.png"></img>
#### 标签管理
<img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/github/sys/tag.png"></img>
#### 集群管理
<img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/github/sys/instance.png"></img>

### 服务器管理
<img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/github/oc4-6.png"></img>

### 远程控制(RDP/VNC)
<img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/github/oc4-10.png"></img>

### Web-Terminal(Web终端)

#### 服务器(批量操作,会话复制,高低权限,布局切换)
<img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/github/terminal/terminal-server.png"></img>
<img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/github/terminal/terminal-server-2.png"></img>
<img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/github/terminal/terminal-server-3.png"></img>

#### 容器(支持登录操作或只读查看日志)
<img src="https://opscloud4-res.oss-cn-hangzhou.aliyuncs.com/help/terminal/terminal-pod.png"></img>
<img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/github/terminal/terminal-k8s-pod-2.png"></img>

### SSH-Server(命令行终端)
#### 帮助命令`help`,命令参数提示
<img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/github/ssh-server/ssh-server-help.png"></img>
<img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/github/ssh-server/ssh-server-help-2.png"></img>

#### 事件列表基于事件驱动实现
<img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/github/ssh-server/ssh-server-event.png"></img>

#### 服务器列表命令`list`
<img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/github/ssh-server/ssh-server-list.png"></img>
#### 容器组列表命令`list-k8s-pod`
<img src="https://opscloud4-res.oss-cn-hangzhou.aliyuncs.com/help/ssh-server/ssh-server-list-k8s-pod.png"></img>

### 审计
<img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/github/terminal/terminal-audit.png"></img>

### 剧本
<img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/github/playbook/playbook.png"></img>
<img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/github/playbook/playbook-2.png"></img>
<img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/github/playbook/playbook-3.png"></img>
<img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/github/playbook/playbook_log.png"></img>

### 用户
<img src="https://opscloud4-res.oss-cn-hangzhou.aliyuncs.com/help/user/user.png"></img>
<img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/github/user/user-details.png"></img>

### 数据源(万物皆资产)
<img src="https://opscloud4-res.oss-cn-hangzhou.aliyuncs.com/help/datasource/instance.png"></img>
<img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/github/datasource/datasource-aliyun.png"></img>
<img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/github/datasource/datasource-aliyun-log.png"></img>
<img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/github/datasource/datasource-zabbix-host.png"></img>

#### <span style="color:green">平台演示视频1</span>
<video src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/video/opscloud4-1.mov" width="400px" height="300px" controls="controls"></video>

#### 核心功能
+ 事件驱动
+ 万物皆资产
  + 多实例支持
  + 多云支持（阿里云，AWS）
+ 运维工单 
+ 堡垒机
  + 远程桌面RDP,VNC(需安装apache-guacamole)
  + Web终端（支持多开，会话复制，命令同步）
  + SSH-Server(命令行终端)
    + 支持RSA-4096bit密钥，支持ED25519密钥 
    + 支持用户关联Gitlab账户中配置的公钥，无需配置即可登录
    + 展示服务器环境，标签，授权账户
    + 支持Kubernetes容器登录（容器堡垒机）
    + 支持查看Kubernetes容器日志
    + 以事件驱动的主动式堡垒机技术（通过事件ID登录服务器）
+ 剧本任务
+ RBAC
+ 集群架构

### 帮助文档

<a style="color:#2b669a" href="https://www.kancloud.cn/ixrjog/opscloud4/2361886" target="_blank">传送门:https://www.kancloud.cn/ixrjog/opscloud4/2361886</a>

### Thanks to JetBrains
<a href="https://www.jetbrains.com" target="_blank">
  <img src="https://opscloud-res.oss-cn-hangzhou.aliyuncs.com/opscloud4/jetbrains-logos/jetbrains-variant-2.svg"></img>
</a>

> Thanks JetBrains to support the project providing such great IDE.