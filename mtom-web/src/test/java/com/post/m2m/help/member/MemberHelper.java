package com.post.m2m.help.member;

import com.post.common.util.SafeUtil;
import com.post.m2m.pojo.dto.member.*;
import com.post.m2m.pojo.po.member.*;
import com.post.m2m.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.post.m2m.pojo.example.member.MemberExample.*;

@Component
public class MemberHelper {

    @Autowired
    private MemberService memberService;

    /**
     * 生成add测试数据
     *
     * @return
     */
    public MemberAddDTO getMemberAddDTO() {
        MemberAddDTO dto = new MemberAddDTO();
        dto.setMName(E_M_NAME);
        dto.setAge(SafeUtil.getInteger(E_AGE));
        return dto;
    }


    /**
     * 生成update测试数据
     *
     * @return
     */
    public MemberUpdateDTO getMemberUpdateDTO(MemberPO member) {
        MemberUpdateDTO dto = new MemberUpdateDTO();
        dto.setId(member.getId());
        dto.setMName(member.getMName());
        dto.setAge(member.getAge());
        return dto;
    }

    /**
     * 保存示例
     *
     * @return
     */
    public MemberPO saveMemberExample() {
        MemberAddDTO addDTO = this.getMemberAddDTO();
        return memberService.save(addDTO);
    }


}

