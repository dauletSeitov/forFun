package just.fo.fun.property.servise;

public interface PropertyService {
    Long getLongPropertyByCode(PropertyCode userLockTime);

    String getStringPropertyByCode(PropertyCode passwordRegex);


    enum PropertyCode{

        HOT_PAGE_LEVEL,
        HOT_PAGE_DAYS,
        LOGIN_REGEX,
        PASSWORD_REGEX,
        ACCEPTABLE_AGE,
        USER_LOCK_TIME,
        USER_INCORRECT_ATTEMPT
    }
}
