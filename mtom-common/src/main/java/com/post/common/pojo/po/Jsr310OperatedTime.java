package com.post.common.pojo.po;

import java.time.LocalDateTime;

/**
 * 操作时间接口-jsr310时间API
 *
 * @author ljm
 * @date 2021/01/25
 */
public interface Jsr310OperatedTime {

    LocalDateTime getOperatedTime();

    void setOperatedTime(LocalDateTime operatedTime);
}

