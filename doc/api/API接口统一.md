## API接口统一

    {
        "code" : "状态码",
        "message" : "提示信息",
        "data" : "数据"
    }
    
    message表示提示信息，接口返回的信息
    data表示返回的数据
    code表示状态码
   
    状态业务码表示：20000表示成功，其他2xxxx表示失败
 
     SUCCESS(20000, "操作成功"),
     FAILED(20500, "操作失败"),
     VALIDATE_FAILED(20404, "参数检验失败"),
     UNAUTHORIZED(20401, "暂未登录或token已经过期"),
     FORBIDDEN(20403, "没有相关权限");
     
 ### 参考
 
 [前后端接口规范](https://github.com/f2e-journey/treasure/blob/master/api.md)
 
 [API接口规范完整版本（续）](https://github.com/mishe/blog/issues/129)