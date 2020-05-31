package org.notail.mall.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.notail.mall.dao.MemberDao;
import org.notail.mall.pojo.Member;
import org.notail.mall.utils.DateUtil;
import org.notail.mall.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.Date;

public class MemberDaoImpl implements MemberDao {

    @Override
    public Member findByMobileAndPwd(String mobile, String pwd) {
        Member member = null;
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        String sql = "select * from member where mobile = ? and pwd = ?";
        try {
            member = runner.query(sql, new BeanHandler<>(Member.class), mobile, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        }
        return member;
    }

    @Override
    public void addMember(Member member) {
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        String sql = "insert into member values(null,?,?,?,?,?,?,?)";
        member.setRegister_time(new Date());
        try {
            runner.update(sql,member.getMobile(),member.getPwd(),member.getNick_name(),member.getReal_name(),member.getEmail(),member.getGender(), DateUtil.formatDate(member.getRegister_time()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
