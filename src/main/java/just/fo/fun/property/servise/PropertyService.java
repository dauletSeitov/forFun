package just.fo.fun.property.servise;


import just.fo.fun.property.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;


    public <T> T getPropertyByCode(PropertyCode code) {


            return (T) propertyRepository.getPropertyByCode(code.name()).getValue();

    }

    public enum PropertyCode{

        HOT_PAGE_LEVEL
    }

}
