package com.caput.sp5chap03.main;

import com.caput.sp5chap03.assembler.Assembler;
import com.caput.sp5chap03.config.AppCtx;
import com.caput.sp5chap03.spring.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainForSpring {


    // 도움말 출력 메소드
    private static void printHelp(){
        System.out.println();
        System.out.println("잘못된 명령어입니다. 아래 사용법을 확인하세요.");
        System.out.println("명령어 사용법:");
        System.out.println("new 이메일 이름 암호 암호확인");
        System.out.println("change 이메일 현재암호 변경암호");
        System.out.println();
    }


    private static ApplicationContext ctx = null;

    private static void processNewCommand(String[] arg){
        // 입력된 값이 {"new", "a@a.com", "이름", "암호", "암호 확인"} 의 규정을 지키지 않을 시에 도움말 출력
        if(arg.length != 5){
            printHelp();
            return;
        }
        MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);
        RegisterRequest req = new RegisterRequest();
        req.setEmail(arg[1]);
        req.setName(arg[2]);
        req.setPassword(arg[3]);
        req.setConfirmPassword(arg[4]);

        // 암호 암호확인 일치하지 않을 시
        if(!req.isPasswordEqualToConfirmPassword()){
            System.out.println("암호와 확인이 일치하지 않습니다.");
            return;
        }
        try {
            regSvc.regist(req);
            System.out.println("등록했습니다.\n");
        } catch (DuplicateMemberException e) {
            System.out.println("이미 존재하는 이메일입니다.\n");
        }
    }

    private static void processChangeCommand(String[] arg) {
        // 입력된 값이 {"change", "a@a.com", "현재 암호", "변경할 암호"} 의 규정을 지키지 않을 시에 도움말 출력
        if(arg.length != 4){
            printHelp();
            return;
        }

        ChangePasswordService changePasswordService = ctx.getBean("changePwdSvc", ChangePasswordService.class);

        try{
            changePasswordService.changePassword(arg[1], arg[2], arg[3]);
            System.out.println("암호를 변경했습니다.");
        } catch(MemberNotFoundException e){
            System.out.println("존재하는 이메일입니다.");
        } catch(WrongIdPasswordException e){
            System.out.println("이메일과 암호가 일치하지 않습니다.");
        }
    }

    public static void main(String[] args) throws IOException{
        ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));

        while(true){
            System.out.println("명령어를 입력하세요");
            String command = reader.readLine();
            if(command.equalsIgnoreCase("exit")){
                System.out.println("종료합니다.");
                break;
            }
            if(command.startsWith("new ")){
                processNewCommand(command.split(" "));
                continue;
            }
            else if(command.startsWith("change ")){
                processChangeCommand(command.split(" "));
                continue;
            }
            printHelp();
        }
    }
}
