package cn.school.dao;

import cn.school.domain.IconAndMotto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wang on 2017/4/19.
 */
public interface IconAndMottoDao extends Dao<IconAndMotto> {
    int add(IconAndMotto iconAndMotto);

    int del(IconAndMotto iconAndMotto);

    int update(IconAndMotto iconAndMotto);

    IconAndMotto findOneById(Serializable Id);

    List<IconAndMotto> findAll();
}

