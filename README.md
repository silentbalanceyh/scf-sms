示例代码

```java
package com.kingxunlian.infix.sms;

import com.fasterxml.jackson.core.JsonObject;
import com.kingxunlian.infix.sms.mobile.UmpExample;
import com.kingxunlian.infix.sms.mobile.UmpSender;
import com.kingxunlian.platform.tool.IO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UmpExmapleTc {

    @Test
    public void verifyTpl() {
        final JsonObject msg = IO.getJObject("sms-test/test.json");
        final Tpl template = new UmpExample();
        final String content = template.to(msg);
        assertEquals("Hello Lang, my email is silentbalanceyh@126.com, please send email to contact with me.", content);
    }

    @Test
    public void verifyRemote() {
        final JsonObject msg = IO.getJObject("sms-test/test.json");
        final Tpl template = new UmpExample();
        final Sender sender = new UmpSender(template);
        // sender.send("15922611447", msg);
        // 测试时再打开：
        sender.send("15922611447", msg);
    }
}
```

参数文件配置，上文中的`sms-test/test.json`文件内容

```json
{
    "name": "Lang",
    "email": "silentbalanceyh@126.com"
}
```

模板文件内容：`sms/exmaple.json`

```json
{
    "pattern": "Hello ${name}, my email is ${email}, please send email to contact with me."
}
```

配置文件地址：

```java
resources/sms/exmaple.json （可自定义）
resources/sms/ump.properties（推荐不变）
```
