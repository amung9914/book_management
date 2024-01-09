document.addEventListener('DOMContentLoaded', function() {

    let validEmail = false;
    let validEmailForRegex = false;
    // 이메일 정규 표현식
    const regexEmail =/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
    const elp = document.getElementById("elp");

    // 아이디 중복검사 결과 요청
    const email =  document.getElementById("memberName");
    email.addEventListener("focusout",function (){

        let message = "올바른 이메일 형식이 아닙니다.";
        validEmailForRegex = checkRegex(elp,email.value,regexEmail,message);

        const validName = document.getElementById("valid_name");
        function success(){
            showMessage(validName,"이메일: 사용할 수 있는 이메일입니다",true);
            validEmail = true;
            document.getElementById("mail_btn").disabled = false;
        }
        function fail(){
            showMessage(validName,"이메일: 사용할 수 없는 이메일입니다",false);
            document.getElementById("mail_btn").disabled = true;
        }

        httpRequestGet("/signup/nameCheck/"+email.value,success,fail);
    });


    let boolPassword = false;
    // 특수문자 포함 비밀번호
    var regexPass = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;
    const password = document.getElementById("password");
    password.addEventListener("focusout", function (){
        let valP = this.value;
        let message = "비밀번호: 특수문자포함 영문/숫자 조합 8~16이내 작성"
        boolPassword = checkRegex(elp,valP,regexPass,message);
    })

    // 비밀번호 일치 여부 체크
    let boolPassCheck = false;
    let valP = document.getElementById("re_pw");
    if(valP){
        valP.addEventListener("input", function() {
            let originalVal = document.getElementById("password").value;
            let message = "비밀번호가 일치하지 않습니다";
            if(originalVal === this.value){
                boolPassCheck = true;
            }else{
                boolPassCheck = false;
            }
            showMessage(elp,message,boolPassCheck);
        })
    }

    /* 이메일 인증 */
    let confirmCode = null;

    const mailBtn = document.getElementById("mail_btn");
    mailBtn.addEventListener("click",function (){
        if(validEmailForRegex){
            data = JSON.stringify({
                "mail" : document.getElementById("memberName").value
            });
            function success(response){
                alert("인증번호 발송");
                confirmCode = response.data;
            }
            function fail(){
                alert("메일전송실패");
            }
            httpRequestForMail("POST","/mail",data,success,fail);
        }
    });

    let boolEmailCode = false;
    document.getElementById("emailAcceptBtn").addEventListener("click", function(){
       let userCode = document.getElementById("emailCode").value;
       if(confirmCode == userCode){
           alert("이메일 인증이 완료되었습니다.")
           boolEmailCode = true;
           document.getElementById("mail_btn").disabled = true;
       }else{
           boolEmailCode = false;
           alert("인증코드를 다시 확인해주세요.");
       }

    });
    /* 이메일 인증 END */


    /* 회원 가입 */
    const joinForm = document.getElementById('join-form');
    if(joinForm){
        document.getElementById('join-form').addEventListener('submit',function (event){
            event.preventDefault();

            body = JSON.stringify({
                "memberName" : document.getElementById("memberName").value,
                "password" : document.getElementById("password").value
            });

            function success(){
                alert("회원 가입이 완료되었습니다.");
                location.replace("/");
            }
            function fail(){
                alert("회원 가입이 실패했습니다.");
                location.replace("/");
            }

            if(!validEmail){
                // 이메일 유효성 검사 완료 여부
                alert("이메일을 확인해주세요.");
                document.getElementById("memberName").focus();
            }else if(!boolEmailCode){
                alert("이메일 인증을 완료해주세요.");
                document.getElementById("memberName").focus();
            }
            else if(!boolPassword){
                alert("비밀번호를 확인해주세요.");
                document.getElementById("password").focus();
            }else if(!boolPassCheck){
                alert("비밀번호를 일치 여부를 확인해주세요.");
                document.getElementById("re_pw").focus();
            }

            else{
                httpRequest("POST","member",body,success,fail);
            }

        });
    } // joinForm end
});

// 검증 여부에 따른 결과 메세지 출력
// (표시할요소, 메세지, 검사성공여부)
function showMessage(box,messageP,isCheck){

    let html = '<span class="notice">' + messageP+'</span>';
    box.innerHTML = html;

    if(isCheck){
        box.style.display = 'none';
    }else{
        box.style.display = 'block';
    }

}

// HTTP GET 요청을 보내는 함수
function httpRequestGet(url,success,fail) {
    fetch(url, {
        method: "GET",
    }).then(response => {
        if (response.status === 200 || response.status === 201) {
            return response.json();
        } else {
            fail();
        }
    }).then(data => {
        if (data) {
            success(data);
        }
    })
}

// 정규 표현식 검사
// checkRegex(메세지를 출력할 요소,검사할 값,비교할 정규표현식,출력할 메세지,)
function checkRegex(elP,valP,regexP,messageP){
    // 정규표현식 패턴과 사용자가 작성한 내용의 패턴이 일치하지 않음
    if(regexP.test(valP) === false){
        showMessage(elP,messageP,false);
        return false;
    }else if(regexP.test(valP) !== false){
        showMessage(elP,messageP,true);
        return true;
    }
}

// 이메일 인증 관련 함수
function httpRequestForMail(method,url,body,success,fail) {
    fetch(url, {
        method: method,
        headers: {
            'Content-Type':'application/json'
        },
        body: body,
    }).then(response => {
        if (response.status === 200 || response.status === 201) {
            return response.json();
        } else {
            fail();
        }
    }).then(data => {
        if (data) {
            success(data);
        }
    })
}