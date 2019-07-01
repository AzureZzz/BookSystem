package com.guet.service.impl;

import com.guet.dao.CollectMapper;
import com.guet.entity.CollectKey;
import com.guet.service.ICollectService;
import com.guet.utils.ReturnMessage;
import com.guet.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CollectServiceImpl implements ICollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Override
    public void addCollect(CollectKey collectKey) throws Exception {
        collectMapper.insert(collectKey);
    }

    @Override
    public void deleteCollect(CollectKey collectKey) throws Exception {
        collectMapper.deleteByPrimaryKey(collectKey);
    }

    @Override
    public Map<String, Object> getReaderCollects(String username, String aoData) throws Exception {
        Map<String,Object> params = ServiceUtils.getAoDataParams(aoData);
        String sEcho = (String) params.get("sEcho");
        int iDisplayStart = (int) params.get("iDisplayStart");
        int iDisplayLength = (int) params.get("iDisplayLength");
        String column = "username";
        boolean sortDir = (boolean) params.get("sortDir");
        String keyWord = (String) params.get("keyWord");

        int totalCount = collectMapper.selectReaderCount(username);
        List<CollectKey> collectKeys =  collectMapper.selectReaderCollects(username,iDisplayStart,iDisplayLength,sortDir,column,keyWord);
        int displayCount = collectKeys.size();
        return ReturnMessage.dataTablesResult(sEcho,totalCount,displayCount,collectKeys);
    }
}
