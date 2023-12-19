document.addEventListener('DOMContentLoaded', function(){

    const joinForm = document.getElementById('join-form');
    if(joinForm){
        /* 회원 가입 */
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

            httpRequest("POST","member",body,success,fail);
        });
    } // joinForm end

    const createBook = document.getElementById('create-book-form');
    if(createBook){
        /* 도서 생성 */
        createBook.addEventListener('submit',function (event){
            event.preventDefault();

            body = JSON.stringify({
                "bookName" : document.getElementById("bookName").value,
                "author" : document.getElementById("author").value
            });

            function success(){
                alert("도서가 등록되었습니다.");
                location.replace("/");
            }
            function fail(){
                alert("도서 등록이 실패했습니다.");
                location.replace("/");
            }

            httpRequest("POST","book",body,success,fail);
        });
    } // create-book-form end
});

// HTTP요청을 보내는 함수
function httpRequest(method,url,body,success,fail){
    fetch(url, {
        method: method,
        headers: {
            'Content-Type':'application/json'
        },
        body: body,
    }).then(response =>{
        if(response.status ===200 || response.status ===201){
            return success();
        }else{
            fail();
        }

    });
}