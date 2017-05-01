package cn.school.service;

import cn.school.dao.IconAndMottoDao;
import cn.school.domain.IconAndMotto;
import cn.school.domain.UserInfor;

import java.util.List;

/**
 * Created by wang on 2017/4/30.
 */
public interface IconAndMottoService extends BaseService<IconAndMotto>{

    void checkNull(IconAndMotto t) throws Exception;

    void add(IconAndMotto iconAndMotto)throws Exception;

    void del(IconAndMotto iconAndMotto)throws Exception;

    void update(IconAndMotto iconAndMotto)throws Exception;

    IconAndMotto find(IconAndMotto iconAndMotto)throws Exception;

    List<IconAndMotto> findAll()throws Exception;
}
