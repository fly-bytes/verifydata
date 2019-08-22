# verifydata
数据校验工具，springboot 使用注解增强数据校验工具，简单高效，支持自定义返回类型
### 支持的校验规则

- 长度
- 正则
- 是否为空   

### 支持校验的对象   
- 除byte基础类型外
- List类型
- 自定义实体


### 教程如下
springboot项目引入下面依赖   
  
    <dependency>
        <groupId>com.github.fly-bytes</groupId>
        <artifactId>verifydata</artifactId>
        <version>1.0</version>
    </dependency>


controller方法上加上注解@Validation，需要校验的参数上加上@Verify即可，**自定义bean不需要加**，只需要在bean属性上添加，如下面TBean

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

##### 校验失败返回基础json

    {
      "code": "-1",
      "data": null,
      "message": "主键不能为空"
    }
  
  如果这个json格式不太符合你的要求，你可以这样做增加其他返回字段，建立实体类继承happy.validation.config.bean.ResponseBody
   
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

 配置一下bean

    @Bean("responseBody")
    @Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Responses responseBody() {
       return new Responses();
    } 

