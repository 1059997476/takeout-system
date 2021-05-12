package com.nchu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nchu.bean.*;
import com.nchu.dao.StoreDao;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Service
public class ShopService extends ServiceImpl<StoreDao, Store> {
    @Resource
    private RestHighLevelClient client;
    @Resource
    private StoreDao storeDao;
    @Resource
    private GroupService groupService;

    public Result insertStore(Store store, List<Group> groups) throws IOException {
        int i = storeDao.insert(store);
        boolean b = groupService.saveBatch(groups);
        if(i==0){
            return new Result("新增失败", Code.error.getCode());
        }
        IndexRequest indexRequest = new IndexRequest(ESIndex.STORE).id(store.getId()).source(store, XContentType.JSON);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        return new Result("新增成功", Code.success.getCode());
    }
    public Result deleteStore(String id) throws IOException {
        Store store = new Store();
        store.setId(id).setFlag(1);
        storeDao.updateById(store);
        UpdateRequest updateRequest = new UpdateRequest();
        client.update(updateRequest,RequestOptions.DEFAULT);
        return new Result("",Code.success.getCode());
    }
    public Result findStores(Page page){
        return new Result("",Code.success.getCode());
    }
}
