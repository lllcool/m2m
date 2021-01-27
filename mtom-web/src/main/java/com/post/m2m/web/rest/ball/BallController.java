package com.post.m2m.web.rest.ball;

import com.post.common.constant.ErrorCode;
import com.post.common.exception.BusinessException;
import com.post.common.pojo.vo.PageVO;
import com.post.m2m.pojo.dto.ball.BallAddDTO;
import com.post.m2m.pojo.dto.ball.BallUpdateDTO;
import com.post.m2m.pojo.mapper.ball.BallMapper;
import com.post.m2m.pojo.po.ball.BallPO;
import com.post.m2m.pojo.qo.ball.BallQO;
import com.post.m2m.pojo.vo.ball.BallListVO;
import com.post.m2m.pojo.vo.ball.BallShowVO;
import com.post.m2m.service.ball.BallService;
import com.post.m2m.web.AbstractController;
import com.post.m2m.web.api.ball.BallAPI;
import com.post.m2m.web.constant.WebConst;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

/**
 * 【Ball】控制器
 *
 * @author ljm
 * @date 2021/01/25
 */
@RestController
@RequestMapping(WebConst.ModulePath.BALL + "/ball")
public class BallController extends AbstractController implements BallAPI {

    @Autowired
    private BallService ballService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BallShowVO> save(@Valid @RequestBody BallAddDTO ballAddDTO) throws Exception {
        BallPO ball = ballService.save(ballAddDTO);
        return ResponseEntity.created(new URI(WebConst.ModulePath.BALL + "/ball/" + ball.getId()))
                .body(BallMapper.INSTANCE.toShowVO(ball));
    }

    @Override
    @PutMapping
    public ResponseEntity<BallShowVO> update(@Valid @RequestBody BallUpdateDTO ballUpdateDTO) {
        BallPO ball = ballService.update(ballUpdateDTO);
        return ResponseEntity.ok(BallMapper.INSTANCE.toShowVO(ball));
    }

    @Override
    @GetMapping
    public ResponseEntity<PageVO<BallListVO>> list(@Valid BallQO ballQO) {
        PageVO<BallListVO> page = ballService.list(ballQO);
        return ResponseEntity.ok(page);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<BallShowVO> show(@PathVariable String id) {
        BallShowVO ballShowVO = ballService.show(id);
        return ResponseEntity.ok(ballShowVO);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> delete(@PathVariable String id) {
        int count = ballService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Integer> deleteBatch(@RequestBody String[] id) {
        if (ArrayUtils.isEmpty(id)) {
            throw new BusinessException(ErrorCode.PARAM_IS_NULL);
        }
        int count = ballService.delete(id);
        return ResponseEntity.ok(count);
    }

}


