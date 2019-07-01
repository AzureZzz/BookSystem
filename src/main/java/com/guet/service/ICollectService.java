package com.guet.service;


import com.guet.entity.CollectKey;

import java.util.Map;

public interface ICollectService {

    void addCollect(CollectKey collectKey)throws Exception;

    void deleteCollect(CollectKey collectKey)throws Exception;

    Map<String,Object> getReaderCollects(String username, String aoData)throws Exception;
}
