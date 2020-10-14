package cn.code.zeus.inventory.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InventoryUpdateRequest implements IRequest {


    Logger logger = LoggerFactory.getLogger(InventoryUpdateRequest.class);

    @Override
    public void process() {
        logger.info("执行更新库存相关逻辑");
    }
}
