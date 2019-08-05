# verifydata
数据校验工具
### 支持的校验规则

- 长度
- 正则
- 是否为空

### 教程如下

springboot项目引入maven  
```javascript
 <dependency>
    <groupId>com.github.liubingxu18</groupId>
    <artifactId>verifydata</artifactId>
    <version>1.1</version>
 </dependency>
```
例如：

    @GetMapping("/test")
    @happy.validation.Validation
    public TBean get(@happy.validation.verify.Verify(maxLength = 2, message = "长度最长为2") String test,
                      TBean tBean){
        return tBean;
    }

    @Data
    private class TBean{
        @happy.validation.verify.Verify(notNull = true, message = "主键不能为空")
        private int id;
        @happy.validation.verify.Verify(maxLength = 20, message = "用户名最长20")
        private String name;
    }

#### 校验失败返回json
```javascript
{
  "code": "-1",
  "data": null,
  "message": "邮箱格式不对"
}
```
