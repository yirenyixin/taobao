package jmu.dao;

import jmu.vo.Product;
import jmu.vo.address;

import java.util.List;

public interface IaddressDAO {
    public List<address> queryAll(address addr) throws Exception ;

    public void update(address addr) throws Exception;

    public void insert(address addr)throws Exception;

    public void delect(address addr)throws Exception;
}
