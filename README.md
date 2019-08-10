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
    <version>1.3</version>
 </dependency>
```
例如：

    @GetMapping("/test")
    @happy.validation.Validation
    public TBean get(@Verify(maxLength = 2, message = "长度最长为2") 
                     @RequestParms("test") String test, TBean tBean){
        return tBean;
    }

    @Data
    private class TBean{
        @Verify(notNull = true, message = "主键不能为空")
        private int id;
        @Verify(maxLength = 20, message = "用户名最长20")
        private String name;
    }

#### 校验失败返回json
```javascript
{
  "code": "-1",
  "data": null,
  "message": "主键不能为空"
}
```  
如果返回json格式不是必须的，你可以按照下面步骤增强实体类。   
1.建立实体类继承happy.validation.config.bean.ResponseBody   
```javascript
import happy.validation.config.bean.ResponseBody;

public class Responses extends ResponseBody {
    private int status = 500;

    public int getStatus() {
        return status;
    }

    @Override
    public void setCode(String code) {
        super.setCode("500");
    }
}
```
2.配置一下bean
```javascript
@Bean("responseBody")
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public Responses responseBody() {
    return new Responses();
} 
```

