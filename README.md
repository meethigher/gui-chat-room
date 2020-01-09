# Java界面式聊天室

## 原理

![原理](https://meethigher.top/blog/2020/01/07/chat-room/服务端与客户端交互过程图.png)

原理上面那个图，描述的很详细了，不多赘述。

## 实现

* Server端
  * ChatServer类：主要用来实现服务端的一些功能
  * ChatServerRunnable类：实现Runnable接口，用来服务端收发消息
  * ServerFrame类：服务端的界面类
* Client端
  * ChatClient类：用来实现客户端的一些功能
  * STCRunnable类：实现Runnable接口，用来实现服务端向客户端下发消息
  * CTSRunnable类：实现Runnable接口，用来实现客户端向服务端发送消息
  * CheckIP类：通过正则表达式验证服务器ip地址是否正确
  * ClientFrame类：客户端的界面类

## 运行结果

* 聊天功能

  ![聊天功能](https://meethigher.top/blog/2020/01/09/chat-room-updraded-version/chat.png)

* 用户退出提示 

![用户退出提示](https://meethigher.top/blog/2020/01/09/chat-room-updraded-version/exit.png )

## 具体过程

[socket聊天室界面版]( https://meethigher.top/blog/2020/01/09/chat-room-updraded-version/ )

