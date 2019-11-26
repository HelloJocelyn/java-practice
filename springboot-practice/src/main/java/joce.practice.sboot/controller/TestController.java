package joce.practice.sboot.controller;

import io.swagger.annotations.*;
import joce.practice.sboot.entity.RequestVO;
import joce.practice.sboot.entity.RespVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "test api")
public class TestController {
    @PostMapping("/tests")
    @ApiOperation("test test test")
    @ApiResponses({
            @ApiResponse(code=200,message = "success",examples = @Example({@ExampleProperty(value = "aaa",mediaType = MediaType.APPLICATION_JSON_VALUE)})),
            @ApiResponse(code = 401,message = "unauthorized",examples = @Example({@ExampleProperty(value = "bbbb",mediaType = MediaType.APPLICATION_JSON_VALUE)}))
    })
    public RespVO getTests(@RequestBody RequestVO requestVO){
        RespVO respVO = new RespVO();
        respVO.setData("aaa");
        respVO.setStatus(101);
//        return "test";
        return respVO;
    }
}
