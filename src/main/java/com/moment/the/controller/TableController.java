package com.moment.the.controller;

import com.moment.the.domain.AnswerDomain;
import com.moment.the.domain.TableDomain;
import com.moment.the.dto.TableDto;
import com.moment.the.service.TableService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class TableController {
    private final TableService tableService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    // localhost:8080/v1/uncomfortable
    @PostMapping("/uncomfortable")
    public void write(@RequestBody TableDto tableDto){
        tableService.write(tableDto);
    }
    // localhost:8080/v1/uncomfortable
    @GetMapping("/uncomfortable")
    public List<TableDomain> top10(){
        return tableService.view();
    }
    // localhost:8080/v1/uncomfortable/{boardIdx}
    @PutMapping("/uncomfortable/{boardIdx}")
    public void goods(@PathVariable Long boardIdx){
        tableService.goods(boardIdx);
    }
    // localhost:8080/v1/uncomfortable/cancel/{boardIdx}
    @PutMapping("/uncomfortable/cancel/{boardIdx}")
    public void cancelGood(@PathVariable Long boardIdx){
        tableService.cancelGood(boardIdx);
    }
    //하려다가 실패한것
//
//    @GetMapping("/")
//    public List<AnswerDomain> findAll(){
//        return tableService.findAll();
//    }
}
