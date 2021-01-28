package com.post.m2m.web.rest.teama;

import com.post.common.constant.ErrorCode;
import com.post.common.exception.BusinessException;
import com.post.common.pojo.qo.OptionQO;
import com.post.common.pojo.vo.OptionVO;
import com.post.common.pojo.vo.PageVO;
import com.post.m2m.pojo.dto.teama.TeamaAddDTO;
import com.post.m2m.pojo.dto.teama.TeamaUpdateDTO;
import com.post.m2m.pojo.mapper.teama.TeamaMapper;
import com.post.m2m.pojo.po.teama.TeamaPO;
import com.post.m2m.pojo.qo.teama.TeamaQO;
import com.post.m2m.pojo.vo.teama.TeamaListVO;
import com.post.m2m.pojo.vo.teama.TeamaShowVO;
import com.post.m2m.service.teama.TeamaService;
import com.post.m2m.web.AbstractController;
import com.post.m2m.web.api.teama.TeamaAPI;
import com.post.m2m.web.constant.WebConst;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * 【teama】控制器
 *
 * @author ljm
 * @date 2021/01/25
 */
@RestController
@RequestMapping(WebConst.ModulePath.TEAMA + "/teama")
public class TeamaController extends AbstractController implements TeamaAPI {

    @Autowired
    private TeamaService teamaService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TeamaShowVO> save(@Valid @RequestBody TeamaAddDTO teamaAddDTO) throws Exception {
        TeamaPO teama = teamaService.save(teamaAddDTO);
        return ResponseEntity.created(new URI(WebConst.ModulePath.TEAMA + "/teama/" + teama.getId()))
                .body(TeamaMapper.INSTANCE.toShowVO(teama));
    }

    @Override
    @PutMapping
    public ResponseEntity<TeamaShowVO> update(@Valid @RequestBody TeamaUpdateDTO teamaUpdateDTO) {
        TeamaPO teama = teamaService.update(teamaUpdateDTO);
        return ResponseEntity.ok(TeamaMapper.INSTANCE.toShowVO(teama));
    }

    @Override
    @GetMapping
    public ResponseEntity<PageVO<TeamaListVO>> list(@Valid TeamaQO teamaQO) {
        PageVO<TeamaListVO> page = teamaService.list(teamaQO);
        return ResponseEntity.ok(page);
    }

    @Override
    @GetMapping(value = "/options")
    public ResponseEntity<List<OptionVO<String, String>>> findOptions(OptionQO<String, String> qo) {
        List<OptionVO<String, String>> options = teamaService.findOptions(qo);
        return ResponseEntity.ok(options);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<TeamaShowVO> show(@PathVariable String id) {
        TeamaShowVO teamaShowVO = teamaService.show(id);
        return ResponseEntity.ok(teamaShowVO);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> delete(@PathVariable String id) {
        int count = teamaService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Integer> deleteBatch(@RequestBody String[] id) {
        if (ArrayUtils.isEmpty(id)) {
            throw new BusinessException(ErrorCode.PARAM_IS_NULL);
        }
        int count = teamaService.delete(id);
        return ResponseEntity.ok(count);
    }

}


