package com.nchu.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nchu.bean.*;
import com.nchu.dao.StoreDao;
import com.nchu.service.GroupService;
import com.nchu.service.ShopService;
import com.nchu.service.StoreTagService;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class ShopServiceImpl extends ServiceImpl<StoreDao, Store> implements ShopService {
    @Resource
    private RestHighLevelClient client;
    @Resource
    private StoreTagService storeTagService;
    @Resource
    private StoreDao storeDao;
    @Resource
    private GroupService groupService;

    @Transactional
    public Result insertStore(Store store) throws IOException {
        storeDao.insert(store);
        //新增店铺食物分类
        List<Group> groups = new LinkedList<>();
        for (String group : store.getGroups()) {
            groups.add(new Group(store.getId(),group));

        }
        //新增店铺分类
        groupService.saveBatch(groups);
        List<StoreTag> storeTags = new LinkedList<>();
        for (String tag : store.getTags()) {
            storeTags.add(new StoreTag(tag,store.getId()));
        }
        storeTagService.saveBatch(storeTags);
        //保存es
        IndexRequest indexRequest = new IndexRequest(ESIndex.STORE).id(store.getId()).source(JSON.toJSONString(store), XContentType.JSON);
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
