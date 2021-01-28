package com.post.m2m.web.rest.teamgroup;

import com.post.common.constant.ErrorCode;
import com.post.common.exception.BusinessException;
import com.post.common.pojo.vo.PageVO;
import com.post.m2m.pojo.dto.teamgroup.TeamGroupAddDTO;
import com.post.m2m.pojo.dto.teamgroup.TeamGroupUpdateDTO;
import com.post.m2m.pojo.mapper.teamgroup.TeamGroupMapper;
import com.post.m2m.pojo.po.teamgroup.TeamGroupPO;
import com.post.m2m.pojo.qo.teamgroup.TeamGroupQO;
import com.post.m2m.pojo.vo.teamgroup.TeamGroupListVO;
import com.post.m2m.pojo.vo.teamgroup.TeamGroupShowVO;
import com.post.m2m.service.teamgroup.TeamGroupService;
import com.post.m2m.web.AbstractController;
import com.post.m2m.web.api.teamgroup.TeamGroupAPI;
import com.post.m2m.web.constant.WebConst;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

/**
 * 【teamGroup】控制器
 *
 * @author ljm
 * @date 2021/01/25
 */
@RestController
@RequestMapping(WebConst.ModulePath.TEAMGROUP + "/teamGroup")
public class TeamGroupController extends AbstractController implements TeamGroupAPI {

    @Autowired
    private TeamGroupService teamGroupService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TeamGroupShowVO> save(@Valid @RequestBody TeamGroupAddDTO teamGroupAddDTO) throws Exception {
        TeamGroupPO teamGroup = teamGroupService.save(teamGroupAddDTO);
        return ResponseEntity.created(new URI(WebConst.ModulePath.TEAMGROUP + "/teamGroup/" + teamGroup.getId()))
                .body(TeamGroupMapper.INSTANCE.toShowVO(teamGroup));
    }

    @Override
    @PutMapping
    public ResponseEntity<TeamGroupShowVO> update(@Valid @RequestBody TeamGroupUpdateDTO teamGroupUpdateDTO) {
        TeamGroupPO teamGroup = teamGroupService.update(teamGroupUpdateDTO);
        return ResponseEntity.ok(TeamGroupMapper.INSTANCE.toShowVO(teamGroup));
    }

    @Override
    @GetMapping
    public ResponseEntity<PageVO<TeamGroupListVO>> list(@Valid TeamGroupQO teamGroupQO) {
        PageVO<TeamGroupListVO> page = teamGroupService.list(teamGroupQO);
        return ResponseEntity.ok(page);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<TeamGroupShowVO> show(@PathVariable String id) {
        TeamGroupShowVO teamGroupShowVO = teamGroupService.show(id);
        return ResponseEntity.ok(teamGroupShowVO);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> delete(@PathVariable String id) {
        int count = teamGroupService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Integer> deleteBatch(@RequestBody String[] id) {
        if (ArrayUtils.isEmpty(id)) {
            throw new BusinessException(ErrorCode.PARAM_IS_NULL);
        }
        int count = teamGroupService.delete(id);
        return ResponseEntity.ok(count);
    }

}


