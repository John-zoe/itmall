package org.notail.mall.dao;

import org.notail.mall.pojo.Member;


public interface MemberDao {
     Member findByMobileAndPwd(String mobile, String pwd);
     void addMember(Member member);
}
