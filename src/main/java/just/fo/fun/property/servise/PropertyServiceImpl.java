package just.fo.fun.property.servise;


import just.fo.fun.entities.Property;
import just.fo.fun.property.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService{


    private final PropertyRepository propertyRepository;


    public Long getLongPropertyByCode(PropertyCode code) {

        String value = propertyRepository.findOneByCodeNotDeleted(code).getValue();

        try {
            return Long.valueOf(value);
        }catch (NumberFormatException e){
            log.error("cannot convert value {} to long", value);
            return null;
        }

    }


    public String getStringPropertyByCode(PropertyCode code) {
        return  propertyRepository.findOneByCodeNotDeleted(code).getValue();
    }

    public Property getPropertyByCode(PropertyCode code) {
        return propertyRepository.findOneByCodeNotDeleted(code);
    }

}
