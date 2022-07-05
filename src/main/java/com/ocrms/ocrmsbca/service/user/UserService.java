package com.ocrms.ocrmsbca.service.user;

import com.ocrms.ocrmsbca.dto.UserDto;
import com.ocrms.ocrmsbca.service.GenericCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 04/07/2022
 */
@Service
public interface UserService extends GenericCrudServiceImpl<UserDto,Long> {
}
