package just.fo.fun.config;

import just.fo.fun.exception.MessageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionTranslator {


    @ExceptionHandler(MessageException.class)
    public ResponseEntity<String> processMessageException(final MessageException ex) {
        log.error("There is something wrong: " + ex);
//
//        ErrorVM errorVM = ex.processValidationError();
//
//        if(errorVM.getFieldErrors().isEmpty() == false) {
//            List<FieldErrorVM> res = errorVM.getFieldErrors()
//                    .stream()
//                    .map(item -> {
//                        String localizedMsg = commonUtil.getMessage(item.getMessage());
//                        return new FieldErrorVM(item.getObjectName(), item.getField(), localizedMsg);
//                    })
//                    .collect(Collectors.toList());
//            errorVM.setFieldErrors(res);
//            return new ResponseEntity<>(errorVM, ex.getHeaders(), ex.getHttpStatus());
//        }
//
//        if(ex.isNeedI18N()) {
//            ErrorVM errorInfo = ex.getErrorInfo();
//            String i18nTranslationCode = errorInfo.getDescription();
//            String translation = commonUtil.getMessage(i18nTranslationCode);
//            errorInfo = new ErrorVM(errorInfo.getMessage(), translation);
//
//            return new ResponseEntity<>(errorInfo, ex.getHeaders(), ex.getHttpStatus());
//        }

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> processException(final Exception ex) {
        log.error("There is something wrong: " + ex);
        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }




}