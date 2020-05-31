package org.notail.mall.service.impl;

import org.notail.mall.dao.MemberDao;
import org.notail.mall.dao.impl.MemberDaoImpl;
import org.notail.mall.pojo.Member;
import org.notail.mall.service.MemberService;

public class MemberServiceImpl implements MemberService {
    MemberDao memberDao = new MemberDaoImpl();

    @Override
    public Member login(String mobile, String pwd) {
        Member member = memberDao.findByMobileAndPwd(mobile, pwd);
        return member;
    }



    @Override
    public boolean register(Member member) {
        Member user = memberDao.findByMobileAndPwd(member.getMobile(), member.getPwd());
        if (user == null){
            memberDao.addMember(member);
            return true;
        }
        else{
            return false;
        }
    }
}
