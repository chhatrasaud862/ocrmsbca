package com.ocrms.ocrmsbca.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrmsbca
 * @Date 04/07/2022
 */
public interface GenericCrudServiceImpl<D, ID>{
    D save(D d);
    List<D> findAll();
    D findById(ID id);
    void deleteById(ID id);
}
