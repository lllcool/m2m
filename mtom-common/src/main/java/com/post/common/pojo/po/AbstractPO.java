package com.post.common.pojo.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 抽象PO
 *
 * @author ljm
 * @date 2021/01/25
 */
public abstract class AbstractPO implements Serializable {

    private static final long serialVersionUID = 640619331196056814L;

    public void preInsert(String createdBy) {
        if (this instanceof CreatedTime || this instanceof OperatedTime) {
            Date now = new Date();
            if (this instanceof CreatedTime) {
                ((CreatedTime) this).setCreatedTime(now);
            }
            if (this instanceof OperatedTime) {
                ((OperatedTime) this).setOperatedTime(now);
            }
        }
        if (this instanceof Jsr310CreatedTime || this instanceof Jsr310OperatedTime) {
            LocalDateTime now = LocalDateTime.now();
            if (this instanceof Jsr310CreatedTime) {
                ((Jsr310CreatedTime) this).setCreatedTime(now);
            }
            if (this instanceof Jsr310OperatedTime) {
                ((Jsr310OperatedTime) this).setOperatedTime(now);
            }
        }
        if (this instanceof Deleted) {
            ((Deleted) this).setDeleted(false);
        }
        if (this instanceof CreatedBy) {
            ((CreatedBy) this).setCreatedBy(createdBy);
        }
        if (this instanceof OperatedBy) {
            ((OperatedBy) this).setOperatedBy(createdBy);
        }
        if (this instanceof Version) {
            ((Version) this).setVersion(1);
        }
    }

    public void preUpdate(String operatedBy) {
        if (this instanceof OperatedTime) {
            ((OperatedTime) this).setOperatedTime(new Date());
        }
        if (this instanceof Jsr310OperatedTime) {
            ((Jsr310OperatedTime) this).setOperatedTime(LocalDateTime.now());
        }
        if (this instanceof OperatedBy) {
            ((OperatedBy) this).setOperatedBy(operatedBy);
        }
    }

    public void postUpdate() {
        if (this instanceof Version) {
            Version version = (Version) this;
            version.setVersion(version.getVersion() + 1);
        }
    }

}

