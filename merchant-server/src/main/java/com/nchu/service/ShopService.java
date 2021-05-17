package com.nchu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nchu.bean.*;

import java.io.IOException;

public interface ShopService {

    Result insertStore(Store store) throws IOException;

    Result deleteStore(String id) throws IOException;

    Result findStores(Page page) ;
}
