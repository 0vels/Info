package cn.school.service;

import cn.school.domain.Queren;

import java.util.List;


public interface QuerenService extends BaseService<Queren> {

    void checkNull(Queren t) throws Exception;

    void add(Queren user)throws Exception;

    void del(Queren user)throws Exception;

    void update(Queren user)throws Exception;

    Queren find(Queren user)throws Exception;

    List<Queren> findAll()throws Exception;
}
