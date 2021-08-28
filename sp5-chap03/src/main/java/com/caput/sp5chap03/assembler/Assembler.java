package com.caput.sp5chap03.assembler;

import com.caput.sp5chap03.spring.ChangePasswordService;
import com.caput.sp5chap03.spring.Member;
import com.caput.sp5chap03.spring.MemberDao;
import com.caput.sp5chap03.spring.MemberRegisterService;

public class Assembler {

    private MemberDao memberDao;
    private MemberRegisterService regSvc;
    private ChangePasswordService pwdSvc;

    public Assembler() {
        memberDao = new MemberDao();
        regSvc = new MemberRegisterService(memberDao);
        pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao);
    }

    public MemberDao getMemberDao(){
        return memberDao;
    }

    public MemberRegisterService getMemberRegisterService(){
        return regSvc;
    }

    public ChangePasswordService getChangePasswordService() {
        return pwdSvc;
    }
}
