package com.addressbook.model;
import java.util.List;

import com.addressbook.model.*;
public class FriendService {

    private FriendDAO friendDAO;

    public FriendService() {
        friendDAO = new FriendDAO();
    }

    // 添加朋友
    public void addFriend(FriendPO friend) {
        friendDAO.insertFriend(friend);
    }

    // 获取所有朋友
    public List<FriendPO> getAllFriends() {
        return friendDAO.getAllFriends();
    }

    // 更新朋友地址
    public void updateFriendAddress(String name, String newAddress) {
        friendDAO.updateFriendAddress(name, newAddress);
    }

    // 删除朋友
    public void deleteFriend(String name) {
        friendDAO.deleteFriend(name);
    }
}
