# 多对多映射测试示例工程

## 关于项目

- web项目启动类：[MtomApp.java](/mtom-web/src/main/java/com/post/m2m/MtomApp.java)
- 单元测试入口类：[Main.java](/mtom-web/src/test/java/com/post/m2m/Main.java)
- mysql建表语句：[mtom.sql](/mtom-web/src/test/resources/DB/mtom.sql)

## 表结构

### 发票信息【invoice】
|字段名 | 类型 | 非空 | 键 | 注释 |
|------ | ---- | --- | --- | ---- |
| id | varchar(32) | 是 | 主键 | 主键ID |
| type | int(8) | 是 |  | 发票类别 |
| invoice_name | varchar(10) |  否  |  | 发票名称 |
| invoice | varchar(20) | 是 |  | 发票标题 |

### 发票明细【invoice_detail】
|字段名 | 类型 | 非空 | 键 | 注释 |
|------ | ---- | --- | --- | ---- |
| id | varchar(32) | 是 | 主键 | 主键ID |
| count | int(4) | 是 |  | 数量 |
| people | varchar(20) |  否  |  | 开票人 |
| price | double(2,0) | 是 |  | 价格 |
| invoice_id | varchar(32) | 是 |  | 发票ID |

### 老师【teacher】
|字段名 | 类型 | 非空 | 键 | 注释 |
|------ | ---- | --- | --- | ---- |
| id | varchar(32) | 是 | 主键 | 主键ID |
| subject | varchar(10) |  否  |  | 科目 |
| teacher_name | varchar(10) |  否  |  | 老师姓名 |

### 学生【student】
|字段名 | 类型 | 非空 | 键 | 注释 |
|------ | ---- | --- | --- | ---- |
| id | varchar(32) | 是 | 主键 | 主键ID |
| name | varchar(32) | 是 |  | 姓名 |
| age | int(3) | 是 |  | 年龄 |

### 老师学生关系表【stu_ref_teach】
|字段名 | 类型 | 非空 | 键 | 注释 |
|------ | ---- | --- | --- | ---- |
| id | varchar(32) | 是 | 主键 | 主键ID |
| stu_id | varchar(32) |  否  |  | 主键ID |
| teacher_id | varchar(32) |  否  |  | 老师 |

### 球【ball】
|字段名 | 类型 | 非空 | 键 | 注释 |
|------ | ---- | --- | --- | ---- |
| id | varchar(32) | 是 | 主键 | 主键ID |
| ball_name | varchar(10) | 是 |  | 球类名称 |
| price | int(5) |  否  |  | 价钱 |

### 团队【teama】
|字段名 | 类型 | 非空 | 键 | 注释 |
|------ | ---- | --- | --- | ---- |
| id | varchar(32) | 是 | 主键 | 主键ID |
| team_name | varchar(20) |  否  |  | 团队名称 |

### 团队组成【team_group】
|字段名 | 类型 | 非空 | 键 | 注释 |
|------ | ---- | --- | --- | ---- |
| id | varchar(32) | 是 | 主键 | 主键ID |
| count | varchar(5) |  否  |  | 数量 |
| team_id | varchar(32) |  否  |  | 团队 |
