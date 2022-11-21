package hello.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class IntegerToStringConverter implements Converter<Integer, String> {

    /**
     * 숫자를 문자로 변환
     */
    @Override
    public String convert(Integer source) {
        log.info("convert source={}", source);

        return String.valueOf(source);
    }
}

