package com.jk.domain;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @outhor Mr.JK
 * @create 2020-04-27  15:36
 */
public class Cart {

    private Map<Integer,CartItem> items = new LinkedHashMap<>();

    public Cart() {
    }

    public Cart(Map<Integer, CartItem> items) {
        this.items = items;
    }


    public void addItem(CartItem cartItem){
        CartItem item = items.get(cartItem.getId());
        if (item == null){
            items.put(cartItem.getId(),cartItem);
        }else {
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    public void deleteItem(Integer id){
        items.remove(id);
    }

    public void clear(){
        items.clear();
    }

    public void updateCount(Integer id,Integer count){
        CartItem cartItem = items.get(id);
        if (cartItem != null){
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    public Integer getTotalCount(){
        Integer totalCount = 0;
        for (Map.Entry<Integer,CartItem>entry : items.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice(){
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer,CartItem>entry : items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ",totalPrice=" + getTotalPrice() +
                ",items=" + items +
                '}';
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
}
