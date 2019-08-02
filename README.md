# verifydata
数据校验工具
### 支持的校验规则

- 长度
- 正则
- 是否为空

### 使用教程如下

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
