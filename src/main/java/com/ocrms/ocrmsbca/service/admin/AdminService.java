package com.ocrms.ocrmsbca.service.admin;

import com.ocrms.ocrmsbca.dto.AdminDto;
import com.ocrms.ocrmsbca.service.GenericCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 04/07/2022
 */
@Service
public interface AdminService extends GenericCrudServiceImpl<AdminDto,Long> {
}
