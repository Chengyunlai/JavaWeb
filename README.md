# JavaWeb
[Git代码](https://github.com/Chengyunlai/JavaWeb)
***
> 前言
>
> > 个人文档版权©，转载和引用请附带作者链接
> 
>> 这个文档希望可以帮助你了解JavaWeb的开发的基础流程，然后再慢慢往**高级框架篇**
>
>> 使用空工程和Jar包导入，手动打包管理项目是一件麻烦的事，你需要Maven帮助你管理项目。
>
>> 见过太多纯文本或者只有内嵌代码块的经验分享，还是MD最友好，所以写技术分享还是用MD吧。
>
>> 这个文档适合懂一点Maven知识，懂一点JavaSE，懂一点接口开发思想，懂一点MVC思想，懂一点HTTP
***

## 项目构建
1. 你要先这样
   ![img_1.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/5b1654ff926a4fc8824b92fbb53fffdd~tplv-k3u1fbpfcp-zoom-1.image)
2. 然后再这样
   ![img_1.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/ece9a8172f4941b2af76bbd9b29a438a~tplv-k3u1fbpfcp-zoom-1.image)
3. 最后要这样
   ![img_2.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/be26be9f09064a2f9a6680e8217554f4~tplv-k3u1fbpfcp-zoom-1.image)

这样的项目内置Tomcat服务器，java的代码和前端的代码都包含在这个工程中，运行时打包成war包即可
***
## 目录结构
![img.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/24c24fb2a6774e6ab9e0f06b8144c20d~tplv-k3u1fbpfcp-zoom-1.image)
***
## Maven
Maven最直观的一点就是做到了jar包管理，所以你想使用任何第三方的技术，又不想去网上搜各种jar包自己下载，在Maven工程中，修改**pom.xml**即可

```xml
<dependencies>
    <!--Servlet的jar包-->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>3.1.0</version>
    </dependency>  
</dependencies>
```
### Maven仓库
用来搜索Jar包的，地址如下:

`https://mvnrepository.com/`

maven工程还能做jar包版本管理，模块开发，继承打包插件等一些列方便开发的功能，所以直接拿起IDEA创建Maven工程进行开发学习吧
***
## 前后端分离思想
前端不碰后端代码

后端不碰前端代码

**将工程中的jsp文件删除，创建html代码即可，享受极致的前端原生开发；请求发送使用axios技术**

axios是基于Ajax技术的封装，正是因为有它开启了前后端分离

***

## 后端开发的思想 :
开发东西，我们会先从**外向内看**:

- [x] 🥰它得会动，长得好看

- [ ] 🚫然后才会去思考如何去开发

所以我基于这种思想展开来分享一下JavaWeb使用的技术栈。

***

## 前端数据想发送给后端，或者我在网页端如何向一个服务器发请求？
### 技术要点 Axios
> Axios可以实现前端向后端的网络请求

官网地址:`https://www.axios-http.cn/`

#### 安装方式一 : 常用在前端框架工程:
* 注意:需要Node环境
* 安装语句:`npm install axios`

#### 安装方式二 : 使用CDN
HTML代码中的添加JavaScript代码链接以下内容:
`https://cdn.staticfile.org/axios/0.2.0/axios.amd.js`
* 补充:CDN网站:`http://staticfile.org/`

#### 使用
##### 作者喜欢的写法
```javascript
axios({配置}).then(res=>{res是相应的内容})

配置详见下面 : 请求配置
```
##### 例子:
```javascript
axios({
  method: 'post',
  url: 'http://www.chengyunlai.top/JavaWeb/login',
  responseType: 'json'
}).then(res=>{
    if(res.data.falg == true){
        console.log("请求成功，我要做一些其他处理")   
    }
})

解释:
method:发送方法Post
url:请求发送的服务器地址,其中JavaWeb是后端工程名,/login是Servlet处理的url
responseType:表示浏览器将要响应的数据类型
res:服务器响应的内容
```

##### 写法二 : 请求方式别名
```markdown
axios.request(config)
axios.get(url[, config])
axios.delete(url[, config])
axios.head(url[, config])
axios.options(url[, config])
axios.post(url[, data[, config]])
axios.put(url[, data[, config]])
axios.patch(url[, data[, config]])
```

#### 请求配置
```javascript
{
  // `url` 是用于请求的服务器 URL
  url: '/user',

  // `method` 是创建请求时使用的方法
  method: 'get', // 默认值

  // `baseURL` 将自动加在 `url` 前面，除非 `url` 是一个绝对 URL。
  // 它可以通过设置一个 `baseURL` 便于为 axios 实例的方法传递相对 URL
  baseURL: 'https://some-domain.com/api/',

  // `transformRequest` 允许在向服务器发送前，修改请求数据
  // 它只能用于 'PUT', 'POST' 和 'PATCH' 这几个请求方法
  // 数组中最后一个函数必须返回一个字符串， 一个Buffer实例，ArrayBuffer，FormData，或 Stream
  // 你可以修改请求头。
  transformRequest: [function (data, headers) {
    // 对发送的 data 进行任意转换处理

    return data;
  }],

  // `transformResponse` 在传递给 then/catch 前，允许修改响应数据
  transformResponse: [function (data) {
    // 对接收的 data 进行任意转换处理

    return data;
  }],

  // 自定义请求头
  headers: {'X-Requested-With': 'XMLHttpRequest'},

  // `params` 是与请求一起发送的 URL 参数
  // 必须是一个简单对象或 URLSearchParams 对象
  params: {
    ID: 12345
  },

  // `paramsSerializer`是可选方法，主要用于序列化`params`
  // (e.g. https://www.npmjs.com/package/qs, http://api.jquery.com/jquery.param/)
  paramsSerializer: function (params) {
    return Qs.stringify(params, {arrayFormat: 'brackets'})
  },

  // `data` 是作为请求体被发送的数据
  // 仅适用 'PUT', 'POST', 'DELETE 和 'PATCH' 请求方法
  // 在没有设置 `transformRequest` 时，则必须是以下类型之一:
  // - string, plain object, ArrayBuffer, ArrayBufferView, URLSearchParams
  // - 浏览器专属: FormData, File, Blob
  // - Node 专属: Stream, Buffer
  data: {
    firstName: 'Fred'
  },
  
  // 发送请求体数据的可选语法
  // 请求方式 post
  // 只有 value 会被发送，key 则不会
  data: 'Country=Brasil&City=Belo Horizonte',

  // `timeout` 指定请求超时的毫秒数。
  // 如果请求时间超过 `timeout` 的值，则请求会被中断
  timeout: 1000, // 默认值是 `0` (永不超时)

  // `withCredentials` 表示跨域请求时是否需要使用凭证
  withCredentials: false, // default

  // `adapter` 允许自定义处理请求，这使测试更加容易。
  // 返回一个 promise 并提供一个有效的响应 （参见 lib/adapters/README.md）。
  adapter: function (config) {
    /* ... */
  },

  // `auth` HTTP Basic Auth
  auth: {
    username: 'janedoe',
    password: 's00pers3cret'
  },

  // `responseType` 表示浏览器将要响应的数据类型
  // 选项包括: 'arraybuffer', 'document', 'json', 'text', 'stream'
  // 浏览器专属：'blob'
  responseType: 'json', // 默认值

  // `responseEncoding` 表示用于解码响应的编码 (Node.js 专属)
  // 注意：忽略 `responseType` 的值为 'stream'，或者是客户端请求
  // Note: Ignored for `responseType` of 'stream' or client-side requests
  responseEncoding: 'utf8', // 默认值

  // `xsrfCookieName` 是 xsrf token 的值，被用作 cookie 的名称
  xsrfCookieName: 'XSRF-TOKEN', // 默认值

  // `xsrfHeaderName` 是带有 xsrf token 值的http 请求头名称
  xsrfHeaderName: 'X-XSRF-TOKEN', // 默认值

  // `onUploadProgress` 允许为上传处理进度事件
  // 浏览器专属
  onUploadProgress: function (progressEvent) {
    // 处理原生进度事件
  },

  // `onDownloadProgress` 允许为下载处理进度事件
  // 浏览器专属
  onDownloadProgress: function (progressEvent) {
    // 处理原生进度事件
  },

  // `maxContentLength` 定义了node.js中允许的HTTP响应内容的最大字节数
  maxContentLength: 2000,

  // `maxBodyLength`（仅Node）定义允许的http请求内容的最大字节数
  maxBodyLength: 2000,

  // `validateStatus` 定义了对于给定的 HTTP状态码是 resolve 还是 reject promise。
  // 如果 `validateStatus` 返回 `true` (或者设置为 `null` 或 `undefined`)，
  // 则promise 将会 resolved，否则是 rejected。
  validateStatus: function (status) {
    return status >= 200 && status < 300; // 默认值
  },

  // `maxRedirects` 定义了在node.js中要遵循的最大重定向数。
  // 如果设置为0，则不会进行重定向
  maxRedirects: 5, // 默认值

  // `socketPath` 定义了在node.js中使用的UNIX套接字。
  // e.g. '/var/run/docker.sock' 发送请求到 docker 守护进程。
  // 只能指定 `socketPath` 或 `proxy` 。
  // 若都指定，这使用 `socketPath` 。
  socketPath: null, // default

  // `httpAgent` and `httpsAgent` define a custom agent to be used when performing http
  // and https requests, respectively, in node.js. This allows options to be added like
  // `keepAlive` that are not enabled by default.
  httpAgent: new http.Agent({ keepAlive: true }),
  httpsAgent: new https.Agent({ keepAlive: true }),

  // `proxy` 定义了代理服务器的主机名，端口和协议。
  // 您可以使用常规的`http_proxy` 和 `https_proxy` 环境变量。
  // 使用 `false` 可以禁用代理功能，同时环境变量也会被忽略。
  // `auth`表示应使用HTTP Basic auth连接到代理，并且提供凭据。
  // 这将设置一个 `Proxy-Authorization` 请求头，它会覆盖 `headers` 中已存在的自定义 `Proxy-Authorization` 请求头。
  // 如果代理服务器使用 HTTPS，则必须设置 protocol 为`https`
  proxy: {
    protocol: 'https',
    host: '127.0.0.1',
    port: 9000,
    auth: {
      username: 'mikeymike',
      password: 'rapunz3l'
    }
  },

  // see https://axios-http.com/zh/docs/cancellation
  cancelToken: new CancelToken(function (cancel) {
  }),

  // `decompress` indicates whether or not the response body should be decompressed 
  // automatically. If set to `true` will also remove the 'content-encoding' header 
  // from the responses objects of all decompressed responses
  // - Node only (XHR cannot turn off decompression)
  decompress: true // 默认值

}
```

### 技术要点 Servlet
> Servlet运行在 Web 服务器或应用服务器上的程序;可以读取来自浏览器发送的表单数据或者访问请求,根据请求做相应的逻辑处理后再响应到客户端浏览器

#### 包配置
```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.1.0</version>
</dependency>  
```
#### 代码
```java
package top.itifrd.controller.servlet;

import top.itifrd.service.LoginService;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("处理Post请求");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("处理Get请求");
    }
}
```
假设服务器地址:`http://www.chengyunlai.top/JavaWeb/`

JavaWeb因为是web工程，页面也在工程中，所以访问JavaWeb时自动定位到webapp根目录

当你在浏览器输入:`http://www.chengyunlai.top/JavaWeb/login` 就表示向服务器发送了一个get请求，而在上述代码中`@WebServlet("/login")` 会匹配到`/login`处理该请求，执行doGet方法

### 技术要点 Json
> Json作为数据传输的一种方式,是 是存储和交换文本信息的语法
#### 示例
```json
{
    "username":"chengyunlai",
    "password": "root"
}
```
都是以键值的方式表示，注意都要用括号包围起来

#### 使用
#### 前端表单数据以JSON字符串的格式发送
##### JSON.stringify()
* 向服务器发送数据时一般是字符串
* 该方法是将JavaScript 对象转换为字符串

示例
```javascript
// > 解释: 定义了一个对象data,属性有两个
let data:{
    username:'',
    password:''
}

// > 获取表单数据并且赋值
data.username = document.getElementById("username").value
data.password = document.getElementById("password").value

// > 转为json字符串
let res = JSON.stringify(data)

// > 通过axios发送

axios({
    method: 'post',
    url: 'http://www.chengyunlai.top/JavaWeb/login',
    data:res
    headers:{
      'Content-Type': 'application/json'
    }
    }).then(res=>{
        if(res.data.falg == true){console.log("请求成功，我要做一些其他处理")}
})
```
#### 后端接收JSON字符，转换成相应的bean对象
* `httpServletRequest.getReader()` 获得reader对象（包含着前端发送的请求体中的内容）
* `reader.readLine()`通过reader对象的readLine方法一行一行的读取，直到null
* `JSON.parseObject()`读取到的是字符，可以通过这个方法转成JSON对象，可以查键值
* `JSON.parseObject(jsonObject.toJSONString(), User.class)` 将JSON对象转成字符串并封装成Bean
```java
// 定义一个方法类，读取请求体的内容，并将内容转成json对象
public class JsonUtil {
    public static JSONObject formDataToJSONObject(BufferedReader formData){
        System.out.println("接收到表单数据:" + formData);
        // String result = null;
        JSONObject jsonObject = null;
        // 接收来自前端发送来的数据
        StringBuilder sb = new StringBuilder();
        try {
            String res = null;
            while ((res = formData.readLine())!= null){
                sb.append(res);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        jsonObject = JSON.parseObject(sb.toString());
        return jsonObject;
    }
}

// 使用
// 通过getReader方法获取表单的数据
BufferedReader reader = httpServletRequest.getReader();
// 将表单的数据转换成 JSON 对象
JSONObject jsonObject = FilterUtils.formDataToJSONObject(reader);
// 将内容转成Bean对象，通过JDBC或者Mybatis封存
User user = JSON.parseObject(jsonObject.toJSONString(), User.class);
```
## 前端发过来的数据怎么做持久化
### JDBC
### Druid
### 封装思想
### Mybatis