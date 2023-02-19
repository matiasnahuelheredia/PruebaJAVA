package com.matiasheredia.JavaTest.model.Validations;

import com.matiasheredia.JavaTest.model.Entities.Message;
import com.matiasheredia.JavaTest.model.Exceptions.MessageEmailFormatException;
import com.matiasheredia.JavaTest.model.Exceptions.MessageValueFormatException;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class KafkaMessageValidation implements MessageValidation{
    final int MAX_EMAIL_LENGTH=50;
    final int MAX_MSG_CHARACTERS_LENGTH=255;
    @Override
    public void validateMessage(Message message) {
        if(!this.isEmailFormatValid(message))       throw MessageEmailFormatException.of(message);
        if(this.isEmailLengthTooLarge(message))     throw MessageEmailFormatException.of(message);
        if (this.isEmailLengthTooLarge(message))    throw MessageValueFormatException.of(message);
    }

    private boolean isEmailFormatValid(Message message)
    {
        final String regexPattern = "^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern).matcher(message.email()).matches();
    }
    private boolean isEmailLengthTooLarge(Message message)
    {
        return message.email().length()>this.MAX_EMAIL_LENGTH ? true:false;
    }

    private boolean isMsgLengthTooLarge(Message message)
    {
        return message.message().length()>this.MAX_MSG_CHARACTERS_LENGTH ? true:false;
    }
}
