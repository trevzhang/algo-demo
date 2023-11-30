# 商品 item, SKU sku, 订单 my_order(id,sku_ids,item_id,is_pay_success,gmt_create)
# 过去7天,统计下单失败率最高的SKU

SELECT sku_id, success_num / order_num AS succ_rate
    FROM (SELECT sku_id,
                 COUNT(id)                       order_num,
                 COUNT_IF(is_pay_success = true) success_num
          FROM (SELECT SPLIT(sku_ids, ',') AS sku_id,
                       is_pay_success,
                       id,
                       gmt_create
                FROM my_order
                WHERE gmt_create >= DATE_ADD(NOW(), -7, 'day')) t1
          GROUP BY sku_id
          )
ORDER BY succ_rate DESC
;
