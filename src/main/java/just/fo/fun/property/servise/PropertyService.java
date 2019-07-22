package just.fo.fun.property.servise;


import just.fo.fun.entities.Property;
import just.fo.fun.property.repository.PropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;


    public Long getLongPropertyByCode(PropertyCode code) {

        String value = propertyRepository.getPropertyByCode(code.name()).getValue();

        try {
            return Long.valueOf(value);
        }catch (NumberFormatException e){
            log.error("cannot convert value {} to long", value);
            return null;
        }

    }


    public String getStringPropertyByCode(PropertyCode code) {
        return  propertyRepository.getPropertyByCode(code.name()).getValue();
    }

    public Property getPropertyByCode(PropertyCode code) {
        return propertyRepository.getPropertyByCode(code.name());
    }

    public enum PropertyCode{

        HOT_PAGE_LEVEL,
        HOT_PAGE_DAYS,
        LOGIN_REGEX,
        PASSWORD_REGEX,
        ACCEPTABLE_AGE,
        USER_LOCK_TIME,
        USER_INCORRECT_ATTEMPT
    }

}
