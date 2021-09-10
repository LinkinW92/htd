package com.skeqi.mes.util.chenj;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class ExceptionHandlerController {

//    private static Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    // HttpMessageNotReadableException
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public void hadlerException(final Exception ex, final WebRequest req, HttpServletResponse response) throws IOException {
//        Map<String, String> errorMap = new HashMap<>();

        System.err.println("拦截异常-" + ex.getLocalizedMessage());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        StringBuilder sb = new StringBuilder();
        sb.append("{").append('"').append("code").append('"').append(":").append("400").append(",")
                .append('"').append("msg").append('"').append(":").append('"').append("数据格式不匹配").
                append('"').append(",").append('"').append("result").append('"').append(":").append("null").append("}");
        PrintWriter out = response.getWriter();
        out.println(sb.toString());
        return;

//        BindingResult results = ((MethodArgumentNotValidException) ex).getBindingResult();
        //参数错误
//        if(ex instanceof MethodArgumentNotValidException){
//            BindingResult result = ((MethodArgumentNotValidException) ex).getBindingResult();
//            wrapperError(result,errorMap);
//            System.err.println("参数错误");
//            return new ResponseEntity<Object>(errorMap, HttpStatus.OK);
//        }
        //其他错误
//        errorMap.put("code","400");
//        errorMap.put("msg","系统错误,请稍后再试");

//        return new ResponseEntity<Object>(errorMap, HttpStatus.OK);

    }

//    private void wrapperError(BindingResult result, Map<String, String> errorMap) {
//        List<ObjectError> list = result.getAllErrors();
//        String eMsg = "";
//        if (list.size() > 0) {
//            eMsg = list.get(0).getDefaultMessage();
//        }
//        errorMap.put("code", "102");
//        errorMap.put("msg", eMsg);
//    }
}
