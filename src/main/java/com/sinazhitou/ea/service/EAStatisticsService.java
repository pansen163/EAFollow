package com.sinazhitou.ea.service;

import com.sinazhitou.ea.model.EALevelsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EAStatisticsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @SuppressWarnings("unchecked")
    public List<EALevelsVo> getEaLevels() {

        List<EALevelsVo> vos = new ArrayList();
        String sql = "select * from userinfo where id<? and username=?";
        vos = (List) jdbcTemplate.query(sql, new Object[]{}, new ResultSetExtractor() {

            public List<EALevelsVo> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<EALevelsVo> list = new ArrayList();
                while (rs.next()) {
                    EALevelsVo vo = new EALevelsVo();
                    vo.setMagic(rs.getInt("magic"));
                    list.add(vo);
                }
                return list;
            }
        });
        return vos;

    }


}
