package just.fo.fun.dss;

import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.stachek66.nlp.mystem.holding.Factory;
import ru.stachek66.nlp.mystem.holding.MyStem;
import ru.stachek66.nlp.mystem.holding.MyStemApplicationException;
import ru.stachek66.nlp.mystem.holding.Request;
import ru.stachek66.nlp.mystem.model.Info;
import scala.Option;
import scala.collection.JavaConversions;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
@Service
@Slf4j
public class DssService {

    @Autowired
    private DssPositiveWordRepository dssPositiveWordRepository;

    @Autowired
    private DssNegativeWordRepository dssNegativeWordRepository;

    private static final EnglishAnalyzer en_an = new EnglishAnalyzer(Version.LUCENE_31);
    private static final QueryParser parser = new QueryParser(Version.LUCENE_31, "", en_an);

    private static final MyStem mystemAnalyzer = new Factory("-igd --eng-gr --format json --weight").newMyStem("3.0", Option.<File>empty()).get();



    public DssModel calculate(String source, boolean isRussianWord) {

        boolean isEnglishSource = false;
        List<String> originalWords = null;


        List<String> sliperator = separator(source);
        if(isRussianWord) {
            originalWords =  normalizeRussian(sliperator);
        } else {
            originalWords = normalizeEnglish(sliperator);
        }


        LinkedList<String> positiveSelectedWords = new LinkedList<>();
        LinkedList<String> negativeSelectedWords = new LinkedList<>();

        for (int i = 0; i < originalWords.size(); i++) {

            if (dssPositiveWordRepository.contains(originalWords.get(i))) {
                positiveSelectedWords.add(originalWords.get(i));
            }

            if (dssNegativeWordRepository.contains(originalWords.get(i))) {
                negativeSelectedWords.add(originalWords.get(i));

            }

        }

        Integer neutralWordsCount = originalWords.size() - negativeSelectedWords.size() - positiveSelectedWords.size();



        double negativePercent = negativeSelectedWords.size() / (double) (negativeSelectedWords.size() + positiveSelectedWords.size()+neutralWordsCount);
        double positivePercent = positiveSelectedWords.size() / (double) (negativeSelectedWords.size() + positiveSelectedWords.size()+neutralWordsCount);
        double neutralPercent = neutralWordsCount / (double) (negativeSelectedWords.size() + positiveSelectedWords.size()+neutralWordsCount);

        if (Double.isNaN(negativePercent)){
            negativePercent = 0;
        }
        if (Double.isNaN(positivePercent)){
            positivePercent = 0;
        }



        return new DssModel(
                negativeSelectedWords,
                positiveSelectedWords,
                negativeSelectedWords.size(),
                positiveSelectedWords.size(),
                negativePercent,
                positivePercent,
                originalWords,
                originalWords.size() - negativeSelectedWords.size() - positiveSelectedWords.size(),
                sliperator,
                neutralPercent

        );

    }


    private static List<String> normalizeRussian(final List<String> text) {

        List<String> res = new ArrayList<>();

        for (String word : text) {

            final Iterable<Info> result;
            try {

                result = JavaConversions.asJavaIterable(
                        mystemAnalyzer.analyze(Request.apply(word))
                                .info()
                                .toIterable()
                );


                for (final Info info : result) {
                    Option<String> lex = info.lex();

                    if (lex.nonEmpty()) {
                        res.add(lex.get());
                    } else
                        res.add(word);
                }
            } catch (MyStemApplicationException e) {
                e.printStackTrace();
            }
        }

        return res;
    }

    private static List<String> separator(final String text){
        String[] split = text.replaceAll("\"", " ").replaceAll("[^a-zA-Zа-яА-Я]", " ").toLowerCase().split("\\s+");
        return  Stream.of(split).filter(st -> st.length() > 0).collect(Collectors.toList());
    }


    private static List<String> normalizeEnglish(List<String> text) {

        List<String> res = new ArrayList<>();

        for(String word : text) {
            try {
                Query parse = parser.parse(word);
                if (StringUtils.isEmpty(parse.toString()))
                    res.add(word);
                else
                    res.add(parse.toString());
            } catch (ParseException e) {
                res.add(word);
            }
        }

        return res;
    }


    public Integer deletePositiveWord(String word) {
        return dssPositiveWordRepository.setIsDeletedTrue(word);
    }

    public Integer deleteNegativeWord(String word) {
        return dssNegativeWordRepository.setIsDeletedTrue(word);
    }


    public DssPositiveWord insertPositiveWord(String word) {
        return dssPositiveWordRepository.save(new DssPositiveWord(word));
    }

    public DssNegativeWord insertNegativeWord(String word) {
        return dssNegativeWordRepository.save(new DssNegativeWord(word));
    }
}
