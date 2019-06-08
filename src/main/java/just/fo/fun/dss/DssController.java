package just.fo.fun.dss;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.stachek66.nlp.mystem.holding.MyStemApplicationException;

@Slf4j
@RestController
@RequestMapping("/api/dss")
public class DssController {

    @Autowired
    private DssService dssService;

    @PostMapping
    public ResponseEntity calculate(@RequestBody final String source) {

        if (StringUtils.isEmpty(source)){
            return new ResponseEntity<>("incorrect param", HttpStatus.CONFLICT);
        }

        try {
            return new ResponseEntity<>(dssService.calculate(source), HttpStatus.OK);
        } catch (MyStemApplicationException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    @PostMapping("/positive")
    public ResponseEntity addPositiveWord(@RequestBody final String word) {

        if (StringUtils.isEmpty(word)){
            return new ResponseEntity<>("incorrect param", HttpStatus.CONFLICT);
        }

        DssPositiveWord dssPositiveWord = dssService.insertPositiveWord(word);

        if(dssPositiveWord != null) {
            return new ResponseEntity<>(dssPositiveWord, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping("/negative")
    public ResponseEntity addNegativeWord(@RequestBody final String word) {

        if (StringUtils.isEmpty(word)){
            return new ResponseEntity<>("incorrect param", HttpStatus.CONFLICT);
        }

        DssNegativeWord dssPositiveWord = dssService.insertNegativeWord(word);

        if(dssPositiveWord != null) {
            return new ResponseEntity<>(dssPositiveWord, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @DeleteMapping("/positive{word}")
    public ResponseEntity deletePositiveWord(@PathVariable final String word) {

        if (StringUtils.isEmpty(word)){
            return new ResponseEntity<>("incorrect param", HttpStatus.CONFLICT);
        }

        Long count = dssService.deletePositiveWord(word);

        if(count != null && count > 0) {
            return new ResponseEntity<>(count, HttpStatus.OK);
        }
        return new ResponseEntity<>(count, HttpStatus.CONFLICT);

    }

    @DeleteMapping("/negative{word}")
    public ResponseEntity deleteNegativeWord(@PathVariable final String word) {

        if (StringUtils.isEmpty(word)){
            return new ResponseEntity<>("incorrect param", HttpStatus.CONFLICT);
        }

        Long count = dssService.deleteNegativeWord(word);

        if(count != null && count > 0) {
            return new ResponseEntity<>(count, HttpStatus.OK);
        }
        return new ResponseEntity<>(count, HttpStatus.CONFLICT);


    }
}
