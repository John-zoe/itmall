package org.notail.mall.service;

import org.notail.mall.pojo.Member;

public interface MemberService {

    Member login(String mobile, String pwd);

    boolean register(Member member);
}
