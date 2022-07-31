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
    D save(D d) throws ParseException, IOException;
    List<D> findAll() throws IOException;
    D findById(ID id) throws IOException;
    void deleteById(ID id);
}
