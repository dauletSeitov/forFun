package just.fo.fun.dss;

import just.fo.fun.dss.twitter.TwitterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/api/dss")
public class DssController {

    @Autowired
    private DssService dssService;

    @Autowired
    private TwitterService twitterService;

    @PostMapping
    public ResponseEntity calculate(@RequestBody final String source) {

        if (StringUtils.isEmpty(source)){
            return new ResponseEntity<>("incorrect param", HttpStatus.CONFLICT);
        }


        return new ResponseEntity<>(dssService.calculate(source), HttpStatus.OK);


    }

    @PostMapping("/twitter")
    public ResponseEntity calculateTwitter(@RequestBody final String query) {

        if (StringUtils.isEmpty(query)){
            return new ResponseEntity<>("incorrect param", HttpStatus.CONFLICT);
        }


        return new ResponseEntity<>(twitterService.calculate(query), HttpStatus.OK);


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

    @DeleteMapping("/positive")
    public ResponseEntity deletePositiveWord(@RequestBody final String word) {

        if (StringUtils.isEmpty(word)){
            return new ResponseEntity<>("incorrect param", HttpStatus.CONFLICT);
        }

        Integer count = dssService.deletePositiveWord(word);

        if(count != null && count > 0) {
            return new ResponseEntity<>(count, HttpStatus.OK);
        }
        return new ResponseEntity<>(count, HttpStatus.CONFLICT);

    }

    @DeleteMapping("/negative")
    public ResponseEntity deleteNegativeWord(@RequestBody final String word) {

        if (StringUtils.isEmpty(word)){
            return new ResponseEntity<>("incorrect param", HttpStatus.CONFLICT);
        }

        Integer count = dssService.deleteNegativeWord(word);

        if(count != null && count > 0) {
            return new ResponseEntity<>(count, HttpStatus.OK);
        }
        return new ResponseEntity<>(count, HttpStatus.CONFLICT);

    }
}
