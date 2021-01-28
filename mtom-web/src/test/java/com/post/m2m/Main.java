package com.post.m2m;

import com.post.m2m.web.rest.ball.BallControllerTest;
import com.post.m2m.web.rest.invoice.InvoiceControllerTest;
import com.post.m2m.web.rest.invoicedetail.InvoiceDetailControllerTest;
import com.post.m2m.web.rest.stu_ref_teach.StuRefTeachControllerTest;
import com.post.m2m.web.rest.student.StudentControllerTest;
import com.post.m2m.web.rest.teacher.TeacherControllerTest;
import com.post.m2m.web.rest.teama.TeamaControllerTest;
import com.post.m2m.web.rest.teamgroup.TeamGroupControllerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 合并测试类
 *
 * @author ljm
 * @date 2021/01/25
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        InvoiceControllerTest.class,
        InvoiceDetailControllerTest.class,
        TeacherControllerTest.class,
        StudentControllerTest.class,
        StuRefTeachControllerTest.class,
        BallControllerTest.class,
        TeamaControllerTest.class,
        TeamGroupControllerTest.class,
})
public class Main {


}

