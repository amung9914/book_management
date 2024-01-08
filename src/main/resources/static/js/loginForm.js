document.addEventListener('DOMContentLoaded', function() {


    const elp = document.getElementById("elp");

    /**
     *  아래부터 로그인 위한 수정 필요
     */

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


// HTTP GET 요청을 보내는 함수
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