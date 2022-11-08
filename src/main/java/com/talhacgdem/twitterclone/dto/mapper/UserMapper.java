package com.talhacgdem.twitterclone.dto.mapper;

import com.talhacgdem.twitterclone.dto.request.UserUpdateDto;
import com.talhacgdem.twitterclone.entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    private boolean isNewValue(Object target, Object source) {
        if (source != null){
            return !source.equals(target);
        }
        return false;
    }

    public User convertFromUpdateDto(User to, UserUpdateDto from) {
        to.setBirthday(isNewValue(to.getBirthday(), from.getBirthday()) ? from.getBirthday() : to.getBirthday());
        to.setBiography(isNewValue(to.getBiography(), from.getBiography()) ? from.getBiography() : to.getBiography());
        to.setPassword(isNewValue(to.getPassword(), from.getPassword()) ? from.getPassword() : to.getPassword());
        to.setLastName(isNewValue(to.getLastName(), from.getLastName()) ? from.getLastName() : to.getLastName());
        to.setFirstName(isNewValue(to.getFirstName(), from.getFirstName()) ? from.getFirstName() : to.getFirstName());
        return to;
    }
}
