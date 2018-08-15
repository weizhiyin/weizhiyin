package net.weizhiyin.common.config;

import net.weizhiyin.base.entity.BaseEntity;
import net.weizhiyin.base.entity.User;
import net.weizhiyin.common.utils.SessionUtil;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class })
})
@Component
public class MyInterceptor implements Interceptor {
    Logger log = LoggerFactory.getLogger(MyInterceptor.class);

    public MyInterceptor() {
        log.info(" MyInterceptor init success ");
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object param = invocation .getArgs()[1];
        if(param instanceof BaseEntity) {
            updateProperties(param,sqlCommandType);
        }else if(param instanceof Map) {
            HashMap<String,List> map = (HashMap<String, List>) param;
            if(map.containsKey("list")) {//XXX BY HZN 如果参数是list 暂时通过这种方法获取
                List<Object> list = map.get("list");
                for(Object obj : list) {
                    boolean flag = obj instanceof BaseEntity;
                    if(!flag) {
                        break;
                    }
                    updateProperties(obj,sqlCommandType);
                }
            }
            if(map.containsKey("et")) {
                Object obj = map.get("et");
                if(obj instanceof BaseEntity){
                    updateProperties(obj,sqlCommandType);
                }
            }
        }

        return invocation.proceed();
    }
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    private void updateProperties(Object param, SqlCommandType sqlCommandType) {
        BaseEntity entity = (BaseEntity) param;
        Date now = new Date();
        User user=(User) SessionUtil.getSessionAttribute(SessionUtil.USER_INFO);
        if(sqlCommandType == SqlCommandType.INSERT){
            entity.setId(UUID.randomUUID().toString());
            entity.setSysCreate(now);
            entity.setSysUpdate(now);
            if(user!=null){
                entity.setSysCreateBy(user.getId());
                entity.setSysUpdateBy(user.getId());
            }
        }
        if(sqlCommandType == SqlCommandType.UPDATE){
            entity.setSysUpdate(now);
            if(user!=null){
                entity.setSysUpdateBy(user.getId());
            }
        }
    }
}
