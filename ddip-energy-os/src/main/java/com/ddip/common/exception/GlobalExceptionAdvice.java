package com.ddip.common.exception;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddip.common.enums.CommonEnum;
import com.ddip.common.response.ResultModel;
import com.ddip.util.LogUtil;

@ControllerAdvice
public class GlobalExceptionAdvice {
	
	/**
	 * 业务异常捕捉
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(BusinessException.class)
	public ResultModel<Object> handlerBusinessException(BusinessException ex){
		LogUtil.info("global BusinessException Msg : ",ex);
		return ResultModel.error(ex.getCode(), ex.getMessage());
	}
	
	/**
	 * 全局异常捕捉
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResultModel<Object> handlerException(Exception ex){
		LogUtil.info("global exception Msg : ",ex);
		return ResultModel.error(CommonEnum.FAILED.getCode(), ex.getMessage());
	}
	
 
	@ResponseBody
	@ExceptionHandler({BindException.class,ConstraintViolationException.class,MethodArgumentNotValidException.class})
	public ResultModel<Object> handlerMethodArgumentNotValidException(Exception ex){
		LogUtil.info("handlerMethodArgumentNotValidException:{} : ",ex.getMessage());
		StringBuilder sb = new StringBuilder();
		Map<String,String> errorMap = new HashMap<String,String>();
		String msg = "";
		if(ex instanceof ConstraintViolationException) {
			for(@SuppressWarnings("rawtypes") ConstraintViolation cv:((ConstraintViolationException) ex).getConstraintViolations()) {
				msg = cv.getMessage();
				sb.append(msg).append(";");
				Iterator<Path.Node> it = cv.getPropertyPath().iterator();
				Path.Node last = null;
				while(it.hasNext()) {
					last = it.next();
				}
				String name = (last!=null)?last.getName():"";
				errorMap.put(name, msg);
			}
			return ResultModel.error(CommonEnum.REQUEST_PARAM_ERROR.getCode(),msg);
		}else {
			List<ObjectError> allErrors = new ArrayList<ObjectError>();
			if(ex instanceof BindException) {
				allErrors = ((BindException) ex).getAllErrors();
			}else if(ex instanceof MethodArgumentNotValidException) {
				MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
				allErrors = exception.getBindingResult().getAllErrors();
				FieldError fieldError = exception.getBindingResult().getFieldError();
				assert fieldError !=null;
			}
			if(!CollectionUtils.isEmpty(allErrors)) {
				for(ObjectError oe:allErrors) {
					msg = oe.getDefaultMessage();
					sb.append(msg).append(";");
					if(oe instanceof FieldError) {
						errorMap.put(((FieldError)oe).getField(),msg);
					}else {
						errorMap.put(oe.getObjectName(), msg);
					}
				}
			}
			return ResultModel.error(CommonEnum.REQUEST_PARAM_ERROR.getCode(), sb.toString());
		}
	}	
}
