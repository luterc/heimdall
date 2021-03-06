/*
 *
 *  *    Copyright 2020-2021 Luter.me
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 */

package com.luter.heimdall.core.config.property;

import lombok.Data;

/**
 * The type Session property.
 *
 * @author Luter
 */
@Data
public class SessionProperty {
    /**
     * 全局Session过期时间，单位秒，默认 3600秒
     */
    private long globalSessionTimeout = 3600;
    /**
     * Session前缀
     */
    private String sessionIdPrefix = "heimdall:sessions:";
    /**
     * 活动Session缓存key，仅限redis缓存， 用以分页获取在线用户
     * <p>
     * <p>
     * 数据以ZSet形式存储
     * <p>
     * key  = SimpleSession.id
     * score = Session创建当前日期，如:20110101
     */
    private String activeSessionCacheKey = "heimdall:active-sessions:";
    /**
     * 活动用户缓存key，用户判断某个principal是否已经登录
     * <p>
     * <p>
     * =====对于redis缓存
     * 数据以Hash表形式存储
     * <p>
     * key  = UserDetails.getPrincipal()
     * value = SimpleSession.id
     */
    private String activeUserCacheKey = "heimdall:active-users:";
    /**
     * 前端请求传过来的token或者sessionId 的参数名称
     * 尽量与cookie的name一致，也可不同
     * <p>
     * <p>
     * 默认   hSession:xxxxx
     */
    private String sessionName = "HSessionId";
    /**
     * 重复登录处理策略
     * <p>
     * <p>
     * false: 不允许，踢掉前面的
     * <p>
     * true: 允许，拒绝后来的
     *
     * @since 1.0.2
     */
    private boolean concurrentLogin = false;
    /////////以下参数目前仅对redis生效@v1.0.0
    /**
     * 要不要续签Session
     */
    private boolean renew = true;
    /**
     * session过期时间比例低于多少了才开始续签，默认:0.5,即:Session过期时间少于globalSessionTimeout * 0.5
     * 取值范围:0.1-0.9，对应10%和90%
     */
    private double ratio = 0.5;

}
