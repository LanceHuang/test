package com.lance.test.aspect.service.impl;

import com.lance.test.aspect.service.IDocumentService;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements IDocumentService {
    @Override
    public String getTitle(int id) {
        return "Document:" + id;
    }
}
