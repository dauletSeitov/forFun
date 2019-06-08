package just.fo.fun.dss;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stachek66.nlp.mystem.holding.MyStemApplicationException;

@Slf4j
@RestController
@RequestMapping("/api/dss")
public class DssController {

    @Autowired
    private DssService dssService;

    @GetMapping("/{source}")
    public ResponseEntity getCategory(@PathVariable final String source) {

        try {
            return new ResponseEntity<>(dssService.calculate(source), HttpStatus.OK);
        } catch (MyStemApplicationException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }
}
